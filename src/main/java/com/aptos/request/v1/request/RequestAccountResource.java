package com.aptos.request.v1.request;

import com.aptos.request.IAptosRequest;
import com.aptos.request.IAptosRequestQuery;
import com.aptos.request.v1.model.Resource;
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
        if (Objects.isNull(this.resource) || "".equals(this.resource.resourceTag())) {
            throw new RuntimeException("Invalid RequestAccountResource Resource");
        }
        return "/v1/accounts/" + this.account + "/resource/" + this.resource.resourceTag();
    }

    String account;

    Resource resource;

    RequestLedgerVersionQuery query;

    @Override
    public IAptosRequestQuery query() {
        return this.query;
    }

}