package com.khh.ddayoddayo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class how_ran extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.how_ran);

	    ImageView exit_bt = (ImageView)findViewById(R.id.exit_bt);
	    exit_bt.setOnClickListener(new View.OnClickListener() { /////exit¶û start°©ÀÚ±â ¹Ù²ñ
 			public void onClick(View v) {
 				finish();
 			}
 		});
	}
	
	public void onResume(){ 
		super.onResume();
		MainActivity.bgm.start();
	}
	
	public void onRestart(){
		super.onRestart();	
		MainActivity.bgm.start();
	}
	
	public void onPause(){ 
		super.onPause();		
		MainActivity.bgm.pause();	
	}
}
