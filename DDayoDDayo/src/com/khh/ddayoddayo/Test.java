package com.khh.ddayoddayo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;

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
public class Test extends Activity {
	

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(new MyView(this));
	    
	}
	class MyView extends View{
		
		
		Bitmap straw;
		Bitmap bg_img;
		Bitmap[] milk = new Bitmap[6];
		Bitmap[] char_draw = new Bitmap[7];
		int char_draw_index=0;
		int milk_index=0;
		
		public MyView(Context context){
			super(context);
			milk[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img);
			milk[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img2);
			milk[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img3);
			milk[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img4);
			milk[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img5);
			milk[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_img6);
			
			straw = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_bbal);
			bg_img = BitmapFactory.decodeResource(context.getResources(), R.drawable.yo_bgimg);
			
			
			char_draw[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char1);
			char_draw[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char2);
			//char_draw[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char3);
			//char_draw[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char4);
			//char_draw[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char5);
			//char_draw[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char6);
			//char_draw[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.milk_char7);
			
		}
		
		public void onDraw(Canvas canvas){
			//#시작되면 배경, 요구르트, 빨대, 캐릭터1, 타이머, 성공개수 띄우기
			canvas.drawBitmap(bg_img, 0, 0, null);
			canvas.drawBitmap(straw, 0, 0, null);
			canvas.drawBitmap(milk[milk_index], 1100, 550, null);
			//canvas.drawBitmap(char_draw[char_draw_index], 100, 150, null);

		}
		
	}
	

}







