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

import android.widget.TextView;
import android.widget.Toast;

/**
 * 스테이지1 : 요구르트
 * 정상 요구르트 - 빨대 잡고 아래로 드래그 (↓)
 * 다먹은 요구르트 - 요구르트 잡고 왼쪽으로 드래그 (←)
 *
 */
public class Stage1Activity extends Activity {
	
	int x, y; //터치좌표값
	boolean point1=false, point2=false;  //point1:터치눌렀을때, point2:터치뗐을때, only:진행중에중복안되도록
	boolean normal_error1=false, normal_error2=false, trash_error1=false, trash_error2=false ;
	boolean trash_point1=false, trash_point2=false;
	int success=0;  //성공 개수
	//타이머
	TextView timerView; 
	int time = MainActivity.time;
	int ttime=0;
	long a;
	int stx=1350, sty=0; //빨대 위치
	int yox=1070;
	int yo_index=0; //요구르트 정상일지 다먹은거일지
	int random=0; //요구르트 종류 할당
	
	int total = 0;  //점수 저장할 변수
	
	public SoundPool soundpool;
	int efs_yogurt, efs_trash, efs_error;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    time = 30 ;
	    setContentView(new Stage1View(this));
	    
	    soundpool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0); 
	    efs_yogurt = soundpool.load(this, R.raw.efs_bbong, 1);
	    efs_error = soundpool.load(this, R.raw.efs_error, 1);
	    efs_trash = soundpool.load(this, R.raw.efs_trash, 1);
	    
	    if(MainActivity.bgmonoff==true){
			Stage2Activity.bgm_stage = MediaPlayer.create(this, R.raw.stage_bgm);
			Stage2Activity.bgm_stage.setLooping(true);
			Stage2Activity.bgm_stage.start();
		}
	}
	
	class Stage1View extends View{
		
		Bitmap straw;
		Bitmap bg_img;
		Bitmap[] yogurt = new Bitmap[2];
		Bitmap[] char_draw = new Bitmap[7];
		int char_draw_index=0;
		
		
		public Stage1View(Context context){
			super(context);
			yogurt[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_img);
			yogurt[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_img2);
			straw = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_bbal);
			bg_img = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_bgimg);
			
			char_draw[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_char1);
			char_draw[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_char2);
			char_draw[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_char3);
			char_draw[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_char4);
			char_draw[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_char5);
			char_draw[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_char6);
			char_draw[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_char7);

		}
		
		public void onDraw(Canvas canvas){
			//#시작되면 배경, 요구르트, 빨대, 캐릭터1, 타이머, 성공개수 띄우기
			canvas.drawBitmap(bg_img, 0, 0, null);
			canvas.drawBitmap(straw, stx, sty, null);
			canvas.drawBitmap(yogurt[yo_index], yox, 540, null);
			canvas.drawBitmap(char_draw[char_draw_index], 100, 150, null);
			Paint paint = new Paint();
			paint.setColor(Color.BLACK);
			paint.setTextSize(80);
			paint.setTypeface(Typeface.create("", Typeface.BOLD));
			canvas.drawText("시간 " + time, 100, 100, paint);
			canvas.drawText("점수 " + total , 100, 250, paint);
			
		
			
			if(time==1){	
				if(selectyo3(total)){
					Intent intent = new Intent(Stage1Activity.this, ScoreActivity.class);
					intent.putExtra("stage", 1);
					intent.putExtra("totalscore", total);
					finish();
					startActivity(intent);
				}
				if(selectyo3(total)==false){
					Intent intent = new Intent(Stage1Activity.this, ScoreActivity2.class);
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
			postInvalidate();
			
			if(point1==true && point2==true){
				point1=false;
				point2=false;
				Handler handler = new Handler();
				Runnable run = new Runnable(){
					public void run(){
						sty=0;
						invalidate();  //빨대 원위치로 다시그리기
					}
				};
				handler.postDelayed(run, 200);  //0.3초 쉬기
			}

		}
		
		
		/**
		 * 사용자가 화면을 터치했을 때 실행되는 이벤트
		 * 눌렀을 때와 떼었을 때의 좌표값을 읽어 성공 여부를 확인.
		 */
		public boolean onTouchEvent(MotionEvent event){
			int action = event.getAction();
			
			switch(action){
			case MotionEvent.ACTION_DOWN :
				//정상 요구르트의 경우
				if(yo_index==0){
					x = (int)event.getX();
					y = (int)event.getY();

					if(x>1150 && x<1600 && y<400)
						point1 = true;
					else
						point1 = false;
					
					if(x>900 && x<1600 && y>400){
						normal_error1 = true;
					}
					else
						normal_error1 = false;
				}
				
				//다먹은 요구르트의 경우
				if(yo_index==1){
					x = (int)event.getX();
					y = (int)event.getY();

					if(x>900 && x<1600 && y>400)
						trash_point1 = true;
					else
						trash_point1 = false;
					
					if(x>1150 && x<1600 && y<400){
						trash_error1 = true;
					}
					else
						trash_error2 = false;
				}					
				break;
				
			case MotionEvent.ACTION_MOVE :
				if(yo_index==1){
					x = (int)event.getX();
					y = (int)event.getY();
					
					if(x<1070 && x>900){
						yox = x ;
					}
					if(x<900)
						yox = 750;
				}
				break;			
				
			case MotionEvent.ACTION_UP :
				//정상 요구르트의 경우
				if(yo_index==0){
					x = (int)event.getX();
					y = (int)event.getY();
					
					//잘못했을경우
					if(normal_error1==true){
						if(x<900){
							total-=100;
							if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
	     					}
						}
						else
							normal_error1=false;
					}
					
					//제대로했을경우
					if(point1 == true ){
						if(x>1000 && x<1600 && y>400 && y<1500){
							//point1 = false;
							point2 = true;
							sty+=400;
							success++;
							total += 200;
							
							if(MainActivity.efsonoff==true){  
								soundpool.play(efs_yogurt, 1, 1, 0, 0, 1);  
	     					}
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
									if(random<=8){
										//yox = 1070;
										point1 = false;
										point2 = false;
										yo_index=0;
									}
									else{
										//yox = 1070;
										point1 = false;
										point2 = false;
										yo_index=1;
										//sty=0;
									}
									invalidate();  //빨대 원위치로 다시그리기
								}
							};
							handler.postDelayed(run, 200);  //0.3초 쉬기
						}
						else {
							point1 = false;
							point2 = false;
						}
					}
				}
				
				//다먹은 요구르트의 경우
				if(yo_index==1){
					x = (int)event.getX();
					y = (int)event.getY();
					
					if(trash_error1==true){
						if(x>1000 && x<1600 && y>400 && y<1500){
							total-=100;
							if(MainActivity.efsonoff==true){  
	    	    				soundpool.play(efs_error, 1, 1, 0, 0, 1);  
	     					}
						}
						else
							trash_error1=false;
					}
					
					if(x<900){
						//point1 = false;
						trash_point2 = true;
						success++;
						if(MainActivity.efsonoff==true){  
    	    				soundpool.play(efs_trash, 1, 1, 0, 0, 1);  
     					}
						total += 100;
						
						if(success%3==0){
							if(char_draw_index<6)
								char_draw_index++;
							else if(char_draw_index>=6)
								char_draw_index=6;
						}
						
						random = (int) (Math.random()*10)+1;
						if(random<=8){
							trash_point1 = false;
							trash_point2 = false;
							yox = 1070;
							yo_index=0;
						}
						else{
							trash_point1 = false;
							trash_point2 = false;
							yox = 1070;
							yo_index=1;
						}
						invalidate();
						
					}
					else{
						trash_point1 = false;
						trash_point2 = false;
						sty=0;
					}
				}
				break;
			}
			return true;
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
			AlertDialog.Builder alert = new AlertDialog.Builder(Stage1Activity.this);
			alert.setMessage("메인으로 돌아가시겠습니까?").setPositiveButton("확인", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					Intent intent = new Intent(Stage1Activity.this, MainActivity.class);
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
	boolean selectyo3(int gamescore) {
		Cursor c1 = MainActivity.db.rawQuery("select * from yo", null);
		c1.moveToFirst(); c1.moveToNext();
		c1.moveToNext();
		
		int score3 = c1.getInt(3);
		c1.close();
		
		if(gamescore > score3)
			return true;
		else
			return false;
	} // 요구르트
}
