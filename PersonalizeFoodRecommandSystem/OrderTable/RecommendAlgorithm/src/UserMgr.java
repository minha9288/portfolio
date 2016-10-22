import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;


public class UserMgr {
	private DBConnectionMgr pool;
	
	public UserMgr() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	//id존재유무 확인
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
	
	
	//새로운 User추가
	public boolean insertUser(UserBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {			
			con=pool.getConnection();
			sql="insert user (user_id, user_nation, user_sex, user_age, point_challenge, point_stable, point_healthy, point_price, user_type)" + "values(?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUser_id());
			pstmt.setString(2, bean.getUser_nation());
			pstmt.setInt(3, bean.getUser_sex());
			pstmt.setInt(4, bean.getUser_age());
			bean.setUser_type(rs.getString(5));
			if(pstmt.executeUpdate()==1) 
				flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	*/
	
	//User 정보 받아옴
	public UserBean getUser(String c_user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserBean bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from user where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_user_id);
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				bean = new UserBean();
				bean.setUser_id(rs.getString("user_id"));
				bean.setUser_nation(rs.getString("user_nation"));
				bean.setUser_sex(rs.getString("user_sex"));
				bean.setUser_age(rs.getInt("user_age"));
				bean.setUser_type(rs.getString("user_type"));
			}else
				System.out.println("여기임??");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}
	

	//가중치를 위해 User의 행동유형 점수를 받아옴
	public float[] getUserTypePoint(String c_user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		float[] point = {0,0,0,0};
		try {
			con = pool.getConnection();
			String sql = "select * from user where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_user_id);
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				point[0] = rs.getInt("point_challenge");
				point[1] = rs.getInt("point_stable");
				point[2] = rs.getInt("point_price");
				point[3] = rs.getInt("point_healthy");
			}else
				System.out.println("여기임??");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return point;
	}
	
	
	/*
	//식당 메뉴 종류 수
	public int kind_RestuarantMenu() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = pool.getConnection();
			String sql = "select count(*) from food";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				num = rs.getInt(1);
			}else
				System.out.println("여기임??");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return num;
	}
	
	//사용자가 먹은 토탈 주문 수
	public int num_userOrderMenu(String c_user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = pool.getConnection();
			String sql = "select count(*) from ordered where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_user_id);
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				num = rs.getInt(1);
			}else
				System.out.println("여기임??");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return num;
	}
	
	
	//사용자가 먹은 메뉴 가지 수
	public int kind_userOrderMenu(String c_user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = pool.getConnection();
			String sql = "select count(distinct (food_id)) from ordered where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_user_id);
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				num = rs.getInt(1);
			}else
				System.out.println("여기임??");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return num;
	}
	
	
	//식당 메뉴 가격 평균
	public int avg_RestuarantMenu() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = pool.getConnection();
			String sql = "select avg(food_price) from food";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				num = rs.getInt(1);
			}else
				System.out.println("여기임??");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return num;
	}
	
	//사용자가 먹은 메뉴 가격 평균
	public int avg_userOrderMenu(String c_user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = pool.getConnection();
			String sql = "select avg(food.food_price) from ordered join food"
					+ " where ordered.food_id=food.food_id and ordered.user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_user_id);
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				num = rs.getInt(1);
			}else
				System.out.println("여기임??");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return num;
	}
	
	//사용자가 먹은 건강메뉴 주문 수
	public int num_userOrderHealthyMenu(String c_user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = pool.getConnection();
			String sql = "select count(*) from ordered join food"
					+ " where ordered.food_id=food.food_id and food.food_healthy=true"
					+ " and ordered.user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_user_id);
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				num = rs.getInt(1);
			}else
				System.out.println("여기임??");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return num;
	}
	
	//식당 건강메뉴 갯수
	public int num_RestuarantHealthyMenu() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = pool.getConnection();
			String sql = "select count(*) from food where food_healthy=true";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				num = rs.getInt(1);
			}else
				System.out.println("여기임??");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return num;
	}
	*/
	
	//사용자 유형 점수 저장
	public void save_userTypePoint(String c_user_id, float[] point, String type_challenge_vs_stable, boolean type_isPrice, boolean type_isHealthy) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {			
			con=pool.getConnection();
			sql="update user_type_point set point_challenge=?, point_stable=?, point_price=?, point_healthy=?"
					+ " type_challenge_vs_stable=?, type_isPrice=?, type_isHealthy=?"
					+ " where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setFloat(1, point[0]);
			pstmt.setFloat(2, point[1]);
			pstmt.setFloat(3, point[2]);
			pstmt.setFloat(4, point[3]);
			pstmt.setString(5, type_challenge_vs_stable);
			pstmt.setBoolean(6, type_isPrice);
			pstmt.setBoolean(7, type_isHealthy);
			pstmt.setString(8, c_user_id);
			
			if(pstmt.executeUpdate()==1) 
				flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
}
