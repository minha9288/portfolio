package com.khh.ddayoddayo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.util.Log;
import android.widget.TextView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;


public class MainActivity extends Activity implements OnClickListener {

	static int time = 50;  //������������ ����� Ÿ�̸� ����
	static boolean splash = true; //���÷��� ����
	
	//* �����, ȿ���� �κ�
	public static MediaPlayer bgm;  //��Ƽ�̵� ���۽�Ű�� ���� �����Ǵ� Ŭ������ mediaplayer  setting.java���� bgm stop()��Ű���� static
	public static boolean bgmonoff=true;  //����� ���� ó���� üũ�� ���·� �ǵ���
	CheckBox bgmCheck;
	CheckBox efsCheck;
	public static boolean efsonoff=true;  //ȿ���� ���� ó���� üũ�� ���·� �ǵ���
	public static SoundPool soundpool;
	public static int efscamera, efs_yogurt, efs_sham, efs_trash;
	
	TextView t1 ; //��ŷ������ �κ�
	
	static SQLiteDatabase db;
	boolean databaseCreated = false;
	boolean yoCreated = false;
	boolean cokeCreated = false;
	boolean shamCreated = false;
	boolean milkCreated = false;
	boolean wineCreated = false;
	
	String DATABASE_NAME="ddb";
	
	private int imageIndex = 0;
	private int imageIndex2 = 0; 
	
	private ImageView right;
	private ImageView left;
	private ImageView c1; //�䱸��Ʈ
	private ImageView c2; //�ݶ�
	private ImageView c3; //������
	private ImageView c4; //����
	private ImageView c5; //���
	
	int yo=0;
	int coke=1;
	int sham=2;
	int milk=3;
	int all=4;
	
	boolean yocheck = true;;
	boolean cokecheck = false;
	boolean shamcheck = false;
	boolean milkcheck = false;
	boolean allcheck = false;
	
	private ImageView rankbt;
	private ImageView setbt;
	
    View page1, page2;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        


        
      //* ó������ ����� ������
        if(bgmonoff==true){
			bgm = MediaPlayer.create(this, R.raw.main_bgm);
			bgm.setLooping(true);
			bgm.start();
		}

        t1 = (TextView)findViewById(R.id.t1);
        
        

        page1 = findViewById(R.id.page1);
        page2 = findViewById(R.id.page2);
        
  		createDatabase("ddb");
  		createTables("yo", "coke", "sham", "milk", "wine");
  		insertOk();
  		
  		if(yocheck){ //ó���� ����Ʈ ��ŷ ��
  			selectyo();
  		}
  		
  		right = (ImageView)findViewById(R.id.right);
        right.setOnClickListener(this);
        left = (ImageView)findViewById(R.id.left);
        left.setOnClickListener(this);
        left.setVisibility(View.INVISIBLE);
        
        c1 = (ImageView)findViewById(R.id.c1);
        c2 = (ImageView)findViewById(R.id.c2);
        c3 = (ImageView)findViewById(R.id.c3);
   	 	c4 = (ImageView)findViewById(R.id.c4);
		 c5 = (ImageView)findViewById(R.id.c5);
		 c1.setOnClickListener(this);
		 c2.setOnClickListener(this);
		 c3.setOnClickListener(this);
		 c4.setOnClickListener(this);
		 c5.setOnClickListener(this);

 		 
         ImageView rankbt = (ImageView)findViewById(R.id.rankbt);
         rankbt.setOnClickListener(this);
         ImageView setbt = (ImageView)findViewById(R.id.setbt);
         setbt.setOnClickListener(this);
        
		 
         ImageView start_bt = (ImageView)findViewById(R.id.start_bt);
         ImageView exit_bt = (ImageView)findViewById(R.id.exit_bt);
         ImageView info_bt = (ImageView)findViewById(R.id.info_bt);
         
         page2.setVisibility(View.GONE);
         bgmCheck = (CheckBox)findViewById(R.id.checkBox1);
 	     efsCheck = (CheckBox)findViewById(R.id.checkBox2);
 			 
 	  //�ڷ� ���ٰ� �ٽ� ����â Ű�� ó�� ���� ON���� �Ǿ��ִ°� ���� ���� true/false �Ǻ��Ͽ� �۾��� üũ���� ����          ȯ�漳��!!
 	    if(bgmonoff==true){	
  	    	bgmCheck.setChecked(MainActivity.bgmonoff);
  	    	bgmCheck.setText("ON");
  	    }
  	    else if(bgmonoff==false){
  	    	bgmCheck.setText("OFF");
  	    }
  	    
  	    if(efsonoff==true){
  	    	efsCheck.setChecked(efsonoff);
  	    	efsCheck.setText("ON");
  	    }
  	    else if(efsonoff==false){
  	    	efsCheck.setText("OFF");
  	    }
  	    
  	    //ȿ����
  	    soundpool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);  //(�ִ� ��Ʈ�� ����, ��Ʈ�� Ÿ��, ��Ʈ�� ǰ��)
		efscamera = soundpool.load(this, R.raw.efs_start, 1);
		efs_yogurt = soundpool.load(this, R.raw.efs_yogurt, 1);
		efs_sham = soundpool.load(this, R.raw.efs_sham, 1);
		efs_trash = soundpool.load(this, R.raw.efs_trash, 1);
         
 		 rankbt.setOnClickListener(new View.OnClickListener() {
 			@Override
 			public void onClick(View v) {
 				//Toast.makeText(getApplicationContext(), "��ŷ!", Toast.LENGTH_SHORT).show();
 				t1.setVisibility(View.VISIBLE);
 				page1.setVisibility(View.VISIBLE);
 				page2.setVisibility(View.GONE);
 				
 				if(yocheck){
 					viewing(1);
 					//yocheck = false;
 				}else if(cokecheck){
 					viewing(2);
 					//cokecheck = false;
 				}else if(shamcheck){
 					viewing(3);
 					//shamcheck = false;
 				}else if(milkcheck){
 					viewing(4);
 					//milkcheck = false;
 				}else if(allcheck){
 					viewing(5);
 					//allcheck = false;
 				}
 				
 			}
 		});
  
 		setbt.setOnClickListener(new View.OnClickListener() {
 			@Override
 			public void onClick(View v) {
 				viewing(6);
 				page2.setVisibility(View.VISIBLE);
 				page1.setVisibility(View.GONE);
 				t1.setVisibility(View.INVISIBLE);
 				yocheck=false;
 				cokecheck=false;
 				shamcheck=false;
 				milkcheck=false;
 				allcheck=false;
 				
 			}
 		});   
  		
         start_bt.setOnClickListener(new View.OnClickListener() {
 			public void onClick(View v) {
 				//�䱸��Ʈ ����
 				if(imageIndex==0){
 					if(efsonoff==true){  //ȿ���� on���� �Ǿ����� ���� ��Ĭ�Ҹ� ������
 						soundpool.play(efscamera, 1, 1, 0, 0, 1);  
 					}
 					Intent intent = new Intent(MainActivity.this, Stage1Activity.class);
 					startActivity(intent);
 				}
 				//�ݶ� ����
 				else if(imageIndex==1){
 					if(efsonoff==true){  //ȿ���� on���� �Ǿ����� ���� ��Ĭ�Ҹ� ������
 						soundpool.play(efscamera, 1, 1, 0, 0, 1);  
 					}
 					Intent intent = new Intent(MainActivity.this, Stage2Activity.class);
 					startActivity(intent);
 				}
 				//������ ����
 				else if(imageIndex==2){
 					if(efsonoff==true){  //ȿ���� on���� �Ǿ����� ���� ��Ĭ�Ҹ� ������
 						soundpool.play(efscamera, 1, 1, 0, 0, 1);  
 					}
 					Intent intent = new Intent(MainActivity.this, Stage3Activity.class);
 					startActivity(intent);
 				}
 				//���� ����
 				else if(imageIndex==3){
 					if(efsonoff==true){  //ȿ���� on���� �Ǿ����� ���� ��Ĭ�Ҹ� ������
 						soundpool.play(efscamera, 1, 1, 0, 0, 1);  
 					}
 					Intent intent = new Intent(MainActivity.this, Stage4Activity.class);
 					startActivity(intent);
 				}
 				//���� ����
 				else if(imageIndex==4){
 					if(efsonoff==true){  //ȿ���� on���� �Ǿ����� ���� ��Ĭ�Ҹ� ������
 						soundpool.play(efscamera, 1, 1, 0, 0, 1);  
 					}
 					Intent intent = new Intent(MainActivity.this, Stage5Activity.class);
 					startActivity(intent);
 				}
 			}
 		});
         
         exit_bt.setOnClickListener(new View.OnClickListener() { /////exit�� start���ڱ� �ٲ�
 			public void onClick(View v) {
 				createDialogBox();
 			}
 		});
         
         info_bt.setOnClickListener(new View.OnClickListener() { /////exit�� start���ڱ� �ٲ�
  			public void onClick(View v) {
  				Intent intent = new Intent(MainActivity.this, info.class);
					startActivity(intent);
  			}
  		});		
  	}  //oncreate
    
    
    public void viewing(int a){
    	if(a==1){
    		if(yocheck){
    			selectyo();
    			yocheck = false;
    			cokecheck=false;
    			shamcheck=false;
    			milkcheck=false;
    			allcheck=false;
    		}
    	}else if(a==2){
    		if(cokecheck){
    			selectcoke();
    			yocheck=false;
    			cokecheck = false;
    			shamcheck=false;
    			milkcheck=false;
    			allcheck=false;
    		}
    	}else if(a==3){
    		if(shamcheck){
    			selectsham();
    			yocheck=false;
    			cokecheck=false;
    			shamcheck=false;
    			milkcheck=false;
    			allcheck=false;
    		}
    	}else if(a==4){
    		if(milkcheck){
    			selectmilk();
    			yocheck=false;
    			cokecheck=false;
    			shamcheck=false;
    			milkcheck=false;
    			allcheck=false;
    		}
    	}else if(a==5){
    		if(allcheck){
    			selectwine();
    			yocheck=false;
    			cokecheck=false;
    			shamcheck=false;
    			milkcheck=false;
    			allcheck=false;
    		}	
    	}
    	else if(a==6){
    		System.out.printf("fucking");
    	}
    }
   
    
  	/**
  	 * �ش� �̸��� �����ͺ��̽��� ������ ����, ������ �����Ѵ�.
  	 * �ٸ� �ۿ��� �������� ���ϵ��� �Ѵ�.
  	 * 
  	 * @param name �����ͺ��̽��� �̸��� �Ķ���ͷ� �Ѵ�.
  	 */
  	void createDatabase(String name) {
  		try { 
              db = openOrCreateDatabase(name, MODE_PRIVATE, null); 
              databaseCreated = true; 
          } catch(Exception ex) { 
              ex.printStackTrace();
          } 
  	}

  	/**
  	 * �ش� �̸��� ���̺��� ������ ���̺� ����
  	 * �Ӽ� : id(pk), ��ŷ, �̸�, ���ھ�
  	 * @param yo �䱸��Ʈ
  	 * @param coke �ݶ�
  	 * @param sham ������
  	 * @param milk ����
  	 * @param wine ����
  	 */
  	void createTables(String yo, String coke, String sham, String milk, String wine) {
  		try {
  			db.execSQL("create table if not exists "+ yo + "("
  				+ " dbid integer primary key autoincrement, "
  				+ " dbrank integer, "
  				+ " dbname text, "
  				+ " dbscore integer);");
  			yoCreated = true;
  		} catch(Exception ex) {
  			Log.d("tableError", "���̺� �����ϴµ� ������");
  		}
  		
  		try {
  	  		db.execSQL("create table if not exists "+ coke + "("
  	  				+ " dbid integer primary key autoincrement, "
  	  				+ " dbrank integer, "
  	  				+ " dbname text, "
  	  				+ " dbscore integer);");
  	  		cokeCreated = true;
  	  	} catch(Exception ex) {
  	  		Log.d("tableError", "���̺� �����ϴµ� ������");
  	  	}
  		
  		try {
  	  		db.execSQL("create table if not exists "+ sham + "("
  	  				+ " dbid integer primary key autoincrement, "
  	  				+ " dbrank integer, "
  	  				+ " dbname text, "
  	  				+ " dbscore integer);");
  	  		shamCreated = true;
  	  	} catch(Exception ex) {
  	  		Log.d("tableError", "���̺� �����ϴµ� ������");
  	  	}
  		
  		try {
  	  		db.execSQL("create table if not exists "+ milk + "("
  	  				+ " dbid integer primary key autoincrement, "
  	  				+ " dbrank integer, "
  	  				+ " dbname text, "
  	  				+ " dbscore integer);");
  	  		milkCreated = true;
  	  	} catch(Exception ex) {
  	  		Log.d("tableError", "���̺� �����ϴµ� ������");
  	  	}
  		
  		try {
  	  		db.execSQL("create table if not exists "+ wine + "("
  	  				+ " dbid integer primary key autoincrement, "
  	  				+ " dbrank integer, "
  	  				+ " dbname text, "
  	  				+ " dbscore integer);");
  	  		wineCreated = true;  	  	} catch(Exception ex) {
  	  		Log.d("tableError", "���̺� �����ϴµ� ������");
  	  	}
  	}
  	
    /**
     * Cursor�� ���̺��� ����Ű�� �� ���̺��� ���ڵ� ���� Ȯ���Ͽ� 0�̸� �ƹ� �����͵� ���� ���̴ϱ� insert()�Լ��� ȣ���Ѵ�.
     */
    void insertOk(){
    	Cursor c1 = db.rawQuery("select * from sham", null);
		c1.moveToFirst();
		int aaa=c1.getCount();
		if(aaa==0) {
			insert("yo", "coke", "sham", "milk", "wine");
		}
    }
  	
  	void insert(String yo, String coke, String sham, String milk, String wine) {
  		db.execSQL("insert into "+yo+" (dbrank, dbname, dbscore) values (1, 'no score', 0);");
  		db.execSQL("insert into "+yo+" (dbrank, dbname, dbscore) values (2, 'no score', 0);");
  		db.execSQL("insert into "+yo+" (dbrank, dbname, dbscore) values (3, 'no score', 0);");
  		
  		db.execSQL("insert into "+coke+" (dbrank, dbname, dbscore) values (1, 'no score', 0);");
  		db.execSQL("insert into "+coke+" (dbrank, dbname, dbscore) values (2, 'no score', 0);");
  		db.execSQL("insert into "+coke+" (dbrank, dbname, dbscore) values (3, 'no score', 0);");
  		
  		db.execSQL("insert into "+sham+" (dbrank, dbname, dbscore) values (1, 'no score', 0);");
  		db.execSQL("insert into "+sham+" (dbrank, dbname, dbscore) values (2, 'no score', 0);");
  		db.execSQL("insert into "+sham+" (dbrank, dbname, dbscore) values (3, 'no score', 0);");
  		
  		db.execSQL("insert into "+milk+" (dbrank, dbname, dbscore) values (1, 'no score', 0);");
  		db.execSQL("insert into "+milk+" (dbrank, dbname, dbscore) values (2, 'no score', 0);");
  		db.execSQL("insert into "+milk+" (dbrank, dbname, dbscore) values (3, 'no score', 0);");
  		
  		db.execSQL("insert into "+wine+" (dbrank, dbname, dbscore) values (1, 'no score', 0);");
  		db.execSQL("insert into "+wine+" (dbrank, dbname, dbscore) values (2, 'no score', 0);");
  		db.execSQL("insert into "+wine+" (dbrank, dbname, dbscore) values (3, 'no score', 0);");
  	}
  		
  	/**
  	 * main�� ��ŷâ���� Textview�� t1�� �䱸��Ʈ�� ��ŷ�� �����ش�.
  	 */
  	void selectyo() {
  		Cursor c1 = db.rawQuery("select * from yo", null);
  		
  		c1.moveToFirst();
  		String one1 = Integer.toString(c1.getInt(1));
  		String two1 = c1.getString(2);
  		String three1 = Integer.toString(c1.getInt(3));
  		Log.d("yo", one1 +" "+two1+" "+three1);
  		
  		c1.moveToNext();
  		String one2 = Integer.toString(c1.getInt(1));
  		String two2 = c1.getString(2);
  		String three2 = Integer.toString(c1.getInt(3));
  		Log.d("yo", one2 +" "+two2+" "+three2);
  		
  		c1.moveToNext();
  		String one3 = Integer.toString(c1.getInt(1));
  		String two3 = c1.getString(2);
  		String three3 = Integer.toString(c1.getInt(3));
  		Log.d("yo", one3 +" "+two3+" "+three3);
  	
  		t1.setTextSize(15);
  		t1.setText("\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\trank\t\t\t\tname\t\t\t\t" +
  				"socre\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t" +
  				one1 +"\t\t\t\t"+two1+"\t\t\t\t"+three1+
  				"\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t"
  				+one2 +"\t\t\t\t"+two2+"\t\t\t"+three2+"\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t"
  				+one3 +"\t\t\t\t"+two3+"\t\t\t"+three3);
  		
  		c1.close();
  	}
  	
  	/**
  	 * main�� ��ŷâ���� Textview�� t1�� �ݶ��� ��ŷ�� �����ش�.
  	 */
  	void selectcoke() {
  		Cursor c1 = db.rawQuery("select * from coke", null);
  		
  		c1.moveToFirst();
  		String one1 = Integer.toString(c1.getInt(1));
  		String two1 = c1.getString(2);
  		String three1 = Integer.toString(c1.getInt(3));
  		Log.d("coke", one1 +" "+two1+" "+three1);
  		
  		c1.moveToNext();
  		String one2 = Integer.toString(c1.getInt(1));
  		String two2 = c1.getString(2);
  		String three2 = Integer.toString(c1.getInt(3));
  		Log.d("coke", one2 +" "+two2+" "+three2);
  		
  		c1.moveToNext();
  		String one3 = Integer.toString(c1.getInt(1));
  		String two3 = c1.getString(2);
  		String three3 = Integer.toString(c1.getInt(3));
  		Log.d("coke", one3 +" "+two3+" "+three3);
  		
  		t1.setTextSize(15);
  		t1.setText("\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\trank\t\t\t\tname\t\t\t\t" +
  				"socre\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t" +
  				one1 +"\t\t\t\t"+two1+"\t\t\t\t"+three1+
  				"\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t"
  				+one2 +"\t\t\t\t"+two2+"\t\t\t"+three2+"\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t"
  				+one3 +"\t\t\t\t"+two3+"\t\t\t"+three3);

  		c1.close();
  	}
  	
  	/**
  	 * main�� ��ŷâ���� Textview�� t1�� �������� ��ŷ�� �����ش�.
  	 */
  	void selectsham() {
  		Cursor c1 = db.rawQuery("select * from sham", null);
  		
  		c1.moveToFirst();
  		String one1 = Integer.toString(c1.getInt(1));
  		String two1 = c1.getString(2);
  		String three1 = Integer.toString(c1.getInt(3));
  		Log.d("sham", one1 +" "+two1+" "+three1);
  		
  		c1.moveToNext();
  		String one2 = Integer.toString(c1.getInt(1));
  		String two2 = c1.getString(2);
  		String three2 = Integer.toString(c1.getInt(3));
  		Log.d("sham", one2 +" "+two2+" "+three2);
  		
  		c1.moveToNext();
  		String one3 = Integer.toString(c1.getInt(1));
  		String two3 = c1.getString(2);
  		String three3 = Integer.toString(c1.getInt(3));
  		Log.d("sham", one3 +" "+two3+" "+three3);
  		
  		t1.setTextSize(15);
  		t1.setText("\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\trank\t\t\t\tname\t\t\t\t" +
  				"socre\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t" +
  				one1 +"\t\t\t\t"+two1+"\t\t\t\t"+three1+
  				"\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t"
  				+one2 +"\t\t\t\t"+two2+"\t\t\t"+three2+"\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t"
  				+one3 +"\t\t\t\t"+two3+"\t\t\t"+three3);

  		c1.close();
  	}
  	
  	/**
  	 * main�� ��ŷâ���� Textview�� t1�� ������ ��ŷ�� �����ش�.
  	 */
  	void selectmilk() {
  		Cursor c1 = db.rawQuery("select * from milk", null);
  		
  		c1.moveToFirst();
  		String one1 = Integer.toString(c1.getInt(1));
  		String two1 = c1.getString(2);
  		String three1 = Integer.toString(c1.getInt(3));
  		Log.d("milk", one1 +" "+two1+" "+three1);
  		
  		c1.moveToNext();
  		String one2 = Integer.toString(c1.getInt(1));
  		String two2 = c1.getString(2);
  		String three2 = Integer.toString(c1.getInt(3));
  		Log.d("milk", one2 +" "+two2+" "+three2);
  		
  		c1.moveToNext();
  		String one3 = Integer.toString(c1.getInt(1));
  		String two3 = c1.getString(2);
  		String three3 = Integer.toString(c1.getInt(3));
  		Log.d("milk", one3 +" "+two3+" "+three3);
  		
  		t1.setTextSize(15);
  		t1.setText("\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\trank\t\t\t\tname\t\t\t\t" +
  				"socre\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t" +
  				one1 +"\t\t\t\t"+two1+"\t\t\t\t"+three1+
  				"\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t"
  				+one2 +"\t\t\t\t"+two2+"\t\t\t"+three2+"\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t"
  				+one3 +"\t\t\t\t"+two3+"\t\t\t"+three3);

  		c1.close();
  	}
 
  	/**
  	 * main�� ��ŷâ���� Textview�� t1�� ������ ��ŷ�� �����ش�.
  	 */
  	void selectwine() {
  		Cursor c1 = db.rawQuery("select * from wine", null);
  		
  		c1.moveToFirst();
  		String one1 = Integer.toString(c1.getInt(1));
  		String two1 = c1.getString(2);
  		String three1 = Integer.toString(c1.getInt(3));
  		Log.d("wine", one1 +" "+two1+" "+three1);
  		
  		c1.moveToNext();
  		String one2 = Integer.toString(c1.getInt(1));
  		String two2 = c1.getString(2);
  		String three2 = Integer.toString(c1.getInt(3));
  		Log.d("wine", one2 +" "+two2+" "+three2);
  		
  		c1.moveToNext();
  		String one3 = Integer.toString(c1.getInt(1));
  		String two3 = c1.getString(2);
  		String three3 = Integer.toString(c1.getInt(3));
  		Log.d("wine", one3 +" "+two3+" "+three3);
  		
  		t1.setTextSize(15);
  		t1.setText("\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\trank\t\t\t\tname\t\t\t\t" +
  				"socre\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t" +
  				one1 +"\t\t\t\t"+two1+"\t\t\t\t"+three1+
  				"\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t"
  				+one2 +"\t\t\t\t"+two2+"\t\t\t"+three2+"\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t"
  				+one3 +"\t\t\t\t"+two3+"\t\t\t"+three3);

  		c1.close();
  	}
  	

  	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.main, menu);
       return true;
   
   }
  	
public void onClick(View v) {
		
		switch(v.getId()){
		case R.id.right:
			if(imageIndex < 4){ //0���� 1�� ��
				imageIndex ++;
			}
			changeImage();
			break;
			
		case R.id.left:
			if(imageIndex > 0){
				imageIndex --;
			}
			changeImage();
			break;
		
			
			//�ش� �������� ĳ���� ���� �� ���� �������� �Ѿ
		case R.id.c1:
			Intent ex_yo = new Intent(MainActivity.this, how_yo.class);
			startActivity(ex_yo);
			break;
		case R.id.c2:
			Intent ex_coke = new Intent(MainActivity.this, how_coke.class);
			startActivity(ex_coke);
			break;
		case R.id.c3:
			Intent ex_sham = new Intent(MainActivity.this, how_sham.class);
			startActivity(ex_sham);
			break;
		case R.id.c4:
			Intent ex_milk = new Intent(MainActivity.this, how_milk.class);
			startActivity(ex_milk);
			break;
		case R.id.c5:
			Intent ex_all = new Intent(MainActivity.this, how_ran.class);
			startActivity(ex_all);
			break;
			
			
		default:
			break;	
		}
	}
	
	
	private void changeImage(){
		if (imageIndex == 0) {
			c1.setVisibility(View.VISIBLE);
			c2.setVisibility(View.INVISIBLE);
			c3.setVisibility(View.INVISIBLE);
			c4.setVisibility(View.INVISIBLE);
			c5.setVisibility(View.INVISIBLE);
			yocheck = true;
			viewing(1);
			yocheck = false;
			left.setVisibility(View.INVISIBLE);
			right.setVisibility(View.VISIBLE);
		}else if (imageIndex == 1){
			c1.setVisibility(View.INVISIBLE);
			c2.setVisibility(View.VISIBLE);
			c3.setVisibility(View.INVISIBLE);
			c4.setVisibility(View.INVISIBLE);
			c5.setVisibility(View.INVISIBLE);
			cokecheck = true;
			viewing(2);
			left.setVisibility(View.VISIBLE);
			right.setVisibility(View.VISIBLE);
		}else if (imageIndex == 2){
			c1.setVisibility(View.INVISIBLE);
			c2.setVisibility(View.INVISIBLE);
			c3.setVisibility(View.VISIBLE);	
			c4.setVisibility(View.INVISIBLE);
			c5.setVisibility(View.INVISIBLE);
			shamcheck = true;
			viewing(3);
			left.setVisibility(View.VISIBLE);
			right.setVisibility(View.VISIBLE);
		}else if (imageIndex == 3){
			c1.setVisibility(View.INVISIBLE);
			c2.setVisibility(View.INVISIBLE);
			c3.setVisibility(View.INVISIBLE);	
			c4.setVisibility(View.VISIBLE);
			c5.setVisibility(View.INVISIBLE);
			milkcheck = true;
			viewing(4);
			left.setVisibility(View.VISIBLE);
			right.setVisibility(View.VISIBLE);
		}else if (imageIndex == 4){
			c1.setVisibility(View.INVISIBLE);
			c2.setVisibility(View.INVISIBLE);
			c3.setVisibility(View.INVISIBLE);	
			c4.setVisibility(View.INVISIBLE);
			c5.setVisibility(View.VISIBLE);
			
			allcheck = true;
			viewing(5);
			left.setVisibility(View.VISIBLE);
			right.setVisibility(View.INVISIBLE);
		}
		
	}
	

	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() ==0 )
		{
			AlertDialog dialog = createDialogBox();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public AlertDialog createDialogBox(){
		AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Exit").setMessage("�����Ͻðڽ��ϱ�?").setPositiveButton("��", new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialog, int which) {
				moveTaskToBack(true);
				android.os.Process.killProcess(android.os.Process.myPid());		
			}
		}).setNegativeButton("�ƴϿ�", null).show();
		
		return dialog;
	}
	
	public void bgmCheckboxClicked(View view){
		if(((CheckBox)view).isChecked()){
			bgmCheck.setText("ON");  //üũ�Ǹ� �۾� ON���� ����
			MainActivity.bgm = MediaPlayer.create(this, R.raw.main_bgm);  //���� �ٽ� �ε��ؼ� ���
			MainActivity.bgm.start();
			MainActivity.bgmonoff = true; 
		}else{
			bgmCheck.setText("OFF"); 
			MainActivity.bgm.stop();  //���� ����
			MainActivity.bgmonoff = false;
		}
	}
	
	public void efsCheckboxClicked(View view){
		if(((CheckBox)view).isChecked()){
			efsCheck.setText("ON");
			efsonoff = true;
		}else{
			efsCheck.setText("OFF");
			soundpool.stop(efscamera);  //ȿ���� ����
			efsonoff = false;
		}
	}


	public void onClick1(View v) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	public void onRestart(){ //��Ƽ��Ƽ�� �ٽý���
		super.onRestart();	
		MainActivity.bgm.start();
	}
	protected void onPause(){
		super.onPause();
		MainActivity.bgm.pause();
	}
	protected void onResume(){
		super.onResume();
		MainActivity.bgm.start();
	}
	public void onDestroy(){  //��Ƽ��Ƽ�� ����ȴ�
		super.onDestroy();
		
		if(null == bgm)
			return;
		bgm.stop();
	}
	*/
}//activity



