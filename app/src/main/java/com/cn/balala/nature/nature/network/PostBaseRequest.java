package com.cn.balala.nature.nature.network;

import java.util.HashMap;

/**
 * Created by Black on 2015/11/15.
 */
public class PostBaseRequest {
    private HashMap<String, Object> args;

    public PostBaseRequest(HashMap<String, Object> requestArg) {
        this.args = requestArg;
        this.args.put("token", getToken());
    }

    private String getToken() {
        return "1";
    }
}
