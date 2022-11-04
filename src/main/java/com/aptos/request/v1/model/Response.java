package com.aptos.request.v1.model;

import com.aptos.utils.StringUtils;
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
public class Response<T> implements Serializable {

    @JsonProperty("message")
    String message;

    @JsonProperty("error_code")
    String errorCode;

    @JsonProperty("vm_error_code")
    String vmErrorCode;

    @JsonProperty("data")
    T data;

    public boolean isValid() {
        return StringUtils.isNotEmpty(this.errorCode);
    }

    public static <T> com.aptos.request.v1.model.Response<T> from(Response response, Class<T> clazz) {
        Response<T> response_ = new Response<T>();
        response_.message = response.message;
        response_.errorCode = response.errorCode;
        response_.vmErrorCode = response.vmErrorCode;

        return response_;
    }


    public static <T> com.aptos.request.v1.model.Response<T> from(T data) {
        Response<T> response = new Response<T>();
        response.data = data;

        return response;
    }

}