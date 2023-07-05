package com.starknet.stark.data.types;

import com.starknet.stark.util.ByteUtils;
import com.sun.istack.internal.NotNull;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

public class Felt {
    private BigInteger PRIME = new BigInteger("800000000000011000000000000000000000000000000000000000000000001", 16);
    public static Felt ZERO = new Felt(BigInteger.ZERO);
    private static Felt ONE = new Felt(BigInteger.ONE);
    private BigInteger value;

    public BigInteger getValue() {
        return value;
    }

    public Felt(BigInteger value) {
        if (BigInteger.ZERO.compareTo(value) >0) {
            throw new IllegalArgumentException("Default Felt constructor does not accept negative numbers, [$value] given.");
        }
        if (value.compareTo(PRIME) >0 ||value.equals(PRIME) ) {
            throw new IllegalArgumentException("Default Felt constructor accepts values smaller than Felt.PRIME, [$value] given.");
        }
        this.value = value;
    }
    public int  compareTo(Felt other){
        return value.compareTo(other.value);
    }
    public List<Felt> toCalldata(){
        return Collections.singletonList(this);
    }

    @Override
    public String toString() {
        return "Felt{" +
                "value=" + value +
                '}';
    }

    public String hexString(){
        return "0x"+value.toString(16);
    }
    public String decString(){
        return value.toString(10);
    }
    public String toShortString(){
        String hexString = value.toString(16);
        return new String(ByteUtils.hexToByte(hexString));

    }

    //ASCII 2 HEX
    public Felt fromShortString(String value){
        if (value.length()>31){
            throw new IllegalArgumentException("Short string cannot be longer than 31 characters.");
        }
        if (!isAscii(value)) {
            throw new IllegalArgumentException("String to be encoded must be an ascii string.");
        }
        return fromHex(ByteUtils.byte2Hex(value.getBytes()));
    }
    @NotNull
    public  Felt fromHex(@NotNull String value) {
        return new Felt(parseHex(value));
    }


    public BigInteger parseHex(String value) {
        if (!value.startsWith("0x")) {
            throw new IllegalArgumentException("Hex must start with 0x");
        }

        return new BigInteger(value.substring("0x".length()), 16);
    }
    private  Boolean isAscii(String str)  {
        for (char ch: str.toCharArray()) {
            if (ch > 127) {
                return false;
            }
        }

        return true;
    }

    public static Felt fromHex2(@NotNull String value) {

        if (!value.startsWith("0x")) {
            throw new IllegalArgumentException("Hex must start with 0x");
        }

        return new Felt(new BigInteger(value.substring("0x".length()), 16));
    }

}
