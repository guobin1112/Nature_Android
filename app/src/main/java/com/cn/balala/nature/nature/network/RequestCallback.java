package com.cn.balala.nature.nature.network;

/**
 * Created by Black on 2015/11/15.
 */
public class RequestCallback<T> {
    private Class<T> typeOfT;
    private RequestListener listener;

    public RequestCallback(Class<T> typeOfT, RequestListener listener) {
        this.typeOfT = typeOfT;
        this.listener = listener;
    }
}
