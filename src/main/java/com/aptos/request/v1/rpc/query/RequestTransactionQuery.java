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
public class RequestTransactionQuery implements IAptosRequestQuery {

    @JSONField(name = "limit")
    String limit;

    @JSONField(name = "start")
    String start;

}