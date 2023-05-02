package com.example.a53536.finishproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 53536 on 2021/5/30.
 */

public class CarActivity  extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    TextView username_tv;
    Button tied_card_btn,create_card_btn,suretied_btn,surebuild_btn;
    String username;
    LinearLayout tied_card_ll,create_card_ll;
    SQLiteDatabase db;
    EditText card_name,card_id,user_name,user_id,user_phone;
    RadioButton nan,nv;
    RadioGroup sex_group;
    String cardname_str,cardid_str,username_str,userid_str,userphone_str,sex_str;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardoperation_page);

        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir()
                .toString() + "/user_msg.db3" , null);

        Intent intent = this.getIntent();
        username = intent.getStringExtra("username");

        username_tv = (TextView) findViewById(R.id.username_tv);
        username_tv.setText("当前就诊人："+ username);

        card_name = (EditText)findViewById(R.id.card_name);
        card_id = (EditText)findViewById(R.id.card_id);
        user_name = (EditText)findViewById(R.id.user_name);
        user_id = (EditText)findViewById(R.id.user_id);
        user_phone = (EditText)findViewById(R.id.user_phone);
        sex_group = (RadioGroup)findViewById(R.id.sex_group);
        sex_group.setOnCheckedChangeListener(this);

        tied_card_ll = (LinearLayout) findViewById(R.id.tied_card_ll);
        create_card_ll = (LinearLayout) findViewById(R.id.create_card_ll);
        create_card_ll.setVisibility(View.GONE);

        tied_card_btn = (Button)findViewById(R.id.tied_card_btn);
        create_card_btn = (Button)findViewById(R.id.create_card_btn);
        suretied_btn=(Button)findViewById(R.id.suretied_btn);
        surebuild_btn=(Button)findViewById(R.id.surebuild_btn);

        tied_card_btn.setOnClickListener(this);
        create_card_btn.setOnClickListener(this);
        suretied_btn.setOnClickListener(this);
        surebuild_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tied_card_btn:{
                tied_card_btn.setTextColor(Color.parseColor("#000000"));
                tied_card_btn.setBackgroundColor(Color.parseColor("#66cccc"));
                create_card_btn.setTextColor(Color.parseColor("#008080"));
                create_card_btn.setBackgroundColor(Color.parseColor("#ffffff"));
                tied_card_ll.setVisibility(View.VISIBLE);
                create_card_ll.setVisibility(View.GONE);
                break;
            }
            case R.id.create_card_btn:{
                tied_card_btn.setTextColor(Color.parseColor("#008080"));
                tied_card_btn.setBackgroundColor(Color.parseColor("#ffffff"));
                create_card_btn.setTextColor(Color.parseColor("#000000"));
                create_card_btn.setBackgroundColor(Color.parseColor("#66cccc"));
                tied_card_ll.setVisibility(View.GONE);
                create_card_ll.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.suretied_btn:{
                cardname_str= card_name.getText().toString();
                cardid_str= card_id.getText().toString();
                if(cardname_str.equals("")||cardid_str.equals("")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(CarActivity.this)
                            .setTitle("错误！")
                            .setMessage("信息填写不能留空")
                            .setIcon(R.mipmap.ic)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .setNegativeButton("重新填写", new DialogInterface.OnClickListener() {//添加取消
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    card_name.setText("");
                                    card_id.setText("");
                                }
                            })
                            .create();
                    alertDialog.show();
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(CarActivity.this)
                            .setTitle("确认绑卡?")
                            .setMessage("姓名："+cardname_str+"\r\n"
                                    +"医保卡号："+cardid_str+"\r\n")
                            .setIcon(R.mipmap.ic)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    insertData1(db , username , cardname_str, cardid_str);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .create();
                    alertDialog.show();
                }
                break;
            }
            case R.id.surebuild_btn:{
                username_str= user_name.getText().toString();
                userid_str= user_id.getText().toString();
                userphone_str= user_phone.getText().toString();
                if(username_str.equals("")||userid_str.equals("")||userphone_str.equals("")||sex_str.equals("")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(CarActivity.this)
                            .setTitle("错误！")
                            .setMessage("信息填写不能留空")
                            .setIcon(R.mipmap.ic)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .setNegativeButton("重新填写", new DialogInterface.OnClickListener() {//添加取消
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    user_name.setText("");
                                    user_id.setText("");
                                    user_phone.setText("");
                                }
                            })
                            .create();
                    alertDialog.show();
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(CarActivity.this)
                            .setTitle("确认建卡?")
                            .setMessage("姓名："+username_str+"\r\n"
                                    +"身份证号："+userid_str+"\r\n"
                                    +"手机号码："+userphone_str+"\r\n"
                                    +"性别："+sex_str+"\r\n")
                            .setIcon(R.mipmap.ic)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    insertData2(db , username , username_str, userid_str,userphone_str,sex_str);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .create();
                    alertDialog.show();
                }

                break;
            }
        }
    }

    private void insertData1(SQLiteDatabase db, String username , String cardname_str,String cardid_str ){
            Cursor cursor = db.rawQuery("select * from HospitalCard_msg", null);
            int flag = 0;
            while(cursor.moveToNext()){
                if(username.equals(cursor.getString(1))){
                    Toast.makeText(this,cursor.getString(1)+"用户绑过医保卡啦！",Toast.LENGTH_SHORT).show();
                    flag = 1;
                    break;
                }
            }
            if(flag==0){
                db.execSQL("insert into HospitalCard_msg values(null , ? , ?, ?)", new String[]{username,cardname_str,cardid_str});
                Toast.makeText(this,"绑卡成功",Toast.LENGTH_SHORT).show();
                card_name.setText("");
                card_id.setText("");
            }
    }
    private void insertData2(SQLiteDatabase db, String username , String username_str,String userid_str,String userphone_str ,String sex_str){
            Cursor cursor = db.rawQuery("select * from BuildCard_msg", null);
            int flag = 0;
            while(cursor.moveToNext()){
                if(username.equals(cursor.getString(1))){
                    Toast.makeText(this,cursor.getString(1)+"用户创建过就诊卡啦！",Toast.LENGTH_SHORT).show();
                    flag = 1;
                    break;
                }
            }
            if(flag==0){
                db.execSQL("insert into BuildCard_msg values(null , ? , ?, ?,?,?)", new String[]{username,username_str,userid_str,userphone_str,sex_str});
                Toast.makeText(this,"建卡成功",Toast.LENGTH_SHORT).show();
                user_name.setText("");
                user_id.setText("");
                user_phone.setText("");
            }
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i){
        sex_str=i==R.id.nan?"男":"女";
    }
}
