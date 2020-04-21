package me.xujichang.lib.permissions;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Info:for LiveDataPermissions  in me.xujichang.lib.permissions.LiveFragment
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/2/26 11:03 PM
 * @since 1.0.0
 */
public class LiveFragment extends Fragment {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private MutableLiveData<PermissionResult> mLiveData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public LiveData<PermissionResult> requestPermissions(String[] array) {
        mLiveData = new MutableLiveData<>();
        requestPermissions(array, PERMISSION_REQUEST_CODE);
        return mLiveData;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            List<String> deniedPermissions = new ArrayList<>();
            List<String> rationalPermissions = new ArrayList<>();
            for (int vIndex = 0; vIndex < permissions.length; vIndex++) {
                if (grantResults[vIndex] == PackageManager.PERMISSION_DENIED) {
                    String permission = permissions[vIndex];
                    if (shouldShowRequestPermissionRationale(permission)) {
                        rationalPermissions.add(permission);
                    } else {
                        deniedPermissions.add(permission);
                    }
                }
            }
            PermissionResult vResult;
            if (!rationalPermissions.isEmpty()) {
                vResult = new PermissionResult(rationalPermissions, PermissionResult.Type.RATIONAL);
            } else if (!deniedPermissions.isEmpty()) {
                vResult = new PermissionResult(deniedPermissions, PermissionResult.Type.DENIED);
            } else {
                vResult = new PermissionResult(Arrays.asList(permissions), PermissionResult.Type.ACCEPT);
            }
            mLiveData.setValue(vResult);
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public MutableLiveData<PermissionResult> getLiveData() {
        return mLiveData;
    }
}
