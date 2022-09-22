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
public class RequestBlockByHeight implements IAptosRequest {

    @Override
    public String path() {
        return "/v1/blocks/by_height/" + this.height;
    }

    @NonNull
    String height;

    RequestBlockQuery query;

    @Override
    public IAptosRequestQuery query() {
        return this.query;
    }

}
