package com.example.ecomerceshoppe.activity;

import static java.util.stream.Collectors.mapping;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.model.Product;
import com.example.ecomerceshoppe.ultils.ObjectWrapperForBinder;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class ProductDetail extends AppCompatActivity {

    ImageView Img_ProductDetail;
    TextView nameProductDetail,priceProductDeltail,tagProductDeltail,descriptionProductDetail;
    Button chatNow,addCart,btnBuy;

    private JSONObject productObj = new JSONObject();

    private Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        // nhận về giá trị gửi từ page Home
        final Object objReceived = ((ObjectWrapperForBinder) getIntent().getExtras().getBinder("product")).getData();

        productObj = (JSONObject) objReceived;
        try {
            product = new Product("1",productObj.getString("name_product"),productObj.getString("tag"),(int) productObj.get("quantity"),Double.parseDouble(productObj.getString("price")),productObj.getString("category"),productObj.getString("description"),((JSONObject) productObj.get("img")).getString("url"));
            System.out.println(product);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        mapping();
        setEvent();

//        //show icon back
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setEvent() {
        Glide.with(this).load(product.getUrlImage()).into(Img_ProductDetail);
        nameProductDetail.setText(product.getNameProduct());
        priceProductDeltail.setText(String.valueOf(product.getPrice()));
        tagProductDeltail.setText(product.getTag());
        descriptionProductDetail.setText(product.getDescription());

    }

    private void mapping() {
        Img_ProductDetail = findViewById(R.id.img_ProductDetail);
        nameProductDetail = findViewById(R.id.nameProductDetail);
        priceProductDeltail = findViewById(R.id.priceProductDeltail);
        tagProductDeltail = findViewById(R.id.tagProductDeltail);
        descriptionProductDetail = findViewById(R.id.descriptionProductDetail);
        chatNow = findViewById(R.id.chatnow);
        addCart = findViewById(R.id.addCart);
        btnBuy = findViewById(R.id.buy);

    }
}
