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
import com.starknet.stark.data.types.transactions.*;
import com.starknet.stark.provider.Provider;
import com.starknet.stark.provider.Request;
import com.starknet.stark.signer.Signer;
import com.starknet.stark.signer.StarkCurveSigner;

import java.math.BigInteger;
import java.security.Signature;
import java.util.List;

import static com.starknet.stark.data.types.Call.callsToExecuteCalldata;

public class StandardAccount extends Account{

    private Signer signer;
    private Provider provider;
    private Felt version = Felt.ONE;
    private BigInteger estimateVersion = BigInteger.valueOf(2).pow(128).add(version.getValue());

    public StandardAccount(Felt address, Felt privateKey,Provider provider){
        this.address = address;
        this.signer = new StarkCurveSigner(privateKey);
        this.provider = provider;
    }

    @Override
    public InvokeTransactionPayload sign(List<Call> calls, ExecutionParams params, Boolean forFeeEstimate) {
        List<Felt> calldata = callsToExecuteCalldata(calls);
        Felt signVersion = forFeeEstimate ? new Felt(estimateVersion) : version;
        InvokeTransactionV1 tx = TransactionFactory.makeInvokeTransaction(address, calldata,
                provider.chainId,
                params.getNonce(), params.getMaxFee(), null, signVersion
        );
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
