package srpark.rxactivity2.activity.result;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import srpark.rxactivity2.OnResultListener;

import java.util.Random;

public class RxResultActivity extends Activity {

    private static OnResultListener mResultListener;
    private static Intent mIntent;

    public static void getInstance(@NonNull Context context, @NonNull Intent intent, OnResultListener resultListener) {
        mIntent = intent;
        mResultListener = resultListener;
        Intent tempIntent = new Intent(context, RxResultActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(tempIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivityForResult(mIntent, new Random().nextInt(Character.MAX_VALUE) + 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResult result = new ActivityResult(resultCode, data);
        mResultListener.onActivityResult(result);
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
