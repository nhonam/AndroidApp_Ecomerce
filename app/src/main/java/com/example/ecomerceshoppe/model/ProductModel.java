package com.example.ecomerceshoppe.model;

import java.util.List;

public class ProductModel {
    public  boolean success;
    public String message;
    public List data;

    public ProductModel(boolean isSuccess, String message, List productList) {
        this.success = isSuccess;
        this.message = message;
        this.data = productList;
    }

    public boolean getisSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getProductList() {
        return data;
    }

    public void setProductList(List<Product> productList) {
        this.data = productList;
    }
}
