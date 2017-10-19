package com.tantao.novel.view.search;

import android.os.Bundle;

import com.tantao.novel.R;
import com.tantao.novel.base.BaseActivity;

public class SearcActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
    }
}
