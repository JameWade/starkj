package com.starknet.stark.data.types.response;

import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;

public class InvokeFunctionResponse {
    @SerializedName("transaction_hash")
    private Felt transactionHash;
}
