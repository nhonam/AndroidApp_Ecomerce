package com.example.ecomerceshoppe.model;

import java.util.Date;

public class User {
    private String userName;
    private String passWord;
    private String fullName;
    private String email;
    private String address;
    private Date birthday;
    private String urlAvatar;
    private String phone;
    private String identity_card;
    private boolean isActive;
    private boolean isAdmin;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentity_card() {
        return identity_card;
    }

    public void setIdentity_card(String identity_card) {
        this.identity_card = identity_card;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    public User() {
    }

    public User(String userName, String passWord, String fullName, String email, String address, Date birthday, String urlAvatar, String phone, String identity_card, boolean isActive, boolean isAdmin) {
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.urlAvatar = urlAvatar;
        this.phone = phone;
        this.identity_card = identity_card;
        this.isActive = isActive;
        this.isAdmin = isAdmin;
    }
}
