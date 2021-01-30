package com.example.severandroidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.severandroidproject.DAO.DaoGioHang;
import com.example.severandroidproject.Model.GioHang;
import com.example.severandroidproject.adapter.GioHangAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangAcitvity extends AppCompatActivity {
    private ImageView iv;
    public static RecyclerView rcListGioHang;
    public static List<GioHang> list = new ArrayList<>();
    DaoGioHang daoGioHang;
    public static GioHangAdapter gioHangAdapter;
    public static TextView tongTien;
    public static int tong = 0;
    private TextView datHang;
    DecimalFormat fm = new DecimalFormat("###,###,###");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang_acitvity);

        iv = findViewById(R.id.ivBack);
        tong = 0;
        datHang = findViewById(R.id.tvDatHangOk);
        rcListGioHang = findViewById(R.id.rcListGioHang);
        daoGioHang = new DaoGioHang(this);
        datHang = findViewById(R.id.tvDatHangOk);
        tongTien = findViewById(R.id.tvTongTien);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        datHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GioHangAcitvity.this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        list = daoGioHang.getAll();
        for(int i=0;i<list.size();i++){
            tong+=(Integer.parseInt(list.get(i).getPro_Price())*list.get(i).getSoluong());
//            Toast.makeText(this, ""+list.get(i).getPro_Price(), Toast.LENGTH_SHORT).show();
        }
        tongTien.setText(fm.format(tong)+" VND");
        gioHangAdapter = new GioHangAdapter(this, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcListGioHang.setLayoutManager(layoutManager);
        rcListGioHang.setAdapter(gioHangAdapter);
    }
}