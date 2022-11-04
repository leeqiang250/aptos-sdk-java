package com.aptos.request.v1.model;

import com.aptos.request.v1.rpc.request.IAptosRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("result")
    boolean result;

    @JsonProperty("request")
    IAptosRequest request;

    @JsonProperty("message")
    String message;

    @JsonProperty("error_code")
    String errorCode;

    @JsonProperty("vm_error_code")
    String vmErrorCode;

}