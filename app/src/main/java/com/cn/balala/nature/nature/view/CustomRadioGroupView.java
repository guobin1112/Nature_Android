package com.cn.balala.nature.nature.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cn.balala.nature.R;


/**
 * @author Jry
 */

public class CustomRadioGroupView extends LinearLayout {
    private CustomRadioGroupListener radioGroupListener;

    private RadioGroup radioGroup;
    private RadioButton radioButtonLeft;
    private RadioButton radioButtonRight;

    private String leftText = "左选项";
    private String rightText = "右选项";


    public CustomRadioGroupView(Context context) {
        this(context, null);
    }

    public CustomRadioGroupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRadioGroupView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.custom_radio_group);

        //获取自定义属性和默认值
        leftText = typedArray.getString(R.styleable.custom_radio_group_text_left);
        if (leftText == null)
            leftText = "左选项";
        rightText = typedArray.getString(R.styleable.custom_radio_group_text_right);
        if (rightText == null)
            rightText = "右选项";
        typedArray.recycle();


        //在构造函数中将Xml中定义的布局解析出来。
        LayoutInflater.from(context).inflate(R.layout.view_custom_radio_group, this, true);

        radioButtonLeft = (RadioButton) findViewById(R.id.rb_left);
        radioButtonRight = (RadioButton) findViewById(R.id.rb_right);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioButtonLeft.setText(leftText);
        radioButtonRight.setText(rightText);
        radioButtonLeft.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioGroupListener != null) {
                    if (checkedId == radioButtonLeft.getId()) {
                        radioGroupListener.setLeftChecked();
                    } else {
                        radioGroupListener.setRightChecked();
                    }
                }
            }
        });
    }

    public void setRadioGroupListener(CustomRadioGroupListener radioGroupListener) {
        this.radioGroupListener = radioGroupListener;
    }
}

