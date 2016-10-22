/**@author 김서희
 * DB에 연결해서 데이터 받아오기
 */

package test5;

import java.sql.*;

public class test5 {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public test5() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch(ClassNotFoundException e) {
			System.err.println("error : Driver not found");
			System.exit(0);
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarttable", "root", "1111");
			String sql = "select * from food where food_name='전'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				for(int i=1; i<12; i++) {
					if(rs.getString(i)!=null)
						System.out.println(rs.getString(i));
				}
			}
		} catch (SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		new test5();
	}
}



/**@author 김서희
 * 메인메뉴1에대한 음식 이미지와 재료 이미지가 보여짐
 */
/*
package test5;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class test5 extends JFrame {
	Container ct;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	String pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11;
	String main1;
	
	public test5() {
		ct = getContentPane();
		main1 = "'비빔밥'";
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch(ClassNotFoundException e) {
			System.err.println("error : Driver not found");
			System.exit(0);
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarttable", "root", "1111");
			String sql = "select * from food where name="+main1;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				for(int i=1; i<12; i++) {
					if(rs.getString(i)!=null) {
						switch(i) {
						case 1 : pic1 = rs.getString(i); break;
						case 2 : pic2 = rs.getString(i); break;
						case 3 : pic3 = rs.getString(i); break;
						case 4 : pic4 = rs.getString(i); break;
						case 5 : pic5 = rs.getString(i); break;
						case 6 : pic6 = rs.getString(i); break;
						case 7 : pic7 = rs.getString(i); break;
						case 8 : pic8 = rs.getString(i); break;
						case 9 : pic9 = rs.getString(i); break;
						case 10 : pic10 = rs.getString(i); break;
						case 11 : pic11 = rs.getString(i); break;
						}
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){
				
			}
		}
		
		picture p = new picture(pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11);
		ct.add(p);
		
		setTitle("Test5_1");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new test5();
	}
}
*/