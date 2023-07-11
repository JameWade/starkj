package com.starknet.stark.data.types.transactions.calculate;

import com.google.gson.JsonParser;
import com.starknet.stark.crypto.Poseidon;
import com.starknet.stark.data.types.Felt;
import com.starknet.stark.data.types.contract.Cairo1ContractDefinition;
import com.starknet.stark.data.types.contract.CasmContractDefinition;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cairo1ClassHashCalculatorUtil {
    public static Felt computeSierraClassHash(Cairo1ContractDefinition contract) {
//        ContractClass contractClass = JsonParser.parseString(ContractClass.serializer(), contract.toJson());
//
//        Felt sierraVersion = Felt.fromShortString("CONTRACT_CLASS_V" + contractClass.getContractClassVersion());
//        Felt externalEntryPointHash = Poseidon.poseidonHash(getSierraEntryPointsArray(contractClass.getEntryPointsByType().getExternal()));
//        Felt l1HandlerEntryPointHash = Poseidon.poseidonHash(getSierraEntryPointsArray(contractClass.getEntryPointsByType().getL1Handler()));
//        Felt constructorEntryPointHash = Poseidon.poseidonHash(getSierraEntryPointsArray(contractClass.getEntryPointsByType().getConstructor()));
//        byte[] abiBytes = contractClass.getAbi().getBytes(StandardCharsets.UTF_8);
//        Felt abiHash = starknetKeccak(abiBytes);
//        Felt sierraProgramHash = Poseidon.poseidonHash(contractClass.getSierraProgram());
//
//        List<Felt> hashInputs = new ArrayList<>(Arrays.asList(sierraVersion, externalEntryPointHash, l1HandlerEntryPointHash, constructorEntryPointHash, abiHash, sierraProgramHash));
//        Felt sierraClassHash = Poseidon.poseidonHash(hashInputs);
//
//        return sierraClassHash;
        return null;
    }

    public static Felt computeCasmClassHash(CasmContractDefinition casmContractDefinition) {
        return null;
    }
//    public Felt computeCasmClassHash(casmContractDefinition contract){}
}
