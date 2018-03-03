package cn.edu.gcu.colormatrix.demo;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends AppCompatActivity {

    private ImageView imgView;
    private SeekBar rSeekBar;
    private SeekBar gSeekBar;
    private SeekBar bSeekBar;
    private SeekBar aSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgView = (ImageView) findViewById(R.id.picture);

        rSeekBar = (SeekBar) findViewById(R.id.r_seek_bar);
        gSeekBar = (SeekBar) findViewById(R.id.g_seek_bar);
        bSeekBar = (SeekBar) findViewById(R.id.b_seek_bar);
        aSeekBar = (SeekBar) findViewById(R.id.a_seek_bar);

        OnSeekBarChangeListener listener = new SeekbarListener();

        rSeekBar.setOnSeekBarChangeListener(listener);
        gSeekBar.setOnSeekBarChangeListener(listener);
        bSeekBar.setOnSeekBarChangeListener(listener);
        aSeekBar.setOnSeekBarChangeListener(listener);

        rSeekBar.setProgress(20);
        gSeekBar.setProgress(20);
        bSeekBar.setProgress(20);
        aSeekBar.setProgress(100);

    }

    private class SeekbarListener implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            float r = ( rSeekBar.getProgress() / 20f ) ;
            float g = ( gSeekBar.getProgress() / 20f ) ;
            float b = ( bSeekBar.getProgress() / 20f );
            float a = ( aSeekBar.getProgress() / 100f );
            Log.d("ColorSeekBar","r:"+r+" g:"+g+" b:"+b+" a:"+a);
            float[]  matrix = {
                    r , 0 , 0 , 0, 0 ,
                    0 , g , 0 , 0, 0 ,
                    0 , 0 , b , 0, 0 ,
                    0 , 0 , 0 , a, 0
            };
            ColorMatrix colorMatrix = new ColorMatrix(matrix);
            imgView.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
