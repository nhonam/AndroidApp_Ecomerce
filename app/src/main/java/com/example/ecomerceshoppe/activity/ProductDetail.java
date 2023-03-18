package com.example.ecomerceshoppe.activity;

import static java.util.stream.Collectors.mapping;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.model.Product;

public class ProductDetail extends AppCompatActivity {

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        product = (Product) getIntent().getSerializableExtra("msg");
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
