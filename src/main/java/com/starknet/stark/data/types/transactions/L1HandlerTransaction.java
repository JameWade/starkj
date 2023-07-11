package com.starknet.stark.data.types.transactions;

import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;

import java.util.List;

public  class L1HandlerTransaction extends Transaction {
        @SerializedName("contract_address")
        private final Felt contractAddress;

        @SerializedName("calldata")
        private final List<Felt> calldata;

        @SerializedName("entry_point_selector")
        private final Felt entryPointSelector;

        @SerializedName("transaction_hash")
        private final Felt hash;

        @SerializedName("max_fee")
        private final Felt maxFee;

        @SerializedName("version")
        private final Felt version;

        @SerializedName("signature")
        private final List<Felt>  signature;

        @SerializedName("nonce")
        private final Felt nonce;

        @SerializedName("type")
        private final TransactionType type;

        public L1HandlerTransaction(
                Felt contractAddress,
                List<Felt>  calldata,
                Felt entryPointSelector,
                Felt hash,
                Felt maxFee,
                Felt version,
                List<Felt>  signature,
                Felt nonce
        ) {
            this.contractAddress = contractAddress;
            this.calldata = calldata;
            this.entryPointSelector = entryPointSelector;
            this.hash = hash;
            this.maxFee = maxFee;
            this.version = version;
            this.signature = signature;
            this.nonce = nonce;
            this.type = TransactionType.L1_HANDLER;
        }

        // Getters for the fields

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
        public List<Felt>  getSignature() {
            return signature;
        }

        @Override
        public Felt getNonce() {
            return nonce;
        }

        @Override
        public TransactionType getType() {
            return type;
        }
    }