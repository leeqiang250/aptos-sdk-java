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
public class TokenDataId implements Serializable {

    @JsonProperty("creator")
    String creator;

    @JsonProperty("collection")
    String collection;

    @JsonProperty("name")
    String name;

    public String getCollectionUniqueKey() {
        return this.creator + "@" + this.collection;
    }

}