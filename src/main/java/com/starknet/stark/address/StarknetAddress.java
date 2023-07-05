package com.starknet.stark.address;

import com.starknet.stark.data.types.Felt;
import com.starknet.stark.util.ByteUtils;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

public class StarknetAddress extends EthAddress{
    private  BigInteger value;
    @Override
    public byte[] getAsBytes() {
        return ByteUtils.positiveNumberToBytesLeftPadded(getValue(), 32);
    }

    @Override
    public String getHexValue() {
        return Numeric.toHexStringWithPrefixZeroPadded(getValue(), 64);
    }

    @Override
    public BigInteger getValue() {
        return this.value;
    }
    public StarknetAddress(Felt felt) {
        this.value = felt.getValue();
    }
    public StarknetAddress(BigInteger value){
        this.value = value;
    }
    public final StarknetAddress copy(BigInteger value) {
        return new StarknetAddress(value);
    }
}
