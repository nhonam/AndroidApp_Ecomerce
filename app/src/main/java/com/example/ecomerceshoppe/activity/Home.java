package com.example.ecomerceshoppe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.ProductAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.adapter.CategoryAdapter;
import com.example.ecomerceshoppe.adapter.ProductLatestAdapter;
import com.example.ecomerceshoppe.adapter.SliderAdapter;
import com.example.ecomerceshoppe.interfaces.APIEvent;
import com.example.ecomerceshoppe.service.CategoryService;
import com.example.ecomerceshoppe.service.ProductService;
import com.example.ecomerceshoppe.slider.PhotoAdapter;
import com.example.ecomerceshoppe.ultils.InternetConnect;
import com.example.ecomerceshoppe.ultils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;


public class Home extends AppCompatActivity {

    JSONArray productList;
    //slider
    private ViewPager viewPager;
    //hiển thị slider
    private PhotoAdapter photoAdapter;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    //gridview Category Product
    GridView productCategory, gr_productList;

    //Name Category Product
    String[] nameLogo;
    //logo Category Product
    int[] imgLogo;
    //get listproduct recive from api
    JSONArray listProduct = new JSONArray();

    List<String> nameProductList = new ArrayList<String>();

    List<Double> priceProductList = new ArrayList<Double>();


    List<String> urlProductList = new ArrayList<String>();

    BottomNavigationView navi;


    int[] imgSliders = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,

    };

    SliderView sliderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();
        ChangActivity();


        //setData
        nameLogo = CategoryService.loadLogoName().toArray(new String[0]);
        imgLogo = CategoryService.loadLogo();

        if (InternetConnect.isConnected(this)) {
            Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
//            ProductAPI.getAPIString(Home.this,"https://facebook.com/");
//            ProductAPI.getAPIJson(this,"https://dummyjson.com/products/1");
            ProductAPI.getAPIJson(this, Utils.BASE_URL + "product/get/allProduct", new APIEvent() {
                @Override
                public void onSuccess(JSONObject response) {
                    try {
                        listProduct = response.getJSONArray("data");

                        JSONObject productTmp = new JSONObject();
                        JSONObject imgTmp = new JSONObject();
                        String urlImgTmp = "";
                        for (int i = 0; i < listProduct.length(); i++) {

                            productTmp = (JSONObject) listProduct.get(i);
                            nameProductList.add((String) productTmp.get("name_product"));

                            imgTmp = (JSONObject) productTmp.get("img");
                            urlImgTmp = (String) imgTmp.get("url");

                            priceProductList.add(Double.parseDouble(productTmp.getString("price")));

                            priceProductList.add(1000.0);
                            urlProductList.add(String.valueOf(urlImgTmp));

                        }

                        //init adapter
                        setAdapter();

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "khong có internet", Toast.LENGTH_LONG).show();
        }


    }

//    new ProductAPI.VolleyCallback() {
//        @Override
//        public void onSuccess(JSONObject result) throws JSONException {
//
//        }
//
//        @Override
//        public void onError(VolleyError error) {
//
//        }
//    }


    private void ChangActivity() {


        navi.setSelectedItemId(R.id.ic_home);
        navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        return true;
                    case R.id.ic_notification:
                        startActivity(new Intent(getApplicationContext(), ThongBao.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.ic_profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return true;
            }
        });


    }

    private void setAdapter() {
        //setCategoryProduct
        CategoryAdapter categoryAdapter = new CategoryAdapter(Home.this, nameLogo, imgLogo);
        productCategory.setAdapter(categoryAdapter);
        // setlatestProduct_test
        ProductLatestAdapter latestProduct_test = new ProductLatestAdapter(Home.this, nameProductList, priceProductList, urlProductList

        );
        gr_productList.setAdapter(latestProduct_test);

        //setAdapterSlider
        SliderAdapter sliderAdapter = new SliderAdapter(imgSliders);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }

    private void Mapping() {
        navi = findViewById(R.id.bottom_navigation);
        productCategory = findViewById(R.id.productCategory);
        gr_productList = findViewById(R.id.latestProduct);
        sliderView = findViewById(R.id.slider);


    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }


}