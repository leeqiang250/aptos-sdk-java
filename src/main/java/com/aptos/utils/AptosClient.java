package com.aptos.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.util.JSONObject1O;
import com.aptos.request.IAptosRequest;
import com.aptos.request.v1.RequestBlocksByVersion;
import com.aptos.request.v1.RequestBlocksByVersionQuery;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * @author liqiang
 */
public class AptosClient {

    private final String host;

    private final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

    public AptosClient(String host) {
        this.host = host;
    }

    @SneakyThrows
    public <T> T call(IAptosRequest aptosRequest, Class<T> objectClass) {
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

        Request request = new Request.Builder().get().url(stringBuilder.toString()).build();
        Response response = okHttpClient.newCall(request).execute();
        String content = response.body().string();
        if (Objects.isNull(content) || "".equals(content)) {
            return null;
        }
        return JSONObject.parseObject(content, objectClass);
    }

}