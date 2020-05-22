package srpark.rxactivity2;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import srpark.rxactivity2.activity.permission.PermissionResult;
import srpark.rxactivity2.activity.permission.RxPermissionActivity;
import srpark.rxactivity2.activity.result.ActivityResult;
import srpark.rxactivity2.activity.result.RxResultActivity;

public class RxActivity {

    private final Context context;
    private final PublishSubject<ActivityResult> resultSubject = PublishSubject.create();
    private final PublishSubject<PermissionResult> permissionSubject = PublishSubject.create();

    private RxActivity(Context context) {
        this.context = context;
    }

    public static RxActivity from(@NonNull Context context) {
        return new RxActivity(context);
    }

    public Observable<ActivityResult> activityForResult(@NonNull final Intent intent) {
        OnResultListener resultListener = new OnResultListener() {
            @Override
            public void onActivityResult(ActivityResult result) {
                resultSubject.onNext(result);
                resultSubject.onComplete();
            }
        };
        RxResultActivity.getInstance(context, intent, resultListener);
        return resultSubject;
    }

    public Observable<PermissionResult> request(@NonNull final String[] permissions) {
        OnResultListener resultListener = new OnResultListener() {
            @Override
            public void onPermissionResult(PermissionResult result) {
                permissionSubject.onNext(result);
                permissionSubject.onComplete();
            }
        };
        RxPermissionActivity.getInstance(context, permissions, resultListener);
        return permissionSubject;
    }
}