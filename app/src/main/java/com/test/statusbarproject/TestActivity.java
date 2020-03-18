package com.test.statusbarproject;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.statusbar.StatusBarUtils;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        StatusBarUtils.setStatusColor(this, ContextCompat.getColor(this,R.color.c_ADECE5));
    }
}
