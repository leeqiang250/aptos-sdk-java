package com.aptos.request.v1.rpc.request;

import com.aptos.request.v1.rpc.query.IAptosRequestQuery;
import com.aptos.request.v1.rpc.query.RequestBlockQuery;
import lombok.*;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestBlockByVersion implements IAptosRequest {

    @Override
    public String path() {
        return "/v1/blocks/by_version/" + this.ledgerVersion;
    }

    @NonNull
    String ledgerVersion;

    RequestBlockQuery query;

    @Override
    public IAptosRequestQuery query() {
        return this.query;
    }

}