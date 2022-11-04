package com.aptos.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

/**
 * @author liqiang
 */
public final class Jackson {

    static ObjectMapper objectMapper = null;

    public static <T> T readValue(Object json, Class<T> clazz) {
        if (Objects.isNull(json)) {
            return null;
        }

        try {
            if (json instanceof String) {
                return objectMapper.readValue((String) json, clazz);
            } else {
                return objectMapper.readValue(Jackson.toJson(json), clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("{}", e);
        }

        return null;
    }

    public static <T> T readValue(String json, TypeReference<T> typeReference) {
        if (Objects.isNull(json)) {
            return null;
        }

        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("{}", e);
        }

        return null;
    }

    public static <T> T readByteValue(byte[] json, TypeReference<T> typeReference) {
        if (Objects.isNull(json)) {
            return null;
        }

        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("{}", e);
        }
        return null;
    }

    public static String toJson(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("{}", e);
        }

        return null;
    }

    public static byte[] toBytes(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }

        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("{}", e);
        }

        return null;
    }

    public static ObjectMapper getObjectMapper() {
        if (Objects.isNull(objectMapper)) {
            objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        return objectMapper;
    }

}