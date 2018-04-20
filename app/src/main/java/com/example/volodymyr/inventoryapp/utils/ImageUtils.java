package com.example.volodymyr.inventoryapp.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ImageUtils {
    private ImageUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static Bitmap getBitmapFromByteArray(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static Bitmap getBitmapFromInputStream(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    public static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        return stream.toByteArray();
    }
}
