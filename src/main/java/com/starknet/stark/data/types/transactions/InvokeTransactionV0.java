package com.starknet.stark.data.types.transactions;

import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;

import java.util.List;

public  class InvokeTransactionV0 extends InvokeTransaction {
        @SerializedName("calldata")
        private final List<Felt> calldata;

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

        @SerializedName("contract_address")
        private final Felt contractAddress;

        @SerializedName("entry_point_selector")
        private final Felt entryPointSelector;

        public InvokeTransactionV0(
                List<Felt> calldata,
                Felt hash,
                Felt maxFee,
                Felt version,
                List<Felt> signature,
                Felt nonce,
                Felt contractAddress,
                Felt entryPointSelector
        ) {
            this.calldata = calldata;
            this.hash = hash;
            this.maxFee = maxFee;
            this.version = version;
            this.signature = signature;
            this.nonce = nonce;
            this.contractAddress = contractAddress;
            this.entryPointSelector = entryPointSelector;
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
    }