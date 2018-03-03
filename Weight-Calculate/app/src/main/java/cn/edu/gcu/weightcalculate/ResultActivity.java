package cn.edu.gcu.weightcalculate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle bundle = this.getIntent().getExtras();
        double height = bundle.getDouble("height");
        String gender = bundle.getString("gender");
        TextView result = (TextView) findViewById(R.id.result_text);
        if(gender!=null && gender.equals("male")){
            double weight = height * 100 - 105;
            result.setText("您的性别为：男\n您的身高为："+height+"米\n您的标准体重为："+weight+"公斤");
        }else if(gender!=null && gender.equals("female")){
            double weight = height * 100 - 100;
            result.setText("您的性别为：女\n您的身高为："+height+"米\n您的标准体重为："+weight+"公斤");
        }
        registerForContextMenu(result);
        Button button = (Button) findViewById(R.id.btn_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Test");
        menu.add(0,1,1,"Item1");
        menu.add(0,2,2,"Item2");
    }
}
