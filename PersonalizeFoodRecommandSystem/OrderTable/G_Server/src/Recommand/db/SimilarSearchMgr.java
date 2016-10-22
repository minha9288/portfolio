package Recommand.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import db.DBConnectionMgr;
import bean.OtherUser;


public class SimilarSearchMgr {
	private DBConnectionMgr pool;
	
	public SimilarSearchMgr() {
		try {
			pool = DBConnectionMgr.getInstance();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//������ �ൿ���� �� ã��
	public Vector<OtherUser> getSimilarTypePeople(String user_type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<OtherUser> user_list = new Vector<OtherUser>();
		try {	
			System.out.println(user_type);
			con=pool.getConnection();
			sql="select * from user where user_type=? order by rand() limit 200";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_type);
			rs = pstmt.executeQuery();	
			while(rs.next()){
				OtherUser bean = new OtherUser();
				bean.setUser_id(rs.getString(1));
				bean.setUser_age(rs.getInt(2));
				bean.setUser_sex(rs.getString(3));
				bean.setUser_type(rs.getString(4));
				bean.setUser_nation(rs.getString(5));
				user_list.add(bean);
				System.out.print("id : " + bean.getUser_id() + " / ");
				System.out.print("age : " + bean.getUser_age() + " / ");
				System.out.print("sex : " + bean.getUser_sex() + " / ");
				System.out.print("type : " + bean.getUser_type() + " / ");
				System.out.print("nation : " + bean.getUser_nation() + " / ");
				System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return user_list;
	}
	
	
	//�����ൿ���� ������ �ӽ����̺� ����
	public void saveSimilarTypePeople(Vector<OtherUser> similar_user_list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {	
			con=pool.getConnection();
			/*
			sql="drop table temp_user_list";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();	

			sql="create table temp_user_list(user_id varchar(100), user_age int, user_sex varchar(10), user_type varchar(50), user_nation varchar(500))";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();	
			*/

			sql="delete from temp_user_list;";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();	
			
			
			for(int i=0; i<similar_user_list.size(); i++){

				sql="insert into temp_user_list values(?,?,?,?,?);";
				pstmt = con.prepareStatement(sql);
				
				OtherUser bean = similar_user_list.get(i);
				pstmt.setString(1, bean.getUser_id());
				pstmt.setInt(2, bean.getUser_age());
				pstmt.setString(3, bean.getUser_sex());
				pstmt.setString(4, bean.getUser_type());
				pstmt.setString(5, bean.getUser_nation());

				pstmt.executeUpdate();
			}			

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	
	//������ ���׷���Ÿ�� �� ã��
	public Vector<OtherUser> getSimilarStreoPeople(int c_user_age, String c_user_sex, String c_user_nation, int rate_age, int rate_sex, int rate_nation){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<OtherUser> user_list = new Vector<OtherUser>();
		int ageMin = c_user_age-3;
		int ageMax = c_user_age+3;
		
		try {	
			//���� ���� �� �޾ƿ���
			con=pool.getConnection();
			sql="select * from temp_user_list where user_age between ? and ? order by rand() limit ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ageMin);
			pstmt.setInt(2, ageMax);
			pstmt.setInt(3, rate_age);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				OtherUser bean = new OtherUser();
				bean.setUser_id(rs.getString(1));
				bean.setUser_age(rs.getInt(2));
				bean.setUser_sex(rs.getString(3));
				bean.setUser_type(rs.getString(4));
				bean.setUser_nation(rs.getString(5));
				user_list.add(bean);
			}
			
			//���� ��ġ �� �޾ƿ���
			sql="select * from temp_user_list where user_sex=? order by rand() limit ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_user_sex);
			pstmt.setInt(2, rate_sex);
			rs = pstmt.executeQuery();
			while(rs.next()){
				OtherUser bean = new OtherUser();
				bean.setUser_id(rs.getString(1));
				bean.setUser_age(rs.getInt(2));
				bean.setUser_sex(rs.getString(3));
				bean.setUser_type(rs.getString(4));
				bean.setUser_nation(rs.getString(5));
				user_list.add(bean);
			}
			
			//���� ��ġ �� �޾ƿ���
			sql="select * from temp_user_list where user_nation=? order by rand() limit ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_user_nation);
			pstmt.setInt(2, rate_nation);
			rs = pstmt.executeQuery();	
			while(rs.next()){
				OtherUser bean = new OtherUser();
				bean.setUser_id(rs.getString(1));
				bean.setUser_age(rs.getInt(2));
				bean.setUser_sex(rs.getString(3));
				bean.setUser_type(rs.getString(4));
				bean.setUser_nation(rs.getString(5));
				user_list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return user_list;
	}
	
	
	//���׷���Ÿ������ ���͸��� ������ �ӽ����̺� ����
	public void saveSimilarStreoPeople(Vector<OtherUser> similar_user_list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {	
			con=pool.getConnection();
			/*
			sql="drop table temp_similar_user";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();	

			sql="create table temp_similar_user(user_id varchar(100), user_age int, user_sex varchar(10), user_type varchar(50), user_nation varchar(500));";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();	
			*/
			
			sql="delete from temp_similar_user;";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();	
			
			
			for(int i=0; i<similar_user_list.size(); i++){
				OtherUser bean = similar_user_list.get(i);
				sql="insert into temp_similar_user values(?,?,?,?,?);";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getUser_id());
				pstmt.setInt(2, bean.getUser_age());
				pstmt.setString(3, bean.getUser_sex());
				pstmt.setString(4, bean.getUser_type());
				pstmt.setString(5, bean.getUser_nation());

				pstmt.executeUpdate();
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
}
