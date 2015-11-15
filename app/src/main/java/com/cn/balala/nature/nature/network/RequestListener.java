package com.cn.balala.nature.nature.network;

/**
 * Created by Black on 2015/11/15.
 */
public interface RequestListener {

    void onSuccess(Object responseModel);

    void onFail(Object responseModel);

}
