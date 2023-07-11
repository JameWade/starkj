package com.starknet.stark.data.types.transactions;

import com.google.gson.annotations.SerializedName;

public enum TransactionStatus {
    @SerializedName("PENDING")
    PENDING,
    @SerializedName("ACCEPTED_ON_L1")
    ACCEPTED_ON_L1,

    @SerializedName("ACCEPTED_ON_L2")
    ACCEPTED_ON_L2,

    @SerializedName("REJECTED")
    REJECTED,

    @SerializedName("UNKNOWN")
    UNKNOWN,
}
