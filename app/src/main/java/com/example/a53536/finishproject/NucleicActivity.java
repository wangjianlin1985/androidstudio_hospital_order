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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 53536 on 2021/5/30.
 */

public class NucleicActivity extends AppCompatActivity{
    TextView username_tv;
    Button reservation_btn,report_btn;
    String username;
    LinearLayout reservation_ll,report_ll;
    SQLiteDatabase db;
    ListView reservation_nucleic;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nucleic_report);

        Intent intent = this.getIntent();
        username = intent.getStringExtra("username");

        reservation_ll = (LinearLayout) findViewById(R.id.reservation_ll);
        report_ll = (LinearLayout) findViewById(R.id.report_ll);
        report_ll.setVisibility(View.GONE);

        username_tv = (TextView) findViewById(R.id.username_tv);
        username_tv.setText("当前就诊人："+ username);

        reservation_btn = (Button)findViewById(R.id.reservation_btn);
        report_btn = (Button)findViewById(R.id.report_btn);

        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir()
                .toString() + "/user_msg.db3" , null);
        Cursor cursor = db.rawQuery("select * from nucleic", null);
        int count=cursor.getCount();

        final String[] appointment_name = new String[count];
        final String[] appointment_time = new String[count];
        final String[] appointment_side = new String[count];
        int i=0;
        while(cursor.moveToNext()){
            if(username.equals(cursor.getString(1))){
                appointment_name[i]=cursor.getString(2);
                appointment_time[i]=cursor.getString(3);
                appointment_side[i]=cursor.getString(4);
                i++;
            }
        }

        final List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for( int num = 0;num< i;num++){
            Map<String,Object> map =new HashMap<String,Object>();
            map.put("appointment_name",appointment_name[num]);
            map.put("appointment_time",appointment_time[num]);
            map.put("appointment_side",appointment_side[num]);
            list.add(map);
        }

        reservation_nucleic = (ListView)findViewById(R.id.reservation_nucleic);
        final SimpleAdapter adapter = new SimpleAdapter(this,list ,R.layout.nucleic_item,
                new String[]{"appointment_name","appointment_time","appointment_side"},
                new int[]{R.id.appointment_name,R.id.appointment_time,R.id.appointment_side});
        reservation_nucleic.setAdapter(adapter);

        reservation_nucleic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//              左边类型
                final String name = appointment_name[position];
                String time = appointment_time[position];
                String side = appointment_side[position];
                AlertDialog alertDialog = new AlertDialog.Builder(NucleicActivity.this)
                        .setTitle("核酸检测预约")
                        .setMessage("姓名："+name+"\r\n"
                                +"预约时间："+time+"\r\n"
                                +"预约地点："+side+"\r\n")
                        .setIcon(R.mipmap.ic)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .setNegativeButton("取消预约", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String sql = "delete from nucleic where appointment_name = '"+name+"'";
                                db.execSQL(sql);
                                list.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        reservation_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View source) {
                report_btn.setTextColor(Color.parseColor("#008080"));
                report_btn.setBackgroundColor(Color.parseColor("#ffffff"));
                reservation_btn.setTextColor(Color.parseColor("#000000"));
                reservation_btn.setBackgroundColor(Color.parseColor("#66cccc"));
                reservation_ll.setVisibility(View.VISIBLE);
                report_ll.setVisibility(View.GONE);
            }
        });
        report_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View source) {
                report_btn.setTextColor(Color.parseColor("#000000"));
                report_btn.setBackgroundColor(Color.parseColor("#66cccc"));
                reservation_btn.setTextColor(Color.parseColor("#008080"));
                reservation_btn.setBackgroundColor(Color.parseColor("#ffffff"));
                reservation_ll.setVisibility(View.GONE);
                report_ll.setVisibility(View.VISIBLE);
            }
        });
    }
}
