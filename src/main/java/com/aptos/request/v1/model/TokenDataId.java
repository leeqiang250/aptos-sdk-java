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
public class TokenDataId implements Serializable {

    @JSONField(name = "creator")
    String creator;

    @JSONField(name = "collection")
    String collection;

    @JSONField(name = "name")
    String name;

    public String getNftGroupKey() {
        return this.creator + "@" + this.collection;
    }

}