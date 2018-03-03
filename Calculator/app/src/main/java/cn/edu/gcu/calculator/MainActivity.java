package cn.edu.gcu.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.cal);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText num1Edit = (EditText) findViewById(R.id.num1);
                EditText num2Edit = (EditText) findViewById(R.id.num2);

                double num1 = Double.valueOf(num1Edit.getText().toString());
                double num2 = Double.valueOf(num2Edit.getText().toString());

                TextView textView  = (TextView) findViewById(R.id.result);
                textView.setText(Double.toString(num1+num2));
            }
        });
    }
}
