package com.example.admin.gridview.DTO;

/**
 * Created by Admin on 8/16/2017.
 */

public class Student {
    private String mssv;
    private String ten;
     public Student(){
         this.mssv = "";
         this.ten = "";
     }
     public Student(String id, String name){
         this.mssv = id;
         this.ten = name;
     }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
