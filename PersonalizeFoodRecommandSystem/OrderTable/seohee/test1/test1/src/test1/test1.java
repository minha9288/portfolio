/**@author �輭��
 * ��ư�� �ִ� ���ڸ� 180�� ������ �����ֱ�
 */
/*
package test1;

import javax.swing.*;
import java.awt.Font;

public class test1 {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JButton button =  new JButton("Click");
		java.awt.geom.AffineTransform rotate;
		button.setBorder(new javax.swing.border.EmptyBorder(40, 4, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180��
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=button.getFont().deriveFont(rotate);
		button.setFont(rotatedFont);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(button);
		frame.setTitle("Test1_1");
		frame.setSize(300,300);
		frame.setVisible(true);
	}
}
*/


/**@author �輭��
 * ��ư 1,2���� 180�� ������
 * gridlayout���� ȭ�� 4���� �ϱ�
 */
/*
package test1;

import javax.swing.*;
import java.awt.*;

public class test1 extends JFrame{
	public test1() {
		Container ct = getContentPane();
		GridLayout gt = new GridLayout(2,2);
		ct.setLayout(gt);
		JButton button1 = new JButton("button1");
		JButton button2 = new JButton("button2");
		JButton button3 = new JButton("button3");
		JButton button4 = new JButton("button4");
	
		// ��ư 1�� 2�� �۾��� ������
		java.awt.geom.AffineTransform rotate;
		button1.setBorder(new javax.swing.border.EmptyBorder(40, 4, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180��
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=button1.getFont().deriveFont(rotate);
		button1.setFont(rotatedFont);
		button2.setFont(rotatedFont);
		
		ct.add(button1);
		ct.add(button2);
		ct.add(button3);
		ct.add(button4);

		setTitle("Test1_2");
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		new test1();
	}
}
*/


/**@author �輭��
 * label�� ��������
 */
/*
package test1;

import javax.swing.*;
import java.awt.*;

public class test1 extends JFrame {
	public test1() {
		Container ct = getContentPane();
		GridLayout gt = new GridLayout(2,2);
		ct.setLayout(gt);
		JLabel label = new JLabel("hello i'm label");
		
		// �۾� ������
		java.awt.geom.AffineTransform rotate;
		label.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180��
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=label.getFont().deriveFont(rotate);
		label.setFont(rotatedFont);
		
		ct.add(label);
		
		setTitle("Test1_3");
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		new test1();
	}
}
*/


/**@author �輭��
 * �̹��� ������
 */
/*
package test1;

import javax.swing.*;
import java.awt.*;

public class test1 extends JFrame {
	Image img;
	public test1(String imagename) {
		Container ct = getContentPane();
		img = Toolkit.getDefaultToolkit().getImage(imagename);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,1));
		
		setTitle("Test1_4");
		setSize(1024, 705);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, img.getHeight(this), img.getWidth(this), 0, 0, 0, img.getWidth(this), img.getHeight(this), this);
	}
	public static void main(String[] args) {
		new test1("c:\\Users\\�輭��\\Pictures\\11.jpg");
	}
}
*/


/**@author �輭��
 * ��ư�� �̹��� �����ϱ�
 */
/*
package test1;

import javax.swing.*;
import java.awt.*;

public class test1 extends JFrame {
	ImageIcon img1, img2, img3, img4;
	JButton bt1, bt2, bt3, bt4;
	public test1(String image1, String image2, String image3, String image4) {
		Container ct = getContentPane();
		
		// �̹��� ��θ� ImageIcon�� ����
		img1 = new ImageIcon(image1);
		img2 = new ImageIcon(image2);
		img3 = new ImageIcon(image3);
		img4 = new ImageIcon(image4);
		
		// �̹��� �������� ��ư���� ����
		bt1 = new JButton(img1);
		bt2 = new JButton(img2);
		bt3 = new JButton(img3);
		bt4 = new JButton(img4);
		
		
		ct.setLayout(new GridLayout(2,2));
		ct.add(bt1);
		ct.add(bt2);
		ct.add(bt3);
		ct.add(bt4);
		
		setTitle("Test1_4");
		setSize(1024, 705);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
	
		// �̹����� ��θ� �Ű������� �Ͽ� ������ ȣ��
		new test1("c:\\Users\\�輭��\\Pictures\\12.jpg", "c:\\Users\\�輭��\\Pictures\\11.jpg", "c:\\Users\\�輭��\\Pictures\\10.jpg", "c:\\Users\\�輭��\\Pictures\\9.jpg");
	}
}
*/


/**@author �輭��
 * �̹��� ���� 2���� ������ �ؿ� �ΰ��� �״��
 * Photo, Photo2, Photh3, Photh4 class
 */

package test1;

import javax.swing.*;
import java.awt.*;

public class test1 extends JFrame {
	Container ct;
	public test1() {
		ct = getContentPane();
		ct.setLayout(new GridLayout(2,2));
		
		//������ �ּҸ� String���� ����
		String dir = "c:\\Users\\�輭��\\Pictures\\7.jpg";
		String dir2 = "c:\\Users\\�輭��\\Pictures\\8.jpg";
		String dir3 = "c:\\Users\\�輭��\\Pictures\\9.jpg";
		String dir4 = "c:\\Users\\�輭��\\Pictures\\10.jpg";
		
		ct.setLayout(new GridLayout(2,2));
		
		// �� ������ Photo class�� �������� �Ű������� ����
		Photo p = new Photo(dir);
		Photo3 p2 = new Photo3(dir2);
		Photo2 p3 = new Photo2(dir3);
		Photo4 p4 = new Photo4(dir4);
		
		ct.add(p);
		ct.add(p2);
		ct.add(p3);
		ct.add(p4);
		
		setTitle("Test1_4");
		setSize(1024, 705);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new test1();
	}
}


