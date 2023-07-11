package com.starknet.stark.data.types.transactions;

import com.starknet.stark.data.types.Felt;

import java.util.List;

public abstract class InvokeTransaction extends Transaction {
    public abstract List<Felt> getCalldata();
}
