/**@author 김서희
 * 버튼에 있는 글자를 180도 돌려서 보여주기
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
		double theta = (1.0) * Math.PI; // 180도
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


/**@author 김서희
 * 버튼 1,2번만 180도 돌리기
 * gridlayout으로 화면 4분할 하기
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
	
		// 버튼 1과 2의 글씨만 뒤집기
		java.awt.geom.AffineTransform rotate;
		button1.setBorder(new javax.swing.border.EmptyBorder(40, 4, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180도
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


/**@author 김서희
 * label도 뒤집어짐
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
		
		// 글씨 뒤집기
		java.awt.geom.AffineTransform rotate;
		label.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180도
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


/**@author 김서희
 * 이미지 뒤집기
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
		new test1("c:\\Users\\김서희\\Pictures\\11.jpg");
	}
}
*/


/**@author 김서희
 * 버튼에 이미지 삽입하기
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
		
		// 이미지 경로를 ImageIcon로 저장
		img1 = new ImageIcon(image1);
		img2 = new ImageIcon(image2);
		img3 = new ImageIcon(image3);
		img4 = new ImageIcon(image4);
		
		// 이미지 아이콘을 버튼으로 저장
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
	
		// 이미지의 경로를 매개변수로 하여 생성자 호출
		new test1("c:\\Users\\김서희\\Pictures\\12.jpg", "c:\\Users\\김서희\\Pictures\\11.jpg", "c:\\Users\\김서희\\Pictures\\10.jpg", "c:\\Users\\김서희\\Pictures\\9.jpg");
	}
}
*/


/**@author 김서희
 * 이미지 위에 2개만 돌리고 밑에 두개는 그대로
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
		
		//사진의 주소를 String으로 저장
		String dir = "c:\\Users\\김서희\\Pictures\\7.jpg";
		String dir2 = "c:\\Users\\김서희\\Pictures\\8.jpg";
		String dir3 = "c:\\Users\\김서희\\Pictures\\9.jpg";
		String dir4 = "c:\\Users\\김서희\\Pictures\\10.jpg";
		
		ct.setLayout(new GridLayout(2,2));
		
		// 각 사진을 Photo class의 생성자의 매개변수로 보냄
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


