/**@author �輭��
 * �簢���� �׷��� ȭ���� ���� �� �� ó�� ���̰� ��
 */
/*
package test2;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
	public test2() {
		Container ct = getContentPane();
		
		MyPanel panel = new MyPanel(); // �簢���� �׸��� ���� Ŭ���� ȣ��
		ct.add(panel);
		
		setTitle("Test2_1");
		setSize(1000,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.setColor(Color.black);
			g.drawRect(0, 0, 500, 350); // �簢���� �׸�
			g.drawRect(500, 350, 500, 350);
			g.drawRect(0, 350, 500, 350);
			g.drawRect(500, 0, 500, 350);
		}
	}
	public static void main(String[] args) {
		new test2();
	}
}
*/


/**@author �輭��
 * �� �� ���̺��� ����ϴ� ���
 */
/*
package test2;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
	int x=400, y=400; // ȭ�鿡 �� ���� ũ��
	
	public test2() {
		Container ct = getContentPane();
		
		int nfc1=0, nfc2=0, nfc3=0, nfc4=1;// nfc�� �� �ڸ��� �̸�. nfc1,2�� ȭ���� �Ųٷ� ���;� ��
		
		if(nfc1==1 && nfc2==0 && nfc3==0 && nfc4==0) { // 1�� �ڸ��� �ɾ��� ���
			// ���⿡�� �̹����� �۾������� �����´�.
			MyPanel1 panel1 = new MyPanel1(x,y); // �簢���� �׸��� ���� Ŭ���� ȣ��
			ct.add(panel1);
		} else if(nfc1==0 && nfc2==1 && nfc3==0 && nfc4==0) { // 2�� �ڸ��� �ɾ��� ���
			// ���⿡�� �̹����� �۾������� �����´�.
			MyPanel1 panel1 = new MyPanel1(x,y); // �簢���� �׸��� ���� Ŭ���� ȣ��
			ct.add(panel1);
		} else if(nfc1==0 && nfc2==0 && nfc3==1 && nfc4==0) { // 3�� �ڸ��� �ɾ��� ���
			// ���⿡�� �̹����� �۾��������� ������.
			MyPanel1 panel1 = new MyPanel1(x,y); // �簢���� �׸��� ���� Ŭ���� ȣ��
			ct.add(panel1);
		} else if(nfc1==0 && nfc2==0 && nfc3==0 && nfc4==1) { // 4�� �ڸ��� �ɾ��� ���
			// ���⿡�� �̹����� �۾��������� ������.
			MyPanel1 panel1 = new MyPanel1(x,y); // �簢���� �׸��� ���� Ŭ���� ȣ��
			ct.add(panel1);
		}
		
		setTitle("Test2_2");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new test2();
	}
}
*/


/**@author �輭��
 * 2���� �ڸ��� ���� ���(13, 14, 23, 24)
 */
/*
package test2;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
	int x=400, y=400; // ȭ�鿡 �� ���� ũ��
	
	public test2() {
		Container ct = getContentPane();
		
		int nfc1=0, nfc2=1, nfc3=0, nfc4=1;// nfc�� �� �ڸ��� �̸�. nfc1,2�� ȭ���� �Ųٷ� ���;� ��
		
		if(nfc1==1 && nfc2==0 && nfc3==1 && nfc4==0) { // 1��3�� �ڸ��� �ɾ��� ���
			// ���⿡�� �̹����� �۾������� �����´�. (1�� �ڸ�)
			// ���⿡�� �̹����� �۾����� ���� �����ش�. (3�� �ڸ�)
			MyPanel2 panel2 = new MyPanel2(x,y); // �簢���� �׸��� ���� Ŭ���� ȣ��
			ct.add(panel2);
		} else if(nfc1==1 && nfc2==0 && nfc3==0 && nfc4==1) { // 1��4�� �ڸ��� �ɾ��� ���
			// ���⿡�� �̹����� �۾������� �����´�. (1�� �ڸ�)
			// ���⿡�� �̹����� �۾����� ���� �����ش�. (4�� �ڸ�)
			MyPanel2 panel2 = new MyPanel2(x,y); // �簢���� �׸��� ���� Ŭ���� ȣ��
			ct.add(panel2);
		} else if(nfc1==0 && nfc2==1 && nfc3==1 && nfc4==0) { // 2��3�� �ڸ��� �ɾ��� ���
			// ���⿡�� �̹����� �۾������� �����´�. (2�� �ڸ�)
			// ���⿡�� �̹����� �۾����� ���� �����ش�. (3�� �ڸ�)
			MyPanel2 panel2 = new MyPanel2(x,y); // �簢���� �׸��� ���� Ŭ���� ȣ��
			ct.add(panel2);
		} else if(nfc1==0 && nfc2==1 && nfc3==0 && nfc4==1) { // 2��4�� �ڸ��� �ɾ��� ���
			// ���⿡�� �̹����� �۾������� �����´�. (2�� �ڸ�)
			// ���⿡�� �̹����� �۾����� ���� �����ش�. (4�� �ڸ�)
			MyPanel2 panel2 = new MyPanel2(x,y); // �簢���� �׸��� ���� Ŭ���� ȣ��
			ct.add(panel2);
		}
		
		setTitle("Test2_3");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new test2();
	}
}
*/


/**@author �輭��
 * 3���� �ڸ��� �ɾҴµ� �� �ٿ� 1��, �� �ٿ� 2�� �ɾ��� ���
 */
/*
package test2;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
	int x=400, y=400; // ȭ�鿡 �� ���� ũ��
	
	public test2() {
		Container ct = getContentPane();
		
		int nfc1=0, nfc2=1, nfc3=1, nfc4=1;// nfc�� �� �ڸ��� �̸�. nfc1,2�� ȭ���� �Ųٷ� ���;� ��
		
		if(nfc1==1 && nfc2==0 && nfc3==1 && nfc4==1) { // 1��, 3��, 4��  �ڸ��� �ɾ��� ���
			// ���⿡�� �̹����� �۾������� �����´�. (1�� �ڸ�)
			// ���⿡�� �̹����� �۾����� ���� �����ش�. (3�� 4�� �ڸ�)
			MyPanel3_1 panel3_1 = new MyPanel3_1(x, y); // �簢���� �׸��� ���� Ŭ���� ȣ��
			ct.add(panel3_1);
		} else if(nfc1==0 && nfc2==1 && nfc3==1 && nfc4==1) { // 2��, 3��, 4�� �ڸ��� �ɾ��� ���
			// ���⿡�� �̹����� �۾������� �����´�. (2�� �ڸ�)
			// ���⿡�� �̹����� �۾����� ���� �����ش�. (3�� 4�� �ڸ�)
			MyPanel3_1 panel3_1 = new MyPanel3_1(x, y); // �簢���� �׸��� ���� Ŭ���� ȣ��
			ct.add(panel3_1);
		}
		
		setTitle("Test2_4");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new test2();
	}
}
*/


/**@author �輭��
 * 3���� �ڸ��� �ɾҴµ� �� �ٿ� 1��, �� �ٿ� 2�� �ɾ��� ���
 */
/*
package test2;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
	int x=400, y=400; // ȭ�鿡 �� ���� ũ��
	
	public test2() {
		Container ct = getContentPane();
		
		int nfc1=1, nfc2=1, nfc3=1, nfc4=0;// nfc�� �� �ڸ��� �̸�. nfc1,2�� ȭ���� �Ųٷ� ���;� ��
		
		if(nfc1==1 && nfc2==1 && nfc3==1 && nfc4==0) { // 1��, 2��, 3��  �ڸ��� �ɾ��� ���
			// ���⿡�� �̹����� �۾������� �����´�. (1��  2�� �ڸ�)
			// ���⿡�� �̹����� �۾����� ���� �����ش�. (3�� �ڸ�)
			MyPanel3_2 panel3_2 = new MyPanel3_2(x, y); // �簢���� �׸��� ���� Ŭ���� ȣ��
			ct.add(panel3_2);
		} else if(nfc1==1 && nfc2==1 && nfc3==0 && nfc4==1) { // 1��, 2��, 4�� �ڸ��� �ɾ��� ���
			// ���⿡�� �̹����� �۾������� �����´�. (1�� 2�� �ڸ�)
			// ���⿡�� �̹����� �۾����� ���� �����ش�. (4�� �ڸ�)
			MyPanel3_2 panel3_2 = new MyPanel3_2(x, y); // �簢���� �׸��� ���� Ŭ���� ȣ��
			ct.add(panel3_2);
		}
		
		setTitle("Test2_5");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new test2();
	}
}
*/


/**@author �輭��
 * 4���� ��� �ڸ��� ���� ���
 */
/*
package test2;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
	int x=400, y=400; // ȭ�鿡 �� ���� ũ��
	
	public test2() {
		Container ct = getContentPane();
		
		int nfc1=1, nfc2=1, nfc3=1, nfc4=1;// nfc�� �� �ڸ��� �̸�. nfc1,2�� ȭ���� �Ųٷ� ���;� ��
		
		if(nfc1==1 && nfc2==1 && nfc3==1 && nfc4==1) { // 1��, 2��, 3��, 4��  �ڸ��� �ɾ��� ���
			// ���⿡�� �̹����� �۾������� �����´�. (1��  2�� �ڸ�)
			// ���⿡�� �̹����� �۾����� ���� �����ش�. (3�� 4�� �ڸ�)
			MyPanel4 panel4 = new MyPanel4(x, y); // �簢���� �׸��� ���� Ŭ���� ȣ��
			ct.add(panel4);
		}
		
		setTitle("Test2_6");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new test2();
	}
}
*/
