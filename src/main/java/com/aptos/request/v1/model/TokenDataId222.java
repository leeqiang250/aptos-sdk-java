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
public class TokenDataId222 implements Serializable {

    @JSONField(name = "amount")
    String amount;

    @JSONField(name = "id")
    String id;

    @JSONField(name = "token_properties")
    String name;

}