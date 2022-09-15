package com.aptos.request.v1.request;

import com.aptos.request.IAptosRequest;
import com.aptos.request.IAptosRequestQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
        if (Objects.isNull(this.height) || "".equals(this.height)) {
            throw new RuntimeException("Invalid RequestBlocksByHeight Height");
        }

        return "/v1/blocks/by_height/" + this.height;
    }

    String height;

    RequestBlockQuery query;

    @Override
    public IAptosRequestQuery query() {
        return this.query;
    }

}
