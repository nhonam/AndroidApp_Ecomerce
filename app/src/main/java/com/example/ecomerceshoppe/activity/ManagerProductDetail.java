package com.example.ecomerceshoppe.activity;

import static android.hardware.SensorPrivacyManager.Sensors.CAMERA;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.ecomerceshoppe.API.ProductAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.interfaces.APICallBack;
import com.example.ecomerceshoppe.model.Product;
import com.example.ecomerceshoppe.service.CategoryService;
import com.example.ecomerceshoppe.ultils.CustomToast;
import com.example.ecomerceshoppe.ultils.Feature;
import com.example.ecomerceshoppe.ultils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ManagerProductDetail extends AppCompatActivity {

    private int GALLERY_REQ_CODE = 1000;
    private EditText edtName, edtTag, edtQuanti, edtPrice, edtDescription;
    private Spinner spCategory;
    private ImageView imgProduct;
//    private Button btnImage, btnSave, btnExit;

    TextView btnSave;

    LinearLayout btnImage, btnExit;

    String idUser = "";

    Bitmap bitmap = null;
    private Product product;
    ArrayAdapter adapterCategory;
    int categoryCurrent = 0;

    String[]  ListCategory ;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.layout_product_detail);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        idUser = getIntent().getStringExtra("idUserCurrent");

        product = (Product) getIntent().getSerializableExtra("msg");
//        if(product.getId()!=null)
//        System.out.println("id   "+product.getId());
        mapping();
        setEvent();
    }

    private void mapping() {
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        progressBar.setVisibility(View.GONE);
        edtName = findViewById(R.id.nameProduct_ManagerProductDetail);
        edtTag = findViewById(R.id.tag_ManagerProductDetail);
        edtQuanti = findViewById(R.id.quantity_ManagerProductDetail);
        edtPrice = findViewById(R.id.price_ManagerProductDetail);
        edtDescription = findViewById(R.id.description_ManagerProductDetail);
        spCategory = findViewById(R.id.category_ManagerProductDetail);
        imgProduct = findViewById(R.id.img_ManagerProductDetail);
        btnImage = findViewById(R.id.editImg_ManagerProductDetail);
        btnSave = findViewById(R.id.save_ManagerProductDetail);
        btnExit = findViewById(R.id.exit_ManagerProductDetail);

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
                    imgProduct.setImageBitmap(bitmap);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == CAMERA) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imgProduct.setImageBitmap(bitmap);
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



    private void SaveCreate() {

        //call api create product
        System.out.println("nút lưu đc nhấn");
        Product productTmp = new Product();
        productTmp.setNameProduct(String.valueOf(edtName.getText()));
        productTmp.setSeller(idUser);
        productTmp.setTag(String.valueOf(edtTag.getText()));
        productTmp.setQuantity(Integer.parseInt(String.valueOf(edtQuanti.getText())));
        productTmp.setPrice(Double.parseDouble(String.valueOf(edtPrice.getText())));
        productTmp.setCategory(String.valueOf(spCategory.getSelectedItem()));
        productTmp.setDescription(String.valueOf(edtDescription.getText()));
        progressBar.setVisibility(View.VISIBLE);
        String base64Img = Feature.CovertBitmapToBase64(bitmap);

        try {
            ProductAPI.APIAddProduct(getApplicationContext(), Utils.BASE_URL + "product/create", productTmp, base64Img, new APICallBack() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {
//                                progressDialog.dismiss();
                    JSONObject data = response.getJSONObject("data");
                    progressBar.setVisibility(View.GONE);
                    CustomToast.makeText(ManagerProductDetail.this, "Thêm Mới Sản Phẩm Thành Công", CustomToast.LENGTH_SHORT, CustomToast.SUCCESS, true).show();
                    CheckProductExist(data);
                }

                @Override
                public void onError(VolleyError error) {
                    System.err.println(error.getMessage());
//                                progressDialog.dismiss();
                    progressBar.setVisibility(View.GONE);
                    CustomToast.makeText(ManagerProductDetail.this, "Error Thêm Mới Sản Phẩm Không Thành Công", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();
                }
            });
        } catch (JSONException e) {
//                        progressDialog.dismiss();
            progressBar.setVisibility(View.GONE);
            CustomToast.makeText(ManagerProductDetail.this, "Catch Thêm Mới Sản Phẩm Không Thành Công", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();

            throw new RuntimeException(e);

        }
    }

    private void CheckProductExist(JSONObject data) {
        try {
            ProductAPI.APICheckProduct(getApplicationContext(), Utils.BASE_URL + "product/check-product/", data.getString("_id"), new APICallBack() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {

                }

                @Override
                public void onError(VolleyError error) {
                }
            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void SaveUpdate() {
        System.out.println("update sp");
        Product productTmp = new Product();
        productTmp.setId(product.getId());
        productTmp.setNameProduct(String.valueOf(edtName.getText()));
//                productTmp.setSeller(product.getSeller());
        productTmp.setTag(String.valueOf(edtTag.getText()));
        productTmp.setQuantity(Integer.parseInt(String.valueOf(edtQuanti.getText())));
        productTmp.setPrice(Double.parseDouble(String.valueOf(edtPrice.getText())));
        productTmp.setCategory(String.valueOf(spCategory.getSelectedItem()));
        productTmp.setDescription(String.valueOf(edtDescription.getText()));

        String base64Img = "";
        if (bitmap != null) {
            //anim loading
            progressBar.setVisibility(View.VISIBLE);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            base64Img = Base64.encodeToString(bytes, Base64.NO_WRAP);
        }

        try {
            ProductAPI.APIUpdateProduct(getApplicationContext(), Utils.BASE_URL + "product/updatePatch/", productTmp, base64Img, new APICallBack() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {
                    progressBar.setVisibility(View.GONE);
                    CustomToast.makeText(ManagerProductDetail.this, "Cập Nhật Sản Phẩm Thành Công", CustomToast.LENGTH_SHORT, CustomToast.SUCCESS, true).show();
//                            System.out.println(response);
//                            progressDialog.dismiss();

                }

                @Override
                public void onError(VolleyError error) {
//                            System.err.println(error.getMessage());
                    progressBar.setVisibility(View.GONE);
                    CustomToast.makeText(ManagerProductDetail.this, "Cập Nhật Sản Phẩm Không Thành Công", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();

                }
            });
        } catch (JSONException e) {
            progressBar.setVisibility(View.GONE);
            CustomToast.makeText(ManagerProductDetail.this, "Cập Nhật Sản Phẩm Không Thành Công", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();

            throw new RuntimeException(e);
        }
    }

    private void setEvent() {

        ListCategory = CategoryService.loadLogoName().toArray(new String[0]);
        adapterCategory = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ListCategory);
        spCategory.setAdapter(adapterCategory);
//        int selectionPosition= adapterCategory.getPosition(product.getCategory());
        spCategory.setSelection(categoryCurrent);
        //nếu tồn tại product , khi bấm bấm update
        if (product != null) {
            Glide.with(this).load(product.getUrlImage()).into(imgProduct);
            edtName.setText(product.getNameProduct());
            edtTag.setText(product.getTag());
            edtQuanti.setText(String.valueOf(product.getQuantity()));
            edtPrice.setText(String.valueOf(product.getPrice()));
            edtDescription.setText(product.getDescription());
            categoryCurrent = adapterCategory.getPosition(product.getCategory());
            spCategory.setSelection(categoryCurrent);
        }

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerProductDetail.this, ManagerShop.class);
                startActivity(intent);

            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // click lưu khi update
                if (product != null) {

                    SaveUpdate();

                } else {
                    SaveCreate();
                }

            }
        });


    }


}
