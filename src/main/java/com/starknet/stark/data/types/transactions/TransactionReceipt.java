package com.starknet.stark.data.types.transactions;

import com.starknet.stark.data.types.Felt;

public abstract class TransactionReceipt {
    public abstract Felt getHash();
    public abstract Felt getActualFee();
    public abstract TransactionReceiptType getType();
    public abstract TransactionStatus getStatus();

    public boolean isAccepted() {
        return (getStatus() == TransactionStatus.ACCEPTED_ON_L1) || (getStatus() == TransactionStatus.ACCEPTED_ON_L2);
    }
}