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
 * ��������1 : �䱸��Ʈ
 * ���� �䱸��Ʈ - ���� ��� �Ʒ��� �巡�� (��)
 * �ٸ��� �䱸��Ʈ - �䱸��Ʈ ��� �������� �巡�� (��)
 *
 */
public class Stage1Activity extends Activity {
	
	int x, y; //��ġ��ǥ��
	boolean point1=false, point2=false;  //point1:��ġ��������, point2:��ġ������, only:�����߿��ߺ��ȵǵ���
	boolean normal_error1=false, normal_error2=false, trash_error1=false, trash_error2=false ;
	boolean trash_point1=false, trash_point2=false;
	int success=0;  //���� ����
	//Ÿ�̸�
	TextView timerView; 
	int time = MainActivity.time;
	int ttime=0;
	long a;
	int stx=1350, sty=0; //���� ��ġ
	int yox=1070;
	int yo_index=0; //�䱸��Ʈ �������� �ٸ���������
	int random=0; //�䱸��Ʈ ���� �Ҵ�
	
	int total = 0;  //���� ������ ����
	
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
			//#���۵Ǹ� ���, �䱸��Ʈ, ����, ĳ����1, Ÿ�̸�, �������� ����
			canvas.drawBitmap(bg_img, 0, 0, null);
			canvas.drawBitmap(straw, stx, sty, null);
			canvas.drawBitmap(yogurt[yo_index], yox, 540, null);
			canvas.drawBitmap(char_draw[char_draw_index], 100, 150, null);
			Paint paint = new Paint();
			paint.setColor(Color.BLACK);
			paint.setTextSize(80);
			paint.setTypeface(Typeface.create("", Typeface.BOLD));
			canvas.drawText("�ð� " + time, 100, 100, paint);
			canvas.drawText("���� " + total , 100, 250, paint);
			
		
			
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
						invalidate();  //���� ����ġ�� �ٽñ׸���
					}
				};
				handler.postDelayed(run, 200);  //0.3�� ����
			}

		}
		
		
		/**
		 * ����ڰ� ȭ���� ��ġ���� �� ����Ǵ� �̺�Ʈ
		 * ������ ���� ������ ���� ��ǥ���� �о� ���� ���θ� Ȯ��.
		 */
		public boolean onTouchEvent(MotionEvent event){
			int action = event.getAction();
			
			switch(action){
			case MotionEvent.ACTION_DOWN :
				//���� �䱸��Ʈ�� ���
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
				
				//�ٸ��� �䱸��Ʈ�� ���
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
				//���� �䱸��Ʈ�� ���
				if(yo_index==0){
					x = (int)event.getX();
					y = (int)event.getY();
					
					//�߸��������
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
					
					//������������
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
									invalidate();  //���� ����ġ�� �ٽñ׸���
								}
							};
							handler.postDelayed(run, 200);  //0.3�� ����
						}
						else {
							point1 = false;
							point2 = false;
						}
					}
				}
				
				//�ٸ��� �䱸��Ʈ�� ���
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
	 * Back��ư ������ �� ���̾�α� Ȯ��
	 * Ȯ�ι�ư : ���� ��Ƽ��Ƽ�� �̵�
	 * ��ҹ�ư : ���� ��Ƽ��Ƽ ����
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event){
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder alert = new AlertDialog.Builder(Stage1Activity.this);
			alert.setMessage("�������� ���ư��ðڽ��ϱ�?").setPositiveButton("Ȯ��", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					Intent intent = new Intent(Stage1Activity.this, MainActivity.class);
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
	} // �䱸��Ʈ
}
