package com.example.severandroidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailProductActivity extends AppCompatActivity {
ImageView proIv;
TextView nameTv,priceTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        proIv=findViewById(R.id.productIV);
        nameTv=findViewById(R.id.nameTv);
        priceTv=findViewById(R.id.priceTv);

        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String image=intent.getStringExtra("image");
        String price=intent.getStringExtra("price");
        Picasso.with(DetailProductActivity.this).load(image).placeholder(R.drawable.ic_launcher_background).into(proIv);
        nameTv.setText(name);
        priceTv.setText(price + " VND");

    }
}