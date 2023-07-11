package com.starknet.stark.data.types.contract;

import com.starknet.stark.data.types.Felt;

public class DeprecatedCairoEntryPoint {
    private String offset;
    private Felt selector;

    public String getOffset() {
        return offset;
    }

    public Felt getSelector() {
        return selector;
    }
}
