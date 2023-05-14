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

import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.adapter.PayMentAdapter;
import com.example.ecomerceshoppe.model.Cart;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {

    BottomSheetDialog dialogPayment;
    TextView btnMethodPayment, tongTien, tongThanhToan;

    ImageView btnbackPayment;


    public static Boolean  isActive = false;
    ArrayList<Cart> listProductPayment;
    ListView lvPayment;

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
        for (int i = 0; i < listProductPayment.size(); i++) {
            System.out.println(listProductPayment.get(i).getNameProduct()+"nè");
        }
        setEvent();


    }

    private void setEvent() {


        btnMethodPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPayment.show();
            }
        });

        PayMentAdapter payMentAdapter = new PayMentAdapter(getApplicationContext(),R.layout.item_payment, listProductPayment);
        lvPayment.setAdapter(payMentAdapter);

//        btnbackPayment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                isActive = true;
//                startActivity(new Intent(getApplicationContext(), Main.class));
//            }
//        });
    }

    private  void mapping(){
        btnMethodPayment = findViewById(R.id.methodPayment);
        btnbackPayment = findViewById(R.id.ic_backPayment);
        lvPayment = findViewById(R.id.lvPayment);
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
