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

	
	
	//#�����ͺ��̽� ����
	void createDatabase(String name) {
		try { 
            db = openOrCreateDatabase(name, MODE_PRIVATE, null); 
            databaseCreated = true; 
            println("database is created."); 
        } catch(Exception ex) { 
            ex.printStackTrace(); 
            println("database is not created."); 
        } 
	} // ddb �����ͺ��̽� ����
	
	
	//#�����ͺ��̽� ����
	boolean openDatabase() {
		dbHelper = new DatabaseHelper(this);
		db = dbHelper.getWritableDatabase();
		return true;
	} // ���� Ŭ������ ������ ȣ��
	
	
	//(3)���� ���
	void createTables() {
		db.execSQL("create table yo ("
				+ " dbid integer primary key autoincrement, "
				+ " dbrank integer, "
				+ " dbname text, "
				+ " dbscore integer);");
		yoCreated = true; // yo ���̺� ����
		
		db.execSQL("create table coke ("
				+ " dbid integer primary key autoincrement, "
				+ " dbrank integer, "
				+ " dbname text, "
				+ " dbscore integer);");
		cokeCreated = true; // coke ���̺� ����
		
		db.execSQL("create table sham ("
				+ " dbid integer primary key autoincrement, "
				+ " dbrank integer, "
				+ " dbname text, "
				+ " dbscore integer);");
		shamCreated = true; // sham ���̺� ����
		
		db.execSQL("create table milk ("
				+ " dbid integer primary key autoincrement, "
				+ " dbrank integer, "
				+ " dbname text, "
				+ " dbscore integer);");
		milkCreated = true; // milk ���̺� ����
		
		db.execSQL("create table wine ("
				+ " dbid integer primary key autoincrement, "
				+ " dbrank integer, "
				+ " dbname text, "
				+ " dbscore integer);");
		wineCreated = true; // wine ���̺� ����
	}
	
	//(3)���� ���
	void insert() {
		db.execSQL("insert into yo (dbrank, dbname, dbscore) valuse (1, ' ', 0);");
		db.execSQL("insert into yo (dbrank, dbname, dbscore) valuse (2, ' ', 0);");
		db.execSQL("insert into yo (dbrank, dbname, dbscore) valuse (3, ' ', 0);");
		// yo ���ڵ� �ʱ�ȭ
		
		db.execSQL("insert into coke (dbrank, dbname, dbscore) valuse (1, ' ', 0);");
		db.execSQL("insert into coke (dbrank, dbname, dbscore) valuse (2, ' ', 0);");
		db.execSQL("insert into coke (dbrank, dbname, dbscore) valuse (3, ' ', 0);");
		// coke ���ڵ� �ʱ�ȭ
		
		db.execSQL("insert into sham (dbrank, dbname, dbscore) valuse (1, ' ', 0);");
		db.execSQL("insert into sham (dbrank, dbname, dbscore) valuse (2, ' ', 0);");
		db.execSQL("insert into sham (dbrank, dbname, dbscore) valuse (3, ' ', 0);");
		// sham ���ڵ� �ʱ�ȭ
		
		db.execSQL("insert into milk (dbrank, dbname, dbscore) valuse (1, ' ', 0);");
		db.execSQL("insert into milk (dbrank, dbname, dbscore) valuse (2, ' ', 0);");
		db.execSQL("insert into milk (dbrank, dbname, dbscore) valuse (3, ' ', 0);");
		// milk ���ڵ� �ʱ�ȭ
		
		db.execSQL("insert into wine (dbrank, dbname, dbscore) valuse (1, ' ', 0);");
		db.execSQL("insert into wine (dbrank, dbname, dbscore) valuse (2, ' ', 0);");
		db.execSQL("insert into wine (dbrank, dbname, dbscore) valuse (3, ' ', 0);");
		// wine ���ڵ� �ʱ�ȭ
	}

	
	//#�����ͺ��̽� ���� Ŭ���� 
	class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, 1);
		} // ������ ȣ��
		
		
		//���̺� ����
		public void onCreate(SQLiteDatabase db) { 
			createTables();
			insert();  //���̺� �ʱ�ȭ
		} // �����ͺ��̽� ������ ó�� ������� �� ȣ��Ǵ� �޼ҵ� ����
		
		public void onOpen(SQLiteDatabase db) {
			
		}
		
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		}
		
	}
	
	
	/* ���� ������ ��ŷâ(������ ���׹����̹Ƿ� �ؿ� ��������)
	 * 3�� �����Ϳ� ���ؼ� �� ũ�� true ������ false��ȯ
	 * true�� ��ȯ�ϸ� 2���� ��
	 * 2���� ���ؼ� 2������ ������ 3���� ���
	 * 2���� ���ؼ� 2������ ũ�� 2���� 3���� ����ϰ� 1���� ��
	 * 1���� ���ؼ� 1������ ������ 2���� ���
	 * 1���� ���ؼ� 1���� ũ�� 1���� 2���� ����ϰ� 1���� ��� */
	/* 2�� ���� ���� ��� 3���� ��� */
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
	
	
	/* ���� ���� �� 3�� ������ ���Ͽ� ��ŷ�� ����� �� �ִ��� Ȯ���ϱ� */
	boolean selectyo3(int gamescore) {
		Cursor c1 = db.rawQuery("select * from yo", null);
		c1.moveToNext();
		c1.moveToNext();
		
		int score3 = c1.getInt(1);
		c1.close();
		
		if(gamescore > score3)
			return true; // 3�� ���� ������ ���� true�� ��ȯ�ؼ� �̸��� ����� �� �ִ� â�� �� �� �ְ� ��
		else
			return false; // 3�� ���� ������ �ȵ��� false�� ��ȯ�ؼ� �̸��� ��� ���ϰ� ��(3���� ������ ���Ƶ� ��������)
	} // �䱸��Ʈ
	
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
	} // �ݶ�
	
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
	} // ������
	
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
	} // ����
	
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
	} // ����

	
	/* 2���� ���ϱ� */
	/* 3������ Ŭ ���(��ŷ�� ����� �� �ִ� ���) 2�� ������ ���ϱ� */
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

	
	
	
	/* 2�� ���� Ŭ ��� 3���� 2������ ����ϰ� 1���� ���ϱ� */
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
	
	
	/* 2������ ũ�� 1������ ���� ��� 2���� ��� */
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
	
	/* 1������ Ŭ ��� 1���� 2����, �� ������ 1���� update */

	
	/* 1������ Ŭ ��� 1���� ��� */
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
	
	
	/* ���ο��� ��ŷâ */
	/* ������ ��ŷâ�� ���ڵ� ���̱� */
	void selectyo() {
		Cursor c1 = db.rawQuery("select * from yo", null);
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.close();
	} // ������ ��ŷâ���� yo ���̺��� ���ڵ� �ҷ�����
	
	void selectcoke() {
		Cursor c1 = db.rawQuery("select * from coke", null);
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.close();
	} // ������ ��ŷâ���� coke ���̺��� ���ڵ� �ҷ�����
	
	void selectsham() {
		Cursor c1 = db.rawQuery("select * from sham", null);
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.close();
	} // ������ ��ŷâ���� sham ���̺��� ���ڵ� �ҷ�����
	
	void selectmilk() {
		Cursor c1 = db.rawQuery("select * from milk", null);
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.close();
	} // ������ ��ŷâ���� milk ���̺��� ���ڵ� �ҷ�����
	
	void selectwine() {
		Cursor c1 = db.rawQuery("select * from wine", null);
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.moveToNext();
		println(c1.getInt(1) + "\t" + c1.getString(2) + "\t" + c1.getInt(3));
		
		c1.close();
	} // ������ ��ŷâ���� coke ���̺��� ���ڵ� �ҷ�����
	
	
	void println(String msg) {
		Log.d("Main_Activity", msg);
		status.append("\n" + msg);
	}
}
