package com.starknet.stark.data.types.contract;

import com.google.gson.*;
import com.starknet.stark.util.Base64Utils;
import org.springframework.boot.json.GsonJsonParser;

import java.io.IOException;

import static com.google.gson.JsonParser.parseString;


public class Cairo0ContractDefinition {
    private String contract;
    private String program;
    private String entryPointsByType;
    private String abi;

    public Cairo0ContractDefinition(String contract) {
        JsonElement jsonElement = parseString(contract);
        JsonObject compiledContract = jsonElement.getAsJsonObject();
//        JsonObject compiledContract2 = new Gson().fromJson(contract,JsonObject.class);
       this.contract = contract;
         program = String.valueOf(compiledContract.get("program"));
         entryPointsByType = String.valueOf(compiledContract.get("entry_points_by_type"));
         abi = String.valueOf(compiledContract.get("abi"));
    }
    public JsonObject toJson() throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("program", Base64Utils.base64Gzipped(program.toString()));
        object.addProperty("entry_points_by_type",entryPointsByType);
        if (abi!=null){
            object.addProperty("abi",abi);
        }
        object.add("abi",new JsonArray());
        return object;
    }


}
