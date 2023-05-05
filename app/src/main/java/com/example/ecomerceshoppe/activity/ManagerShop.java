package com.example.ecomerceshoppe.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.ecomerceshoppe.Pragment.FragManagerProduct;
import com.example.ecomerceshoppe.Pragment.FragOrder;
import com.example.ecomerceshoppe.Pragment.FragRevenue;
import com.example.ecomerceshoppe.Pragment.FragSell;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.model.User;
import com.example.ecomerceshoppe.ultils.Feature;
import com.google.android.material.navigation.NavigationView;

public class ManagerShop extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    FragManagerProduct fragManagerProduct=null;
    FragRevenue fragRevenue =null;
    FragOrder fragOrder =null;
    FragSell fragSell =null;
    SharedPreferences sharedPreferences;
    ImageView avtShop;
    TextView shopName;
    String userStrCurent;
    Bundle bundle = new Bundle();
    void LoadDataInLocal(){
        sharedPreferences = getSharedPreferences("matkhau", MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password","");
        String idUserCurent = sharedPreferences.getString("idUserCurent","");
        String token = sharedPreferences.getString("token","");
        userStrCurent = sharedPreferences.getString("user","");

        bundle.putString("user", userStrCurent);
        bundle.putString("token", token);
        //set Avatar and name của Shop


// set Fragmentclass Arguments
        fragManagerProduct= new FragManagerProduct();
        fragManagerProduct.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.NoiDung,fragManagerProduct)
                .commit();

        //tạo fragOrder va truyền dữ liệu vào fragOrder
        Bundle bundleOrder = new Bundle();
        bundleOrder.putString("idUserCurent", idUserCurent);
        fragOrder = new FragOrder();
        fragOrder.setArguments(bundleOrder);

        //tạo fragSell va truyền dữ liệu vào fragSell
        Bundle bundleSell = new Bundle();
        bundleSell.putString("idUserCurent", idUserCurent);
        fragSell = new FragSell();
        fragSell.setArguments(bundleSell);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_shop);

        mapping();
        LoadDataInLocal();

        setEvent();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private void setEvent() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //set ảnh đại diện của shop và tên của shop
        User userCurrent = Feature.ConvertStringtoUser(userStrCurent);
        Glide.with(ManagerShop.this).load(userCurrent.getUrlAvatar()).into(avtShop);
        shopName.setText(userCurrent.getFullName());
        //end
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mn_ManagerPD:
//                        item.setChecked(true);
//                        Toast.makeText(ManagerShop.this, "Quản lí sản phẩm", Toast.LENGTH_SHORT).show();
//                        fragManagerProduct.setArguments(bundle);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung,fragManagerProduct)
                                .commit();
;

                        break;
                    case R.id.mn_Revenue:
//                        item.setChecked(true);
//                        Toast.makeText(ManagerShop.this, "Thống Kê Doanh Thu", Toast.LENGTH_SHORT).show();
                        if (fragRevenue==null)
                            fragRevenue= new FragRevenue();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung,fragRevenue)
                                .commit();

                        break;

                    case R.id.mn_ManagerOrder:
//                        FragOrder fragOrder = new FragOrder();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung,fragOrder)
                                .commit();

                        break;


                    case R.id.mn_Sell:
//                        FragOrder fragOrder = new FragOrder();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung,fragSell)
                                .commit();

                        break;


                    case R.id.mn_Exit:
                        item.setChecked(true);
//                        Toast.makeText(ManagerShop.this, "Cài đặt", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ManagerShop.this, Main.class));
                        break;

                }
                if (drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });
    }

    private void mapping() {

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
        View headerView = navigationView.getHeaderView(0);
        avtShop =  headerView.findViewById(R.id.avt_Shop);
        shopName = (TextView) headerView.findViewById(R.id.shopName);
    }

}
