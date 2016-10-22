/**@author 김서희
 * 사각형을 그려서 화면이 분할 된 것 처럼 보이게 함
 */
/*
package test2;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
	public test2() {
		Container ct = getContentPane();
		
		MyPanel panel = new MyPanel(); // 사각형을 그리기 위한 클래스 호출
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
			g.drawRect(0, 0, 500, 350); // 사각형을 그림
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


/**@author 김서희
 * 한 명만 테이블을 사용하는 경우
 */
/*
package test2;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
	int x=400, y=400; // 화면에 꽉 차는 크기
	
	public test2() {
		Container ct = getContentPane();
		
		int nfc1=0, nfc2=0, nfc3=0, nfc4=1;// nfc는 각 자리의 이름. nfc1,2는 화면이 거꾸로 나와야 함
		
		if(nfc1==1 && nfc2==0 && nfc3==0 && nfc4==0) { // 1번 자리에 앉았을 경우
			// 여기에서 이미지랑 글씨같은거 뒤집는다.
			MyPanel1 panel1 = new MyPanel1(x,y); // 사각형을 그리기 위한 클래스 호출
			ct.add(panel1);
		} else if(nfc1==0 && nfc2==1 && nfc3==0 && nfc4==0) { // 2번 자리에 앉았을 경우
			// 여기에서 이미지랑 글씨같은거 뒤집는다.
			MyPanel1 panel1 = new MyPanel1(x,y); // 사각형을 그리기 위한 클래스 호출
			ct.add(panel1);
		} else if(nfc1==0 && nfc2==0 && nfc3==1 && nfc4==0) { // 3번 자리에 앉았을 경우
			// 여기에서 이미지랑 글씨같은것을 보여줌.
			MyPanel1 panel1 = new MyPanel1(x,y); // 사각형을 그리기 위한 클래스 호출
			ct.add(panel1);
		} else if(nfc1==0 && nfc2==0 && nfc3==0 && nfc4==1) { // 4번 자리에 앉았을 경우
			// 여기에서 이미지랑 글씨같은것을 보여줌.
			MyPanel1 panel1 = new MyPanel1(x,y); // 사각형을 그리기 위한 클래스 호출
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


/**@author 김서희
 * 2명이 자리에 앉은 경우(13, 14, 23, 24)
 */
/*
package test2;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
	int x=400, y=400; // 화면에 꽉 차는 크기
	
	public test2() {
		Container ct = getContentPane();
		
		int nfc1=0, nfc2=1, nfc3=0, nfc4=1;// nfc는 각 자리의 이름. nfc1,2는 화면이 거꾸로 나와야 함
		
		if(nfc1==1 && nfc2==0 && nfc3==1 && nfc4==0) { // 1번3번 자리에 앉았을 경우
			// 여기에서 이미지랑 글씨같은거 뒤집는다. (1번 자리)
			// 여기에서 이미지랑 글씨같은 것을 보여준다. (3번 자리)
			MyPanel2 panel2 = new MyPanel2(x,y); // 사각형을 그리기 위한 클래스 호출
			ct.add(panel2);
		} else if(nfc1==1 && nfc2==0 && nfc3==0 && nfc4==1) { // 1번4번 자리에 앉았을 경우
			// 여기에서 이미지랑 글씨같은거 뒤집는다. (1번 자리)
			// 여기에서 이미지랑 글씨같은 것을 보여준다. (4번 자리)
			MyPanel2 panel2 = new MyPanel2(x,y); // 사각형을 그리기 위한 클래스 호출
			ct.add(panel2);
		} else if(nfc1==0 && nfc2==1 && nfc3==1 && nfc4==0) { // 2번3번 자리에 앉았을 경우
			// 여기에서 이미지랑 글씨같은거 뒤집는다. (2번 자리)
			// 여기에서 이미지랑 글씨같은 것을 보여준다. (3번 자리)
			MyPanel2 panel2 = new MyPanel2(x,y); // 사각형을 그리기 위한 클래스 호출
			ct.add(panel2);
		} else if(nfc1==0 && nfc2==1 && nfc3==0 && nfc4==1) { // 2번4번 자리에 앉았을 경우
			// 여기에서 이미지랑 글씨같은거 뒤집는다. (2번 자리)
			// 여기에서 이미지랑 글씨같은 것을 보여준다. (4번 자리)
			MyPanel2 panel2 = new MyPanel2(x,y); // 사각형을 그리기 위한 클래스 호출
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


/**@author 김서희
 * 3명이 자리에 앉았는데 윗 줄에 1명, 밑 줄에 2명 앉았을 경우
 */
/*
package test2;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
	int x=400, y=400; // 화면에 꽉 차는 크기
	
	public test2() {
		Container ct = getContentPane();
		
		int nfc1=0, nfc2=1, nfc3=1, nfc4=1;// nfc는 각 자리의 이름. nfc1,2는 화면이 거꾸로 나와야 함
		
		if(nfc1==1 && nfc2==0 && nfc3==1 && nfc4==1) { // 1번, 3번, 4번  자리에 앉았을 경우
			// 여기에서 이미지랑 글씨같은거 뒤집는다. (1번 자리)
			// 여기에서 이미지랑 글씨같은 것을 보여준다. (3번 4번 자리)
			MyPanel3_1 panel3_1 = new MyPanel3_1(x, y); // 사각형을 그리기 위한 클래스 호출
			ct.add(panel3_1);
		} else if(nfc1==0 && nfc2==1 && nfc3==1 && nfc4==1) { // 2번, 3번, 4번 자리에 앉았을 경우
			// 여기에서 이미지랑 글씨같은거 뒤집는다. (2번 자리)
			// 여기에서 이미지랑 글씨같은 것을 보여준다. (3번 4번 자리)
			MyPanel3_1 panel3_1 = new MyPanel3_1(x, y); // 사각형을 그리기 위한 클래스 호출
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


/**@author 김서희
 * 3명이 자리에 앉았는데 밑 줄에 1명, 윗 줄에 2명 앉았을 경우
 */
/*
package test2;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
	int x=400, y=400; // 화면에 꽉 차는 크기
	
	public test2() {
		Container ct = getContentPane();
		
		int nfc1=1, nfc2=1, nfc3=1, nfc4=0;// nfc는 각 자리의 이름. nfc1,2는 화면이 거꾸로 나와야 함
		
		if(nfc1==1 && nfc2==1 && nfc3==1 && nfc4==0) { // 1번, 2번, 3번  자리에 앉았을 경우
			// 여기에서 이미지랑 글씨같은거 뒤집는다. (1번  2번 자리)
			// 여기에서 이미지랑 글씨같은 것을 보여준다. (3번 자리)
			MyPanel3_2 panel3_2 = new MyPanel3_2(x, y); // 사각형을 그리기 위한 클래스 호출
			ct.add(panel3_2);
		} else if(nfc1==1 && nfc2==1 && nfc3==0 && nfc4==1) { // 1번, 2번, 4번 자리에 앉았을 경우
			// 여기에서 이미지랑 글씨같은거 뒤집는다. (1번 2번 자리)
			// 여기에서 이미지랑 글씨같은 것을 보여준다. (4번 자리)
			MyPanel3_2 panel3_2 = new MyPanel3_2(x, y); // 사각형을 그리기 위한 클래스 호출
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


/**@author 김서희
 * 4명이 모두 자리에 않은 경우
 */
/*
package test2;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
	int x=400, y=400; // 화면에 꽉 차는 크기
	
	public test2() {
		Container ct = getContentPane();
		
		int nfc1=1, nfc2=1, nfc3=1, nfc4=1;// nfc는 각 자리의 이름. nfc1,2는 화면이 거꾸로 나와야 함
		
		if(nfc1==1 && nfc2==1 && nfc3==1 && nfc4==1) { // 1번, 2번, 3번, 4번  자리에 앉았을 경우
			// 여기에서 이미지랑 글씨같은거 뒤집는다. (1번  2번 자리)
			// 여기에서 이미지랑 글씨같은 것을 보여준다. (3번 4번 자리)
			MyPanel4 panel4 = new MyPanel4(x, y); // 사각형을 그리기 위한 클래스 호출
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
