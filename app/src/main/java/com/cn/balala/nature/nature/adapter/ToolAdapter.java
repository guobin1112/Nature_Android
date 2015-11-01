package com.cn.balala.nature.nature.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.balala.nature.R;
import com.cn.balala.nature.nature.model.ToolModel;

import java.util.List;

/**
 * Created by Black on 2015/10/27.
 */
public class ToolAdapter extends BaseAdapter {

    private Context mContext;
    private List<ToolModel> mDataList;

    public ToolAdapter(Context mContext, List<ToolModel> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public ToolModel getItem(int i) {
        return mDataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_tool, viewGroup, false);

            holder = new ViewHolder();
            holder.ivToolIcon = (ImageView) view.findViewById(R.id.iv_tool_icon);
            holder.tvToolName = (TextView) view.findViewById(R.id.tv_tool_name);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ToolModel model = getItem(i);
        holder.tvToolName.setText(model.toolName);
        holder.ivToolIcon.setImageResource(model.toolIcon);

        return view;
    }

    final private class ViewHolder {
        ImageView ivToolIcon;
        TextView tvToolName;
    }
}
