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
public class CoinStore extends Resource {

    public static CoinStore coinStore(Resource resource) {
        return CoinStore.builder()
                .moduleAddress("0x1")
                .moduleName("coin")
                .resourceName("CoinStore")
                .resources(List.of(resource))
                .build();
    }

}