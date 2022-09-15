package com.aptos.request.v1.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author liqiang
 */
@Data
@SuperBuilder
public class Token extends Type {

    public static Token APT() {
        return Token.builder()
                .moduleAddress("0x1")
                .moduleName("aptos_coin")
                .name("AptosCoin")
                .build();
    }

}