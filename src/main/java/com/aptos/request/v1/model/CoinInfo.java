package com.aptos.request.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author liqiang
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class CoinInfo extends Resource {

    public static CoinInfo of(Resource resource) {
        return CoinInfo.builder()
                .moduleAddress("0x1")
                .moduleName("coin")
                .resourceName("CoinInfo")
                .resources(List.of(resource))
                .build();
    }

}