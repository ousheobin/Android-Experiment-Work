package cn.edu.gcu.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import cn.edu.gcu.databasedemo.databse.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtphone;
    private EditText edtAddress;
    private EditText edtEmail;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    private Button createDBBtn;
    private Button openConnBtn;
    private Button prevBtn;
    private Button nextBtn;
    private Button addRecordBtn;
    private Button editRecordBtn;
    private Button deleteRecordBtn;
    private Button closeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAddress  = (EditText) findViewById(R.id.edt_user_address);
        edtEmail    = (EditText) findViewById(R.id.edt_user_email);
        edtphone    = (EditText) findViewById(R.id.edt_user_phone);
        edtName     = (EditText) findViewById(R.id.edt_name);

        createDBBtn     = (Button) findViewById(R.id.btn_create_db);
        openConnBtn     = (Button) findViewById(R.id.btn_open_db);
        prevBtn         = (Button) findViewById(R.id.btn_prev);
        nextBtn         = (Button) findViewById(R.id.btn_next);
        addRecordBtn    = (Button) findViewById(R.id.btn_add_record);
        editRecordBtn   = (Button) findViewById(R.id.btn_edit_record);
        deleteRecordBtn = (Button) findViewById(R.id.btn_delete_record);
        closeBtn        = (Button) findViewById(R.id.btn_close_phone_book);

        BtnOnClickListener btnOnClickListener = new BtnOnClickListener();

        createDBBtn.setOnClickListener(btnOnClickListener);
        openConnBtn.setOnClickListener(btnOnClickListener);
        prevBtn.setOnClickListener(btnOnClickListener);
        nextBtn.setOnClickListener(btnOnClickListener);
        addRecordBtn.setOnClickListener(btnOnClickListener);
        editRecordBtn.setOnClickListener(btnOnClickListener);
        deleteRecordBtn.setOnClickListener(btnOnClickListener);
        closeBtn.setOnClickListener(btnOnClickListener);
     }

     private class BtnOnClickListener implements OnClickListener{

         @Override
         public void onClick(View view) {
             int id = view.getId();
             switch (id) {
                 case R.id.btn_create_db:
                     dbHelper = new DatabaseHelper(getApplicationContext());
                     break;
                 case R.id.btn_open_db:
                     if (dbHelper != null) {
                         db = dbHelper.getWritableDatabase();
                         String sql = "SELECT * FROM tb_phone ORDER BY f_id ASC;";
                         cursor = db.rawQuery(sql, new String[]{});
                         Log.d("Main Activity","Cursor open");
                         if(cursor.getCount() > 0 ){
                             cursor.moveToFirst();
                             edtEmail.setText(cursor.getString(cursor.getColumnIndex("f_email")));
                             edtAddress.setText(cursor.getString(cursor.getColumnIndex("f_address")));
                             edtName.setText(cursor.getString(cursor.getColumnIndex("f_name")));
                             edtphone.setText(cursor.getString(cursor.getColumnIndex("f_phone")));
                             if(cursor.isFirst()){
                                 prevBtn.setVisibility(View.INVISIBLE);
                             }else{
                                 prevBtn.setVisibility(View.VISIBLE);
                             }
                             if(cursor.isLast()){
                                 nextBtn.setVisibility(View.INVISIBLE);
                             }else{
                                 nextBtn.setVisibility(View.VISIBLE);
                             }
                             Log.d("Main Activity","Cursor position -->" + cursor.getPosition());
                         }else{
                             prevBtn.setVisibility(View.INVISIBLE);
                             nextBtn.setVisibility(View.INVISIBLE);
                         }

                     }
                     break;
                 case R.id.btn_prev:
                     if (db != null && cursor!=null) {
                         if (!cursor.isFirst() && !cursor.isBeforeFirst()) {
                             cursor.moveToPrevious();
                             edtEmail.setText(cursor.getString(cursor.getColumnIndex("f_email")));
                             edtAddress.setText(cursor.getString(cursor.getColumnIndex("f_address")));
                             edtName.setText(cursor.getString(cursor.getColumnIndex("f_name")));
                             edtphone.setText(cursor.getString(cursor.getColumnIndex("f_phone")));
                             if(cursor.isFirst()){
                                 prevBtn.setVisibility(View.INVISIBLE);
                             }else{
                                 prevBtn.setVisibility(View.VISIBLE);
                             }
                             if(cursor.isLast()){
                                 nextBtn.setVisibility(View.INVISIBLE);
                             }else{
                                 nextBtn.setVisibility(View.VISIBLE);
                             }
                             Log.d("Main Activity","Cursor prev -->"+cursor.getPosition());
                         }else if(cursor != null && cursor.isBeforeFirst()){
                             cursor.moveToFirst();
                             edtEmail.setText(cursor.getString(cursor.getColumnIndex("f_email")));
                             edtAddress.setText(cursor.getString(cursor.getColumnIndex("f_address")));
                             edtName.setText(cursor.getString(cursor.getColumnIndex("f_name")));
                             edtphone.setText(cursor.getString(cursor.getColumnIndex("f_phone")));
                             prevBtn.setVisibility(View.INVISIBLE);
                             if(cursor.isLast()){
                                 nextBtn.setVisibility(View.INVISIBLE);
                             }else{
                                 nextBtn.setVisibility(View.VISIBLE);
                             }
                             Log.d("Main Activity","Cursor prev -->"+cursor.getPosition());
                         }

                     }
                     break;
                 case R.id.btn_next:
                     if (db != null && cursor!=null) {
                         if (!cursor.isLast() && !cursor.isAfterLast()) {
                             cursor.moveToNext();
                             edtEmail.setText(cursor.getString(cursor.getColumnIndex("f_email")));
                             edtAddress.setText(cursor.getString(cursor.getColumnIndex("f_address")));
                             edtName.setText(cursor.getString(cursor.getColumnIndex("f_name")));
                             edtphone.setText(cursor.getString(cursor.getColumnIndex("f_phone")));
                             if(cursor.isFirst()){
                                 prevBtn.setVisibility(View.INVISIBLE);
                             }else{
                                 prevBtn.setVisibility(View.VISIBLE);
                             }
                             if(cursor.isLast()){
                                 nextBtn.setVisibility(View.INVISIBLE);
                             }else{
                                 nextBtn.setVisibility(View.VISIBLE);
                             }
                             Log.d("Main Activity","Cursor next -->"+cursor.getPosition());
                         }else if(cursor != null && cursor.isAfterLast()){
                             cursor.moveToLast();
                             edtEmail.setText(cursor.getString(cursor.getColumnIndex("f_email")));
                             edtAddress.setText(cursor.getString(cursor.getColumnIndex("f_address")));
                             edtName.setText(cursor.getString(cursor.getColumnIndex("f_name")));
                             edtphone.setText(cursor.getString(cursor.getColumnIndex("f_phone")));
                             if(cursor.isFirst()){
                                 prevBtn.setVisibility(View.INVISIBLE);
                             }else{
                                 prevBtn.setVisibility(View.VISIBLE);
                             }
                             nextBtn.setVisibility(View.INVISIBLE);
                             Log.d("Main Activity","Cursor next -->"+cursor.getPosition());

                         }
                     }
                     break;
                 case R.id.btn_add_record:
                     if (db != null) {
                         String sql = "INSERT INTO tb_phone(f_name,f_phone,f_address,f_email) VALUES(?,?,?,?);";
                         db.beginTransaction();  //开始事务
                         try {
                             db.execSQL(sql, new String[]{edtName.getText().toString(), edtphone.getText().toString(), edtAddress.getText().toString(), edtEmail.getText().toString()});
                             db.setTransactionSuccessful();
                         } finally {
                             db.endTransaction();
                         }
                         Log.d("Main Activity","Add success");
                     }
                     break;
                 case R.id.btn_edit_record:
                     if (db != null  && cursor!=null) {
                         String sql = "UPDATE tb_phone SET f_name = ? , f_phone = ? ,f_address = ?,f_email= ? WHERE f_id=?;";
                         db.beginTransaction();  //开始事务
                         try {
                             db.execSQL(sql, new Object[]{edtName.getText().toString(), edtphone.getText().toString(), edtAddress.getText().toString(), edtEmail.getText().toString(), cursor.getInt(cursor.getColumnIndex("f_id"))});
                             db.setTransactionSuccessful();
                             Log.d("MainActivity", "Update success");
                         } finally {
                             db.endTransaction();
                         }
                     }
                     break;
                 case R.id.btn_delete_record:
                     if (db != null  && cursor!=null) {
                         String sql = "Delete from tb_phone WHERE f_id = ?";
                         db.beginTransaction();  //开始事务
                         int recID = cursor.getInt(cursor.getColumnIndex("f_id"));
                         if (cursor.isLast()) {
                             cursor.moveToPrevious();
                         } else if (cursor.isFirst()) {
                             cursor.moveToNext();
                         } else {
                             cursor.moveToNext();
                         }
                         try {
                             db.execSQL(sql, new Object[]{recID});
                             db.setTransactionSuccessful();
                             Log.d("MainActivity", "delete sucess");
                         } finally {
                             db.endTransaction();
                         }
                     }
                     break;
                 case R.id.btn_close_phone_book:
                     if (db != null) {
                         cursor.close();
                         cursor = null;
                         edtEmail.setText(null);
                         edtAddress.setText(null);
                         edtName.setText(null);
                         edtphone.setText(null);
                         db.close();
                         db = null;
                         Log.d("MainActivity","Close Success");
                     }
                     break;
                 default:
                     Log.d("Main Activity", "Do Noting");
             }
         }
     }


}
