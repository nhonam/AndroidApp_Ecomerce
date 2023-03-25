package com.example.ecomerceshoppe.interfaces;


import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public interface APICallBack {

    public void onSuccess(JSONObject response) throws JSONException;

    public void onError(VolleyError error);

}
