package com.cn.balala.nature.nature.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.balala.nature.R;
import com.cn.balala.nature.nature.model.MessageModel;

import java.util.List;


/**
 * Created by Daisy on 2015/7/29.
 */
public class MessageAdapter extends BaseAdapter {
    private List<MessageModel> lists;
    private Context mContext;


    public MessageAdapter(Context context, List<MessageModel> dataList) {
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
    public MessageModel getItem(int position) {
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
            LayoutInflater vi = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.item_message, parent, false);

            holder = new ViewHolder();
            holder.typePic = (ImageView) convertView.findViewById(R.id.iv_news_type);
            holder.typeTitle = (TextView) convertView.findViewById(R.id.tv_news_type);
            holder.describe = (TextView) convertView.findViewById(R.id.tv_news_describe);
            holder.time = (TextView) convertView.findViewById(R.id.tv_news_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

            holder.describe.setText(getItem(position).title);
            holder.time.setText("2015-11-12");


        return convertView;
    }

    static class ViewHolder {
        ImageView typePic;
        TextView typeTitle;
        TextView describe;
        TextView time;
    }
}
