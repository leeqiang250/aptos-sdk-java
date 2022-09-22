package com.aptos.request.v1.rpc.request;

import lombok.*;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccount implements IAptosRequest {

    @Override
    public String path() {
        return "/v1/accounts/" + this.account;
    }

    @NonNull
    String account;

}