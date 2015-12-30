package com.cn.balala.nature.nature.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.balala.nature.R;
import com.cn.balala.nature.nature.model.ConferenceModel;

import java.util.List;

/**
 * Created by Administrator on 2015/12/30.
 */
public class ConferenceAdapter extends BaseAdapter {
        private List<ConferenceModel> lists;
        private Context mContext;
        public ConferenceAdapter(Context context,
                            List<ConferenceModel> dataList) {
            mContext = context;
            lists = dataList;
        }

        @Override
        public boolean isEnabled(int position) {
            return super.isEnabled(position);//当前行是否可以点击
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return lists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


    @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_conference, null);
                holder = new ViewHolder();
                holder.conference_name = (TextView) convertView.findViewById(R.id.tv_conference_name);
                holder.conference_time = (TextView) convertView.findViewById(R.id.tv_conference_time);
                holder.conference_place = (TextView) convertView.findViewById(R.id.tv_conference_place);
                holder.conference_responsible_person = (TextView) convertView.findViewById(R.id.tv_conference_responsible_person);
                holder.conference_state= (ImageView) convertView.findViewById(R.id.iv_conference_state);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (lists.size() > 0) {
                holder.conference_name.setText(lists.get(position).conferenceName);
                holder.conference_time.setText(lists.get(position).conferenceTime);
                holder.conference_place.setText(lists.get(position).conferenceAddress);
                holder.conference_responsible_person.setText(lists.get(position).conferencePerson);
                if(lists.get(position).conferenceState==1) {
                }
                else if(lists.get(position).conferenceState==2) {
                }
                else {
                }
            }

            return convertView;
        }



        static class ViewHolder {
            TextView conference_name;
            TextView conference_time;
            TextView conference_place;
            TextView conference_responsible_person;
            ImageView conference_state;
        }
    }
