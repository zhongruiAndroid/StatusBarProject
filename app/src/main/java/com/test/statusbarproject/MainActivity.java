package com.test.statusbarproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.FloatRange;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.statusbar.StatusBarUtil;
import com.github.statusbar.StatusBarUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtils.setStatusColor(this, ContextCompat.getColor(this, R.color.colorAccent));

        initView();

    }



    private void initView() {
        Button btTest = findViewById(R.id.btTest);
        btTest.setOnClickListener(this);

        Button btTestDrawerLayout = findViewById(R.id.btTestDrawerLayout);
        btTestDrawerLayout.setOnClickListener(this);

        Button btTestIntoStatusBar = findViewById(R.id.btTestIntoStatusBar);
        btTestIntoStatusBar.setOnClickListener(this);
    }

    private void startAct(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btTest:
                startAct(TestActivity.class);
                break;
            case R.id.btTestDrawerLayout:
                startAct(DrawLayoutActivity.class);
                break;
            case R.id.btTestIntoStatusBar:
                startAct(IntoStatusBarActivity.class);
                break;
        }
    }
}
