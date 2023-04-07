package com.example.ecomerceshoppe.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ecomerceshoppe.Pragment.FragManagerProduct;
import com.example.ecomerceshoppe.Pragment.FragProfile;
import com.example.ecomerceshoppe.Pragment.FragRevenue;
import com.example.ecomerceshoppe.R;
import com.google.android.material.navigation.NavigationView;

public class ManagerShop extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    FragManagerProduct fragManagerProduct=null;
    FragRevenue fragRevenue =null;
    SharedPreferences sharedPreferences;

    void LoadDataInLocal(){
        sharedPreferences = getSharedPreferences("matkhau", MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password","");
        String idUserCurent = sharedPreferences.getString("idUserCurent","");
        String token = sharedPreferences.getString("token","");
        String userStr = sharedPreferences.getString("user","");
        Bundle bundle = new Bundle();
        bundle.putString("user", userStr);
        bundle.putString("token", token);
        System.out.println("idUserCurent: "+idUserCurent);


// set Fragmentclass Arguments
        fragManagerProduct= new FragManagerProduct();
        fragManagerProduct.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.NoiDung,fragManagerProduct)
                .commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_shop);
        LoadDataInLocal();
        mapping();
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

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mn_ManagerPD:
//                        Toast.makeText(ManagerShop.this, "Quản lí sản phẩm", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung,fragManagerProduct)
                                .commit();
                        break;
                    case R.id.mn_Revenue:
//                        Toast.makeText(ManagerShop.this, "Thống Kê Doanh Thu", Toast.LENGTH_SHORT).show();
                        if (fragRevenue==null)
                            fragRevenue= new FragRevenue();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung,fragRevenue)
                                .commit();

                        break;

//                    case R.id.mn_ManagerOrder:
//                        Toast.makeText(ManagerShop.this, "Cài đặt", Toast.LENGTH_SHORT).show();
//                        if (fragRevenue==null)
//                            fragRevenue= new FragRevenue();
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.NoiDung,fragRevenue)
//                                .commit();
//
//                        break;

                    case R.id.mn_Exit:
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
    }

}