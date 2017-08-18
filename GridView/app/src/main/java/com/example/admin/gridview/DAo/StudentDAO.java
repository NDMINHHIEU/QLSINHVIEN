package com.example.admin.gridview.DAo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.admin.gridview.DTO.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 8/16/2017.
 */

public class StudentDAO {
    private Context context;
    SQLiteDatabase db;
    Database database;

    public StudentDAO(Context context){
        this.context = context;
        database = new Database(context);
    }

    public void addStudent(Student student){
        db = database.getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("MSSV",student.getMssv());
            contentValues.put("Ten", student.getTen());
            db.insert("sinhvien",null,contentValues);
        }
        catch (Exception e)
        {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public List<Student> getList(){
        db = database.getReadableDatabase();
        List<Student> list = new ArrayList<>();
        try{
            String sql = "select * from sinhvien";
            Cursor cursor = db.rawQuery(sql,null);
            cursor.moveToPosition(-1);
            while(cursor.moveToNext()){
                Student student = new Student(cursor.getString(0),cursor.getString(1));
                list.add(student);
            }
        }catch (Exception e){
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
        }
        finally {
            return list;
        }
    }

    public void deleteStudent(Student student){
        db = database.getWritableDatabase();
        try{
            String sql = "delete from sinhvien where MSSV = " + student.getMssv();
            db.execSQL(sql);
        }
        catch (Exception e)
        {
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
        }
    }

    public void Search(String newText){
        String sql = "select * from sinhvien where MSSV like %" + newText + "% or Ten like %" + newText + "%";
        db = database.getReadableDatabase();
        try{
            db.execSQL(sql);
        }
        catch (Exception e)
        {
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
        }
    }
}
