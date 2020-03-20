package com.test.statusbarproject;

import android.support.annotation.FloatRange;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.statusbar.StatusBarUtils;

public class DrawLayoutActivity extends AppCompatActivity {

    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_layout);
        dl = findViewById(R.id.dl);
        setBarColor(0);
    }

    private void setBarColor( @FloatRange(from = 0.0D,to = 1.0D) float ratio){
        StatusBarUtils.setStatusColorForDrawerLayout(this,dl, ContextCompat.getColor(this, R.color.c_ADECE5),ratio);
    }
}
