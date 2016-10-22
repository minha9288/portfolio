/*  ±è¼­Èñ     È¸ÀÇ¸ñ·Ï Ã³¸® */
package com.greenlight.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.greenlight.meetinglist.ConfListBean;
import com.greenlight.meetinglist.EnteredBean;

public class ConfListMgr {
	private DBConnMgr pool;
	
	public ConfListMgr() {
		try {
			pool = DBConnMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getTotalCount(String userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = pool.getConnection();
			sql = "select count(meeting_id) from entered where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return totalCount;
	}
	
	public boolean nameChecking(String meetingName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql="select meeting_name from meeting where meeting_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, meetingName);
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	public boolean exitMeeting(int meetingId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "delete from entered where meeting_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, meetingId);
			int count = pstmt.executeUpdate();
			if (count > 0)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	public boolean deleteMeeting(int meetingId, String userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "delete from entered where meeting_id=? and user_id=?";
			String sql2 = "delete from meeting where meeting_id=? and user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, meetingId);
			pstmt.setString(2, userId);
			int count = pstmt.executeUpdate();
			if (count > 0)
				pstmt = con.prepareStatement(sql2);
				pstmt.setInt(1, meetingId);
				pstmt.setString(2, userId);
				int count2 = pstmt.executeUpdate();
				if (count2 > 0)
					flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	public boolean deleteMeetingCheck(int meetingId, String userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select * from meeting where meeting_id=? and user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, meetingId);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	public String getName(int meetingId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String name = null;
		try {
			con = pool.getConnection();
			sql="select meeting_name from meeting where meeting_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, meetingId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				name=rs.getString("meeting_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return name;
	}
	
	public int getMeetingId(String userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int id = 0;
		try {
			con = pool.getConnection();
			sql="select meeting_id from entered where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id=rs.getInt("meeting_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return id;
	}
	
	public boolean insertEntered(int meetingId, String userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con=pool.getConnection();
			sql="insert entered (meeting_id, user_id)" + "values(?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, meetingId);
			pstmt.setString(2, userId);
			if(pstmt.executeUpdate()==1) {
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	public int getId(String meetingName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int id = 0;
		try {
			con = pool.getConnection();
			sql="select meeting_id from meeting where meeting_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, meetingName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id=rs.getInt("meeting_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return id;
	}
	
	public boolean insertMeeting(String meetingName, String userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con=pool.getConnection();
			sql="insert meeting (meeting_name, user_id)" + "values(?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, meetingName);
			pstmt.setString(2, userId);
			if(pstmt.executeUpdate()==1) {
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	public Vector<ConfListBean> getConfList(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql=null;
		Vector<ConfListBean> vlist = new Vector<ConfListBean>();
		try {
			con = pool.getConnection();
			sql = "select * from meeting where meeting_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ConfListBean cbean = new ConfListBean();
				cbean.setMeetingId(rs.getInt("meeting_id"));
				cbean.setMeetingName(rs.getString("meeting_Name"));
				vlist.add(cbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	public Vector<EnteredBean> getEnteredList(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql=null;
		Vector<EnteredBean> vlist = new Vector<EnteredBean>();
		try {
			con = pool.getConnection();
			sql = "select * from entered where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EnteredBean ebean = new EnteredBean();
				ebean.setMeetingId(rs.getInt("meeting_id"));
				ebean.setUserId(rs.getString("user_id"));
				vlist.add(ebean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
}
