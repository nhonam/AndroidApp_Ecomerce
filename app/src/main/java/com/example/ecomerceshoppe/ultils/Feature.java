package com.example.ecomerceshoppe.ultils;

import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileFilter;
import java.util.Map;

public class Feature {
    public static JSONObject convertKeyValueToJSON(LinkedTreeMap<String, Object> ltm) {
        JSONObject jo=new JSONObject();
        Object[] objs = ltm.entrySet().toArray();
        for (int l=0;l<objs.length;l++)
        {
            Map.Entry o= (Map.Entry) objs[l];
            try {
                if (o.getValue() instanceof LinkedTreeMap)
                    jo.put(o.getKey().toString(),convertKeyValueToJSON((LinkedTreeMap<String, Object>) o.getValue()));
                else
                    jo.put(o.getKey().toString(),o.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jo;
    }

    public static FileFilter isValidImage = new FileFilter() {


        @Override
        public boolean accept(File pathname) {
            final String name = pathname.getName();
            String ext = null;
            int i = name.lastIndexOf('.');


            if (i > 0 && i < name.length() - 1) {
                ext = name.substring(i + 1).toLowerCase();
            }
            if (ext == null)
                return false;
            else if (!ext.equals("jpg") && !ext.equals("jpeg") && !ext.equals("png") && !ext.equals("gif"))
                return false;
            else
                return true;
        }
    };

}
