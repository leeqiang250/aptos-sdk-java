package com.aptos.request.v1.rpc.query;

import com.alibaba.fastjson2.annotation.JSONField;
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
public class RequestBlockQuery implements IAptosRequestQuery {

    @JSONField(name = "with_transactions")
    boolean withTransactions = false;

}