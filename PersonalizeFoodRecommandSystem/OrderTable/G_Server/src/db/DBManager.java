package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 
 * @author hanyujin
 *
 */
public class DBManager {
	public static boolean DEBUG_MODE = true;
	private static DBManager sInstance;

	private DBManager() {
	}

	public static DBManager getInstance() {
		if (sInstance == null) {
			sInstance = new DBManager();
		}

		return sInstance;
	}

	/**
	 * MYsql DB
	 * 
	 * @return Connection
	 */
	public Connection dbConn() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://127.0.0.1:3306/greenlight?useUnicode=true&characterEncoding=EUC_KR",
							"root", "1111");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return conn;
	}

	/**
	 * Mysql DB
	 * 
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public void dbClose(Connection conn, Statement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Throwable e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}

	/**
	 * Mysql DB select
	 * 
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public void dbClose(Connection conn, Statement pstmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Throwable e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
}
