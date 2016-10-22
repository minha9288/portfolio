package com.greenlight.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.*;

/*
 * meeting.jsp페이지와 관련된 DB연결 내용
 */
public class MeetingMgr {
	private DBConnMgr pool;
	
	public MeetingMgr() {
		try {
			pool = DBConnMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//해당 meeting에 참여하고있는 사람들의 정보 받아오기
	public Vector enteredMeeting(int meeting_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = null;
		Vector attendList = new Vector();
		try {
			con = pool.getConnection();
			String sql = "select * from entered where meeting_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, meeting_id);
			rs = pstmt.executeQuery();
			while (rs.next()){
				attendList.add(rs.getString(2));	
				System.out.println(rs.getString(2));
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return attendList;
	}
	
	public boolean insertEntered(int meetingId, String userId){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = null;
		Vector attendList = new Vector();
		try {
			con = pool.getConnection();
			String sql = "insert into entered values('?', '?')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, meetingId);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return true;
	}
	
}
