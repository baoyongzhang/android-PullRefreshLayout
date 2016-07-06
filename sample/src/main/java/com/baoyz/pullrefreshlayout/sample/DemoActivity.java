package com.baoyz.pullrefreshlayout.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baoyz.pullrefreshlayout.sample.gank.GankActivity;

public class DemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

    }

    public void onListViewClick(View view) {
        startActivity(new Intent(this, ListViewActivity.class));
    }

    public void onRecyclerViewClick(View view) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    public void onScrollViewClick(View view) {
        startActivity(new Intent(this, ScrollViewActivity.class));
    }

    public void onGankDemoClick(View view) {
        // Content from http://gank.io
        startActivity(new Intent(this, GankActivity.class));
    }

}
