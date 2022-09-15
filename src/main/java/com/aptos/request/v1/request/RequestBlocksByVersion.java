package com.aptos.request.v1.request;

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
public class RequestBlocksByVersion implements IAptosRequest {

    @Override
    public String path() {
        return "/v1/blocks/by_version/" + this.ledgerVersion;
    }

    long ledgerVersion;

    RequestBlocksByVersionQuery query = RequestBlocksByVersionQuery.builder()
            .withTransactions(false)
            .build();

    @Override
    public IAptosRequestQuery query() {
        return this.query;
    }

}