package com.example.volodymyr.inventoryapp.utils;

public class IntegerUtils {
    private static final String TAG = ImageUtils.class.getSimpleName();

    private IntegerUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static int parceInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }
}
