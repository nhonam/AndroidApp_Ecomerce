package com.example.ecomerceshoppe.model;

import java.util.List;

public class Cart {
    String id;
    //full name của user
    private String shopName;
    private String nameProduct;
    private Double price;
    private int quantity;
    private String urlImage;

    public Cart(String id, String shopName, String nameProduct, Double price, int quantity, String urlImage) {
        this.id = id;
        this.shopName = shopName;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.urlImage = urlImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
