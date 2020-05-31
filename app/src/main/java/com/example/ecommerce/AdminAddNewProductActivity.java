package com.example.ecommerce;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AdminAddNewProductActivity extends AppCompatActivity {

    private String categoryName, inputproductName, inputproductDescription, inputproductPrice, saveCurrentDate, saveCurrentTime, productRandomKey;
    private ImageView InputProductImage;
    private EditText InputProductName, InputProductDescription, InputProductPrice;
    private Button AddNewProductButton;
    private static final int GalleryPick = 1;
    private Uri ImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);
        categoryName = getIntent().getExtras().get("category").toString();
        Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show();

        InputProductImage = (ImageView)findViewById(R.id.selectProductImage);
        InputProductName = (EditText)findViewById(R.id.productName);
        InputProductDescription = (EditText)findViewById(R.id.productDescription);
        InputProductPrice = (EditText)findViewById(R.id.productPrice);
        AddNewProductButton = (Button)findViewById(R.id.btnAddProduct);

        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }

    private void validateData() {
        inputproductName = InputProductName.getText().toString();
        inputproductDescription = InputProductDescription.getText().toString();
        inputproductPrice = InputProductPrice.getText().toString();

        if(ImageUri == null){
            Toast.makeText(this,"Product Image Is Mandatory",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(inputproductName)){
            Toast.makeText(this,"Enter Product Name",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(inputproductDescription)){
            Toast.makeText(this,"Enter Product Description",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(inputproductPrice)){
            Toast.makeText(this,"Enter Product Price",Toast.LENGTH_SHORT).show();
        } else {
            storeProductInformation();
        }
    }

    private void storeProductInformation() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;
    }

    private void openGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageUri = data.getData();
        InputProductImage.setImageURI(ImageUri);
    }
}
