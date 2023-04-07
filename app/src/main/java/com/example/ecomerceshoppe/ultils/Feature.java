package com.example.ecomerceshoppe.ultils;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Base64;

import com.example.ecomerceshoppe.model.User;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class Feature {

    public static String CovertBitmapToBase64(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return  Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public static User ConvertStringtoUser(String str){
            Gson gson = new Gson();
            return gson.fromJson(str, User.class);

    }

    public  static Date ConvertStringtoDate(String strDate ) {

        Instant instant = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            instant = Instant.parse(strDate);
        }
        LocalDateTime dateTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }
        LocalDate localDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            localDate = dateTime.toLocalDate();
        }
        Date date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return date;

    }
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
