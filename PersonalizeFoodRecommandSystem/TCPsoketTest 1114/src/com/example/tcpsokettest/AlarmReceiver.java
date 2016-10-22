package com.example.tcpsokettest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import bean.Food;

public class AlarmReceiver extends Activity {
	 
	private Food selected_food;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		registerReceiver(mbr,new IntentFilter());
		
	}
	
	private BroadcastReceiver mbr=new BroadcastReceiver(){
		public void onReceive(Context context, Intent intent) {//알람 시간이 되었을때 onReceive를 호출함
			  
			 Toast.makeText(context, "alarm", Toast.LENGTH_LONG).show();
			 
			 SharedPreferences preferences = getSharedPreferences("shared", 0);
			 String val = preferences.getString("selected", null);

			 if (val != null) {
				 Log.e("selected",val);
			 }

				 
	 		 //NotificationManager 안드로이드 상태바에 메세지를 던지기위한 서비스 불러오고 
			 NotificationManager nm = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
			
			 //그메세지를 클릭했을때 불러올엑티비티를 설정함 - NotificationFollower
			 Intent intentActivity = new Intent(context, NotificationFollower.class);
			 intentActivity.putExtra("selected_food", selected_food);
			 intentActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
			                | Intent.FLAG_ACTIVITY_CLEAR_TOP
			                | Intent.FLAG_ACTIVITY_SINGLE_TOP); // 또 같은 액티비티를 부를 경우 위에 것을 재활용
			 PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentActivity, PendingIntent.FLAG_UPDATE_CURRENT);
			   
			 //상단바에 뜨는 메세지 내용 
			 Notification notification = new Notification(R.drawable.smarttable_logo,
						"Smart Table", System.currentTimeMillis());  //현재 생성 시간
			      
			   //setLatestEventInfo-이 메세지를 클릭하면 서비를 실행/중단시키는 액티빝로 이동
					// 알림메세지 제목/메세지내용/객체
			   notification.setLatestEventInfo(context,  "음식은 어떠셨나요?",
						"평점을 남겨주세요!", pendingIntent);
			   notification.flags |= Notification.FLAG_AUTO_CANCEL;
			   nm.notify(1, notification);
			  }
			 };
	}
	
