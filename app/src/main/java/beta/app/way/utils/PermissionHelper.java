package beta.app.way.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by panjiyudasetya on 12/16/16.
 */

public class PermissionHelper {
    /** The {@link Activity} */
    private Activity mActivity;

    /** The {@link List} of permissions */
    private List<String> mPermissions;

    /** The {@link List} of permission information */
    private List<String> mPermissionInfos;

    /** The {@link List} of denied permissions */
    private List<String> mDeniedPermissions;

    /** The {@link List} of denied permission information */
    private List<String> mDeniedPermissionInfos;

    /** The properties {@Link DialogProps} of request permission dialog */
    private DialogProps mProps;

    /** Permission dialog properties */
    public static class DialogProps {
        int style;
        String title;
        String positiveText;
        String negativeText;

        public DialogProps(int style, @NonNull String title, @NonNull String positiveText, @NonNull String negativeText) {
            this.style = style;
            this.title = title;
            this.positiveText = positiveText;
            this.negativeText = negativeText;
        }
    }

    /** The constructor for this class */
    public PermissionHelper(@NonNull Activity activity, @NonNull DialogProps props) {
        mActivity = activity;
        mPermissions = null;
        mPermissionInfos = null;
    }

    /** Get the permissions for this class */
    public final List<String> getPermissions() {
        return mPermissions;
    }

    /** Set the permissions for this class */
    public final void setPermissions(String[] permissions) {
        mPermissions = Arrays.asList(permissions);
    }

    /** Set the permissions for this class */
    public final void setPermissions(List<String> permissions) {
        mPermissions = permissions;
    }

    /** Set the permissions for this class */
    public final void setPermissions(String[] permissions, String[] permissionInfos) {
        mPermissions = Arrays.asList(permissions);
        mPermissionInfos = Arrays.asList(permissionInfos);
    }

    /** Set the permissions for this class */
    public final void setPermissions(List<String> permissions, List<String> permissionInfos) {
        mPermissions = permissions;
        mPermissionInfos = permissionInfos;
    }

    /** Request all permission access */
    public final void requestAllPermission(int requestCode) {
        if (mDeniedPermissions == null) throw new IllegalStateException("Denied permission list is null, make sure you have called #isAllPermissionAllowed() before");
        else {
            if (mDeniedPermissionInfos != null) {
                if (mDeniedPermissions.size() == 1) {
                    requestPermission(mDeniedPermissions.get(0), mDeniedPermissionInfos.get(0), requestCode);
                } else {
                    // TODO: 7/21/2016 Implement multiple information for each permissions
                    requestPermission(mDeniedPermissions.toArray(new String[mDeniedPermissions.size()]), requestCode);
                }
            } else {
                if (mDeniedPermissions.size() == 1) {
                    requestPermission(mDeniedPermissions.get(0), requestCode);
                } else {
                    requestPermission(mDeniedPermissions.toArray(new String[mDeniedPermissions.size()]), requestCode);
                }
            }
        }
    }

    /**
     * Determines if app has full permission access.
     * @return true if <strong>ALL</strong> permissions are allowed, otherwise false
     */
    public final boolean isAllPermissionAllowed() {
        if (mPermissions == null) throw new IllegalStateException("Permission list is null, make sure the List of permissions has been set");
        else {
            boolean isAllPermissionAllowed = true;

            mDeniedPermissions = new ArrayList<>();
            mDeniedPermissionInfos = new ArrayList<>();

            int size = mPermissions.size();
            for (int i = 0; i < size; i++) {
                if (!hasPermission(mPermissions.get(i))) {
                    isAllPermissionAllowed = false;

                    if (!mDeniedPermissions.contains(mPermissions.get(i))) mDeniedPermissions.add(mPermissions.get(i));
                    if (mPermissionInfos != null) {
                        if (mPermissions.size() != mPermissionInfos.size()) throw new IllegalStateException("Permission list information has a different size as the permissions");
                        else if (!mDeniedPermissionInfos.contains(mPermissionInfos.get(i))) mDeniedPermissionInfos.add(mPermissionInfos.get(i));
                    }
                }
            }

            return isAllPermissionAllowed;
        }
    }

    /**
     * Checks if application has a specified permission.
     * @param permission the specified permission
     * @return true if permission is granted
     */
    private boolean hasPermission(@NonNull String permission) {
        return (ActivityCompat.checkSelfPermission(mActivity, permission) == PackageManager.PERMISSION_GRANTED);
    }

    /**
     * Checks if application has the specified permissions.
     * @param permissions the permission list as an array
     * @return true if <strong>ALL</strong> permissions are granted
     */
    private boolean hasPermission(@NonNull String[] permissions) {
        boolean isAllGranted = true;

        int size = permissions.length;
        String permission;
        for (int i = 0; i < size; i++) {
            permission = permissions[i];
            if (!hasPermission(permission)) {
                if (isAllGranted) isAllGranted = false;
            }
        }

        return isAllGranted;
    }

    /**
     * Request a permission access.
     * @param permission the specified permission
     * @param requestCode the request code
     */
    private void requestPermission(@NonNull String permission, int requestCode) {
        requestPermission(permission, null, requestCode);
    }

    /**
     * Request a permission access while displaying an information.
     * @param permission the specified permission
     * @param permissionInfo the information for the specified permission
     * @param requestCode the request code
     */
    private void requestPermission(@NonNull String permission, @Nullable String permissionInfo, int requestCode) {
        if (!hasPermission(permission)) {
            if (permissionInfo != null && !permissionInfo.isEmpty()) {
                // Check if app has requested this permission previously and the user denied the request
                if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission)) {
                    // Display the explanation
                    showPemissionDetailDialog(permission, permissionInfo, requestCode);
                } else {
                    // Don't display any explanation, request immediately
                    ActivityCompat.requestPermissions(
                            mActivity,
                            new String[] { permission },
                            requestCode);
                }
            } else {
                // Don't display any explanation, request immediately
                ActivityCompat.requestPermissions(
                        mActivity,
                        new String[] { permission },
                        requestCode);
            }
        }
    }

    /**
     * Request permissions access.
     * @param permissions the permission list as an array
     * @param requestCode the request code
     */
    private void requestPermission(@NonNull String[] permissions, int requestCode) {
        List<String> requiredPermissions = new ArrayList<>();

        int size = permissions.length;
        String permission;
        for (int i = 0; i < size; i++) {
            permission = permissions[i];
            if (!hasPermission(permission)) requiredPermissions.add(permission);
        }

        if (requiredPermissions.size() > 0) {
            ActivityCompat.requestPermissions(
                    mActivity,
                    requiredPermissions.toArray(new String[requiredPermissions.size()]),
                    requestCode);
        }
    }

    /**
     * Display a permission information dialog
     */
    private void showPemissionDetailDialog(@NonNull final String permission, @NonNull String message, final int requestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity, mProps.style);
        builder.setTitle(mProps.title);
        builder.setMessage(message);
        builder.setPositiveButton(mProps.positiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                // Request for the permission immediately, don't show the message again
                requestPermission(permission, requestCode);
            }
        });
        builder.setNegativeButton(mProps.negativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // Send a result for Marshmallow and above
                    mActivity.onRequestPermissionsResult(requestCode, new String[]{ permission }, new int[0]);
                }
            }
        });
        builder.show();
    }
}
