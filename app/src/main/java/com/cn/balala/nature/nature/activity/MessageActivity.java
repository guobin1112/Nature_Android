package com.cn.balala.nature.nature.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.cn.balala.nature.R;
import com.cn.balala.nature.nature.adapter.MessageAdapter;
import com.cn.balala.nature.nature.model.MessageModel;

import java.util.ArrayList;
import java.util.List;


public class MessageActivity extends AppCompatActivity {

    private ListView lv_news;
    private MessageAdapter messageAdapter;

    private List<MessageModel> newsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        lv_news = (ListView)findViewById(R.id.lv_news);
        newsList=new ArrayList<>();
        messageAdapter = new MessageAdapter(MessageActivity.this,newsList);
        lv_news.setAdapter(messageAdapter);

    }

}
