package com.aptos.request.v1.rpc.request;

import com.aptos.request.v1.rpc.query.IAptosRequestQuery;
import com.aptos.request.v1.rpc.query.RequestLedgerVersionQuery;
import lombok.*;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccountResources implements IAptosRequest {

    @Override
    public String path() {
        return "/v1/accounts/" + this.account + "/resources";
    }

    @NonNull
    String account;

    RequestLedgerVersionQuery query;

    @Override
    public IAptosRequestQuery query() {
        return this.query;
    }

}