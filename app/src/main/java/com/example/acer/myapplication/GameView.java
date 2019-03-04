package com.example.acer.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {

    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    int screenX;
    volatile boolean playing;
    private ArrayList<Star> stars = new ArrayList<Star>();
    private Player player; // 1
    public static MediaPlayer gameSound;
    private Friend friend;


    public GameView(Context context, int screenX, int screenY) {
        super(context);
        player = new Player(context, screenX, screenY); // 2
        surfaceHolder = getHolder();
        paint = new Paint();
        int starNums = 100;
        for(int i=0; i<starNums;i++) {
            stars.add(new Star(screenX,screenY));
        }
        this.screenX = screenX;
        this.friend = new Friend(context,screenX,screenY);
        gameSound = MediaPlayer.create(context,R.raw.gameon);
        gameSound.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;
               //event.getX
        }
        // обработку gameover
        return true;
    }

    @Override
    public void run() {
        while(playing) {
            update();
            draw();
        }
    }

    public void control() {

    }

    public void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.WHITE);
            for (Star s : stars) {
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(), s.getY(), paint);
            }
            canvas.drawBitmap(player.getBitmap(),player.getX(),player.getY(),paint);
            canvas.drawBitmap(friend.getBitmap(),friend.getX(),friend.getY(),paint);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }



    public void update() {
        player.update();
        for(Star s: stars) {
            s.update(player.getSpeed());
        }
        friend.update(player.getSpeed());
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

}
