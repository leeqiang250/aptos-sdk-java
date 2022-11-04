package com.aptos.request.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liqiang
 */
@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCollectionData implements Serializable {

    @JsonProperty("type")
    String type;

    @JsonProperty("data")
    Data data;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Data implements Serializable {

        @JsonProperty("collection_data")
        Table collectionData;

        @JsonProperty("token_data")
        Table tokenData;

    }

}