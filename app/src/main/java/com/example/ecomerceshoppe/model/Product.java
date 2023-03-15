package com.example.ecomerceshoppe.model;

public class Product {
    private int id;
    private String nameProduct;
    private String urlImg;
    private int quantity;
    private double price;
    private String category;
    private String description;


    public Product(int id, String nameProduct, String urlImg, int quantity, double price, String category, String description) {

        this.id = id;
        this.nameProduct = nameProduct;
        this.urlImg = urlImg;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public Product(String nameProduct, String urlImg, double price) {
        this.nameProduct = nameProduct;
        this.urlImg = urlImg;
        this.price = price;


        this.id = 0;

        this.quantity = 0;

        this.category = null;
        this.description = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
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


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", urlImg='" + urlImg + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
