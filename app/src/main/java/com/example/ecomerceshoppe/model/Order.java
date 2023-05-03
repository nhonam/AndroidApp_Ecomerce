package com.example.ecomerceshoppe.model;

import java.util.Date;

public class Order {
    private String id;
    private String customer;
    private Double total_Price;
    private String Adress;
    private Date creatAtOrder;
    private String Status;

    public Order(String id, String customer, Double total_Price, String adress, Date creatAtOrder, String status) {
        this.id = id;
        this.customer = customer;
        this.total_Price = total_Price;
        Adress = adress;
        this.creatAtOrder = creatAtOrder;
        Status = status;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Double getTotal_Price() {
        return total_Price;
    }

    public void setTotal_Price(Double total_Price) {
        this.total_Price = total_Price;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public Date getCreatAtOrder() {
        return creatAtOrder;
    }

    public void setCreatAtOrder(Date creatAtOrder) {
        this.creatAtOrder = creatAtOrder;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
