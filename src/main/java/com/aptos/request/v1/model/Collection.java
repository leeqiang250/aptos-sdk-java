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
public class Collection implements Serializable {

    @JsonProperty("creator")
    String creator;

    @JsonProperty("collection")
    String collection;

    public String getCollectionUniqueKey() {
        return this.creator + "@" + this.collection;
    }

    public static Collection fromCollectionUniqueKey(String value) {
        var values = value.split("@");

        return Collection.builder()
                .creator(values[0])
                .collection(values[1])
                .build();
    }

}