package com.aptos.request.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenId implements Serializable {

    @JsonProperty("token_data_id")
    TokenDataId tokenDataId;

    @JsonProperty("property_version")
    String propertyVersion;

    public String getUniqueKey() {
        return List.of(
                this.tokenDataId.creator,
                this.tokenDataId.collection,
                this.tokenDataId.name,
                this.propertyVersion
        ).stream().collect(Collectors.joining("@"));
    }

    public TokenId fromUniqueKey(String value) {
        var values = value.split("@");

        return TokenId.builder()
                .tokenDataId(TokenDataId.builder()
                        .creator(values[0])
                        .collection(values[1])
                        .name(values[2])
                        .build()
                )
                .propertyVersion(values[3])
                .build();
    }

}