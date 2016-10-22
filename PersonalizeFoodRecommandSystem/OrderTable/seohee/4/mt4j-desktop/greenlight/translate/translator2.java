package translate;

import java.sql.*;

public class translator2 {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	String text_after_translate;
	
	public String translating2(int food_id, String nation, String what) {
		if(nation.equals("uk"))
			nation="us";
		
		if(what.equals("name"))
			db(food_id, nation);
		else
			db2(food_id, nation);
		
		return text_after_translate;
	}
	
	public void db(int food_id, String nation) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch(ClassNotFoundException e) {
			System.err.println("error : Driver not found");
			System.exit(0);
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			String sql = "select food_name from food_translator where food_id="+food_id+" && nation='"+nation+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				text_after_translate = rs.getString(1);
			}
		} catch(SQLException e) {
			System.out.println("error");
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
			} catch(Exception e){ }
		}
	}
	
	public void db2(int food_id, String nation) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch(ClassNotFoundException e) {
			System.err.println("error : Driver not found");
			System.exit(0);
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			String sql = "select food_explain from food_translator where food_id="+food_id+" && nation='"+nation+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				text_after_translate = rs.getString(1);
			}
		} catch(SQLException e) {
			System.out.println("error2");
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
			} catch(Exception e){ }
		}
	}
}