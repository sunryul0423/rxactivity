package srpark.rxactivity2.activity.keyboard;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public abstract class SimpleActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private Activity mActivity;

    public SimpleActivityLifecycleCallbacks(Activity activity) {
        mActivity = activity;
    }

    public abstract void onActivityDestroyed();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (mActivity == activity) {
            mActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
            onActivityDestroyed();
        }
    }
}
