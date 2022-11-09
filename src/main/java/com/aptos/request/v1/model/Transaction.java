package com.aptos.request.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {

    public static final String PENDING_TRANSACTION = "pending_transaction";

    public static final String USER_TRANSACTION = "user_transaction";


    @JsonProperty("version")
    String version;

    @JsonProperty("hash")
    String hash;

    @JsonProperty("state_change_hash")
    String stateChangeHash;

    @JsonProperty("event_root_hash")
    String eventRootHash;

    @JsonProperty("state_checkpoint_hash")
    String stateCheckpointHash;

    @JsonProperty("gas_used")
    String gasUsed;

    @JsonProperty("success")
    boolean success;

    @JsonProperty("vm_status")
    String vmStatus;

    @JsonProperty("accumulator_root_hash")
    String accumulatorRootHash;

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

    @JsonProperty("signature")
    Signature signature;

    @JsonProperty("events")
    List<Event<Map>> events;

    @JsonProperty("proposer")
    String proposer;

    @JsonProperty("timestamp")
    String timestamp;

    @JsonProperty("type")
    String type;

    public String getTimestampMillisecond() {
        return String.valueOf(Long.parseLong(this.timestamp) / 1000L);
    }

}