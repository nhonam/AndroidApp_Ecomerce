package com.example.ecomerceshoppe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecomerceshoppe.R;


public class CategoryAdapter extends BaseAdapter  {

    Context context;
    private String[] logoNameList;
    private int[] imgPhotoList;

    public  CategoryAdapter(Context context, String[] logoNameList, int[] imgPhotoList) {
        this.context = context;
        this.logoNameList = logoNameList;
        this.imgPhotoList = imgPhotoList;
    }

    @Override
    public int getCount() {
        if(logoNameList.length==0)
            return 0;
        return logoNameList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view= layoutInflater.inflate(R.layout.row_category_product, null);
        TextView  txtName =  view.findViewById(R.id.txtCategoryProduct);
        ImageView imageView = view.findViewById(R.id.imgCategoryProduct);

        txtName.setText(logoNameList[i]);
        imageView.setImageResource(imgPhotoList[i]);
        return view;
    }
}
