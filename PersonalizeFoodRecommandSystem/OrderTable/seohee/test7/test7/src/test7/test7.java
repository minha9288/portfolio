/**@author 김서희
 * 재료를 선택하면 대체재료가 보여지고
 * 원하는 대체재료를 선택하면 기존 재료가 대체재료로 바뀌어져 보여지고
 * 음식 이미지도 해당 재료가 들어간 사진으로 바뀜
 */

package test7;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class test7 extends JFrame {
	Container ct;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	String Pic, pic1, pic2; // Pic은 음식, pic1은 재료  이미지 이름
	
	Image img1; // 음식사진
	
	JButton bt1, bt2; // 기본 재료 버튼
	ImageIcon ii1, ii2; // 기본 재료 버튼에 사용할 이미지
	
	JButton rbt1, rbt2; // 대체 재료 버튼
	ImageIcon rii1, rii2; // 대체 재료 버튼에 사용할 이미지
	
	JPanel pp; // 이게 없으면 버튼 하나가 전체를 다 먹어버림...
	
	public test7() {
		ct = getContentPane();
		
		DBConn("비빔밥");
		
		img1 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+Pic+".jpg");
		
		ii1 = new ImageIcon("C:\\SmartTable\\test5\\"+pic1+".jpg");
		ii2 = new ImageIcon("C:\\SmartTable\\test5\\"+pic2+".jpg");
		bt1 = new JButton(ii1);
		bt1.setSize(50, 50);
		bt1.setLocation(0, 110);
		
		bt2 = new JButton(ii2);
		bt2.setSize(50, 50);
		bt2.setLocation(0, 110);
		
		pp = new JPanel();
		
		// 기존 재료를 눌러 대체재료로 바꾸기
		bt1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				rii1 = new ImageIcon("C:\\SmartTable\\test5\\"+pic1+".jpg");
				rbt1 = new JButton(rii1);
				rbt1.setSize(50, 50);
				rbt1.setLocation(0, 200);
				
				// 대체재료로 기존의 재료와 같은 재료를 선택한 경우
				rbt1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						img1 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+Pic+".jpg"); // 재료가 바뀐 음식 사지으로 바꿈

						// 대체재료 리스트 삭제
						ct.remove(rbt1);
						ct.remove(rbt2);
						
						revalidate();
						repaint(); 
					}
				});
				
				rii2 = new ImageIcon("C:\\SmartTable\\test5\\"+pic2+".jpg");
				rbt2 = new JButton(rii2);
				rbt2.setSize(50, 50);
				rbt2.setLocation(60, 200);
				
				// 대체재료로 기존의 재료와 다른 재료를 선택한 경우
				rbt2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						img1 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\전2.jpg"); // 재료가 바뀐 음식 사지으로 바꿈
						
						// 원래 있던 재료와 대체재료 리스트 삭제
						ct.remove(bt1);
						ct.remove(rbt1);
						ct.remove(rbt2);
						
						// 대체재료로 바뀐 이미지를 추가
						ct.add(bt2);
						ct.add(pp);
						
						revalidate();
						repaint();
					}
				});
				
				// 대체재료 이미지 추가
				ct.add(rbt1);
				ct.add(rbt2);
				ct.add(pp);
				
				revalidate();
				repaint();
			}
		});
		
		bt2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				rii1 = new ImageIcon("C:\\SmartTable\\test5\\"+pic1+".jpg");
				rbt1 = new JButton(rii1);
				rbt1.setSize(50, 50);
				rbt1.setLocation(0, 200);
				// 대체재료로 기존의 재료와 같은 재료를 선택한 경우
				rbt1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						img1 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+Pic+".jpg"); // 재료가 바뀐 음식 사지으로 바꿈

						ct.remove(bt2);
						ct.remove(rbt1);
						ct.remove(rbt2);
						
						ct.add(bt1);
						ct.add(pp);
						
						revalidate();
						repaint(); 
					}
				});
				
				rii2 = new ImageIcon("C:\\SmartTable\\test5\\"+pic2+".jpg");
				rbt2 = new JButton(rii2);
				rbt2.setSize(50, 50);
				rbt2.setLocation(60, 200);
				rbt2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						img1 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\전2.jpg"); // 재료가 바뀐 음식 사지으로 바꿈
						
						ct.remove(rbt1);
						ct.remove(rbt2);
						
						revalidate();
						repaint();
					}
				});
				
				ct.add(rbt1);
				ct.add(rbt2);
				ct.add(pp);
				
				revalidate();
				repaint();
			}
		});

		ct.add(bt1);
		ct.add(pp);
		
		revalidate();
		repaint();
		
		setTitle("Test7_1");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void paint(Graphics g) { // 음식 이미지 출력
		super.paint(g);
		g.drawImage(img1, 0, 0, 100, 100, this);
	}
	
	public void DBConn(String main1) { // DB에 연결하고 쿼리문을 실행하는 부분
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch(ClassNotFoundException e) {
			System.err.println("error : Driver not found");
			System.exit(0);
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarttable", "root", "1111");
			String sql=null;
			int how_many=0;
			if(main1=="전") {
				sql = "select * from jeon where food_name='"+main1+"'";
				how_many=7;
			} else if(main1=="비빔밥") {
				sql = "select * from bibim where food_name='"+main1+"'";
				how_many=8;
			} else if(main1=="해물찜") {
				sql = "select * from hae where food_name='"+main1+"'";
				how_many=12;
			}
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				for(int i=1; i<=how_many; i++) {
					switch(i) {
						case 1 : Pic = rs.getString(i); break;
						case 2 : pic1 = rs.getString(i); break;
						case 3 : pic2 = rs.getString(i); break;
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
			} catch(Exception e){ }
		}
	}
	
	public static void main(String[] args) {
		new test7();
	}
}