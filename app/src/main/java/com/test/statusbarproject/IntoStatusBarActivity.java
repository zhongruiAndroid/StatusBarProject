package com.test.statusbarproject;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.github.statusbar.StatusBarUtils;

public class IntoStatusBarActivity extends AppCompatActivity {
    NestedScrollView nsv;
    FrameLayout flTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into_status_bar);
        StatusBarUtils.setIntoStatusBar(this);

        initView();
    }

    private void initView() {
        flTitle = findViewById(R.id.flTitle);
        flTitle.setPadding(0,StatusBarUtils.getStatusBarHeight(this),0,0);
        nsv = findViewById(R.id.nsv);

        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int x, int y, int i2, int i3) {
                float radio = 1;
                if (y <= 400) {
                    radio = y * 1f / 200;
                }
                flTitle.setAlpha(radio);
            }
        });
    }
}
