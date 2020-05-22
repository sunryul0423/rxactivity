package srpark.rxactivity2.activity.result;

import android.content.Intent;

public class ActivityResult {
    private final int resultCode;
    private final Intent data;

    ActivityResult(int resultCode, Intent intent) {
        this.resultCode = resultCode;
        this.data = intent;
    }

    public int getResultCode() {
        return resultCode;
    }

    public Intent getData() {
        return data;
    }
}
