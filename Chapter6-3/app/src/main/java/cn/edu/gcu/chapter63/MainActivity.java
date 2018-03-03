package cn.edu.gcu.chapter63;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    RecView recView = null;
    int x = 150;
    int y = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recView = new RecView(this);
        recView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    x = (int) event.getX() - 150;
                    y = (int) event.getY() - 100;
                    recView.getXY(x,y);
                    recView.layout(x,y,x+200,y+100);
                    setContentView(recView);
                }
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    x = (int) event.getX() - 150;
                    y = (int) event.getY() - 100;
                    recView.getXY(x,y);
                    setContentView(recView);
                }
                Log.d("tag","on touch x:"+x+" y:"+x);
                return true;

            }
        });
        recView.getXY(x,y);
        setContentView(recView);
    }


}
