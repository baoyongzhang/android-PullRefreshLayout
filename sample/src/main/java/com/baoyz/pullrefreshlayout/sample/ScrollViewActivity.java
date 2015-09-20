package com.baoyz.pullrefreshlayout.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.baoyz.widget.PullRefreshLayout;
import com.baoyz.widget.SmartisanDrawable;

public class ScrollViewActivity extends Activity {

    PullRefreshLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        layout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        layout.setColorSchemeColors(Color.GRAY);
        layout.setRefreshDrawable(new SmartisanDrawable(this, layout));
    }

}
