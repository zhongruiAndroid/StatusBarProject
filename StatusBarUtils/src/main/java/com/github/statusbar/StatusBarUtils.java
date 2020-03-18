package com.github.statusbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.v4.graphics.ColorUtils;
import android.view.Window;
import android.view.WindowManager;

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
        if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.KITKAT){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }else{

        }
    }

}
