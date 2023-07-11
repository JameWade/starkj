package com.starknet.stark.data.types.transactions;

import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.Felt;
import com.starknet.stark.data.types.contract.Cairo0ContractDefinition;

import java.util.List;

public  class DeclareTransactionV1 extends DeclareTransaction {
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

        @SerializedName("type")
        private final TransactionType type;

        private final Cairo0ContractDefinition contractDefinition;

        public DeclareTransactionV1(
                Felt classHash,
                Felt senderAddress,
                Felt hash,
                Felt maxFee,
                Felt version,
                List<Felt> signature,
                Felt nonce,
                Cairo0ContractDefinition contractDefinition
        ) {
            this.classHash = classHash;
            this.senderAddress = senderAddress;
            this.hash = hash;
            this.maxFee = maxFee;
            this.version = version;
            this.signature = signature;
            this.nonce = nonce;
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

        public DeclareTransactionV1Payload toPayload() {
            if (contractDefinition == null) {
                throw new ConvertingToPayloadFailedException();
            }
            return new DeclareTransactionV1Payload(contractDefinition, maxFee, nonce, signature,senderAddress, version,TransactionType.DECLARE);
        }

        public static class ConvertingToPayloadFailedException extends RuntimeException {
        }
    }
