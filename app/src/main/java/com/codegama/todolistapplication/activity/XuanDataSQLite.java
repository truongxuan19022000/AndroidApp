package com.codegama.todolistapplication.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.codegama.todolistapplication.model.TaskDetails;

import java.util.ArrayList;
import java.util.List;

public class XuanDataSQLite extends SQLiteOpenHelper {
    private static final String TAG="MyDatabaseHelper";
    private static final String DATABASE_NAME="Task_DB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_Task = "Task";
    private static final String ID_COLUMN = "id";
    private static final String Task_Name = "nameTask";
    private static final String CREATE_Task_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_Task + " (" +
                    ID_COLUMN + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    Task_Name + " TEXT NOT NULL" +
                    ")";
    private static XuanDataSQLite MyDataSQLite;
    public static XuanDataSQLite getInstance(Context context)
    {
        if(MyDataSQLite == null){
            MyDataSQLite = new XuanDataSQLite(context.getApplicationContext());
        }
        return MyDataSQLite;
    }
    public XuanDataSQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG,"oncreate:");
        try {
            db.execSQL(CREATE_Task_TABLE_SQL);
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.e(TAG, "onUpgrapde: ");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Task);
        onCreate(db);
    }
    public boolean insertTask(TaskDetails taskDetails) {
/**
 * long insert(String table, String nullColumnHack, ContentValues values)
 * chèn một bản ghi trên các cơ sở dữ liệu. Bảng chỉ định tên bảng,
 * nullColumnHack không cho phép các giá trị hoàn toàn vô giá trị.
 * Nếu số thứ hai là null, android sẽ lưu trữ các giá trị null nếu giá
 trị
 * là trống rỗng. Đối số thứ ba xác định các giá trị được lưu trữ.
 */
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Task_Name, taskDetails.getNameTask());
        long rowId = db.insert(TABLE_Task, null, values);
        db.close();
        if (rowId != -1)
            return true;

            return false;
        
    }
    /**
     Cursor query(String table, String[] columns, String selection, String[]
     selectionArgs,
     String groupBy, String having, String orderBy)
     */
    public TaskDetails getTask(String id) {
            SQLiteDatabase db = getReadableDatabase();
            TaskDetails taskDetails = null;
            Cursor cursor = db.query(TABLE_Task, new String[]{ID_COLUMN,
                            Task_Name},
                    ID_COLUMN + " = ?",
                    new String[]{String.valueOf(id)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                taskDetails = new TaskDetails(cursor.getInt(0), cursor.getString(1));
                cursor.close();
            }
            db.close();
            return taskDetails;
    }
/**
 * thực hiện 1 câu truy vấn trực tiếp
 *add tung dong vao list ds
 */
public ArrayList<TaskDetails> getAllTask() {
    SQLiteDatabase db=getReadableDatabase();
    ArrayList<TaskDetails> taskDetails =new ArrayList<TaskDetails>();
    String sql="SELECT * FROM "+TABLE_Task;
    Cursor cursor =db.rawQuery(sql,null);
    if(cursor != null && cursor.moveToFirst()){
        do{
            taskDetails.add(new TaskDetails(cursor.getInt(cursor.getColumnIndex(ID_COLUMN)),
                            cursor.getString(cursor.getColumnIndex(Task_Name)))
                    );

        }while (cursor.moveToNext());
        cursor.close();
    }
    db.close();
    return taskDetails;
}
    //Đếm tổng số dòng trong database
    public int getTotalWord() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_Task;
        Cursor cursor = db.rawQuery(sql, null);
        int totalRows = cursor.getCount();
        cursor.close();
        return totalRows;
    }

    public int updateTask(TaskDetails TaskDetail) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Task_Name,TaskDetail.getNameTask());
        int rowEffect=db.update(TABLE_Task,values,ID_COLUMN+"= ?",
                new String[]{String.valueOf(TaskDetail.getId())});
        db.close();
        return rowEffect;
    }
    public int deleteAllTask() {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(TABLE_Task, null,null);
        db.close();
        return rowEffect;
    }
    public int deleteTask(TaskDetails TaskDetail) {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(TABLE_Task, ID_COLUMN + " = ?", new
                String[]{String.valueOf(TaskDetail.getId())});
        db.close();
        return rowEffect;
    }
}

