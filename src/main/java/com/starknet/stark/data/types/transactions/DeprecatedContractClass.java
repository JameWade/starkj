package com.starknet.stark.data.types.transactions;

import com.google.gson.annotations.SerializedName;
import com.starknet.stark.data.types.contract.AbiElement;
import com.starknet.stark.data.types.contract.DeprecatedCairoEntryPoint;

import java.util.List;

public class DeprecatedContractClass extends ContractClassBase {
    private String program;

    @SerializedName("entry_points_by_type")
    private EntryPointsByType entryPointsByType;

    private List<AbiElement> abi;

    public String getProgram() {
        return program;
    }

    public EntryPointsByType getEntryPointsByType() {
        return entryPointsByType;
    }

    public List<AbiElement> getAbi() {
        return abi;
    }

    public static class EntryPointsByType {
        @SerializedName("CONSTRUCTOR")
        private List<DeprecatedCairoEntryPoint> constructor;

        @SerializedName("EXTERNAL")
        private List<DeprecatedCairoEntryPoint> external;

        @SerializedName("L1_HANDLER")
        private List<DeprecatedCairoEntryPoint> l1Handler;

        public List<DeprecatedCairoEntryPoint> getConstructor() {
            return constructor;
        }

        public List<DeprecatedCairoEntryPoint> getExternal() {
            return external;
        }

        public List<DeprecatedCairoEntryPoint> getL1Handler() {
            return l1Handler;
        }
    }
}