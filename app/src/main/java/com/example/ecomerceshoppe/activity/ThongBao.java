package com.example.ecomerceshoppe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ecomerceshoppe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ThongBao extends AppCompatActivity {


    BottomNavigationView navi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_notification);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        navi = findViewById(R.id.bottom_navigation_noti);


        ChangeActivity();



    }

    private void ChangeActivity() {


        navi.setSelectedItemId(R.id.ic_notification);

        navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.ic_notification:

                        return true;
                    case R.id.ic_profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return true;
            }
        });


    }

}
