package com.example.ecomerceshoppe.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.example.ecomerceshoppe.Pragment.FragHome;
import com.example.ecomerceshoppe.Pragment.FragNotification;
import com.example.ecomerceshoppe.Pragment.FragProfile;
import com.example.ecomerceshoppe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main extends AppCompatActivity {

    FragHome fragHome=null;
    FragNotification fragNotification  = null;
    FragProfile fragProfile = null;

    BottomNavigationView navi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        navi = findViewById(R.id.bottom_navigation);

        navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        item.setChecked(true);
                        if (fragHome==null)
                            fragHome= new FragHome();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_main,fragHome)
                                .commit();
                        break;
                    case R.id.ic_notification:
                        item.setChecked(true);
                        if (fragNotification==null)
                            fragNotification= new FragNotification();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_main,fragNotification)
                                .commit();

                        break;
                    case R.id.ic_profile:
                        item.setChecked(true);
                        if (fragProfile==null)
                            fragProfile= new FragProfile();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_main,fragProfile)
                                .commit();

                        break;
                }


                return false;
            }
        });

    }
}
