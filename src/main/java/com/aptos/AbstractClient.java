package com.aptos;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import com.aptos.request.v1.rpc.request.IAptosRequest;
import com.aptos.request.v1.rpc.request.RequestSubmitBatchTransaction;
import com.aptos.request.v1.rpc.request.RequestSubmitTransaction;
import com.aptos.request.v1.model.AptosRpcException;
import com.aptos.utils.StringUtils;
import lombok.SneakyThrows;
import okhttp3.*;
import okio.ByteString;

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

    @SneakyThrows
    public <T> T call(IAptosRequest aptosRequest, Class<T> aptosResponseClass) {
        String content = request(aptosRequest);
        return JSONObject.parseObject(content, aptosResponseClass);
    }

    @SneakyThrows
    public <T> List<T> callList(IAptosRequest aptosRequest, Class<T> aptosResponseClass) {
        String content = request(aptosRequest);
        JSONArray jsonArray = JSONArray.parseArray(content);
        List<T> list = new ArrayList<>(jsonArray.size());
        jsonArray.forEach(o -> list.add(JSONObject.parseObject(o.toString(), aptosResponseClass)));
        return list;
    }

    Request getRequest(IAptosRequest aptosRequest) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.host);
        stringBuilder.append(aptosRequest.path());
        if (Objects.nonNull(aptosRequest.query())) {
            stringBuilder.append("?");
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(aptosRequest.query()));
            jsonObject.forEach((s, o) -> {
                stringBuilder.append(s);
                stringBuilder.append("=");
                stringBuilder.append(o);
                stringBuilder.append("&");
            });
        }

        switch (aptosRequest.method()) {
            case GET: {
                return new Request.Builder()
                        .get()
                        .url(stringBuilder.toString())
                        .build();
            }
            case POST: {
                if (Objects.isNull(aptosRequest.body())) {
                    throw new RuntimeException("body is null");
                }

                RequestBody body;
                if (StringUtils.endsWithIgnoreCase(aptosRequest.path(), RequestSubmitTransaction.PATH) || StringUtils.endsWithIgnoreCase(aptosRequest.path(), RequestSubmitBatchTransaction.PATH)) {
                    body = RequestBody.create(MEDIA_TYPE_JSON, ByteString.encodeUtf8(JSONObject.toJSONString(aptosRequest.body())));
                } else {
                    body = RequestBody.create(JSONObject.toJSONString(aptosRequest.body()), MEDIA_TYPE_JSON_UTF8);
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

    @SneakyThrows
    public String request(IAptosRequest aptosRequest) {
        Request request = getRequest(aptosRequest);
        Response response = this.okHttpClient.newCall(request).execute();
        String content = response.body().string();
        System.out.println(content);
        if ("".equals(content)) {
            response.close();
            throw AptosRpcException.builder()
                    .message("response is null")
                    .errorCode("response is null")
                    .vmErrorCode(null)
                    .build();
        }

        AptosRpcException aptosRpcException = null;
        try {
            aptosRpcException = JSONObject.parseObject(content, AptosRpcException.class);
        } catch (JSONException exception) {
        }
        if (Objects.nonNull(aptosRpcException) && Objects.nonNull(aptosRpcException.getErrorCode()) && !"".equals(aptosRpcException.getErrorCode())) {
            response.close();
            throw aptosRpcException;
        }
        response.close();

        return content;
    }

}