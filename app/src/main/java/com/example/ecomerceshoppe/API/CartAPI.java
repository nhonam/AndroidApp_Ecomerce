package com.example.ecomerceshoppe.API;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecomerceshoppe.interfaces.APICallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CartAPI {
    public static void AddToCart(Context context, String url,String idUser, String idProduct,int quantity, APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        postData.put("product", idProduct);
        postData.put("customer", idUser);
        postData.put("quantity", quantity);

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, (JSONObject) requestBody.get("data"),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callBack.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        callBack.onError(error);
                    }
                });
        requestQueue.add(request);

    }

    public static void getCartByUser(Context context, String url, String id_User, APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url+id_User, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callBack.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        System.err.println("Lỗi call api getCartByUser (CartAPI.java) -> " + error.getMessage());
                        callBack.onError(error);

                    }
                }) ;
        requestQueue.add(request);
    }


    public static void DeleteCart(Context context, String url, String idCart, APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url+idCart, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callBack.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        System.err.println("Lỗi call api Delete (CartAPI.java) -> " + error.getMessage());
                        callBack.onError(error);

                    }
                }) ;
        requestQueue.add(request);
    }




}
