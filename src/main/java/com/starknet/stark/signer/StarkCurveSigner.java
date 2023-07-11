package com.starknet.stark.signer;

import com.starknet.stark.crypto.StarknetCurve;
import com.starknet.stark.data.types.Felt;
import com.starknet.stark.data.types.TypedData;
import com.starknet.stark.data.types.transactions.Transaction;

import java.util.List;

public class StarkCurveSigner implements Signer {
    private Felt privateKey;
    public StarkCurveSigner(Felt privateKey){
        this.privateKey = privateKey;
    }
    @Override
    public List<Felt> signTransaction(Transaction transaction) {
        return StarknetCurve.sign(privateKey, transaction.getHash()).toList();
    }

    @Override
    public List<Felt> signTypedData(TypedData typedData, Felt accountAddress) {
        return null;
    }
}
