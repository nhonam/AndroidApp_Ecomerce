package com.example.ecomerceshoppe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ecomerceshoppe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {


    BottomNavigationView navi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        navi = findViewById(R.id.bottom_navigation_pro);



        ChangActivity();



    }

    private void ChangActivity() {


        navi.setSelectedItemId(R.id.ic_profile);


        navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.ic_notification:
                        startActivity(new Intent(getApplicationContext(), ThongBao.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.ic_profile:
                        return true;
                }
                return true;
            }
        });


    }

}
