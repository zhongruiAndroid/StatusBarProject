package com.test.statusbarproject.systembar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import java.lang.reflect.Method;

/**
 * StatusBar Utils handle with special FlymeOS4.x/Android4.4.4
 * (状态栏工具,处理魅族FlymeOS4.x/Android4.4.4)
 */
public class StatusBarUtils {
    public static int getNotchHeight(Context context) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("notch_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        if (isFlymeOs4x()) {
            return 2 * statusBarHeight;
        }

        return statusBarHeight;
    }

    public static int getNotchWidth(Context context) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("notch_width", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        if (isFlymeOs4x()) {
            return 2 * statusBarHeight;
        }

        return statusBarHeight;
    }


    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
            }
            if (isFlymeOs4x()) {
                return 2 * statusBarHeight;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return statusBarHeight;
    }

    public static boolean isFlymeOs4x() {
        String sysVersion = Build.VERSION.RELEASE;
        if ("4.4.4".equals(sysVersion)) {
            String sysIncrement = Build.VERSION.INCREMENTAL;
            String displayId = Build.DISPLAY;
            if (!TextUtils.isEmpty(sysIncrement)) {
                return sysIncrement.contains("Flyme_OS_4");
            } else {
                return displayId.contains("Flyme OS 4");
            }
        }
        return false;
    }

    public static int getNavBarHeight(Context context) {
        int navigationBarHeight = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Resources rs = context.getResources();
            int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
            if (id > 0 && checkDeviceHasNavigationBar(context)) {
                navigationBarHeight = rs.getDimensionPixelSize(id);
            }
        }
        return navigationBarHeight;
    }

    private static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hasNavigationBar;

    }

    private static final int PORTRAIT = 0;
    private static final int LANDSCAPE = 1;
    private volatile static Point[] mRealSizes = new Point[2];


    public static int getScreenRealHeight(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return getScreenHeight(context);
        }

        int orientation = context != null
                ? context.getResources().getConfiguration().orientation
                : context.getResources().getConfiguration().orientation;
        orientation = orientation == Configuration.ORIENTATION_PORTRAIT ? PORTRAIT : LANDSCAPE;

        if (mRealSizes[orientation] == null) {
            WindowManager windowManager = context != null
                    ? (WindowManager) context.getSystemService(Context.WINDOW_SERVICE)
                    : (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager == null) {
                return getScreenHeight(context);
            }
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getRealSize(point);
            mRealSizes[orientation] = point;
        }
        return mRealSizes[orientation].y;
    }

    public static int getScreenRealWidth(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return getScreenWidth(context);
        }

        int orientation = context != null
                ? context.getResources().getConfiguration().orientation
                : context.getResources().getConfiguration().orientation;
        orientation = orientation == Configuration.ORIENTATION_PORTRAIT ? PORTRAIT : LANDSCAPE;

        if (mRealSizes[orientation] == null) {
            WindowManager windowManager = context != null
                    ? (WindowManager) context.getSystemService(Context.WINDOW_SERVICE)
                    : (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager == null) {
                return getScreenWidth(context);
            }
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getRealSize(point);
            mRealSizes[orientation] = point;
        }
        return mRealSizes[orientation].x;
    }

    public static int getScreenHeight(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }
        return 0;
    }

    public static int getScreenWidth(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
        return 0;
    }

    private volatile static boolean mHasCheckAllScreen;
    private volatile static boolean mIsAllScreenDevice;

    public static boolean isAllScreenDevice(Context context) {
        if (mHasCheckAllScreen || context == null) {
            return mIsAllScreenDevice;
        }
        mHasCheckAllScreen = true;
        mIsAllScreenDevice = false;
        // 低于 API 21的，都不会是全面屏。。。
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return false;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getRealSize(point);
            float width, height;
            if (point.x < point.y) {
                width = point.x;
                height = point.y;
            } else {
                width = point.y;
                height = point.x;
            }
            if (height / width >= 1.97f) {
                mIsAllScreenDevice = true;
            }
        }
        return mIsAllScreenDevice;
    }

    public static void hideStatusBarAndNavBar(Window window) {

        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = window.getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = window.getDecorView();
            int uiOptions
                    = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public static void hideNavBar(Window window) {

        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = window.getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = window.getDecorView();
            int uiOptions
                    = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    ;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


}
