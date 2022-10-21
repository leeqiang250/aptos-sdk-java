package com.aptos.utils;

import com.google.common.io.BaseEncoding;

import java.nio.charset.StandardCharsets;

/**
 * @author liqiang
 */
public class Hex {

    final static String FALSE = "0x00";

    final static String TRUE = "0x01";

    public static String decodeToString(String hex) {
        return new String(decode(hex));
    }

    public static byte[] decode(String hex) {
        return "0x".equalsIgnoreCase(hex.substring(0, 2))
                ? BaseEncoding.base16().decode(hex.substring(2).toUpperCase())
                : BaseEncoding.base16().decode(hex.toUpperCase());
    }

    public static String encode(String text) {
        return encode(text.getBytes(StandardCharsets.UTF_8));
    }

    public static String encode(byte[] bytes) {
        return "0x" + BaseEncoding.base16().encode(bytes).toLowerCase();
    }

    public static String encodeBoolean(boolean b) {
        return b ? TRUE : FALSE;
    }

    public static boolean decodeBoolean(String hex) {
        return TRUE.equalsIgnoreCase(hex);
    }

}