package com.starknet.stark.crypto;

import com.starknet.stark.data.types.Felt;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.signers.DSAKCalculator;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.util.Arrays;

import java.math.BigInteger;

import java.util.List;

import static java.util.Arrays.copyOf;

public class StarknetCurve {
    private static BigInteger CURVE_ORDER = new BigInteger("800000000000010FFFFFFFFFFFFFFFFB781126DCAE7B2321E66A241ADC64D2F",16);

    static {
        NativeLoader.load("crypto_jni");
    }
    //来自外部动态链接库   //未对接-->starkWare

    private static native byte[] pedersen(byte[] first, byte[] second);
    private static byte[] feltToNative(Felt input) {
        return bigintToNative(input.getValue());
    }
    //bigInt 2 native
    private static byte[] bigintToNative(BigInteger input) {
        byte[] converted = input.toByteArray();
        //转换为小端序
        Arrays.reverse(converted);
        if (converted.length == 32) {
            return converted;
        }
        return copyOf(converted,32);
    }
    public static Felt pedersen(Felt first, Felt second) {
        byte[] hash = pedersen(feltToNative(first), feltToNative(second));
        //改回大端序
        byte[] hashValue = Arrays.reverse(hash);
        return new Felt(new BigInteger(hashValue));
    }
    public static Felt pedersen(Iterable<Felt> values) {
        Felt result = Felt.ZERO;
        for (Felt value : values) {
            result = pedersen(result, value);
        }
        return result;
    }
    public static Felt pedersenOnElements(List<Felt> values) {
        Felt innerPedersen = pedersen(values);
        Felt sizeFelt = new Felt(new BigInteger(String.valueOf(values.size())));
        return pedersen(innerPedersen, sizeFelt);
    }

    public static Felt pedersenOnElements(Felt... values) {
        return pedersenOnElements(java.util.Arrays.asList(values));
    }

    private static native byte[]  sign(byte[]  privateKey ,byte[]  hash, byte[]  k);
    public   static StarknetCurveSignature sign(Felt privateKey, Felt hash, BigInteger k) {
        byte[] signature = sign(
                feltToNative(privateKey),
                feltToNative(hash),
                bigintToNative(k)
        );
        BigInteger r = new BigInteger(Arrays.copyOfRange(signature, 0, 32));
        BigInteger w = new BigInteger(Arrays.copyOfRange(signature, 32, 64));
        BigInteger s = w.modInverse(CURVE_ORDER);
        return new StarknetCurveSignature(new Felt(r), new Felt(s));
    }

    public static StarknetCurveSignature sign(Felt privateKey, Felt hash)   {
        HMacDSAKCalculator cal = new HMacDSAKCalculator(new SHA256Digest());
        cal.init(CURVE_ORDER, privateKey.getValue(), hash.getValue().toByteArray());


        // Generated K might not be suitable for signing. Probability of it is very low.
        // https://github.com/starkware-libs/cairo-lang/blob/167b28bcd940fd25ea3816204fa882a0b0a49603/src/starkware/crypto/starkware/crypto/signature/signature.py#L141
        Exception lastError = null;
        for (int i=0;i<3;i++) {
            try {
                return sign(privateKey, hash, cal.nextK());
            } catch (IllegalArgumentException e) {
                // This shouldn't really happen, all Felt instances should work
                throw e;
            } catch (Exception e) {
                lastError = e;
            }
        }

        if (lastError == null) {
            // Impossible
            throw new AssertionError("No signature or error after signing.");
        }


        throw new RuntimeException(lastError);
    }
}

