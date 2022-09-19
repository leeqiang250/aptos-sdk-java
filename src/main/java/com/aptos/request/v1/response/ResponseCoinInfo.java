package com.aptos.request.v1.response;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCoinInfo implements Serializable {

    @JSONField(name = "type")
    String type;

    @JSONField(name = "data")
    ResponseCoinInfo.Data data;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Data implements Serializable {

        @JSONField(name = "decimals")
        int decimals;

        @JSONField(name = "name")
        String name;

        @JSONField(name = "symbol")
        String symbol;

    }

}