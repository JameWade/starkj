package com.starknet.stark.data.types;

import com.starknet.stark.data.types.conversions.ConvertibleToCalldata;

import java.util.ArrayList;
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
    public Call(Felt contractAddress, Felt entrypoint){
        this.contractAddress = contractAddress;
        this.entrypoint = entrypoint;
        this.calldata = Collections.emptyList();
    }
    public Call fromCallArguments(Felt contractAddress,  Felt entrypoint, List<ConvertibleToCalldata> arguments ) {
        List<Felt> calldata = new ArrayList<>();
        for (ConvertibleToCalldata argument : arguments) {
            calldata.addAll(argument.toCalldata());
        }
        return new Call(contractAddress, entrypoint, calldata);
    }

    public static List<Felt> callsToExecuteCalldata(List<Call> calls) {
        List<Felt> wholeCalldata = new ArrayList<>();
        List<Felt> callArray = new ArrayList<>();
        for (Call call : calls) {
            callArray.add(call.contractAddress); // to
            callArray.add(call.entrypoint); // selector
            callArray.add(new Felt(wholeCalldata.size())); // offset
            callArray.add(new Felt(call.calldata.size())); // len

            wholeCalldata.addAll(call.calldata);
        }

        List<Felt> result = new ArrayList<>();
        result.add(new Felt(calls.size()));
        result.addAll(callArray);
        result.add(new Felt(wholeCalldata.size()));
        result.addAll(wholeCalldata);

        return result;
    }
}
