package com.aptos.request.v1.rpc.request;

import lombok.*;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestTransactionByHash implements IAptosRequest {

    @Override
    public String path() {
        return "/v1/transactions/by_hash/" + this.hash;
    }

    @NonNull
    String hash;

}