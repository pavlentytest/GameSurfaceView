package com.example.acer.myapplication;

import java.util.Random;

public class Star {
    private int x;
    private int y;
    private int speed;

    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public Star (int screenX, int screenY) {
        this.maxX = screenX;
        this.maxY = screenY;
        this.minX= 0;
        this.minY = 0;
        Random generator = new Random();
        this.x = generator.nextInt(maxX);
        this.y = generator.nextInt(maxY);
    }

    public void update(int playerSpeed) {
        x -= playerSpeed;
        // todo: использовать playerSpeed
        if(x < 0) {
            x = maxX;
            Random generator = new Random();
            y = generator.nextInt(maxY);
            speed = generator.nextInt(15);
        }

    }

    public float getStarWidth() {
        float minX = 1.0f;
        float maxX = 10.0f;
        Random rand = new Random();
        float finalX = rand.nextFloat() * (maxX - minX) + minX;
        return finalX;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
