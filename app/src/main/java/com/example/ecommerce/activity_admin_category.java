package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class activity_admin_category extends AppCompatActivity {

    ImageView tShirts, sportsTshirts, femaleDresses, sweaters;
    ImageView glasses, hatsCaps, walletBagePurses, shoes;
    ImageView headphones, laptops, watches, mobilePhones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        tShirts =(ImageView)findViewById(R.id.ivtshirt);
        sportsTshirts =(ImageView)findViewById(R.id.ivsports);
        femaleDresses =(ImageView)findViewById(R.id.ivfemale);
        sweaters =(ImageView)findViewById(R.id.ivsweater);
        glasses =(ImageView)findViewById(R.id.ivglass);
        hatsCaps =(ImageView)findViewById(R.id.ivhat);
        walletBagePurses =(ImageView)findViewById(R.id.ivpurse);
        shoes =(ImageView)findViewById(R.id.ivshoe);
        headphones =(ImageView)findViewById(R.id.ivheadphones);
        laptops =(ImageView)findViewById(R.id.ivlaptop);
        watches =(ImageView)findViewById(R.id.ivwatch);
        mobilePhones =(ImageView)findViewById(R.id.ivmobile);

        tShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_category.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "tShirts" );
                startActivity(intent);
            }
        });
        sportsTshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_category.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "sportsTshirts" );
                startActivity(intent);
            }
        });
        femaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_category.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "femaleDresses" );
                startActivity(intent);
            }
        });
        sweaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_category.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "sweaters" );
                startActivity(intent);
            }
        });
        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_category.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "glasses" );
                startActivity(intent);
            }
        });
        hatsCaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_category.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "hatsCaps" );
                startActivity(intent);
            }
        });
        walletBagePurses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_category.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "walletBagePurses" );
                startActivity(intent);
            }
        });
        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_category.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "shoes" );
                startActivity(intent);
            }
        });
        headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_category.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "headphones" );
                startActivity(intent);
            }
        });
        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_category.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "laptops" );
                startActivity(intent);
            }
        });
        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_category.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "watches" );
                startActivity(intent);
            }
        });
        mobilePhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_category.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "mobilePhones" );
                startActivity(intent);
            }
        });
    }
//    private void setupUIViews(){
//
//    }
}




