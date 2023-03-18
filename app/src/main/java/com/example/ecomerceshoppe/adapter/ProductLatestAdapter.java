package com.example.ecomerceshoppe.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ecomerceshoppe.R;
//import com.example.ecomerceshoppe.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductLatestAdapter extends BaseAdapter {

    Context context;
    private List<String> nameProductList;

    private List<Double> priceProductList;

    private List<String> urlProductList;
    @Override
    public int getCount() {
        return nameProductList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }


    public ProductLatestAdapter(Context context, List<String> nameProductList, List<Double> priceProductList, List<String> urlProductList) {
        this.context = context;
        this.nameProductList = nameProductList;
        this.priceProductList = priceProductList;
        this.urlProductList = urlProductList;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view= layoutInflater.inflate(R.layout.row_leastproduct, null);
        TextView txtName =  view.findViewById(R.id.txtProductName);
        TextView txtPrice =  view.findViewById(R.id.txtPrice);
        ImageView imageView = view.findViewById(R.id.imgProductLatest);

        txtName.setText(nameProductList.get(i));
        txtPrice.setText(String.valueOf(priceProductList.get(i)));
        Glide.with(context).load(urlProductList.get(i)).into(imageView);
        return view;
    }
}
