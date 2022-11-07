package com.aptos.request.v1.rpc.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
public class RequestGasEstimate extends RequestSeat implements IAptosRequest {

    @Override
    public String path() {
        return "/v1/estimate_gas_price";
    }

}
