package com.aptos.request.v1;

import com.aptos.request.IAptosRequest;

/**
 * @author liqiang
 */
public class RequestLedgerInfo implements IAptosRequest {

    @Override
    public String path() {
        return "/v1";
    }

}