package com.example.severandroidproject.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.severandroidproject.Model.GioHang;
import com.example.severandroidproject.database.Database;


import java.util.ArrayList;

public class DaoGioHang {
    Database database;

    public DaoGioHang(Context context) {
        database = new Database(context);
    }
    public ArrayList<GioHang> getAll() {
        ArrayList<GioHang> list = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM gioHang", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String loai = cs.getString(1);
            String name = cs.getString(2);
            String img = cs.getString(3);
            String price = cs.getString(4);
            int soluong = cs.getInt(5);
            GioHang gioHang = new GioHang(id,loai, name,img,  price,soluong);
            list.add(gioHang);
            cs.moveToNext();
        }
        cs.close();
        return list;
    }
    //Thêm lớp mới
    public boolean them(GioHang gioHang) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("catelory", gioHang.getCatelory());
        contentValues.put("pro_Name", gioHang.getPro_Name());
        contentValues.put("pro_Price", gioHang.getPro_Price());
        contentValues.put("pro_Img", gioHang.getPro_Img());
        contentValues.put("soluong", gioHang.getSoluong());

        long r = db.insert("gioHang", null, contentValues);
        if (r <= 0) {
            return false;
        }
        return true;
    }

    //Cập nhật tên Lớp mới, không cho sửa mã lớp bởi mã lớp là khóa chính, sửa sẽ lỗi!
    public boolean sua(int id, int sl) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("soluong", sl);
        int r = db.update("gioHang", contentValues, "id=?", new String[]{String.valueOf(id)});
        if (r <= 0) {
            return false;
        }
        return true;
    }

    //Xóa lớp
    public boolean xoa(GioHang gioHang) {
        SQLiteDatabase db = database.getWritableDatabase();
        int r = db.delete("gioHang", "id=?", new String[]{String.valueOf(gioHang.getId())});
        if (r <= 0) {
            return false;
        }
        return true;
    }
}
