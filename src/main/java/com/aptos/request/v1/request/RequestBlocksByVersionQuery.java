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
public class RequestBlocksByVersionQuery implements IAptosRequestQuery {

    @JSONField(name = "with_transactions")
    boolean withTransactions = false;

}