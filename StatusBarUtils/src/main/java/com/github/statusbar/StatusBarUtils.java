package com.github.statusbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.v4.graphics.ColorUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Method;

public class StatusBarUtils {
    private static boolean isNull(Object object){
        return object==null;
    }

    public static void setStatusColor(Activity activity, @ColorInt int color){
        setStatusColor(activity,color,0);
    }
    public static void setStatusColor(Activity activity, @ColorInt int color,@FloatRange(from = 0.0D,to = 1.0D) float ratio){
        if(isNull(activity)){
            return;
        }
        int statusBarColor=ColorUtils.blendARGB(color,Color.BLACK,ratio);
        Window window = activity.getWindow();
        if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.KITKAT_WATCH||isEmotionUI3()){
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            if(decorView==null){
                return;
            }
            ViewGroup contentView = decorView.findViewById(android.R.id.content);
            if(contentView==null){
                return;
            }
            View childAt = contentView.getChildAt(0);
            if(childAt!=null){
                childAt.setFitsSystemWindows(true);
            }
            View beforeView = decorView.findViewById(R.id.status_bar_view_id);
            if(beforeView==null){
                View statusBarView = createStatusBarView(activity);
                statusBarView.setBackgroundColor(statusBarColor);
                decorView.addView(statusBarView,0);
            }else{
                beforeView.setBackgroundColor(statusBarColor);
            }
        }else{
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(statusBarColor);
        }
    }

    private static View createStatusBarView(Activity activity){
        View view=new View(activity);
        view.setId(R.id.status_bar_view_id);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getStatusBarHeight(activity)));
        return view;
    }
    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    private static boolean isEMUI3=false;
    private static boolean isEmotionUI3(){
        if(isEMUI3){
            return isEMUI3;
        }
        String property = getSystemProperty("ro.build.version.emui", "");
        if ("EmotionUI 3".equals(property) || property.contains("EmotionUI_3.1")) {
            isEMUI3=true;
            return true;
        }
        return false;
    }
    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            get.setAccessible(true);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
