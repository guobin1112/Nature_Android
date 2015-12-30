package com.cn.balala.nature.nature.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cn.balala.nature.R;
import com.cn.balala.nature.nature.adapter.MessageAdapter;
import com.cn.balala.nature.nature.model.MessageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private ListView lv_news;
    private MessageAdapter messageAdapter;

    private List<MessageModel> newsList;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_message, container, false);
        lv_news = (ListView)view.findViewById(R.id.lv_news);

        newsList=new ArrayList<>();
        newsList.add(new MessageModel("审批消息","",""));
        newsList.add(new MessageModel("请假消息","",""));
        newsList.add(new MessageModel("会议消息","",""));
        newsList.add(new MessageModel("会议消息","",""));
        newsList.add(new MessageModel("会议消息","",""));
        newsList.add(new MessageModel("会议消息","",""));
        newsList.add(new MessageModel("审批消息","",""));
        newsList.add(new MessageModel("请假消息","",""));
        newsList.add(new MessageModel("会议消息","",""));
        newsList.add(new MessageModel("审批消息","",""));
        newsList.add(new MessageModel("请假消息","",""));
        newsList.add(new MessageModel("会议消息","",""));
        newsList.add(new MessageModel("审批消息","",""));
        newsList.add(new MessageModel("请假消息","",""));
        newsList.add(new MessageModel("会议消息","",""));
        newsList.add(new MessageModel("审批消息","",""));
        newsList.add(new MessageModel("请假消息","",""));
        newsList.add(new MessageModel("会议消息","",""));
        newsList.add(new MessageModel("审批消息","",""));
        newsList.add(new MessageModel("请假消息","",""));
        newsList.add(new MessageModel("会议消息","",""));


        messageAdapter = new MessageAdapter(getActivity(),newsList);
        lv_news.setAdapter(messageAdapter);
        return view;
    }


}
