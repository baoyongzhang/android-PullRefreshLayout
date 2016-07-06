package com.baoyz.pullrefreshlayout.sample.gank;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.baoyz.pullrefreshlayout.sample.R;
import com.baoyz.pullrefreshlayout.sample.gank.adapter.GankAdapter;
import com.baoyz.pullrefreshlayout.sample.gank.model.GankData;
import com.baoyz.pullrefreshlayout.sample.gank.network.GankApi;
import com.baoyz.widget.PullRefreshLayout;

import retrofit.Call;
import retrofit.Callback;
import retrofit.MoshiConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Content from http://gank.io
 */
public class GankActivity extends Activity {

    private RecyclerView mRecyclerView;
    private GankApi mGankApi;
    private PullRefreshLayout mRefreshLayout;
    private int mCurrentPage = 1;
    private GankAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GankAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout = (PullRefreshLayout) findViewById(R.id.refreshLayout);
        mRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_SMARTISAN);
        mRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int direction) {
                switch (direction) {
                    case PullRefreshLayout.DIRECTION_UP:
                        loadData(1);
                        break;
                    case PullRefreshLayout.DIRECTION_DOWN:
                        loadData(mCurrentPage + 1);
                        break;
                }
            }
        });

        mGankApi = new Retrofit
                .Builder()
                .baseUrl(GankApi.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(GankApi.class);

        mRefreshLayout.setRefreshing(true);
        loadData(mCurrentPage);
    }

    private void loadData(final int page) {
        Call<GankData> call = mGankApi.getData(page);
        call.enqueue(new Callback<GankData>() {
            @Override
            public void onResponse(Response<GankData> response, Retrofit retrofit) {
                mRefreshLayout.setRefreshing(false);
                if (!response.isSuccess()) {
                    Toast.makeText(GankActivity.this, response.code() + ", " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                mCurrentPage = page;
                if (mCurrentPage == 1) {
                    mAdapter.clear();
                }
                GankData data = response.body();
                if (data == null || data.getResults() == null || data.getResults().size() <= 0) {
                    return;
                }
                mAdapter.addItems(data.getResults());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable throwable) {
                mRefreshLayout.setRefreshing(false);
            }
        });
    }
}
