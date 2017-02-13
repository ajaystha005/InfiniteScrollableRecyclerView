package com.recylerview.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ajaystha.recyclerview_infinitescroll.InfiniteScrollRecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private DemoAdapter mAdapter;
    private ArrayList<String> demoList = new ArrayList<>();
    private InfiniteScrollRecyclerView mScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);

        mScrollListener = new InfiniteScrollRecyclerView() {
            @Override
            protected void onFetchMoreItem(int currentPage) {
                final int lastInsertPosition = demoList.size() - 1;
                demoList.addAll(fetchMoreData(currentPage + ""));
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        updateResult(lastInsertPosition);

                    }
                });
            }

            @Override
            protected void onReachTop() {
                super.onReachTop();
                Log.e("Reach to Top", "Okay");
            }

            @Override
            protected void onReachBottom() {
                super.onReachBottom();
                Log.e("Reach to Bottom", "Okay");
            }

            @Override
            protected void onScrollDown() {
                super.onScrollDown();
                Log.e("Scroll - Down", "Okay");
            }

            @Override
            protected void onScrollUp() {
                super.onScrollUp();
                Log.e("Scroll - Top", "Okay");
            }
        };
        mScrollListener.setLayoutManager(mLinearLayoutManager);
        mScrollListener.setThresholdValue(3);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addOnScrollListener(mScrollListener);


        demoList.addAll(fetchMoreData(0 + ""));
        mAdapter = new DemoAdapter(MainActivity.this, demoList);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void updateResult(int lastInsertPosition) {
        if (mAdapter != null) {
            mAdapter.notifyItemRangeInserted(lastInsertPosition, (demoList.size() - 1) - lastInsertPosition);
        }
    }

    private ArrayList<String> fetchMoreData(String page) {

        ArrayList<String> list = new ArrayList<>();

        //Just to stop fetching
        if (Integer.parseInt(page) > 5) {
            return list;
        }
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        list.add(page);
        return list;
    }
}
