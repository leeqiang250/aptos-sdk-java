package com.aptos.request.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author liqiang
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class Struct extends Resource {

    public static Struct Account() {
        return Struct.builder()
                .moduleAddress("0x1")
                .moduleName("account")
                .resourceName("Account")
                .build();
    }

    public static Struct APT() {
        return Struct.builder()
                .moduleAddress("0x1")
                .moduleName("aptos_coin")
                .resourceName("AptosCoin")
                .build();
    }

}