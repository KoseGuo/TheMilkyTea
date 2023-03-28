package com.kose.milkytea.ui;

import android.content.Intent;
import android.os.Handler;

import com.kose.milkytea.R;
import com.kose.milkytea.databinding.ActivityLauncherBinding;
import com.kose.milkytea.ui.base.BaseActivity;
import com.kose.milkytea.ui.home.MainActivity;

public class LauncherActivity extends BaseActivity<ActivityLauncherBinding> {

    @Override
    protected void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }
}