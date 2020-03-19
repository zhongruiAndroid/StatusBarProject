package com.test.statusbarproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.statusbar.StatusBarUtil;
import com.github.statusbar.StatusBarUtils;

public class TestActivity extends AppCompatActivity {

    private Button bt;
    AppCompatSeekBar sbSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setBarColor(0);

        sbSeekBar=findViewById(R.id.sbSeekBar);
        sbSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float v = progress * 1f / sbSeekBar.getMax();
                setBarColor(v);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    private void setBarColor( @FloatRange(from = 0.0D,to = 1.0D) float ratio){
        StatusBarUtils.setStatusColor(this,ContextCompat.getColor(TestActivity.this, R.color.c_ADECE5),ratio);
    }

}
