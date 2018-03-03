package cn.edu.gcu.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class DemoService extends Service {

    private static final String TAG = "DemoService Demo";

    public DemoService(){

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"Service Demo On Bind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"Service Demo On Create");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG,"Service Demo On Start");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"Service Demo Handle Start Command");
        return super.onStartCommand(intent, flags, startId);
    }
}
