package com.example.ecomerceshoppe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.ecomerceshoppe.API.CartAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Product;
import com.example.ecomerceshoppe.ultils.CustomToast;
import com.example.ecomerceshoppe.ultils.ObjectWrapperForBinder;
import com.example.ecomerceshoppe.ultils.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetail extends AppCompatActivity {
    ImageView Img_ProductDetail, btnBackHome, btnCart;
    TextView nameProductDetail, priceProductDeltail, tagProductDeltail, descriptionProductDetail;
    Button chatNow, addCart, btnBuy;

    private JSONObject productObj = new JSONObject();

    private Product product;
    private String idUserCurrent = "";
    BottomSheetDialog dialogSelectQuanti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.product_detail);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        // nhận về giá trị gửi từ frag Home
        final Object objReceived = ((ObjectWrapperForBinder) getIntent().getExtras().getBinder("product")).getData();
        idUserCurrent = getIntent().getExtras().getString("idUserCurrent");

        productObj = (JSONObject) objReceived;
        try {
            product = new Product(productObj.getString("_id"), ((JSONObject) productObj.get("seller")).getString("_id"), productObj.getString("name_product"), productObj.getString("tag"), (int) productObj.get("quantity"), Double.parseDouble(productObj.getString("price")), productObj.getString("category"), productObj.getString("description"), ((JSONObject) productObj.get("img")).getString("url"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        mapping();
        dialogSelectQuanti = new BottomSheetDialog(this);
        createDialog();
        setEvent();

//        //show icon back
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void loadData() {
        Glide.with(this).load(product.getUrlImage()).into(Img_ProductDetail);
        nameProductDetail.setText(product.getNameProduct());
        priceProductDeltail.setText(String.valueOf(product.getPrice()));
        tagProductDeltail.setText(product.getTag());
        descriptionProductDetail.setText(product.getDescription());
    }

    private void setEvent() {
        loadData();
        chatNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSelectQuanti.show();

            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetail.this, Main.class));
            }
        });

//        btnCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                startActivity(new Intent(ProductDetail.this, CartUser.class));
//            }
//        });

    }


    private void createDialog() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet, null, false);
        Button btnSubmit = view.findViewById(R.id.submit_quanti);
//        EditText edtQuanti = findViewById();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addToCart();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                dialogSelectQuanti.dismiss();
            }
        });
        dialogSelectQuanti.setContentView(view);

    }

    private void addToCart() throws JSONException {
        CartAPI.AddToCart(ProductDetail.this, Utils.BASE_URL + "cart/add", idUserCurrent, product.getId(), 1, new APICallBack() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                CustomToast.makeText(ProductDetail.this, "Thêm sản phẩm vào giỏ hàng thành công", CustomToast.LENGTH_SHORT, CustomToast.SUCCESS, true).show();
            }

            @Override
            public void onError(VolleyError error) {
                CustomToast.makeText(ProductDetail.this, "Thêm sản phẩm vào giỏ hàng thất bại", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();

            }
        });
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
        btnBackHome = findViewById(R.id.back_Productdetail);
        btnCart = findViewById(R.id.cart_Productdetail);

    }
}
