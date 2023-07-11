package com.starknet.stark.data.types;

import com.starknet.stark.util.ByteUtils;
import com.starknet.stark.util.HexUtils;
import com.sun.istack.internal.NotNull;
import org.omg.CORBA.PUBLIC_MEMBER;
import sun.security.timestamp.TSRequest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Felt {
    //蒙哥马利表示  a'= a * N   //some err in this ,and i don't know why
    public static BigInteger PRIME = new BigInteger("800000000000011000000000000000000000000000000000000000000000001", 16);
    public static BigInteger kMontgomeryR = new BigInteger("7fffffffffffdf0ffffffffffffffffffffffffffffffffffffffffffffffe1", 16);
    public static BigInteger kMontgomeryRSquared = new BigInteger("7ffd4ab5e008810ffffffffff6f800000000001330ffffffffffd737e000401", 16);
    public static BigInteger kMontgomeryRCubed = new BigInteger("38e5f79873c0a6df47d84f8363000187545706677ffcc06cc7177d1406df18e", 16);
    public static BigInteger kHalfMultiplicativeGroupSize = new BigInteger("400000000000008800000000000000000000000000000000000000000000000", 16);
    public static BigInteger kModulus = PRIME;
    public static Felt ZERO = new Felt(BigInteger.ZERO);
    public static Felt ONE = new Felt(kMontgomeryR);
    //value
    private BigInteger value;

    public BigInteger getValue() {
        return value;
    }

    //default
    public Felt() {
        this.value = BigInteger.ZERO;
    }

    public Felt(String value) {
        this.value = new BigInteger(value, 16);
    }

    public Felt(BigInteger value) {
        if (BigInteger.ZERO.compareTo(value) > 0) {
            throw new IllegalArgumentException("Default Felt constructor does not accept negative numbers, [$value] given.");
        }
        if (value.compareTo(PRIME) >= 0) {
            throw new IllegalArgumentException("Default Felt constructor accepts values smaller than Felt.PRIME, [$value] given.");
        }
        this.value = value;
    }

    public Felt(long value) {
        this(BigInteger.valueOf(value));
    }

    public Felt(int value) {
        this(BigInteger.valueOf(value));
    }

    //运算
    public Felt add(Felt rhs) {
        return new Felt(this.value.add(rhs.getValue()).mod(this.kModulus));
    }

    public Felt sub(Felt rhs) {
        if (this.compareTo(rhs) >= 0) {
            return new Felt(this.value.subtract(rhs.value));
        }
        return new Felt(this.value.add(kModulus).subtract(rhs.getValue()));
    }


    //montgomery mul
    public Felt mul(Felt rhs) {
        value.multiply(rhs.getValue()).multiply(kMontgomeryRSquared).mod(kModulus);
        return this;
    }

    public boolean IsSquare() {
        if (this.value == ZERO.value) {
            return true;
        }
        // value is a square if and only if value^((p-1) / 2) = 1.   //费马定理
        return this.pow((kHalfMultiplicativeGroupSize.toString(2))).compareTo(this.ONE) == 0;
    }

    //蒙哥马利模幂
    public Felt pow(String exponentBits) {
        return genericPow(
                this, exponentBits, Felt.ONE,
                (multiplier, dst) -> dst.mul(multiplier)
        );
    }

    private static Felt genericPow(Felt base, String exponentBits, Felt identity, PowOperation powOperation) {

        System.out.println("this is bit:"+exponentBits);
        Felt result = identity;

        for (int i = exponentBits.length() - 1; i >= 0; i--) {
            System.out.println("result:"+result.getValue());
            result = powOperation.apply(base, result);

            if (exponentBits.charAt(i) == '1') {
                result = result.mul(base);
            }
        }
        return result;
    }

    private interface PowOperation {
        Felt apply(Felt multiplier, Felt dst);
    }

    public Felt divide(Felt rhs) {
        return new Felt(this.value.multiply(rhs.kMontgomeryRSquared));
    }

    public int compareTo(Felt other) {
        return value.compareTo(other.value);
    }

    public List<Felt> toCalldata() {
        return Collections.singletonList(this);
    }

    @Override
    public String toString() {
        return "Felt{" +
                "value=" + value +
                '}';
    }


    public String hexString() {
        return "0x" + value.toString(16);
    }

    public String decString() {
        return value.toString(10);
    }


    public static void main(String[] args) {
        //alpha
        Felt ALPHA = new Felt(new BigInteger("1", 16));
        System.out.println("ALFA:"+ALPHA.getValue());//1

        //BETA
        Felt BETA = new Felt(new BigInteger("6f21413efbe40de150e596d72f7a8c5609ad26c15c915c1f4cdfcb99cee9e89", 16));
        System.out.println("BETA:" + BETA.getValue()); //3141592653589793238462643383279502884197169399375105820974944592307816406665

//        R  //7fffffffffffdf0ffffffffffffffffffffffffffffffffffffffffffffffe1
        Felt R = new Felt(new BigInteger("7fffffffffffdf0ffffffffffffffffffffffffffffffffffffffffffffffe1", 16));
        System.out.println(R.getValue());
        //order
        Felt order = new Felt(new BigInteger("800000000000010ffffffffffffffffb781126dcae7b2321e66a241adc64d2f", 16));
        System.out.println(order.getValue()); //3618502788666131213697322783095070105526743751716087489154079457884512865583

        /////
        Felt a = Felt.ONE;
        System.out.println("a = 1:"+ a.mul(a));
        System.out.println("a = 1:"+a);
        assert a.mul(a).compareTo(a) == 0 : 123;
        ///
        Felt z = Felt.ZERO;
        Felt b = z.sub(a);
        assert a.add(b).compareTo(z) == 0 : 123;

        ///
        Felt c = new Felt("01f5883e65f820d099915c908686b9d1c903896a679f32d65369cbe3b0000005");
        Felt d = new Felt("010644e72e131a0f9b8504b68181585d94816a916871ca8d3c208c16d87cfd42");
        Felt e = new Felt("02fbcd25940b3ae0351661470808122f5d84f3fbd010fd638f8a57fa887cfd47");
        System.out.println("C+D:" + c.add(d).getValue().toString(16));
        assert c.add(d).compareTo(e) == 0 : 123;

        ////
        Felt f = new Felt("17");
        assert c.IsSquare() : 345;
        ////mul
        Felt G = Felt.ONE;
        System.out.println("G = 1:" + G.mul(new Felt(2)));
    }

    /**
     * #############################################################################################
     */
//Encode as ASCII string, with up to 31 characters. Example: 0x68656c6c6f -> "hello".
    public String toShortString() {
        String hexString = value.toString(16);
        return new String(ByteUtils.hexToByte(hexString));

    }

    //ASCII 2 HEX
    public Felt fromShortString(String value) {
        if (value.length() > 31) {
            throw new IllegalArgumentException("Short string cannot be longer than 31 characters.");
        }
        if (!isAscii(value)) {
            throw new IllegalArgumentException("String to be encoded must be an ascii string.");
        }
        return fromHex(ByteUtils.byte2Hex(value.getBytes()));
    }

    @NotNull
    public static Felt fromHex(@NotNull String value) {
        return new Felt(parseHex(value));
    }


    public static BigInteger parseHex(String value) {
        if (!value.startsWith("0x")) {
            throw new IllegalArgumentException("Hex must start with 0x");
        }

        return new BigInteger(value.substring("0x".length()), 10);
    }

    private Boolean isAscii(String str) {
        for (char ch : str.toCharArray()) {
            if (ch > 127) {
                return false;
            }
        }

        return true;
    }
}
