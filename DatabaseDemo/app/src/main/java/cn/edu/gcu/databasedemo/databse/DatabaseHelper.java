package cn.edu.gcu.databasedemo.databse;

/**
 * Created by ousheobin on 2017/11/23.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dormitory_helper.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        //CursorFactory设置为null,使用默认值
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("create table","Do create");
        String create_phone_table = "CREATE TABLE tb_phone(f_id INTEGER PRIMARY KEY AUTOINCREMENT,f_name VARCHAR(255),f_phone VARCHAR(255),f_address VARCHAR(255),f_email VARCHAR(255));";
        db.execSQL(create_phone_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.i("On SQLite Upgrade","Do nothing");
    }

}
