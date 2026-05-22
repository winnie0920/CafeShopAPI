package com.cafeshop;

public class ImageUtil {
    private static final String R2_BASE_URL =
            "https://pub-248e3a67abac41649f69b5e50260c0f5.r2.dev/";
    public static String buildUrl(String path) {
        if (path == null || path.isBlank()) return null;

        if (path.startsWith("http")) {
            return path;
        }
        return R2_BASE_URL + path;
    }
}