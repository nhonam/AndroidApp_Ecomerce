//package com.example.ecomerceshoppe.activity;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.SearchView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.VolleyError;
//import com.example.ecomerceshoppe.API.UserAPI;
//import com.example.ecomerceshoppe.R;
//import com.example.ecomerceshoppe.adapter.ProductManagerAdapter;
//import com.example.ecomerceshoppe.interfaces.APICallBack;
//import com.example.ecomerceshoppe.model.Product;
//import com.example.ecomerceshoppe.ultils.Utils;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//public class ManagerProduct extends AppCompatActivity {
//
//    JSONArray listProduct = new JSONArray();
//    Product product;
//    ListView listViewProduct;
//    ArrayList<Product> ProductList = new ArrayList<>();
//
//    private int positionCurrent = 0;
//
//    private ProductManagerAdapter productManagerAdapter;
//
//    Button btnAddProduct;
//    ImageView btnBack;
//
//    SearchView searchView;
//
//    String idUser = "639efa984b8a0a26a55db03c"; //test
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
//        setContentView(R.layout.layout_manager_product);
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//        SharedPreferences sharedPreferences = getSharedPreferences("matkhau", MODE_PRIVATE);
//        idUser=sharedPreferences.getString("idUserCurent","");
//
////        initData();
//        mapping();
//
//
//        try {
//            UserAPI.GetProductBySeller(this, Utils.BASE_URL + "product/getbyseller", idUser, new APICallBack() {
//
//                @Override
//                public void onSuccess(JSONObject response) throws JSONException {
//                    listProduct = response.getJSONArray("data");
//                    JSONObject productObj = new JSONObject();
//                    for (int i = 0; i < listProduct.length(); i++) {
//                        productObj = (JSONObject) listProduct.get(i);
//
//                        ProductList.add(new Product(productObj.getString("_id"), idUser, productObj.getString("name_product"), productObj.getString("tag"), (int) productObj.get("quantity"), Double.parseDouble(productObj.getString("price")), productObj.getString("category"), productObj.getString("description"), ((JSONObject) productObj.get("img")).getString("url")));
////
////                        System.out.println(ProductList.get(i));
//                    }
//
//                    setEvent();
//                }
//
//                @Override
//                public void onError(VolleyError error) {
//
//                }
//            });
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//
//    private void mapping() {
//        listViewProduct = findViewById(R.id.listviewManagerProduct);
//        searchView = findViewById(R.id.seacrch_SV);
//        searchView.setQueryHint("Nhập vào tên sản phẩm để tìm kiếm");
//        btnAddProduct = findViewById(R.id.addProduct);
//        btnBack = findViewById(R.id.ic_backManagerProduct);
//    }
//
//
//    private void setEvent() {
//        productManagerAdapter = new ProductManagerAdapter(ManagerProduct.this, R.layout.row_manager_product1, ProductList);
//        listViewProduct.setAdapter(productManagerAdapter);
//
//
////        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                positionCurrent = i;
////                product = ProductList.get(i);
////            }
////        });
//
//        btnAddProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ManagerProduct.this, ManagerProductDetail.class);
//                intent.putExtra("idUserCurrent",idUser);
//                startActivity(intent);
//
//
//            }
//        });
//
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ManagerProduct.this, Main.class);
//                startActivity(intent);
//            }
//        });
//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
////                productManagerAdapter.getFilter().filter(s);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
////                productManagerAdapter.getFilter().filter(s);
//                ArrayList<Product> list = new ArrayList<>();
//                for (Product product : ProductList
//                ) {
//
//                    if (product.getNameProduct().toLowerCase().contains(s.toLowerCase())) {
//                        list.add(product);
//                    }
//                }
//                productManagerAdapter = new ProductManagerAdapter(ManagerProduct.this, R.layout.row_manager_product1, list);
//                listViewProduct.setAdapter(productManagerAdapter);
//
//
//                return false;
//            }
//        });
//    }
//
//}