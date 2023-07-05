package com.starknet.stark.data.types;

import java.util.Collections;
import java.util.List;

//typealias Calldata = List<Felt>
//typealias Signature = List<Felt>
//typealias CallArguments = List<ConvertibleToCalldata>
public class Call {
    private Felt contractAddress;
    private Felt entrypoint;
    private List<Felt> calldata;
    public Call(Felt contractAddress, Felt entrypoint, List<Felt> calldata) {
        this.contractAddress = contractAddress;
        this.entrypoint = entrypoint;
        this.calldata = calldata;
    }

}
