package cn.edu.gcu.chapter63;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by ousheobin on 2017/11/1.
 */

public class RecView extends View{

    int x , y ;

    public RecView(Context context){
        super(context);
    }

    void getXY(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        canvas.drawRect(x,y,x+300,y+200,paint);
        paint.setColor(Color.TRANSPARENT);
        canvas.drawRect(x,y,x+1000,y+1000,paint);
    }
}
