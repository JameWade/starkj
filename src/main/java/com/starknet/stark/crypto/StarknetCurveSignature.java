package com.starknet.stark.crypto;

import com.starknet.stark.data.types.Felt;

import java.util.Arrays;
import java.util.List;

public class StarknetCurveSignature {
    private Felt r;
    private Felt s;

    public StarknetCurveSignature(Felt felt, Felt felt1) {
    }

    public List<Felt> toList(){
        return Arrays.asList(r, s);
    }  
}
