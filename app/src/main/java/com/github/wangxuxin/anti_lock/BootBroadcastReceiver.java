package com.github.wangxuxin.anti_lock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by a1274 on 2017/7/12.
 */
public class BootBroadcastReceiver extends BroadcastReceiver {
    private static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            //Intent mainActivityIntent = new Intent(context, MainActivity.class);  // 要启动的Activity
            //mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //context.startActivity(mainActivityIntent);
            MyToast.makeText(context,"Anti-lock已启动",Toast.LENGTH_LONG);
            DeviceMethod deviceMethod = new DeviceMethod(context.getApplicationContext());
            if(deviceMethod.getCurrentFailedPasswordAttempts()>=5){
                deviceMethod.setPassword("233333");
            }
        }
    }
}
