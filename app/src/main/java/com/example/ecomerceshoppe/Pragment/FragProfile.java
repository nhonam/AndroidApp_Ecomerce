package com.example.ecomerceshoppe.Pragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.ActionMenuView;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.UserAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.activity.CartUser;
import com.example.ecomerceshoppe.activity.Login;
import com.example.ecomerceshoppe.activity.ManagerShop;
import com.example.ecomerceshoppe.activity.VerifyOTP;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.User;
import com.example.ecomerceshoppe.ultils.Feature;
import com.example.ecomerceshoppe.ultils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class FragProfile  extends Fragment {

    JSONObject profileObj = new JSONObject();
    ImageView avtUser;
    TextView nameUser;


    TextView nameProfile, emailProfile,phoneProfile,
            adressProfile, birthdayProfile, idProfile;

    private TextView btnManagerProduct;
    private ImageView btnCart;

    Button btnLogout;
    User userCurrent=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String UserStr = getArguments().getString("user");
        String token = getArguments().getString("token");

         userCurrent = Feature.ConvertStringtoUser(UserStr);



        View view=inflater.inflate(R.layout.frag_profile, container, false);


        mapping(view);
        try {
            UserAPI.getProfileUserAPI(getContext(), Utils.BASE_URL + "auth/getUser/",token , new APICallBack() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {
//                    System.out.println("Profile :" + response.getJSONObject("data"));
                        profileObj = response.getJSONObject("data");
//                    System.out.println(profileObj);
//                    System.out.println( profileObj.get("admin"));
                    setEvent();

                }

                @Override
                public void onError(VolleyError error) {

                }
            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return view;
    }

    private void setEvent() {
        btnManagerProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userCurrent.isAdmin()) {
//                    Intent intent = new Intent(getContext(), ManagerProduct.class);
//
//                    startActivity(intent);

                    Intent intent = new Intent(getContext(), ManagerShop.class);
//
                    startActivity(intent);

                }else {
                    Intent intent = new Intent(getContext(), VerifyOTP.class);
                    startActivity(intent);
                }

            }
        });


        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CartUser.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences  = getContext().getSharedPreferences("matkhau", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);
            }
        });


    }

    private void mapping(View view) {
        nameProfile = view.findViewById(R.id.fullname);
        emailProfile = view.findViewById(R.id.emailProfile);
        phoneProfile = view.findViewById(R.id.phoneProfile);
        adressProfile = view.findViewById(R.id.adressProfile);
        birthdayProfile = view.findViewById(R.id.birthdayProfile);
        idProfile = view.findViewById(R.id.id_user);
        avtUser = view.findViewById(R.id.avt_User);
        nameUser = view.findViewById(R.id.name_User);

        btnCart = (ImageView) view.findViewById(R.id.cart_profile);
//        navi = view.findViewById(R.id.bottom_navigation_pro);
        btnManagerProduct = view.findViewById(R.id.btnManagerProduct);
        btnLogout = view.findViewById(R.id.logout);



    }
}
