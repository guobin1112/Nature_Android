package com.cn.balala.nature.nature.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cn.balala.nature.R;


public class ChangePasswordActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener{
    ToggleButton img_eye_old;
    ToggleButton img_eye_new;
    EditText et_change_password_old;
    EditText et_change_password_new;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        et_change_password_new = (EditText) findViewById(R.id.et_change_password_new);
        et_change_password_old = (EditText) findViewById(R.id.et_change_password_old);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        et_change_password_new.addTextChangedListener(this);
        et_change_password_old.addTextChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
               /* intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                startActivity(intent);*/
                if (et_change_password_new.length() < 6||et_change_password_new.length() >12)
                {
                    Toast.makeText(ChangePasswordActivity.this, "请设置6-12位新登录密码",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ChangePasswordActivity.this, "修改成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangePasswordActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (TextUtils.isEmpty(et_change_password_old.getText().toString())) {
            btn_submit.setEnabled(false);
        } else if (TextUtils.isEmpty(et_change_password_new.getText().toString())) {
            btn_submit.setEnabled(false);
        } else {
            btn_submit.setEnabled(true);
        }
    }
}
