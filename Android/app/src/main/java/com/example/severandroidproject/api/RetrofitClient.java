package com.example.severandroidproject.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String ROOT_API="http://192.168.43.61:3000/";
    public  static Retrofit retrofit=null;
    public static Retrofit getClient(){
        if(retrofit==null){
           retrofit=new Retrofit
                   .Builder()
                   .baseUrl(ROOT_API)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
        }
        return  retrofit;
    };

}
