package com.example.ecomerceshoppe.Pragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.SellerAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.adapter.OrderAdapter;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class FragOrder extends Fragment {
    private Order orderUser;
    private ArrayList<Order> listOrder;
    String idShop = "";//"63af70c03f562b7531d4c5db"; //ttesstt


    ListView listViewOrder;
    OrderAdapter orderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_manager_order_shop, container, false);
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

                listOrder = new ArrayList<>();
                for (int i = 0; i < dataArr.length(); i++) {
                    Order orderTmp = new Order();
                    data = (JSONObject) dataArr.get(i);
                    orderTmp.setId(data.getString("_id"));
                    orderTmp.setCustomer(data.getString("customer"));
                    orderTmp.setTotal_Price(data.getDouble("total_amount"));
                    orderTmp.setAdress(data.getString("address"));
                    orderTmp.setStatus(data.getString("status"));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        orderTmp.setCreatAtOrder( Date.from(Instant.parse(data.getString("createdAt"))));
                    }

                    listOrder.add(orderTmp);

                }
                System.out.println("================");
                System.out.println(listOrder.get(0).getId());
                System.out.println(listOrder.get(1).getId());
                System.out.println("================");
                setEvent();
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }


    private void mapping(View view) {
        listViewOrder = view.findViewById(R.id.listviewManagerOrder);

    }

    private void setEvent() {
        orderAdapter = new OrderAdapter(getContext(), R.layout.row_manager_order, listOrder);
//        System.out.println(orderAdapter);
              listViewOrder.setAdapter(orderAdapter);


    }

}
