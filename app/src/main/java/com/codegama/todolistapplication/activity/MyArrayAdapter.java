package com.codegama.todolistapplication.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.codegama.todolistapplication.R;
import com.codegama.todolistapplication.model.TaskDetails;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<TaskDetails> {
    Activity context=null;
    ArrayList<TaskDetails> myArray=null;
    int layoutld;
    public MyArrayAdapter(Activity  context, int layoutld, ArrayList<TaskDetails> arr) {
        super(context, layoutld, arr);
        this.context=context;
        this.layoutld=layoutld;
        this.myArray=arr;
    }

    @NonNull
    @Override
    //Tạo 1 lớp custom view ở listview
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        convertView=inflater.inflate(layoutld,null);
        if(myArray.size()>0 && position>=0){
            //dòng lệnh lấy textview ra để hiện thị mã và tên lên
            final TextView txtdisplay;
            txtdisplay=convertView.findViewById(R.id.txtItem);
            //Lấy ra nhân viên thứ position
            final TaskDetails taskDetails=myArray.get(position);
            //Đưa thông tin lên textview.emp.tostring() sẽ trả về id và name
            txtdisplay.setText(taskDetails.getNameTask());
            //Lấy imgaveow ra để thiết lập hình ảnh cho đúng nam hay nữ
            final CheckBox checkBox;
            checkBox=convertView.findViewById(R.id.chkItem);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View view) {
                    if(checkBox.isChecked()){
                        checkBox.setTextColor(R.color.colorSiliver);
                        txtdisplay.setTextColor(R.color.colorSiliver);
                        txtdisplay.setText("Hoàn thành!!");
                    }
                    else  {
                        checkBox.setTextColor(R.color.colorAccent);
                    }
                }
            });
        }
        return convertView;
        //trả về view này là trả về thông số thay đổi
    }
}
