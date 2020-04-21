package me.xujichang.lib.permissions;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.jar.Attributes;

/**
 * Info:for LiveDataPermissions  in me.xujichang.lib.permissions.LivePermissions
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/2/26 11:02 PM
 * @since 1.0.0
 */
public class LivePermissions {
    private static final String TAG = "PERMISSIONS";
    private LiveFragment mLiveFragment;

    public LivePermissions(AppCompatActivity pActivity) {
        mLiveFragment = getInstance(pActivity.getSupportFragmentManager());
    }

    public LivePermissions(Fragment pFragment) {
        mLiveFragment = getInstance(pFragment.getChildFragmentManager());
    }


    private LiveFragment getInstance(FragmentManager pManager) {
        if (null == mLiveFragment) {
            synchronized (this) {
                if (null == mLiveFragment) {
                    Fragment vFragment = pManager.findFragmentByTag(TAG);
                    if (null == vFragment) {
                        mLiveFragment = new LiveFragment();
                        pManager.beginTransaction().add(mLiveFragment, TAG).commitNow();
                    } else {
                        mLiveFragment = (LiveFragment) vFragment;
                    }
                }
            }
        }
        return mLiveFragment;
    }

    public LiveData<PermissionResult> requestPermissions(List<String> pList) {
        String[] array = new String[pList.size()];
        pList.toArray(array);
        return requestPermissions(array);
    }

    public LiveData<PermissionResult> requestPermissions(String[] array) {
        return mLiveFragment.requestPermissions(array);
    }
}
