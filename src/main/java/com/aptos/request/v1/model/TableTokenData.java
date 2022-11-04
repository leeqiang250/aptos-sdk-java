package com.aptos.request.v1.model;

import com.aptos.utils.Hex;
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
public class TableTokenData implements Serializable {

    @JsonProperty("default_properties")
    DefaultProperties defaultProperties;

    @JsonProperty("description")
    String description;

    @JsonProperty("largest_property_version")
    String largestPropertyVersion;

    @JsonProperty("maximum")
    String maximum;

    @JsonProperty("mutability_config")
    MutabilityConfig mutabilityConfig;

    @JsonProperty("name")
    String name;

    @JsonProperty("royalty")
    Royalty royalty;

    @JsonProperty("supply")
    String supply;

    @JsonProperty("uri")
    String uri;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class DefaultProperties implements Serializable {

        @JsonProperty("map")
        PropertyMap map;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MutabilityConfig implements Serializable {

        @JsonProperty("description")
        boolean description;

        @JsonProperty("maximum")
        boolean maximum;

        @JsonProperty("properties")
        boolean properties;

        @JsonProperty("royalty")
        boolean royalty;

        @JsonProperty("uri")
        boolean uri;

    }

    public void decode() {
        this.defaultProperties.map.data.forEach(data -> {
            data.key = Hex.decodeToString(data.key);
            data.value.type = Hex.decodeToString(data.value.type);
            data.value.value = Hex.decodeToString(data.value.value);
        });
        this.description = Hex.decodeToString(this.description);
        this.name = Hex.decodeToString(this.name);
        this.uri = Hex.decodeToString(this.uri);
    }

}