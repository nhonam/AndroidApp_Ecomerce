package com.example.ecomerceshoppe.activity;

import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecomerceshoppe.R;

import org.json.JSONException;

public class VerifyOTP extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.layout_otp);
        //kiểm tra nếu chưa có đầy đủ thông tin tthif phai update thông tin rồi mới gủi OTP


    }
}
