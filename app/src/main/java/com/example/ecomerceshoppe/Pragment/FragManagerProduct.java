package com.example.ecomerceshoppe.Pragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.UserAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.activity.ManagerProductDetail;
import com.example.ecomerceshoppe.adapter.ProductManagerAdapter;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Product;
import com.example.ecomerceshoppe.model.User;
import com.example.ecomerceshoppe.ultils.Feature;
import com.example.ecomerceshoppe.ultils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragManagerProduct extends Fragment {

    JSONArray listProduct = new JSONArray();
    ListView listViewProduct;
    ArrayList<Product> ProductList = new ArrayList<>();

    private int positionCurrent = 0;

    private ProductManagerAdapter productManagerAdapter;

    Button btnAddProduct;

    SearchView searchView;

    String idUser = "639efa984b8a0a26a55db03c"; //test

    User userCurrent=null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.layout_manager_product, container, false);
//        SharedPreferences sharedPreferences = getSharedPreferences("matkhau", MODE_PRIVATE);
//        idUser=sharedPreferences.getString("idUserCurent","");
        String UserStr = getArguments().getString("user");
        String token = getArguments().getString("token");

        userCurrent = Feature.ConvertStringtoUser(UserStr);
        idUser = userCurrent.getId();
        System.out.println("idUserCurent Frag: "+idUser);
//        initData();
        mapping(view);


        try {
            UserAPI.GetProductBySeller(getContext(), Utils.BASE_URL + "product/getbyseller", idUser, new APICallBack() {

                @Override
                public void onSuccess(JSONObject response) throws JSONException {
                    listProduct = response.getJSONArray("data");
                    JSONObject productObj = new JSONObject();
                    for (int i = 0; i < listProduct.length(); i++) {
                        productObj = (JSONObject) listProduct.get(i);

                        ProductList.add(new Product(productObj.getString("_id"), idUser, productObj.getString("name_product"), productObj.getString("tag"), (int) productObj.get("quantity"), Double.parseDouble(productObj.getString("price")), productObj.getString("category"), productObj.getString("description"), ((JSONObject) productObj.get("img")).getString("url")));
//
//                        System.out.println(ProductList.get(i));
                    }

                    setEvent();
                }

                @Override
                public void onError(VolleyError error) {

                }
            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return view;
    }

    private void setEvent() {
        productManagerAdapter = new ProductManagerAdapter(getContext(), R.layout.row_manager_product1, ProductList);
        listViewProduct.setAdapter(productManagerAdapter);


//        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                positionCurrent = i;
//                product = ProductList.get(i);
//            }
//        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ManagerProductDetail.class);
                intent.putExtra("idUserCurrent",idUser);
                startActivity(intent);


            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
//                productManagerAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                productManagerAdapter.getFilter().filter(s);
                ArrayList<Product> list = new ArrayList<>();
                for (Product product : ProductList
                ) {

                    if (product.getNameProduct().toLowerCase().contains(s.toLowerCase())) {
                        list.add(product);
                    }
                }
                productManagerAdapter = new ProductManagerAdapter(getContext(), R.layout.row_manager_product1, list);
                listViewProduct.setAdapter(productManagerAdapter);


                return false;
            }
        });
    }

    private void mapping(View view) {
        listViewProduct = view.findViewById(R.id.listviewManagerProduct);
        searchView = view.findViewById(R.id.seacrch_SV);
        searchView.setQueryHint("Nhập vào tên sản phẩm để tìm kiếm");
        btnAddProduct = view.findViewById(R.id.addProduct);

    }

}
