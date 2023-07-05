package com.starknet.stark.data.types.transactions;

import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;

public enum TransactionType {
    @SerializedName("DECLARE")
    DECLARE(Felt.fromHex2("0x6465636c617265")), // encodeShortString('declare'),

    @SerializedName("DEPLOY")
    DEPLOY(Felt.fromHex2("0x6465706c6f79")), // encodeShortString('deploy'),

    @SerializedName("DEPLOY_ACCOUNT")
    DEPLOY_ACCOUNT(Felt.fromHex2("0x6465706c6f795f6163636f756e74")), // encodeShortString('deploy_account'),

    @SerializedName("INVOKE")
    INVOKE(Felt.fromHex2("0x696e766f6b65")), // encodeShortString('invoke'),

    @SerializedName("L1_HANDLER")
    L1_HANDLER(Felt.fromHex2("0x6c315f68616e646c6572")); // encodeShortString('l1_handler')

    TransactionType(Felt hex) {

    }
}
