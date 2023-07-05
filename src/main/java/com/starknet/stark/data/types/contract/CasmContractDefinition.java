package com.starknet.stark.data.types.contract;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.starknet.stark.util.Base64Utils;

import java.io.IOException;

import static com.google.gson.JsonParser.parseString;

public class CasmContractDefinition {
    private String casmClassVersion = "COMPILED_CLASS_V1";
    private String prime;
    private String hints;
    private String compilerVersion;
    private String entryPointsByType;
    private String bytecode;

    public CasmContractDefinition(String contract) {
        JsonElement jsonElement = parseString(contract);
        JsonObject compiledContract = jsonElement.getAsJsonObject();
//        JsonObject compiledContract2 = new Gson().fromJson(contract,JsonObject.class);
        bytecode = String.valueOf(compiledContract.get("bytecode"));
        entryPointsByType = String.valueOf(compiledContract.get("entry_points_by_type"));
        prime = String.valueOf(compiledContract.get("prime")) ;
        hints = compiledContract.get("hints").getAsString();
        compilerVersion = compiledContract.get("compiler_version").getAsString();;
    }
    public JsonObject toJson() throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("casm_class_version", casmClassVersion);
        object.addProperty("entry_points_by_type",entryPointsByType);
        object.addProperty("bytecode",bytecode);
        object.addProperty("prime",prime);
        JsonArray arr = new JsonArray();
        arr.add(hints);
        object.add("hints",arr);
    return object;
    }
}
