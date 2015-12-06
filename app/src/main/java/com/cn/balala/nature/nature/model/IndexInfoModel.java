package com.cn.balala.nature.nature.model;

import java.util.List;

/**
 * Created by Black on 2015/12/5.
 */
public class IndexInfoModel {

    public List<BannerInfo> bannerList;

    public class BannerInfo {
        public String bannerActionUrl;
        public int bannerID;
        public String bannerUrl;
    }

    public class FunctionIDinfo {
        public int funtionID;
    }
}
