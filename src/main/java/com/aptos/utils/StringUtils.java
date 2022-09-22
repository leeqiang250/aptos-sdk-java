package com.aptos.utils;

import javax.annotation.Nullable;

/**
 * @author liqiang
 */
public class StringUtils {

    public static boolean endsWithIgnoreCase(@Nullable String str, @Nullable String suffix) {
        return str != null && suffix != null && str.length() >= suffix.length() && str.regionMatches(true, str.length() - suffix.length(), suffix, 0, suffix.length());
    }

}