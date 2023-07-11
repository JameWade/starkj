package com.starknet.stark.signer;

import com.starknet.stark.data.types.Felt;
import com.starknet.stark.data.types.TypedData;
import com.starknet.stark.data.types.transactions.Transaction;

import java.util.List;

public interface Signer {
    public List<Felt> signTransaction(Transaction transaction);
    public List<Felt> signTypedData(TypedData typedData,Felt accountAddress);
}
