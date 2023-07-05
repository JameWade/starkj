package com.starknet.stark.account;

import com.starknet.stark.data.types.Call;
import com.starknet.stark.data.types.ExecutionParams;
import com.starknet.stark.data.types.Felt;
import com.starknet.stark.data.types.TypedData;
import com.starknet.stark.data.types.contract.Cairo0ContractDefinition;
import com.starknet.stark.data.types.contract.Cairo1ContractDefinition;
import com.starknet.stark.data.types.contract.CasmContractDefinition;
import com.starknet.stark.data.types.response.EstimateFeeResponse;
import com.starknet.stark.data.types.response.InvokeFunctionResponse;
import com.starknet.stark.data.types.transactions.DeclareTransactionV1Payload;
import com.starknet.stark.data.types.transactions.DeclareTransactionV2Payload;
import com.starknet.stark.data.types.transactions.DeployAccountTransactionPayload;
import com.starknet.stark.data.types.transactions.InvokeTransactionPayload;
import com.starknet.stark.provider.Request;

import java.security.Signature;
import java.util.List;

public class StandardAccount extends Account{


    @Override
    public InvokeTransactionPayload sign(List<Call> calls, ExecutionParams params, Boolean forFeeEstimate) {
        return null;
    }

    @Override
    public DeployAccountTransactionPayload signDeployAccount(Felt classHash, List<Felt> calldata, Felt salt, Felt maxFee, Felt zero, boolean b) {
        return null;
    }

    @Override
    public DeclareTransactionV1Payload SignDeclare(Cairo0ContractDefinition contractDefinition, Felt classHash, ExecutionParams params, Boolean forFeeEstimate) {
        return null;
    }

    @Override
    public DeclareTransactionV2Payload SignDeclare(Cairo1ContractDefinition contractDefinition, CasmContractDefinition casmContractDefinition, ExecutionParams params, Boolean forFeeEstimate) {
        return null;
    }

    @Override
    public Signature signTypedData(TypedData typedData) {
        return null;
    }

    @Override
    public Request<Boolean> verifyTypedDataSignature(TypedData typedData, Signature signature) {
        return null;
    }

    @Override
    public Request<InvokeFunctionResponse> execute(List<Call> calls, Felt maxFee) {
        return null;
    }

    @Override
    public Request<InvokeFunctionResponse> execute(List<Call> calls) {
        return null;
    }

    @Override
    public Request<List<EstimateFeeResponse>> estimateFee(List<Call> calls) {
        return null;
    }

    @Override
    public Request<Felt> getNonce() {
        return null;
    }
}
