package cn.edu.gcu.slidingdrawexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SlidingDrawer slidingDrawer;
    ImageButton imageButton;
    ListView listView;
    LinearLayout linearLayout;
    String data[] = {
            "使命召唤",
            "植物大战僵尸",
            "愤怒的小鸟"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                imageButton.setImageResource(R.drawable.down);
            }
        });

        slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                imageButton.setImageResource(R.drawable.up);
            }
        });

        slidingDrawer.setOnDrawerScrollListener(new SlidingDrawer.OnDrawerScrollListener() {
            @Override
            public void onScrollStarted() {
                Toast.makeText(getApplicationContext(),"拖动开始",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScrollEnded() {
                Toast.makeText(getApplicationContext(),"拖动结束",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initComponent(){
        slidingDrawer = (SlidingDrawer) findViewById(R.id.sliding_drawer);
        imageButton = (ImageButton) findViewById(R.id.handle);
        linearLayout = (LinearLayout) findViewById(R.id.content);
        listView = new ListView(this);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data));
        linearLayout.addView(listView);
    }
}
