package com.example.volodymyr.inventoryapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.volodymyr.inventoryapp.data.model.Product;
import com.example.volodymyr.inventoryapp.di.DatabaseInfo;

import javax.inject.Inject;

public class DbHelper extends SQLiteOpenHelper {

    public static final String PRODUCT_TABLE_NAME = "products";
    public static final String PRODUCT_COLUMN_PRODUCT_ID = "id";
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
                + PRODUCT_COLUMN_PRODUCT_NAME + " VARCHAR(50) DEFAULT '', "
                + PRODUCT_COLUMN_PRODUCT_PRICE + " INTEGER(10) DEFAULT 0, "
                + PRODUCT_COLUMN_PRODUCT_QUANTITY + " INTEGER(5) DEFAULT 0, "
                + PRODUCT_COLUMN_PRODUCT_SUPPLIER_NAME + " VARCHAR(20) DEFAULT '', "
                + PRODUCT_COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER + " VARCHAR(20)  DEFAULT ''" + ")");
    }

    public Long insertProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
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
                Product product = new Product(
                        cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_NAME)),
                        cursor.getInt(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_QUANTITY)),
                        cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_SUPPLIER_NAME)),
                        cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER)));
                return product;
            } else {
                throw new Resources.NotFoundException("Product with id " + productId + " does not exists");
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }
}
