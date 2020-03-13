package com.github.statusbar;

import android.app.Activity;
import android.view.WindowManager;

public class StatusBarUtils {
    private static boolean isNull(Object object){
        return object==null;
    }
    public static void get(Activity activity){
        if(isNull(activity)){
            return;
        }
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
}
