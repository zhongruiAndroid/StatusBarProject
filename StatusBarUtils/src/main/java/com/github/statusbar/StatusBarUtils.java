package com.github.statusbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Method;

public class StatusBarUtils {
    private static boolean isNull(Object object) {
        return object == null;
    }

    public static void setStatusColor(Activity activity, @ColorInt int color) {
        setStatusColor(activity, color, 0);
    }

    public static void setStatusColor(Activity activity, @ColorInt int color, @FloatRange(from = 0.0D, to = 1.0D) float ratio) {
        setStatusColor(activity, color, Color.BLACK, ratio);
    }

    public static void setStatusColor(Activity activity, @ColorInt int color, @ColorInt int colorEnd, @FloatRange(from = 0.0D, to = 1.0D) float ratio) {
        if (isNull(activity)) {
            return;
        }
        int statusBarColor = ColorUtils.blendARGB(color, colorEnd, ratio);
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !isEmotionUI3()) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(statusBarColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            if (decorView == null) {
                return;
            }
            ViewGroup contentView = decorView.findViewById(android.R.id.content);
            if (contentView == null) {
                return;
            }
            View childAt = contentView.getChildAt(0);
            if (childAt != null) {
                childAt.setFitsSystemWindows(true);
            }
            View statusBarView = decorView.findViewById(R.id.status_bar_view_id);
            if (statusBarView == null) {
                statusBarView = createStatusBarView(activity, statusBarColor);
                decorView.addView(statusBarView, 0);
            } else {
                statusBarView.setBackgroundColor(statusBarColor);
            }
        }
    }

    public static void setIntoStatusBar(Activity activity) {
        setIntoStatusBar(activity, true);
    }

    public static void setIntoStatusBar(Activity activity, boolean isIntoStatusBar) {
        setIntoStatusBar(activity, Color.TRANSPARENT, isIntoStatusBar);
    }

    public static void setIntoStatusBar(Activity activity, @ColorInt int color) {
        setIntoStatusBar(activity, color, 0, true);
    }
    public static void setIntoStatusBar(Activity activity, @ColorInt int color, boolean isIntoStatusBar) {
        setIntoStatusBar(activity, color, 0, isIntoStatusBar);
    }
    public static void setIntoStatusBar(Activity activity, @ColorInt int color, @FloatRange(from = 0.0D, to = 1.0D) float ratio ) {
        setIntoStatusBar(activity, color, Color.BLACK, ratio,true);
    }
    public static void setIntoStatusBar(Activity activity, @ColorInt int color, @FloatRange(from = 0.0D, to = 1.0D) float ratio, boolean isIntoStatusBar) {
        setIntoStatusBar(activity, color, Color.BLACK, ratio, isIntoStatusBar);
    }
    public static void setIntoStatusBar(Activity activity, @ColorInt int color, @ColorInt int colorEnd, @FloatRange(from = 0.0D, to = 1.0D) float ratio) {
        setIntoStatusBar(activity,color,colorEnd,ratio,true);
    }
    public static void setIntoStatusBar(Activity activity, @ColorInt int color, @ColorInt int colorEnd, @FloatRange(from = 0.0D, to = 1.0D) float ratio, boolean isIntoStatusBar) {
        if (isNull(activity)) {
            return;
        }
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        if (decorView == null) {
            return;
        }
        int statusBarColor = ColorUtils.blendARGB(color, colorEnd, ratio);
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !isEmotionUI3()) {
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            int systemUiVisibility = decorView.getSystemUiVisibility();
            if (isIntoStatusBar) {
                systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            } else {
                systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            }
            decorView.setSystemUiVisibility(systemUiVisibility);
            window.setStatusBarColor(statusBarColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            View statusBarView = decorView.findViewById(R.id.status_bar_view_id);
            if (statusBarView == null) {
                statusBarView = createStatusBarView(activity, statusBarColor);
                decorView.addView(statusBarView, 0);
            } else {
                statusBarView.setBackgroundColor(statusBarColor);
            }
        }
    }

    @Deprecated
    public static void setStatusColorForDrawerLayout(Activity activity, DrawerLayout drawerLayout, @ColorInt int color) {
        setStatusColorForDrawerLayout(activity, drawerLayout, color, Color.BLACK, 0);
    }

    @Deprecated
    public static void setStatusColorForDrawerLayout(Activity activity, DrawerLayout drawerLayout, @ColorInt int color, @FloatRange(from = 0.0D, to = 1.0D) float ratio) {
        setStatusColorForDrawerLayout(activity, drawerLayout, color, Color.BLACK, ratio);
    }

    @Deprecated
    public static void setStatusColorForDrawerLayout(Activity activity, DrawerLayout drawerLayout, @ColorInt int color, @ColorInt int colorEnd, @FloatRange(from = 0.0D, to = 1.0D) float ratio) {
        if (isNull(activity)) {
            return;
        }
        Window window = activity.getWindow();
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        if (decorView == null) {
            return;
        }
        int statusBarColor = ColorUtils.blendARGB(color, colorEnd, ratio);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !isEmotionUI3()) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        int statusBarHeight = getStatusBarHeight(activity);
        for (int i = 0; i < drawerLayout.getChildCount(); i++) {
            if (i == 0) {
                View childAt = drawerLayout.getChildAt(0);
                drawerLayout.removeView(childAt);
                View parentView = drawerLayout.findViewById(R.id.status_bar_view_parent_id);
                LinearLayout linearLayout = null;
                if (parentView == null) {
                    linearLayout = new LinearLayout(activity);
                    linearLayout.setId(R.id.status_bar_view_parent_id);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    linearLayout.addView(childAt);
                    drawerLayout.addView(linearLayout, 0);
                }
                View statusBarView = drawerLayout.findViewById(R.id.status_bar_view_id);
                if (statusBarView == null) {
                    statusBarView = createStatusBarView(activity, statusBarColor);
                    linearLayout.addView(statusBarView, 0);
                } else {
                    statusBarView.setBackgroundColor(statusBarColor);
                }
                continue;
            }
            View layoutChildAt = drawerLayout.getChildAt(i);
            layoutChildAt.setPadding(layoutChildAt.getPaddingLeft(), statusBarHeight + layoutChildAt.getPaddingTop(), layoutChildAt.getPaddingRight(), layoutChildAt.getPaddingBottom());
        }
    }

    private static View createStatusBarView(Activity activity, @ColorInt int statusBarColor) {
        View view = new View(activity);
        view.setId(R.id.status_bar_view_id);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity)));
        view.setBackgroundColor(statusBarColor);
        return view;
    }

    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    private static boolean isEMUI3 = false;

    public static boolean isEmotionUI3() {
        if (isEMUI3) {
            return isEMUI3;
        }
        String property = getSystemProperty("ro.build.version.emui", "");
        if ("EmotionUI 3".equals(property) || property.contains("EmotionUI_3.1")) {
            isEMUI3 = true;
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
