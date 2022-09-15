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
public class RequestBlocksByVersion implements IAptosRequest {

    @Override
    public String path() {
        if (Objects.isNull(this.ledgerVersion) || "".equals(this.ledgerVersion)) {
            throw new RuntimeException("Invalid RequestBlocksByVersion LedgerVersion");
        }

        return "/v1/blocks/by_version/" + this.ledgerVersion;
    }

    String ledgerVersion;

    RequestBlocksByVersionQuery query;

    @Override
    public IAptosRequestQuery query() {
        return this.query;
    }

}