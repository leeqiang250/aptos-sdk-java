package com.aptos.request.v1.model;

import com.alibaba.fastjson2.annotation.JSONField;
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

    @JSONField(name = "sender")
    String sender;

    @JSONField(name = "sequence_number")
    String sequenceNumber;

    @JSONField(name = "max_gas_amount")
    String maxGasAmount;

    @JSONField(name = "gas_unit_price")
    String gasUnitPrice;

    @JSONField(name = "expiration_timestamp_secs")
    String expirationTimestampSecs;

    @JSONField(name = "payload")
    TransactionPayload payload;

    @JSONField(name = "signature")
    Signature signature;

}