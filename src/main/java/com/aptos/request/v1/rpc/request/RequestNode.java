package com.aptos.request.v1.rpc.request;

import lombok.*;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestNode implements IAptosRequest {

    @Override
    public String path() {
        return "/v1";
    }

    String seat;

}