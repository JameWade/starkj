package com.starknet.stark.data.types.response;

import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;

public class DeclareResponse {
    @SerializedName("transaction_hash")
    Felt transactionHash;

    @SerializedName("class_hash")
    Felt classHash;

    public Felt getClassHash() {
        return classHash;
    }

    public Felt getTransactionHash() {
        return transactionHash;
    }
}
