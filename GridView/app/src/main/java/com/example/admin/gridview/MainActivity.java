package com.example.admin.gridview;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.gridview.DAo.StudentDAO;
import com.example.admin.gridview.DTO.Student;
import com.example.admin.gridview.DTO.StudentAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    ListView listView;
    List<Student> lv;
    SearchView mSearchView;
    StudentDAO studentDAO;
    StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lvStudent);
        studentDAO = new StudentDAO(getApplicationContext());
        Load();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = lv.get(position);
                studentDAO.deleteStudent(student);
                Load();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//tao su kien khoi tao cho menu
        //getMenuInflater().inflate(R.menu.menu,menu);
        getMenuInflater().inflate(R.menu.search_view,menu);
        MenuItem itemSearch = menu.findItem(R.id.Search_bar);
        mSearchView = (SearchView) itemSearch.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.item_add){
            final Dialog dialog = new Dialog(this);
            dialog.setTitle("Thêm sinh viên");
            dialog.setContentView(R.layout.add_item_layout);
            TextView mssv = (TextView) dialog.findViewById(R.id.txt_item_mssv);
            TextView ten = (TextView) dialog.findViewById(R.id.txt_item_Ten);
            final EditText emssv = (EditText) dialog.findViewById(R.id.edit_item_mssv);
            final EditText eten = (EditText) dialog.findViewById(R.id.edit_item_ten);
            Button btadd = (Button) dialog.findViewById(R.id.btThem);
            dialog.show();

            btadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        Student student = new Student(emssv.getText().toString(),eten.getText().toString());
                        studentDAO.addStudent(student);
                        Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                        Load();
                        dialog.dismiss();
                    }catch (Exception q){
                        Toast.makeText(getApplicationContext(),q.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return true;
        }
        return false;
    }

    public void Load(){
        lv = studentDAO.getList();
        studentAdapter = new StudentAdapter(this,R.layout.row_view,lv);
        listView.setAdapter(studentAdapter);
    }
}
