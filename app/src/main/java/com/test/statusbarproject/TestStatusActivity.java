package com.test.statusbarproject;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.github.statusbar.StatusBarUtils;

public class TestStatusActivity extends AppCompatActivity   {

    private AppCompatCheckBox cb;
    private AppCompatSeekBar sbColor;
    private int statusBarColor=Color.BLUE;
    private Button btDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_status);
        initView();
    }

    private int color;
    private int colorEnd;

    private float ratio;

    private void initView() {

        color = ContextCompat.getColor(this, R.color.colorPrimary);
        colorEnd = Color.TRANSPARENT;

        btDialog = findViewById(R.id.btDialog);
        btDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        View btSet = findViewById(R.id.btSet);
        btSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusBarUtils.setStatusColor(TestStatusActivity.this,statusBarColor,cb.isChecked());
            }
        });
        sbColor = findViewById(R.id.sbColor);
        sbColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ratio = progress * 1f / sbColor.getMax();
                statusBarColor = ColorUtils.blendARGB(color, colorEnd, ratio);
                StatusBarUtils.setStatusColor(TestStatusActivity.this,statusBarColor,cb.isChecked());
//                setStatusColor(statusBarColor);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        cb = findViewById(R.id.cb);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shenRu(isChecked);
            }
        });
    }

    private void showDialog() {
        Dialog dialog=new Dialog(this);
        View inflate = getLayoutInflater().inflate(R.layout.dialog_confit, null);
        dialog.setContentView(inflate);
        dialog.show();
    }

    private void setStatusColor(int statusBarColor) {
        Window window = getWindow();
        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !StatusBarUtils.isEmotionUI3()) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(statusBarColor);
        }
    }

    private void shenRu(boolean isShenRu) {
        StatusBarUtils.setStatusColor(this,statusBarColor,isShenRu);
        if(true){
            return;
        }
        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        if (isShenRu) {
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        } else {
            systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        }
        decorView.setSystemUiVisibility(systemUiVisibility);
    }


}
