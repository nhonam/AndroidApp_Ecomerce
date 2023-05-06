package com.example.ecomerceshoppe.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecomerceshoppe.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;

public class Payment extends AppCompatActivity {

    BottomSheetDialog dialogPayment;
    TextView btnMethodPayment;

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);


        mapping();
        dialogPayment = new BottomSheetDialog(this);
        createDialog();
        setEvent();


    }

    private void setEvent() {
        btnMethodPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPayment.show();
            }
        });
    }

    private  void mapping(){
        btnMethodPayment = findViewById(R.id.methodPayment);
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
