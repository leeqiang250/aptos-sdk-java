package com.aptos.request.v1.rpc.request;

import com.aptos.request.v1.rpc.AptosMethod;
import com.aptos.request.v1.rpc.body.IAptosRequestBody;
import lombok.*;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestTable implements IAptosRequest {

    @Override
    public String path() {
        return "/v1/tables/" + this.handle + "/item";
    }

    @NonNull
    String handle;

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