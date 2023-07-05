package com.starknet.stark.address;

import org.web3j.abi.datatypes.Address;

import java.math.BigInteger;

public abstract class EthAddress {

    public abstract byte[] getAsBytes();

    public abstract String getHexValue();

    public abstract BigInteger getValue();
    public final Address getWeb3jAddress() {
        return new Address(getValue());
    }
    public final boolean isZeroAddress() {
        return getValue().equals(BigInteger.ZERO) ;
    }
    public final String toString() {
        return getHexValue();
    }

//    public final EthAddress from(BigInteger value, Chain chain) {
//        return new StarknetAddress(value);
//    }
}
