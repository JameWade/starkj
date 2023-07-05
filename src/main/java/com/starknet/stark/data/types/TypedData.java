package com.starknet.stark.data.types;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TypedData {
    private Map<String, List<Type>> types ;
    private String primaryType ;
    private JsonObject domain;
    private JsonObject message;

    public TypedData(Map<String, List<Type>> types, String primaryType, JsonObject domain, JsonObject message) {
        this.types = types;
        this.primaryType = primaryType;
        this.domain = domain;
        this.message = message;
    }

    class Type{
        private String name;
        private String type;

        public String getType() {
            return type;
        }

    }
    private List<String> getDependencies(String typeName) {
        List<String> deps = new ArrayList<>();
        List<String> toVisit = new ArrayList<>();

        deps.add(typeName);
        toVisit.add(typeName);

        while (!toVisit.isEmpty()) {
            String type = toVisit.remove(0);
            List<Type> params = types.get(type);
            if (params == null) {
                params = Collections.emptyList();
            }

            for (Type param : params) {
                String typeStripped = stripPointer(param.getType());

                if (types.containsKey(typeStripped) && !deps.contains(typeStripped)) {
                    deps.add(typeStripped);
                    toVisit.add(typeStripped);
                }
            }
        }

        return deps;
    }

    private  String    stripPointer(String value)  {
        if (value.endsWith("*")){
            return value.substring(0,value.length()-1);
        }

        return value;
    }

}
