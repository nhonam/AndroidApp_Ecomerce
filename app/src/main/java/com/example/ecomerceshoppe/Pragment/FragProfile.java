package com.example.ecomerceshoppe.Pragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.UserAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.activity.CartUser;
import com.example.ecomerceshoppe.activity.ManagerProduct;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.User;
import com.example.ecomerceshoppe.ultils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragProfile  extends Fragment {

    JSONObject profileObj = new JSONObject();

    ImageView avtUser;

    TextView nameUser, idUser;

    TextView nameProfile, emailProfile,phoneProfile,
            adressProfile, birthdayProfile, idProfile;

    private User user;

    private BottomNavigationView navi;
    private TextView btnManagerProduct;
    private ImageView btnCart;

    List<User> userList = new ArrayList<User>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.frag_profile, container, false);
        mapping(view);
        try {
            UserAPI.getProfileUserAPI(getContext(), Utils.BASE_URL + "auth/getUser/","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJUcmFuIE5oYXQgRHV5IiwiZGF0YSI6IjY0MTgxYmFiY2QwMWViNmFjYTA5ODdkYiIsImlhdCI6MTY3OTM4MTAxNDk3OSwiZXhwIjoxNjc5NDY3NDE0OTc5fQ.aDJFIiwLm-3-mvZRojdv9ebGJ_ZLxTcA4dGsNTHaXrI" , new APICallBack() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {
//                    System.out.println("Profile :" + response.getJSONObject("data"));
                    profileObj = response.getJSONObject("data");
                    System.out.println(profileObj);
                    System.out.println( profileObj.get("admin"));
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
                Intent intent = new Intent(getContext(), ManagerProduct.class);
                startActivity(intent);
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CartUser.class);
                startActivity(intent);
            }
        });
    }

    private void mapping(View view) {
        nameProfile = view.findViewById(R.id.nameProfile);
        emailProfile = view.findViewById(R.id.emailProfile);
        phoneProfile = view.findViewById(R.id.phoneProfile);
        adressProfile = view.findViewById(R.id.adressProfile);
        birthdayProfile = view.findViewById(R.id.birthdayProfile);
        idProfile = view.findViewById(R.id.idProfile);
        avtUser = view.findViewById(R.id.avt_User);
        nameUser = view.findViewById(R.id.name_User);
        idUser = view.findViewById(R.id.id_user);

        btnCart = (ImageView) view.findViewById(R.id.cart_profile);
        navi = view.findViewById(R.id.bottom_navigation_pro);
        btnManagerProduct = view.findViewById(R.id.btnManagerProduct);

    }
}
