package com.example.tcpsokettest;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class check_activity extends Activity implements OnClickListener{

	Button setAlarm_bt;
	Button setNotification_bt;
	Button setPopup_bt;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.check);
	    final ImageView exit_bt = (ImageView)findViewById(R.id.exit);
	    
	    exit_bt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				createDialogBox();
			}
		});
	   
	}
	public AlertDialog createDialogBox(){
		AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Exit").setMessage("종료하시겠습니까?").setPositiveButton("네", new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialog, int which) {
				moveTaskToBack(true);
				android.os.Process.killProcess(android.os.Process.myPid());		
			}
		}).setNegativeButton("아니요", null).show();
		
		return dialog;
	}
	@Override
	public void onClick(View v) {		
//		 // 알람 매니저에 등록할 인텐트를 만듬        
//        Intent intent = new Intent(this, AlarmReceiver.class);
//        
//        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
//       
//        // 알람을 받을 시간을 5초 뒤로 설정
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.SECOND, 2);
//        
//        // 알람 매니저에 알람을 등록
//        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
	}
}
