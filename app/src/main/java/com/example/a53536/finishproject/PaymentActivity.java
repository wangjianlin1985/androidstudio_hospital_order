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

public class PaymentActivity extends AppCompatActivity {
    TextView username_tv;
    Button no_pay,already_pay;
    String username;
    LinearLayout image1,image2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        Intent intent = this.getIntent();
        username = intent.getStringExtra("username");

        image1 = (LinearLayout)findViewById(R.id.image1);
        image2 = (LinearLayout)findViewById(R.id.image2);
        image2.setVisibility(View.GONE);

        username_tv = (TextView) findViewById(R.id.username_tv);
        username_tv.setText("当前就诊人："+ username);

        no_pay = (Button)findViewById(R.id.no_pay);
        already_pay = (Button)findViewById(R.id.already_pay);

        already_pay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View source) {
                no_pay.setTextColor(Color.parseColor("#008080"));
                no_pay.setBackgroundColor(Color.parseColor("#ffffff"));
                already_pay.setTextColor(Color.parseColor("#000000"));
                already_pay.setBackgroundColor(Color.parseColor("#66cccc"));
                image1.setVisibility(View.GONE);
                image2.setVisibility(View.VISIBLE);
            }
        });
        no_pay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View source) {
                no_pay.setTextColor(Color.parseColor("#000000"));
                no_pay.setBackgroundColor(Color.parseColor("#66cccc"));
                already_pay.setTextColor(Color.parseColor("#008080"));
                already_pay.setBackgroundColor(Color.parseColor("#ffffff"));
                image1.setVisibility(View.VISIBLE);
                image2.setVisibility(View.GONE);
            }
        });
    }
}
