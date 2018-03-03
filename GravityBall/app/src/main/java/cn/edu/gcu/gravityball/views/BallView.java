package cn.edu.gcu.gravityball.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import cn.edu.gcu.gravityball.MainActivity;
import cn.edu.gcu.gravityball.R;


/**
 * Created by ousheobin on 2017/12/14.
 */

public class BallView extends SurfaceView implements SurfaceHolder.Callback,Runnable ,SensorEventListener {


    public static final int TIME_IN_FRAME = 50;
    Paint mPaint = null;
    Paint mTextPaint = null;

    SurfaceHolder mSurfaceHolder = null;
    boolean mRunning = false;
    Canvas mCanvas = null;

    boolean mIsRunning = false;

    private SensorManager mSensorMgr = null;
    Sensor mSensor = null;

    int mScreenWidth = 0;
    int mScreenHeight = 0;

    private int mScreenBallWidth = 0;
    private int mScreenBallHeight = 0;

    private Bitmap mbitmapBg;
    private Bitmap mbitmapBall;

    private float mPosX = 200;
    private float mPosY = 0;

    private float mGX = 0;
    private float mGY = 0;
    private float mGZ = 0;

    public BallView(Context context,SensorManager mSensorMgr)
    {
        super(context);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        mCanvas = new Canvas();
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mbitmapBall = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ball );
        mbitmapBg = BitmapFactory.decodeResource(this.getResources(), R.mipmap.bg);
        this.mSensorMgr = mSensorMgr;
        if (mSensorMgr == null) {
            Toast.makeText(context,"不支持传感器!",Toast.LENGTH_LONG).show();
            throw new UnsupportedOperationException("Sensors not supported");
        }
        mSensor = mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorMgr.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
    }


    private void Draw()
    {
        int w=mbitmapBall.getWidth();
        int h=mbitmapBall.getHeight();
        float sx=(float)100/w;//要强制转换，不转换我的在这总是死掉。
        float sy=(float)100/h;
        Matrix matrix = new Matrix();
        matrix.postScale(sx, sy); // 长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(mbitmapBall, 0, 0, w,h, matrix, true);
        mbitmapBg = Bitmap.createScaledBitmap(mbitmapBg, mScreenWidth, mScreenHeight, true);
        mCanvas.drawBitmap(mbitmapBg,0,0, mPaint);
        mCanvas.drawBitmap(resizeBmp, mPosX,mPosY, mPaint);
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height){

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        mIsRunning = true;
        new Thread(this).start();
        mScreenWidth = this.getWidth();
        mScreenHeight = this.getHeight();
        mScreenBallWidth = mScreenWidth - mbitmapBall.getWidth();
        mScreenBallHeight = mScreenHeight - mbitmapBall.getHeight();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsRunning = false;
    }
    @Override
    public void run()
    {
        while (mIsRunning)
        {
            long startTime = System.currentTimeMillis();
            synchronized (mSurfaceHolder)
            {
                mCanvas = mSurfaceHolder.lockCanvas();
                Draw();
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
            long endTime = System.currentTimeMillis();
            int diffTime = (int) (endTime - startTime);
            while (diffTime <= TIME_IN_FRAME)
            {
                diffTime = (int) (System.currentTimeMillis() - startTime);
                Thread.yield();
            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        mGX = event.values[SensorManager.DATA_X];
        mGY= event.values[SensorManager.DATA_Y];
        mGZ = event.values[SensorManager.DATA_Z];
        mPosX -= mGX * 2.5;
        mPosY += mGY * 2.5;
        if (mPosX < 0) {
            mPosX = 0;
        } else if (mPosX > mScreenBallWidth) {
            mPosX = mScreenBallWidth;
        }
        if (mPosY < 0)
        {
            mPosY = 0;
        } else if (mPosY > mScreenBallHeight)
        {
            mPosY = mScreenBallHeight;
        }
    }
}
