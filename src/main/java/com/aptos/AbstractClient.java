package com.aptos;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.aptos.request.v1.rpc.request.IAptosRequest;
import com.aptos.request.v1.rpc.request.RequestSubmitBatchTransaction;
import com.aptos.request.v1.rpc.request.RequestSubmitTransaction;
import com.aptos.request.v1.model.AptosException;
import com.aptos.utils.StringUtils;
import lombok.SneakyThrows;
import okhttp3.*;
import okio.ByteString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author liqiang
 */
public abstract class AbstractClient {

    static final MediaType MEDIA_TYPE_JSON_UTF8 = MediaType.parse("application/json;charset=utf-8");

    static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    final String host;

    final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

    public AbstractClient(String host) {
        this.host = host;
    }

    public <T> com.aptos.request.v1.model.Response<T> call(IAptosRequest request, Class<T> clazz) {
        var response = new com.aptos.request.v1.model.Response<T>();
        try {
            String content = this.request(request);
            if (StringUtils.isEmpty(content)) {

            }

            AptosException exception = JSONObject.parseObject(content, AptosException.class);
            if (Objects.nonNull(exception)
                    && Objects.nonNull(exception.getErrorCode())
                    && StringUtils.isNotEmpty(exception.getErrorCode())) {
                return null;
            }

            response.setData(JSONObject.parseObject(content, clazz));
        } catch (Exception e) {

        }

        return response;
    }

    @SneakyThrows
    public <T> List<T> callList(IAptosRequest request, Class<T> clazz) {
        String content = this.request(request);
        JSONArray jsonArray = JSONArray.parseArray(content);
        List<T> list = new ArrayList<>(jsonArray.size());
        jsonArray.forEach(o -> list.add(JSONObject.parseObject(o.toString(), clazz)));
        return list;
    }

    Request getRequest(IAptosRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.host);
        stringBuilder.append(request.path());
        if (Objects.nonNull(request.query())) {
            stringBuilder.append("?");
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(request.query()));
            jsonObject.forEach((s, o) -> {
                stringBuilder.append(s);
                stringBuilder.append("=");
                stringBuilder.append(o);
                stringBuilder.append("&");
            });
        }

        switch (request.method()) {
            case GET: {
                return new Request.Builder()
                        .get()
                        .url(stringBuilder.toString())
                        .build();
            }
            case POST: {
                if (Objects.isNull(request.body())) {
                    throw new RuntimeException("body is null");
                }

                RequestBody body;
                if (StringUtils.endsWithIgnoreCase(request.path(), RequestSubmitTransaction.PATH) || StringUtils.endsWithIgnoreCase(request.path(), RequestSubmitBatchTransaction.PATH)) {
                    body = RequestBody.create(MEDIA_TYPE_JSON, ByteString.encodeUtf8(JSONObject.toJSONString(request.body())));
                } else {
                    body = RequestBody.create(JSONObject.toJSONString(request.body()), MEDIA_TYPE_JSON_UTF8);
                }
                return new Request.Builder()
                        .post(body)
                        .url(stringBuilder.toString())
                        .build();
            }
            default: {
                break;
            }
        }

        throw new RuntimeException("unsupported method");
    }

    public String request(IAptosRequest request) throws IOException {
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("path:" + request.path());
        System.out.println("parameter:" + JSONObject.toJSONString(request));
        Request request_ = this.getRequest(request);
        Response response = this.okHttpClient.newCall(request_).execute();
        String content = response.body().string();
//        System.out.println("content:" + content);
//        System.out.println("------------------------------------------------------------------------------------------------");
//        if (StringUtils.isEmpty(content)) {
//            response.close();
//            throw AptosRpcException.builder()
//                    .message("response is null")
//                    .errorCode("response is null")
//                    .vmErrorCode(null)
//                    .build();
//        }
//
//        AptosRpcException aptosRpcException = null;
//        try {
//            aptosRpcException = JSONObject.parseObject(content, AptosRpcException.class);
//        } catch (JSONException e) {
//        }
//
//        if (Objects.nonNull(aptosRpcException) && Objects.nonNull(aptosRpcException.getErrorCode()) && StringUtils.isNotEmpty(aptosRpcException.getErrorCode())) {
//            response.close();
//            throw aptosRpcException;
//        }
        response.close();

        return content;
    }

}