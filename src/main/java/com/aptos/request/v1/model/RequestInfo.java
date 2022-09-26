package com.aptos.request.v1.model;

import com.alibaba.fastjson2.annotation.JSONField;
import com.aptos.request.v1.rpc.request.IAptosRequest;
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
public class RequestInfo implements Serializable {

    @JSONField(name = "result")
    boolean result;

    @JSONField(name = "request")
    IAptosRequest request;

    @JSONField(name = "message")
    String message;

    @JSONField(name = "error_code")
    String errorCode;

    @JSONField(name = "vm_error_code")
    String vmErrorCode;

}