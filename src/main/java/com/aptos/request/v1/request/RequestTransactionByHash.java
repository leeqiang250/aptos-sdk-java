package com.aptos.request.v1.request;

import com.aptos.request.IAptosRequest;
import lombok.*;

import java.util.Objects;

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
        if (Objects.isNull(this.hash) || "".equals(this.hash)) {
            throw new RuntimeException("Invalid RequestTransactionByHash Hash");
        }

        return "/v1/transactions/by_hash/" + this.hash;
    }

    String hash;

}