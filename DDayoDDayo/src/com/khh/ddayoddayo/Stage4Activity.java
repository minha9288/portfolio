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
import android.util.FloatMath;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;


public class Stage4Activity extends Activity{
 
	int time=60;
	int ttime=0;
	long a;
	int success=0;
	int milk_index=0;
	int char_draw_index = 0;
	
	float[] x = new float[10];
	float[] y = new float[10];
	boolean[] touched = new boolean[10];
 
	boolean check = false;
	boolean normal_check1 = false, normal_check2 = false;  //������ ��, ����� ��
	boolean open_check1 = false, open_check2 = false, open_check3 = false;  //�������� ���� ��, ������ ��, ���� ��
	boolean trash_check1 = false, trash_check2 = false;
	boolean turn_check1 = false;
	
	int random = 0;

	int bcs_index = 0;
	int bcs_random = 0, bcs_number=0, bcs_numcheck=0;
	boolean bcs_check1 = false, taste_check1=false, taste_check2=false;
	int bcs_draw_index = 0;
 
	int milkx=1105;
	
	int total = 0;
	
	boolean normal_error1 = false, normal_error2=false, trash_error1=false, trash_error1_ok=false, trash_error2=false, 
			turn_error1=false, turn_error2=false, bcs_error1=false, bcs_error2=false;
 
	public SoundPool soundpool;
	int efs_milk, efs_trash, efs_error, efs_turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    setContentView(new Stage4View(this));
	    
	    soundpool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0); 
	    efs_milk = soundpool.load(this, R.raw.efs_milk, 1);
	    efs_trash = soundpool.load(this, R.raw.efs_trash, 1);              
	    efs_error = soundpool.load(this, R.raw.efs_error, 1);
	    efs_turn = soundpool.load(this, R.raw.efs_turn, 1);
	    
	    if(MainActivity.bgmonoff==true){
			Stage2Activity.bgm_stage = MediaPlayer.create(this, R.raw.stage_bgm);
			Stage2Activity.bgm_stage.setLooping(true);
			Stage2Activity.bgm_stage.start();
		}
    }
    
    class Stage4View extends View{
		Bitmap bg_img, bg_img2;
		Bitmap[] milk = new Bitmap[15];
		Bitmap[] char_draw = new Bitmap[7];
		Bitmap[] bcs = new Bitmap[4];

		
		
		public Stage4View(Context context){
			super(context);
			bg_img = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_bgimg);  //���
			bg_img2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_bgimg2);
			
			milk[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img);  //�⺻
			milk[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img2);  //�⺻ - �Ա� ������
			milk[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img3);  //�⺻ - ���� ������
			milk[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img4);  //���ư���
			milk[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img5);  //������
			milk[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img6);  //������
			
			milk[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bmilk_img1);  //�ٳ������� �⺻
			milk[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bmilk_img2);  //�ٳ������� �⺻ - �Ա� ������
			milk[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bmilk_img3);  //�ٳ������� �⺻ - ���� ������
			
			milk[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cmilk_img1);  //���ڿ��� �⺻
			milk[10] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cmilk_img2);  //���ڿ��� �⺻ - �Ա� ������
			milk[11] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cmilk_img3);  //���ڿ��� �⺻ - ���� ������
			
			milk[12] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smilk_img1);  //������� �⺻
			milk[13] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smilk_img2);  //������� �⺻ - �Ա� ������
			milk[14] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smilk_img3);  //������� �⺻ - ���� ������
			
			char_draw[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char1);
			char_draw[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char2);
			char_draw[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char3);
			char_draw[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char4);
			char_draw[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char5);
			char_draw[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char6);
			char_draw[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char7);
			
			bcs[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk);
			bcs[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.banana);
			bcs[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.choco);
			bcs[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.berry);			
		}
		
		public void onDraw(Canvas canvas){
			canvas.drawBitmap(bg_img, 0, 0, null);
			canvas.drawBitmap(bcs[bcs_draw_index], 0, 0, null);
			canvas.drawBitmap(milk[milk_index], milkx, 385, null);
			canvas.drawBitmap(char_draw[char_draw_index], 180, 700, null);
			canvas.drawBitmap(bg_img2, 35, 500, null);
			
			
			if(time==1){
				if(selectmilk3(total)==true){
					Intent intent = new Intent(Stage4Activity.this, ScoreActivity.class);
					intent.putExtra("stage", 4);
					intent.putExtra("totalscore", total);
					finish();
					startActivity(intent);
				}
				else if(selectmilk3(total)==false){
					Intent intent = new Intent(Stage4Activity.this, ScoreActivity2.class);
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
			
			Paint paint = new Paint();
			paint.setColor(Color.BLACK);
			paint.setTextSize(80);
			paint.setTypeface(Typeface.create("", Typeface.BOLD));
			canvas.drawText("�ð�" + time, 100, 100, paint);
			canvas.drawText("���� " + total, 100, 250, paint);
			postInvalidate();
		}//ondraw����
		
		public boolean onTouchEvent(MotionEvent event){
		     int action = event.getAction() & MotionEvent.ACTION_MASK;
		     int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK);
		     pointerIndex = pointerIndex >> MotionEvent.ACTION_POINTER_ID_SHIFT;
		     int pointerId = event.getPointerId(pointerIndex);
		     
		     switch(action){
		     
		     case MotionEvent.ACTION_DOWN:	    	 
		     case MotionEvent.ACTION_POINTER_DOWN:	    	 
		    	 /**
		    	  * ���� ������ ���.
		    	  * �ٳ������ڵ�������� ã�����̸� taste_check1 Ȯ��
		    	  * (���� �ٳ������ڵ��Ⱑ ������ �� taste_check2�� Ȯ���� success++)
		    	  * �׳� ������ normal_check1 Ȯ��
		    	  * (���� �巡�� ��ǥ�ް� �����ϸ� ������ ������ ���� ���� ����)
		    	  */
		    	 if(milk_index==0){
		    		 if(bcs_index==0){
		    			 touched[pointerId] = true;
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    	 
			    		 Log.i("down", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
			    	 
			    		 //����� �Ѱ��
			    		 if(x[0] > 1100 && x[0] < 1600 && x[1] > 1100 && x[1] < 1600
				 				 && y[0] > 400 && y[0] < 800 && y[1] > 400 && y[1] < 800){
			    			 normal_check1 = true;
			    		 }
			    		 
			    		 
			    		 //�����ε� �������, �������
			    		 if(x[0] > 1200 && x[0] < 1600){
			    			 normal_error1 = true;
			    			 normal_error2 = true;
			    		 Log.i("error", "�����ε� ������� normal_error1 : " + normal_error1);
			    		 }
			    		 else{
			    			 normal_error1 = false;
			    			 normal_error2 = false;
			    		 }
			    		 
		    		 }
		    		 //�ٳ������ڵ��� ã��
		    		 else if(bcs_index!=0){
		    			 touched[pointerId] = true;
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    		 
			    		 Log.i("�ٳ������ڵ���", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);

			    		 
			    		 if(x[0] > 1200 && x[0] < 1400){
			    			 bcs_check1 = true; //����� �������
			    			 bcs_error2 = true; //���� ã�ƾߵǴµ� �������
			    		 }
			    		 else{
			    			 bcs_check1 = false;
			    			 bcs_error2 = false;
			    		 }
			    		 
			    		//ã�ƾߵǴµ� �����
			    		 if(x[0] > 1000 && x[0] < 1600 && x[1] > 1000 && x[1] < 1600
				 				 && y[0] > 500 && y[0] < 900 && y[1] > 500 && y[1] < 800){
			    			 bcs_error1 = true;
			    		 }
			    		 else
			    			 bcs_error1 = false;
		    		 }
		    		 
		    	 }
		    	 
		    	 /**
		    	  * ���ư� ������ ���.
		    	  * turn_check1 Ȯ��
		    	  * (���� ���������� �Ѱܼ� turn_check2 Ȯ��)
		    	  */
		    	 //���ư� ������ ���
		    	 if(milk_index==3){
		    		 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
		    		 
		    		 if(x[0] > 1200 && x[0] < 1600){
		    			 turn_check1 = true;  //����
		    			 turn_error2 = true;  //�������
		    		 }
		    		 else{
		    			 turn_check1 = false;
		    			 turn_error2 = false;
		    		 }
		    		 
		    		 //�����ߵǴµ� �����
		    		 if(x[0] > 1000 && x[0] < 1600 && x[1] > 1000 && x[1] < 1600
			 				 && y[0] > 500 && y[0] < 900 && y[1] > 500 && y[1] < 800){
		    			 turn_error1 = true;
		    		 }
		    		 else
		    			 turn_error1 = false;
		    	 }
		    	 
		    	 /**
		    	  * ���� ������ ���.
		    	  * �ٱ�����ǥ�� �о� open_check1�� Ȯ���Ѵ�.
		    	  * (���� ���� ��ǥ�� �о� ������)
		    	  */
		    	 //���� ������ ���
		    	 if(milk_index==4){
			    		 touched[pointerId] = true;
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    		 
		    			 if(x[0] < 1300 && x[0] > 1500 && x[1] < 1300 && x[1] > 1500
				 				 && y[0] > 300 && y[0] < 800 && y[1] > 300 && y[1] < 800){
			    			 open_check1 = true;
			    		 }
			    		 else
			    			 open_check1 = false;
		    			 
		    			 
		    			 //����ߵǴ¿����ε� �������, �������
			    		 if(x[0] > 1200 && x[0] < 1600){
			    			 trash_error2 = true;
			    		 Log.i("error", "����ߵǴµ� ������� normal_error1 : " + normal_error1);
			    		 }
			    		 else{
			    			 trash_error2 = false;
			    		 }
		    	 }
		    	 
		    	 /**
		    	  * ���� ������ ���.
		    	  * trash_check1Ȯ���Ѵ�
		    	  * (���� �������� �巡���ؼ� trash_check2 Ȯ���ϰ� success++)
		    	  */
		    	//���� ������ ���
		    	 if(milk_index==5 && open_check3 == true){
	    			 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
		    		 
		    		 Log.i("check3", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
		    		 
		    		 if(x[0] > 1200 && x[0] < 1600)
		    			 trash_check1 = true;
		    		 else
		    			 trash_check1 = false;
	    		 }
		    	 
		    	 /**
		    	  * �ٳ������ڵ��� ������ ���
		    	  * ���� ���� ���� ��ó�� �Ȱ��� ����.
		    	  */
		    	//�ٳ������ڵ��� ������ ���
		    	 if(milk_index==6 || milk_index==9 || milk_index==12){
		    		 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
			    	 	
		    		 Log.i("�ٳ������ڵ���2", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
			    	 	
		    		 if(x[0] > 1000 && x[0] < 1600 && x[1] > 1000 && x[1] < 1600
		    				 && y[0] > 500 && y[0] < 900 && y[1] > 500 && y[1] < 800){
			    	 	taste_check1 = true;
		    		 } 
		    	 }
		    	 break;
		      
		     case MotionEvent.ACTION_MOVE:
		    	 /**
		    	  * ���� ������ ���.
		    	  * ������ ��ǥ �ΰ� �޾Ƽ� Ȯ��Ǹ� milk_index=1�� �����Ͽ� �Ա� ���� ��� ����
		    	  */
		    	 //���� ������ ���
		    	 if(milk_index==0 && normal_check1==true){
		    		 int pointerCount = event.getPointerCount();
			    	 for(int i=0; i<pointerCount; i++){
			    		 pointerIndex = i;
			    		 pointerId = event.getPointerId(pointerIndex);
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    		 
			    		 Log.i("move", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
			    		 
			    		 if(x[0] > 1500 && x[1] < 1300 && y[0] < 600 && y[1] < 600){
			    			 normal_check2 = true;
			    			 milk_index = 1;
			    		 }
			    		 
			    		 if(x[0] < 1300 & x[1] > 1500 && y[0] < 600 && y[1] < 600){
			    			 normal_check2 = true;
			    			 milk_index = 1;
			    		 }
			    		 else
			    			 normal_check2 = false;
			    	 }
		    	 }
		    	 
		    	 
		    	 
		    	 /**
		    	  * ���� ������ ���.
		    	  * ��ǥ �ΰ� �޾Ƽ� �������� ����Ǹ� open_check2 Ȯ��.
		    	  * (���� �������� ������ ���� ���� ������� ����)
		    	  */
		    	 //���� ������ ���
		    	 if(milk_index==4 && open_check1==true){
		    		 int pointerCount = event.getPointerCount();
			    	 for(int i=0; i<pointerCount; i++){
			    		 pointerIndex = i;
			    		 pointerId = event.getPointerId(pointerIndex);
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    		 
			    		 if((x[0] > 900 && x[1] < 1600) || (x[0] < 1600 & x[1] > 900)){
			    			 open_check2 = true;
			    		 }
			    		 else
			    			 open_check2 = false;
			    	 }
		    	 }
		    	 
		    	 
		    	//���� ������ ���
		    	 if(milk_index==5 && open_check3 == true){
	    			 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
		    		 
		    		 Log.i("���� move", "DOWN : X ( " + x[0] + " ) , Y ( " + y[0] + " )");
		    		 
		    		 if(x[0] < 1105)
		    			 milkx = (int)x[0];
		    		 if(x[0] < 1000)
		    			 milkx = 900;
	    		 }


		    	 
		    	 /**
		    	  * �ٳ������ڵ����� ���
		    	  * ��������� �Ȱ��� �����ϵ�
		    	  * ���� ������ ����� �ٳ���=7, ����=10, ����=13
		    	  */
		    	//�ٳ������ڵ��� ������ ���
		    	 if((milk_index==6 || milk_index==9 || milk_index==12) && taste_check1==true){
		    		 int pointerCount = event.getPointerCount();
			    	 for(int i=0; i<pointerCount; i++){
			    		 pointerIndex = i;
			    		 pointerId = event.getPointerId(pointerIndex);
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    		 
			    		 Log.i("�ٳ������ڵ���3", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
			    		 
			    		 if(x[0] > 1400 && x[1] < 1300 && y[0] > 500 && y[1] > 500){
			    			 taste_check2 = true;
			    			 if(milk_index==6)
			    				 milk_index =7;
			    			 if(milk_index==9)
			    				 milk_index =10;
			    			 if(milk_index==12)
			    				 milk_index =13;
			    		 }
			    		 else if(x[0] < 1300 & x[1] > 1400 && y[0] > 500 && y[1] > 500){
			    			 taste_check2 = true;
			    			 if(milk_index==6)
			    				 milk_index =7;
			    			 if(milk_index==9)
			    				 milk_index =10;
			    			 if(milk_index==12)
			    				 milk_index =13;
			    		 }
			    		 else
			    			 normal_check2 = false;
			    	 }
		    	 }
		    	 
		    	 
		    	 /**
		    	  * �ٳ������ڵ�������� ã���ִ� ��������� ���
		    	  * ���������� �巡������ ��� �����̵����Ѵ�. (�����°Ͱ� ���� ȿ��)
		    	  */
		    	 /*
		    	 if(milk_index==0 && bcs_index != 0){
	    			 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
		    		 
		    		 if(bcs_error2==true){
		    			 if(x[0] > 1500)
			    			 milkx = (int)x[0];
			    		 if(x[0] > 1400)
			    			 milkx = 1450;
		    		 }
		    		 
	    		 }
		    	 */
		    	 
		    	 
		    	 
		    	 break;
		    	 
		     case MotionEvent.ACTION_UP:
		    	 /**
		    	  * �ٳ������ڵ��⸦ ã���ִ� ���� ������ ���.
		    	  * ���������� bcs_number����ŭ ������ �ٳ���/����/���� ������ ��������.
		    	  */
		    	 if(milk_index==0 && bcs_index!=0){
		    		 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
		    		 
		    		 Log.i("�̰�", x[0] + " , " + y[0]);
		    		 
		    		 if(x[0] > 1600 && y[0] > 700){
    					 bcs_check1 = false;
    					 bcs_numcheck++;
    					 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_turn, 1, 1, 0, 0, 1);  
	     					}
    		    		 milkx = 1105;
		    		 }

		    		 if(bcs_numcheck==bcs_number){
		    			 milk_index = bcs_index;
		    			 bcs_index = 0;
		    			 bcs_number = 0;
		    			 bcs_numcheck = 0;
		    			 bcs_random = 0;
		    		 }
		    	 }
		    	 
		    	 /**
		    	  * �����ε� �߸� �ൿ������ ����üũ
		    	  */
		    	 //�����ε� �������
		    	 if(milk_index==0 && normal_error1==true){
		    		 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
		    		 if(x[0] < 1000 && y[0] > 500){
						 total -= 100;
						 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
	     					}
						 normal_error1 = false;
			    	 }
		    	 }
		    	 //�����ε� �������
		    	 if(milk_index==0 && normal_error2==true){
		    		 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
			    	 if(x[0] > 1600 && y[0] > 500){
			    		 total -=100;
			    		 if(MainActivity.efsonoff==true){  
	    	    				
			    			soundpool.play(efs_turn, 1, 1, 0, 0, 1);  
	     					}
			    		 normal_error2 = false;
			    	 }
		    	 }
		    	 
		    	//���� ã�ƾߵǴµ� �������
		    	 if(milk_index==0 && bcs_error2==true){
		    		 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
			    	 if(x[0] < 1000 && y[0] > 500){
			    		 total -=100;
			    		 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
	     					}
			    		 bcs_error2 = false;
			    	 }
		    	 }
		    	 
		    	//�ٳ������ڵ���
		    	 /*
		    	 if(milk_index==0){
		    		 if(bcs_index!=0){
		    			 touched[pointerId] = true;
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    		 
			    		 Log.i("�ٳ������ڵ���4", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
			    		 
			    		 if(x[0] > 1300){
	    					 bcs_check1 = false;
			    		 }
			    		 bcs_numcheck++;
			    		 if(bcs_numcheck==bcs_number){
			    			 milk_index = bcs_index;
			    			 bcs_index = 0;
			    			 bcs_number = 0;
			    			 bcs_numcheck = 0;
			    			 bcs_random = 0;
			    		 }
		    		 }
		    	 }
		    	 */
		    	 
		    	 /**
		    	  * �������� �߸��ൿ�� ����üũ
		    	  * ����ߵǴµ� �������. ������ MOTION_CANCLE, MOTION_POINTER_UP���� ó��
		    	  */
		    	 //����ߵǴµ� �������
		    	 if(milk_index==4 && trash_error2==true){
		    		 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
			    	 if(x[0] > 1600 && y[0] > 500){
			    		 total -=100;
			    		 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
	     					}
			    		 
			    		 trash_error2 = false;
			    	 }
		    	 }
		    	 
		    	 /**
		    	  * ���� ������ ���.
		    	  * �������� �巡�� �����ϸ� success++
		    	  */
		    	 //���� ������ ���
		    	 if(milk_index==5 && open_check3 == true){
	    			 if(trash_check1 == true){
	    				 touched[pointerId] = false;
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    		 
	    				 if(x[0] < 1000){
	    					 success ++;
	    					 if(MainActivity.efsonoff==true){  
	     	    				soundpool.play(efs_trash, 1, 1, 0, 0, 1);  
	      					}
	    					 total += 400;
	    					 trash_check1 = false;
	    					 open_check3 = false;
	    					 
	    					 
	    					 if(success%3==0){
	 							if(char_draw_index<6)
	 								char_draw_index++;
	 							else if(char_draw_index>=6)
	 								char_draw_index=6;
	 						}
	    					 
	    					 
	    					 Handler handler = new Handler();
								Runnable run = new Runnable(){
									public void run(){

				 						random = (int) (Math.random()*10)+1;
				 						if(random<=5){
				 							milkx=1105;
				 							milk_index=0;  //����
				 						}
				 						else if(random>5 && random<=7){
				 							milkx=1105;
				 							milk_index=3;  //���ư�
				 						}
				 						else if(random>7 && random<=9){
				 							milkx=1105;
				 							milk_index=4; //������
				 						}
				 						else{
				 							bcs_random = (int)(Math.random()*9)+1;
				 							bcs_number = (int)(Math.random()*3)+2;
				 							milkx=1105;
				 							if(bcs_random<=3){
				 								bcs_index=6; //�ٳ���
				 								bcs_draw_index=1;
				 								milk_index=0;
				 							}
				 							else if(bcs_random>3 && bcs_random<=6){
				 								bcs_index=9;  //����
				 								bcs_draw_index=2;
				 								milk_index=0;
				 							}
				 							else if(bcs_random>6 && bcs_random<=9){
				 								bcs_index=12;  //����
				 								bcs_draw_index=3;
				 								milk_index=0;
				 							}
				 						}
										invalidate(); 
									}
								};
								handler.postDelayed(run, 200);  
	    					 
	 						
	    				 }
	    				 else{
	    					 trash_check1 = false;
	    				 }
	    			 }
		    	 }
		    	 
		    	 /**
		    	  * ���ư� ������ ���.
		    	  * ������ �巡�� ��ǥ�� Ȯ�� �Ǹ� success++
		    	  */
		    	 //���ư� ������ ���
		    	 if(milk_index==3){
		    		 touched[pointerId] = false;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
		    		 
		    		//�����ߵǴµ� �������
		    		 if(x[0] < 1000 && y[0] > 500){
			    		 Log.i("error", "�����ߵǴµ� �������" + x[0] +" , " + y[0]);
			    		 total -=100;
			    		 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
	     					}
			    		 turn_error2 = false;
			    	 }
		    		 
		    		 //����� �������
		    		 if(x[0] > 1600 && y[0] > 500){
    					 success ++;
    					 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_turn, 1, 1, 0, 0, 1);  
	    	    				
	     					}
    					 total += 200;
    					 turn_check1 = false;
    					     
    					 if(success%3==0){
 							if(char_draw_index<6)
 								char_draw_index++;
 							else if(char_draw_index>=6)
 								char_draw_index=6;
 						}
    					 milk_index=0;
    				 }
    				 else
    					 turn_check1 = false;
		    	 }
		    	 break;
		     case MotionEvent.ACTION_POINTER_UP:
		     case MotionEvent.ACTION_CANCEL:
		    	 /**
		    	  * ���� ������ ���.
		    	  * �������� ��� �巡�� �����ϸ� success++
		    	  */
		    	 //���� ������ ���
		    	 if(milk_index==1){
		    		 touched[pointerId] = false;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
			      
		    		 Log.i("up", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
			      
			      
			    	 if(x[0] > 1000 && x[0] < 1600 && x[1] > 1000 && x[1] < 1600
				 				&& y[0] > 500 && y[0] < 900 && y[1] > 500 && y[1] < 900){
			    		 success ++;
			    		 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_milk, 1, 1, 0, 0, 1);  
	     					}
			    		 total += 400;
			    		 normal_check1 = false;
			    		 normal_check2 = false;
			    		 milk_index = 2;
			    		 
			    		 if(success%3==0){
								if(char_draw_index<6)
									char_draw_index++;
								else if(char_draw_index>=6)
									char_draw_index=6;
							}
	 						


			    		 Handler handler = new Handler();
							Runnable run = new Runnable(){
								public void run(){

			 						random = (int) (Math.random()*10)+1;
			 						if(random<=5){
			 							milkx=1105;
			 							milk_index=0;  //����
			 						}
			 						else if(random>5 && random<=7){
			 							milkx=1105;
			 							milk_index=3;  //���ư�
			 						}
			 						else if(random>7 && random<=9){
			 							milkx=1105;
			 							milk_index=4; //������
			 						}
			 						else{
			 							bcs_random = (int)(Math.random()*9)+1;
			 							bcs_number = (int)(Math.random()*3)+2;
			 							milkx=1105;
			 							if(bcs_random<=3){
			 								bcs_index=6; //�ٳ���
			 								bcs_draw_index=1;
			 								milk_index=0;
			 							}
			 							else if(bcs_random>3 && bcs_random<=6){
			 								bcs_index=9;  //����
			 								bcs_draw_index=2;
			 								milk_index=0;
			 							}
			 							else if(bcs_random>6 && bcs_random<=9){
			 								bcs_index=12;  //����
			 								bcs_draw_index=3;
			 								milk_index=0;
			 							}
			 						}
									invalidate(); 
								}
							};
							handler.postDelayed(run, 300); 
			    	 }
			    	 else{
			    		 normal_check1 = false;
			    		 normal_check2 = false;
			    	 }
		    	 }
		    	 
		    	 //�����ߵǴµ� �����
		    	 if(milk_index==3 && turn_error1==true){
		    		 if(x[0] > 1000 && x[0] < 1600 && x[1] > 1000 && x[1] < 1600
				 				&& y[0] > 500 && y[0] < 900 && y[1] > 500 && y[1] < 900){
		    			 Log.i("error", "�����ߵǴµ� �����");
			    		 total-=100;
			    		 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
	     					}
			    		 turn_error1 = false;
		    		 }
		    	 }
		    	 
		    	//ã�ƾߵǴµ� �����
		    	 if(milk_index==0 && bcs_index!=0 && bcs_error1==true){
		    		 if(x[0] > 1000 && x[0] < 1600 && x[1] > 1000 && x[1] < 1600
				 				&& y[0] > 500 && y[0] < 900 && y[1] > 500 && y[1] < 900){
		    			 Log.i("error", "ã�ƾߵǴµ� �����");
			    		 total-=100;
			    		 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
	     					}
			    		 bcs_error1 = false;
		    		 }
		    	 }
		    	 
		    	 //���� ������ ���
		    	 if(milk_index==4){
		    		 
		    			 touched[pointerId] = false;
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    		 
			    		 Log.i("up", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
			    		 
			    		 //������Ѱ��
		    			 if(x[0] > 1000 && x[0] < 1600 && x[1] > 1000 && x[1] < 1600){
			    			 open_check3 = true;
			    			 open_check1 = false;
			    			 open_check2 = false;
			    			 milk_index = 5;
			    		 }
			    		 else{
			    			 open_check1 = false;
			    			 open_check2 = false;
			    			 open_check3 = false;
			    		 }
		    	 }
		    	 
		    	//�ٳ������ڵ��� ������ ���
		    	 if(milk_index==7 || milk_index==10 || milk_index==13){
		    		 touched[pointerId] = false;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
			      
		    		 Log.i("up", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
			      
			      
			    	 if(x[0] > 1000 && x[0] < 1600 && x[1] > 1000 && x[1] < 1600
				 				&& y[0] > 500 && y[0] < 900 && y[1] > 500 && y[1] < 900){
			    		 success ++;
			    		 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_milk, 1, 1, 0, 0, 1);  
	     					}
			    		 total += 600;
			    		 taste_check1 = false;
			    		 taste_check2 = false;
			    		 if(milk_index==7)
			    			 milk_index=8;
			    		 else if(milk_index==10)
			    			 milk_index=11;
			    		 else if(milk_index==13)
			    			 milk_index=14;
			    		 
			    		 bcs_draw_index=0;
			    		 
			    		 
			    		 if(success%3==0){
								if(char_draw_index<6)
									char_draw_index++;
								else if(char_draw_index>=6)
									char_draw_index=6;
							}

			    		 Handler handler = new Handler();
							Runnable run = new Runnable(){
								public void run(){

			 						random = (int) (Math.random()*10)+1;
			 						if(random<=5){
			 							milkx=1105;
			 							milk_index=0;  //����
			 						}
			 						else if(random>5 && random<=7){
			 							milkx=1105;
			 							milk_index=3;  //���ư�
			 						}
			 						else if(random>7 && random<=9){
			 							milkx=1105;
			 							milk_index=4; //������
			 						}
			 						else{
			 							bcs_random = (int)(Math.random()*9)+1;
			 							bcs_number = (int)(Math.random()*3)+2;
			 							milkx=1105;
			 							if(bcs_random<=3){
			 								bcs_index=6; //�ٳ���
			 								bcs_draw_index=1;
			 								milk_index=0;
			 							}
			 							else if(bcs_random>3 && bcs_random<=6){
			 								bcs_index=9;  //����
			 								bcs_draw_index=2;
			 								milk_index=0;
			 							}
			 							else if(bcs_random>6 && bcs_random<=9){
			 								bcs_index=12;  //����
			 								bcs_draw_index=3;
			 								milk_index=0;
			 							}
			 						}
									invalidate(); 
								}
							};
							handler.postDelayed(run, 300); 
			    		 
			    		 
			    		 
			    	 }
			    	 else{
			    		 normal_check1 = false;
			    		 normal_check2 = false;
			    		 total -= 100;
			    		 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
	     					}
			    	 }
		    	 }
			     break;
			      
		     }//switch�� ����
		    
		     return true;
		} //touchevent ����
		
    }//view class ����
    
    /**
	 * Back��ư ������ �� ���̾�α� Ȯ��
	 * Ȯ�ι�ư : ���� ��Ƽ��Ƽ�� �̵�
	 * ��ҹ�ư : ���� ��Ƽ��Ƽ ����
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event){
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder alert = new AlertDialog.Builder(Stage4Activity.this);
			alert.setMessage("�������� ���ư��ðڽ��ϱ�?").setPositiveButton("Ȯ��", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					Intent intent = new Intent(Stage4Activity.this, MainActivity.class);
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
    boolean selectmilk3(int gamescore) {
		Cursor c1 = MainActivity.db.rawQuery("select * from milk", null);
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
