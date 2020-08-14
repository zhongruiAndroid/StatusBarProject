package com.test.statusbarproject.systembar;

import android.database.ContentObserver;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.view.View;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 沉浸式参数信息
 */
public class BarParams implements Cloneable {
    /**
     * 状态栏颜色
     */
    @ColorInt
    public int statusBarColor = Color.TRANSPARENT;
    /**
     * 导航栏颜色
     */
    @ColorInt
    public int navigationBarColor = Color.BLACK;
    /**
     * 状态栏透明度
     */
    @FloatRange(from = 0f, to = 1f)
    public float statusBarAlpha = 0.0f;
    /**
     * 导航栏透明度
     */
    @FloatRange(from = 0f, to = 1f)
    float navigationBarAlpha = 0.0f;
    /**
     * 有导航栏的情况，全屏显示
     */
    public boolean fullScreen = false;
    public boolean fullScreenTemp = fullScreen;
    /**
     * 隐藏Bar
     */
    public BarHide barHide = BarHide.FLAG_SHOW_BAR;
    /**
     * 状态栏字体深色与亮色标志位
     */
    public boolean darkFont = false;
    /**
     * 是否可以修改状态栏颜色
     */
    public boolean statusBarFlag = true;
    /**
     * 状态栏变换后的颜色
     */
    @ColorInt
    public int statusBarColorTransform = Color.BLACK;
    /**
     * 导航栏变换后的颜色
     */
    @ColorInt
    public int navigationBarColorTransform = Color.BLACK;
    /**
     * 支持view变色
     */
    public Map<View, Map<Integer, Integer>> viewMap = new HashMap<>();
    @FloatRange(from = 0f, to = 1f)
    public float viewAlpha = 0.0f;
    /**
     * 解决标题栏与状态栏重叠问题
     */
    public boolean fits = false;
    @ColorInt
    public int statusBarColorContentView = Color.TRANSPARENT;
    @ColorInt
    public int statusBarColorContentViewTransform = Color.BLACK;
    @FloatRange(from = 0f, to = 1f)
    public float statusBarContentViewAlpha = 0.0f;
    public int navigationBarColorTemp = navigationBarColor;
    /**
     * 4.4自定义一个状态栏
     */
    public View statusBarView;
    /**
     * 4.4自定义一个导航栏
     */
    public View navigationBarView;
    /**
     * 解决标题栏与状态栏重叠问题
     */
    public View statusBarViewByHeight;
    /**
     * flymeOS状态栏字体变色
     */
    @ColorInt
    public int flymeOSStatusBarFontColor;
    /**
     * 结合actionBar使用
     */
    public boolean isSupportActionBar = false;
    /**
     * 标题栏view
     */
    public View titleBarView;
    /**
     * 标题栏的高度
     */
    public int titleBarHeight;
    /**
     * 标题栏的paddingTop高度
     */
    public int titleBarPaddingTopHeight;
    /**
     * 使用margin来修正标题栏位置
     */
    public View titleBarViewMarginTop;
    /**
     * 标题栏标识，保证只执行一次
     */
    public boolean titleBarViewMarginTopFlag = false;
    /**
     * 解决软键盘与输入框冲突问题
     */
    public boolean keyboardEnable = false;
    /**
     * 软键盘属性
     */
    public int keyboardMode
            = WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
            | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
    /**
     * 是否能修改导航栏颜色
     */
    public boolean navigationBarEnable = true;
    /**
     * 是否能修改4.4手机导航栏颜色
     */
    public boolean navigationBarWithKitkatEnable = true;
    /**
     * 解决出现底部多余导航栏高度，默认为false
     */
    @Deprecated
    public boolean fixMarginAtBottom = false;
    /**
     * 也没是否使用fitsSystemWindows属性
     */
    public boolean systemWindows = false;
    /**
     * 软键盘监听类
     */
    public KeyboardPatch keyboardPatch;
    /**
     * 软键盘监听类
     */
    public OnKeyboardListener onKeyboardListener;
    /**
     * emui3.1监听器
     */
    public ContentObserver navigationStatusObserver;

    @Override
    protected BarParams clone() {
        BarParams barParams = null;
        try {
            barParams = (BarParams) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return barParams;
    }
}
