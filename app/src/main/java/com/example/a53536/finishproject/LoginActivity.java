package com.example.a53536.finishproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    Button btn_login,btn_register;
    EditText et_usename,et_password;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir()
                .toString() + "/user_msg.db3" , null);

        db.execSQL("create table if not exists user_msg(_id integer primary key autoincrement,"
                + " username varchar(50)," + " password varchar(255)," + " user_id varchar(255))");

        db.execSQL("create table if not exists nucleic(_id integer primary key autoincrement,"
                + " username varchar(50)," + " appointment_name varchar(50),"+ " time varchar(255)," + " place varchar(255))");

        db.execSQL("create table if not exists vaccine(_id integer primary key autoincrement,"
                + " username varchar(50)," + " appointment_name varchar(255),"
                +" appointment_id varchar(255)," +" appointment_time varchar(255),"
                + " appointment_site varchar(255))");

        db.execSQL("create table if not exists reservation(_id integer primary key autoincrement,"
                + " username varchar(50)," + "  appointment_type varchar(255)," + " appointment_name varchar(255))");

        db.execSQL("create table if not exists HospitalCard_msg(_id integer primary key autoincrement,"
                + " username varchar(50)," + " card_name varchar(255)," + " card_id varchar(255))");

        db.execSQL("create table if not exists BuildCard_msg(_id integer primary key autoincrement,"
                + " username varchar(50)," + " user_name varchar(255),"
                + " user_id varchar(255)," +" user_phone varchar(255),"
                + " user_sex varchar(255))");

        et_usename = (EditText) findViewById(R.id.et_usename);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register = (Button)findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View source){
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View source){
                int flag = 0;
                String medical_ID=null,userID=null;
                String username = et_usename.getText().toString();
                String password = et_password.getText().toString();

                Cursor cursor = db.rawQuery("select * from user_msg", null);
                while(cursor.moveToNext()){
                    if(username.equals(cursor.getString(1)) && password.equals(cursor.getString(2))){
                        flag =1;
                        medical_ID=cursor.getString(0);
                        userID=cursor.getString(3);
                        break;
                    }
                }
                if(flag==1){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,"登入成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}