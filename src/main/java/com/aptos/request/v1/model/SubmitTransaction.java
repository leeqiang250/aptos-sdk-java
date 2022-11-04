package com.aptos.request.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitTransaction implements Serializable {

    @JsonProperty("sender")
    String sender;

    @JsonProperty("sequence_number")
    String sequenceNumber;

    @JsonProperty("max_gas_amount")
    String maxGasAmount;

    @JsonProperty("gas_unit_price")
    String gasUnitPrice;

    @JsonProperty("expiration_timestamp_secs")
    String expirationTimestampSecs;

    @JsonProperty("payload")
    TransactionPayload payload;

    @JsonProperty("signature")
    Signature signature;

}