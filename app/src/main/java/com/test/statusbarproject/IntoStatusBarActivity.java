package com.test.statusbarproject;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.github.statusbar.StatusBarUtils;
import com.test.statusbarproject.systembar.ImmersionBar;

public class IntoStatusBarActivity extends AppCompatActivity {
    NestedScrollView nsv;
    FrameLayout flTitle;
    private CheckBox cb;
    AppCompatSeekBar sbSeekBar;
    RadioGroup rgColor;
    private float ratio;
    private int color;
    private int colorEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into_status_bar);
//        StatusBarUtils.setIntoStatusBar(this,Color.TRANSPARENT);
        initView();
        init();
    }

    private CheckBox cbStatusColor;
    private CheckBox cbFitsSystemWindows;
    private CheckBox cbStatusBarDarkFont;

    private int color1=Color.RED;
    private int color2=Color.parseColor("#63329AF3");
    private void init() {
        cbStatusColor = findViewById(R.id.cbStatusColor);
        cbStatusColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setStatusBarColor(isChecked?color1:color2,cbFitsSystemWindows.isChecked(),cbStatusBarDarkFont.isChecked());
            }
        });
        cbFitsSystemWindows = findViewById(R.id.cbFitsSystemWindows);
        cbFitsSystemWindows.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setStatusBarColor(cbStatusColor.isChecked()?color1:color2,cbFitsSystemWindows.isChecked(),cbStatusBarDarkFont.isChecked());
            }
        });
        cbStatusBarDarkFont = findViewById(R.id.cbStatusBarDarkFont);
        cbStatusBarDarkFont.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setStatusBarColor(cbStatusColor.isChecked()?color1:color2,cbFitsSystemWindows.isChecked(),cbStatusBarDarkFont.isChecked());
            }
        });
    }

    private void showDialog() {
        Dialog dialog=new Dialog(this);
        View inflate = getLayoutInflater().inflate(R.layout.dialog_confit, null);
        dialog.setContentView(inflate);
        dialog.show();
    }

    private void initView() {
        cb = findViewById(R.id.cb);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
                int statusBarColor = ColorUtils.blendARGB(color, colorEnd, ratio);
                Window window = getWindow();
                if(isChecked){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP  ) {
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                        window.setStatusBarColor(statusBarColor);
                    }
                }else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP  ) {
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        int systemUiVisibility = decorView.getSystemUiVisibility();
                        systemUiVisibility&=~View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                        systemUiVisibility&=~View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                        decorView.setSystemUiVisibility(0);
                        window.setStatusBarColor(statusBarColor);
                    }
                }
            }
        });
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


        color=ContextCompat.getColor(IntoStatusBarActivity.this, R.color.c_ADECE5);
        colorEnd=Color.TRANSPARENT;

        setBarColor(color,colorEnd,ratio);

        rgColor=findViewById(R.id.rgColor);
        rgColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rbBlack){
                    colorEnd=Color.TRANSPARENT;
                }else{
                    colorEnd=ContextCompat.getColor(IntoStatusBarActivity.this, R.color._ffff7a84);
                }
                setBarColor(color,colorEnd,ratio);
            }
        });

        sbSeekBar =findViewById(R.id.sbSeekBar);
        sbSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ratio = progress * 1f / sbSeekBar.getMax();
                int statusBarColor = ColorUtils.blendARGB(color,colorEnd, ratio);
//                setStatusBarColor(statusBarColor,cbFitsSystemWindows.isChecked(),cbStatusBarDarkFont.isChecked());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setBarColor(int color, int colorEnd, float ratio) {
//        StatusBarUtils.setStatusColor(this,color,colorEnd,ratio);
    }
    private ImmersionBar immersionBar;
    public void setStatusBarColor(Integer statusBarColor, boolean isFitsSystemWindows, boolean isStatusBarDarkFont) {
        if(true){
            return;
        }
        if (  statusBarColor == null) {
            return;
        }
        if (immersionBar == null) {
            immersionBar = ImmersionBar.with(this);
        }
        if (immersionBar.getBarParams() == null) {
            return;
        }
        immersionBar.fitsSystemWindows(isFitsSystemWindows);
        if (isFitsSystemWindows) {
            immersionBar.statusBarColorInt(statusBarColor);
        }
        immersionBar
//              .navigationBarColorInt(statusColor)
                .statusBarDarkFont(isStatusBarDarkFont)
                .fullScreen(true)
                .navigationBarEnable(false)
                .init();

    }
}
