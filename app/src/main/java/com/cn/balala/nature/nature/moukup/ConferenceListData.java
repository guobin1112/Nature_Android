package com.cn.balala.nature.nature.moukup;

import com.cn.balala.nature.nature.model.ConferenceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/30.
 */
public class ConferenceListData {
        static public List<ConferenceModel> list = new ArrayList<>();

        static public List<ConferenceModel> getList() {
            list.add(new ConferenceModel(1,"第一季度总结大会", "2015/12/12  9:00am","A3-307", "张三",1));
            list.add(new ConferenceModel(2,"第二季度总结大会", "2015/10/10  9:00am","A1-207",  "李四",2));
            list.add(new ConferenceModel(3,"第三季度总结大会", "2015/8/8  9:00am", "A2-107", "王五",3));
            list.add(new ConferenceModel(4,"第四季度总结大会", "2015/4/4  9:00am", "A3-307", "赵六",3));

            return list;
        }
    }
