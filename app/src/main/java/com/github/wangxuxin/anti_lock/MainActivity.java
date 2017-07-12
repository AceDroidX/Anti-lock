package com.github.wangxuxin.anti_lock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DeviceMethod deviceMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch switch_program = (Switch) findViewById(R.id.switch_program);
        Switch switch_debug = (Switch) findViewById(R.id.switch_debug);

        //是否第一次打开
        if(isFirstOpen()){
            finish();
            Intent intent = new Intent();
            //intent.putExtra("type",type+"/"+l);
            intent.setClass(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }

        deviceMethod = new DeviceMethod(getApplicationContext());

        //是否授权
        TextView isAuthorize = (TextView) findViewById(R.id.textIsAuthorize);
        Button buttonMain = (Button) findViewById(R.id.buttonMain);
        if(deviceMethod.isActivate()){
            isAuthorize.setText("是否授权：已授权");
            buttonMain.setVisibility(8);
        }else{
            isAuthorize.setText("是否授权：未授权");
            buttonMain.setVisibility(0);
            buttonMain.setText("点击授权");
            buttonMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deviceMethod.onActivate();
                    finish();
                }
            });
        }

        //开关初始化
        if(getSharedPreferences("data", 0).getString("switch_program","null").equals("true")){
            switch_program.setChecked(true);
        }else {
            switch_program.setChecked(false);
        }
        if(getSharedPreferences("data", 0).getString("isDebug","null").equals("true")){
            switch_program.setChecked(true);
        }else {
            switch_program.setChecked(false);
        }

        //开关监听
        switch_program.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                //1、打开Preferences，名称为setting，如果存在则打开它，否则创建新的Preferences
                SharedPreferences isFirstOpen = getSharedPreferences("data", 0);
                //2、让setting处于编辑状态
                SharedPreferences.Editor editor = isFirstOpen.edit();
                //3、存放数据
                if(isChecked){
                    editor.putString("switch_program","true");
                }else {
                    editor.putString("switch_program","false");
                }
                //4、完成提交
                editor.apply();
            }
        });
        switch_debug.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                //1、打开Preferences，名称为setting，如果存在则打开它，否则创建新的Preferences
                SharedPreferences isFirstOpen = getSharedPreferences("data", 0);
                //2、让setting处于编辑状态
                SharedPreferences.Editor editor = isFirstOpen.edit();
                //3、存放数据
                if(isChecked){
                    editor.putString("isDebug","true");
                }else {
                    editor.putString("isDebug","false");
                }
                //4、完成提交
                editor.apply();
            }
        });

        //按钮监听
        Button buttonTest = (Button)findViewById(R.id.buttonTest);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deviceMethod.setPassword("233333");

            }
        });
        Button buttonAbout = (Button)findViewById(R.id.buttonAbout);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //intent.putExtra("type",type+"/"+l);
                intent.setClass(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
        Button button_help = (Button)findViewById(R.id.button_help);
        button_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //intent.putExtra("type",type+"/"+l);
                intent.setClass(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }

    //检测是否第一次打开
    private boolean isFirstOpen(){
        SharedPreferences isFirstOpen = getSharedPreferences("data", 0);
        if(isFirstOpen.getString("isFirstOpen","null").equals("false")){
            return false;
        }else {
            return true;
        }
    }
}
