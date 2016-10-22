package com.mmouse.mm.RMate;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Amyhee on 2014. 7. 1..
 */
public class SplashActivity extends Activity {
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        setContentView(R.layout.splash);

        Handler handler = new Handler(){
            public void handleMessage(Message msg)
            {
                finish();
            }
        };

        handler.sendEmptyMessageDelayed(0,3000);
    }
}
