package com.example.severandroidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.severandroidproject.Model.Product;
import com.example.severandroidproject.adapter.ProductAdapter;
import com.example.severandroidproject.api.API;
import com.example.severandroidproject.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
 RetrofitClient retrofit=new RetrofitClient();
RecyclerView rcvProduct;
 ArrayList<Product > list;
 ProductAdapter productAdapter;
    SwipeRefreshLayout pullToRefresh;
    ImageView btnGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullToRefresh = findViewById(R.id.pullToRefresh);
        btnGioHang = findViewById(R.id.btnGioHang);
        list=new ArrayList<>();
        rcvProduct=findViewById(R.id.rcvProduct);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rcvProduct.setLayoutManager(layoutManager);

        getData();
        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GioHangAcitvity.class);
                startActivity(i);
            }
        });

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData(); // your code
                pullToRefresh.setRefreshing(false);
            }

            private void refreshData() {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });
    }

    private void getData() {
        API api=retrofit.getClient().create(API.class);
        api.getPhanLoai().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> ds=response.body();
                for (int i=0;i<ds.size();i++){
                    Product product=(Product) ds.get(i);
                    list.add(product);
                     Log.d("size",product.pro_Price+"");
                    productAdapter=new ProductAdapter(MainActivity.this,list);
                    rcvProduct.setAdapter(productAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("E",t.getMessage());
            }
        });

    }
}