package com.starknet.stark.data.types.response;

import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;

public class GetBlockHashAndNumberResponse {
    @SerializedName("block_hash")
    Felt blockHash;

    @SerializedName("block_number")
    Integer blockNumber;
}
