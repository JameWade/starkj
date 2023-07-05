package com.starknet.stark.data.types.contract;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.starknet.stark.util.Base64Utils;

import java.io.IOException;

import static com.google.gson.JsonParser.parseString;


public class Cairo1ContractDefinition {

    private String sierraProgram;
    private String entryPointsByType;
    private String contractClassVersion;
    private String abi;

    public Cairo1ContractDefinition(String contract) {
        JsonElement jsonElement = parseString(contract);
        JsonObject compiledContract = jsonElement.getAsJsonObject();
//        JsonObject compiledContract2 = new Gson().fromJson(contract,JsonObject.class);
        sierraProgram = String.valueOf(compiledContract.get("sierra_program"));
         entryPointsByType = String.valueOf(compiledContract.get("entry_points_by_type"));
        contractClassVersion = String.valueOf(compiledContract.get("contract_class_version")) ;
         abi = String.valueOf(compiledContract.get("abi"));
    }
    public JsonObject toJson() throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("program", Base64Utils.base64Gzipped(sierraProgram));
        object.addProperty("entry_points_by_type",entryPointsByType);
        object.addProperty("contract_class_version",contractClassVersion);
        if (abi!=null){
            object.addProperty("abi",abi);
        }
        object.add("abi",new JsonArray());
        return object;
    }


}
