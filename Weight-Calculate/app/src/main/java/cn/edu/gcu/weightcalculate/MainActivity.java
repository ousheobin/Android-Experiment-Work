package cn.edu.gcu.weightcalculate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btn_cal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),ResultActivity.class);
                intent.putExtras(createFormBundle());
                startActivity(intent);
                finish();
            }
        });
    }

    private Bundle createFormBundle(){
        Bundle bundle = new Bundle();
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group);
        int checked = group.getCheckedRadioButtonId();
        if(checked == R.id.radio_btn_male){
            bundle.putString("gender","male");
        } else {
            bundle.putString("gender","female");
        }
        EditText heightEditText = (EditText)findViewById(R.id.height);
        double height = 1.0d;
        try{
            height = Double.valueOf(heightEditText.getText().toString());
        }catch (Exception e){
            Log.e("Exception","Catche Exception",e);
        }
        bundle.putDouble("height",height);
        return bundle;
    }
}
