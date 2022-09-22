package com.aptos.request.v1.rpc.request;

import com.aptos.request.v1.rpc.AptosMethod;
import com.aptos.request.v1.rpc.body.IAptosRequestBody;
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
public class RequestSubmitBatchTransaction implements IAptosRequest {

    public static final String PATH = "/v1/transactions/batch";

    @Override
    public String path() {
        return PATH;
    }

    IAptosRequestBody body;

    @Override
    public IAptosRequestBody body() {
        return this.body;
    }

    @Override
    public AptosMethod method() {
        return AptosMethod.POST;
    }

}