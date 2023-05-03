package com.example.ecomerceshoppe.API;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.ultils.Utils;

import org.json.JSONException;
import org.json.JSONObject;


public class SellerAPI {
    public static void getOrderByShop(Context context, String idShop, APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        postData.put("id", idShop);

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Utils.BASE_URL+"customer/purchase/seller", (JSONObject) requestBody.get("data"),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callBack.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println( "Nam : " +response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        callBack.onError(error);
                    }
                }) ;
        requestQueue.add(request);

    }

    public static void confirmSellAPI(Context context, String idOrder, APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        postData.put("id", idOrder);

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Utils.BASE_URL+"customer/sell/confirm", (JSONObject) requestBody.get("data"),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callBack.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println( "Nam : " +response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        callBack.onError(error);
                    }
                }) ;
        requestQueue.add(request);

    }

}
