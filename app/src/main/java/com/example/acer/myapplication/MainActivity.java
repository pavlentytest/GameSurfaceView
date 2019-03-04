package com.example.acer.myapplication;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GameView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        gv = new GameView(this,size.x,size.y);
        //setContentView(R.layout.activity_main);
        setContentView(gv);
    }



    @Override
    protected void onPause() {
        super.onPause();
        gv.pause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        gv.resume();
    }

}
