package com.aptos.request.v1.request;

import com.alibaba.fastjson2.annotation.JSONField;
import com.aptos.request.IAptosRequestBody;
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
public class RequestTableCollectionDataBody implements IAptosRequestBody {

    @JSONField(name = "key_type")
    String keyType;

    @JSONField(name = "value_type")
    String valueType;

    @JSONField(name = "key")
    String key;

}