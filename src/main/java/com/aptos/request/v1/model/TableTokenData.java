package com.aptos.request.v1.model;

import com.alibaba.fastjson2.annotation.JSONField;
import com.aptos.utils.Hex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableTokenData implements Serializable {

    @JSONField(name = "default_properties")
    DefaultProperties defaultProperties;

    @JSONField(name = "description")
    String description;

    @JSONField(name = "largest_property_version")
    String largestPropertyVersion;

    @JSONField(name = "maximum")
    String maximum;

    @JSONField(name = "mutability_config")
    MutabilityConfig mutabilityConfig;

    @JSONField(name = "name")
    String name;

    @JSONField(name = "royalty")
    Royalty royalty;

    @JSONField(name = "supply")
    String supply;

    @JSONField(name = "uri")
    String uri;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class DefaultProperties implements Serializable {

        @JSONField(name = "map")
        Map map;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class Map implements Serializable {

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
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MutabilityConfig implements Serializable {

        @JSONField(name = "description")
        boolean description;

        @JSONField(name = "maximum")
        boolean maximum;

        @JSONField(name = "properties")
        boolean properties;

        @JSONField(name = "royalty")
        boolean royalty;

        @JSONField(name = "uri")
        boolean uri;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Royalty implements Serializable {

        @JSONField(name = "payee_address")
        String payeeAddress;

        @JSONField(name = "royalty_points_denominator")
        String royaltyPointsDenominator;

        @JSONField(name = "royalty_points_numerator")
        String royaltyPointsNumerator;

    }

    public void decode() {
        this.defaultProperties.map.data.forEach(new Consumer<DefaultProperties.Map.Data>() {
            @Override
            public void accept(DefaultProperties.Map.Data data) {
                data.key = Hex.decodeToString(data.key);
                data.value.type = Hex.decodeToString(data.value.type);
                data.value.value = Hex.decodeToString(data.value.value);
            }
        });
        this.description = Hex.decodeToString(this.description);
        this.name = Hex.decodeToString(this.name);
        this.uri = Hex.decodeToString(this.uri);
    }

}