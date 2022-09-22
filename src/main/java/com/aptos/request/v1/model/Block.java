package com.aptos.request.v1.model;

import com.alibaba.fastjson2.annotation.JSONField;
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

    @JSONField(name = "block_height")
    String blockHeight;

    @JSONField(name = "block_hash")
    String blockHash;

    @JSONField(name = "block_timestamp")
    String blockTimestamp;

    @JSONField(name = "first_version")
    String firstVersion;

    @JSONField(name = "last_version")
    String lastVersion;

    @JSONField(name = "transactions")
    List<Transaction> transactions;

}