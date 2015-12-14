package com.cn.balala.nature.nature.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.balala.nature.R;

public class PreConferenceActivity extends AppCompatActivity {

    ImageView ivLogo;
    TextView tvTitle, tvTime, tvAddress;
    Button btnInfo, btnMaterial, btnEntrance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_conference);

        initView();
    }

    private void initView() {
        ivLogo = (ImageView) findViewById(R.id.iv_logo);
        tvTitle = (TextView) findViewById(R.id.tv_conference_title);
        tvTime = (TextView) findViewById(R.id.tv_conference_time);
        tvAddress = (TextView) findViewById(R.id.tv_conference_address);
        btnInfo = (Button) findViewById(R.id.btn_conference_info);
        btnMaterial = (Button) findViewById(R.id.btn_conference_material);
        btnEntrance = (Button) findViewById(R.id.btn_conference_entrance);
    }
}
