package com.starknet.stark.data.types.transactions;


import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;

import java.util.List;

public class InvokeTransactionPayload {
    public Felt getSenderAddress() {
        return senderAddress;
    }

    public List<Felt> getCalldata() {
        return calldata;
    }

    public List<Felt> getSignature() {
        return signature;
    }

    public Felt getMaxFee() {
        return maxFee;
    }

    public Felt getVersion() {
        return version;
    }

    public Felt getNonce() {
        return nonce;
    }

    public TransactionType getType() {
        return type;
    }

    @SerializedName("sender_address")
    private Felt senderAddress;

    @SerializedName("calldata")
    private List<Felt> calldata;

    @SerializedName("signature")
    private List<Felt> signature;

    @SerializedName("max_fee")
    private Felt maxFee;

    @SerializedName("version")
    private Felt version;

    @SerializedName("nonce")
    private Felt nonce;
    @SerializedName("type")
    private TransactionType type ;

    public InvokeTransactionPayload(Felt senderAddress, List<Felt> calldata, List<Felt> signature, Felt maxFee, Felt version, Felt nonce) {

        this.type = TransactionType.INVOKE;
    this.senderAddress = senderAddress;
        this.calldata = calldata;
        this.signature = signature;
        this.maxFee = maxFee;
        this.version = version;
        this.nonce = nonce;
    }

}
