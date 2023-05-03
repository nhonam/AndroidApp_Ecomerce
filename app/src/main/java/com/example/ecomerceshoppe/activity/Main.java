package com.example.ecomerceshoppe.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecomerceshoppe.Pragment.FragHome;
import com.example.ecomerceshoppe.Pragment.FragCart;
import com.example.ecomerceshoppe.Pragment.FragOrder;
import com.example.ecomerceshoppe.Pragment.FragProfile;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.ultils.Feature;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main extends AppCompatActivity {
    FragHome fragHome = null;
    FragCart fragCart = null;
    FragOrder fragOrder = null;
    FragProfile fragProfile = null;
    SharedPreferences sharedPreferences;
    BottomNavigationView navi;

    void LoadDataInLocal() {
        sharedPreferences = getSharedPreferences("matkhau", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        String idUserCurent = sharedPreferences.getString("idUserCurent", "");
        String token = sharedPreferences.getString("token", "");
        String userStr = sharedPreferences.getString("user", "");

        //tạo fragHome va truyền dữ liệu vào fragHome
        Bundle bundleHome = new Bundle();
        bundleHome.putString("idUserCurent", idUserCurent);
        fragHome = new FragHome();
        fragHome.setArguments(bundleHome);

        //tạo fragCart va truyền dữ liệu vào fragCart
        Bundle bundleCart = new Bundle();
        bundleCart.putString("idUserCurent", idUserCurent);
        fragCart = new FragCart();
        fragCart.setArguments(bundleCart);


//tạo fragProfile và truyền dữ liệu vào profile
        Bundle bundleProfile = new Bundle();
        bundleProfile.putString("user", userStr);
        bundleProfile.putString("token", token);
        fragProfile = new FragProfile();
        fragProfile.setArguments(bundleProfile);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.main);
        LoadDataInLocal();
        navi = findViewById(R.id.bottom_navigation);
        //nếu bấm lưu trong UpdateProfile thì nhảy qua fragProfile

        if (fragHome == null)
            fragHome = new FragHome();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.fade_out,  // enter
                        R.anim.slide_out_left  // exit
                )
                .replace(R.id.content_main, fragHome)
                .commit();

        navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 switch (item.getItemId()) {
                    case R.id.ic_home:
                        item.setChecked(true);
//                        if (fragHome == null)
//                            fragHome = new FragHome();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(
                                        R.anim.fade_out,  // enter
                                        R.anim.slide_out_left  // exit
                                )
                                .replace(R.id.content_main, fragHome)
                                .commit();
                        break;
                    case R.id.ic_cart:
                        item.setChecked(true);
//                        if (fragNotification == null)
//                            fragNotification = new FragCart();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(
                                        R.anim.slide_in,
                                        R.anim.fade_out
                                )
                                .replace(R.id.content_main, fragCart)
                                .commit();

                        break;
                    case R.id.ic_profile:
                        item.setChecked(true);
//                        if (fragProfile==null)
//                            fragProfile= new FragProfile();

                        getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(
                                        R.anim.fade_out,  // enter
                                        R.anim.slide_in  // exit
                                )
                                .replace(R.id.content_main, fragProfile)
                                .commit();
                        break;
                }
                return false;
            }
        });

    }
}
