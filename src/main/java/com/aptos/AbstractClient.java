package com.aptos;

import com.aptos.request.v1.model.RequestInfo;
import com.aptos.request.v1.rpc.request.IAptosRequest;
import com.aptos.request.v1.rpc.request.RequestSubmitBatchTransaction;
import com.aptos.request.v1.rpc.request.RequestSubmitTransaction;
import com.aptos.utils.Jackson;
import com.aptos.utils.StringUtils;
import okhttp3.*;
import okio.ByteString;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author liqiang
 */
@SuppressWarnings({"all"})
public abstract class AbstractClient {

    static final MediaType MEDIA_TYPE_JSON_UTF8 = MediaType.parse("application/json;charset=utf-8");

    static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    final String host;

    final Consumer<RequestInfo> info;

    final Consumer<String> log;

    final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

    public AbstractClient(String host, Consumer<RequestInfo> info, Consumer<String> log) {
        this.host = host;
        this.info = info;
        this.log = log;
    }

    public <T> com.aptos.request.v1.model.Response<T> call(IAptosRequest request, Class<T> clazz) {
        String content = null;
        RequestInfo info = RequestInfo.builder()
                .result(true)
                .request(request)
                .build();
        var response = new com.aptos.request.v1.model.Response<T>();
        try {
            content = this.request(request);
            if (!String.class.equals(clazz) || !"\"0x".equals(content.substring(0, 3))) {
                var map = Jackson.readValue(content, Map.class);
                var message = map.get("message");
                var errorCode = map.get("error_code");
                var vmErrorCode = map.get("vm_error_code");
                if (!Objects.isNull(errorCode) && StringUtils.isNotEmpty(errorCode.toString())) {
                    response.setMessage(Objects.isNull(message) ? "" : message.toString());
                    response.setErrorCode(Objects.isNull(errorCode) ? "" : errorCode.toString());
                    response.setVmErrorCode(Objects.isNull(vmErrorCode) ? "" : vmErrorCode.toString());

                    info.setResult(false);
                    info.setMessage(response.getMessage());
                    info.setErrorCode(response.getErrorCode());
                    info.setVmErrorCode(response.getVmErrorCode());

                    this.info.accept(info);

                    return response;
                }
            }

            response.setData(Jackson.readValue(content, clazz));
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setErrorCode(e.getMessage());
            response.setVmErrorCode(e.getMessage());

            info.setResult(false);
            info.setMessage(response.getMessage());
            info.setErrorCode(response.getErrorCode());
            info.setVmErrorCode(response.getVmErrorCode());
        }

        this.info.accept(info);

        return response;
    }

    public <T> com.aptos.request.v1.model.Response<List<T>> callList(IAptosRequest request, Function<String, List<T>> function) {
        String content = null;
        RequestInfo info = RequestInfo.builder()
                .result(true)
                .request(request)
                .build();
        var response = new com.aptos.request.v1.model.Response<List<T>>();
        try {
            content = this.request(request);
            response.setData(function.apply(content));
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setErrorCode(e.getMessage());
            response.setVmErrorCode(e.getMessage());

            info.setResult(false);
            info.setMessage(response.getMessage());
            info.setErrorCode(response.getErrorCode());
            info.setVmErrorCode(response.getVmErrorCode());
        }

        this.info.accept(info);

        return response;
    }

    Request getRequest(IAptosRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.host);
        stringBuilder.append(request.path());
        if (Objects.nonNull(request.query())) {
            stringBuilder.append("?");
            var map = Jackson.readValue(request.query(), Map.class);
            map.forEach(new BiConsumer() {
                @Override
                public void accept(Object s, Object o) {
                    stringBuilder.append(s);
                    stringBuilder.append("=");
                    stringBuilder.append(o);
                    stringBuilder.append("&");
                }
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
                    body = RequestBody.create(MEDIA_TYPE_JSON, ByteString.encodeUtf8(Jackson.toJson(request.body())));
                } else {
                    body = RequestBody.create(Jackson.toJson(request.body()), MEDIA_TYPE_JSON_UTF8);
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
        log.accept("------------------------------------------------------------------------------------------------");
        log.accept("path:" + request.path());
        log.accept("parameter:" + Jackson.toJson(request));
        log.accept("------------------------------------------------------------------------------------------------");
        Request request_ = this.getRequest(request);
        Response response = this.okHttpClient.newCall(request_).execute();
        String content = response.body().string();
        response.close();

        return content;
    }

}