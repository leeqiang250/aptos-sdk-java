package com.aptos.request.v1.rpc.body;

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
public class TableBody implements IAptosRequestBody {

    @JSONField(name = "key_type")
    String keyType;

    @JSONField(name = "value_type")
    String valueType;

    @JSONField(name = "key")
    Serializable key;

}