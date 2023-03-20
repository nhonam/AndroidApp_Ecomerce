package com.example.ecomerceshoppe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecomerceshoppe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {


    private BottomNavigationView navi;
    private Button btnManagerProduct;
    private ImageView btnCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mapping();

        ChangActivity();

        setEvent();

    }

    private void setEvent() {
        btnManagerProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, ManagerProduct.class);
                startActivity(intent);
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, CartUser.class);
                startActivity(intent);
            }
        });
    }


    private void mapping() {
        btnCart = (ImageView) findViewById(R.id.cart_profile);
        navi = findViewById(R.id.bottom_navigation_pro);
        btnManagerProduct = findViewById(R.id.btnManagerProduct);

    }

    private void ChangActivity() {


        navi.setSelectedItemId(R.id.ic_profile);


        navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.ic_notification:
                        startActivity(new Intent(getApplicationContext(), ThongBao.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.ic_profile:
                        return true;
                }
                return true;
            }
        });


    }

}
