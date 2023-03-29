package com.example.ecomerceshoppe.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.main);

        navi = findViewById(R.id.bottom_navigation);
        if (fragHome==null)
            fragHome= new FragHome();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.fade_out,  // enter
                        R.anim.slide_out_left  // exit
                )
                .replace(R.id.content_main,fragHome)
                .commit();

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
                                .setCustomAnimations(
                                        R.anim.fade_out,  // enter
                                        R.anim.slide_out_left  // exit
                                )
                                .replace(R.id.content_main,fragHome)
                                .commit();
                        break;
                    case R.id.ic_notification:
                        item.setChecked(true);
                        if (fragNotification==null)
                            fragNotification= new FragNotification();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(
                                        R.anim.slide_in ,
                                        R.anim.fade_out

                                )
                                .replace(R.id.content_main,fragNotification)
                                .commit();

                        break;
                    case R.id.ic_profile:
                        item.setChecked(true);
                        if (fragProfile==null)
                            fragProfile= new FragProfile();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(
                                        R.anim.fade_out,  // enter
                                        R.anim.slide_in  // exit
                                )
                                .replace(R.id.content_main,fragProfile)
                                .commit();

                        break;
                }


                return false;
            }
        });

    }
}
