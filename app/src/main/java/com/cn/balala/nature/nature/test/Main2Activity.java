package com.cn.balala.nature.nature.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cn.balala.nature.R;
import com.cn.balala.nature.nature.model.BaseModel;
import com.cn.balala.nature.nature.model.LoginModel;
import com.cn.balala.nature.nature.network.MainClient;
import com.cn.balala.nature.nature.network.RequestListener;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> requestArgs = new HashMap<String, Object>();
                requestArgs.put("loginAccount", "bll_lingdao");
                requestArgs.put("password", "1");
                MainClient.httpPost(Main2Activity.this, "/login.html", requestArgs, new BaseModel<LoginModel>(), new RequestListener() {
                    @Override
                    public void onSuccess(Object responseModel) {
                        Toast.makeText(Main2Activity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(Object responseModel) {
                        Toast.makeText(Main2Activity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
