package com.khh.ddayoddayo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


//ㅡㅡ스플래시 액티비티ㅡㅡ
public class SplashActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_splash);
	
	    Handler handler = new Handler(){
	    	public void handleMessage(Message msg){
	    		MainActivity.splash = false;
	    		finish();
	    		Intent intent = new Intent(SplashActivity.this, MainActivity.class);
	    		startActivity(intent);
	    	}
	    };
	    handler.sendEmptyMessageDelayed(0, 2000); //3초동안 유지. (1000milisec=1sec)
	    
	    // TODO Auto-generated method stub
	}

}
