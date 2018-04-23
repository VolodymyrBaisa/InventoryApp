package com.example.volodymyr.inventoryapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.volodymyr.inventoryapp.data.model.Product;
import com.example.volodymyr.inventoryapp.di.DatabaseInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DbHelper extends SQLiteOpenHelper {

    public static final String PRODUCT_TABLE_NAME = "products";
    public static final String PRODUCT_COLUMN_PRODUCT_ID = "id";
    public static final String PRODUCT_COLUMN_PRODUCT_IMAGE_LINK = "product_image_link";
    public static final String PRODUCT_COLUMN_PRODUCT_NAME = "product_name";
    public static final String PRODUCT_COLUMN_PRODUCT_PRICE = "price";
    public static final String PRODUCT_COLUMN_PRODUCT_QUANTITY = "quantity";
    public static final String PRODUCT_COLUMN_PRODUCT_SUPPLIER_NAME = "supplier_name";
    public static final String PRODUCT_COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";

    @Inject
    public DbHelper(Context context,
                    @DatabaseInfo String name,
                    @DatabaseInfo int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tableCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
        onCreate(db);
    }

    private void tableCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + PRODUCT_TABLE_NAME + "("
                + PRODUCT_COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PRODUCT_COLUMN_PRODUCT_IMAGE_LINK + " VARCHAR DEFAULT '', "
                + PRODUCT_COLUMN_PRODUCT_NAME + " VARCHAR(80) DEFAULT '', "
                + PRODUCT_COLUMN_PRODUCT_PRICE + " INTEGER(10) DEFAULT 0, "
                + PRODUCT_COLUMN_PRODUCT_QUANTITY + " INTEGER(5) DEFAULT 0, "
                + PRODUCT_COLUMN_PRODUCT_SUPPLIER_NAME + " VARCHAR(30) DEFAULT '', "
                + PRODUCT_COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER + " VARCHAR(25)  DEFAULT ''" + ")");
    }

    public Long insertProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_COLUMN_PRODUCT_IMAGE_LINK, product.getProductImageLink());
        contentValues.put(PRODUCT_COLUMN_PRODUCT_NAME, product.getProductName());
        contentValues.put(PRODUCT_COLUMN_PRODUCT_PRICE, product.getPrice());
        contentValues.put(PRODUCT_COLUMN_PRODUCT_QUANTITY, product.getQuantity());
        contentValues.put(PRODUCT_COLUMN_PRODUCT_SUPPLIER_NAME, product.getSupplierName());
        contentValues.put(PRODUCT_COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, product.getSupplierPhoneNumber());
        return db.insert(PRODUCT_TABLE_NAME, null, contentValues);
    }

    public Product getProduct(Long productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(
                    "SELECT * FROM " + PRODUCT_TABLE_NAME + " WHERE "
                            + PRODUCT_COLUMN_PRODUCT_ID
                            + " = ? ",
                    new String[]{productId + ""});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                return new Product(
                        cursor.getInt(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_ID)),
                        cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_IMAGE_LINK)),
                        cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_NAME)),
                        cursor.getInt(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_QUANTITY)),
                        cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_SUPPLIER_NAME)),
                        cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER)));
            } else {
                throw new Resources.NotFoundException("Product with id " + productId + " does not exists");
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    @Nullable
    public List<Product> getProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(
                    "SELECT * FROM " + PRODUCT_TABLE_NAME, null);

            if (cursor.getCount() > 0) {
                ArrayList<Product> products = new ArrayList<>();

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Product product = new Product(
                            cursor.getInt(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_ID)),
                            cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_IMAGE_LINK)),
                            cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_NAME)),
                            cursor.getInt(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_PRICE)),
                            cursor.getInt(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_QUANTITY)),
                            cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_SUPPLIER_NAME)),
                            cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER)));

                    products.add(product);
                    cursor.moveToNext();
                }

                return products;
            } else {
                return new ArrayList<>();
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    public int updateProduct(Long productId, Product product) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_COLUMN_PRODUCT_IMAGE_LINK, product.getProductImageLink());
        contentValues.put(PRODUCT_COLUMN_PRODUCT_NAME, product.getProductName());
        contentValues.put(PRODUCT_COLUMN_PRODUCT_PRICE, product.getPrice());
        contentValues.put(PRODUCT_COLUMN_PRODUCT_QUANTITY, product.getQuantity());
        contentValues.put(PRODUCT_COLUMN_PRODUCT_SUPPLIER_NAME, product.getSupplierName());
        contentValues.put(PRODUCT_COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, product.getSupplierPhoneNumber());

        return db.update(PRODUCT_TABLE_NAME, contentValues, PRODUCT_COLUMN_PRODUCT_ID + "=" + productId, null);
    }

    public void deleteAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + PRODUCT_TABLE_NAME);
    }

    public void deleteProduct(Long productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(PRODUCT_TABLE_NAME, PRODUCT_COLUMN_PRODUCT_ID + "=" + productId, null);
    }

    public boolean isProductExists(long productId) {
        SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = null;
        try {
            cursor = db.rawQuery(
                    "SELECT * FROM " + PRODUCT_TABLE_NAME + " WHERE "
                            + PRODUCT_COLUMN_PRODUCT_ID
                            + " = ? ",
                    new String[]{productId + ""});

            return cursor.getCount() > 0;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }
}
