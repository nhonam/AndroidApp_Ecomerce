package com.example.ecomerceshoppe.API;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProductAPI {


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


    public static void getAPIJson(Context context, String url, APICallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            callBack.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error: "+error.getMessage());
                error.printStackTrace();
                System.err.println("lỗi port server vui lòng đổi IP trong file Utils.java");
                callBack.onError(error);
            }
        });

        queue.add(jsonObjectRequest);
    }



    public static void getAllCategory(Context context, APICallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "product/get/allCategory", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            callBack.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error: "+error.getMessage());
                error.printStackTrace();
                System.err.println("lỗi port server vui lòng đổi IP trong file Utils.java");
                callBack.onError(error);
            }
        });

        queue.add(jsonObjectRequest);
    }

    public static void APIDelProduct(Context context, String url, String idProduct, APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        postData.put("id", "63af70c03f562b7531d4c5db");

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url+idProduct,null,
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
    public static void APIUpdateProduct(Context context, String url, Product product, String img, APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        JSONObject postData = new JSONObject();
        postData.put("_id", product.getId());
        postData.put("name_product", product.getNameProduct());
        postData.put("tag", product.getTag());
        postData.put("quantity", product.getQuantity());
        postData.put("price", product.getPrice());
        postData.put("category", product.getCategory());
        postData.put("description", product.getDescription());
        if (!img.equalsIgnoreCase("")){
            postData.put("image","data:image/jpeg;base64,"+ img);
        }

        postData.put("updatedAt", formatter.format(date));

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PATCH, url+product.getId(),requestBody.getJSONObject("data"),
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

    public static void APIAddProduct(Context context, String url, Product product, String base64Img, APICallBack callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();

        System.out.println("api create đc gọi");
        JSONObject postData = new JSONObject();

        postData.put("seller", product.getSeller());
        postData.put("name_product", product.getNameProduct());
        postData.put("tag", product.getTag());
        postData.put("quantity", product.getQuantity());
        postData.put("price", product.getPrice());
        postData.put("category", product.getCategory());
        postData.put("description", product.getDescription());

        postData.put("image","data:image/jpeg;base64,"+ base64Img);
//        postData.put("createdAt", formatter.format(date));

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,requestBody.getJSONObject("data"),
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

    public static void test(Context context, String url, APICallBack callBack, String id_User) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        postData.put("id", "63af70c03f562b7531d4c5db");

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url+id_User, (JSONObject) requestBody.get("data"),
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
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("param1", "num1");
                params.put("param2", "num2");
                return params;
            };

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJUcmFuIE5oYXQgRHV5IiwiZGF0YSI6IjY0MTgxYmFiY2QwMWViNmFjYTA5ODdkYiIsImlhdCI6MTY3OTMyNTkyMDkxMCwiZXhwIjoxNjc5NDEyMzIwOTEwfQ.M8s-rFhZ9Klm5GmuPtog-UIbH0_ADT7rh4vt93ltv98");
                return headers;
            }
        };
        requestQueue.add(request);

    }

    public static void testAPI(Context context, String url,Product product,String base64Img,  APICallBack callBack, String id_User) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();


        JSONObject postData = new JSONObject();
        postData.put("_id", product.getId());
        postData.put("name_product", product.getNameProduct());
        postData.put("tag", product.getTag());
        postData.put("quantity", product.getQuantity());
        postData.put("price", product.getPrice());
        postData.put("category", product.getCategory());
        postData.put("description", product.getDescription());
//        postData.put("image", base64Img);

        postData.put("updatedAt", formatter.format(date));

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PATCH, url+product.getId(),requestBody.getJSONObject("data"),
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
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("image", base64Img);
//                params.put("param2", "num2");
                return params;
            };

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJUcmFuIE5oYXQgRHV5IiwiZGF0YSI6IjY0MTgxYmFiY2QwMWViNmFjYTA5ODdkYiIsImlhdCI6MTY3OTMyNTkyMDkxMCwiZXhwIjoxNjc5NDEyMzIwOTEwfQ.M8s-rFhZ9Klm5GmuPtog-UIbH0_ADT7rh4vt93ltv98");
                return headers;
            }
        };
        requestQueue.add(request);

    }



}
