package com.example.a53536.finishproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 53536 on 2021/5/26.
 */

public class ExamineActivity extends AppCompatActivity {
    TextView username_tv;
    Button outpatient_btn,hospital_btn;
    String username;
    LinearLayout image1,image2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examine_report);

        Intent intent = this.getIntent();
        username = intent.getStringExtra("username");

        image1 = (LinearLayout)findViewById(R.id.image1);
        image2 = (LinearLayout)findViewById(R.id.image2);
        image2.setVisibility(View.GONE);

        username_tv = (TextView) findViewById(R.id.username_tv);
        username_tv.setText("当前就诊人："+ username);

        outpatient_btn = (Button)findViewById(R.id.outpatient_btn);
        hospital_btn = (Button)findViewById(R.id.hospital_btn);

        hospital_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View source) {
                outpatient_btn.setTextColor(Color.parseColor("#008080"));
                outpatient_btn.setBackgroundColor(Color.parseColor("#ffffff"));
                hospital_btn.setTextColor(Color.parseColor("#000000"));
                hospital_btn.setBackgroundColor(Color.parseColor("#66cccc"));
                image1.setVisibility(View.GONE);
                image2.setVisibility(View.VISIBLE);
            }
        });
        outpatient_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View source) {
                outpatient_btn.setTextColor(Color.parseColor("#000000"));
                outpatient_btn.setBackgroundColor(Color.parseColor("#66cccc"));
                hospital_btn.setTextColor(Color.parseColor("#008080"));
                hospital_btn.setBackgroundColor(Color.parseColor("#ffffff"));
                image1.setVisibility(View.VISIBLE);
                image2.setVisibility(View.GONE);
            }
        });
    }
}