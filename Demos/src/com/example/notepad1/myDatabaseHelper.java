package com.example.notepad1;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class myDatabaseHelper extends SQLiteOpenHelper { 
    private static final String DB_NAME = "mydata.db"; //数据库名称 
    private static final int version = 1; //数据库版本 
    public myDatabaseHelper(Context context) { 
        super(context, DB_NAME, null, version); 
        // TODO Auto-generated constructor stub 
    } 
    @Override
    public void onCreate(SQLiteDatabase db) { 
        String sql = "create table notes(id integer primary key autoincrement,title varchar(20)  , mood varchar(10) , content varchar(10), date varchar(10)  );";           
        db.execSQL(sql); 
    } 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
        // TODO Auto-generated method stub 
    } 
} 

