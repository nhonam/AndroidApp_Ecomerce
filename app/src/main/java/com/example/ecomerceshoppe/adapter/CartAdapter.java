package com.example.ecomerceshoppe.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.ecomerceshoppe.API.CartAPI;
import com.example.ecomerceshoppe.Pragment.FragCart;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Cart;
import com.example.ecomerceshoppe.ultils.CustomToast;
import com.example.ecomerceshoppe.ultils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<Cart> {
    Context myContext;
    int myLayout;

    ArrayList<Cart> listCart ;
    public static Double  sumCart=0.0;

    ArrayList<Cart> listCartTmp = new ArrayList<Cart>();


    public CartAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Cart> listCart) {
        super(context, resource, listCart);
        this.myContext = context;
        this.myLayout = resource;
        this.listCart = listCart;
        this.listCartTmp.addAll(listCart);
    }

    private void clickDeleteItem(String idCartItem) {
        Dialog dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.alert_yes_no);

        Button btnYes = dialog.findViewById(R.id.YES);
        Button btnNo = dialog.findViewById(R.id.NO);


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call api xóa
                try {
                    CartAPI.DeleteCart(getContext().getApplicationContext(), Utils.BASE_URL + "cart/delete/", idCartItem, new APICallBack() {
                        @Override
                        public void onSuccess(JSONObject response) throws JSONException {
                            CustomToast.makeText(getContext(), "Xóa Sản Phẩm Thành Công", CustomToast.LENGTH_SHORT, CustomToast.SUCCESS, true).show();
                            notifyDataSetChanged();
                            dialog.dismiss();

                        }

                        @Override
                        public void onError(VolleyError error) {
                            CustomToast.makeText(getContext(), "Xóa Sản Phẩm Không Thành Công", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                dialog.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }



    private Boolean checkAll(){
        int j =0;
        int len = listCart.size();
        for (int i = 0; i < len; i++) {
            if(listCart.get(i).getSelected()){
                j++;
            }
        }

        if (j==len)
            return true;
        return false;
    }

    @Override
    public View getView(int i, @Nullable View view,@NonNull ViewGroup viewGroup) {


        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(myContext).inflate(myLayout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Cart cart = listCart.get(i);

        viewHolder.txtProductName.setText(cart.getNameProduct());
        viewHolder.edtQuantity.setText(String.valueOf(cart.getQuantity()));
        viewHolder.txtShopName.setText(cart.getShopName());
        viewHolder.txtPrice.setText( String.valueOf(cart.getPrice()));
        Glide.with(myContext).load(cart.getUrlImage()).into(viewHolder.ImgCart);



        if(cart.getSelected()) {
            viewHolder.checkBox.setChecked(true);
        }

        else{
            viewHolder.checkBox.setChecked(false);

        }


        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cart.getSelected())
                    sumCart-=cart.getPrice();
                clickDeleteItem(cart.getId());
                notifyDataSetChanged();


            }
        });


        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cart.setSelected(b);

                if(checkAll()){
                    FragCart.cbCheckAll.setChecked(true);
                    FragCart.tongTien.setText(String.valueOf(sumCart));
                }else {
                    FragCart.cbCheckAll.setChecked(false);
                    FragCart.tongTien.setText(String.valueOf(sumCart));
                }
                if(cart.getSelected()){
                    sumCart += cart.getPrice();
                } else if (!cart.getSelected()) {
                    sumCart -= cart.getPrice();
                }
                FragCart.tongTien.setText(String.valueOf(sumCart));
//                System.out.println(sumCart);

            }
        });
        return view;
    }

    private class ViewHolder {

         TextView txtShopName, txtProductName, txtPrice, Delete;
         EditText edtQuantity;
         public   CheckBox checkBox;
         ImageView ImgCart;




        public ViewHolder(View view) {
            txtShopName = view.findViewById(R.id.nameShopCart);
            checkBox = view.findViewById(R.id.checkCart);
            txtProductName = view.findViewById(R.id.nameProductCart);
            txtPrice = view.findViewById(R.id.priceCart);
            edtQuantity = view.findViewById(R.id.quantityCart);
            ImgCart = view.findViewById(R.id.imgCart);
            Delete = view.findViewById(R.id.Delete);

        }

    }

//    public  void searchSV(String query){
//        listCart.clear();
//        if(query==""){
//            listCart.addAll(listCartTmp);
//        }
//        for (Cart sv:listCartTmp) {
//            if(sv.getHoTen().contains(query))
//                data.add(sv);
//        }
//        notifyDataSetChanged();
//    }
    public  void XoaDuLieu(){
        listCartTmp.clear();
        listCartTmp.addAll(listCart);
        listCart.clear();
        for (Cart cart:listCartTmp ) {
            if(!cart.getSelected())
                listCart.add(cart);
        }
        notifyDataSetChanged();
        listCartTmp.clear();
        listCartTmp.addAll(listCart);
    }
    public void CheckAll(){

        for ( Cart sv:listCart      ) {
            sv.setSelected(true);

//            sumCart+=sv.getPrice();
        }
        notifyDataSetChanged();
    }

    public void UnCheckAll(){

        for ( Cart sv:listCart      ) {
            sv.setSelected(false);
        }
        notifyDataSetChanged();
    }
}
