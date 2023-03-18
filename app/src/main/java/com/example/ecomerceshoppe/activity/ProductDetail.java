package com.example.ecomerceshoppe.activity;

import static java.util.stream.Collectors.mapping;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.model.Product;
import com.example.ecomerceshoppe.ultils.ObjectWrapperForBinder;
import com.google.gson.Gson;

public class ProductDetail extends AppCompatActivity {

    private Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Gson gson = new Gson();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
// nhận về giá trị gửi từ page Home
        final Object objReceived = ((ObjectWrapperForBinder) getIntent().getExtras().getBinder("product")).getData();

        mapping();
        setEvent();


        //show icon back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setEvent() {
    }

    private void mapping() {
    }
}
