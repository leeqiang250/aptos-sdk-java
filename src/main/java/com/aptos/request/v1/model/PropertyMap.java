package com.aptos.request.v1.model;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyMap implements Serializable {

    @JSONField(name = "data")
    List<Data> data;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Data implements Serializable {

        @JSONField(name = "key")
        String key;

        @JSONField(name = "value")
        Value value;

        @lombok.Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class Value implements Serializable {

            @JSONField(name = "type")
            String type;

            @JSONField(name = "value")
            String value;

        }
    }

}
