package com.starknet.stark.data.types;

import com.sun.istack.internal.NotNull;

public  class ExecutionParams {
    @NotNull
    private  Felt nonce;
    @NotNull
    private  Felt maxFee;

    @NotNull
    public  Felt getNonce() {
        return this.nonce;
    }

    @NotNull
    public final Felt getMaxFee() {
        return this.maxFee;
    }

    public ExecutionParams(@NotNull Felt nonce, @NotNull Felt maxFee) {
        super();
        this.nonce = nonce;
        this.maxFee = maxFee;
    }


}
