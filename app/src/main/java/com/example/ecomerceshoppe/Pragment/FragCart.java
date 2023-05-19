package com.example.ecomerceshoppe.Pragment;
// cuối kỳ

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
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

    private ArrayList<Cart> listCart;
    String idUser = ""; //ttesstt  63af70c03f562b7531d4c5db
    TextView btnBuyCart;
    public static TextView tongTien;
    CartAdapter cartAdapter;

    ListView listViewCart;

    ImageView ic_homeCart;
    public static CheckBox cbCheckAll;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_cart_user, container, false);
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
                    for (int i = 0; i < listcartJSON.length(); i++) {
                        JSONObject cartTmpObj = (JSONObject) listcartJSON.get(i);
//                        System.out.println(cartTmpObj.get("product"));
                        JSONObject productObj = (JSONObject) cartTmpObj.get("product");

                        JSONObject imgObj = (JSONObject) productObj.get("img");
                        JSONObject sellerObj = (JSONObject) productObj.get("seller");
                        Cart cartTemp = new Cart();
                        cartTemp.setId(cartTmpObj.getString("_id"));
//                        System.out.println(cartTmpObj.getString("_id")+"-+-");
                        cartTemp.setShopName(sellerObj.getString("fullname"));
//                        cartTemp.setS(sellerObj.getString("_id"));
                        cartTemp.setNameProduct(productObj.getString("name_product"));
                        cartTemp.setPrice(productObj.getInt("price"));
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


    private void mapping(View view) {
        listViewCart = view.findViewById(R.id.listViewCart);
        ic_homeCart = view.findViewById(R.id.homeCart);
        btnBuyCart = view.findViewById(R.id.btnBuyCart);
        cbCheckAll = view.findViewById(R.id.selectAll);
        tongTien = view.findViewById(R.id.tongtien);


    }


    private void setEvent() {
        cartAdapter = new CartAdapter(getContext(), R.layout.row_cart, listCart);
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

                Intent intent = new Intent(getContext(), Payment.class);
                intent.putExtra("listOrder", buyCarts());
                intent.putExtra("idUser",idUser);
                startActivity(intent);
            }
        });


        cbCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartAdapter.notifyDataSetChanged();
                if (cbCheckAll.isChecked()) {
                    cartAdapter.CheckAll();
                } else if (!cbCheckAll.isChecked()) {
                    cartAdapter.UnCheckAll();
                }

            }
        });

        listViewCart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                cartAdapter = new CartAdapter(getContext(), R.layout.row_cart, listCart);
//                listViewCart.setAdapter(cartAdapter);
                cartAdapter.notifyDataSetChanged();

            }
        });


    }

    //lấy những sản phẩm được tích chọn là mua
    private ArrayList<Cart> buyCarts() {
        ArrayList<Cart> BuyCartList = new ArrayList<>();
        for (int i = 0; i < listCart.size(); i++) {
            if (listCart.get(i).getSelected()) {
                BuyCartList.add(listCart.get(i));
//                System.out.println(BuyCartList.get(i).getId()+"|||000000000000000000");

            }
        }
        return BuyCartList;
    }

}
