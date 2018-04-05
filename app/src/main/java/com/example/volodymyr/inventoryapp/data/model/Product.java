package com.example.volodymyr.inventoryapp.data.model;

public class Product {
    private String mProductName;
    private int mPrice;
    private int mQuantity;
    private String mSupplierName;
    private String mSupplierPhoneNumber;

    public Product(String productName, int price, int quantity, String supplierName, String supplierPhoneNumber) {
        this.mProductName = productName;
        this.mPrice = price;
        this.mQuantity = quantity;
        this.mSupplierName = supplierName;
        this.mSupplierPhoneNumber = supplierPhoneNumber;
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
