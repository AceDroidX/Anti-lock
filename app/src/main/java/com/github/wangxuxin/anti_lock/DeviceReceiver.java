package com.github.wangxuxin.anti_lock;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by a1274 on 2017/7/11.
 */
public class DeviceReceiver extends DeviceAdminReceiver {

    @Override
    public void onEnabled(Context context, Intent intent) {
        // 设备管理：可用
        MyToast.makeText(context, "设备管理：可用", Toast.LENGTH_SHORT);
    }

    @Override
    public void onDisabled(final Context context, Intent intent) {
        // 设备管理：不可用
        MyToast.makeText(context, "设备管理：不可用", Toast.LENGTH_SHORT);
        //如果取消了激活就再次提示激活
        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DeviceMethod.getInstance(context.getApplicationContext()).onActivate();
            }
        }, 3000);
        */
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
           /* // 这里处理 不可编辑设备。这里可以造成死机状态
            Intent intent2 = new Intent(context, NoticeSetting.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);
            context.stopService(intent);// 是否可以停止*/

        return "确定要取消吗(\"▔□▔)/\n好基(姬)友/父母将会锁住您的手机 影响您的手机安全!!!(划掉（￣▽￣）)\n这将会关闭" + context.getString(R.string.app_name) + "的各项功能";
    }

    @Override
    public void onPasswordChanged(Context context, Intent intent) {
        // 设备管理：密码己经改变
        MyToast.makeText(context, "设备管理：密码己经改变", Toast.LENGTH_SHORT);
    }

    @Override
    public void onPasswordFailed(Context context, Intent intent) {
        // 设备管理：改变密码失败
        MyToast.makeText(context, "设备管理：改变密码失败", Toast.LENGTH_SHORT);
    }

    @Override
    public void onPasswordSucceeded(Context context, Intent intent) {
        // 设备管理：改变密码成功
        MyToast.makeText(context, "设备管理：改变密码成功", Toast.LENGTH_SHORT);
    }
}
