package com.example.severandroidproject.api;
import com.example.severandroidproject.Model.Product;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("/api/products")
    Call<List<Product>>  getPhanLoai();
}
