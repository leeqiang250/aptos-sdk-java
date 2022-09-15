package com.aptos.request.v1.request;

import com.aptos.request.IAptosRequest;
import com.aptos.request.IAptosRequestQuery;
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