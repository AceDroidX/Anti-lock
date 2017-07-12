package com.github.wangxuxin.anti_lock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by a1274 on 2017/7/12.
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button button_Iknow = (Button) findViewById(R.id.button_Iknow);
        Button button_agree = (Button) findViewById(R.id.button_agree);
        Button button_disagree = (Button) findViewById(R.id.button_disagree);
        LinearLayout linearLayout_welcome = (LinearLayout) findViewById(R.id.linearLayout_welcome);

        //是否第一次打开
        if(isFirstOpen()){
            button_Iknow.setVisibility(8);
            button_disagree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.exit(0);
                }
            });
            button_agree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //1、打开Preferences，名称为setting，如果存在则打开它，否则创建新的Preferences
                    SharedPreferences isFirstOpen = getSharedPreferences("data", 0);
                    //2、让setting处于编辑状态
                    SharedPreferences.Editor editor = isFirstOpen.edit();
                    //3、存放数据
                    editor.putString("isFirstOpen","false");
                    //4、完成提交
                    editor.apply();
                    finish();
                    Intent intent = new Intent();
                    //intent.putExtra("type",type+"/"+l);
                    intent.setClass(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }else {
            linearLayout_welcome.setVisibility(8);
            button_Iknow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    Intent intent = new Intent();
                    //intent.putExtra("type",type+"/"+l);
                    intent.setClass(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
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
