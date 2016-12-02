package com.vivian.shadedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.vivian.shadedemo.adapter.CardViewAdapter;
import com.vivian.shadedemo.entity.Card;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    CardViewAdapter mAdapter;
    List<Card> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewRecyclerView();
    }

    public void initViewRecyclerView() {
        LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_main_recyclerview, null);
        setContentView(view);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CardViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        //模拟数据
        for (int i = 0; i < 10; i++) {
            Card card = new Card();
            String content = "刘雯、何穗、奚梦瑶、雎晓雯......在公开的 52 位模特名单里，一下子出现了四位中国模特。要知道，一年一度的顶级维密大秀可是为“国际超模”四个字加持的大秀啊。接下来，时间将印证这四位模特的未来身价和职业生涯。";
            card.setContent(content);
            mList.add(card);
        }
        mAdapter.setData(mList);
        mAdapter.notifyDataSetChanged();
    }
}
