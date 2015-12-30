package com.cn.balala.nature.nature.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.cn.balala.nature.R;
import com.cn.balala.nature.nature.activity.ApplicationMeetingActivity;
import com.cn.balala.nature.nature.adapter.ConferenceAdapter;
import com.cn.balala.nature.nature.moukup.ConferenceListData;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConferenceFragment extends Fragment {

    ListView ConferenceList;
    ConferenceAdapter adapter;
    ImageView iv_add;

    public ConferenceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conference, container, false);
        iv_add=(ImageView)view.findViewById(R.id.iv_add);
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), ApplicationMeetingActivity.class);
                startActivity(intent);
            }
        });
        ConferenceList = (ListView) view.findViewById(R.id.lv_conference);
        adapter = new ConferenceAdapter(getActivity(), ConferenceListData.getList());
        ConferenceList.setAdapter(adapter);
        return view;
    }


}
