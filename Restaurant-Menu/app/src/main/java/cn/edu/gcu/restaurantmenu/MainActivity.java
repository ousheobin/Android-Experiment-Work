package cn.edu.gcu.restaurantmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    private ImageSwitcher imageSwitcher;
    private Gallery gallery;

    private int[] imgs = {
            R.mipmap.cai1,
            R.mipmap.cai2,
            R.mipmap.cai3,
            R.mipmap.cai4,
            R.mipmap.cai5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.image_switcher);
        imageSwitcher.setFactory(new SwitcherFactory());
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));
        imageSwitcher.setImageResource(R.mipmap.cai1);
        gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setOnItemSelectedListener(new ItemSelectListener());
        gallery.setAdapter(new BaseAdapter());
    }

    private class SwitcherFactory implements ViewSwitcher.ViewFactory {

        @Override
        public View makeView(){
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            return imageView;
        }
    }

    private class BaseAdapter extends android.widget.BaseAdapter {

        @Override
        public int getCount(){
            return imgs.length;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource((int)getItem(position));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setLayoutParams(new Gallery.LayoutParams(200,200));
            return imageView;
        }

        @Override
        public Object getItem(int position) {
            return imgs[position];
        }
    }

    private class ItemSelectListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            imageSwitcher.setImageResource((int)gallery.getItemAtPosition(position));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


}
