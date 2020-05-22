package srpark.rxactivity2.activity.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import srpark.rxactivity2.OnResultListener;

import java.util.Random;

public class RxPermissionActivity extends Activity {

    private static OnResultListener mResultListener;
    private static String[] mPermissions;

    public static void getInstance(Context context, @NonNull String[] permissions, OnResultListener resultListener) {
        mPermissions = permissions;
        mResultListener = resultListener;
        Intent tempIntent = new Intent(context, RxPermissionActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(tempIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyPermission();
    }

    private void verifyPermission() {
        boolean isGranted = true;
        for (String results : mPermissions) {
            final int permission = ContextCompat.checkSelfPermission(this, results);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
                break;
            }
        }
        if (isGranted) {
            PermissionResult result = new PermissionResult(true, "");
            mResultListener.onPermissionResult(result);
            finish();
        } else {
            ActivityCompat.requestPermissions(this, mPermissions, new Random().nextInt(Character.MAX_VALUE) + 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isGranted = true;
        String grant = "";
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
                grant = permissions[i];
                break;
            }
        }
        PermissionResult result;
        if (isGranted) {
            result = new PermissionResult(true, "");
        } else {
            result = new PermissionResult(false, grant);
        }
        mResultListener.onPermissionResult(result);
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
