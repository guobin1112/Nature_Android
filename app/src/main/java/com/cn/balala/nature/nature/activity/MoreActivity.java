package com.cn.balala.nature.nature.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.balala.nature.R;

public class MoreActivity extends AppCompatActivity {
    TextView tvAboutUs;
    TextView tvSuggest;
    TextView tvShare;
    private int chioceItem;
    String qq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        tvAboutUs = (TextView) findViewById(R.id.tv_more_aboutus);
        tvSuggest = (TextView) findViewById(R.id.tv_more_suggest);
        tvShare = (TextView) findViewById(R.id.tv_more_share);
        tvAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MoreActivity.this,AboutUsActivity.class);
                startActivity(intent);
            }
        });
        tvSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MoreActivity.this,"建议",Toast.LENGTH_SHORT).show();
            }
        });

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MoreActivity.this,"分享",Toast.LENGTH_SHORT).show();

            }
        });
    }


}





