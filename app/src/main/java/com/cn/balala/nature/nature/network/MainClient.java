package com.cn.balala.nature.nature.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.cn.balala.nature.nature.model.BaseModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClients;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by Black on 2015/11/15.
 */
public class MainClient {
    public static final String ROOT_URL = "http://121.42.156.36:8080/ykb";
    private static CloseableHttpClient mHttpClients;

    static {
        if (mHttpClients == null) {
            mHttpClients = HttpClients.createDefault();
        }
    }

    public static void httpPost(Context context, String apiUrl, HashMap<String, Object> requestArgs,
                                BaseModel responseModel, RequestListener listener) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.show();

        HttpResponse response = doPost(apiUrl, requestArgs);
        if (response != null) {
            if (response.getStatusLine().getStatusCode() == 200) {
                try {
                    String data = EntityUtils.toString(response.getEntity(), "utf-8");
                    Gson gson = new Gson();
                    responseModel = gson.fromJson(data, responseModel.getClass());

                    if (listener != null) {

                        if (responseModel.result == 200) {
                            dialog.dismiss();
                            listener.onSuccess(responseModel);
                        } else {
                            listener.onFail(responseModel);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                    Toast.makeText(context, "解析错误", Toast.LENGTH_SHORT).show();
                }
            } else {
                dialog.dismiss();
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
            }

        }

    }


    private static HttpResponse doPost(String apiUrl, HashMap<String, Object> requestArgs) {
        final PostBaseRequest request = new PostBaseRequest(requestArgs);
        final HttpResponse[] response = new HttpResponse[1];
        Gson gson = new Gson();
        String json = gson.toJson(request);
        Log.d("NatureNet", json);
        final HttpPost httpPost = new HttpPost(ROOT_URL + apiUrl);
        final List<NameValuePair> parameter = new ArrayList<>();
        parameter.add(new BasicNameValuePair("data", json));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameter, "utf-8");
                    Log.d("NatureNet", entity.toString());
                    httpPost.setEntity(entity);
                    Log.d("NatureNet", httpPost.toString());
                    response[0] = mHttpClients.execute(httpPost);
                    Log.d("NatureNet", response[0].toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                }
            }
        }).start();

        return response[0];
    }
}
