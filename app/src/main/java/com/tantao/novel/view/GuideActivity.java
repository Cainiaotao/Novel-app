package com.tantao.novel.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tantao.novel.R;
import com.tantao.novel.base.BaseActivity;
import com.tantao.novel.view.main.MainActivity;

public class GuideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initDate();
    }

    private void initDate() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        },3000);
    }
}
