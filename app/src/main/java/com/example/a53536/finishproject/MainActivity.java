package com.example.a53536.finishproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tv_username,tv_medical_ID,tv_userID,index_nav_tv,reservation_nav_tv,me_nav_tv;
    ImageButton research,order,btn_yuyue,reservation_btn,payment_btn,report_btn,examine_btn,vaccine_btn,nuleic_btn,car_btn,hospitalCard_btn,patientCard_btn;
    ImageButton imageButton2,imageButton3;
    String username,medical_ID,userID;
    SQLiteDatabase db;
    LinearLayout user_ll,layout1,layout2,layout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        research= (ImageButton)findViewById(R.id.research);
        order=(ImageButton)findViewById(R.id.order);
        btn_yuyue = (ImageButton)findViewById(R.id.btn_yuyue);
        reservation_btn = (ImageButton)findViewById(R.id.reservation_btn);
        payment_btn = (ImageButton)findViewById(R.id.payment_btn);
        report_btn = (ImageButton)findViewById(R.id.report_btn);
        examine_btn = (ImageButton)findViewById(R.id.examine_btn);
        vaccine_btn= (ImageButton)findViewById(R.id.vaccine_btn);
        nuleic_btn= (ImageButton)findViewById(R.id.nuleic_btn);
        car_btn=(ImageButton)findViewById(R.id.car_btn);
        hospitalCard_btn=(ImageButton)findViewById(R.id.hospitalCard_btn);
        patientCard_btn=(ImageButton)findViewById(R.id.patientCard_btn);

        imageButton2=(ImageButton)findViewById(R.id.imageButton2);
        imageButton3=(ImageButton)findViewById(R.id.imageButton3);

        user_ll = (LinearLayout)findViewById(R.id.user_ll);
        layout1 = (LinearLayout)findViewById(R.id.layout1);
        layout2 = (LinearLayout)findViewById(R.id.layout2);
        layout3 = (LinearLayout)findViewById(R.id.layout3);

        tv_username.setText(username);
        tv_medical_ID.setText("就诊ID：" + medical_ID);
        tv_userID.setText("身份证："+ userID);

        research.setOnClickListener(this);
        order.setOnClickListener(this);
        btn_yuyue.setOnClickListener(this);
        reservation_btn.setOnClickListener(this);
        payment_btn.setOnClickListener(this);
        report_btn.setOnClickListener(this);
        examine_btn.setOnClickListener(this);
        vaccine_btn.setOnClickListener(this);
        nuleic_btn.setOnClickListener(this);
        car_btn.setOnClickListener(this);
        hospitalCard_btn.setOnClickListener(this);
        patientCard_btn.setOnClickListener(this);

        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);

        user_ll.setOnClickListener(this);
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.user_ll:{
                Intent intent = new Intent(this,UserMsgActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.research:{
                Intent intent = new Intent(this,ResearchActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.order:{
                Intent intent = new Intent(this,OrderActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.btn_yuyue:{
                Intent intent = new Intent(this,AppointmentActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.reservation_btn:{
                Intent intent = new Intent(this,MyReservationActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.payment_btn:{
                Intent intent = new Intent(this,PaymentActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.report_btn:{
                Intent intent = new Intent(this,InspectionReportActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.examine_btn:{
                Intent intent = new Intent(this,ExamineActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.vaccine_btn:{
                Intent intent = new Intent(this,VaccineActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.nuleic_btn:{
                Intent intent = new Intent(this,NucleicActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.car_btn:{
                Intent intent = new Intent(this,CarActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.hospitalCard_btn:{
                Intent intent = new Intent(this,HospitalCardActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
            case R.id.patientCard_btn:{
                Intent intent = new Intent(this,PatientCardActivity.class);
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
            case R.id.imageButton3:{
                Intent intent = new Intent(this,UserMsgActivity.class);
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
            case R.id.layout3:{
                Intent intent = new Intent(this,UserMsgActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            }
        }
    }
}
