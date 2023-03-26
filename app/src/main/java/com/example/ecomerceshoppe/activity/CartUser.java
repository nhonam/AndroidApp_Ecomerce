package com.example.ecomerceshoppe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.CartAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.adapter.CartAdapter;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Cart;
import com.example.ecomerceshoppe.ultils.Utils;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CartUser extends AppCompatActivity {

    private Cart cartUser;
    private ArrayList<Cart> listCart = new ArrayList<>();
    String idUser ="63af70c03f562b7531d4c5db";
    TextView txtVIew ;

    ListView listViewCart;

    ImageView ic_homeCart;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cart_user);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        mapping();

        try {
            CartAPI.getCartByUser(this, Utils.BASE_URL + "cart/get/cart/user/", idUser, new APICallBack() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {
//                    System.out.println("api get cart by user: "+response);
                    JSONArray listcartJSON = response.getJSONArray("data");

                    for (int i = 0; i < listcartJSON.length() ; i++) {
                        JSONObject cartTmp = (JSONObject) listcartJSON.get(i);
                        System.out.println(cartTmp.get("_id"));
                        JSONObject tmp = (JSONObject) ((JSONObject) (cartTmp.get("product"))).get("seller");
                        System.out.println("Shop name"+ tmp.getString("fullname") );
                        JSONObject tmp2 = (JSONObject) cartTmp.get("product");
                        System.out.println( ((JSONObject) cartTmp.get("product")).get("name_product"));
                        System.out.println();
                        JSONObject price = ((JSONObject) cartTmp.get("product"));
                        Double priceDB = price.getDouble("price");
                        System.out.println(cartTmp.get("quantity"));
                        JSONObject tmp1 = (JSONObject) ((JSONObject) (cartTmp.get("product"))).get("img");
                        System.out.println(tmp1.get("url"));

                        listCart.add(new Cart((String) cartTmp.get("_id"),String.valueOf( tmp.getString("fullname")),String.valueOf(tmp2.getString("name_product")),priceDB ,(int) cartTmp.get("quantity"), (String) tmp1.get("url")));

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




    }

    private void mapping(){
        listViewCart = findViewById(R.id.listViewCart);
        ic_homeCart = findViewById(R.id.homeCart);


    }
    private  void setEvent(){

        CartAdapter cartAdapter = new CartAdapter(CartUser.this, R.layout.row_cart, listCart);

        listViewCart.setAdapter(cartAdapter);


        ic_homeCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartUser.this, Main.class);
                startActivity(intent);
            }
        });


    }

}
