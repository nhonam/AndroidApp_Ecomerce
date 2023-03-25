package com.example.ecomerceshoppe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.model.Cart;
import com.example.ecomerceshoppe.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends ArrayAdapter<Cart> {
    Context myContext;
    int myLayout;

    ArrayList<Cart> listCart ;

    ArrayList<Cart> listCartTmp = new ArrayList<Cart>();


    public CartAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Cart> listCart) {
        super(context, resource, listCart);
        this.myContext = context;
        this.myLayout = resource;
        this.listCart = listCart;
//        this.data_tmp.addAll(data);
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

        return view;
    }

    private class ViewHolder {

         TextView txtShopName, txtProductName, txtPrice;
         EditText edtQuantity;

         ImageView ImgCart;


        public ViewHolder(View view) {
            txtShopName = view.findViewById(R.id.nameShopCart);
            txtProductName = view.findViewById(R.id.nameProductCart);
            txtPrice = view.findViewById(R.id.priceCart);
            edtQuantity = view.findViewById(R.id.quantityCart);
            ImgCart = view.findViewById(R.id.imgCart);

        }

    }
}
