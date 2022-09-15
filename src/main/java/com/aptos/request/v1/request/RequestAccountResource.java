package com.aptos.request.v1.request;

import com.aptos.request.IAptosRequest;
import com.aptos.request.IAptosRequestQuery;
import com.aptos.request.v1.model.Resource;
import lombok.*;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccountResource implements IAptosRequest {

    @Override
    public String path() {
        return "/v1/accounts/" + this.account + "/resource/" + this.resource.resourceTag();
    }

    @NonNull
    String account;

    @NonNull
    Resource resource;

    RequestLedgerVersionQuery query;

    @Override
    public IAptosRequestQuery query() {
        return this.query;
    }

}