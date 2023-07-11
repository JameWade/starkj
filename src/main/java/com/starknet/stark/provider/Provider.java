package com.starknet.stark.provider;

import com.starknet.stark.data.types.Call;
import com.starknet.stark.data.types.Felt;
import com.starknet.stark.data.types.StarknetChainId;
import com.starknet.stark.data.types.block.BlockTag;
import com.starknet.stark.data.types.response.*;
import com.starknet.stark.data.types.transactions.*;

import java.util.List;

public interface Provider {
     public StarknetChainId chainId = null;
    //Call 合约
    Request<List<Felt>> callContract(Call call, BlockTag blockTag);
    Request<List<Felt>> callContract(Call call,Felt blockHash);
    Request<List<Felt>> callContract(Call call, Integer blockNumber);
    default Request<List<Felt>> callContract(Call call){
        return callContract(call ,BlockTag.LATEST);
    }

    //获取存储变量
    Request<Felt> getStorageAt(Felt contractAddress,Felt key,BlockTag blockTag);
    Request<Felt> getStorageAt(Felt contractAddress,Felt key,Felt blockHash);
    Request<Felt> getStorageAt(Felt contractAddress,Felt key,Integer blockNumber);
    default Request<Felt> getStorageAt(Felt contractAddress,Felt key){
        return getStorageAt(contractAddress,key,BlockTag.LATEST);
    }

    Request<Transaction> getTransaction(Felt transactionHash);
    Request<? extends TransactionReceipt> getTransactionReceipt(Felt transactionHash);

    Request<InvokeFunctionResponse> invokeFunction(InvokeTransactionPayload payload);

    //获取合约class定义
    Request<ContractClassBase>  getClass(Felt classHash);
    //获取合约class hash
    Request<Felt> getClassHashAt(Felt contractAddress, Felt blockHash);
    Request<Felt> getClassHashAt(Felt contractAddress,Integer  blockNumber);
    Request<Felt> getClassHashAt(Felt contractAddress,BlockTag blockTag);
    default Request<Felt> getClassHashAt(Felt contractAddress){
        return getClassHashAt(contractAddress,BlockTag.LATEST);
    }

    //声明合约
    Request<DeclareResponse> declareContract(DeclareTransactionV1Payload payload);
    Request<DeclareResponse> declareContract(DeclareTransactionV2Payload payload );

    //估算交易费
    Request<List<EstimateFeeResponse>> getEstimateFee( List<TransactionPayload> payload, Felt blockHash);
    Request<List<EstimateFeeResponse>> getEstimateFee(List<TransactionPayload> payload, BlockTag blockTag );
    default Request<List<EstimateFeeResponse>> getEstimateFee(List<TransactionPayload> payload)  {
        return getEstimateFee(payload, BlockTag.LATEST);
    }
    //获取nonce
    Request<Felt> getNonce(Felt contractAddress, BlockTag blockTag);
    default Request<Felt> getNonce(Felt contractAddress){
        return getNonce(contractAddress,  BlockTag.PENDING);
    }

    //获取区块号
    Request<Integer> getBlockNumber();
    //获取区块号和区块哈希
    Request<GetBlockHashAndNumberResponse> getBlockHashAndNumber();
    //获取区块中的交易数
    Request<Integer> getBlockTransactionCount(BlockTag blockTag);
    Request<Integer> getBlockTransactionCount(Felt blockHash);
    Request<Integer> getBlockTransactionCount(Integer blockNumber);
    //部署账户合约
    Request<DeployAccountResponse> deployAccount(DeployAccountTransactionPayload payload);
}
