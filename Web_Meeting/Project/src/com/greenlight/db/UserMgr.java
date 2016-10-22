/*ȸ�� ����*/
package com.greenlight.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.greenlight.member.MemberBean;

public class UserMgr {
	private DBConnMgr pool;
	
	public UserMgr() {
		try {
			pool = DBConnMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "delete from user where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			int count = pstmt.executeUpdate();
			System.out.println(count);
			if (count > 0)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	public boolean updateMember(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con=pool.getConnection();
			String sql="update user set user_pw=?, user_email=?, user_phone=? where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUserPassword());
			pstmt.setString(2, bean.getUserEmail());
			pstmt.setString(3, bean.getUserPhone());
			pstmt.setString(4, bean.getUserId());
			int count = pstmt.executeUpdate();
			if(count > 0) flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	public boolean passwordCheck(String id, String pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select user_id from user where user_id=? and user_pw=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); // id : �Ű�����
			pstmt.setString(2, pass); // pass : �Ű�����
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	public MemberBean getMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from user where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new MemberBean();
				bean.setUserId(rs.getString("user_id"));
				bean.setUserName(rs.getString("user_name"));
				bean.setUserPassword(rs.getString("user_pw"));
				bean.setUserEmail(rs.getString("user_email"));
				bean.setUserPhone(rs.getString("user_phone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}
	
	public boolean insertUser(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con=pool.getConnection();
			sql="insert user (user_id, user_name, user_pw, user_email, user_phone)" + "values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUserId());
			pstmt.setString(2, bean.getUserName());
			pstmt.setString(3, bean.getUserPassword());
			pstmt.setString(4, bean.getUserEmail());
			pstmt.setString(5, bean.getUserPhone());
			if(pstmt.executeUpdate()==1) flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	public boolean idChecking(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select user_id from user where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); // id : �Ű�����
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	public boolean loginMember(String id, String pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select user_id from user where user_id=? and user_pw=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); // id : �Ű�����
			pstmt.setString(2, pass); // pass : �Ű�����
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
		
	}
	
	public boolean checkNamePhone(String name, String phone) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select user_name, user_phone from user where user_name=? and user_phone=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name); // id : �Ű�����
			pstmt.setString(2, phone); // pass : �Ű�����
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	//민하 추가 - user 이름 알아내기
	public String returnName(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String name="";
		try{
			con = pool.getConnection();
			sql = "select user_name from user where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();	
			if(rs.next())
				name = rs.getString("user_name");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return name;
	}
	
	//민하 추가 - 사용자 id와 이름, email 가져오기
	public Vector<MemberBean> inviteRead(String search_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<MemberBean> vlist = new Vector<MemberBean>();
		try {
			con = pool.getConnection();
			sql = "select * from user where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberBean bean = new MemberBean();
				bean.setUserId(rs.getString(1));
				bean.setUserName(rs.getString(2));
				bean.setUserEmail(rs.getString(4));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
}
