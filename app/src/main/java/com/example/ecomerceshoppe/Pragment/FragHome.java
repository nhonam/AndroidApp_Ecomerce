package com.example.ecomerceshoppe.Pragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.ProductAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.activity.ProductDetail;
import com.example.ecomerceshoppe.adapter.CategoryAdapter;
import com.example.ecomerceshoppe.adapter.ProductLatestAdapter;
import com.example.ecomerceshoppe.adapter.SliderAdapter;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Product;
import com.example.ecomerceshoppe.service.CategoryService;
import com.example.ecomerceshoppe.ultils.InternetConnect;
import com.example.ecomerceshoppe.ultils.ObjectWrapperForBinder;
import com.example.ecomerceshoppe.ultils.Utils;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragHome  extends Fragment {
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

    List<Product> ListProduct = new ArrayList<Product>();


    CategoryAdapter categoryAdapter;
    ProductLatestAdapter latestProduct_test;
    SliderAdapter sliderAdapter;


    int[] imgSliders = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.f,

    };

    SliderView sliderView;

    private String category = "";

    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.frag_home, container, false);

        Mapping(view);
        nameLogo = CategoryService.loadLogoName().toArray(new String[0]);
        imgLogo = CategoryService.loadLogo();

        if (InternetConnect.isConnected(getContext())) {
            Toast.makeText(getContext(), "ok", Toast.LENGTH_LONG).show();
//            ProductAPI.getAPIString(Home.this,"https://facebook.com/");
//            ProductAPI.getAPIJson(this,"https://dummyjson.com/products/1");
//            ProductAPI.getAllCategories(this, "Thời Trang Nam", Utils.BASE_URL+"product/search/");




            ProductAPI.getAPIJson(getContext(), Utils.BASE_URL + "product/get/allProduct/", new APICallBack() {
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
                        setEvent();

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }

                @Override
                public void onError(VolleyError error) {

                }
            });

        } else {
            Toast.makeText(getContext()

                    , "khong có internet", Toast.LENGTH_LONG).show();
        }



        return view;
    }

    private void setAdapter() {
        //setCategoryProduct
            categoryAdapter = new CategoryAdapter(getContext(), nameLogo, imgLogo);
        productCategory.setAdapter(categoryAdapter);
        // setlatestProduct_test
        latestProduct_test = new ProductLatestAdapter(getContext(), nameProductList, priceProductList, urlProductList

        );
        gr_productList.setAdapter(latestProduct_test);

        //setAdapterSlider
        sliderAdapter = new SliderAdapter(imgSliders);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }


    private void setEvent() {
        gr_productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Object objSent = new Object();
                final Bundle bundle = new Bundle();
                try {
                    bundle.putBinder("product", new ObjectWrapperForBinder(listProduct.get(i)));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                startActivity(new Intent(getContext(), ProductDetail.class).putExtras(bundle));

            }
        });

        productCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                category = nameLogo[i];

                ProductAPI.getAPIJson(getContext(), Utils.BASE_URL + "product/get/allProduct/" + category, new APICallBack() {

                    @Override
                    public void onSuccess(JSONObject response) {
                        try {
                            nameProductList.clear();
                            priceProductList.clear();
                            urlProductList.clear();
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
                                urlProductList.add(String.valueOf(urlImgTmp));
                            }
                            latestProduct_test.notifyDataSetChanged();

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }

                    @Override
                    public void onError(VolleyError error) {

                    }
                });


            }

        });


    }

    private void Mapping(View view) {
//        navi = findViewById(R.id.bottom_navigation);

        productCategory = view.findViewById(R.id.productCategory);
        gr_productList = view.findViewById(R.id.latestProduct);
        sliderView = view.findViewById(R.id.slider);



    }
}

