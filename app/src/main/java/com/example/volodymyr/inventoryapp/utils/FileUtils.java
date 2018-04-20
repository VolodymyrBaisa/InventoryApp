package com.example.volodymyr.inventoryapp.utils;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();
    private FileUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static FileInputStream readFile(String filePath){
        File file = new File(filePath);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            Log.d(TAG, e.getMessage());
        }
        return null;
    }
}
