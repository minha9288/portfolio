package com.mmouse.mm.RMate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * Created by Bagwaahai on 7/7/2014.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getBooleanExtra("reminder",false))
        {
            Toast.makeText(context, "You have 1 minute left !", Toast.LENGTH_LONG).show();
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
        }else {
            Toast.makeText(context, "Time is up !", Toast.LENGTH_LONG).show();
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
        }
    }
}
