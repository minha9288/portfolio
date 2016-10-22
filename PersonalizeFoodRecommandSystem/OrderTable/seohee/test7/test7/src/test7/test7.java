/**@author �輭��
 * ��Ḧ �����ϸ� ��ü��ᰡ ��������
 * ���ϴ� ��ü��Ḧ �����ϸ� ���� ��ᰡ ��ü���� �ٲ���� ��������
 * ���� �̹����� �ش� ��ᰡ �� �������� �ٲ�
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
	
	String Pic, pic1, pic2; // Pic�� ����, pic1�� ���  �̹��� �̸�
	
	Image img1; // ���Ļ���
	
	JButton bt1, bt2; // �⺻ ��� ��ư
	ImageIcon ii1, ii2; // �⺻ ��� ��ư�� ����� �̹���
	
	JButton rbt1, rbt2; // ��ü ��� ��ư
	ImageIcon rii1, rii2; // ��ü ��� ��ư�� ����� �̹���
	
	JPanel pp; // �̰� ������ ��ư �ϳ��� ��ü�� �� �Ծ����...
	
	public test7() {
		ct = getContentPane();
		
		DBConn("�����");
		
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
		
		// ���� ��Ḧ ���� ��ü���� �ٲٱ�
		bt1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				rii1 = new ImageIcon("C:\\SmartTable\\test5\\"+pic1+".jpg");
				rbt1 = new JButton(rii1);
				rbt1.setSize(50, 50);
				rbt1.setLocation(0, 200);
				
				// ��ü���� ������ ���� ���� ��Ḧ ������ ���
				rbt1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						img1 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+Pic+".jpg"); // ��ᰡ �ٲ� ���� �������� �ٲ�

						// ��ü��� ����Ʈ ����
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
				
				// ��ü���� ������ ���� �ٸ� ��Ḧ ������ ���
				rbt2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						img1 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\��2.jpg"); // ��ᰡ �ٲ� ���� �������� �ٲ�
						
						// ���� �ִ� ���� ��ü��� ����Ʈ ����
						ct.remove(bt1);
						ct.remove(rbt1);
						ct.remove(rbt2);
						
						// ��ü���� �ٲ� �̹����� �߰�
						ct.add(bt2);
						ct.add(pp);
						
						revalidate();
						repaint();
					}
				});
				
				// ��ü��� �̹��� �߰�
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
				// ��ü���� ������ ���� ���� ��Ḧ ������ ���
				rbt1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						img1 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+Pic+".jpg"); // ��ᰡ �ٲ� ���� �������� �ٲ�

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
						img1 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\��2.jpg"); // ��ᰡ �ٲ� ���� �������� �ٲ�
						
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
	
	public void paint(Graphics g) { // ���� �̹��� ���
		super.paint(g);
		g.drawImage(img1, 0, 0, 100, 100, this);
	}
	
	public void DBConn(String main1) { // DB�� �����ϰ� �������� �����ϴ� �κ�
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
			if(main1=="��") {
				sql = "select * from jeon where food_name='"+main1+"'";
				how_many=7;
			} else if(main1=="�����") {
				sql = "select * from bibim where food_name='"+main1+"'";
				how_many=8;
			} else if(main1=="�ع���") {
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
			} catch(Exception e){ }
		}
	}
	
	public static void main(String[] args) {
		new test7();
	}
}