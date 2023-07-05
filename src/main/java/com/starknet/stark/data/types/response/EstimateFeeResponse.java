package com.starknet.stark.data.types.response;

import com.starknet.stark.data.types.Felt;

import com.google.gson.annotations.SerializedName;

public class EstimateFeeResponse {
    @SerializedName(value = "gas_consumed", alternate = {"gas_usage"})
    private Felt gasConsumed;

    @SerializedName("gas_price")
    private Felt gasPrice;

    @SerializedName("overall_fee")
    private Felt overallFee;

    public EstimateFeeResponse(Felt gasConsumed, Felt gasPrice, Felt overallFee) {
        this.gasConsumed = gasConsumed;
        this.gasPrice = gasPrice;
        this.overallFee = overallFee;
    }

    public Felt getGasConsumed() {
        return gasConsumed;
    }

    public void setGasConsumed(Felt gasConsumed) {
        this.gasConsumed = gasConsumed;
    }

    public Felt getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(Felt gasPrice) {
        this.gasPrice = gasPrice;
    }

    public Felt getOverallFee() {
        return overallFee;
    }

    public void setOverallFee(Felt overallFee) {
        this.overallFee = overallFee;
    }
}
