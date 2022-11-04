package com.aptos.request.v1.rpc.body;

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
public class TableBody implements IAptosRequestBody {

    @JsonProperty("key_type")
    String keyType;

    @JsonProperty("value_type")
    String valueType;

    @JsonProperty("key")
    Serializable key;

}