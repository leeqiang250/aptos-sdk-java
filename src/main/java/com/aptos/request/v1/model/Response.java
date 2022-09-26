package com.aptos.request.v1.model;

import com.alibaba.fastjson2.annotation.JSONField;
import com.aptos.utils.StringUtils;
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

    @JSONField(name = "message")
    String message;

    @JSONField(name = "error_code")
    String errorCode;

    @JSONField(name = "vm_error_code")
    String vmErrorCode;

    @JSONField(name = "data")
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