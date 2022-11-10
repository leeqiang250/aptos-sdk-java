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
public class TokenMutabilityConfig implements Serializable {

    @JsonProperty("maximum")
    boolean maximum;

    @JsonProperty("uri")
    boolean uri;

    @JsonProperty("royalty")
    boolean royalty;

    @JsonProperty("description")
    boolean description;

    @JsonProperty("properties")
    boolean properties;

}