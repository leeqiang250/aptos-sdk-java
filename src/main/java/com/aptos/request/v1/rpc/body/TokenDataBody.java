package com.aptos.request.v1.rpc.body;

import com.alibaba.fastjson2.annotation.JSONField;
import com.aptos.request.v1.model.TokenDataId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDataBody implements IAptosRequestBody {

    @JSONField(name = "key_type")
    String keyType;

    @JSONField(name = "value_type")
    String valueType;

    @JSONField(name = "key")
    TokenDataId key;

}