package com.aptos.request.v1.model;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liqiang
 */
@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCollectionData implements Serializable {

    @JSONField(name = "type")
    String type;

    @JSONField(name = "data")
    Data data;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Data implements Serializable {

        @JSONField(name = "collection_data")
        Table collectionData;

        @JSONField(name = "token_data")
        Table tokenData;

        @lombok.Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class Table implements Serializable {

            @JSONField(name = "handle")
            String handle;

        }

    }

}