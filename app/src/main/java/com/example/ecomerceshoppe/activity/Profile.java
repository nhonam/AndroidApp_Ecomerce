//package com.example.ecomerceshoppe.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.VolleyError;
//import com.example.ecomerceshoppe.API.ProductAPI;
//import com.example.ecomerceshoppe.API.UserAPI;
//import com.example.ecomerceshoppe.R;
//import com.example.ecomerceshoppe.interfaces.APICallBack;
//import com.example.ecomerceshoppe.model.User;
//import com.example.ecomerceshoppe.ultils.Utils;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Profile extends AppCompatActivity {
//
//    JSONObject profileObj = new JSONObject();
//
//    ImageView avtUser;
//
//    TextView nameUser, idUser;
//
//    TextView nameProfile, emailProfile,phoneProfile,
//    adressProfile, birthdayProfile, idProfile;
//
//    private User user;
//
//    private BottomNavigationView navi;
//    private TextView btnManagerProduct;
//    private ImageView btnCart;
//
//    List<User> userList = new ArrayList<User>();
//
//    private void mapping() {
//        nameProfile = findViewById(R.id.nameProfile);
//        emailProfile = findViewById(R.id.emailProfile);
//        phoneProfile = findViewById(R.id.phoneProfile);
//        adressProfile = findViewById(R.id.adressProfile);
//        birthdayProfile = findViewById(R.id.birthdayProfile);
//        idProfile = findViewById(R.id.idProfile);
//        avtUser = findViewById(R.id.avt_User);
//        nameUser = findViewById(R.id.name_User);
//        idUser = findViewById(R.id.id_user);
//
//        btnCart = (ImageView) findViewById(R.id.cart_profile);
//        navi = findViewById(R.id.bottom_navigation_pro);
//        btnManagerProduct = findViewById(R.id.btnManagerProduct);
//
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
//        setContentView(R.layout.activity_profile);
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//        mapping();
//
//        try {
//            UserAPI.getProfileUserAPI(this, Utils.BASE_URL + "auth/getUser/","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJUcmFuIE5oYXQgRHV5IiwiZGF0YSI6IjY0MTgxYmFiY2QwMWViNmFjYTA5ODdkYiIsImlhdCI6MTY3OTM4MTAxNDk3OSwiZXhwIjoxNjc5NDY3NDE0OTc5fQ.aDJFIiwLm-3-mvZRojdv9ebGJ_ZLxTcA4dGsNTHaXrI" , new APICallBack() {
//                @Override
//                public void onSuccess(JSONObject response) throws JSONException {
////                    System.out.println("Profile :" + response.getJSONObject("data"));
//                    profileObj = response.getJSONObject("data");
//                    System.out.println(profileObj);
//                    System.out.println( profileObj.get("admin"));
//
//                }
//
//                @Override
//                public void onError(VolleyError error) {
//
//                }
//            });
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//
//        ChangActivity();
//
//        setEvent();
//
//    }
//
//    private void setEvent() {
//        btnManagerProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Profile.this, ManagerProduct.class);
//                startActivity(intent);
//            }
//        });
//
//        btnCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Profile.this, CartUser.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//
//
//
//    private void ChangActivity() {
//
//
//        navi.setSelectedItemId(R.id.ic_profile);
//
//
//        navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.ic_home:
//                        startActivity(new Intent(getApplicationContext(), Home.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.ic_notification:
//                        startActivity(new Intent(getApplicationContext(), ThongBao.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.ic_profile:
//                        return true;
//                }
//                return true;
//            }
//        });
//
//
//    }
//
//}
