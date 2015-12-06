package com.cn.balala.nature.nature.network;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.cn.balala.nature.nature.model.BaseModel;
import com.cn.balala.nature.nature.util.Constant;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClients;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by Black on 2015/11/15.
 */
public class MainClient {
    public static final String ROOT_URL = "http://121.42.156.36:8080/ykb";
    private static CloseableHttpClient mHttpClients;

    private static HttpResponse response = null;

    private static final int NETWORK_RESULT = 1;
    private static final int RESULT_OK = 1;
    private static final int RESULT_ERROR = 2;

    static {
        if (mHttpClients == null) {
            mHttpClients = HttpClients.createDefault();
        }
    }


    /**
     * 1.登录
     *
     * @param context
     * @param requestModel
     * @param responseModel
     * @param listener
     */
    public static void login(Context context, LoginRequestModel requestModel,
                             Class responseModel, RequestListener listener) {
        String url = Constant.APIURL.LOGIN;
        doHttpPost(context, url, requestModel, responseModel, listener);

    }

    public static void getIndexInfo(Context context, IndexInfoRequestModel requestModel,
                                    Class responseModel, RequestListener listener) {
        String url = Constant.APIURL.GET_INDEX_INFO;
        doHttpPost(context, url, requestModel, responseModel, listener);
    }


    /**
     * 登录入参model
     */
    public static class LoginRequestModel {
        private String loginAccount;
        private String password;
        private String token;

        public LoginRequestModel(String loginAccount, String password) {
            this.loginAccount = loginAccount;
            this.password = password;
            this.token = getToken();
        }
    }

    public static class IndexInfoRequestModel {
        private String companyId;
        private String token;

        public IndexInfoRequestModel(String companyId) {
            this.companyId = companyId;
            this.token = getToken();
        }
    }

    /**
     * 获得token
     *
     * @return
     */
    private static String getToken() {
        return "1";
    }


    /**
     * http post请求
     *
     * @param context
     * @param apiUrl
     * @param requestModel
     * @param responseModel
     * @param listener
     */
    public static void doHttpPost(final Context context, String apiUrl, Object requestModel,
                                  final Class responseModel, final RequestListener listener) {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.show();

        final Gson gson = new Gson();
        final String json = gson.toJson(requestModel);
        Log.d("NatureNetwork", json);
        final HttpPost httpPost = new HttpPost(ROOT_URL + apiUrl);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    StringEntity entity = new StringEntity(json, "utf-8");//解决中文乱码问题
                    entity.setContentEncoding("UTF-8");
                    entity.setContentType("application/json");

                    Log.d("NatureNetwork", entity.toString());
                    httpPost.setEntity(entity);
                    Log.d("NatureNetwork", httpPost.toString());
                    response = mHttpClients.execute(httpPost);
                    Log.d("NatureNetwork", response.toString());
                    if (response != null) {
                        if (response.getStatusLine().getStatusCode() == 200) {
                            try {
                                dialog.dismiss();
                                String data = EntityUtils.toString(response.getEntity(), "utf-8");
                                Gson gson = new Gson();
                                final BaseModel model = gson.fromJson(data, BaseModel.class);
                                model.data = gson.fromJson(data, responseModel);

                                Log.d("NatureNetwork", data);

                                if (listener != null) {

                                    ((Activity) context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (model.result == 200) {
                                                listener.onSuccess(model.data);
                                            } else {
                                                listener.onFail(model.data);
                                            }
                                        }
                                    });
                                }

                            } catch (IOException e) {
                                e.printStackTrace();

                                ((Activity) context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        dialog.dismiss();
                                        Toast.makeText(context, "解析错误", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        } else {

                            ((Activity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                    Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }


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


    }
}

