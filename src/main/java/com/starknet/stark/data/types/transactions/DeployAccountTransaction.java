package com.starknet.stark.data.types.transactions;

import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;

import java.util.List;

public  class DeployAccountTransaction extends Transaction {
        @SerializedName("class_hash")

        private final Felt classHash;

        @SerializedName("contract_address")
        private final Felt contractAddress;

        @SerializedName("contract_address_salt")
        private final Felt contractAddressSalt;

        @SerializedName("constructor_calldata")

        private final List<Felt> constructorCalldata;

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

        @SerializedName("type")
        private final TransactionType type;

        public DeployAccountTransaction(
                Felt classHash,
                Felt contractAddress,
                Felt contractAddressSalt,
                List<Felt> constructorCalldata,
                Felt hash,
                Felt maxFee,
                Felt version,
                List<Felt> signature,
                Felt nonce
        ) {
            this.classHash = classHash;
            this.contractAddress = contractAddress;
            this.contractAddressSalt = contractAddressSalt;
            this.constructorCalldata = constructorCalldata;
            this.hash = hash;
            this.maxFee = maxFee;
            this.version = version;
            this.signature = signature;
            this.nonce = nonce;
            this.type = TransactionType.DEPLOY_ACCOUNT;
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
        public List<Felt> getSignature() {
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

        public DeployAccountTransactionPayload toPayload() {
            return new DeployAccountTransactionPayload(classHash, contractAddressSalt, constructorCalldata, version, nonce, maxFee, signature,type);
        }
    }