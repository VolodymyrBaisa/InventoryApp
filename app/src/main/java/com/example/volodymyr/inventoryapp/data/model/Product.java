package com.example.volodymyr.inventoryapp.data.model;

public class Product {
    private long mId;
    private byte[] mProductImage;
    private String mProductName;
    private int mPrice;
    private int mQuantity;
    private String mSupplierName;
    private String mSupplierPhoneNumber;

    public Product(byte[] productImage, String productName, int price, int quantity, String supplierName, String supplierPhoneNumber) {
        this(-1, productImage, productName, price, quantity, supplierName, supplierPhoneNumber);
    }

    public Product(long id, byte[] productImage, String productName, int price, int quantity, String supplierName, String supplierPhoneNumber) {
        if (id != -1) this.mId = id;
        this.mProductImage = productImage;
        this.mProductName = productName;
        this.mPrice = price;
        this.mQuantity = quantity;
        this.mSupplierName = supplierName;
        this.mSupplierPhoneNumber = supplierPhoneNumber;
    }

    public long getId() {
        return mId;
    }

    public byte[] getProductImage() {
        return mProductImage;
    }

    public String getProductName() {
        return mProductName;
    }

    public int getPrice() {
        return mPrice;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public String getSupplierName() {
        return mSupplierName;
    }

    public String getSupplierPhoneNumber() {
        return mSupplierPhoneNumber;
    }
}
