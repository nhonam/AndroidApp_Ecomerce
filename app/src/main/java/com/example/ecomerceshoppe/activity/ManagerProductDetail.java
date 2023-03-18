package com.example.ecomerceshoppe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.PointerIcon;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.adapter.ProductManagerAdapter;
import com.example.ecomerceshoppe.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ManagerProductDetail extends AppCompatActivity {

    private int GALLERY_REQ_CODE = 1000;

    private EditText edtName, edtTag, edtQuanti, edtPrice, edtDescription;
    private Spinner spCategory;
    private ImageView imgProduct;
    private Button btnImage, btnSave, btnExit;

    private Product product;
    ArrayAdapter adapterCategory;

    List<String> ListCategory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_product_detail);
        product = (Product) getIntent().getSerializableExtra("msg");
        mapping();
        setEvent();


        //show icon back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void mapping() {
        edtName = findViewById(R.id.nameProduct_ManagerProductDetail);
        edtTag = findViewById(R.id.tag_ManagerProductDetail);
        edtQuanti = findViewById(R.id.quantity_ManagerProductDetail);
        edtPrice = findViewById(R.id.price_ManagerProductDetail);
        edtDescription = findViewById(R.id.description_ManagerProductDetail);
        spCategory = findViewById(R.id.category_ManagerProductDetail);
        imgProduct = findViewById(R.id.img_ManagerProductDetail);
        btnImage = findViewById(R.id.editImg_ManagerProductDetail);
        btnSave = findViewById(R.id.save_ManagerProductDetail);
        btnExit = findViewById(R.id.exit_ManagerProductDetail);

    }

    private void initDataForCategory(){
        ListCategory.add("Áo Quần");
        ListCategory.add("Sách Vở");
        ListCategory.add("Đồ Gia Dụng");
        ListCategory.add("Đồ Điện Tử");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if (requestCode== GALLERY_REQ_CODE) {
                imgProduct.setImageURI(data.getData());
            }
        }
    }




    private void setEvent() {

        Glide.with(this).load(product.getUrlImage()).into(imgProduct);
        edtName.setText(product.getNameProduct());
        edtTag.setText(product.getTag());
        edtQuanti.setText(String.valueOf(product.getQuantity()));
        edtPrice.setText(  String.valueOf(product.getPrice()));
        edtDescription.setText(product.getDescription());

        initDataForCategory();
        adapterCategory = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ListCategory);
        spCategory.setAdapter(adapterCategory);

        int selectionPosition= adapterCategory.getPosition(product.getCategory());
        System.out.println(product.getCategory());

        spCategory.setSelection(selectionPosition);

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
