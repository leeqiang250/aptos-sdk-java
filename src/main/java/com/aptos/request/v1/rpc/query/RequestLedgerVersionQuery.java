package com.aptos.request.v1.rpc.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestLedgerVersionQuery implements IAptosRequestQuery {

    @JsonProperty("ledger_version")
    String ledgerVersion;

}