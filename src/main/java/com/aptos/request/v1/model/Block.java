package com.aptos.request.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Block implements Serializable {

    @JsonProperty("block_height")
    String blockHeight;

    @JsonProperty("block_hash")
    String blockHash;

    @JsonProperty("block_timestamp")
    String blockTimestamp;

    @JsonProperty("first_version")
    String firstVersion;

    @JsonProperty("last_version")
    String lastVersion;

    @JsonProperty("transactions")
    List<Transaction> transactions;

}