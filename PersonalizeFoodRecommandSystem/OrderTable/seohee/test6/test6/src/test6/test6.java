/**@author �輭��
 * button�� �̿��Ͽ� ������ main1�� main2�� �ٲ���
 * ���� ������ ��� ������ �ٲ�
 * ó���� ������ ������ main1
 */
/*
package test6;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class test6 extends JFrame {
	Container ct;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	String pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11; // pic1�� ����, pic2~11�� ���
	String main1, main2; // ��������1, ��������2
	JButton main1_b, main2_b; // ��������1�� �����ִ� ��ư, ��������2�� �����ִ� ��ư
	int isMain1; // main1�ΰ�? 0�̸� ��, 1�̸� �ƴϿ�(main2) 
	picture p;
	
	public test6() {
		ct = getContentPane();
		main1 = "'�ع���'";
		main2 = "'�����'";
		main1_b = new JButton("main1");
		main2_b = new JButton("main2");
		
		DBConn(main1);
		p = new picture(pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11);
		
		main1_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConn(main1);
				p = new picture(pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11);
				ct.add(p);
				ct.validate();
			}
		});
		
		main2_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConn(main2);
				p = new picture(pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11);
				ct.add(p);
				ct.validate();
			}
		});
		
		ct.repaint();
		ct.add(main1_b, BorderLayout.NORTH);
		ct.add(main2_b, BorderLayout.SOUTH);
		ct.add(p);
		
		setTitle("Test6_1");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void DBConn(String main) { // DB�� �����ϰ� �������� �����ϴ� �κ�
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch(ClassNotFoundException e) {
			System.err.println("error : Driver not found");
			System.exit(0);
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarttable", "root", "1111");
			String sql = "select * from food where name="+main;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				for(int i=1; i<12; i++) {
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
		} catch (SQLException e) {
			System.out.println("���� ����");
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
		System.out.println(pic1);
	}
	
	
	
	public static void main(String[] args) {
		new test6();
	}
}*/

/**@author �輭��
 * �̹����� �� ����1, 2 ��ư�� ������ ���İ� ��� �̹����� �ٲ��.
 */

package test6;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class test6 extends JFrame {
	Container ct;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	String pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11; // pic1�� ����, pic2~11�� ���
	String main1, main2; // ��������1, ��������2
	JButton main1_b, main2_b; // ��������1�� �����ִ� ��ư, ��������2�� �����ִ� ��ư
	int isMain1; // main1�ΰ�? 0�̸� ��, 1�̸� �ƴϿ�(main2) 
	picture p;
	ImageIcon main1_i, main2_i; // ��������1 ��ư�� �̹���, ��������2 ��ư�� �̹���
	
	public test6() {
		ct = getContentPane();
		main1 = "�ع���";
		main2 = "�����";
		
		main1_i = new ImageIcon("C:\\SmartTable\\test5\\"+main1+".jpg");
		main2_i = new ImageIcon("C:\\SmartTable\\test5\\"+main2+".jpg");
		
		main1_b = new JButton(main1_i);
		main2_b = new JButton(main2_i);
		
		DBConn(main1);
		p = new picture(pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11);
		
		main1_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConn(main1);
				p = new picture(pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11);
				ct.add(p);
				ct.validate();
			}
		});
		
		main2_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConn(main2);
				p = new picture(pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11);
				ct.add(p);
				ct.validate();
			}
		});
		
		ct.repaint();
		ct.add(main1_b, BorderLayout.NORTH);
		ct.add(main2_b, BorderLayout.SOUTH);
		ct.add(p);
		
		setTitle("Test6_2");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void DBConn(String main) { // DB�� �����ϰ� �������� �����ϴ� �κ�
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch(ClassNotFoundException e) {
			System.err.println("error : Driver not found");
			System.exit(0);
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarttable", "root", "1111");
			String sql = "select * from food where name='"+main+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				for(int i=1; i<12; i++) {
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
		} catch (SQLException e) {
			System.out.println("���� ����");
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
		System.out.println(pic1);
	}
	
	
	
	public static void main(String[] args) {
		new test6();
	}
}
