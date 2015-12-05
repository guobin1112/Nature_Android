package com.cn.balala.nature.nature.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.balala.nature.R;
import com.cn.balala.nature.nature.model.LoginModel;
import com.cn.balala.nature.nature.network.MainClient;
import com.cn.balala.nature.nature.network.RequestListener;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {

    Button button;
    TextView webview;

    String html;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        button = (Button) findViewById(R.id.button);
        webview = (TextView) findViewById(R.id.webview);

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        webview.setText("123");
                        break;
                }
            }
        };

        MainClient.LoginRequestModel model = new MainClient.LoginRequestModel("bll_1", "1");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> requestArgs = new HashMap<String, Object>();
                requestArgs.put("loginAccount", "bll_lingdao");
                requestArgs.put("password", "1");
                MainClient.login(Main2Activity.this, new MainClient.LoginRequestModel("bll_1", "1"), LoginModel.class, new RequestListener() {
                    @Override
                    public void onSuccess(Object responseModel) {
                        Toast.makeText(Main2Activity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(Object responseModel) {
                        Toast.makeText(Main2Activity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                    }
                });

//                final CloseableHttpClient clients = HttpClients.createDefault();
//
//                final HttpGet httpGet = new HttpGet("http://www.baidu.com");
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            HttpResponse response = clients.execute(httpGet);
////                            html = EntityUtils.toString(response.getEntity(), "utf-8");
//                            Log.d("NatureNet", EntityUtils.toString(response.getEntity(), "utf-8"));
//
//                            Looper.prepare();
//                            Message message = new Message();
//                            message.what = 1;
//                            handler.sendMessage(message);
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();

            }
        });
    }
}
