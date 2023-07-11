package com.starknet.stark.crypto;

import com.starknet.stark.crypto.curve.Felt;
import org.bouncycastle.jcajce.provider.digest.Keccak;

import java.math.BigInteger;

public class KeccakUtil {
    private BigInteger MASK_250 = BigInteger.valueOf(2).pow(250) .subtract( BigInteger.ONE);
    public Felt starknetKeccak(byte[] input){
        return new Felt(keccak(input).and(MASK_250));
    }
    public static BigInteger keccak(byte[] input) {
      Keccak.Digest256 keccak = new Keccak.Digest256();
      keccak.update(input);
      return new BigInteger(keccak.digest());
    }
}
