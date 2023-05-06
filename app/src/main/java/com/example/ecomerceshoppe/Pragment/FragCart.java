package com.example.ecomerceshoppe.Pragment;
// cuối kỳ
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.CartAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.activity.Main;
import com.example.ecomerceshoppe.activity.Payment;
import com.example.ecomerceshoppe.adapter.CartAdapter;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Cart;
import com.example.ecomerceshoppe.ultils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragCart extends Fragment {
    private Cart cartUser;
    private ArrayList<Cart> listCart;
    String idUser ="63af70c03f562b7531d4c5db"; //ttesstt
    TextView btnBuyCart ;


    ListView listViewCart;

    ImageView ic_homeCart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.activity_cart_user, container, false);
        //lấy dữ liệu đc gửi từ trang Main
        idUser = getArguments().getString("idUserCurent");
        mapping(view);

        try {
            CartAPI.getCartByUser(getContext(), Utils.BASE_URL + "cart/get/cartuser/", idUser, new APICallBack() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {
//                    System.out.println("api get cart by user: "+response);
                    JSONArray listcartJSON = response.getJSONArray("data");
                    listCart = new ArrayList<>();
                    for (int i = 0; i < listcartJSON.length() ; i++) {
                        JSONObject cartTmpObj = (JSONObject) listcartJSON.get(i);
                        System.out.println(cartTmpObj.get("product"));
                        JSONObject productObj = (JSONObject) cartTmpObj.get("product");

                        JSONObject imgObj =  (JSONObject) productObj.get("img");
                        JSONObject sellerObj = (JSONObject) productObj.get("seller");
                        Cart cartTemp = new Cart();
                        cartTemp.setId(cartTmpObj.getString("_id"));
                        cartTemp.setShopName(sellerObj.getString("fullname"));
                        cartTemp.setNameProduct(productObj.getString("name_product"));
                        cartTemp.setPrice(productObj.getDouble("price"));
                        cartTemp.setQuantity(cartTmpObj.getInt("quantity"));
                        cartTemp.setUrlImage(imgObj.getString("url"));

                        listCart.add(cartTemp);

                    }
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



    private void mapping(View view){
        listViewCart = view.findViewById(R.id.listViewCart);
        ic_homeCart = view.findViewById(R.id.homeCart);
        btnBuyCart = view.findViewById(R.id.btnBuyCart);



    }
    private  void setEvent(){

        CartAdapter cartAdapter = new CartAdapter(getContext(), R.layout.row_cart, listCart);

        listViewCart.setAdapter(cartAdapter);


        ic_homeCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Main.class);
                startActivity(intent);
            }
        });

        btnBuyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getContext(), Payment.class));
            }
        });


    }

}
