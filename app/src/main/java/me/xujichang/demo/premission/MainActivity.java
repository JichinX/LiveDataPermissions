package me.xujichang.demo.premission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import me.xujichang.lib.permissions.LivePermissions;
import me.xujichang.lib.permissions.PermissionResult;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LivePermissions(this)
                .requestPermissions(new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.ACCESS_FINE_LOCATION})
                .observe(this, new Observer<PermissionResult>() {
                    @Override
                    public void onChanged(PermissionResult pResult) {
                        switch (pResult.getType()) {
                            case RATIONAL:
                                Log.i(TAG, "onChanged: 权限可再次申请->" + pResult.getList());
                                break;
                            case ACCEPT:
                            default:
                                Log.i(TAG, "onChanged: 权限获取成功->" + pResult.getList());
                                break;
                            case DENIED:
                                Log.i(TAG, "onChanged: 权限被拒绝->" + pResult.getList());
                                break;
                        }
                    }
                });
    }
}
