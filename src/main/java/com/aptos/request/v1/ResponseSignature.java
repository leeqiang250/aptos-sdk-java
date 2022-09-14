package com.aptos.request.v1;

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
public class ResponseSignature implements Serializable {

    @JSONField(name = "type")
    String type;

    @JSONField(name = "public_key")
    String publicKey;

    @JSONField(name = "signature")
    String signature;

}