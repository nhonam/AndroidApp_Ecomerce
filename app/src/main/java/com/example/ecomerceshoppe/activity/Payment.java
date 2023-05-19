package com.example.ecomerceshoppe.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.CartAPI;
import com.example.ecomerceshoppe.Pragment.FragCart;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.adapter.PayMentAdapter;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Cart;
import com.example.ecomerceshoppe.ultils.CustomToast;
import com.example.ecomerceshoppe.ultils.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {

    BottomSheetDialog dialogPayment;
    TextView btnMethodPayment , btnMuaHang, soNha, thanhPho; //soNha+thanhPho = address

    ImageView btnbackPayment;



    public static Boolean  isActive = false;
    ArrayList<Cart> listProductPayment;
    ListView lvPayment;
    String idUser="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);


        mapping();
        dialogPayment = new BottomSheetDialog(this);
        createDialog();

        //getList những sản phẩm được mua form FragCart
        listProductPayment = getIntent().getExtras().getParcelableArrayList("listOrder");
        //lấy iduser được gửi từ FragCart
//        Intent intent = getIntent();
        idUser = getIntent().getStringExtra("idUser");
        ListCartDaMua();
//        for (int i = 0; i < listProductPayment.size(); i++) {
//
//            System.out.println(listProductPayment.get(i).getId()+"-+-");
//        }





    }

    private void ListCartDaMua(){
        try {
            CartAPI.getCartByUser(Payment.this, Utils.BASE_URL + "cart/get/cartuser/", idUser, new APICallBack() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {
//                    System.out.println("api get cart by user: "+response);
                    JSONArray listcartJSON = response.getJSONArray("data");

                    JSONArray formData = new JSONArray();
                    for (int j = 0; j < listProductPayment.size(); j++) {
                        System.out.println(listProductPayment.get(j).getId());
                        for (int i = 0; i < listcartJSON.length(); i++) {
                            JSONObject cartTmpObj = (JSONObject) listcartJSON.get(i);
                            //                        System.out.println(cartTmpObj.get("product"));
                            JSONObject productObj = (JSONObject) cartTmpObj.getJSONObject("product");
//                            System.out.println(listProductPayment.get(j).getId() +"======");
//                            System.out.println(cartTmpObj.getString("_id"));
                            if (listProductPayment.get(j).getId().equalsIgnoreCase(cartTmpObj.getString("_id"))) {
//                                String createdAt = cartTmpObj.getString("createdAt");


                                JSONObject data = new JSONObject();
                                data.put("index",cartTmpObj.getString("createdAt"));
                                data.put("product",productObj.getString("_id"));
                                data.put("seller",productObj.getJSONObject("seller"));
                                data.put("quantity",cartTmpObj.getInt("quantity"));

                                formData.put(data);

                            }


                        }
                    }

                    setEvent(formData);

//                    System.out.println(formData+"-----");
                    //call api buyt product
                }

                @Override
                public void onError(VolleyError error) {

                }
            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void setEvent(JSONArray arrProduct) {


        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call api mua hàng
                Double tongTien = Double.parseDouble((String) FragCart.tongTien.getText());
                String addressShip = soNha.getText()+" "+thanhPho.getText();
                try {
                    CartAPI.APIBuyProduct(Payment.this, Utils.BASE_URL + "customer/purchase/buy", idUser,addressShip ,arrProduct, tongTien, new APICallBack() {
                        @Override
                        public void onSuccess(JSONObject response) throws JSONException {
                            CustomToast.makeText(Payment.this, "Mua hàng thành công, đang chờ Shop xác nhận  !", CustomToast.LENGTH_LONG, CustomToast.SUCCESS, true).show();

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


        btnMethodPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPayment.show();
            }
        });

        PayMentAdapter payMentAdapter = new PayMentAdapter(getApplicationContext(),R.layout.item_payment, listProductPayment);
        lvPayment.setAdapter(payMentAdapter);

        btnbackPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isActive = true;
                startActivity(new Intent(getApplicationContext(), Main.class));
            }
        });
    }

    private  void mapping(){
        btnMethodPayment = findViewById(R.id.methodPayment);
        btnbackPayment = findViewById(R.id.ic_backPayment);
        lvPayment = findViewById(R.id.lvPayment);
        btnMuaHang = findViewById(R.id.btnPayment);
        soNha = findViewById(R.id.soNha);
        thanhPho = findViewById(R.id.thanhPho);
//        tongTien = findViewById(R.id.tienhang);
//        tongThanhToan = findViewById(R.id.tong);

    }

    private void createDialog() {
        View view = getLayoutInflater().inflate(R.layout.method_payment, null, false);
        TextView btnSubmit = view.findViewById(R.id.btnPayment);
//        EditText edtQuanti = findViewById();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //call api
                dialogPayment.dismiss();
            }
        });
        dialogPayment.setContentView(view);

    }


}
