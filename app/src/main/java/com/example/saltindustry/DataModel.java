package com.example.saltindustry;

public class DataModel {

    private String firebaseKey;

    private String date;
    private String shopName;
    private String customerName;
    private String productName;

    public String getDate() {
        return date;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    private String productQuantity ;
    private String customerNumber;
    private String price;

    public DataModel() {
        // Default constructor required for calls to DataSnapshot.getValue(DataModel.class)
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public DataModel(String date, String shopName, String customerName, String productName, String productQuantity, String customerNumber, String price) {
        this.date = date;
        this.shopName = shopName;
        this.customerName = customerName;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.customerNumber = customerNumber;
        this.price = price;
    }

    public String getFirebaseKey() {
        return firebaseKey;
    }

    public void setFirebaseKey(String firebaseKey) {
        this.firebaseKey = firebaseKey;
    }
    public String getShopName() {
        return shopName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getPrice() {
        return price;
    }
}
