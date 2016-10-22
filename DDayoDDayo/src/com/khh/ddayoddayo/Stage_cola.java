package com.khh.ddayoddayo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class Stage_cola extends Activity implements OnTouchListener {

	private float x =0.0f;
 	private float y =0.0f;
 	private float x2 = -1;
	private float y2 = -1;
	//private ImageView c1;
	String tag;
	
	int num=0;
	int time=30;
	int ttime=0;
	long a;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_stage_cola);
	    //setContentView(new MyView(this));
	    
	}
	
	/*
	protected class MyView extends View{
		int width, height;
    	private float x =0.0f;
    	private float y =0.0f;
    	private float x2 = -1;
    	private float y2 = -1;
    	Bitmap cola;
    	Bitmap cola2;
    	Bitmap score;
    	Bitmap back;
    	Bitmap open;
    	Bitmap gameover;
		private Paint paint;
		boolean opencheck_left = false;
		boolean opencheck_right = false;
    	boolean gamecheck = false;
    	
    	
    	public MyView(Context context) {
    		super(context);
    		cola2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_img);
    		back = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_bgimg);
    		open = BitmapFactory.decodeResource(context.getResources(), R.drawable.cokeopen_img); //따진것
    	}
    	
    	public void onDraw(Canvas canvas) {
    		
    		canvas.drawBitmap(back, 0, 0, null);
    		canvas.drawBitmap(cola2, 1000, 580, paint);
    		
    		Paint paint = new Paint();
    		paint.setTypeface(Typeface.create(" ", Typeface.BOLD));
    		paint.setTextSize(65);
			paint.setColor(Color.BLACK);
			
    		String score = Integer.toString(num);
			canvas.drawText("점수 : "+score, 200, 220, paint);
			
			if(ttime==1){
				a=System.currentTimeMillis();
			}
			ttime = ttime+1;
			long b = System.currentTimeMillis();
			time = (int)(30-(b-a)/1000);
			
			canvas.drawText("시간 : "+time, 200, 120, paint);
			postInvalidate();
					
			if(opencheck_right){
    			canvas.drawBitmap(open, 1000, 580, paint);
    			Log.i(tag, "true!!");
    			num = num + 1;
    			opencheck_right=false;
    		}
    	}
    	
    	public boolean onTouchEvent(MotionEvent event) {
    		int move = event.getAction();
    		float x = event.getX();
    		float y = event.getY();
	
    		System.out.println("움직일까?");
    		switch(move){
    		case MotionEvent.ACTION_MOVE :
    			System.out.println(x+" , "+y);
    	
    			if(( x >= 1330 && x <= 1400) && ( y >= 660 && y <= 690)){
    				opencheck_right = true;
    				
    				Log.i(tag, "true!!?");
    			}
		invalidate();

		break;
	}
	return true;
}
	}
	
	
*/
	
	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}


	
}
