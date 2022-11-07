package com.aptos.request.v1.rpc.request;

import lombok.*;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
public class RequestNode extends RequestSeat implements IAptosRequest {

    @Override
    public String path() {
        return "/v1";
    }

}