package com.example.ecomerceshoppe.DTO;

import java.util.Date;

public class UserDTO {

    private String id;



    private String fullName;
    private String email;
    private String address;
    private Date birthday;
    private String urlAvatar;
    private String phone;
    private String identity_card;

    private boolean isAdmin;
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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



    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public UserDTO() {

    }

    public UserDTO(String id, String email, String address, Date birthday, String urlAvatar, String phone, String identity_card, boolean isAdmin) {

        this.id = id;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.urlAvatar = urlAvatar;
        this.phone = phone;
        this.identity_card = identity_card;

        this.isAdmin = isAdmin;
    }
}
