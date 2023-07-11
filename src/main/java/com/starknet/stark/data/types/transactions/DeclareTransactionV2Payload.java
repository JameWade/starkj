package com.starknet.stark.data.types.transactions;


import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;
import com.starknet.stark.data.types.contract.Cairo1ContractDefinition;

import java.math.BigInteger;
import java.util.List;

public class DeclareTransactionV2Payload {
    @SerializedName("contract_class")
    private Cairo1ContractDefinition contractDefinition;


    @SerializedName("max_fee")
    private Felt maxFee;

    @SerializedName("nonce")
    private Felt nonce;

    @SerializedName("signature")
    private List<Felt> signature;
    @SerializedName("sender_address")
    private Felt senderAddress ;
    @SerializedName("compiled_class_hash")
    private Felt compiledClassHash;
    @SerializedName("version")
    private Felt version = new Felt( BigInteger.ONE);
    @SerializedName("type")
    private TransactionType type;

    public DeclareTransactionV2Payload(Felt compiledClassHash, Felt maxFee, Felt nonce, List<Felt> signature, Felt senderAddress, Felt version, TransactionType type) {
        this.compiledClassHash = compiledClassHash;
        this.maxFee = maxFee;
        this.nonce = nonce;
        this.signature = signature;
        this.senderAddress = senderAddress;
        this.version = version;
        this.type = type;
    }
}
