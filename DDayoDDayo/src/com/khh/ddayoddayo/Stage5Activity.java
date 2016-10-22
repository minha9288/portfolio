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
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Stage5Activity extends Activity implements SensorEventListener {
	
	//�ð�
	int time=30;
	int ttime=0;
	long a;
	//����
	int num=0; //��������
	int total=0;  //��������
	//ȿ����
	public SoundPool soundpool;
	int efs_sham, efs_trash, efs_error, efs_yogurt, efs_cola, efs_milk;

	//��ü ����
	int touch_x, touch_y ; //��ġ��ǥ
	int number=1; //ĵ �ε���
	int random=0;
	
	//�䱸��Ʈ
	boolean yo_point1=false, yo_point2=false;
	int straw_y=0; //������ġ
	
	
	//�ݶ�
	int x_cola, y_cola;
	
	//������
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
	boolean shakecheck=false;
	boolean sham_dragcheck1=false, sham_dragcheck2=false;
	int sham_index = 0;
	
	//����
	int milk_index=0;
	float[] multi_touch_x = new float[10];
	float[] multi_touch_y = new float[10];
	boolean[] touched = new boolean[10];
	boolean milk_normal_check1 = false, milk_normal_check2 = false;  //������ ��, ����� ��

	
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(new Stage1View(this));
	    
		//ȿ����
		soundpool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0); 
	    efs_sham = soundpool.load(this, R.raw.efs_sham, 1);
	    efs_trash = soundpool.load(this, R.raw.efs_trash, 1);             
	    efs_error = soundpool.load(this, R.raw.efs_error, 1);
	    efs_yogurt = soundpool.load(this, R.raw.efs_bbong, 1);            
	    efs_cola = soundpool.load(this, R.raw.efs_cola, 1);
	    efs_milk = soundpool.load(this, R.raw.efs_milk, 1);              
	    //�����
		if(MainActivity.bgmonoff==true){
			Stage2Activity.bgm_stage = MediaPlayer.create(this, R.raw.stage_bgm);
			Stage2Activity.bgm_stage.setLooping(true);
			Stage2Activity.bgm_stage.start();
		}
		
		//���⼾�� ��ü����
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);    
		accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); 
	}
	
	class Stage1View extends View{
		//���
		Bitmap back;
		//ĳ����
		Bitmap[] char_ran = new Bitmap[2];
		int charnumber=0;
		
		//�䱸��Ʈ
		Bitmap straw;
		Bitmap yogurt;
		
		//�ݶ�
		Bitmap [] cola = new Bitmap[2];
		boolean opencheck = false;
		int colanumber=0;
		
		//��
		Bitmap[] sham = new Bitmap[2];
		
		//����
		Bitmap[] milk = new Bitmap[3];
		
		
		
		public Stage1View(Context context){
			
			super(context);
			back = BitmapFactory.decodeResource(context.getResources(), R.drawable.random_bgimg);
			//ĳ����
			char_ran[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.random_char1);
			char_ran[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.random_char2);
			//�䱸��Ʈ
			yogurt = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_img);
			straw = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_bbal);
			
			//�ݶ�
			cola[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_img1);
    		cola[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coke_img3);
    		//��
    		sham[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sham_img);
			sham[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sham_img2);
    		
			//����
			milk[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img);  //�⺻
			milk[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img2);  //�⺻ - �Ա� ������
			milk[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img3);  //�⺻ - ���� ������
		}
		
		public void onDraw(Canvas canvas){
			
			canvas.drawBitmap(back, 0, 0, null);	
			canvas.drawBitmap(char_ran[charnumber], 80, 80, null);			
			
			Paint paint = new Paint();
			//�䱸��Ʈ
			if(number==1){
			
				canvas.drawBitmap(straw, 1350, straw_y, null);
				canvas.drawBitmap(yogurt, 1070, 540, null);
				
				if(yo_point1==true && yo_point2==true){
					yo_point1=false;
					yo_point2=false;
					Handler handler = new Handler();
					Runnable run = new Runnable(){
						public void run(){
							straw_y=0;
							invalidate();  //���� ����ġ�� �ٽñ׸���
						}
					};
					handler.postDelayed(run, 200);  //0.3�� ����
				}
				
			
			}	
			//�ݶ�
			else if(number==2){
				
				canvas.drawBitmap(cola[colanumber],1070, 540, null);
				
				
				if(opencheck){
	    			canvas.drawBitmap(cola[1], 1070, 540, null);  
	    			num++;
	    			opencheck=false;
	    			  			
				}
			}
			//������
			else if(number==3){
				canvas.drawBitmap(sham[sham_index], 1000, 30, null);
			}
			//����
			else if(number==4){
				canvas.drawBitmap(milk[milk_index], 1105, 385, null);
			}
			paint.setTypeface(Typeface.create(" ", Typeface.BOLD));
    		paint.setTextSize(80);
			paint.setColor(Color.BLACK);
	                                                 
			canvas.drawText("���� : "+total, 100, 250, paint);
			canvas.drawText("�ð� " + time, 100, 100, paint);

			
			if(time==1){	
				if(selectwine3(total)){
					Intent intent = new Intent(Stage5Activity.this, ScoreActivity.class);
					intent.putExtra("stage", 5);
					intent.putExtra("totalscore", total);
					finish();
					startActivity(intent);
				}
				if(selectwine3(total)==false){
					Intent intent = new Intent(Stage5Activity.this, ScoreActivity2.class);
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
			time = (int)(45-(b-a)/1000);
			postInvalidate();
		}
		
		
		public boolean onTouchEvent(MotionEvent event){
			int action = event.getAction() & MotionEvent.ACTION_MASK;
		     int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK);
		     pointerIndex = pointerIndex >> MotionEvent.ACTION_POINTER_ID_SHIFT;
		     int pointerId = event.getPointerId(pointerIndex);

			switch(action){
			case MotionEvent.ACTION_DOWN :
			case MotionEvent.ACTION_POINTER_DOWN:
				//�䱸��Ʈ
				if(number==1){                
					touch_x = (int)event.getX();
					touch_y = (int)event.getY();

					if(touch_x>1150 && touch_x<1600 && touch_y<400)
						yo_point1 = true;
					else
						yo_point1 = false;

				}
				
				//�ݶ�
				if(number==2){				  
					int x2 = (int)event.getX();
    	    		int y2 = (int)event.getY();
    	    		if(x2>1150 && x2<1350 && y2>500 && y2<700 )
    	    		{
    	    			opencheck = false;
    	    		}
				}
				//������
				if(number==3){
					touch_x = (int)event.getX();
					touch_y = (int)event.getY();
					
					Log.i("test", "����1");
					if(shakecheck==true){						
						if(touch_x>1000 && touch_x<1600 && touch_y>100 && touch_y<1500){
							Log.i("test", "����22");
							sham_dragcheck1 = true;
						}
						else
							sham_dragcheck1 = false;
					}
				}
				//����
				if(number==4){
					touched[pointerId] = true;
					multi_touch_x[pointerId] = (int)event.getX(pointerIndex);
					multi_touch_y[pointerId] = (int)event.getY(pointerIndex);
					
					if(multi_touch_x[0] > 1100 && multi_touch_x[0] < 1600 && multi_touch_x[1] > 1100 && multi_touch_x[1] < 1600
			 				 && multi_touch_y[0] > 400 && multi_touch_y[0] < 800 && multi_touch_y[1] > 400 && multi_touch_y[1] < 800){
		    			 milk_normal_check1 = true;
		    		 }
				}
				break;	 
				
			case MotionEvent.ACTION_MOVE :
				if(number==4){
					if(milk_index==0 && milk_normal_check1==true){
			    		 int pointerCount = event.getPointerCount();
				    	 for(int i=0; i<pointerCount; i++){
				    		 pointerIndex = i;
				    		 pointerId = event.getPointerId(pointerIndex);
				    		 multi_touch_x[pointerId] = (int)event.getX(pointerIndex);
				    		 multi_touch_y[pointerId] = (int)event.getY(pointerIndex);
				    		 
				    		 if(multi_touch_x[0] > 1500 && multi_touch_x[1] < 1300 && multi_touch_y[0] < 600 && multi_touch_y[1] < 600){
				    			 milk_normal_check2 = true;
				    			 milk_index = 1;
				    		 }
				    		 
				    		 if(multi_touch_x[0] < 1300 & multi_touch_x[1] > 1500 && multi_touch_y[0] < 600 && multi_touch_y[1] < 600){
				    			 milk_normal_check2 = true;
				    			 milk_index = 1;
				    		 }
				    		 else
				    			 milk_normal_check2 = false;
				    	 }
			    	 }
				}
				break;
	
			case MotionEvent.ACTION_UP :
				//�䱸��Ʈ 
				if(number==1){
					touch_x = (int)event.getX();
					touch_y = (int)event.getY();
					
					if(yo_point1 == true ){
						if(touch_x>1000 && touch_x<1600 && touch_y>400 && touch_y<1500){
							//point1 = false;
							yo_point2 = true;
							straw_y+=400;
							num++;
							total += 200;
							
							if(MainActivity.efsonoff==true){  
								soundpool.play(efs_yogurt, 1, 1, 0, 0, 1);  
	     					}
							
							//6�� ������ ��� �ٲ�� -ĳ����
	    	    			if(num%6==0){ 
	    	    				charnumber=1;	
	    	    			}
							
							Handler handler = new Handler();
							Runnable run = new Runnable(){
								public void run(){

									random = (int) (Math.random()*10)+1;
									if(random<3) {
										number=1;
									}
									else if(random>2 && random<6){
										number=2;
									}
									else if(random>5 && random<9){ 
										
										//number = 1; 
										number=3;	
										sham_index=0;
									}
									else{
										number=4;
										milk_index=0;
			 						}
									invalidate(); 
								}
							};
							handler.postDelayed(run, 200);  //0.3�� ����
						}
						else {
							yo_point1 = false;
							yo_point2 = false;
						}
					}
				}
				
				
				
				//�ݶ�
				else if(number==2){
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
	    	    			//6�� ������ ��� �ٲ�� -ĳ����
	    	    			if(num%6==0){ 
	    	    				charnumber=1;
	    	    			}

	    	    			//���ο� ���� ��������
	    	    			Handler handler = new Handler();
							Runnable run = new Runnable(){
								public void run(){
									random = (int) (Math.random()*10)+1;
									if(random<3) {
										number=1;
									}
									else if(random>2 && random<6){
										number=2;
									}
									else if(random>5 && random<9){ 
										number = 1;
										//number=3;	
										//sham_index=0;
									}
									else{
										number=4;
										milk_index=0;
			 						}
									invalidate(); 
								}
							};
							handler.postDelayed(run, 200); 

							
	    	    		}
	    	    		
				}

				//���� ������
				else if(number==3){
					touch_x = (int)event.getX();
					touch_y = (int)event.getY();
					
					if(shakecheck==true && sham_dragcheck1==true){
						Log.i("test", "����33");
						if(touch_x>1150 && touch_x<1600 && touch_y<200){
							sham_dragcheck2 = true;
							shakecheck = false;
							
							sham_index = 1;
							invalidate();
							
							shake=0;
							num++;
							if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_sham, 1, 1, 0, 0, 1);  
	     					}
							total += 400;
							
							//6�� ������ ��� �ٲ�� -ĳ����
	    	    			if(num%6==0){ 
	    	    				charnumber=3;	
	    	    			}
							
	    	    			//���ο� ���� ��������
							Handler handler = new Handler();
							Runnable run = new Runnable(){
								public void run(){
									random = (int) (Math.random()*10)+1;
									if(random<3) {
										number=1;
									}
									else if(random>2 && random<6){
										number=2;
									}
									else if(random>5 && random<9){ 
										number=1;
										//number=3;	
										//sham_index=0;
									}
									else{
										number=4;
										milk_index=0;
			 						}
									invalidate(); 
								}
							};
							handler.postDelayed(run, 200);  
						}
						else{
							sham_dragcheck2 = false;
							
						}
					}
				}
				break;
				
			case MotionEvent.ACTION_POINTER_UP :
			case MotionEvent.ACTION_CANCEL :
				//����
				if(number==4){
					touched[pointerId] = true;
					multi_touch_x[pointerId] = (int)event.getX(pointerIndex);
					multi_touch_y[pointerId] = (int)event.getY(pointerIndex);
					
					if(multi_touch_x[0] > 1000 && multi_touch_x[0] < 1600 && multi_touch_x[1] > 1000 && multi_touch_x[1] < 1600
			 				&& multi_touch_y[0] > 500 && multi_touch_y[0] < 900 && multi_touch_y[1] > 500 && multi_touch_y[1] < 900){
						num++;
						if(MainActivity.efsonoff==true){  
    	    				soundpool.play(efs_milk, 1, 1, 0, 0, 1);  
     					}
						total += 400;
			    		milk_normal_check1 = false;
			    		milk_normal_check2 = false;
			    		milk_index = 2;
			    		
			    		//6�� ������ ��� �ٲ�� -ĳ����
    	    			if(num%6==0){ 
    	    				charnumber=1;
    	    			}

    	    			//���ο� ���� ���� ����
    	    			Handler handler = new Handler();
						Runnable run = new Runnable(){
							public void run(){
								random = (int) (Math.random()*10)+1;
								if(random<3) {
									number=1;
								}
								else if(random>2 && random<6){
									number=2;
								}
								else if(random>5 && random<9){ 
									number=1;
									//number=3;	
									//sham_index=0;
								}
								else{
									number=4;
									milk_index=0;
		 						}
								invalidate(); 
							}
						};
						handler.postDelayed(run, 200);  
					}
					else{
			    		 milk_normal_check1 = false;
			    		 milk_normal_check2 = false;
			    	 }
				}
				
			} //switch�� ����
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
					// �̺�Ʈ�߻�!!                              
					lastX = event.values[DATA_X];  
					lastY = event.values[DATA_Y];  
					lastZ = event.values[DATA_Z];  
					shake ++;
					if(shake>=5){
						if(shake==5){
							Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
							vibe.vibrate(500);
							shakecheck=true;
						}
					}
				}        
			}     
		}
	}

		
	
	/**
	 * Back��ư ������ �� ���̾�α� Ȯ��
	 * Ȯ�ι�ư : ���� ��Ƽ��Ƽ�� �̵�
	 * ��ҹ�ư : ���� ��Ƽ��Ƽ ����
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event){
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder alert = new AlertDialog.Builder(Stage5Activity.this);
			alert.setMessage("�������� ���ư��ðڽ��ϱ�?").setPositiveButton("Ȯ��", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					Intent intent = new Intent(Stage5Activity.this, MainActivity.class);
					MainActivity.splash = false;
					finish();
					Stage2Activity.bgm_stage.stop();
					startActivity(intent);
				}
			}).setNegativeButton("���", new DialogInterface.OnClickListener() {
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
	 * ������ �����ϸ� ���� ��ŷ�� �ִ� 3���� ������ ���������� ���Ѵ�.
	 * ���������� 3������ ũ�� true��, 3������ ������ false�� ��ȯ�Ѵ�.
	 * true�� ��ȯ������ �̸� ��� â��, false�� ��ȯ������ ���� Ȯ�� â�� ���� �ڵ��� �ϵ���...
	 * @param gamescore ��������
	 * @return true(�̸��� ��� �� �� �ִ� â�� �����) or false(���� Ȯ�� â�� �����)
	 */
    boolean selectwine3(int gamescore) {
		Cursor c1 = MainActivity.db.rawQuery("select * from wine", null);
		c1.moveToFirst(); c1.moveToNext();
		c1.moveToNext();
		
		int score3 = c1.getInt(3);
		c1.close();
		
		if(gamescore > score3)
			return true;
		else
			return false;
	} // ����
}
