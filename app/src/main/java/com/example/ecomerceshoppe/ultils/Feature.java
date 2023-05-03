package com.example.ecomerceshoppe.ultils;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.model.User;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
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

    //
public static Date ConvertStringtoDate(String dateString){

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    try {
        Date date = dateFormat.parse(dateString);
        return  date;
    } catch (ParseException e) {
        e.printStackTrace();
    }
   return new Date();
}

    public static Date ConvertMinutetoDate(String dateString){
//        String dateString = "2023-04-22T03:12:08.690Z";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = inputFormat.parse(dateString);
            String outputDate = outputFormat.format(date);
           return  date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
       return  new Date();
    }



}


