package com.example.ecomerceshoppe.API;

import android.content.Context;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecomerceshoppe.interfaces.APIEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

public class ProductAPI {

//    public interface VolleyCallback {
//        void onSuccess(JSONObject result) throws JSONException;
//        void onError(VolleyError error);
//    }



    public static void getAPIString(Context context, String url) {
        //init request
        RequestQueue queue = Volley.newRequestQueue(context);
        //add url in request
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);

    }


    public static void getAPIJson(Context context, String url, APIEvent listener) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        listener.onSuccess(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error: ");
                error.printStackTrace();
//                callback.onError(error);
                System.err.println("lỗi port server vui lòng đổi IP trong file Utils.java");
            }
        });

        queue.add(jsonObjectRequest);
    }

    public static void getAllCategories(Context context, String category, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url+category, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // handle response

                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle error
                        System.err.println(("API Error " + error.toString()));
                    }
                }) ;
        queue.add(jsonObjectRequest);
    }


}
