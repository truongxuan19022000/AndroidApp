package com.codegama.todolistapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codegama.todolistapplication.R;
import com.codegama.todolistapplication.model.TaskDetails;

import java.util.ArrayList;
import java.util.List;

public class TaskDetail extends AppCompatActivity {
TextView nameTask;
ListView lvTask;
ImageButton Addtask;
CheckBox checkItem;
    AlertDialog alertDialog;
    EditText Editname;
    MyArrayAdapter adapter=null;
    ArrayList<TaskDetails> taskDetails=new ArrayList<TaskDetails>();
    XuanDataSQLite db=null;
    TaskDetails taskSelected=new TaskDetails();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        taskDetails=new ArrayList<TaskDetails>();
        Addtask=findViewById(R.id.imageAddtask);
        lvTask=findViewById(R.id.lvTask);
        nameTask=findViewById(R.id.txtItem);
        //Khởi tạo đối tượng adapter và gán dât
        adapter =new MyArrayAdapter(this, R.layout.my_item_layout,taskDetails);
        //Gán Adapter vào listview
        lvTask.setAdapter(adapter);
        db= XuanDataSQLite.getInstance(this);
        refreshDataForListView();

        Addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog=new AlertDialog.Builder(TaskDetail.this).create();
                Editname=new EditText(TaskDetail.this);
                alertDialog.setTitle("Thêm nhiệm vụ ");
                alertDialog.setView(Editname);
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TaskDetails taskDetails=new TaskDetails();
                        taskDetails.setNameTask(Editname.getText().toString());
                        boolean t=db.insertTask(taskDetails);
                        Toast.makeText(TaskDetail.this, "success!!", Toast.LENGTH_SHORT).show();
                        refreshDataForListView();
                        alertDialog.show();
                    }
                });
                alertDialog.show();
//                nameTask.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        TaskDetails taskDetails=new TaskDetails();
//                        taskDetails.setNameTask(Editname.getText().toString());
//                        boolean t=db.insertTask(taskDetails);
//                        Toast.makeText(TaskDetail.this, "success!!", Toast.LENGTH_SHORT).show();
//                        alertDialog.show();
//                    }
//                });
            }
        });
        lvTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                refreshDataForListView();
                db.deleteTask(taskDetails.get(i));
                taskDetails.remove(i);
                Toast.makeText(TaskDetail.this, "Delete Successfully!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });



    }

    /*Load listview lay du lieu*/
    private void refreshDataForListView(){
        try {
            taskDetails=db.getAllTask();
            adapter=new MyArrayAdapter(TaskDetail.this,R.layout.my_item_layout,taskDetails);
            lvTask.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }catch (Exception e){
            System.out.println("có lỗi: " +e.toString());
        }
    }
}