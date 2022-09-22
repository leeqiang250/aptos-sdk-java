package com.aptos.request.v1.model;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.*;

import java.io.Serializable;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Signature implements Serializable {

    public static final String ED25519_SIGNATURE = "ed25519_signature";

    @JSONField(name = "type")
    String type;

    @JSONField(name = "public_key")
    String publicKey;

    @JSONField(name = "signature")
    String signature;

}