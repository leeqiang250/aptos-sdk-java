package com.aptos.utils;

import com.google.common.io.BaseEncoding;

/**
 * @author liqiang
 */
public class Hex {

    public static byte[] decode(String hex) {
        return "0x".equalsIgnoreCase(hex.substring(0, 2))
                ? BaseEncoding.base16().decode(hex.substring(2).toUpperCase())
                : BaseEncoding.base16().decode(hex.toUpperCase());
    }

    public static String encode(byte[] bytes) {
        return "0x" + BaseEncoding.base16().encode(bytes).toLowerCase();
    }

}