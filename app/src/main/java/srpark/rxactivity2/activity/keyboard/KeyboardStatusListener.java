package srpark.rxactivity2.activity.keyboard;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewTreeObserver;

import srpark.rxactivity2.OnResultListener;


public class KeyboardStatusListener implements ViewTreeObserver.OnGlobalLayoutListener {

    // 키보드 Size 가정
    private static final int INVISIBLE_VIEW_SIZE_BY_PX = 200;
    private final View rootView;
    private boolean isOpen;
    private OnResultListener listener;


    public KeyboardStatusListener(View rootView, OnResultListener listener) {
        this.rootView = rootView;
        this.listener = listener;
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        int rootViewHeight = rootView.getHeight();
        Resources resources = rootView.getResources();

        // 네비게이션 Bar
        int navigationBarSize = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (navigationBarSize > 0) {
            rootViewHeight += resources.getDimensionPixelSize(navigationBarSize);
        }
        // 상태 Bar
        int statusBarSize = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (statusBarSize > 0) {
            rootViewHeight += resources.getDimensionPixelSize(statusBarSize);
        }

        int heightDiff = rootView.getRootView().getHeight() - rootViewHeight;
        boolean isOpen = heightDiff > INVISIBLE_VIEW_SIZE_BY_PX;

        if (this.isOpen != isOpen) {
            this.isOpen = isOpen;
            listener.onKeyboardChange(isOpen);
        }
    }

    public void clearListener() {
        this.listener = null;
        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }
}
