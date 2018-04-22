package com.example.volodymyr.inventoryapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.Nullable;

public class ImageUtils {
    private static final String TAG = ImageUtils.class.getSimpleName();

    private ImageUtils() {
        throw new IllegalAccessError("Utility class");
    }

    @Nullable
    public static InputStream getImageStream(Context context, String imageLink) {
        try {
            if (context != null) {
                return context.getContentResolver().openInputStream(Uri.parse(imageLink));
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    @Nullable
    public static Bitmap getBitmapFromStream(InputStream imageStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        return BitmapFactory.decodeStream(imageStream, null, options);
    }
}
