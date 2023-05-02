package com.example.a53536.finishproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 53536 on 2021/5/30.
 */

public class UserMsgActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tv_username,tv_medical_ID,tv_userID,index_nav_tv,reservation_nav_tv,me_nav_tv;
    TextView id1,id2,id3,id4;
    String username,medical_ID,userID;
    SQLiteDatabase db;
    ImageButton imageButton1,imageButton2;
    LinearLayout layout1,layout2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermsg_page);

        Intent intent = this.getIntent();
        username = intent.getStringExtra("username");

        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir()
                .toString() + "/user_msg.db3" , null);
        Cursor cursor = db.rawQuery("select * from user_msg", null);
        while(cursor.moveToNext()){
            if(username.equals(cursor.getString(1))){
                medical_ID=cursor.getString(0);
                userID=cursor.getString(3);
                break;
            }
        }

        tv_username = (TextView)findViewById(R.id.username);
        tv_medical_ID = (TextView)findViewById(R.id.medical_ID);
        tv_userID = (TextView)findViewById(R.id.user_id);
        index_nav_tv = (TextView)findViewById(R.id.index_nav_tv);
        reservation_nav_tv = (TextView)findViewById(R.id.reservation_nav_tv);
        me_nav_tv = (TextView)findViewById(R.id.me_nav_tv);

        reservation_nav_tv.setTextColor(Color.parseColor("#000000"));
        index_nav_tv.setTextColor(Color.parseColor("#000000"));
        me_nav_tv.setTextColor(Color.parseColor("#008080"));

        tv_username.setText(username);
        tv_medical_ID.setText("就诊ID：" + medical_ID);
        tv_userID.setText("身份证："+ userID);

        imageButton1=(ImageButton)findViewById(R.id.imageButton1);
        imageButton2=(ImageButton)findViewById(R.id.imageButton2);

        id1 = (TextView)findViewById(R.id.id1);
        id2 = (TextView)findViewById(R.id.id2);
        id3 = (TextView)findViewById(R.id.id3);
        id4 = (TextView)findViewById(R.id.id4);

        id1.setOnClickListener(this);
        id2.setOnClickListener(this);
        id3.setOnClickListener(this);
        id4.setOnClickListener(this);

        layout1 = (LinearLayout)findViewById(R.id.layout1);
        layout2 = (LinearLayout)findViewById(R.id.layout2);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);

        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.imageButton1:{
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.imageButton2:{
                Intent intent = new Intent(this,MyReservationActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.layout1:{
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.layout2:{
                Intent intent = new Intent(this,MyReservationActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.id1:{
                Toast.makeText(UserMsgActivity.this,"sorry，设置功能还在开发:D",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.id2:{
                Toast.makeText(UserMsgActivity.this,"sorry，客服中心功能还在开发:D",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.id3:{
                Toast.makeText(UserMsgActivity.this,"sorry，帮助中心功能还在开发:D",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.id4:{
                AlertDialog alertDialog = new AlertDialog.Builder(UserMsgActivity.this)
                        .setTitle("关于我们")
                        .setMessage("由林榆杰和卢小龙两人合力完成\r\n时间有限，功能不齐全，望谅解:D")
                        .setIcon(R.mipmap.ic)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create();
                alertDialog.show();
                break;
            }
        }
    }
}
