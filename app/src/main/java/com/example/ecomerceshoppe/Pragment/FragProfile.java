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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.ecomerceshoppe.API.UserAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.activity.Login;
import com.example.ecomerceshoppe.activity.ManagerShop;
import com.example.ecomerceshoppe.activity.VerifyOTP;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.User;
import com.example.ecomerceshoppe.ultils.Feature;
import com.example.ecomerceshoppe.ultils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class FragProfile  extends Fragment {

    JSONObject profileObj = new JSONObject();
    ImageView avtUser;
    TextView nameUser;


    TextView nameProfile, emailProfile,phoneProfile,
            adressProfile, birthdayProfile, idProfile;

    private TextView btnManagerProduct;

    ImageView btnSetting;

    LinearLayout btnLogout;
    User userCurrent=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String UserStr = getArguments().getString("user");
        String token = getArguments().getString("token");

         userCurrent = Feature.ConvertStringtoUser(UserStr);

        View view=inflater.inflate(R.layout.profile, container, false);

        mapping(view);
        setDataProfile();
        try {
            APIgetInfoUser(token);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return view;
    }

    private void APIgetInfoUser(String token) throws JSONException {
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


        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
//        nameProfile = view.findViewById(R.id.fullname);
        emailProfile = view.findViewById(R.id.emailProfile);
        phoneProfile = view.findViewById(R.id.phoneProfile);
        adressProfile = view.findViewById(R.id.adressProfile);
        birthdayProfile = view.findViewById(R.id.birthdayProfile);
        idProfile = view.findViewById(R.id.id_user);
        avtUser = view.findViewById(R.id.avt_User);
        nameUser = view.findViewById(R.id.name_User);
//        navi = view.findViewById(R.id.bottom_navigation_pro);
        btnManagerProduct = view.findViewById(R.id.btnManagerProduct);
        btnLogout = view.findViewById(R.id.logout);
        btnSetting = view.findViewById(R.id.setting);

    }

    public void setDataProfile() {

        Glide.with(getContext()).load(userCurrent.getUrlAvatar()).into(avtUser);
        nameUser.setText(userCurrent.getFullName());
        idProfile.setText(userCurrent.getId());
        emailProfile.setText(userCurrent.getEmail());
//        nameProfile.setText(userCurrent.getFullName());
        phoneProfile.setText(userCurrent.getPhone());
        adressProfile.setText(userCurrent.getAddress());
//        birthdayProfile.setText((CharSequence) userCurrent.getBirthday());

    }
}
