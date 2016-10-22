package com.khh.ddayoddayo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class Stage2Activity extends Activity implements OnTouchListener {

	//private float x =0.0f;
 	//private float y =0.0f;
 	//private float x2 = -1;
	//private float y2 = -1;
	
	String tag;
	
	int num=0;
	int time=30;
	int ttime=0;
	long a;
	int random=0;
	
	int colanumber = 0;
	int charnumber = 0;
	
	int total = 0;
	
	public static MediaPlayer bgm_stage;
	
	public SoundPool soundpool;
	int efs_yogurt,efs_sham, efs_trash, efs_cola, efs_milk, efs_error, efs_turn; //각 스테이지별 효과음
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_stage_cola);
	    // TODO Auto-generated method stub
	    //c1 = (ImageView)findViewById(R.id.c1);
	    setContentView(new MyView(this));
	    
	    soundpool = new SoundPool(7, AudioManager.STREAM_MUSIC, 0); 
	    efs_yogurt = soundpool.load(this, R.raw.efs_yogurt, 1);
	    efs_cola = soundpool.load(this, R.raw.efs_cola, 1);
		efs_sham = soundpool.load(this, R.raw.efs_sham, 1);
		efs_milk = soundpool.load(this, R.raw.efs_milk, 1);
		efs_trash = soundpool.load(this, R.raw.efs_trash, 1);
		efs_error = soundpool.load(this, R.raw.efs_error, 1);
		efs_turn = soundpool.load(this, R.raw.efs_turn, 1);
		
		if(MainActivity.bgmonoff==true){
			bgm_stage = MediaPlayer.create(this, R.raw.stage_bgm);
			bgm_stage.setLooping(true);
			bgm_stage.start();
		}
		
	}
	
	protected class MyView extends View{
		int width, height;
    	private float x =0.0f;
    	private float y =0.0f;
    	private float x2 = -1;
    	private float y2 = -1;

    	Bitmap back;
    	
    	Bitmap[] cola = new Bitmap[5];
    	Bitmap[] cola_char = new Bitmap[7];
    	
		private Paint paint;
		boolean opencheck = false;
		boolean changecheck = false;
    	boolean gamecheck = false;
    	boolean touch = false; //눌렸는냐 안눌렸느냐
    	
    	int state2[] = {0,1,2};
    	int state = 0;
    	
    	int cola_x = 1070;
    	
    	public MyView(Context context) {
    		super(context);
    		cola[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_img1);
    		cola[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_img3);
    		cola[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_img4);
    		cola[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_img5);
    		
    		cola_char[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_char1);
    		cola_char[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_char2);
    		cola_char[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_char3);
    		cola_char[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_char4);
    		cola_char[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_char5);
    		cola_char[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_char6);
    		cola_char[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_char7);
    		back = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_bgimg);

    	}
    	
    	public void onDraw(Canvas canvas) {
    		
    		canvas.drawBitmap(back, 0, 0, null);
 
    		canvas.drawBitmap(cola[colanumber],1070, 540, null);
    		//canvas.drawBitmap(cola[2],0, 0, null); //콜라따진거는 화면에 다 차는걸로
    		canvas.drawBitmap(cola_char[charnumber], 0, 0, null);
    		

    		Paint paint = new Paint();
    		paint.setTypeface(Typeface.create(" ", Typeface.BOLD));
    		paint.setTextSize(80);
			paint.setColor(Color.BLACK);
	                                                 
    		//String score = Integer.toString(num);
			canvas.drawText("점수 : "+total, 100, 250, paint);
			
			
			if(time==1){
				if(selectcoke3(total)){
					Intent intent = new Intent(Stage2Activity.this, ScoreActivity.class);
					intent.putExtra("stage", 2);
					intent.putExtra("totalscore", total);
					finish();
					startActivity(intent);
					bgm_stage.stop();
				}
				else{
					Intent intent = new Intent(Stage2Activity.this, ScoreActivity2.class);
					intent.putExtra("totalscore", total);
					finish();
					startActivity(intent);
					bgm_stage.stop();
				}
    		}
			
			if(ttime==1){
				a=System.currentTimeMillis();
			}
			ttime = ttime+1;
			long b = System.currentTimeMillis();
			time = (int)(30-(b-a)/1000);
			
			canvas.drawText("시간 : "+time, 100, 100, paint);
			postInvalidate();
				
			
			if(changecheck){
    			canvas.drawBitmap(cola[3], cola_x, 540, paint);   
    			System.out.println("@!");
    			changecheck = false;
			}
			if(opencheck){
    			canvas.drawBitmap(cola[2], 1070, 540, null);  
    			opencheck=false;
    			changecheck = false;
    			
			}
    	}
    	
    	public boolean onTouchEvent(MotionEvent event) {
    		int move = event.getAction();
    		float x = event.getX();
    		float y = event.getY();
	
    		System.out.println(x+","+y);
    		switch(move){

    		case MotionEvent.ACTION_DOWN:
    			if(colanumber==0){                //따질 떄
    				int x2 = (int)event.getX();
    	    		int y2 = (int)event.getY();
    	    		if(x2>1150 && x2<1350 && y2>500 && y2<700 )
    	    		{
    	    			opencheck = false;
    	    		}
    			}
    			if(colanumber==3){               //버릴 때
    				int x2 = (int)event.getX();
    	    		int y2 = (int)event.getY();
    	    		if(x2>1200 && x2<1400 && y2>500 && y2<1000)
    	    		{
    	    			touch = true;
    	    		}
    			}
    			if(colanumber==4){               //바꿀 때
    				int x2 = (int)event.getX();
    	    		int y2 = (int)event.getY();
    	    		if(x2>800 && x2<1200 && y2>550 && y2<1000)
    	    		{
    	    			touch = true;
    	    		}
    			}

    			break;
    			
    		case MotionEvent.ACTION_MOVE :
    			if(colanumber==3){
					x = (int)event.getX();
					y = (int)event.getY();
					
					if(x<1270 && x>900){
						cola_x = (int)x - 200;
						changecheck = true;
						System.out.println("ㅠㅠ");
					}
					if(x<900)
						cola_x = 650;	
					System.out.println("ㅠ2ㅠ");
				}
    			break;	
				
    		case MotionEvent.ACTION_UP :    	
    			if(colanumber==0){                  //정상일때. 처음에 올렸을 때
    				int x2 = (int)event.getX();
    	    		int y2 = (int)event.getY();
    	    		if(x2>1250 && x2<1400 && y2<550)
    	    		{
    	    			total = total+300;
    	    			num++;
    	    			if(MainActivity.efsonoff==true){  
    	    				soundpool.play(efs_cola, 1, 1, 0, 0, 1);  
     					}
    	    			opencheck = true;
    	    			if(num%3==0){
							if(charnumber<6)
								charnumber++;
							else if(charnumber>=6)
								charnumber=6;
    	    			}
    	    			Handler handler = new Handler();
						Runnable run = new Runnable(){
							public void run(){

								random = (int) (Math.random()*10)+1;
								if(random<6) {
									colanumber=0;
									
								}
								else if(random>5 && random<9){ //6.7.8
									
									colanumber=3;	
								}
								else{//9.10.11
									colanumber=4;
		 						}
								invalidate(); 
							}
						};
						handler.postDelayed(run, 200); 
						
    	    		}
    	    		
    	    		 else if(x2<1010){
    	    		 
    	    			total = total-100;
    	    			if(MainActivity.efsonoff==true){  
    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
     					}
    	    		}
    			}
    			//버릴때
    			if(colanumber==3){
    				int x2 = (int)event.getX();
    	    		int y2 = (int)event.getY();
    	    		if(x2>800 && x2<1500 && y2>600 && y2<1000)
    	    		{
    	    			total = total+200;
    	    			num++;
    	    			if(MainActivity.efsonoff==true){  
    	    				soundpool.play(efs_trash, 1, 1, 0, 0, 1);  
     					}
    	    			System.out.println("왜안움직여");
    	    			if(num%3==0){
							if(charnumber<6)
								charnumber++;
							else if(charnumber>=6)
								charnumber=6;
						}
    	    			
    	    			Handler handler = new Handler();
						Runnable run = new Runnable(){
							public void run(){

								random = (int) (Math.random()*10)+1;
								if(random<6) {
									colanumber=0;
								}
								else if(random>5 && random<9){ 
									colanumber=3;
								}
								else{
									colanumber=4; 
								}
								invalidate(); 
							}
						};
						handler.postDelayed(run, 200); 
						
    	    		}
    	    		
    	    		else if(x2>1600){
       	    		 
    	    			total = total-100;
    	    			if(MainActivity.efsonoff==true){  
    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
    	    			}
    	    		}
    			}
    			if(colanumber==4){
    				int x2 = (int)event.getX();
    	    		int y2 = (int)event.getY();
    	    		if(x2>1000 && x2>1200 && y2>550 && y2<1000)
    	    		{
    	    			total = total+400;
    	    			num++;
    	    			if(MainActivity.efsonoff==true){  
    	    				soundpool.play(efs_turn, 1, 1, 0, 0, 1);  
     					}
    	    			if(num%3==0){
							if(charnumber<6)
								charnumber++;
							else if(charnumber>=6)
								charnumber=6;
						}
    	    			Handler handler = new Handler();
						Runnable run = new Runnable(){
							public void run(){

								random = (int) (Math.random()*10)+1;
								if(random<6) {
									colanumber=0;
								}
								else if(random>5 && random<9){
									colanumber=3;
								}
								else{
									colanumber=4; 
								}							
								invalidate(); 
							}
						};
						handler.postDelayed(run, 200); 	
    	    		}
    	    		else if(x2<900 && y2<1020){
          	    		 
    	    			total = total-100;
    	    			if(MainActivity.efsonoff==true){  
    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
     					}
    	    			
    	    		}
    			}
    			else
    				touch = true;
    			break;
    		}
    		
	return true;
}
	}
	
	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/**
	 * Back버튼 눌렀을 때 다이얼로그 확인
	 * 확인버튼 : 메인 액티비티로 이동
	 * 취소버튼 : 현재 액티비티 유지
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event){
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder alert = new AlertDialog.Builder(Stage2Activity.this);
			alert.setMessage("메인으로 돌아가시겠습니까?").setPositiveButton("확인", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					Intent intent = new Intent(Stage2Activity.this, MainActivity.class);
					MainActivity.splash = false;
					finish();
					bgm_stage.stop();
					startActivity(intent);
				}
			}).setNegativeButton("취소", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			AlertDialog backdialog = alert.create();
			backdialog.show();
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	/**
	 * 게임을 종료하면 기존 랭킹에 있는 3위의 점수와 게임점수를 비교한다.
	 * 게임점수가 3위보다 크면 true를, 3위보다 작으면 false를 반환한다.
	 * true를 반환받으면 이름 등록 창을, false를 반환받으면 점수 확인 창을 띄우는 코딩을 하도록...
	 * @param gamescore 게임점수
	 * @return true(이름을 등록 할 수 있는 창을 띄워라) or false(점수 확인 창을 띄워라)
	 */
	boolean selectcoke3(int gamescore) {
		Cursor c1 = MainActivity.db.rawQuery("select * from coke", null);
		c1.moveToFirst(); c1.moveToNext();
		c1.moveToNext();
		
		int score3 = c1.getInt(3);
		c1.close();
		
		if(gamescore > score3)
			return true;
		else
			return false;
	} // 콜라

}
