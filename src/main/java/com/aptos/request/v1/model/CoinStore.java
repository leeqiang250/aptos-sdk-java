package com.aptos.request.v1.model;

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
public class CoinStore implements Serializable {

    @JSONField(name = "type")
    String type;

    @JSONField(name = "data")
    Data data;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Data implements Serializable {

        @JSONField(name = "Coin")
        Coin coin;

        @JSONField(name = "frozen")
        boolean frozen;

        @lombok.Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class Coin implements Serializable {

            @JSONField(name = "value")
            String value;

        }

    }

}