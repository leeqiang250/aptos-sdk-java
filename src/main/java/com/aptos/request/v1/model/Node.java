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
public class Node implements Serializable {

    @JsonProperty("rpc")
    String rpc;

    @JsonProperty("chain_id")
    long chainId;

    @JsonProperty("epoch")
    String epoch;

    @JsonProperty("ledger_version")
    String ledgerVersion;

    @JsonProperty("oldest_ledger_version")
    String oldestLedgerVersion;

    @JsonProperty("ledger_timestamp")
    String ledgerTimestamp;

    @JsonProperty("node_role")
    String nodeRole;

    @JsonProperty("oldest_block_height")
    String oldestBlockHeight;

    @JsonProperty("block_height")
    String blockHeight;

}