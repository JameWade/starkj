package com.starknet.stark.data.types.transactions;

import com.starknet.stark.data.types.Felt;
import com.starknet.stark.data.types.StarknetChainId;
import com.starknet.stark.data.types.contract.Cairo0ContractDefinition;
import com.starknet.stark.data.types.contract.Cairo1ContractDefinition;
import com.starknet.stark.data.types.contract.CasmContractDefinition;
import com.starknet.stark.data.types.transactions.calculate.Cairo1ClassHashCalculatorUtil;
import com.starknet.stark.data.types.transactions.calculate.TransactionHashCalculatorUtil;

import java.util.List;

public class TransactionFactory {
    public static InvokeTransactionV1 makeInvokeTransaction(
            Felt senderAddress,
            List<Felt> calldata,
            StarknetChainId chainId,
            Felt nonce,
            Felt maxFee,
            List<Felt> signature,
            Felt version
    ) {
        Felt hash = TransactionHashCalculatorUtil.calculateInvokeTxHash(senderAddress, calldata, chainId, version, nonce, maxFee);
        return new InvokeTransactionV1(calldata, senderAddress, hash, maxFee, version, signature, nonce);
    }

    public static DeployAccountTransaction makeDeployAccountTransaction(
            Felt classHash,
            Felt contractAddress,
            Felt salt,
            List<Felt> calldata,
            StarknetChainId chainId,
            Felt version,
            Felt maxFee,
            List<Felt> signature,
            Felt nonce
    ) {
        Felt hash = TransactionHashCalculatorUtil.calculateDeployAccountTxHash(classHash, calldata, salt, chainId, version, maxFee, nonce);
        return new DeployAccountTransaction(classHash, contractAddress, salt, calldata, hash, maxFee, version, signature, nonce);
    }

    public static DeclareTransactionV1 makeDeclareV1Transaction(
            Felt classHash,
            Felt senderAddress,
            Cairo0ContractDefinition contractDefinition,
            StarknetChainId chainId,
            Felt maxFee,
            Felt version,
            Felt nonce,
            List<Felt> signature
    ) {
        Felt hash = TransactionHashCalculatorUtil.calculateDeclareV1TxHash(classHash, chainId, senderAddress, maxFee, version, nonce);
        return new DeclareTransactionV1(classHash, senderAddress, hash, maxFee, version, signature, nonce, contractDefinition);
    }

    public static DeclareTransactionV2 makeDeclareV2Transaction(
            Felt senderAddress,
            Cairo1ContractDefinition contractDefinition,
            StarknetChainId chainId,
            Felt maxFee,
            Felt version,
            Felt nonce,
            CasmContractDefinition casmContractDefinition,
            List<Felt> signature
    ) {
        Felt classHash = Cairo1ClassHashCalculatorUtil.computeSierraClassHash(contractDefinition);
        Felt compiledClassHash = Cairo1ClassHashCalculatorUtil.computeCasmClassHash(casmContractDefinition);
        Felt hash = TransactionHashCalculatorUtil.calculateDeclareV2TxHash(classHash, chainId, senderAddress, maxFee, version, nonce, compiledClassHash);
        return new DeclareTransactionV2(classHash, senderAddress, hash, maxFee, version, signature, nonce, compiledClassHash, contractDefinition);
    }
}

