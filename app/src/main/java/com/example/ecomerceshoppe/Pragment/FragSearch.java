package com.example.ecomerceshoppe.Pragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.ProductAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.adapter.ProductLatestAdapter;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.ultils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragSearch extends Fragment {

    GridView gr_productList;
    JSONArray listProduct = new JSONArray();
    List<String> nameProductList = new ArrayList<String>();

    List<Double> priceProductList = new ArrayList<Double>();

    List<String> urlProductList = new ArrayList<String>();
    ProductLatestAdapter latestProduct_test;
    EditText edtSearch;
    private void Mapping(View view) {
        gr_productList = view.findViewById(R.id.search_list);
        edtSearch = view.findViewById(R.id.edtsearch_product);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_search_product, container, false);
        Mapping(view);

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
//                            priceProductList.add(Double.parseDouble(productTmp.getString("price")));
                        priceProductList.add((productTmp.getDouble("price")));
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



        return view;
    }

    private void setEvent() {
    }

    private void setAdapter() {
        latestProduct_test = new ProductLatestAdapter(getContext(), nameProductList, priceProductList, urlProductList);
        gr_productList.setAdapter(latestProduct_test);
    }
}
