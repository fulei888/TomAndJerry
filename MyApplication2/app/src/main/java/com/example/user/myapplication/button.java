package com.example.user.myapplication;

/**
 * Created by USER on 11/21/2016.
 */


import android.content.res.Resources;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
        import android.graphics.RectF;

/**
 * Created by draperg on 11/18/16.
 */

public class button {
    private Bitmap image,photo;
            RectF pos;
    private Paint spencerPaint;
    boolean pressed =false;
    char lable;

    public button(Resources res, int w, int h) {
        int  duckWidth = w/10;
        int duckHeight = h/10;
        spencerPaint = new Paint();
        pos = new RectF(0,0,duckWidth,duckHeight);
        /*
        put a button imagine in the image
         */
        image = BitmapFactory.decodeResource(res,
                R.drawable.bomb1);
        photo = BitmapFactory.decodeResource(res,
                R.drawable.blow2);
        /*
        scale the picture
         */
        image = Bitmap.createScaledBitmap(image,
                duckWidth, duckHeight, true);
        photo = Bitmap.createScaledBitmap(photo,
                duckWidth, duckHeight, true);

    }

    public void setLocation(float x, float y) {
        pos.offsetTo(x,y);
    }

    public void draw(Canvas canvas) {
        if (pressed == false) {
            canvas.drawBitmap(image, pos.left, pos.top,
                    spencerPaint);
        } else {
            canvas.drawBitmap(photo, pos.left, pos.top,
                    spencerPaint);

        }
    }

    public boolean contains(float x, float y) {
        return pos.contains(x,y);

    }
    /*

     */
    public void press(){
        pressed = true;
    }
    public void release(){
        pressed = false;
    }

    public void getlable(char a){
        lable =a;
    }


}
