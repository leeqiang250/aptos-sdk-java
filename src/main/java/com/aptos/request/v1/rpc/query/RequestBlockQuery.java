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
public class RequestBlockQuery implements IAptosRequestQuery {

    @JsonProperty("with_transactions")
    boolean withTransactions = false;

}