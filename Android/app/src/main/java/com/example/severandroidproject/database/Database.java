package com.example.severandroidproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
public Database(Context context){
    super(context, "Gio_Hang", null, 1);

}
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE gioHang(" +
                "id INTEGER primary key AUTOINCREMENT," +
                "catelory text," +
                "pro_Name text," +
                "pro_Img text, " +
                "pro_Price text," +
                "soluong INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
