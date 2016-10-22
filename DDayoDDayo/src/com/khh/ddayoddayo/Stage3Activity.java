package com.khh.ddayoddayo;


import android.app.Activity; 
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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
import android.hardware.Sensor; 
import android.hardware.SensorEvent; 
import android.hardware.SensorEventListener; 
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;   
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * 스테이지3 : 샴페인
 * 정상 샴페인 - 5번 흔들고 위쪽으로 드래그 (↑ / ↗)
 * 깨진 샴페인 - 샴페인 잡고 왼쪽으로 드래그 (←)
 *
 */
public class Stage3Activity extends Activity implements SensorEventListener {       
	private long lastTime;    
	private float speed;     
	private float lastX;   
	private float lastY;   
	private float lastZ; 
	private float x, y, z;  
	private static final int SHAKE_THRESHOLD = 800;  
	private static final int DATA_X = SensorManager.DATA_X;  
	private static final int DATA_Y = SensorManager.DATA_Y;  
	private static final int DATA_Z = SensorManager.DATA_Z;  
	private SensorManager sensorManager;  
	private Sensor accelerormeterSensor;   
	int shake=0;
	int success=0;
	boolean shakecheck=false;
	boolean dragcheck1=false, dragcheck2=false;
	boolean normal_error1=false, normal_error2=false, trash_error=false, shake_error=false;
	int time = 60;
	int ttime=0;
	long a;
	int sham_index = 0;
	int random = 0;
	
	int shamx=1000;
	int total = 0;

	public SoundPool soundpool;
	int efs_sham, efs_trash, efs_error;
	    
	protected void onCreate(Bundle savedInstanceState) {    
		super.onCreate(savedInstanceState);  
		setContentView(new Stage3View(this));
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);    
		accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); 
		
		soundpool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0); 
	    efs_sham = soundpool.load(this, R.raw.efs_sham, 1);
	    efs_trash = soundpool.load(this, R.raw.efs_trash, 1);             
	    efs_error = soundpool.load(this, R.raw.efs_error, 1);
	    
		if(MainActivity.bgmonoff==true){
			Stage2Activity.bgm_stage = MediaPlayer.create(this, R.raw.stage_bgm);
			Stage2Activity.bgm_stage.setLooping(true);
			Stage2Activity.bgm_stage.start();
		}
	}    
   
	
	class Stage3View extends View{
		Bitmap bg_img;

		Bitmap[] sham = new Bitmap[3];
		Bitmap[] char_draw = new Bitmap[7];
		int char_draw_index = 0;
		
		public Stage3View(Context context){
			super(context);
			bg_img = BitmapFactory.decodeResource(context.getResources(), R.drawable.sham_bgimg);
			sham[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sham_img);
			sham[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sham_img2);
			sham[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sham_img3);
			
			char_draw[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sham_char1);
			char_draw[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sham_char2);
			char_draw[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sham_char3);
			char_draw[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sham_char4);
			char_draw[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sham_char5);
			char_draw[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sham_char6);
			char_draw[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sham_char7);
		}
		
		public void onDraw(Canvas canvas){
			canvas.drawBitmap(bg_img, 0, 0, null);
			canvas.drawBitmap(sham[sham_index], shamx, 30, null);
			canvas.drawBitmap(char_draw[char_draw_index], 100, 100, null);
			
			if(time==1){
				if(selectsham3(total)==true){
					Intent intent = new Intent(Stage3Activity.this, ScoreActivity.class);
					intent.putExtra("stage", 3);
					intent.putExtra("totalscore", total);
					finish();
					startActivity(intent);
				}
				if(selectsham3(total)==false){
					Intent intent = new Intent(Stage3Activity.this, ScoreActivity2.class);
					intent.putExtra("totalscore", total);
					finish();
					startActivity(intent);
				}
				
    		}
			
			if(ttime==1){
				a=System.currentTimeMillis();
			}
			ttime = ttime+1;
			long b = System.currentTimeMillis();
			time = (int)(30-(b-a)/1000);
			
			Paint paint = new Paint();
			paint.setColor(Color.BLUE);
			paint.setTextSize(80);
			paint.setTypeface(Typeface.create("", Typeface.BOLD));
			canvas.drawText("시간" + time, 100, 100, paint);
			canvas.drawText("점수 " + total, 100, 250, paint);
			postInvalidate();
		}
		
		public boolean onTouchEvent(MotionEvent event){
			int action = event.getAction();
			
			switch(action){
			case MotionEvent.ACTION_DOWN :
				
				//정상 샴페인의 경우
				if(sham_index==0){
					
					x = (int)event.getX();
					y = (int)event.getY();
					
					//제대로했을경우
					if(shakecheck==true){						
						if(x>1000 && x<1600 && y>100 && y<1500){
							dragcheck1 = true;
						}
						else
							dragcheck1 = false;
					}
					//흔들어야되는데 버릴경우
					else if(shakecheck==false){
						Log.i("errortest", "여기안오나?");
						if(x>1150 && x<1600 && y>500){
							normal_error1 = true;
							Log.i("errortest", "normal_eror1 : "+normal_error1);
						}
						else if(x>1000 && x<1600 && y>100 && y<1500){
							normal_error1 = true;
						}
						else
							normal_error1 = false;
					}
				}
				
				
				//깨진 샴페인의 경우
				else if(sham_index==2){
					if(shakecheck==true){
						x = (int)event.getX();
						y = (int)event.getY();
						
						
						//제대로 했을경우
						if(x>1150 && x<1600 && y>500){
							dragcheck1 = true;
						}
						else
							dragcheck1 = false;
					
					}
				}

				break;
				
			case MotionEvent.ACTION_MOVE :
				if(sham_index==2){
					x = (int)event.getX();
					y = (int)event.getY();
					
					if(x<1000 && x>880){
						shamx = (int)x ;
					}
					if(x<880)
						shamx = 700;
				}
				break;
				
			case MotionEvent.ACTION_UP :
				//정상 샴페인의 경우
				if(sham_index==0){
					x = (int)event.getX();
					y = (int)event.getY();
					
					//흔들어야되는데 버릴경우
					if(shakecheck==false && normal_error1==true){
						if(x<920 && y>500)
							total-=100;
						if(MainActivity.efsonoff==true){  
    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
     					}
						else
							normal_error1 = false;
					}
					
					
					//제대로했을경우
					if(shakecheck==true && dragcheck1==true){
						if(x>1150 && x<1600 && y<200){
							dragcheck2 = true;
							shakecheck = false;
							
							sham_index = 1;
							invalidate();
							
							shake=0;
							success++;
							if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_sham, 1, 1, 0, 0, 1);  
	     					}
							total += 400;
							if(success%3==0)
								char_draw_index++;
							
							Handler handler = new Handler();
							Runnable run = new Runnable(){
								public void run(){
									random = (int) (Math.random()*10)+1;
									if(random<=6){
										shamx=1000;
										sham_index=0;
									}
									else{
										shamx=1000;
										sham_index=2;
									}
									invalidate(); 
								}
							};
							handler.postDelayed(run, 200);  
						}
						else{
							dragcheck2 = false;
							
						}
					}
				}
				//깨진 샴페인의 경우
				if(sham_index==2){
					x = (int)event.getX();
					y = (int)event.getY();
					
					//버려야하는데 올릴경우
					if(x>1150 && x<1600 && y<500){
						total -= 100;
						if(MainActivity.efsonoff==true){  
							soundpool.play(efs_error, 1, 1, 0, 0, 1);  
						}
					}

					//제대로 했을경우
					if(x<860 && y>500){
						dragcheck2 = true;
						shake=0;
						success++;
						if(MainActivity.efsonoff==true){  
    	    				soundpool.play(efs_trash, 1, 1, 0, 0, 1);  
     					}
						total += 300;
						if(success%3==0)
							char_draw_index++;

						Handler handler = new Handler();
						Runnable run = new Runnable(){
							public void run(){
								random = (int) (Math.random()*10)+1;
								if(random<=7){
									shamx=1000;
									sham_index=0;
								}
								else{
									shamx=1000;
									sham_index=2;
								}
								invalidate(); 
							}
						};
						handler.postDelayed(run, 100);  
					}
					else{
						dragcheck2 = false;
					}
				}
				break;
			}
			return true;
		}	
	}
	
	public void onStart() {      
		super.onStart();      
		if (accelerormeterSensor != null)      
			sensorManager.registerListener(this, accelerormeterSensor,   
					SensorManager.SENSOR_DELAY_GAME);  
		}     
	@Override  
	public void onStop() {   
		super.onStop();      
		if (sensorManager != null)    
			sensorManager.unregisterListener(this);  
		}   
	@Override  
	public void onAccuracyChanged(Sensor sensor, int accuracy) {   
		}    
	@Override    
	public void onSensorChanged(SensorEvent event) {  
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {    
			long currentTime = System.currentTimeMillis();      
			long gabOfTime = (currentTime - lastTime);          
			if (gabOfTime > 100) {            
				lastTime = currentTime;     
				x = event.values[SensorManager.DATA_X];        
				y = event.values[SensorManager.DATA_Y];        
				z = event.values[SensorManager.DATA_Z];       
				speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;      
				if (speed > SHAKE_THRESHOLD) {      
					// 이벤트발생!!                              
					lastX = event.values[DATA_X];  
					lastY = event.values[DATA_Y];  
					lastZ = event.values[DATA_Z];  
					shake ++;
					if(shake>=5){
						if(sham_index==2){
							if(shake==5){
								Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
								vibe.vibrate(500);
								total -= 100;
								if(MainActivity.efsonoff==true){  
		    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
		     					}
							}
						}
						else{
							shakecheck=true;
							if(shake==5 && sham_index==0){
								Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
								vibe.vibrate(500);
							}
						}
					}
				}        
			}     
		}
	}
	
	/**
	 * Back버튼 눌렀을 때 다이얼로그 확인
	 * 확인버튼 : 메인 액티비티로 이동
	 * 취소버튼 : 현재 액티비티 유지
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event){
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder alert = new AlertDialog.Builder(Stage3Activity.this);
			alert.setMessage("메인으로 돌아가시겠습니까?").setPositiveButton("확인", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					Intent intent = new Intent(Stage3Activity.this, MainActivity.class);
					MainActivity.splash = false;
					finish();
					Stage2Activity.bgm_stage.stop();
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
	boolean selectsham3(int gamescore) {
		Cursor c1 = MainActivity.db.rawQuery("select * from sham", null);
		c1.moveToFirst(); 
		c1.moveToNext();
		c1.moveToNext();
		
		int score3 = c1.getInt(3);
		c1.close();
		
		if(gamescore > score3)
			return true;
		else
			return false;
	} // 샴페인
	
}		
	