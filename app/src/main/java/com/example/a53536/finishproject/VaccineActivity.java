package com.example.a53536.finishproject;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by 53536 on 2021/5/27.
 */

public class VaccineActivity extends AppCompatActivity {
    EditText name_et,ID_et,time_et;
    Spinner spinner;
    Button sure_btn,reset_btn;
    SQLiteDatabase db;
    String username,appointment_name,appointment_id,appointment_time,appointment_site;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccine_page);

        Intent intent = this.getIntent();
        username = intent.getStringExtra("username");

        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir()
                .toString() + "/user_msg.db3" , null);

        sure_btn = (Button)findViewById(R.id.sure_btn);
        reset_btn = (Button)findViewById(R.id.reset_btn);

        name_et = (EditText)findViewById(R.id.name_et);
        ID_et = (EditText)findViewById(R.id.id_et);
        time_et = (EditText)findViewById(R.id.time_et);

        time_et.setFocusable(false);
        time_et.setFocusableInTouchMode(false);

        spinner = (Spinner)findViewById(R.id.spinner);
        final String[] site = new String[]{"广科大第一附属医院","柳州市人民医院","柳州市中医医院",
                "城中区柳东卫生院","柳州市工人医院","柳州市妇幼保健院","柳江区人民医院","融水县人民医院"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,site);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView,View view,int i,long l){
                appointment_site=site[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });

        sure_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View source){
                appointment_name = name_et.getText().toString();
                appointment_id = ID_et.getText().toString();
                appointment_time = time_et.getText().toString();
                insertData(db , username , appointment_name, appointment_id,appointment_time,appointment_site);

            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View source){
                name_et.setText("");
                ID_et.setText("");
                time_et.setText("");
            }
        });
    }
    private void insertData(SQLiteDatabase db, String username , String appointment_name,String appointment_id,String appointment_time,String appointment_site ){
        if(appointment_name.equals("")||appointment_id.equals("")||appointment_time.equals("")||appointment_site.equals("")){
            AlertDialog alertDialog = new AlertDialog.Builder(VaccineActivity.this)
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
                            name_et.setText("");
                            ID_et.setText("");
                            time_et.setText("");
                        }
                    })
                    .create();
            alertDialog.show();
        }else {
            Cursor cursor = db.rawQuery("select * from vaccine", null);
            int flag = 0;
            while(cursor.moveToNext()){
                if(appointment_name.equals(cursor.getString(2))){
                    AlertDialog alertDialog = new AlertDialog.Builder(VaccineActivity.this)
                            .setTitle("预约情况")
                            .setMessage(cursor.getString(2)+"已经预约过啦")
                            .setIcon(R.mipmap.ic)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .setNegativeButton("重新填写", new DialogInterface.OnClickListener() {//添加取消
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    name_et.setText("");
                                    ID_et.setText("");
                                    time_et.setText("");
                                }
                            })
                            .create();
                    alertDialog.show();
                    flag = 1;
                    break;
                }
            }
            if(flag==0){
                db.execSQL("insert into vaccine values(null , ? , ?, ?, ?, ?)", new String[]{username,appointment_name,appointment_id,appointment_time,appointment_site});
                Toast.makeText(VaccineActivity.this,"预约成功",Toast.LENGTH_SHORT).show();
                name_et.setText("");
                ID_et.setText("");
                time_et.setText("");
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onclick(View v){
        Calendar calendar=Calendar.getInstance();
        final int now_year = calendar.get(Calendar.YEAR);
        final int now_month = calendar.get(Calendar.MONTH)+1;
        final int now_day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(year<now_year||(year==now_year&&month+1<now_month)||
                        (year==now_year&&month+1==now_month&&dayOfMonth<now_day)){
                    Toast.makeText( VaccineActivity.this, "你必须选择正确的时间", Toast.LENGTH_SHORT ).show();
                }else{
                    String text = "你选择了：" + year + "年" + (month + 1) + "月" + dayOfMonth + "日";
                    time_et.setText(year + "年" + (month + 1) + "月" + dayOfMonth + "日");
                    Toast.makeText( VaccineActivity.this, text, Toast.LENGTH_SHORT ).show();
                }

            }
        }
                ,calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH)
                ,calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
