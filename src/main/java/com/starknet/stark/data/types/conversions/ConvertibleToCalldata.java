package com.starknet.stark.data.types.conversions;

import com.starknet.stark.data.types.Felt;

import java.util.List;

public interface ConvertibleToCalldata {
    public List<Felt> toCalldata();
}
