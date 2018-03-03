package cn.edu.gcu.gallary;

import android.graphics.Bitmap;
import android.media.Image;
import android.renderscript.Allocation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    private int[] imageViewArray;
    private int currentImage = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewArray = new int[3];
        imageViewArray[0] = R.mipmap.bg1;
        imageViewArray[1] = R.mipmap.bg2;
        imageViewArray[2] = R.mipmap.bg3;
        currentImage = 0;
        Button prevBtn = (Button) findViewById(R.id.btn_prev);
        Button nextBtn = (Button) findViewById(R.id.btn_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    switch (currentImage){
                        case 0 : currentImage = 1; break;
                        case 1 : currentImage = 2; break;
                        default: currentImage = 0; break;
                    }
                    changeImages(currentImage);
                }catch (Exception e){
                    Log.e("Exception","e",e);
                }
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    switch (currentImage){
                        case 0 : currentImage = 2; break;
                        case 1 : currentImage = 0; break;
                        default: currentImage = 1; break;
                    }
                    changeImages(currentImage);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void changeImages(int currentImage){
        ImageView image = (ImageView) findViewById(R.id.image_view1);
        Log.d("Denug","--->"+currentImage);
        image.setImageResource(imageViewArray[currentImage]);
    }
}
