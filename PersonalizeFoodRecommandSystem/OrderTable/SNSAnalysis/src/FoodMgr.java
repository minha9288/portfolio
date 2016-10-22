import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;


public class FoodMgr {
	private DBConnectionMgr pool;
	
	public FoodMgr() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean idChecking(String c_user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select user_id from user where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_user_id); 
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	
	@SuppressWarnings("null")
	public ArrayList<String> getFoodName() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<String> food_list = new ArrayList<String>();
		try {
			con = pool.getConnection();
			sql = "select * from food;";
			pstmt = con.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			while(rs.next()){
				food_list.add(rs.getString("food_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return food_list;
	}
	
	
	public ArrayList<String> getDic_Opinion() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<String> opinion_list = new ArrayList<String>();
		try {
			con = pool.getConnection();
			sql = "select * from dic_opinion;";
			pstmt = con.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			while(rs.next()){
				opinion_list.add(rs.getString("keyword"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return opinion_list;
	}

		
}
