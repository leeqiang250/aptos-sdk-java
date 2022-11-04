package com.aptos.request.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("type")
    String type;

    @JsonProperty("data")
    Data data;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Data implements Serializable {

        @JsonProperty("Coin")
        Coin coin;

        @JsonProperty("frozen")
        boolean frozen;

        @lombok.Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class Coin implements Serializable {

            @JsonProperty("value")
            String value;

        }

    }

}