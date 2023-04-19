package com.example.ecomerceshoppe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.SentMail;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.ultils.CustomToast;
import com.example.ecomerceshoppe.ultils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class SentOTP extends AppCompatActivity {
    AppCompatButton btnSentEmail;
    TextView tvEmail;
    private String email="";
    private String idUserCurrent="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.sent_otp);
        //kiểm tra nếu chưa có đầy đủ thông tin tthif phai update thông tin rồi mới gủi OTP

        email = getIntent().getStringExtra("email");
        idUserCurrent = getIntent().getStringExtra("idUserCurrent");



        mapping();
        setEvent();
    }

    private void mapping() {
        btnSentEmail = findViewById(R.id.btnSentOTP);
        tvEmail  = findViewById(R.id.emailSent);
        tvEmail.setText(email);
    }
    private void setEvent() {
        btnSentEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SentMail.SentMailAPI(getApplicationContext(), Utils.BASE_URL + "auth/verify/seller", email, new APICallBack() {
                        @Override
                        public void onSuccess(JSONObject response) throws JSONException {

                            CustomToast.makeText(getApplicationContext(), "Gửi mã OTP thành công!", CustomToast.LENGTH_SHORT, CustomToast.SUCCESS, true).show();


                            Intent i = new Intent(SentOTP.this, VerifyOTP.class);
                            i.putExtra("secret",response.getString("data"));
                            i.putExtra("idUserCurrent",idUserCurrent);
                            startActivity(i);

                        }

                        @Override
                        public void onError(VolleyError error) {

                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }



}
