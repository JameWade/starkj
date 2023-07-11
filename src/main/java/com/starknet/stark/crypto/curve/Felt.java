package com.starknet.stark.crypto.curve;

import java.math.BigInteger;

public class Felt {
    private BigInteger Gp = new BigInteger("3618502788666131213697322783095070105623107215331596699973092056135872020481", 16);
    private BigInteger value;
    public BigInteger getValue() {
        return value;
    }
    public Felt(BigInteger value){
        this.value = value;
    }
    public Felt add(Felt rhs) {
        return new Felt(this.value.add(rhs.getValue()).mod(Gp));
    }
    public Felt sub(Felt rhs) {
        return new Felt(this.value.add(Gp).subtract(rhs.getValue()).mod(Gp));
    }
    public Felt mul(Felt rhs) {
        return new Felt(this.value.multiply(rhs.getValue()).mod(Gp));
    }
    public Felt divide(Felt rhs) {
        return new Felt(this.value.divide(rhs.value).mod(Gp));
    }
}
