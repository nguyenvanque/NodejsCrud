package com.example.severandroidproject.Model;

import java.io.Serializable;

public class GioHang implements Serializable {
    private int id;
    private String catelory, pro_Name, pro_Price, pro_Img;
    private int soluong;


    public GioHang() {
    }

    public GioHang(int id, String catelory, String pro_Name, String pro_Img, String pro_Price, int soluong) {
        this.id = id;
        this.catelory = catelory;
        this.pro_Name = pro_Name;
        this.pro_Price = pro_Price;
        this.pro_Img = pro_Img;
        this.soluong=soluong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatelory() {
        return catelory;
    }

    public void setCatelory(String catelory) {
        this.catelory = catelory;
    }

    public String getPro_Name() {
        return pro_Name;
    }

    public void setPro_Name(String pro_Name) {
        this.pro_Name = pro_Name;
    }

    public String getPro_Price() {
        return pro_Price;
    }

    public void setPro_Price(String pro_Price) {
        this.pro_Price = pro_Price;
    }

    public String getPro_Img() {
        return pro_Img;
    }

    public void setPro_Img(String pro_Img) {
        this.pro_Img = pro_Img;
    }
}
