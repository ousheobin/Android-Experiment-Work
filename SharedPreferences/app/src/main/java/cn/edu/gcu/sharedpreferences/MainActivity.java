package cn.edu.gcu.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.StrictMode;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText nameEdit;
    private EditText ageEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSave = (Button) findViewById(R.id.save_btn);
        Button btnShow = (Button) findViewById(R.id.show_btn);
        nameEdit = (EditText) findViewById(R.id.name_edit);
        ageEdit = (EditText) findViewById(R.id.age_edit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("1.jq", Context.MODE_PRIVATE);
                String name = nameEdit.getText().toString();
                int age = 0;
                Editor editor = sharedPreferences.edit();
                editor.putString("name",name);
                try{
                    age = Integer.valueOf(ageEdit.getText().toString());
                }catch (Exception e){
                    Log.e("Main Activity","Exception",e);
                }
                editor.putInt("age",age);
                editor.commit();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("1.jq", Context.MODE_PRIVATE);
                int age = sharedPreferences.getInt("age",0);
                String name = sharedPreferences.getString("name","");
                nameEdit.setText(name);
                ageEdit.setText(Integer.toString(age));
            }
        });
    }
}
