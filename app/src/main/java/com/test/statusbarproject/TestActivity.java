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
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.statusbar.StatusBarUtil;
import com.github.statusbar.StatusBarUtils;

public class TestActivity extends AppCompatActivity {

    AppCompatSeekBar sbSeekBar;
    RadioGroup rgColor;
    private float ratio;
    private int color;
    private int colorEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        color=ContextCompat.getColor(TestActivity.this, R.color.c_ADECE5);
        colorEnd=Color.BLACK;

        setBarColor(color,colorEnd,ratio);

        rgColor=findViewById(R.id.rgColor);
        rgColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rbBlack){
                    colorEnd=Color.BLACK;
                }else{
                    colorEnd=ContextCompat.getColor(TestActivity.this, R.color._ffff7a84);
                }
                setBarColor(color,colorEnd,ratio);
            }
        });

        sbSeekBar=findViewById(R.id.sbSeekBar);
        sbSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ratio = progress * 1f / sbSeekBar.getMax();
                setBarColor(color,colorEnd,ratio);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    private void setBarColor(@ColorInt int color,@ColorInt int colorEnd ,@FloatRange(from = 0.0D,to = 1.0D) float ratio){
        StatusBarUtils.setStatusColor(this,color,colorEnd,ratio);
    }

}
