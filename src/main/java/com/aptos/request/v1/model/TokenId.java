package com.aptos.request.v1.model;

import com.alibaba.fastjson2.annotation.JSONField;
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
public class TokenId implements Serializable {

    @JSONField(name = "token_data_id")
    TokenDataId tokenDataId;

    @JSONField(name = "property_version")
    String propertyVersion;

}