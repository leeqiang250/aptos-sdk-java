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
public class RequestAccountResource implements IAptosRequest {

    @Override
    public String path() {
        if (Objects.isNull(this.account) || "".equals(this.account)) {
            throw new RuntimeException("Invalid RequestAccountResource Account");
        }
        if (Objects.isNull(this.resourceType) || "".equals(this.resourceType)) {
            throw new RuntimeException("Invalid RequestAccountResource ResourceType");
        }
        return "/v1/accounts/" + this.account + "/resource/" + this.resourceType;
    }

    String account;

    String resourceType;

    RequestLedgerVersionQuery query;

    @Override
    public IAptosRequestQuery query() {
        return this.query;
    }

}