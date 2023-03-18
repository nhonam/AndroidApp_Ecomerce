package com.example.ecomerceshoppe.model;

import java.io.Serializable;

public class Product  implements Serializable {
    private String seller;
    private String nameProduct;
    private String tag;
    private int quantity;
    private double price;
    private String category;
    private String description;
    private String urlImage;

    private boolean isSelect;

    public Product(String seller, String nameProduct, String tag, int quantity, double price, String category, String description, String urlImage) {
        this.seller = seller;
        this.nameProduct = nameProduct;
        this.tag = tag;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.description = description;
        this.urlImage = urlImage;
        this.isSelect = false;
    }

    public Product() {
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
