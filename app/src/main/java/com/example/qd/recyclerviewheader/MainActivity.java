package com.example.qd.recyclerviewheader;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.andview.refreshview.XRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
    private List<String> mDatas;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mDatas = new ArrayList<>();
        setAdapter();
    }

    private void setAdapter() {
        //添加标题
        mDatas.add("title");
        //添加数据
        for (int i = 0; i < 15; i++) {
            mDatas.add(i + "");
        }
        //设置adapter
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mainAdapter = new MainAdapter(MainActivity.this, mDatas);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);

        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            //下拉刷新
            @Override
            public void onRefresh(boolean isPullDown) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.clear();
                        for (int i = 0; i < 15; i++) {
                            mDatas.add(i + "");
                        }
                        mainAdapter.notifyDataSetChanged();
                        xRefreshView.stopRefresh();
                    }
                }, 1500);
            }

            //上拉加载
            @Override
            public void onLoadMore(boolean isSilence) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            mDatas.add(i + "");
                        }
                        mainAdapter.notifyDataSetChanged();
                        xRefreshView.stopLoadMore();
                    }
                }, 1500);
            }
        });
    }
}
