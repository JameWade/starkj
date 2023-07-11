package com.starknet.stark.data.types.transactions.calculate;

import com.starknet.stark.crypto.StarknetCurve;
import com.starknet.stark.data.types.Felt;
import com.starknet.stark.data.types.StarknetChainId;
import com.starknet.stark.data.types.transactions.TransactionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class TransactionHashCalculatorUtil {
    public static Felt calculateInvokeTxHash(
            Felt contractAddress,
            List<Felt> calldata,
            StarknetChainId chainId,
            Felt version,
            Felt nonce,
            Felt maxFee
    ) {
        return transactionHashCommon(
                TransactionType.INVOKE,
                version,
                contractAddress,
                Felt.ZERO,
                calldata,
                maxFee,
                chainId,
                nonce
        );
    }

    private static Felt transactionHashCommon(
            TransactionType txType,
            Felt version,
            Felt contractAddress,
            Felt entryPointSelector,
            List<Felt> calldata,
            Felt maxFee,
            StarknetChainId chainId,
            Felt nonce
    ) {
        return StarknetCurve.pedersenOnElements(
                txType.getTxPrefix(),
                version,
                contractAddress,
                entryPointSelector,
                StarknetCurve.pedersenOnElements(calldata),
                maxFee,
                chainId.getValue(),
                nonce
        );
    }

    public static Felt calculateDeployAccountTxHash(
            Felt classHash,
            List<Felt> calldata,
            Felt salt,
            StarknetChainId chainId,
            Felt version,
            Felt maxFee,
            Felt nonce
    ) {
        Felt contractAddress = ContractAddressCalculatorUtil.calculateAddressFromHash(classHash, calldata, salt);
        List<Felt> calldataList = new ArrayList<>();
        calldataList.add(classHash);
        calldataList.add(salt);
        calldataList.addAll(calldata);
        return transactionHashCommon(
                TransactionType.DEPLOY_ACCOUNT,
                version,
                contractAddress,
                Felt.ZERO,
                calldataList,
                maxFee,
                chainId,
                nonce
        );
    }

    public static Felt calculateDeclareV1TxHash(
            Felt classHash,
            StarknetChainId chainId,
            Felt senderAddress,
            Felt maxFee,
            Felt version,
            Felt nonce
            ) {
        Felt hash = StarknetCurve.pedersenOnElements(Arrays.asList(classHash));
        return StarknetCurve.pedersenOnElements(
                TransactionType.DECLARE.getTxPrefix(),
                version,
                senderAddress,
                Felt.ZERO,
                hash,
                maxFee,
                chainId.getValue(),
                nonce
                );
    }

    public static Felt calculateDeclareV2TxHash(
            Felt classHash,
            StarknetChainId chainId,
            Felt senderAddress,
            Felt maxFee,
            Felt version,
            Felt nonce,
            Felt compiledClassHash
    ) {
        Felt calldataHash = StarknetCurve.pedersenOnElements(Collections.singletonList(classHash));
        return StarknetCurve.pedersenOnElements(
                TransactionType.DECLARE.getTxPrefix(),
                version,
                senderAddress,
                Felt.ZERO,
                calldataHash,
                maxFee,
                chainId.getValue(),
                nonce,
                compiledClassHash
        );
    }
}
