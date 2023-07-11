package com.starknet.stark.data.types.transactions;

import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;

import java.util.List;

public  class InvokeTransactionV1 extends InvokeTransaction {
        @SerializedName("calldata")
        private final List<Felt> calldata;

        @SerializedName("contract_address")
        private final Felt senderAddress;

        @SerializedName("transaction_hash")
        private final Felt hash;

        @SerializedName("max_fee")
        private final Felt maxFee;

        @SerializedName("version")
        private final Felt version;

        @SerializedName("signature")
        private final List<Felt> signature;

        @SerializedName("nonce")
        private final Felt nonce;

        public InvokeTransactionV1(
                List<Felt> calldata,
                Felt senderAddress,
                Felt hash,
                Felt maxFee,
                Felt version,
                List<Felt> signature,
                Felt nonce
        ) {
            this.calldata = calldata;
            this.senderAddress = senderAddress;
            this.hash = hash;
            this.maxFee = maxFee;
            this.version = version;
            this.signature = signature;
            this.nonce = nonce;
        }

        // Getters for the fields

        @Override
        public List<Felt> getCalldata() {
            return calldata;
        }

        @Override
        public Felt getHash() {
            return hash;
        }

        @Override
        public Felt getMaxFee() {
            return maxFee;
        }

        @Override
        public Felt getVersion() {
            return version;
        }

        @Override
        public List<Felt> getSignature() {
            return signature;
        }

        @Override
        public Felt getNonce() {
            return nonce;
        }

        @Override
        public TransactionType getType() {
            return TransactionType.INVOKE;
        }

        public InvokeTransactionPayload toPayload() {
            return new InvokeTransactionPayload(senderAddress,calldata, signature, maxFee, nonce, version);
        }

        public static InvokeTransaction fromPayload(InvokeTransactionPayload payload) {
            return new InvokeTransactionV1(
                    payload.getCalldata(),
                    payload.getSenderAddress(),
                    Felt.ZERO,
                    payload.getMaxFee(),
                    payload.getVersion(),
                    payload.getSignature(),
                    payload.getNonce()
            );
        }
    }