package com.aptos.request.v1.request;

import com.aptos.request.IAptosRequest;
import com.aptos.request.IAptosRequestQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
        if (Objects.isNull(this.account) || "".equals(this.account)) {
            throw new RuntimeException("Invalid RequestAccountResources Account");
        }
        return "/v1/accounts/" + this.account + "/resources";
    }

    String account;

    RequestLedgerVersionQuery query;

    @Override
    public IAptosRequestQuery query() {
        return this.query;
    }

}