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



//�Ѥѱ�� ����Ұ�� ��Ƽ��Ƽ�Ѥ�
public class ScoreActivity extends Activity {

	


	
	EditText text;
	String name;
	int stage;
	int totalscore;
	
	TextView scoreview; //���� �����پ�
	
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
	     * ���� ��� ��ư. ����ڰ� ȹ���� ������ ��� �����Ѵ�.
	     * stageActivity���� �Ѱܹ��� �������� ���� �����Ͽ�
	     * �ش� ���������� ��� ������ ����ǵ��� �Ѵ�. 
	     */
	    findViewById(R.id.rank_inputbt).setOnClickListener(new OnClickListener(){
	    	public void onClick(View view){
	    		
	    		//#��Ϲ�ư�� ������ ������ ��� ������ �� �������� ���ư���
	    		//����ڰ� �Է��� �̸��� �޾ƿ´�	    		
	    		text = (EditText)findViewById(R.id.editText1);
	    		name = text.getText().toString();
	    		
	    		//�������� ����
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
	    			Log.i("error", "���⼭����?111");
	    			selectmilk2(name, totalscore, "milk");
	    			break;
	    		case 5 :
	    			selectwine2(name, totalscore, "wine");
	    			break;
	    		}
	    			
	    		//�������� ���ư���
	    		finish();
	    		Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
	    		MainActivity.splash = false;
	    		startActivity(intent);
	    		
	    	}
	    });
	    
	    findViewById(R.id.rank_cancelbt).setOnClickListener(new OnClickListener(){
	    	public void onClick(View view){
	    		AlertDialog.Builder alert = new AlertDialog.Builder(ScoreActivity.this);
				alert.setMessage("�������� ���ư��ðڽ��ϱ�?").setPositiveButton("Ȯ��", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){
						Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
						MainActivity.splash = false;
						finish();
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
	    		
	    		
	    	}
	    });	
	}
	
	/**
	 * Back��ư ������ �� ���̾�α� Ȯ��
	 * Ȯ�ι�ư : ���� ��Ƽ��Ƽ�� �̵�
	 * ��ҹ�ư : ���� ��Ƽ��Ƽ ����
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event){
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder alert = new AlertDialog.Builder(ScoreActivity.this);
			alert.setMessage("�������� ���ư��ðڽ��ϱ�?").setPositiveButton("Ȯ��", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
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
	} // �ݶ�
	
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
	} // ������
	
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
	} // ����
	
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
	} // ����
	
	/**
	 * 3������ ���������� Ŀ�� ��ŷ�� ����� �� �־ �̸��� �Է��ϰ� ��Ϲ�ư�� ���� ���
	 * 2���� ������ �����ͼ� ���������� ���Ѵ�.
	 * 2���� �������� ���������� ũ�� select___1()�Լ� ȣ��, ������ update___3()�Լ� ȣ��
	 * @param gamename ����ϰ��� �ϴ� �̸�
	 * @param gamescore ���� ����
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
	} // �䱸��Ʈ
	
	void selectcoke2(String gamename, int gamescore, String coke) {
		Cursor c1 = MainActivity.db.rawQuery("select * from coke", null);
		c1.moveToFirst(); c1.moveToNext();
		
		int score2 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score2)
			selectcoke1(gamename, gamescore, coke);
		else
			updatecoke3(gamename, gamescore, coke);
	} // �ݶ�

	
	void selectsham2(String gamename, int gamescore, String sham) {
		Cursor c1 = MainActivity.db.rawQuery("select * from sham", null);
		c1.moveToFirst(); c1.moveToNext();
		
		int score2 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score2)
			selectsham1(gamename, gamescore, sham);
		else
			updatesham3(gamename, gamescore, sham);
	} // ������
	
	
	void selectmilk2(String gamename, int gamescore, String milk) {
		Log.i("error", "���⼭����? selectmilk2");
		Cursor c1 = MainActivity.db.rawQuery("select * from milk", null);
		c1.moveToFirst(); c1.moveToNext();
		
		int score2 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score2)
			selectmilk1(gamename, gamescore, milk);
		else
			updatemilk3(gamename, gamescore, milk);
	} // ����
	
	
	void selectwine2(String gamename, int gamescore, String wine) {
		Cursor c1 = MainActivity.db.rawQuery("select * from wine", null);
		c1.moveToFirst(); c1.moveToNext();
		
		int score2 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score2)
			selectwine1(gamename, gamescore, wine);
		else
			updatewine3(gamename, gamescore, wine);
	} // ����

	/**
	 * 2������ ���������� ���� ���
	 * ��ŷ 3���� ���������� update
	 * @param gamename ����� �̸�
	 * @param gamescore ���� ����
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
			 //t1.setText("updateyo3 ����");
			 Log.i("scroe", "updateyo3����");
		 }
	} // �䱸��Ʈ 
	
	void updatecoke3(String gamename, int gamescore, String coke) {
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"3"};
	 
	        int rowAffected = MainActivity.db.update(coke, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatecoke3 ����");
			Log.i("score", "updatecoke3 ����");
		}
	} // �ݶ�
	
	void updatesham3(String gamename, int gamescore, String sham) {
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"3"};
	 
	        int rowAffected = MainActivity.db.update(sham, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatesham3 ����");
			Log.i("score", "updatesham3 ����");
		}	
	} // ������
	
	void updatemilk3(String gamename, int gamescore, String milk) {
		Log.i("error", "���⼭����?updatemilk3");
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"3"};
	 
	        int rowAffected = MainActivity.db.update(milk, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatemilk3 ����");
			Log.i("score", "updatemilk3 ����");
		}	
	} // ����
	
	void updatewine3(String gamename, int gamescore,  String wine) {
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"3"};
	 
	        int rowAffected = MainActivity.db.update(wine, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatewine3 ����");
			Log.i("score", "updatewine3 ����");
		}	
	} // ����
	
	/**
	 * 2�� �������� ū ���
	 * 3���� 2���� �̸��� ������ update�Ѵ�.
	 * 1���� ������ ���� ������ ���Ѵ�.
	 * 1���� �������� ���� ������ ũ�� update___1()�Լ� ȣ��
	 * 1���� �������� ���� ������ ������ update___2()�Լ� ȣ��
	 * @param gamename ��ŷ�� ����� �̸�
	 * @param gamescore ���� ����
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
			//t1.setText("selectyo1 ����");
			Log.i("score", "selectyo1 ����");
		}
		
		Cursor c1 = MainActivity.db.rawQuery("select * from yo", null);
		c1.moveToFirst();
		
		int score1 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score1)
			updateyo1(gamename, gamescore, yo);
		else
			updateyo2(gamename, gamescore, yo);
	} // �䱸��Ʈ
	
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
			//t1.setText("selectcoke1 ����");
			Log.i("score", "selectcoke1 ����");
		}
		
		Cursor c1 = MainActivity.db.rawQuery("select * from coke", null);
		c1.moveToFirst();
		
		int score1 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score1)
			updateyo1(gamename, gamescore, coke);
		else
			updateyo2(gamename, gamescore, coke);
	} // �ݶ�
	
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
			//t1.setText("selectsham1 ����");
			Log.i("score", "selectsham1 ����");
		}
		
		Cursor c1 = MainActivity.db.rawQuery("select * from sham", null);
		c1.moveToFirst();
		int score1 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score1)
			updatesham1(gamename, gamescore, sham);
		else
			updatesham2(gamename, gamescore, sham);
	} // ������
	
	void selectmilk1(String gamename, int gamescore, String milk) {
		Log.i("error", "���⼭����? selectmilk1");
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
			//t1.setText("selectmilk1 ����");
			Log.i("score", "selectmilk1 ����");
		}
		
		Cursor c1 = MainActivity.db.rawQuery("select * from milk", null);
		c1.moveToFirst();
		int score1 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score1)
			updatemilk1(gamename, gamescore, milk);
		else
			updatemilk2(gamename, gamescore, milk);
	} // ����
	
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
			//t1.setText("selectwine1 ����");
			Log.i("score", "selectwine1����");
		}
		
		Cursor c1 = MainActivity.db.rawQuery("select * from wine", null);
		c1.moveToFirst();
		int score1 = c1.getInt(3);
		c1.close();
		
		if(gamescore >= score1)
			updatewine1(gamename, gamescore, wine);
		else
			updatewine2(gamename, gamescore, wine);
	} // ����
	
	/**
	 * 2���� �������� ���������� ���� 1���� �������� ���� ���
	 * ��ŷ 2���� ���� ������ �� �̸��� ����Ѵ�.
	 * @param gamename ����� �̸�
	 * @param gamescore ���� ����
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
			//t1.setText("updateyo2 ����");
			Log.i("score", "updateyo2����");
		}
	} // �䱸��Ʈ
	
	
	void updatecoke2(String gamename, int gamescore, String coke) {
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(coke, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatecoke2 ����");
			Log.i("score", "updatecoke2 ����");
		}
	} // �ݶ�
	

	void updatesham2(String gamename, int gamescore, String sham) {
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(sham, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatesham2 ����");
			Log.i("score", "updatesham2 ����");
		}
	} // ������
	

	void updatemilk2(String gamename, int gamescore, String milk) {
		Log.i("error", "���⼭����? updatemilk2");
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(milk, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatemilk2 ����");
			Log.i("score", "updatemilk2 ����");
		}
	} // ����
	

	void updatewine2(String gamename, int gamescore,  String wine) {
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"2"};
	 
	        int rowAffected = MainActivity.db.update(wine, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatewine2 ����");
			Log.i("score", "updatewine2 ����");
		}
	} // ����
	
	/**
	 * 1���� �������� ���� ������ ���� ���
	 * 1���� ������ ��ŷ 2���� update
	 * ���� ������ ��ŷ 1���� update
	 * @param gamename ����� �̸�
	 * @param gamescore ���� ����
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
			//t1.setText("updateyo1�� 2���� ��� ����");
			Log.i("score", "updateyo1�� 2���� ��� ����");
		}
		
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"1"};
	 
	        int rowAffected = MainActivity.db.update(yo, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updateyo1�� 1���� ��� ����");
			Log.i("score", "updateyo1�� 1���� ��� ����");
		}
	} // �䱸��Ʈ
	
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
			//t1.setText("updatecoke1�� 2���� ��� ����");
			Log.i("score", "updatecoke1�� 2���� ��� ����");
		}
		
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"1"};
	 
	        int rowAffected = MainActivity.db.update(coke, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatecoke1�� 1���� ��� ����");
			Log.i("score", "updatecoke1�� 1���� ��� ����");
		}	
	} // �ݶ�
	
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
			//t1.setText("updatesham1�� 2���� ��� ����");
			Log.i("score", "updatesham1�� 2���� ��� ����");
		}
		
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"1"};
	 
	        int rowAffected = MainActivity.db.update(sham, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatesham1�� 1���� ��� ����");
			Log.i("score", "updatesham1 1���� ��� ����");
		}
	} // ������
	
	void updatemilk1(String gamename, int gamescore, String milk) {
		Log.i("error", "���⼭����? updatemilk1");
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
			//t1.setText("updatemilk1�� 2���� ��� ����");
			Log.i("score", "updatemilk1�� 2���� ��� ����");
		}
		
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"1"};
	 
	        int rowAffected = MainActivity.db.update(milk, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatemilk1�� 1���� ��� ����");
			Log.i("score", "updatemilk1�� 1���� ��� ����");
		}
	} // ����
	
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
			//t1.setText("updateyo1�� 2���� ��� ����");
			Log.i("score", "updateyo1�� 2���� ��� ����");
		}
		
		try {
	        ContentValues recordValues = new ContentValues();
	        recordValues.put("dbname", gamename);
	        recordValues.put("dbscore", gamescore);
	        
	        String[] whereArgs = {"1"};
	 
	        int rowAffected = MainActivity.db.update(wine, recordValues, "dbrank = ?", whereArgs);
		}
		catch(Exception ex) {
			//t1.setText("updatewine1�� 1���� ��� ����");
			Log.i("score", "updatewine1�� 1���� ��� ����");
		}	} // ����
	
}
