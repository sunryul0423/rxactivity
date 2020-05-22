package srpark.rxactivity2.activity.keyboard;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import srpark.rxactivity2.OnResultListener;

public class RxKeyboard {

    private final Activity activity;
    private final BehaviorSubject<Boolean> behaviorSubject = BehaviorSubject.create();

    private RxKeyboard(Activity activity) {
        this.activity = activity;
    }

    public static RxKeyboard from(@NonNull Activity activity) {
        return new RxKeyboard(activity);
    }

    public Observable<Boolean> keyboardStatus(View rootView) {
        OnResultListener resultListener = new OnResultListener() {
            @Override
            public void onKeyboardChange(boolean isShow) {
                behaviorSubject.onNext(isShow);
            }
        };
        KeyboardStatusListener keyboardStatusListener = new KeyboardStatusListener(rootView, resultListener);
        activity.getApplication().registerActivityLifecycleCallbacks(new SimpleActivityLifecycleCallbacks(activity) {

            @Override
            public void onActivityDestroyed() {
                keyboardStatusListener.clearListener();
                if (behaviorSubject.hasObservers()) {
                    behaviorSubject.onComplete();
                }
            }
        });

        return behaviorSubject;
    }
}