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
public class TableCollectionData implements Serializable {

    @JsonProperty("description")
    String description;

    @JsonProperty("maximum")
    String maximum;

    @JsonProperty("mutability_config")
    MutabilityConfig mutabilityConfig;

    @JsonProperty("name")
    String name;

    @JsonProperty("supply")
    String supply;

    @JsonProperty("uri")
    String uri;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MutabilityConfig implements Serializable {

        @JsonProperty("description")
        boolean description;

        @JsonProperty("maximum")
        boolean maximum;

        @JsonProperty("uri")
        boolean uri;

    }

}