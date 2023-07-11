package com.starknet.stark.data.types.transactions;

import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;

import java.util.List;

public abstract class Transaction {
//    private Felt hash;
//    private Felt maxFee;
//    private Felt version;
//    private List<Felt> signature;
//    private Felt nonce;
//    private TransactionType type;
    public abstract Felt getHash();
    public abstract Felt getMaxFee();
    public abstract Felt getVersion();
    public abstract List<Felt> getSignature();
    public abstract Felt getNonce();
    public abstract TransactionType getType();
}
