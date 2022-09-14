package com.aptos.utils;

import lombok.SneakyThrows;

import java.util.List;

/**
 * @author liqiang
 */
public class AptosClient {


    @SneakyThrows
    public String call(String method, List<Object> params) {
        return "";
    }

//    @SneakyThrows
//    public String call(String method, List<Object> params) {
//        JSONObject jsonBody = new JSONObject();
//        jsonBody.put("jsonrpc", "2.0");
//        jsonBody.put("method", method);
//        jsonBody.put("id", UUID.randomUUID().toString());
//        jsonBody.put("params", params);
//        RequestBody body = RequestBody.create(jsonBody.toString(), JSON_MEDIA_TYPE);
//        Request request = new Request.Builder().post(body).url(this.baseUrl).build();
//        Response response = okHttpClient.newCall(request).execute();
//        return response.body().string();
//    }
}
