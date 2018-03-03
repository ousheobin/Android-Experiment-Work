package cn.edu.gcu.change.color;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CountThread countThread = null;
    ChangcolorThread changcolorThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startThread1 = (Button) findViewById(R.id.btn_start_thread_1);
        startThread1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCountThread();
            }
        });

        Button stopThread1 = (Button) findViewById(R.id.btn_end_thread_1);
        stopThread1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopCountThread();
            }
        });

        Button startThread2 = (Button) findViewById(R.id.btn_start_thread_2);
        startThread2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChangcolorThread();
            }
        });

        Button stopThread2 = (Button) findViewById(R.id.btn_end_thread_2);
        stopThread2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopChangcolorThread();
            }
        });

    }

    private void startCountThread() {
        if(countThread==null){
            Handler countHandler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    TextView res = (TextView) findViewById(R.id.text_count);
                    int count = msg.getData().getInt("count");
                    res.setText(Integer.toString(count));
                    return false;
                }
            });
            countThread = new CountThread(countHandler);
            countThread.start();
        }
    }

    private void stopCountThread(){
        if(countThread!=null){
            countThread.setRunning(false);
            try{
                Thread.sleep(1001);
            } catch (Exception e){

            }
            countThread = null;
        }
    }

    private void startChangcolorThread() {
        if(changcolorThread==null){
            Handler changeColorHandler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    Button btn = (Button) findViewById(R.id.btn_start_thread_2);
                    btn.setBackgroundColor(Color.parseColor(msg.getData().getString("color")));
                    return false;
                }
            });
            changcolorThread = new ChangcolorThread(changeColorHandler);
            changcolorThread.start();
        }
    }

    private void stopChangcolorThread() {
        if(changcolorThread!=null){
            changcolorThread.setRunning(false);
            try{
                Thread.sleep(1001);
            } catch (Exception e){

            }
            changcolorThread = null;
        }
    }

    private class CountThread extends Thread{

        int i = 0;

        private Handler handler;

        private boolean isRunning = true;

        public CountThread(Handler handler){
            this.handler = handler;
        }

        public boolean isRunning() {
            return isRunning;
        }

        public void setRunning(boolean running) {
            isRunning = running;
        }

        @Override
        public void run() {
            while(isRunning) {
                i++;
                Bundle bundle = new Bundle();
                bundle.putInt("count",i);
                Message msg = new Message();
                msg.setData(bundle);
                msg.what = 0;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {

                }
            }
        }
    }

    private class ChangcolorThread extends Thread {

        private Handler handler;

        private int index = 0;

        private String[] colors = {"#FFD64843","#FF52C4AD","#FF11ACE4"};

        private boolean isRunning = true;

        public ChangcolorThread(Handler handler){
            this.handler = handler;
        }

        public boolean isRunning() {
            return isRunning;
        }

        public void setRunning(boolean running) {
            isRunning = running;
        }

        @Override
        public void run() {
            while(isRunning) {
                index = (index+1) % colors.length;
                Bundle bundle = new Bundle();
                bundle.putString("color",colors[index]);
                Message msg = new Message();
                msg.setData(bundle);
                msg.what = 0;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {

                }
            }
        }
    }


}
