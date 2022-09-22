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
public class CollectionData implements Serializable {

    @JSONField(name = "description")
    String description;

    @JSONField(name = "maximum")
    String maximum;

    @JSONField(name = "mutability_config")
    MutabilityConfig mutabilityConfig;

    @JSONField(name = "name")
    String name;

    @JSONField(name = "supply")
    String supply;

    @JSONField(name = "uri")
    String uri;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MutabilityConfig implements Serializable {

        @JSONField(name = "description")
        boolean description;

        @JSONField(name = "maximum")
        boolean maximum;

        @JSONField(name = "uri")
        boolean uri;

    }

}