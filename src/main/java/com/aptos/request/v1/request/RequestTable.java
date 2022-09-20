package com.aptos.request.v1.request;

import com.aptos.request.AptosMethod;
import com.aptos.request.IAptosRequest;
import com.aptos.request.IAptosRequestBody;
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