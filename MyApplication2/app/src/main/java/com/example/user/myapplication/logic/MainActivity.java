package com.example.user.myapplication.logic;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.example.user.myapplication.TView;

public class MainActivity extends Activity {

    private TView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        tv = new TView(this);
        setContentView(tv);


    }





}