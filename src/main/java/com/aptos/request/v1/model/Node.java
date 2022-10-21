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
public class Node implements Serializable {

    @JSONField(name = "rpc")
    String rpc;

    @JSONField(name = "chain_id")
    long chainId;

    @JSONField(name = "epoch")
    String epoch;

    @JSONField(name = "ledger_version")
    String ledgerVersion;

    @JSONField(name = "oldest_ledger_version")
    String oldestLedgerVersion;

    @JSONField(name = "ledger_timestamp")
    String ledgerTimestamp;

    @JSONField(name = "node_role")
    String nodeRole;

    @JSONField(name = "oldest_block_height")
    String oldestBlockHeight;

    @JSONField(name = "block_height")
    String blockHeight;

}