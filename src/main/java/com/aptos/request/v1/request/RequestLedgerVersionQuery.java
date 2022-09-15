package com.aptos.request.v1.request;

import com.alibaba.fastjson2.annotation.JSONField;
import com.aptos.request.IAptosRequestQuery;
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

    @JSONField(name = "ledger_version")
    long ledgerVersion;

}