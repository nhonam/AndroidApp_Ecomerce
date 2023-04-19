package com.example.ecomerceshoppe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
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


public class VerifyOTP extends AppCompatActivity {

    AppCompatButton btnVertify;

    TextView resentOTP;
    EditText edtOne, edtTwo, edtThree, edtFour, edtFive, edtSix;
    String secret="";
    String idUserCurrent="";

    String OTP="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.layout_otp);
        //nhận thoog tin được gủi tù SentOTP
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            secret = extras.getString("secret");
            idUserCurrent = extras.getString("idUserCurrent");

        }




        mapping();
        setupOTPInputs();
        setEvent();
    }

    private void setupOTPInputs() {
        edtOne.requestFocus();
        edtOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    edtTwo.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    edtThree.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    edtFour.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    edtFive.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    edtSix.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//        OTP = edtOne.getText().toString().trim()+edtTwo.getText().toString().trim()+
//                edtThree.getText().toString().trim()+edtFour.getText().toString().trim()+
//                edtFive.getText().toString().trim()+edtSix.getText().toString().trim();
    }


    private void setEvent() {
        btnVertify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OTP = edtOne.getText().toString().trim()+edtTwo.getText().toString().trim()+
                        edtThree.getText().toString().trim()+edtFour.getText().toString().trim()+
                        edtFive.getText().toString().trim()+edtSix.getText().toString().trim();
                try {
                    SentMail.VertityOTPAPI(getApplicationContext(), idUserCurrent, Utils.BASE_URL + "auth/verify/seller/", OTP, secret, new APICallBack() {
                        @Override
                        public void onSuccess(JSONObject response) throws JSONException {
                            System.out.println(response);
                            CustomToast.makeText(VerifyOTP.this, "Đăng ký SHOP thành công, bây giờ bạn có thể đăng sản phẩm và kinh doanh nào !!!", CustomToast.LENGTH_LONG, CustomToast.ERROR, true).show();
                            startActivity(new Intent(VerifyOTP.this,ManagerShop.class));
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
    private void mapping() {
        edtOne = findViewById(R.id.one);
        edtOne.setInputType(InputType.TYPE_CLASS_NUMBER);

        edtTwo = findViewById(R.id.two);
        edtTwo.setInputType(InputType.TYPE_CLASS_NUMBER);

        edtThree = findViewById(R.id.three);
        edtThree.setInputType(InputType.TYPE_CLASS_NUMBER);

        edtFour = findViewById(R.id.four);
        edtFour.setInputType(InputType.TYPE_CLASS_NUMBER);

        edtFive = findViewById(R.id.five);
        edtFive.setInputType(InputType.TYPE_CLASS_NUMBER);


        edtSix= findViewById(R.id.six);
        edtSix.setInputType(InputType.TYPE_CLASS_NUMBER);


        btnVertify  = findViewById(R.id.btnVertifyOTP);
        resentOTP  = findViewById(R.id.resentOTP);
    }


}
