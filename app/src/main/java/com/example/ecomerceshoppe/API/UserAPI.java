package com.example.ecomerceshoppe.API;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
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

import java.util.HashMap;
import java.util.Map;

public class UserAPI {
    public static void getProfileUserAPI(Context context, String url, String access_token, APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        postData.put("id", "63af70c03f562b7531d4c5db");

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, (JSONObject) requestBody.get("data"),
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
                        System.err.println("Lỗi call api getProfileUserAPI (UserAPI.java) ->" + error.getMessage());
                        callBack.onError(error);

                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + access_token);

                return headers;
            }
        };


        requestQueue.add(request);

    }

    public static void LoginAPI(Context context, String url, String username, String password, APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        postData.put("username", username);
        postData.put("password", password);

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody.getJSONObject("data"),
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
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.data != null) {
                            String jsonError = new String(networkResponse.data);
                            System.err.println(jsonError);
                        }
//                        System.err.println("Lỗi call api LoginAPI (UserAPI.java) ->" + error.getMessage());
                        callBack.onError(error);


                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");

                return headers;
            }
        };


        requestQueue.add(request);

    }

    public static void GetProductBySeller(Context context, String url, String idSeller, APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        postData.put("seller", idSeller);

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody.getJSONObject("data"),
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
                        System.err.println("Lỗi call api GetProductBySeller (UserAPI.java) ->" + error.getMessage());
                        callBack.onError(error);


                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");

                return headers;
            }
        };


        requestQueue.add(request);

    }

    public static void RegisterAPI(Context context, String username, String password,String repassword, APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        postData.put("username", username);
        postData.put("password", password);
        postData.put("repassword", repassword);

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Utils.BASE_URL + "auth/register", requestBody.getJSONObject("data"),
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
                        System.err.println("Lỗi call api RegisterAPI (UserAPI.java) ->" + error.getMessage());
                        callBack.onError(error);


                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");

                return headers;
            }
        };


        requestQueue.add(request);

    }
}
