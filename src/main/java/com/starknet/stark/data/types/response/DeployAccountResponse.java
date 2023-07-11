package com.starknet.stark.data.types.response;

import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;

public class DeployAccountResponse {
    @SerializedName("transaction_hash")
    Felt transactionHash;

    @SerializedName(value = "address", alternate = "contract_address")
    Felt address;
}
