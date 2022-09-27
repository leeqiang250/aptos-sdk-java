package com.aptos.request.v1.rpc.request;

import com.aptos.request.v1.rpc.query.IAptosRequestQuery;
import com.aptos.request.v1.rpc.query.RequestTransactionQuery;
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
public class RequestTransaction implements IAptosRequest {

    public static final String PATH = "/v1/transactions";

    @Override
    public String path() {
        return PATH;
    }

    RequestTransactionQuery query;

    @Override
    public IAptosRequestQuery query() {
        return this.query;
    }

}