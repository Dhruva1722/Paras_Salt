package com.example.saltindustry;

public class DataModel {

    private String shopName;
    private String customerName;
    private String productName;
    private String customerNumber;
    private String price;

    public DataModel() {
        // Default constructor required for calls to DataSnapshot.getValue(DataModel.class)
    }

    public DataModel(String shopName, String customerName, String productName, String customerNumber, String price) {
        this.shopName = shopName;
        this.customerName = customerName;
        this.productName = productName;
        this.customerNumber = customerNumber;
        this.price = price;
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
