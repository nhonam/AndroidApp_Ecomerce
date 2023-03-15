package com.example.ecomerceshoppe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ecomerceshoppe.R;

public class Login extends AppCompatActivity {

    AppCompatButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mapping();
        setControl();
    }

    private void setControl() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        btnLogin = findViewById(R.id.btnLogin);
    }


}