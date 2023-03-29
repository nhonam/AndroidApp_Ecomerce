package com.example.ecomerceshoppe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.UserAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.User;
import com.example.ecomerceshoppe.ultils.CustomToast;
import com.example.ecomerceshoppe.ultils.Singleton;
import com.example.ecomerceshoppe.ultils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    AppCompatButton btnLogin;
    private String username="nhonam";
    private String password="123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);



        mapping();
//        test API Login Success
        try {
            UserAPI.LoginAPI(this, Utils.BASE_URL + "auth/login",username,password, new APICallBack() {
                @Override
                public void onSuccess(JSONObject response) {
                    //tạo 1 user dùng singleton
//                    Singleton userCurrent = Singleton.getInstance(new User());
                    if(true){

                        setControl();
                    }else {
                        System.out.println("đăng nhập không thành công vui lòng nhập lại password và mật khẩu");
                    }

                }

                @Override
                public void onError(VolleyError error) {

                }
            });
        } catch (JSONException e) {
            System.out.println("nam"+e.getMessage());
            throw new RuntimeException(e);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomToast.makeText(Login.this,"Kết Nối Server không thành công",CustomToast.LENGTH_SHORT,CustomToast.ERROR,true).show();
            }
        });

    }

    private void setControl() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Main.class);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        btnLogin = findViewById(R.id.btnLogin);
    }


}