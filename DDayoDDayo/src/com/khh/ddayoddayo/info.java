package com.khh.ddayoddayo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class info extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.activity_infro);
	    
	    
	    ImageView exitbt = (ImageView)findViewById(R.id.exitbt);
	    
	    exitbt.setOnClickListener(new View.OnClickListener() {
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
