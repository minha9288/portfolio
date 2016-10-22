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
	boolean normal_check1 = false, normal_check2 = false;  //열었을 때, 당겼을 때
	boolean open_check1 = false, open_check2 = false, open_check3 = false;  //열린상태 잡을 때, 접었을 때, 버릴 때
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
			bg_img = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_bgimg);  //배경
			bg_img2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_bgimg2);
			
			milk[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img);  //기본
			milk[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img2);  //기본 - 입구 열린거
			milk[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img3);  //기본 - 완전 따진거
			milk[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img4);  //돌아간거
			milk[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img5);  //열린거
			milk[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img6);  //접힌거
			
			milk[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bmilk_img1);  //바나나우유 기본
			milk[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bmilk_img2);  //바나나우유 기본 - 입구 열린거
			milk[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bmilk_img3);  //바나나우유 기본 - 완전 따진거
			
			milk[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cmilk_img1);  //초코우유 기본
			milk[10] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cmilk_img2);  //초코우유 기본 - 입구 열린거
			milk[11] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cmilk_img3);  //초코우유 기본 - 완전 따진거
			
			milk[12] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smilk_img1);  //딸기우유 기본
			milk[13] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smilk_img2);  //딸기우유 기본 - 입구 열린거
			milk[14] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smilk_img3);  //딸기우유 기본 - 완전 따진거
			
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
			canvas.drawText("시간" + time, 100, 100, paint);
			canvas.drawText("점수 " + total, 100, 250, paint);
			postInvalidate();
		}//ondraw종료
		
		public boolean onTouchEvent(MotionEvent event){
		     int action = event.getAction() & MotionEvent.ACTION_MASK;
		     int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK);
		     pointerIndex = pointerIndex >> MotionEvent.ACTION_POINTER_ID_SHIFT;
		     int pointerId = event.getPointerId(pointerIndex);
		     
		     switch(action){
		     
		     case MotionEvent.ACTION_DOWN:	    	 
		     case MotionEvent.ACTION_POINTER_DOWN:	    	 
		    	 /**
		    	  * 정상 우유의 경우.
		    	  * 바나나초코딸기우유를 찾는중이면 taste_check1 확인
		    	  * (이후 바나나초코딸기가 나왔을 때 taste_check2를 확인해 success++)
		    	  * 그냥 우유면 normal_check1 확인
		    	  * (이후 드래그 좌표받고 성공하면 랜덤값 돌려서 다음 우유 띄우기)
		    	  */
		    	 if(milk_index==0){
		    		 if(bcs_index==0){
		    			 touched[pointerId] = true;
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    	 
			    		 Log.i("down", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
			    	 
			    		 //제대로 한경우
			    		 if(x[0] > 1100 && x[0] < 1600 && x[1] > 1100 && x[1] < 1600
				 				 && y[0] > 400 && y[0] < 800 && y[1] > 400 && y[1] < 800){
			    			 normal_check1 = true;
			    		 }
			    		 
			    		 
			    		 //정상인데 버릴경우, 돌릴경우
			    		 if(x[0] > 1200 && x[0] < 1600){
			    			 normal_error1 = true;
			    			 normal_error2 = true;
			    		 Log.i("error", "정상인데 버릴경우 normal_error1 : " + normal_error1);
			    		 }
			    		 else{
			    			 normal_error1 = false;
			    			 normal_error2 = false;
			    		 }
			    		 
		    		 }
		    		 //바나나초코딸기 찾기
		    		 else if(bcs_index!=0){
		    			 touched[pointerId] = true;
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    		 
			    		 Log.i("바나나초코딸기", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);

			    		 
			    		 if(x[0] > 1200 && x[0] < 1400){
			    			 bcs_check1 = true; //제대로 했을경우
			    			 bcs_error2 = true; //우유 찾아야되는데 버릴경우
			    		 }
			    		 else{
			    			 bcs_check1 = false;
			    			 bcs_error2 = false;
			    		 }
			    		 
			    		//찾아야되는데 열경우
			    		 if(x[0] > 1000 && x[0] < 1600 && x[1] > 1000 && x[1] < 1600
				 				 && y[0] > 500 && y[0] < 900 && y[1] > 500 && y[1] < 800){
			    			 bcs_error1 = true;
			    		 }
			    		 else
			    			 bcs_error1 = false;
		    		 }
		    		 
		    	 }
		    	 
		    	 /**
		    	  * 돌아간 우유의 경우.
		    	  * turn_check1 확인
		    	  * (이후 오른쪽으로 넘겨서 turn_check2 확인)
		    	  */
		    	 //돌아간 우유의 경우
		    	 if(milk_index==3){
		    		 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
		    		 
		    		 if(x[0] > 1200 && x[0] < 1600){
		    			 turn_check1 = true;  //정상
		    			 turn_error2 = true;  //버릴경우
		    		 }
		    		 else{
		    			 turn_check1 = false;
		    			 turn_error2 = false;
		    		 }
		    		 
		    		 //돌려야되는데 열경우
		    		 if(x[0] > 1000 && x[0] < 1600 && x[1] > 1000 && x[1] < 1600
			 				 && y[0] > 500 && y[0] < 900 && y[1] > 500 && y[1] < 800){
		    			 turn_error1 = true;
		    		 }
		    		 else
		    			 turn_error1 = false;
		    	 }
		    	 
		    	 /**
		    	  * 열린 우유의 경우.
		    	  * 바깥쪽좌표를 읽어 open_check1을 확인한다.
		    	  * (이후 안쪽 좌표를 읽어 접히기)
		    	  */
		    	 //열린 우유의 경우
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
		    			 
		    			 
		    			 //접어야되는우유인데 버릴경우, 돌릴경우
			    		 if(x[0] > 1200 && x[0] < 1600){
			    			 trash_error2 = true;
			    		 Log.i("error", "접어야되는데 버릴경우 normal_error1 : " + normal_error1);
			    		 }
			    		 else{
			    			 trash_error2 = false;
			    		 }
		    	 }
		    	 
		    	 /**
		    	  * 접힌 우유의 경우.
		    	  * trash_check1확인한다
		    	  * (이후 왼쪽으로 드래그해서 trash_check2 확인하고 success++)
		    	  */
		    	//접힌 우유의 경우
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
		    	  * 바나나초코딸기 우유인 경우
		    	  * 정상 우유 따는 것처럼 똑같이 동작.
		    	  */
		    	//바나나초코딸기 우유의 경우
		    	 if(milk_index==6 || milk_index==9 || milk_index==12){
		    		 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
			    	 	
		    		 Log.i("바나나초코딸기2", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
			    	 	
		    		 if(x[0] > 1000 && x[0] < 1600 && x[1] > 1000 && x[1] < 1600
		    				 && y[0] > 500 && y[0] < 900 && y[1] > 500 && y[1] < 800){
			    	 	taste_check1 = true;
		    		 } 
		    	 }
		    	 break;
		      
		     case MotionEvent.ACTION_MOVE:
		    	 /**
		    	  * 정상 우유의 경우.
		    	  * 포인터 좌표 두개 받아서 확대되면 milk_index=1로 변경하여 입구 열린 모양 띄우기
		    	  */
		    	 //정상 우유의 경우
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
		    	  * 열린 우유의 경우.
		    	  * 좌표 두개 받아서 안쪽으로 진행되면 open_check2 확인.
		    	  * (이후 안쪽으로 들어오면 접힌 우유 모양으로 변경)
		    	  */
		    	 //열린 우유의 경우
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
		    	 
		    	 
		    	//접힌 우유의 경우
		    	 if(milk_index==5 && open_check3 == true){
	    			 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
		    		 
		    		 Log.i("깨진 move", "DOWN : X ( " + x[0] + " ) , Y ( " + y[0] + " )");
		    		 
		    		 if(x[0] < 1105)
		    			 milkx = (int)x[0];
		    		 if(x[0] < 1000)
		    			 milkx = 900;
	    		 }


		    	 
		    	 /**
		    	  * 바나나초코딸기의 경우
		    	  * 정상우유와 똑같이 동작하되
		    	  * 열린 우유의 모양이 바나나=7, 초코=10, 딸기=13
		    	  */
		    	//바나나초코딸기 우유의 경우
		    	 if((milk_index==6 || milk_index==9 || milk_index==12) && taste_check1==true){
		    		 int pointerCount = event.getPointerCount();
			    	 for(int i=0; i<pointerCount; i++){
			    		 pointerIndex = i;
			    		 pointerId = event.getPointerId(pointerIndex);
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    		 
			    		 Log.i("바나나초코딸기3", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
			    		 
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
		    	  * 바나나초코딸기우유를 찾고있는 정상우유의 경우
		    	  * 오른쪽으로 드래그했을 경우 움직이도록한다. (버리는것과 같은 효과)
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
		    	  * 바나나초코딸기를 찾고있는 정상 우유의 경우.
		    	  * 랜덤생성한 bcs_number값만큼 돌리면 바나나/초코/딸기 우유로 변경해줌.
		    	  */
		    	 if(milk_index==0 && bcs_index!=0){
		    		 touched[pointerId] = true;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
		    		 
		    		 Log.i("이거", x[0] + " , " + y[0]);
		    		 
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
		    	  * 정상인데 잘못 행동했을때 에러체크
		    	  */
		    	 //정상인데 버릴경우
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
		    	 //정상인데 돌릴경우
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
		    	 
		    	//우유 찾아야되는데 버릴경우
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
		    	 
		    	//바나나초코딸기
		    	 /*
		    	 if(milk_index==0){
		    		 if(bcs_index!=0){
		    			 touched[pointerId] = true;
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    		 
			    		 Log.i("바나나초코딸기4", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
			    		 
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
		    	  * 열린우유 잘못행동한 에러체크
		    	  * 접어야되는데 돌릴경우. 열경우는 MOTION_CANCLE, MOTION_POINTER_UP에서 처리
		    	  */
		    	 //접어야되는데 돌릴경우
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
		    	  * 접힌 우유의 경우.
		    	  * 왼쪽으로 드래그 성공하면 success++
		    	  */
		    	 //접힌 우유의 경우
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
				 							milk_index=0;  //정상
				 						}
				 						else if(random>5 && random<=7){
				 							milkx=1105;
				 							milk_index=3;  //돌아간
				 						}
				 						else if(random>7 && random<=9){
				 							milkx=1105;
				 							milk_index=4; //버리기
				 						}
				 						else{
				 							bcs_random = (int)(Math.random()*9)+1;
				 							bcs_number = (int)(Math.random()*3)+2;
				 							milkx=1105;
				 							if(bcs_random<=3){
				 								bcs_index=6; //바나나
				 								bcs_draw_index=1;
				 								milk_index=0;
				 							}
				 							else if(bcs_random>3 && bcs_random<=6){
				 								bcs_index=9;  //초코
				 								bcs_draw_index=2;
				 								milk_index=0;
				 							}
				 							else if(bcs_random>6 && bcs_random<=9){
				 								bcs_index=12;  //딸기
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
		    	  * 돌아간 우유의 경우.
		    	  * 오른쪽 드래그 좌표로 확인 되면 success++
		    	  */
		    	 //돌아간 우유의 경우
		    	 if(milk_index==3){
		    		 touched[pointerId] = false;
		    		 x[pointerId] = (int)event.getX(pointerIndex);
		    		 y[pointerId] = (int)event.getY(pointerIndex);
		    		 
		    		//돌려야되는데 버릴경우
		    		 if(x[0] < 1000 && y[0] > 500){
			    		 Log.i("error", "돌려야되는데 버린경우" + x[0] +" , " + y[0]);
			    		 total -=100;
			    		 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
	     					}
			    		 turn_error2 = false;
			    	 }
		    		 
		    		 //제대로 했을경우
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
		    	  * 정상 우유의 경우.
		    	  * 안쪽으로 축소 드래그 성공하면 success++
		    	  */
		    	 //정상 우유의 경우
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
			 							milk_index=0;  //정상
			 						}
			 						else if(random>5 && random<=7){
			 							milkx=1105;
			 							milk_index=3;  //돌아간
			 						}
			 						else if(random>7 && random<=9){
			 							milkx=1105;
			 							milk_index=4; //버리기
			 						}
			 						else{
			 							bcs_random = (int)(Math.random()*9)+1;
			 							bcs_number = (int)(Math.random()*3)+2;
			 							milkx=1105;
			 							if(bcs_random<=3){
			 								bcs_index=6; //바나나
			 								bcs_draw_index=1;
			 								milk_index=0;
			 							}
			 							else if(bcs_random>3 && bcs_random<=6){
			 								bcs_index=9;  //초코
			 								bcs_draw_index=2;
			 								milk_index=0;
			 							}
			 							else if(bcs_random>6 && bcs_random<=9){
			 								bcs_index=12;  //딸기
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
		    	 
		    	 //돌려야되는데 연경우
		    	 if(milk_index==3 && turn_error1==true){
		    		 if(x[0] > 1000 && x[0] < 1600 && x[1] > 1000 && x[1] < 1600
				 				&& y[0] > 500 && y[0] < 900 && y[1] > 500 && y[1] < 900){
		    			 Log.i("error", "돌려야되는데 연경우");
			    		 total-=100;
			    		 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
	     					}
			    		 turn_error1 = false;
		    		 }
		    	 }
		    	 
		    	//찾아야되는데 연경우
		    	 if(milk_index==0 && bcs_index!=0 && bcs_error1==true){
		    		 if(x[0] > 1000 && x[0] < 1600 && x[1] > 1000 && x[1] < 1600
				 				&& y[0] > 500 && y[0] < 900 && y[1] > 500 && y[1] < 900){
		    			 Log.i("error", "찾아야되는데 연경우");
			    		 total-=100;
			    		 if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
	     					}
			    		 bcs_error1 = false;
		    		 }
		    	 }
		    	 
		    	 //열린 우유의 경우
		    	 if(milk_index==4){
		    		 
		    			 touched[pointerId] = false;
			    		 x[pointerId] = (int)event.getX(pointerIndex);
			    		 y[pointerId] = (int)event.getY(pointerIndex);
			    		 
			    		 Log.i("up", "X1 = " + x[0] + ", X2 = " + x[1] + ", Y1 = " + y[0] + ", Y2 = " + y[1]);
			    		 
			    		 //제대로한경우
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
		    	 
		    	//바나나초코딸기 우유의 경우
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
			 							milk_index=0;  //정상
			 						}
			 						else if(random>5 && random<=7){
			 							milkx=1105;
			 							milk_index=3;  //돌아간
			 						}
			 						else if(random>7 && random<=9){
			 							milkx=1105;
			 							milk_index=4; //버리기
			 						}
			 						else{
			 							bcs_random = (int)(Math.random()*9)+1;
			 							bcs_number = (int)(Math.random()*3)+2;
			 							milkx=1105;
			 							if(bcs_random<=3){
			 								bcs_index=6; //바나나
			 								bcs_draw_index=1;
			 								milk_index=0;
			 							}
			 							else if(bcs_random>3 && bcs_random<=6){
			 								bcs_index=9;  //초코
			 								bcs_draw_index=2;
			 								milk_index=0;
			 							}
			 							else if(bcs_random>6 && bcs_random<=9){
			 								bcs_index=12;  //딸기
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
			      
		     }//switch문 종료
		    
		     return true;
		} //touchevent 종료
		
    }//view class 종료
    
    /**
	 * Back버튼 눌렀을 때 다이얼로그 확인
	 * 확인버튼 : 메인 액티비티로 이동
	 * 취소버튼 : 현재 액티비티 유지
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event){
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder alert = new AlertDialog.Builder(Stage4Activity.this);
			alert.setMessage("메인으로 돌아가시겠습니까?").setPositiveButton("확인", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					Intent intent = new Intent(Stage4Activity.this, MainActivity.class);
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
	} // 우유
}
