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
public class TokenData implements Serializable {

    @JsonProperty("default_properties")
    PropertyMap defaultProperties;

    @JsonProperty("description")
    String description;

    @JsonProperty("largest_property_version")
    String largestPropertyVersion;

    @JsonProperty("maximum")
    String maximum;

    @JsonProperty("mutability_config")
    TokenMutabilityConfig mutabilityConfig;

    @JsonProperty("name")
    String name;

    @JsonProperty("royalty")
    Royalty royalty;

    @JsonProperty("supply")
    String supply;

    @JsonProperty("uri")
    String uri;

}