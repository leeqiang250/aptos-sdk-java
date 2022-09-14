package com.aptos.request.v1;

import com.aptos.request.IAptosRequest;
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
public class RequestAccountResources implements IAptosRequest {

    @Override
    public String path() {
        return "/v1/accounts/" + this.account + "/resources";
    }

    String account;

    RequestLedgerVersionQuery query;

    @Override
    public IAptosRequestQuery query() {
        return query;
    }

}