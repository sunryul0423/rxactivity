package srpark.rxactivity2.activity.permission;

public class PermissionResult {

    private final boolean isGranted;
    private final String permission;

    PermissionResult(boolean isGranted, String permission) {
        this.isGranted = isGranted;
        this.permission = permission;
    }

    public boolean isGranted() {
        return isGranted;
    }

    public String getPermission() {
        return permission;
    }
}
