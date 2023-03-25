package com.example.ecomerceshoppe.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.ecomerceshoppe.API.ProductAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.activity.ManagerProductDetail;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Product;
import com.example.ecomerceshoppe.ultils.CustomToast;
import com.example.ecomerceshoppe.ultils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductManagerAdapter extends ArrayAdapter<Product> {
    Context myContext;
    int myLayout;
    ArrayList<Product> data;
    ArrayList<Product> data_tmp = new ArrayList<>();

    public ProductManagerAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> listProduct) {
        super(context, resource, listProduct);
        this.myContext = context;
        this.myLayout = resource;
        this.data = listProduct;
//        this.data_tmp.addAll(data);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHorder viewHorder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(myContext).inflate(myLayout, null);
            viewHorder = new ViewHorder(convertView);
            convertView.setTag(viewHorder);
        } else {
            viewHorder = (ViewHorder) convertView.getTag();
        }
        Product pd = data.get(position);
        viewHorder.txtProductName.setText((pd.getNameProduct()));
        viewHorder.txtTag.setText((pd.getTag()));
        viewHorder.txtquantity.setText(String.valueOf(pd.getQuantity()));
        viewHorder.txtPrice.setText(String.valueOf(pd.getPrice()));
        viewHorder.txtCategory.setText(pd.getCategory());
        viewHorder.txtDescription.setText(pd.getDescription());
        Glide.with(myContext).load(pd.getUrlImage()).into(viewHorder.imageView);
//click vào icon sửa
        viewHorder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myContext, ManagerProductDetail.class);
                intent.putExtra("msg",pd);
                myContext.startActivity(intent);
            }
        });

        viewHorder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickDeleteItem(pd.getId());
            }
        });


        return convertView;

    }

    private class ViewHorder {

        TextView txtProductName,txtTag,txtquantity,txtPrice,txtCategory,txtDescription;
        ImageView imageView, ivEdit, ivDel;

        CheckBox checkBox;


        public ViewHorder(View view) {
            txtProductName = view.findViewById(R.id.name_ManagerProduct);
            txtTag = view.findViewById(R.id.tag_ManagerProduct);
            txtquantity = view.findViewById(R.id.quantity_ManagerProduct);
            txtPrice = view.findViewById(R.id.price_ManagerProduct);
            txtCategory = view.findViewById(R.id.category_ManagerProduct);
            txtDescription = view.findViewById(R.id.description_ManagerProduct);
            imageView = view.findViewById(R.id.image_ManagerProduct);
            ivEdit = view.findViewById(R.id.updateProduct);
            ivDel = view.findViewById(R.id.Delete_Product);
        }
    }
    public  void searchProduct(String query){
        data.clear();
        if(query==""){
            data.addAll(data_tmp);
        }
        for (Product pd:data_tmp) {
            if(pd.getNameProduct().contains(query))
                data.add(pd);
        }
        notifyDataSetChanged();
    }

    private void clickDeleteItem( String idProduct) {
        Dialog dialog = new Dialog(myContext);

        dialog.setContentView(R.layout.alert_delete);

        Button btnYes = dialog.findViewById(R.id.Yes);
        Button btnNo = dialog.findViewById(R.id.No);


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call api xóa

                try {
                    ProductAPI.APIDelProduct(myContext.getApplicationContext(), Utils.BASE_URL + "product/delete/", idProduct, new APICallBack() {
                        @Override
                        public void onSuccess(JSONObject response) throws JSONException {
                            CustomToast.makeText(myContext,"Xóa Sản Phẩm Thành Công",CustomToast.LENGTH_SHORT,CustomToast.SUCCESS,true).show();
                        }

                        @Override
                        public void onError(VolleyError error) {
                            CustomToast.makeText(myContext,"Xóa Sản Phẩm Không Thành Công",CustomToast.LENGTH_SHORT,CustomToast.ERROR,true).show();
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
    public  void XoaDuLieu(){
        data_tmp.clear();
        data_tmp.addAll(data);
        data.clear();
        for (Product sv:data_tmp ) {
            if(!sv.isSelect())
                data.add(sv);
        }
        notifyDataSetChanged();
        data_tmp.clear();
        data_tmp.addAll(data);
    }
    public void CheckAll(){
        for ( Product sv:data      ) {
            sv.setSelect(true);
        }
        notifyDataSetChanged();
    }

    public void UnCheckAll(){
        for ( Product sv:data      ) {
            sv.setSelect(false);
        }
        notifyDataSetChanged();
    }

}
