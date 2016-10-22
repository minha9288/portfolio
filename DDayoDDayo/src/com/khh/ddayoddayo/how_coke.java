package com.khh.ddayoddayo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class how_coke extends Activity implements OnClickListener {

	private int imageIndex = 0;
	
	private ImageView right;
	private ImageView left;
	private ImageView c1; 
	private ImageView c2;
	private ImageView c3;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.how_coke);
	    
	    right = (ImageView)findViewById(R.id.right);
        right.setOnClickListener(this);
        left = (ImageView)findViewById(R.id.left);
        left.setOnClickListener(this);
        left.setVisibility(View.INVISIBLE);
        
        c1 = (ImageView)findViewById(R.id.c1);
        c2 = (ImageView)findViewById(R.id.c2);
        c3 = (ImageView)findViewById(R.id.c3);

        ImageView exit_bt = (ImageView)findViewById(R.id.exit_bt);
        exit_bt.setOnClickListener(new View.OnClickListener() { /////exit¶û start°©ÀÚ±â ¹Ù²ñ
 			public void onClick(View v) {
 				finish();
 			}
 		});
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.right:
			if(imageIndex < 2){ //0¿¡¼­ 1ÀÌ µÊ
				imageIndex ++;
			}
			changeImage();
			break;
			
		case R.id.left:
			if(imageIndex > 0){
				imageIndex --;
			}
			changeImage();
			break;
		}
	}
	private void changeImage(){
		if (imageIndex == 0) {
			c1.setVisibility(View.VISIBLE);
			c2.setVisibility(View.INVISIBLE);
			c3.setVisibility(View.INVISIBLE);
			
			left.setVisibility(View.INVISIBLE);
			
		}else if (imageIndex == 1){
			c1.setVisibility(View.INVISIBLE);
			c2.setVisibility(View.VISIBLE);
			c3.setVisibility(View.INVISIBLE);
			
			left.setVisibility(View.VISIBLE);
			right.setVisibility(View.VISIBLE);
		}else if(imageIndex == 2){
			c1.setVisibility(View.INVISIBLE);
			c2.setVisibility(View.INVISIBLE);
			c3.setVisibility(View.VISIBLE);
			
			right.setVisibility(View.INVISIBLE);
		}
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
