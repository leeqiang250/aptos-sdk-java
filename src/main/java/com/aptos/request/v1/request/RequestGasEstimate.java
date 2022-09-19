package com.aptos.request.v1.request;

import com.aptos.request.IAptosRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
public class RequestGasEstimate implements IAptosRequest {

    @Override
    public String path() {
        return "/v1/estimate_gas_price";
    }

}