package com.aptos.utils;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.aptos.request.IAptosRequest;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author liqiang
 */
public abstract class Client {

    private final String host;

    private final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

    public Client(String host) {
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

        return new Request.Builder().get().url(stringBuilder.toString()).build();
    }

    @SneakyThrows
    public String request(IAptosRequest aptosRequest) {
        Request request = getRequest(aptosRequest);
        Response response = okHttpClient.newCall(request).execute();
        if (Objects.isNull(response)) {
            throw AptosRpcException.builder()
                    .message("request execute error")
                    .errorCode("request execute error")
                    .vmErrorCode(null)
                    .build();
        }
        String content = response.body().string();
        if (Objects.isNull(content) || "".equals(content)) {
            throw AptosRpcException.builder()
                    .message("response is null")
                    .errorCode("response is null")
                    .vmErrorCode(null)
                    .build();
        }

        AptosRpcException aptosRPCException = null;
        try {
            aptosRPCException = JSONObject.parseObject(content, AptosRpcException.class);
        } catch (Exception exception) {
        }
        if (Objects.nonNull(aptosRPCException) && Objects.nonNull(aptosRPCException.errorCode) && !"".equals(aptosRPCException.errorCode)) {
            throw aptosRPCException;
        }

        return content;
    }

}