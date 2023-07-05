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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Account {
    public Felt address;
    public InvokeTransactionPayload sign(Call call, ExecutionParams params, Boolean forFeeEstimate) {
        return sign(Collections.singletonList(call), params, forFeeEstimate);
    }

    public abstract InvokeTransactionPayload sign(List<Call> calls, ExecutionParams params, Boolean forFeeEstimate);

    public DeployAccountTransactionPayload signDeployAccount(Felt classHash, List<Felt> calldata, Felt salt, Felt maxFee) {
        return  signDeployAccount(classHash, calldata, salt, maxFee, Felt.ZERO, false);
    }

    public abstract DeployAccountTransactionPayload signDeployAccount(Felt classHash, List<Felt> calldata, Felt salt, Felt maxFee, Felt zero, boolean b);

    public abstract DeclareTransactionV1Payload SignDeclare(Cairo0ContractDefinition contractDefinition, Felt classHash, ExecutionParams params, Boolean forFeeEstimate);
    public abstract DeclareTransactionV2Payload SignDeclare(Cairo1ContractDefinition contractDefinition, CasmContractDefinition casmContractDefinition, ExecutionParams params, Boolean forFeeEstimate);

    public abstract  Signature signTypedData(TypedData typedData );
    public abstract Request<Boolean> verifyTypedDataSignature(TypedData typedData, Signature signature );
    public abstract  Request<InvokeFunctionResponse>execute( List<Call> calls, Felt maxFee );
    public Request<InvokeFunctionResponse> execute( Call call, Felt maxFee ){
        return execute(Collections.singletonList(call), maxFee);
    }
    public abstract Request<InvokeFunctionResponse> execute( List<Call> calls);
    public Request<InvokeFunctionResponse> execute(Call call )  {
        return execute(Collections.singletonList(call));
    }

    public abstract Request<List<EstimateFeeResponse>> estimateFee(List<Call> calls);
    public  Request<List<EstimateFeeResponse>> estimateFee(Call call) {
        return estimateFee(Collections.singletonList(call));
    }
    public abstract Request<Felt> getNonce();
}
