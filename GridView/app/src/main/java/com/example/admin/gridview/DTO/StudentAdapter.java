package com.example.admin.gridview.DTO;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.gridview.R;

import java.util.List;

/**
 * Created by Admin on 8/16/2017.
 */

public class StudentAdapter extends ArrayAdapter {
    List<Student> list;
    Context context;
    int resource;
    public StudentAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View row = layoutInflater.inflate(R.layout.row_view,null);
        TextView txtTen = (TextView) row.findViewById(R.id.txtTen);
        TextView txtMSSv = (TextView) row.findViewById(R.id.txtMSSV);
        Student student = list.get(position);
        txtTen.setText(student.getTen());
        txtMSSv.setText(student.getMssv());
        return row;
    }
}
