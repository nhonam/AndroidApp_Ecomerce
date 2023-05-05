package com.example.ecomerceshoppe.activity;

import static android.hardware.SensorPrivacyManager.Sensors.CAMERA;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.ecomerceshoppe.API.UserAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Product;
import com.example.ecomerceshoppe.model.User;
import com.example.ecomerceshoppe.ultils.Feature;
import com.example.ecomerceshoppe.ultils.ObjectWrapperForBinder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Update_Profile extends AppCompatActivity {

    private int GALLERY_REQ_CODE = 1000;
    AppCompatButton btnSave;
    EditText tvFullName, tvEmail, tvAdress, tvBirthdat, tvPhone, tvCCCD;
     TextView tvChangAvt;
    Bitmap bitmap = null;
    ImageView imgProfile;
    User userCur = null;

    String ngaySinh="";


    private void setMapping() {
        tvFullName = findViewById(R.id.fullnameUpdate);
        tvEmail = findViewById(R.id.EmailUpdate);
        tvAdress = findViewById(R.id.adressUpdatePf);
        tvBirthdat = findViewById(R.id.birthdayUpdate);
        tvBirthdat.setInputType(InputType.TYPE_CLASS_DATETIME);
        tvPhone = findViewById(R.id.phoneUpdate);
        tvPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
        tvCCCD = findViewById(R.id.cccdUpdate);
        tvCCCD.setInputType(InputType.TYPE_CLASS_NUMBER);
        imgProfile = findViewById(R.id.img_profile_avatar);
        tvChangAvt = findViewById(R.id.txt_profile_btn_change_avatar);

        btnSave = findViewById(R.id.btnSaveProfile);

    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.update_profile);

        //get dữ liệu về thông tin user đc gửi từ fragProfile
         Object objReceived=null;
        try {
            objReceived   = ((ObjectWrapperForBinder) getIntent().getExtras().getBinder("user")).getData();
        }catch (Exception e){

        }

        //get dữ liệu về thông tin user đc gửi từ Login
        Object objReceived_Login = null;
        try {
            objReceived_Login = ((ObjectWrapperForBinder) getIntent().getExtras().getBinder("userCurrent")).getData();

        } catch (Exception e) {

        }


        //nếu bấm cập nhật sửa thông tin
        if(objReceived!=null) {
            userCur = (User) objReceived;
        }else {
            //nếu login vào chưa có thông tin thì phải cập nhật thông tin
            JSONObject userCurrentObj = (JSONObject) objReceived_Login;
            try {
                userCur = new User();
                userCur.setId(userCurrentObj.getString("_id"));
                userCur.setFullName("");
                userCur.setEmail("");
                userCur.setAddress("");
//                userCur.setBirthday(new Date());
                userCur.setPhone("");
                userCur.setIdentity_card("");

                        //Product(productObj.getString("_id"), ((JSONObject) productObj.get("seller")).getString("_id"), productObj.getString("name_product"), productObj.getString("tag"), (int) productObj.get("quantity"), Double.parseDouble(productObj.getString("price")), productObj.getString("category"), productObj.getString("description"), ((JSONObject) productObj.get("img")).getString("url"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }


        setMapping();
        loadData();
        setEvent();

    }

    private void loadData() {
        Glide.with(this).load(userCur.getUrlAvatar()).into(imgProfile);
        tvFullName.setText(userCur.getFullName());
        tvEmail.setText(userCur.getEmail());
        tvAdress.setText(userCur.getAddress());
        System.out.println("nânnanananna"+userCur.getBirthday());

        tvBirthdat.setText(String.valueOf( userCur.getBirthday()));
        tvPhone.setText(userCur.getPhone());
        tvCCCD.setText(userCur.getIdentity_card());
    }


    private void setEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getInfoUser();
                    UpdateInfo();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                startActivity(new Intent(Update_Profile.this, Main.class));
            }
        });

        tvBirthdat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });

        tvChangAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });
    }

    public void showDatePickerDialog(View v) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        c.set(year, monthOfYear, dayOfMonth);
                        EditText editTextDate = findViewById(R.id.birthdayUpdate);
                        ngaySinh = simpleDateFormat.format(c.getTime());
                        editTextDate.setText(simpleDateFormat.format(c.getTime()));

                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private User getInfoUser() {
        User user = new User();
        user.setId(userCur.getId());
        user.setFullName(String.valueOf(tvFullName.getText()));
        user.setEmail(String.valueOf(tvEmail.getText()));
        user.setAddress(String.valueOf(tvAdress.getText()));
        user.setBirthday(  Feature.ConvertStringtoDate(ngaySinh) );
        user.setPhone(String.valueOf(tvPhone.getText()));
        user.setIdentity_card(String.valueOf(tvCCCD.getText()));


        return user;
    }

    private void UpdateInfo() throws JSONException {
        //updateImage
        String base64Img = "";
        if (bitmap != null) {
            //anim loading
//            progressBar.setVisibility(View.VISIBLE);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            base64Img = Base64.encodeToString(bytes, Base64.NO_WRAP);
        }

        UserAPI.UpdateInfoUserAPI(Update_Profile.this,  base64Img, getInfoUser(), new APICallBack() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {


            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY_REQ_CODE) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    imgProfile.setImageBitmap(bitmap);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == CAMERA) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imgProfile.setImageBitmap(bitmap);
        }
    }


    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQ_CODE);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Vui lòng chọn !!!");
        String[] pictureDialogItems = {
                "Chọn ảnh từ Thư Viện",
                "Chụp ảnh bằng Camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
}
