package com.example.a53536.finishproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResearchActivity extends AppCompatActivity {
    TextView username_tv;
    String username;
    Button submit_btn;
    LinearLayout msg_ll,image_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);

        Intent intent = this.getIntent();
        username = intent.getStringExtra("username");

        username_tv = (TextView) findViewById(R.id.username_tv);
        username_tv.setText("当前就诊人："+ username);

        msg_ll = (LinearLayout)findViewById(R.id.msg_ll);
        image_ll = (LinearLayout)findViewById(R.id.image_ll);
        image_ll.setVisibility(View.GONE);

        submit_btn = (Button)findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View source){
                image_ll.setVisibility(View.VISIBLE);
                msg_ll.setVisibility(View.GONE);
            }
        });
    }
}
