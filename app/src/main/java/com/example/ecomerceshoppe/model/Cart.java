package com.example.ecomerceshoppe.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Cart implements Parcelable {
    String id;
    //full name của user
    private String shopName;
    private String nameProduct;
    private int price;
    private int quantity;
    private String urlImage;
    private Boolean isSelected = false;

    protected Cart(Parcel in) {
        id = in.readString();
        shopName = in.readString();
        nameProduct = in.readString();
        if (in.readByte() == 0) {
            price = 0;
        } else {
            price = in.readInt();
        }
        quantity = in.readInt();
        urlImage = in.readString();
        byte tmpIsSelected = in.readByte();
        isSelected = tmpIsSelected == 0 ? null : tmpIsSelected == 1;
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

//    public Cart(String id, String shopName, String nameProduct, Double price, int quantity, String urlImage) {
//        this.id = id;
//        this.shopName = shopName;
//        this.nameProduct = nameProduct;
//        this.price = price;
//        this.quantity = quantity;
//        this.urlImage = urlImage;
//    }

    public Cart() {

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(shopName);
        parcel.writeString(nameProduct);
        if (price == 0) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(price);
        }
        parcel.writeInt(quantity);
        parcel.writeString(urlImage);
        parcel.writeByte((byte) (isSelected == null ? 0 : isSelected ? 1 : 2));
    }
}
