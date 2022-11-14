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

    public String getNftUniqueKey() {
        return this.getCollectionUniqueKey() + "@" + this.name;
    }

    public static TokenDataId fromNftUniqueKey(String value) {
        var values = value.split("@");

        return TokenDataId.builder()
                .creator(values[0])
                .collection(values[1])
                .name(values[2])
                .build();
    }

    public static TokenId zeroPropertyVersionTokenId(TokenDataId tokenDataId) {
        return TokenId.builder()
                .tokenDataId(tokenDataId)
                .propertyVersion("0")
                .build();
    }

}