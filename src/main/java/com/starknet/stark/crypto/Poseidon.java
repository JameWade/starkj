package com.starknet.stark.crypto;




import com.starknet.stark.data.types.Felt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Poseidon {
    private static final int m = 3;
    private static final int r = 2;

    static {
        System.loadLibrary("poseidon_jni");
    }

    private static native long[][] hades(long[][] values);

    private static long[] splitBigInteger(BigInteger bigInt) {
        long[] result = new long[4];

        if (bigInt.equals(BigInteger.ZERO)) {
            return result;
        }

        BigInteger mask = BigInteger.valueOf(2).pow(64).subtract(BigInteger.ONE);

        for (int i = 0; i < 4; i++) {
            result[i] = bigInt.shiftRight(i * 64).and(mask).longValue();
        }

        return result;
    }

    private static BigInteger[] toBigIntegerArray(long[] longArray) {
        BigInteger[] bigIntegerArray = new BigInteger[longArray.length];

        for (int i = 0; i < longArray.length; i++) {
            bigIntegerArray[i] = new BigInteger(Long.toUnsignedString(longArray[i]));
        }

        return bigIntegerArray;
    }

    private static BigInteger unsplitBigInteger(long[] arr) {
        List<BigInteger> powersOfTwo = Arrays.asList(
                BigInteger.valueOf(2).pow(0),
                BigInteger.valueOf(2).pow(64),
                BigInteger.valueOf(2).pow(128),
                BigInteger.valueOf(2).pow(192)
        );
        BigInteger[] barr = toBigIntegerArray(arr);
        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < barr.length; i++) {
            result = result.add(barr[i].multiply(powersOfTwo.get(i)));
        }

        return result;
    }

    public static Felt poseidonHash(Felt x) {
        long[] result = hades(new long[][]{
                splitBigInteger(new BigInteger(x.decString())),
                new long[]{0, 0, 0, 0},
                new long[]{1, 0, 0, 0}
        })[0];

        return new Felt(unsplitBigInteger(result));
    }

    public static Felt poseidonHash(Felt x, Felt y) {
        long[] result = hades(new long[][]{
                splitBigInteger(new BigInteger(x.decString())),
                splitBigInteger(new BigInteger(y.decString())),
                new long[]{2, 0, 0, 0}
        })[0];

        return new Felt(unsplitBigInteger(result));
    }

    public static Felt poseidonHash(List<Felt> values) {
        List<Felt> inputValues = new ArrayList<>(values);
        inputValues.add(Felt.ONE);
        if (inputValues.size() % r == 1) {
            inputValues.add(Felt.ZERO);
        }

        long[][] state = new long[m][4];

        for (int iter = 0; iter < inputValues.size(); iter += 2) {
            state = hades(new long[][]{
                    splitBigInteger(unsplitBigInteger(state[0]).add(new BigInteger(inputValues.get(iter).decString()))),
                    splitBigInteger(unsplitBigInteger(state[1]).add(new BigInteger(inputValues.get(iter + 1).decString()))),
                    state[2]
            });
        }

        return new Felt(unsplitBigInteger(state[0]));
    }

    public static Felt poseidonHash(Felt... values) {
        return poseidonHash(Arrays.asList(values));
    }
}
