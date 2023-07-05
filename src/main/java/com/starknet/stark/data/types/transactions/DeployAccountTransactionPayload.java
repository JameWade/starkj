package com.starknet.stark.data.types.transactions;


import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;

import java.util.List;

public class DeployAccountTransactionPayload {
    @SerializedName("class_hash")
    private Felt classHash;

    @SerializedName("contract_address_salt")
    private Felt salt;

    @SerializedName("constructor_calldata")
    private List<Felt> constructorCalldata;

    @SerializedName("version")
    private Felt version;

    @SerializedName("nonce")
    private Felt nonce;

    @SerializedName("max_fee")
    private Felt maxFee;
    @SerializedName("signature")
    private List<Felt> signature;
    @SerializedName("type")
    private TransactionType type;

    public DeployAccountTransactionPayload(Felt classHash, Felt salt, List<Felt> constructorCalldata, Felt version, Felt nonce, Felt maxFee, List<Felt> signature, TransactionType type) {
        this.classHash = classHash;
        this.salt = salt;
        this.constructorCalldata = constructorCalldata;
        this.version = version;
        this.nonce = nonce;
        this.maxFee = maxFee;
        this.signature = signature;
        this.type = type;
    }
}
