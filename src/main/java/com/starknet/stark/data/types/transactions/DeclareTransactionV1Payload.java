package com.starknet.stark.data.types.transactions;


import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.math.BigInteger;
import java.util.List;

public class DeclareTransactionV1Payload {
    @SerializedName("contract_class")
    private Cairo0ContractDefinition contractDefinition;


    @SerializedName("max_fee")
    private Felt maxFee;

    @SerializedName("nonce")
    private Felt nonce;

    @SerializedName("signature")
    private List<Felt> signature;
    @SerializedName("sender_address")
    private Felt senderAddress ;
    @SerializedName("version")
    private Felt version = new Felt( BigInteger.ONE);
    @SerializedName("type")
    private TransactionType type;

    public DeclareTransactionV1Payload(Cairo0ContractDefinition contractDefinition, Felt maxFee, Felt nonce, List<Felt> signature, Felt senderAddress, Felt version, TransactionType type) {
        this.contractDefinition = contractDefinition;
        this.maxFee = maxFee;
        this.nonce = nonce;
        this.signature = signature;
        this.senderAddress = senderAddress;
        this.version = version;
        this.type = type;
    }
}
