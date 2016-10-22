package com.khh.ddayoddayo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

public class Data extends Activity {
	
	SQLiteDatabase db;
	boolean databaseCreated = false;
	boolean yoCreated = false;
	boolean cokeCreated = false;
	boolean shamCreated = false;
	boolean milkCreated = false;
	boolean wineCreated = false;
	
	DatabaseHelper dbHelper;
	String DATABASE_NAME="ddb";
	TextView status;

	
	
	//#데이터베이스 생성
	void createDatabase(String name) {
		try { 
            db = openOrCreateDatabase(name, MODE_PRIVATE, null); 
            databaseCreated = true; 
            println("database is created."); 
        } catch(Exception ex) { 
            ex.printStackTrace(); 
            println("database is not created."); 
        } 
	} // ddb 데이터베이스 생성
	
	
	//#데이터베이스 열기
	boolean openDatabase() {
		dbHelper = new DatabaseHelper(this);
		db = dbHelper.getWritableDatabase();
		return true;
	} // 상위 클래스의 생성자 호출
	
	
	//(3)에서 사용
	void createTables() {
		db.execSQL("create table yo ("
				+ " dbid integer primary key autoincrement, "
				+ " dbrank integer, "
				+ " dbname text, "
				+ " dbscore integer);");
		yoCreated = true; // yo 테이블 생성
		
		db.execSQL("create table coke ("
				+ " dbid integer primary key autoincrement, "
				+ " dbrank integer, "
				+ " dbname text, "
				+ " dbscore integer);");
		cokeCreated = true; // coke 테이블 생성
		
		db.execSQL("create table sham ("
				+ " dbid integer primary key autoincrement, "
				+ " dbrank integer, "
				+ " dbname text, "
				+ " dbscore integer);");
		shamCreated = true; // sham 테이블 생성
		
		db.execSQL("create table milk ("
				+ " dbid integer primary key autoincrement, "
				+ " dbrank integer, "
				+ " dbname text, "
				+ " dbscore integer);");
		milkCreated = true; // milk 테이블 생성
		
		db.execSQL("create table wine ("
				+ " dbid integer primary key autoincrement, "
				+ " dbrank integer, "
				+ " dbname text, "
				+ " dbscore integer);");
		wineCreated = true; // wine 테이블 생성
	}
	
	//(3)에서 사용
	void insert() {
		db.execSQL("insert into yo (dbrank, dbname, dbscore) valuse (1, ' ', 0);");
		db.execSQL("insert into yo (dbrank, dbname, dbscore) valuse (2, ' ', 0);");
		db.execSQL("insert into yo (dbrank, dbname, dbscore) valuse (3, ' ', 0);");
		// yo 레코드 초기화
		
		db.execSQL("insert into coke (dbrank, dbname, dbscore) valuse (1, ' ', 0);");
		db.execSQL("insert into coke (dbrank, dbname, dbscore) valuse (2, ' ', 0);");
		db.execSQL("insert into coke (dbrank, dbname, dbscore) valuse (3, ' ', 0);");
		// coke 레코드 초기화
		
		db.execSQL("insert into sham (dbrank, dbname, dbscore) valuse (1, ' ', 0);");
		db.execSQL("insert into sham (dbrank, dbname, dbscore) valuse (2, ' ', 0);");
		db.execSQL("insert into sham (dbrank, dbname, dbscore) valuse (3, ' ', 0);");
		// sham 레코드 초기화
		
		db.execSQL("insert into milk (dbrank, dbname, dbscore) valuse (1, ' ', 0);");
		db.execSQL("insert into milk (dbrank, dbname, dbscore) valuse (2, ' ', 0);");
		db.execSQL("insert into milk (dbrank, dbname, dbscore) valuse (3, ' ', 0);");
		// milk 레코드 초기화
		
		db.execSQL("insert into wine (dbrank, dbname, dbscore) valuse (1, ' ', 0);");
		db.execSQL("insert into wine (dbrank, dbname, dbscore) valuse (2, ' ', 0);");
		db.execSQL("insert into wine (dbrank, dbname, dbscore) valuse (3, ' ', 0);");
		// wine 레코드 초기화
	}

	
	//#데이터베이스 상위 클래스 
	class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, 1);
		} // 생성자 호출
		
		
		//테이블 생성
		public void onCreate(SQLiteDatabase db) { 
			createTables();
			insert();  //테이블 초기화
		} // 데이터베이스 파일이 처음 만들어질 때 호출되는 메소드 정의
		
		public void onOpen(SQLiteDatabase db) {
			
		}
		
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		}
		
	}
	
	
	/* 게임 끝나고 랭킹창(순서는 뒤죽박죽이므로 밑에 설명참고)
	 * 3위 데이터와 비교해서 더 크면 true 작으면 false반환
	 * true를 반환하면 2위와 비교
	 * 2위와 비교해서 2위보다 작으면 3위에 등록
	 * 2위와 비교해서 2위보다 크면 2위를 3위에 등록하고 1위와 비교
	 * 1위와 비교해서 1위보다 작으면 2위에 등록
	 * 1위와 비교해서 1보다 크면 1위를 2위에 등록하고 1위에 등록 */
	/* 2위 보다 작을 경우 3위에 등록 */
	void updateyo3(String gamename, int gamescore) {
		db.execSQL("update yo set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=3);");
	}
	
	void updatecoke3(String gamename, int gamescore) {
		db.execSQL("update coke set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=3);");
	}
	
	void updatesham3(String gamename, int gamescore) {
		db.execSQL("update sham set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=3);");
	}
	
	void updatemilk3(String gamename, int gamescore) {
		db.execSQL("update milk set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=3);");
	}
	
	void updatewine3(String gamename, int gamescore) {
		db.execSQL("update wine set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=3);");
	}
	
	
	/* 게임 종료 후 3위 점수와 비교하여 랭킹에 등록할 수 있는지 확인하기 */
	boolean selectyo3(int gamescore) {
		Cursor c1 = db.rawQuery("select * from yo", null);
		c1.moveToNext();
		c1.moveToNext();
		
		int score3 = c1.getInt(1);
		c1.close();
		
		if(gamescore > score3)
			return true; // 3위 내에 점수가 들어가면 true를 반환해서 이름을 등록할 수 있는 창을 뜰 수 있게 함
		else
			return false; // 3위 내에 점수가 안들어가면 false를 반환해서 이름을 등록 못하게 함(3위와 점수가 같아도 마찬가지)
	} // 요구르트
	
	boolean selectcoke3(int gamescore) {
		Cursor c1 = db.rawQuery("select * from coke", null);
		c1.moveToNext();
		c1.moveToNext();
		
		int score3 = c1.getInt(1);
		c1.close();
		
		if(gamescore > score3)
			return true;
		else
			return false;
	} // 콜라
	
	boolean selectsham3(int gamescore) {
		Cursor c1 = db.rawQuery("select * from sham", null);
		c1.moveToNext();
		c1.moveToNext();
		
		int score3 = c1.getInt(1);
		c1.close();
		
		if(gamescore > score3)
			return true;
		else
			return false;
	} // 샴페인
	
	boolean selectmilk3(int gamescore) {
		Cursor c1 = db.rawQuery("select * from milk", null);
		c1.moveToNext();
		c1.moveToNext();
		
		int score3 = c1.getInt(1);
		c1.close();
		
		if(gamescore > score3)
			return true;
		else
			return false;
	} // 우유
	
	boolean selectwine3(int gamescore) {
		Cursor c1 = db.rawQuery("select * from wine", null);
		c1.moveToNext();
		c1.moveToNext();
		
		int score3 = c1.getInt(1);
		c1.close();
		
		if(gamescore > score3)
			return true;
		else
			return false;
	} // 와인

	
	/* 2위와 비교하기 */
	/* 3위보다 클 경우(랭킹에 등록할 수 있는 경우) 2위 점수와 비교하기 */
	void selectyo2(String gamename, int gamescore) {
		Cursor c1 = db.rawQuery("select * from yo", null);
		c1.moveToNext();
		
		int score2 = c1.getInt(1);
		c1.close();
		
		if(gamescore >= score2)
			selectyo1(gamename, gamescore);
		else
			updateyo3(gamename, gamescore);
	}
	
	void selectcoke2(String gamename, int gamescore) {
		Cursor c1 = db.rawQuery("select * from coke", null);
		c1.moveToNext();
		
		int score2 = c1.getInt(1);
		c1.close();
		
		if(gamescore >= score2)
			selectcoke1(gamename, gamescore);
		else
			updatecoke3(gamename, gamescore);
	}

	
	void selectsham2(String gamename, int gamescore) {
		Cursor c1 = db.rawQuery("select * from sham", null);
		c1.moveToNext();
		
		int score2 = c1.getInt(1);
		c1.close();
		
		if(gamescore >= score2)
			selectsham1(gamename, gamescore);
		else
			updatesham3(gamename, gamescore);
	}
	
	
	void selectmilk2(String gamename, int gamescore) {
		Cursor c1 = db.rawQuery("select * from milk", null);
		c1.moveToNext();
		
		int score2 = c1.getInt(1);
		c1.close();
		
		if(gamescore >= score2)
			selectmilk1(gamename, gamescore);
		else
			updatemilk3(gamename, gamescore);
	}
	
	
	void selectwine2(String gamename, int gamescore) {
		Cursor c1 = db.rawQuery("select * from wine", null);
		c1.moveToNext();
		
		int score2 = c1.getInt(1);
		c1.close();
		
		if(gamescore >= score2)
			selectwine1(gamename, gamescore);
		else
			updatewine3(gamename, gamescore);
	}

	
	
	
	/* 2위 보다 클 경우 3위에 2위점수 등록하고 1위와 비교하기 */
	void selectyo1(String gamename, int gamescore) {
		db.execSQL("update yo set dbname=(select dbname from yo where dbrank=2), dbscore=(select dbscore from yo where dbrank=2) where dbrank=3);");
		Cursor c1 = db.rawQuery("select * from yo", null);
		
		int score1 = c1.getInt(1);
		c1.close();
		
		if(gamescore >= score1)
			updateyo1(gamename, gamescore);
		else
			updateyo2(gamename, gamescore);
	}
	
	void selectcoke1(String gamename, int gamescore) {
		db.execSQL("update coke set dbname=(select dbname from coke where dbrank=2), dbscore=(select dbscore from coke where dbrank=2) where dbrank=3);");
		Cursor c1 = db.rawQuery("select * from coke", null);
		
		int score1 = c1.getInt(1);
		c1.close();
		
		if(gamescore >= score1)
			updatecoke1(gamename, gamescore);
		else
			updatecoke2(gamename, gamescore);
	}
	
	void selectsham1(String gamename, int gamescore) {
		db.execSQL("update sham set dbname=(select dbname from sham where dbrank=2), dbscore=(select dbscore from sham where dbrank=2) where dbrank=3);");
		Cursor c1 = db.rawQuery("select * from sham", null);
		
		int score1 = c1.getInt(1);
		c1.close();
		
		if(gamescore >= score1)
			updatesham1(gamename, gamescore);
		else
			updatesham2(gamename, gamescore);
	}
	
	void selectmilk1(String gamename, int gamescore) {
		db.execSQL("update milk set dbname=(select dbname from milk where dbrank=2), dbscore=(select dbscore from milk where dbrank=2) where dbrank=3);");
		Cursor c1 = db.rawQuery("select dbscore from milk", null);
		
		int score1 = c1.getInt(1);
		c1.close();
		
		if(gamescore >= score1)
			updatemilk1(gamename, gamescore);
		else
			updatemilk2(gamename, gamescore);
	}
	
	void selectwine1(String gamename, int gamescore) {
		db.execSQL("update wine set dbname=(select dbname from wine where dbrank=2), dbscore=(select dbscore from wine where dbrank=2) where dbrank=3);");
		Cursor c1 = db.rawQuery("select * from wine", null);
		
		int score1 = c1.getInt(1);
		c1.close();
		
		if(gamescore >= score1)
			updatewine1(gamename, gamescore);
		else
			updatewine2(gamename, gamescore);
	}
	
	
	/* 2위보다 크고 1위보다 작을 경우 2위에 등록 */
	void updateyo2(String gamename, int gamescore) {
		db.execSQL("update yo set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=2);");
	}
	
	
	void updatecoke2(String gamename, int gamescore) {
		db.execSQL("update coke set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=2);");
	}
	

	void updatesham2(String gamename, int gamescore) {
		db.execSQL("update sham set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=2);");
	}
	

	void updatemilk2(String gamename, int gamescore) {
		db.execSQL("update milk set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=2);");
	}
	

	void updatewine2(String gamename, int gamescore) {
		db.execSQL("update wine set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=2);");
	}	
	
	/* 1위보다 클 경우 1위는 2위로, 새 점수는 1위로 update */

	
	/* 1위보다 클 경우 1위에 등록 */
	void updateyo1(String gamename, int gamescore) {
		db.execSQL("update yo set dbname=(select dbname from yo where dbrank=1), dbscore=(select dbscore from yo where dbrank=1) where dbrank=2);");
		db.execSQL("update yo set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=1);");
	}
	
	void updatecoke1(String gamename, int gamescore) {
		db.execSQL("update coke set dbname=(select dbname from coke where dbrank=1), dbscore=(select dbscore from coke where dbrank=1) where dbrank=2);");
		db.execSQL("update coke set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=1);");
	}
	
	void updatesham1(String gamename, int gamescore) {
		db.execSQL("update sham set dbname=(select dbname from sham where dbrank=1), dbscore=(select dbscore from sham where dbrank=1) where dbrank=2);");
		db.execSQL("update sham set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=1);");
	}
	
	void updatemilk1(String gamename, int gamescore) {
		db.execSQL("update milk set dbname=(select dbname from milk where dbrank=1), dbscore=(select dbscore from milk where dbrank=1) where dbrank=2);");
		db.execSQL("update milk set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=1);");
	}
	
	void updatewine1(String gamename, int gamescore) {
		db.execSQL("update wine set dbname=(select dbname from wine where dbrank=1), dbscore=(select dbscore from wine where dbrank=1) where dbrank=2);");
		db.execSQL("update wine set dbname=" + gamename + ", dbscore=" + gamescore + " where dbrank=1);");
	}
	
	
	/* 메인에서 랭킹창 */
	/* 메인의 랭킹창에 레코드 보이기 */
	void selectyo() {
		Cursor c1 = db.rawQuery("select * from yo", null);
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.close();
	} // 메인의 랭킹창에서 yo 테이블의 레코드 불러오기
	
	void selectcoke() {
		Cursor c1 = db.rawQuery("select * from coke", null);
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.close();
	} // 메인의 랭킹창에서 coke 테이블의 레코드 불러오기
	
	void selectsham() {
		Cursor c1 = db.rawQuery("select * from sham", null);
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.close();
	} // 메인의 랭킹창에서 sham 테이블의 레코드 불러오기
	
	void selectmilk() {
		Cursor c1 = db.rawQuery("select * from milk", null);
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.close();
	} // 메인의 랭킹창에서 milk 테이블의 레코드 불러오기
	
	void selectwine() {
		Cursor c1 = db.rawQuery("select * from wine", null);
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.close();
	} // 메인의 랭킹창에서 coke 테이블의 레코드 불러오기
	
	
	void println(String msg) {
		Log.d("Main_Activity", msg);
		status.append("\n" + msg);
	}
}
