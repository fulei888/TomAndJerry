package com.example.user.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.user.myapplication.logic.Player;

import static com.example.user.myapplication.logic.Player.O;
import static com.example.user.myapplication.logic.Player.X;

/**
 * Created by USER on 12/11/2016.
 */

public class GuiToken {
    private PointF goal, velocity;
    private RectF pos;
    private Bitmap ob,xb;
    private Paint spencerPaint;
    boolean pressed =false;
    boolean turn = false;
    public GuiToken(Resources res, int w, int h,float x, float y, Player current) {
        /*
        size the image
         */
        int duckWidth = w / 10;
        int duckHeight = h / 10;
        spencerPaint = new Paint();
        pos = new RectF(0, 0, duckWidth, duckHeight);
        /*make the O image and X image

         */
        ob = BitmapFactory.decodeResource(res,
                R.drawable.j1);
        xb = BitmapFactory.decodeResource(res,
                R.drawable.cat1);
        ob = Bitmap.createScaledBitmap(ob,
                duckWidth, duckHeight, true);
        xb = Bitmap.createScaledBitmap(xb,
                duckWidth, duckHeight, true);
        goal = new PointF();
        velocity = new PointF();
        setLocation(x,y);
        /*
        find the row and column of button and move forward
         */
        if (x==w * 0.15f && y>=h*0.25f && y<=h*0.65f){
            setGoal(w*0.25f, y);

        }
        if (y==h *0.15f&& x>=w*0.25f && x<=w*0.65f){
            setGoal(x,h*0.25f);

        }
        if(current == Player.X){
            turn = false;
        }
        if(current == Player.O){
            turn = true;
        }

    }
    /*
    set goal and move and move slowly
     */
        public void setLocation(float x1, float y1 ) {
            pos.offsetTo(x1,y1);
        }
    public void setGoal(float x, float y) {
        goal.set(x,y);
        float run = goal.x - pos.left;
        float rise = goal.y - pos.top;
        velocity.x = run/20;
        velocity.y = rise/20;
    }
    public void move() {
        pos.offset(velocity.x, velocity.y);
        float dx = Math.abs(pos.left - goal.x);
        float dy = Math.abs(pos.top - goal.y);
        double distanceToGoal = Math.hypot(dx, dy);
        if (distanceToGoal <= 20) {
            pos.offsetTo(goal.x, goal.y);
            velocity.set(0,0);
        }
    }
/*
draw the image
 */
    public void draw(Canvas canvas) {
        if (turn ==false) {
            canvas.drawBitmap(xb, pos.left, pos.top,
                    spencerPaint);
        }
        if (turn ==true) {
            canvas.drawBitmap(ob, pos.left, pos.top,
                    spencerPaint);
        }
    }



}


