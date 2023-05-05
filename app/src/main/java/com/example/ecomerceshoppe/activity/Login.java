package com.example.ecomerceshoppe.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.UserAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.User;
import com.example.ecomerceshoppe.ultils.CustomToast;
import com.example.ecomerceshoppe.ultils.Feature;
import com.example.ecomerceshoppe.ultils.ObjectWrapperForBinder;
import com.example.ecomerceshoppe.ultils.Utils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    AppCompatButton btnLogin;
    private String username = "";
    private String password = "";
    TextView txtregisterNow;
    EditText edtUserName, edtpassWord;
    SharedPreferences sharedPreferences;
    JSONObject dataUserCurrent = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        mapping();
        sharedPreferences = getSharedPreferences("matkhau", MODE_PRIVATE);
        ReadPassWord();
        try {
            APILoginDefault();
        } catch (JSONException e) {
            System.out.println("Error catch LoginAPI in class (Login.java)" + e.getMessage());
            throw new RuntimeException(e);
        }
        setEvent();
    }

    private void SaveInfoToLocal(String idUser, String token, User user) {
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", gson.toJson(user));
        editor.putString("token", token);
        editor.putString("idUserCurent", idUser);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();

    }

    private void APILoginDefault() throws JSONException {
        UserAPI.LoginAPI(getApplicationContext(), Utils.BASE_URL + "auth/login", username, password, new APICallBack() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                //tạo 1 user dùng singleton
//                    Singleton userCurrent = Singleton.getInstance(new User());
                dataUserCurrent = response.getJSONObject("data");
                JSONObject userCurrent = dataUserCurrent.getJSONObject("admin");

                if (userCurrent.getString("email").toLowerCase().trim().equalsIgnoreCase("") || userCurrent.getString("fullname").trim().equalsIgnoreCase("")){
                    final Bundle bundle = new Bundle();
                    bundle.putBinder("userCurrent", new ObjectWrapperForBinder(userCurrent));
                    Intent intent = new Intent(getApplicationContext(), Update_Profile.class).putExtras(bundle);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(Login.this, Main.class);
                    startActivity(intent);
                }

//

            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    private Boolean ReadPassWord() {

//        System.out.println("doc mat khau "+sharedPreferences.getString("username","")+sharedPreferences.getString("password",""));
        username = sharedPreferences.getString("username", "");
        password = sharedPreferences.getString("password", "");
        if (password.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    private void setEvent() {

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                username = String.valueOf(edtUserName.getText());
                password = String.valueOf(edtpassWord.getText());

                try {
                    APILogin();
                } catch (JSONException e) {
                    System.out.println("Error catch LoginAPI in class (Login.java)" + e.getMessage());
                    throw new RuntimeException(e);
                }

            }
        });

        txtregisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

    }

    public void APILogin() throws JSONException {
        UserAPI.LoginAPI(getApplicationContext(), Utils.BASE_URL + "auth/login", username, password, new APICallBack() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                //tạo 1 user dùng singleton
//                    Singleton userCurrent = Singleton.getInstance(new User());
                dataUserCurrent = response.getJSONObject("data");

                String token = dataUserCurrent.getString("token");
                JSONObject userCurrent = dataUserCurrent.getJSONObject("admin");
                String idUserCurrent = userCurrent.getString("_id");
//                System.out.println(idUserCurrent);
//                System.out.println("token" + token);
//                System.out.println("userCurrent" + userCurrent);
                User userDTO = new User();
                userDTO.setId(userCurrent.getString("_id"));
                userDTO.setFullName(userCurrent.getString("fullname"));
                userDTO.setEmail(userCurrent.getString("email"));
                userDTO.setAddress(userCurrent.getString("address"));
                userDTO.setIdentity_card(userCurrent.getString("identity_card"));
                userDTO.setPhone(userCurrent.getString("phone"));
                JSONObject tmp = (JSONObject) userCurrent.get("avt");
                userDTO.setUrlAvatar(tmp.getString("url"));

                userDTO.setAdmin(Boolean.parseBoolean(userCurrent.getString("isAdmin")));
                System.out.println("nanannanana"+userCurrent.getString("birthday"));
//                userDTO.setBirthday( userCurrent.getString("birthday"))));
                System.out.println("hehehhe"+userDTO.getBirthday());
                SaveInfoToLocal(userCurrent.getString("_id"), token, userDTO);
                Intent intent = new Intent(Login.this, Main.class);
                startActivity(intent);
            }

            @Override
            public void onError(VolleyError error) {
                edtUserName.setText("");
                edtpassWord.setText("");
                edtUserName.requestFocus();
                System.err.println("Error in onError APIlogin in (login.java)" + error.getMessage());
                CustomToast.makeText(Login.this, "Tên đăng nhập hoặc mật khẩu không đúng !", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();

            }
        });
    }

    private void mapping() {
        btnLogin = findViewById(R.id.btnLogin);
        txtregisterNow = findViewById(R.id.registerNow);
        edtUserName = (EditText) findViewById(R.id.login_username);
        edtpassWord = (EditText) findViewById(R.id.login_password);

    }


}