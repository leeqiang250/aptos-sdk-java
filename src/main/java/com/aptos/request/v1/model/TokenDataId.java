package com.aptos.request.v1.model;

import com.alibaba.fastjson2.annotation.JSONField;
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
public class TokenDataId {

    @JSONField(name = "creator")
    String creator;

    @JSONField(name = "collection")
    String collection;

    @JSONField(name = "name")
    String name;

}
