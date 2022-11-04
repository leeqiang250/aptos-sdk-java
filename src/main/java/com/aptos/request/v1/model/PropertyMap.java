package com.aptos.request.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyMap implements Serializable {

    @JsonProperty("data")
    List<Data> data;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Data implements Serializable {

        @JsonProperty("key")
        String key;

        @JsonProperty("value")
        Value value;

        @lombok.Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class Value implements Serializable {

            @JsonProperty("type")
            String type;

            @JsonProperty("value")
            String value;

        }
    }

}
