package com.cn.balala.nature.nature.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.balala.nature.R;

public class PreConferenceActivity extends AppCompatActivity {

    private static final int BEFORE_CONFERENCE = 1;//会议前
    private static final int DURING_CONFERENCE = 2;//会议中
    private static final int AFTER_CONFERENCE = 3;//会议后

    private int conferencePeriod = 1;

    ImageView ivLogo, ivBack;
    TextView tvOther;
    Button btnInfo, btnMaterial, btnEntrance;
    RelativeLayout rlBeforeConference, rlDuringConference, rlAfterConference;

    BeforeConferenceLayout beforeConferenceLayout;
    DuringConferenceLayout duringConferenceLayout;
    AfterConferenceLayout afterConferenceLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_conference);

        conferencePeriod = getIntent().getIntExtra("conferencePeriod", 1);

        initView();
    }

    private void initView() {
        ivLogo = (ImageView) findViewById(R.id.iv_logo);
        btnInfo = (Button) findViewById(R.id.btn_conference_info);
        btnMaterial = (Button) findViewById(R.id.btn_conference_material);
        btnEntrance = (Button) findViewById(R.id.btn_conference_entrance);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvOther = (TextView) findViewById(R.id.tv_other);
        rlBeforeConference = (RelativeLayout) findViewById(R.id.rl_before_conference);
        rlDuringConference = (RelativeLayout) findViewById(R.id.rl_during_conference);
        rlAfterConference = (RelativeLayout) findViewById(R.id.rl_after_conference);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PreConferenceActivity.this, "正在开发中", Toast.LENGTH_SHORT).show();
            }
        });

        btnMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PreConferenceActivity.this, "正在开发中", Toast.LENGTH_SHORT).show();
            }
        });

        if (conferencePeriod == BEFORE_CONFERENCE) {
            rlBeforeConference.setVisibility(View.VISIBLE);
            rlDuringConference.setVisibility(View.GONE);
            rlAfterConference.setVisibility(View.GONE);

            beforeConferenceLayout = new BeforeConferenceLayout();

            btnEntrance.setBackgroundResource(R.drawable.shape_rounded_btn_disabled);
            btnEntrance.setText("进入会议");
            btnEntrance.setClickable(false);
            tvOther.setText("请假");
            tvOther.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(PreConferenceActivity.this, "正在开发中", Toast.LENGTH_SHORT).show();
                }
            });

        }

        if (conferencePeriod == DURING_CONFERENCE) {
            rlBeforeConference.setVisibility(View.GONE);
            rlDuringConference.setVisibility(View.VISIBLE);
            rlAfterConference.setVisibility(View.GONE);

            duringConferenceLayout = new DuringConferenceLayout();

            btnEntrance.setBackgroundResource(R.drawable.shape_rounded_btn_red);
            btnEntrance.setText("进入会议");
            btnEntrance.setClickable(false);
            tvOther.setText("签到");
            tvOther.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(PreConferenceActivity.this, "正在开发中", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (conferencePeriod == AFTER_CONFERENCE) {
            rlBeforeConference.setVisibility(View.GONE);
            rlDuringConference.setVisibility(View.GONE);
            rlAfterConference.setVisibility(View.VISIBLE);

            afterConferenceLayout = new AfterConferenceLayout();

            btnEntrance.setBackgroundResource(R.drawable.shape_rounded_btn_red);
            btnEntrance.setText("会议资料");
            btnEntrance.setClickable(false);
            tvOther.setText("");
        }

    }


    private class BeforeConferenceLayout {
        TextView tvTitle, tvTime, tvLocation;

        public BeforeConferenceLayout() {
            this.tvTitle = (TextView) findViewById(R.id.tv_conference_address_before);
            this.tvTime = (TextView) findViewById(R.id.tv_conference_time_before);
            this.tvLocation = (TextView) findViewById(R.id.tv_conference_address_before);
        }
    }

    private class DuringConferenceLayout {
        TextView tvTitle, tvTime, tvLocation, tvSpeaker;

        public DuringConferenceLayout() {
            this.tvTitle = (TextView) findViewById(R.id.tv_conference_address);
            this.tvTime = (TextView) findViewById(R.id.tv_conference_time);
            this.tvLocation = (TextView) findViewById(R.id.tv_conference_address);
            this.tvSpeaker = (TextView) findViewById(R.id.tv_conference_speaker);
        }
    }

    private class AfterConferenceLayout {
        TextView tvTitle, tvTime, tvLocation;

        public AfterConferenceLayout() {
            this.tvTitle = (TextView) findViewById(R.id.tv_conference_address_after);
            this.tvTime = (TextView) findViewById(R.id.tv_conference_time_after);
            this.tvLocation = (TextView) findViewById(R.id.tv_conference_address_after);
        }
    }
}
