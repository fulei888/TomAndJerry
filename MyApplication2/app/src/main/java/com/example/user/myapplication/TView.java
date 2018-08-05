package com.example.user.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.user.myapplication.logic.GameBoard;
import com.example.user.myapplication.logic.Player;

import java.util.ArrayList;


/**
 * Created by USER on 11/14/2016.
 */

public class TView extends View  {


    Paint  panduPaint = new Paint();
    Bitmap duckImage, redbut;
    private boolean initialized;
    private button b1;
    ArrayList<GuiToken> tokens = new ArrayList<>();
    GameBoard gb = new GameBoard();

    ArrayList<button> con = new ArrayList<button>();
    int w;
    int h;
    Timer t;
/*
make the program draw every 100
 */
    private class Timer extends Handler {

        public Timer() {
            handleMessage(obtainMessage());
        }

        @Override
        public void handleMessage(Message m) {
            for(GuiToken t:tokens) {
                t.move();
            }


            invalidate();
            sendMessageDelayed(obtainMessage(), 100);
        }
    }


    public TView(Context c){
        super(c);
        initialized = false;
/*
put a picture in the duckImage
 */duckImage = BitmapFactory.decodeResource(getResources(), R.drawable.duck);


    }

    @Override
    public void onDraw(Canvas canvas){

        /*
        get the screen wideth and Height
         */
        w = canvas.getWidth();
        h = canvas.getHeight();
        /*
        if (!initialized) egual if(initialized ==false)
         */
        if (!initialized) {

            for(char i='1'; i<='5'; i++){
                b1 = new button(getResources(), w, h);
                b1.setLocation(w * (0.25f + (i-'1') * 0.1f)
                        , h * 0.15f);
                b1.getlable(i);
                con.add(b1);
            }
            for(char u='a'; u<'f'; u++ ){
                b1 = new button(getResources(), w, h);
                b1.setLocation(w * 0.15f
                        , h * (0.25f + (u - 'a') * 0.1f));
                b1.getlable(u);
                con.add(b1);

            }
            t = new Timer();
            initialized = true;
        }

            /*draw grids

             */
            panduPaint.setColor(Color.RED);
            panduPaint.setStyle(Paint.Style.STROKE);
            panduPaint.setStrokeWidth(10);
            canvas.drawColor(Color.YELLOW);
            /*
            draw the trangle
             */
            canvas.drawRect(w * 0.25f, h * 0.25f, w * 0.75f, h * 0.75f, panduPaint);

            canvas.drawLine(w * 0.25f, h * 0.35f, w * 0.75f, h * 0.35f, panduPaint);
            canvas.drawLine(w * 0.25f, h * 0.45f, w * 0.75f, h * 0.45f, panduPaint);
            canvas.drawLine(w * 0.25f, h * 0.55f, w * 0.75f, h * 0.55f, panduPaint);
            canvas.drawLine(w * 0.25f, h * 0.65f, w * 0.75f, h * 0.65f, panduPaint);

            canvas.drawLine(w * 0.35f, h * 0.25f, w * 0.35f, h * 0.75f, panduPaint);
            canvas.drawLine(w * 0.45f, h * 0.25f, w * 0.45f, h * 0.75f, panduPaint);
            canvas.drawLine(w * 0.55f, h * 0.25f, w * 0.55f, h * 0.75f, panduPaint);
            canvas.drawLine(w * 0.65f, h * 0.25f, w * 0.65f, h * 0.75f, panduPaint);
            int duckWidth = w / 4;
            int duckHeight = h / 5;
            duckImage = Bitmap.createScaledBitmap(duckImage, duckWidth, duckHeight, true);
            canvas.drawBitmap(duckImage, w * 0.35f, h * 0.001f, panduPaint);
        /*
        draw button
         */
        for (button a : con) {
            a.draw(canvas);
        }

            for(GuiToken t:tokens) {
                t.draw(canvas);
            }



    }


    @Override
    public boolean onTouchEvent(MotionEvent m) {
        float x = m.getX();
        float y = m.getY();

        if (m.getAction() == MotionEvent.ACTION_DOWN) {
        /*
        touch button and get press picture
         */

            for (button c : con) {

                if (c.contains(x, y)) {
                    c.press();
                    Player current = gb.getCurrentPlayer();

                /*
                when you touch the button, you get a word
                 */
                    Toast t = Toast.makeText(getContext(),
                            "Wow!!!",
                            Toast.LENGTH_SHORT);
                    t.show();
                    if(current ==Player.X){
                        Log.d("turen", "xxxx");
                    }
                    if(current ==Player.O){
                        Log.d("turen", "ooooo");

                    }


                    char label = c.lable;
                    /*
                    find out whose turn
                     */
                    gb.submitMove(label, current);
                  GuiToken  g = new GuiToken(getResources(), w, h,c.pos.left,c.pos.top,current);
                     tokens.add(g);
                }

            }
            /*
            If you touch inside of the grid, you get a respond
             */
            if(x>0.25*w && x<0.75*w &&y>0.25*h && y<0.75*h){

                Toast t = Toast.makeText(getContext(),
                        "Please touch the button",
                        Toast.LENGTH_SHORT);
                t.show();
            }

        }
        /*
        When you unpress, recover to the original button
         */
        if (m.getAction() == MotionEvent.ACTION_UP) {

            for (button c : con) {
                c.release();

            }

        }
        /*
        invalidate only update ondraw method
         */
        invalidate();



        return true;
    }


}
