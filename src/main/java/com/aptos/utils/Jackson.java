package com.aptos.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liqiang
 */
public final class Jackson {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public Jackson() {
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    }

    public static <T> T readValue(Object json, Class<T> clazz) {
        try {
            if (json instanceof String) {
                return objectMapper.readValue((String) json, clazz);
            } else {
                return objectMapper.readValue(Jackson.toJson(json), clazz);
            }
        } catch (Exception e) {
            //log.error("{}", e);
        }

        return null;
    }

    public static <T> T readValue(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            //log.error("{}", e);
        }

        return null;
    }

    public static <T> T readByteValue(byte[] json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            //log.error("{}", e);
        }
        return null;
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            //log.error("{}", e);
        }

        return null;
    }

    public static byte[] toBytes(Object object) {
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (Exception e) {
            //log.error("{}", e);
        }

        return null;
    }

}