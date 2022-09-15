package com.aptos.request.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoinStore extends Module {

    Token token;

    @Override
    public String type() {
        if (Objects.isNull(this.token)) {
            throw new RuntimeException("Invalid CoinStore Token");
        }
        return "0x1::coin::CoinStore<" + this.token.type() + ">";
    }

}