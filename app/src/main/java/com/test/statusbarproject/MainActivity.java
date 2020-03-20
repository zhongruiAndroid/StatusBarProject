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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.setStatusColor(this, ContextCompat.getColor(this, R.color.colorAccent));
        initView();

        TextView tv=findViewById(R.id.tv);
        ViewGroup.LayoutParams layoutParams = tv.getLayoutParams();
        layoutParams.height=StatusBarUtils.getStatusBarHeight(this);
        tv.setLayoutParams(layoutParams);
    }

    private void setBarColor( @FloatRange(from = 0.0D,to = 1.0D) float ratio){
        StatusBarUtils.setStatusColor(this, ContextCompat.getColor(this, R.color.c_ADECE5),ratio);
    }

    private void initView() {
        Button btTest=findViewById(R.id.btTest);
        btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(TestActivity.class);
            }
        });
        Button btTestDrawerLayout=findViewById(R.id.btTestDrawerLayout);
        btTestDrawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(DrawLayoutActivity.class);
            }
        });
    }
    private void showDialog() {
        final Dialog dialog=new Dialog(this);
        TextView textView = new TextView(this);
        textView.setText("TextView");
        dialog.setContentView(textView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Context context = dialog.getContext();
                Activity activity = findActivity(context);
                if(activity==null||activity.isFinishing()){
                }else{
                    dialog.show();
                }
            }
        },1000);
    }
    public static Activity findActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            ContextWrapper wrapper = (ContextWrapper) context;
            return findActivity(wrapper.getBaseContext());
        } else {
            return null;
        }
    }
    private void startAct(Class clazz){
        startActivity(new Intent(this,clazz));
    }
}
