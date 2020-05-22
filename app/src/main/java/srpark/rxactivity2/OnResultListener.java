package srpark.rxactivity2;

import srpark.rxactivity2.activity.permission.PermissionResult;
import srpark.rxactivity2.activity.result.ActivityResult;

public abstract class OnResultListener {
    public void onActivityResult(ActivityResult result) {
    }

    public void onPermissionResult(PermissionResult result) {
    }

    public void onKeyboardChange(boolean isShow) {
    }
}
