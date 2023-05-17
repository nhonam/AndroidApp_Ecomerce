package com.example.ecomerceshoppe.Pragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.ProductAPI;
import com.example.ecomerceshoppe.API.SellerAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.activity.Login;
import com.example.ecomerceshoppe.activity.ManagerShop;
import com.example.ecomerceshoppe.adapter.SellAdapter;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Order;
import com.example.ecomerceshoppe.ultils.CustomToast;
import com.example.ecomerceshoppe.ultils.ExportPDF;
import com.example.ecomerceshoppe.ultils.ObjectWrapperForBinder;
import com.example.ecomerceshoppe.ultils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragSell extends Fragment {

    private ArrayList<Order> listSell; //list hàng chưa được shop xác nhận
    String idShop = "";//"63af70c03f562b7531d4c5db"; //ttesstt


    ListView listViewSell;
    SellAdapter sellAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_manager_sell, container, false);
        //lấy dữ liệu đc gửi từ trang ManagerShop
        idShop = getArguments().getString("idUserCurent");
        mapping(view);


        try {
            getOrderAPI();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return view;
    }

    private void getOrderAPI() throws JSONException {
        SellerAPI.getOrderByShop(getContext(), idShop, new APICallBack() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                JSONArray dataArr = (JSONArray) response.get("data");
                JSONObject data; //item data in dataArr
                Order orderTmp = new Order();
                listSell = new ArrayList<>();
                for (int i = 0; i < dataArr.length(); i++) {
                    data = (JSONObject) dataArr.get(i);
                    //nếu đang chờ đc xác nhận của shop thì add vào list
                    if( data.getString("status").equalsIgnoreCase("pendding")){
                        orderTmp.setId(data.getString("_id"));
                        orderTmp.setCustomer(data.getString("customer"));
                        orderTmp.setTotal_Price(data.getDouble("total_amount"));
//                    orderTmp.setCreatAtOrder(data.getString("createdAt"));
                        orderTmp.setStatus(data.getString("status"));

                        listSell.add(orderTmp);
                    }


                }
                setEvent();
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }


    private void mapping(View view) {
        listViewSell = view.findViewById(R.id.listviewMsell);

    }

    private void setEvent() {
        sellAdapter = new SellAdapter(getContext(), R.layout.row_manager_order, listSell);

        listViewSell.setAdapter(sellAdapter);

        listViewSell.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clickItem(listSell.get(i).getId());
            }
        });


    }

    private void clickItem(String idOrder) {
        Dialog dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.alert_confirmsell);

        Button btnYes = dialog.findViewById(R.id.YES_Sell);
        Button btnNo = dialog.findViewById(R.id.NO_Sell);


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SellerAPI.confirmSellAPI(getContext(), idOrder, new APICallBack() {
                        @Override
                        public void onSuccess(JSONObject response) throws JSONException {
                            CustomToast.makeText(getContext(), "Xác nhận bán mặt hàng thành công, Shiper sẽ sớm tới lấy hàng, vui lòng chuẩn bị hàng !", CustomToast.LENGTH_LONG, CustomToast.SUCCESS, true).show();
                            sellAdapter.notifyDataSetChanged();
                            ExportPDF exportPDF = new ExportPDF();
                            exportPDF.main(getContext(), "Hoadon" + idOrder);

                        }

                        @Override
                        public void onError(VolleyError error) {

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

}
