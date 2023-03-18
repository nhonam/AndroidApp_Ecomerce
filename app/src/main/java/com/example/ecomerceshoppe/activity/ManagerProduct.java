package com.example.ecomerceshoppe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.adapter.ProductManagerAdapter;
import com.example.ecomerceshoppe.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ManagerProduct extends AppCompatActivity {

    Product product;
    ListView listViewProduct;
    ArrayList<Product> ProductList = new ArrayList<>();

    private int positionCurrent =0;

    private ProductManagerAdapter productManagerAdapter;

    Button btn_Thoat;
    Button btnCheckAll, btnUnCheckAll, btnXoaDuLieu;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_product);

        initData();

        mapping();
        initData();
        setEvent();

    }

    private void mapping() {
        listViewProduct = findViewById(R.id.listviewManagerProduct);
        searchView = findViewById(R.id.seacrch_SV);
        btnCheckAll=findViewById(R.id.btnCheckAll);;
        btnUnCheckAll=findViewById(R.id.btnUnCheckAll);
        btnXoaDuLieu=findViewById(R.id.btnXoaDuLieu);
        btn_Thoat = findViewById(R.id.btn_Thoat);
    }

    private void initData(){

        ProductList.add(new Product("1", "Đắc Nhân Tâm", "#book",100, 1000.0, "Sách Vở", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
        ProductList.add(new Product("1", "Kỉ Luật Sống", "#book",100, 1000.0, "Sách Vở", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
        ProductList.add(new Product("1", "Áo thể thao", "#ao",100, 1000.0, "Quần Áo", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
        ProductList.add(new Product("1", "Bếp Điện", "#ao",100, 1000.0, "Đồ Gia Dụng", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
        ProductList.add(new Product("1", "Áo Chống Nắng", "#ao",100, 1000.0, "Quần Áo", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
        ProductList.add(new Product("1", "Sách Vở", "#ao",100, 1000.0, "Quần Áo", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
        ProductList.add(new Product("1", "Áo Quần", "#ao",100, 1000.0, "Đồ Điện Tử", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
        ProductList.add(new Product("1", "Đồ Gia Dụng", "#ao",100, 1000.0, "Quần Áo", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
        ProductList.add(new Product("1", "Đồ Gia Dụng", "#ao",100, 1000.0, "Đồ Gia Dụngo", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
        ProductList.add(new Product("1", "Áo Quần", "#ao",100, 1000.0, "Đồ Điện Tử", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
        ProductList.add(new Product("1", "Đồ Điện Tử", "#ao",100, 1000.0, "Quần Áo", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));
        ProductList.add(new Product("1", "Đồ Gia Dụng", "#ao",100, 1000.0, "Đồ Gia Dụng", "Áo khoác gió chống nắng","https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/335123675_157318646850042_7137214095489076443_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=nrXyrXovJVYAX92mzlg&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfAwN3USAPRYClVg0C0r0JvHXIMixYzaNIeCywypDJu4kA&oe=641973F7"));


    }

    private void setEvent() {
        productManagerAdapter = new ProductManagerAdapter(ManagerProduct.this, R.layout.row_manager_product, ProductList);
        listViewProduct.setAdapter(productManagerAdapter);
        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ManagerProduct.this, "Bạn chọn: " + ProductList.get(i) + "/" + i, Toast.LENGTH_SHORT).show();
                positionCurrent = i;
                System.out.println(i);
                product = ProductList.get(i);
            }
        });

        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnXoaDuLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productManagerAdapter.XoaDuLieu();
            }
        });

        btnCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productManagerAdapter.CheckAll();
            }
        });
        btnUnCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productManagerAdapter.UnCheckAll();
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