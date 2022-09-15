package com.aptos.utils;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AptosRpcException extends RuntimeException {

    @JSONField(name = "message")
    String message;

    @JSONField(name = "error_code")
    String errorCode;

    @JSONField(name = "vm_error_code")
    String vmErrorCode;

}