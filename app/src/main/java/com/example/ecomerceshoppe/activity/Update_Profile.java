package com.example.ecomerceshoppe.activity;

import static android.hardware.SensorPrivacyManager.Sensors.CAMERA;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.VolleyError;
import com.example.ecomerceshoppe.API.UserAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class Update_Profile extends AppCompatActivity {

    private int GALLERY_REQ_CODE = 1000;
    AppCompatButton btnSave;
    TextView tvFullName, tvEmail, tvAdress, tvBirthdat, tvPhone, tvCCCD, tvChangAvt;
    Bitmap bitmap = null;
    User userTmp = null;

    ImageView imgProfile;

    private void setMapping() {
        tvFullName = findViewById(R.id.fullname_Pf);
        tvEmail = findViewById(R.id.emailProfile);
        tvAdress = findViewById(R.id.adressUpdatePf);
        tvBirthdat = findViewById(R.id.birthdayUpdate);
        tvPhone = findViewById(R.id.phoneUpdate);
        tvCCCD = findViewById(R.id.cccdUpdate);
        imgProfile = findViewById(R.id.img_profile_avatar);
        tvChangAvt = findViewById(R.id.txt_profile_btn_change_avatar);

        btnSave = findViewById(R.id.btnSaveProfile);

    }
    private String idUser="";
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.update_profile);

        idUser = getIntent().getStringExtra("idUserCurrent");

        setMapping();
        setEvent();

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

        tvChangAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });
    }

    private User getInfoUser() {
        User user = new User();
        user.setFullName(String.valueOf(tvFullName.getText()));


        return user;
    }

    private void UpdateInfo() throws JSONException {
        UserAPI.UpdateInfoUserAPI(Update_Profile.this, userTmp, new APICallBack() {
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
