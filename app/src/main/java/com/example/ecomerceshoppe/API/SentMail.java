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

public class SentMail {
    public static void SentMailAPI(Context context, String url, String email,APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        postData.put("email", email);

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

    public static void VertityOTPAPI(Context context,String idUser, String url,String OTP,  String secret, APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        postData.put("token", OTP); // OTP
        postData.put("_id", idUser);

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url+secret, (JSONObject) requestBody.get("data"),
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
                }) ;
        requestQueue.add(request);

    }
}
