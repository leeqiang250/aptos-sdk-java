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
public class AccountCollectionDataData implements Serializable {

    @JsonProperty("collection_data")
    Table collectionData;

    @JsonProperty("token_data")
    Table tokenData;

}
