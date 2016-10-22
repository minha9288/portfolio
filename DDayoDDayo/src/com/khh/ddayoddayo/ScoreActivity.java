package com.khh.ddayoddayo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



//ㅡㅡ기록 등록할경우 액티비티ㅡㅡ
public class ScoreActivity extends Activity {

	


	
	EditText text;
	String name;
	int stage;
	int totalscore;
	
	TextView scoreview; //점수 보여줄애
	
	public SoundPool soundpool;
	int score_in;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);    
	    setContentView(R.layout.activity_score);
	    
	    soundpool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0); 
	    score_in = soundpool.load(this, R.raw.efs_rankinput, 1);
	    soundpool.play(score_in, 1, 1, 0, 0, 1);  
	    if(MainActivity.efsonoff==true){  
			soundpool.play(score_in, 1, 1, 0, 0, 1);  
			}
	    


	    Intent intent = getIntent();
	    stage = intent.getExtras().getInt("stage");
	    totalscore = intent.getExtras().getInt("totalscore");
	    
	    
	    scoreview = (TextView) findViewById(R.id.scoreview);
	    scoreview.setText(String.valueOf(totalscore));
    
	    /**
	     * 점수 등록 버튼. 사용자가 획득한 점수를 디비에 저장한다.
	     * stageActivity에서 넘겨받은 스테이지 값을 구분하여
	     * 해당 스테이지의 디비에 저장이 실행되도록 한다. 
	     */
	    findViewById(R.id.rank_inputbt).setOnClickListener(new OnClickListener(){
	    	public void onClick(View view){
	    		
	    		//#등록버튼을 누르면 점수를 디비에 저장한 후 메인으로 돌아간다
	    		//사용자가 입력한 이름을 받아온다	    		
	    		text = (EditText)findViewById(R.id.editText1);
	    		name = text.getText().toString();
	    		
	    		//스테이지 구분
	    		switch(stage){
	    		case 1 :
	    			selectyo2(name, totalscore, "yo");
	    			break;
	    		case 2 :
	    			selectcoke2(name, totalscore, "coke");
	    			break;
	    		case 3 :
	    			selectsham2(name, totalscore, "sham");
	    			break;
	    		case 4 :
	    			Log.i("error", "여기서에러?111");
	    			selectmilk2(name, totalscore, "milk");
	    			break;
	    		case 5 :
	    			selectwine2(name, totalscore, "wine");
	    			break;
	    		}
	    			
	    		//메인으로 돌아가기
	    		finish();
	    		Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
	    		MainActivity.splash = false;
	    		startActivity(intent);
	    		
	    	}
	    });
	    
	    findViewById(R.id.rank_cancelbt).setOnClickListener(new OnClickListener(){
	    	public void onClick(View view){
	    		AlertDialog.Builder alert = new AlertDialog.Builder(ScoreActivity.this);
				alert.setMessage("메인으로 돌아가시겠습니까?").setPositiveButton("확인", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){
						Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
						MainActivity.splash = false;
						finish();
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
	    		
	    		
	    	}
	    });	
	}
	
	/**
	 * Back버튼 눌렀을 때 다이얼로그 확인
	 * 확인버튼 : 메인 액티비티로 이동
	 * 취소버튼 : 현재 액티비티 유지
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event){
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder alert = new AlertDialog.Builder(ScoreActivity.this);
			alert.setMessage("메인으로 돌아가시겠습니까?").setPositiveButton("확인", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
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
	
	boolean selectsham3(int gamescore) {
		Cursor c1 = MainActivity.db.rawQuery("select * from sham", null);
		c1.moveToFirst(); c1.moveToNext();
		c1.moveToNext();
		
		int score3 = c1.getInt(3);
		c1.close();
		
		if(gamescore < score3)
			return true;
		else
			return false;
	} // 샴페인
	
	boolean selectmilk3(int gamescore) {
		Cursor c1 = MainActivity.db.rawQuery("select * from milk", null);
		c1.moveToFirst(); c1.moveToNext();
		c1.moveToNext();
		
		int score3 = c1.getInt(3);
		c1.close();
		
		if(gamescore < score3)
			return true;
		else
			return false;
	} // 우유
	
	boolean selectwine3(int gamescore) {
		Cursor c1 = MainActivity.db.rawQuery("select * from wine", null);
		c1.moveToFirst(); c1.moveToNext();
		c1.moveToNext();
		
		int score3 = c1.getInt(3);
		c1.close();
		
		if(gamescore < score3)
			return true;
		else
			return false;
	} // 와인
	
	/**
	 * 3위보다 게임점수가 커서 랭킹에 등록할 수 있어서 이름을 입력하고 등록버튼을 누른 경우
	 * 2위의 점수를 가져와서 게임점수와 비교한다.
	 * 2위의 점수보다 게임점수가 크면 select___1()함수 호출, 작으면 update___3()함수 호출
	 * @param gamename 등록하고자 하는 이름
	 * @param gamescore 게임 점수
	 */
	void selectyo2(String gamename, int gamescore,  String yo) {
		Cursor c1 = MainActivity.db.rawQuery("select * from yo", null);
		c1.moveToFirst();
  		c1.moveToNext();
  		
		int score2 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score2)
			selectyo1(gamename, gamescore, yo);
		else
			updateyo3(gamename, gamescore, yo);
	} // 요구르트
	
	void selectcoke2(String gamename, int gamescore, String coke) {
		Cursor c1 = MainActivity.db.rawQuery("select * from coke", null);
		c1.moveToFirst(); c1.moveToNext();
		
		int score2 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score2)
			selectcoke1(gamename, gamescore, coke);
		else
			updatecoke3(gamename, gamescore, coke);
	} // 콜라

	
	void selectsham2(String gamename, int gamescore, String sham) {
		Cursor c1 = MainActivity.db.rawQuery("select * from sham", null);
		c1.moveToFirst(); c1.moveToNext();
		
		int score2 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score2)
			selectsham1(gamename, gamescore, sham);
		else
			updatesham3(gamename, gamescore, sham);
	} // 샴페인
	
	
	void selectmilk2(String gamename, int gamescore, String milk) {
		Log.i("error", "여기서에러? selectmilk2");
		Cursor c1 = MainActivity.db.rawQuery("select * from milk", null);
		c1.moveToFirst(); c1.moveToNext();
		
		int score2 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score2)
			selectmilk1(gamename, gamescore, milk);
		else
			updatemilk3(gamename, gamescore, milk);
	} // 우유
	
	
	void selectwine2(String gamename, int gamescore, String wine) {
		Cursor c1 = MainActivity.db.rawQuery("select * from wine", null);
		c1.moveToFirst(); c1.moveToNext();
		
		int score2 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score2)
			selectwine1(gamename, gamescore, wine);
		else
			updatewine3(gamename, gamescore, wine);
	} // 와인

	/**
	 * 2위보다 게임점수가 낮을 경우
	 * 랭킹 3위에 게임점수를 update
	 * @param gamename 등록할 이름
	 * @param gamescore 게임 점수
	 */
	void updateyo3(String gamename, int gamescore, String yo) {
		try {
		        ContentValues recordValues = new ContentValues();
		        recordValues.put("dbname", gamename);
		        recordValues.put("dbscore", gamescore);
		        
		        String[] whereArgs = {"3"};
		 
		        int rowAffected = MainActivity.db.update(yo, recordValues, "dbrank = ?", whereArgs);
		 }
		 catch(Exception ex) {
			 //t1.setText("updateyo3 오류");
			 Log.i("scroe", "updateyo3오류");
		 }
	} // 요구르트 
	
	void updatecoke3(String gamename, int gamescore, String coke) {
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"3"};
	 
	        int rowAffected = MainActivity.db.update(coke, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatecoke3 오류");
			Log.i("score", "updatecoke3 오류");
		}
	} // 콜라
	
	void updatesham3(String gamename, int gamescore, String sham) {
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"3"};
	 
	        int rowAffected = MainActivity.db.update(sham, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatesham3 오류");
			Log.i("score", "updatesham3 오류");
		}	
	} // 샴페인
	
	void updatemilk3(String gamename, int gamescore, String milk) {
		Log.i("error", "여기서에러?updatemilk3");
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"3"};
	 
	        int rowAffected = MainActivity.db.update(milk, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatemilk3 오류");
			Log.i("score", "updatemilk3 오류");
		}	
	} // 우유
	
	void updatewine3(String gamename, int gamescore,  String wine) {
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"3"};
	 
	        int rowAffected = MainActivity.db.update(wine, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatewine3 오류");
			Log.i("score", "updatewine3 오류");
		}	
	} // 와인
	
	/**
	 * 2위 점수보다 큰 경우
	 * 3위에 2위의 이름과 점수를 update한다.
	 * 1위의 점수와 게임 점수를 비교한다.
	 * 1위의 점수보다 게임 점수가 크면 update___1()함수 호출
	 * 1위의 점수보다 게임 점수가 작으면 update___2()함수 호출
	 * @param gamename 랭킹에 등록할 이름
	 * @param gamescore 게임 점수
	 */
	void selectyo1(String gamename, int gamescore, String yo) {
		try {
	        ContentValues recordValues = new ContentValues();
	        Cursor c = MainActivity.db.rawQuery("select * from yo", null);
	        c.moveToFirst();
	        c.moveToNext();
	        
	        int score = c.getInt(3);
	        String name = c.getString(2);
	        c.close();
	        
	        recordValues.put("dbname", name);
	        recordValues.put("dbscore", score);
	        
	        String[] whereArgs = {"3"};
	 
	        int rowAffected = MainActivity.db.update(yo, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("selectyo1 오류");
			Log.i("score", "selectyo1 오류");
		}
		
		Cursor c1 = MainActivity.db.rawQuery("select * from yo", null);
		c1.moveToFirst();
		
		int score1 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score1)
			updateyo1(gamename, gamescore, yo);
		else
			updateyo2(gamename, gamescore, yo);
	} // 요구르트
	
	void selectcoke1(String gamename, int gamescore, String coke) {
		try {
	        ContentValues recordValues = new ContentValues();
	        Cursor c = MainActivity.db.rawQuery("select * from coke", null);
	        c.moveToFirst();
	        c.moveToNext();
	        
	        int score = c.getInt(3);
	        String name = c.getString(2);
	        c.close();
	        
	        recordValues.put("dbname", name);
	        recordValues.put("dbscore", score);
	        
	        String[] whereArgs = {"3"};
	 
	        int rowAffected = MainActivity.db.update(coke, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("selectcoke1 오류");
			Log.i("score", "selectcoke1 오류");
		}
		
		Cursor c1 = MainActivity.db.rawQuery("select * from coke", null);
		c1.moveToFirst();
		
		int score1 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score1)
			updateyo1(gamename, gamescore, coke);
		else
			updateyo2(gamename, gamescore, coke);
	} // 콜라
	
	void selectsham1(String gamename, int gamescore, String sham) {
		try {
	        ContentValues recordValues = new ContentValues();
	        Cursor c = MainActivity.db.rawQuery("select * from sham", null);
	        c.moveToFirst();
	        c.moveToNext();
	        
	        int score = c.getInt(3);
	        String name = c.getString(2);
	        c.close();
	        
	        recordValues.put("dbname", name);
	        recordValues.put("dbscore", score);
	        
	        String[] whereArgs = {"3"};
	 
	        int rowAffected = MainActivity.db.update(sham, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("selectsham1 오류");
			Log.i("score", "selectsham1 오류");
		}
		
		Cursor c1 = MainActivity.db.rawQuery("select * from sham", null);
		c1.moveToFirst();
		int score1 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score1)
			updatesham1(gamename, gamescore, sham);
		else
			updatesham2(gamename, gamescore, sham);
	} // 샴페인
	
	void selectmilk1(String gamename, int gamescore, String milk) {
		Log.i("error", "여기서에러? selectmilk1");
		try {
	        ContentValues recordValues = new ContentValues();
	        Cursor c = MainActivity.db.rawQuery("select * from milk", null);
	        c.moveToFirst();
	        c.moveToNext();
	        
	        int score = c.getInt(3);
	        String name = c.getString(2);
	        c.close();
	        
	        recordValues.put("dbname", name);
	        recordValues.put("dbscore", score);
	        
	        String[] whereArgs = {"3"};
	 
	        int rowAffected = MainActivity.db.update(milk, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("selectmilk1 오류");
			Log.i("score", "selectmilk1 오류");
		}
		
		Cursor c1 = MainActivity.db.rawQuery("select * from milk", null);
		c1.moveToFirst();
		int score1 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score1)
			updatemilk1(gamename, gamescore, milk);
		else
			updatemilk2(gamename, gamescore, milk);
	} // 우유
	
	void selectwine1(String gamename, int gamescore,  String wine) {
		try {
	        ContentValues recordValues = new ContentValues();
	        Cursor c = MainActivity.db.rawQuery("select * from wine", null);
	        c.moveToFirst();
	        c.moveToNext();
	        
	        int score = c.getInt(3);
	        String name = c.getString(2);
	        c.close();
	        
	        recordValues.put("dbname", name);
	        recordValues.put("dbscore", score);
	        
	        String[] whereArgs = {"3"};
	 
	        int rowAffected = MainActivity.db.update(wine, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("selectwine1 오류");
			Log.i("score", "selectwine1오류");
		}
		
		Cursor c1 = MainActivity.db.rawQuery("select * from wine", null);
		c1.moveToFirst();
		int score1 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score1)
			updatewine1(gamename, gamescore, wine);
		else
			updatewine2(gamename, gamescore, wine);
	} // 와인
	
	/**
	 * 2위의 점수보다 게임점수가 높고 1위의 점수보다 낮을 경우
	 * 랭킹 2위에 게임 점수와 그 이름을 등록한다.
	 * @param gamename 등록할 이름
	 * @param gamescore 게임 점수
	 */
	void updateyo2(String gamename, int gamescore, String yo) {
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(yo, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updateyo2 오류");
			Log.i("score", "updateyo2오류");
		}
	} // 요구르트
	
	
	void updatecoke2(String gamename, int gamescore, String coke) {
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(coke, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatecoke2 오류");
			Log.i("score", "updatecoke2 오류");
		}
	} // 콜라
	

	void updatesham2(String gamename, int gamescore, String sham) {
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(sham, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatesham2 오류");
			Log.i("score", "updatesham2 오류");
		}
	} // 샴페인
	

	void updatemilk2(String gamename, int gamescore, String milk) {
		Log.i("error", "여기서에러? updatemilk2");
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(milk, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatemilk2 오류");
			Log.i("score", "updatemilk2 오류");
		}
	} // 우유
	

	void updatewine2(String gamename, int gamescore,  String wine) {
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(wine, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatewine2 오류");
			Log.i("score", "updatewine2 오류");
		}
	} // 와인
	
	/**
	 * 1위의 점수보다 게임 점수가 높을 경우
	 * 1위의 점수를 랭킹 2위로 update
	 * 게임 점수를 랭킹 1위로 update
	 * @param gamename 등록할 이름
	 * @param gamescore 게임 점수
	 */
	void updateyo1(String gamename, int gamescore, String yo) {
		try {
	        ContentValues recordValues = new ContentValues();
	        Cursor c = MainActivity.db.rawQuery("select * from yo", null);
	        c.moveToFirst();
	        
	        int score = c.getInt(3);
	        String name = c.getString(2);
	        c.close();
	        
	        recordValues.put("dbname", name);
	        recordValues.put("dbscore", score);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(yo, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updateyo1의 2위에 등록 오류");
			Log.i("score", "updateyo1의 2위에 등록 오류");
		}
		
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"1"};
	 
	        int rowAffected = MainActivity.db.update(yo, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updateyo1의 1위에 등록 오류");
			Log.i("score", "updateyo1의 1위에 등록 오류");
		}
	} // 요구르트
	
	void updatecoke1(String gamename, int gamescore, String coke) {
		try {
	        ContentValues recordValues = new ContentValues();
	        Cursor c1 = MainActivity.db.rawQuery("select * from coke", null);
	        c1.moveToFirst();
	        
	        int score = c1.getInt(3);
	        String name = c1.getString(2);
	        c1.close();
	        
	        recordValues.put("dbname", name);
	        recordValues.put("dbscore", score);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(coke, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatecoke1의 2위에 등록 오류");
			Log.i("score", "updatecoke1의 2위에 등록 오류");
		}
		
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"1"};
	 
	        int rowAffected = MainActivity.db.update(coke, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatecoke1의 1위에 등록 오류");
			Log.i("score", "updatecoke1의 1위에 등록 오류");
		}	
	} // 콜라
	
	void updatesham1(String gamename, int gamescore, String sham) {
		try {
	        ContentValues recordValues = new ContentValues();
	        Cursor c = MainActivity.db.rawQuery("select * from sham", null);
	        c.moveToFirst();
	        
	        int score = c.getInt(3);
	        String name = c.getString(2);
	        c.close();
	        
	        recordValues.put("dbname", name);
	        recordValues.put("dbscore", score);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(sham, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatesham1의 2위에 등록 오류");
			Log.i("score", "updatesham1의 2위에 등록 오류");
		}
		
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"1"};
	 
	        int rowAffected = MainActivity.db.update(sham, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatesham1의 1위에 등록 오류");
			Log.i("score", "updatesham1 1위에 등록 오류");
		}
	} // 샴페인
	
	void updatemilk1(String gamename, int gamescore, String milk) {
		Log.i("error", "여기서에러? updatemilk1");
		try {
	        ContentValues recordValues = new ContentValues();
	        Cursor c = MainActivity.db.rawQuery("select * from milk", null);
	        c.moveToFirst();
	        
	        int score = c.getInt(3);
	        String name = c.getString(2);
	        c.close();
	        
	        recordValues.put("dbname", name);
	        recordValues.put("dbscore", score);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(milk, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatemilk1의 2위에 등록 오류");
			Log.i("score", "updatemilk1의 2위에 등록 오류");
		}
		
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"1"};
	 
	        int rowAffected = MainActivity.db.update(milk, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatemilk1의 1위에 등록 오류");
			Log.i("score", "updatemilk1의 1위에 등록 오류");
		}
	} // 우유
	
	void updatewine1(String gamename, int gamescore,  String wine) {
		try {
	        ContentValues recordValues = new ContentValues();
	        Cursor c = MainActivity.db.rawQuery("select * from wine", null);
	        c.moveToFirst();
	        
	        int score = c.getInt(3);
	        String name = c.getString(2);
	        c.close();
	        
	        recordValues.put("dbname", name);
	        recordValues.put("dbscore", score);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(wine, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updateyo1의 2위에 등록 오류");
			Log.i("score", "updateyo1의 2위에 등록 오류");
		}
		
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"1"};
	 
	        int rowAffected = MainActivity.db.update(wine, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatewine1의 1위에 등록 오류");
			Log.i("score", "updatewine1의 1위에 등록 오류");
		}	} // 와인
	
}
