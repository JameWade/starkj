package com.starknet.stark.data.types.transactions;

import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;
import com.starknet.stark.data.types.contract.Cairo1ContractDefinition;

import java.util.List;

public  class DeclareTransactionV2 extends DeclareTransaction {
        @SerializedName("class_hash")
        private final Felt classHash;

        @SerializedName("sender_address")
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

        @SerializedName("compiled_class_hash")
        private final Felt compiledClassHash;

        @SerializedName("type")
        private final TransactionType type;

        private final Cairo1ContractDefinition contractDefinition;

        public DeclareTransactionV2(
                Felt classHash,
                Felt senderAddress,
                Felt hash,
                Felt maxFee,
                Felt version,
                List<Felt> signature,
                Felt nonce,
                Felt compiledClassHash,
                Cairo1ContractDefinition contractDefinition
        ) {
            this.classHash = classHash;
            this.senderAddress = senderAddress;
            this.hash = hash;
            this.maxFee = maxFee;
            this.version = version;
            this.signature = signature;
            this.nonce = nonce;
            this.compiledClassHash = compiledClassHash;
            this.type = TransactionType.DECLARE;
            this.contractDefinition = contractDefinition;
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

        public DeclareTransactionV2Payload toPayload() {
            if (contractDefinition == null) {
                throw new ConvertingToPayloadFailedException();
            }
            return new DeclareTransactionV2Payload(compiledClassHash, maxFee, nonce,signature,senderAddress, version, type);
        }

        public static class ConvertingToPayloadFailedException extends RuntimeException {
        }
    }