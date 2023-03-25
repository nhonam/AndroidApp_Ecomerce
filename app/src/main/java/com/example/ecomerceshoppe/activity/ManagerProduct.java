package com.example.ecomerceshoppe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.UserAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.adapter.ProductManagerAdapter;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Product;
import com.example.ecomerceshoppe.ultils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManagerProduct extends AppCompatActivity {

    JSONArray listProduct = new JSONArray();
    Product product;
    ListView listViewProduct;
    ArrayList<Product> ProductList = new ArrayList<>();

    private int positionCurrent =0;

    private ProductManagerAdapter productManagerAdapter;

    Button btnAddProduct;

    SearchView searchView ;

    String idUser ="639efa984b8a0a26a55db03c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.layout_manager_product);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

//        initData();
        mapping();



        try {
            UserAPI.GetProductBySeller(this, Utils.BASE_URL+"product/getbyseller", idUser,new APICallBack() {

                @Override
                public void onSuccess(JSONObject response) throws JSONException {
                    listProduct = response.getJSONArray("data");
                    JSONObject productObj = new JSONObject();
                    for (int i = 0; i < listProduct.length(); i++) {
                        productObj = (JSONObject) listProduct.get(i);

                        ProductList.add( new Product( productObj.getString("_id"),idUser,productObj.getString("name_product"),productObj.getString("tag"),(int) productObj.get("quantity"),Double.parseDouble(productObj.getString("price")),productObj.getString("category"),productObj.getString("description"),((JSONObject) productObj.get("img")).getString("url")));
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



    }

    private void mapping() {
        listViewProduct = findViewById(R.id.listviewManagerProduct);
        searchView = findViewById(R.id.seacrch_SV);
        btnAddProduct = findViewById(R.id.addProduct);

    }

    private void initData(){

//        ProductList.add(new Product("1", "Đắc Nhân Tâm", "#book",100, 1000.0, "Sách Vở", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
//        ProductList.add(new Product("1", "Kỉ Luật Sống", "#book",100, 1000.0, "Sách Vở", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
//        ProductList.add(new Product("1", "Áo thể thao", "#ao",100, 1000.0, "Quần Áo", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
//        ProductList.add(new Product("1", "Bếp Điện", "#ao",100, 1000.0, "Đồ Gia Dụng", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
//        ProductList.add(new Product("1", "Áo Chống Nắng", "#ao",100, 1000.0, "Quần Áo", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
//        ProductList.add(new Product("1", "Sách Vở", "#ao",100, 1000.0, "Quần Áo", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
//        ProductList.add(new Product("1", "Áo Quần", "#ao",100, 1000.0, "Đồ Điện Tử", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
//        ProductList.add(new Product("1", "Đồ Gia Dụng", "#ao",100, 1000.0, "Quần Áo", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
//        ProductList.add(new Product("1", "Đồ Gia Dụng", "#ao",100, 1000.0, "Đồ Gia Dụngo", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
//        ProductList.add(new Product("1", "Áo Quần", "#ao",100, 1000.0, "Đồ Điện Tử", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
//        ProductList.add(new Product("1", "Đồ Điện Tử", "#ao",100, 1000.0, "Quần Áo", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
//        ProductList.add(new Product("1", "Đồ Gia Dụng", "#ao",100, 1000.0, "Đồ Gia Dụng", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
//

    }

    private void setEvent() {
        productManagerAdapter = new ProductManagerAdapter(ManagerProduct.this, R.layout.row_manager_product1, ProductList);
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
                Intent intent = new Intent(ManagerProduct.this, ManagerProductDetail.class);
                startActivity(intent);
            }
        });




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                productManagerAdapter.searchProduct(s);
                return false;
            }
        });
    }

}