package com.khh.ddayoddayo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class ExitActivity extends Activity {
	
	 public static ArrayList<Activity> acList;

	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
	    
	    acList = new ArrayList<Activity>();
		
		for(int i=0 ; i<acList.size() ; i++)
			acList.get(i).finish();
	
	}
	
}
