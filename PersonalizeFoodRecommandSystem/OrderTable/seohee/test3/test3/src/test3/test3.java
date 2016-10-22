/**@author 김서희
 * 마우스의 움직이는 좌표와 드래그하는 좌표를 화면 위, 아래에 표시
 */
/*
package test3;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class test3 extends JFrame implements MouseListener, MouseMotionListener {
	Container ct;
	JLabel label, label2;
	public test3() {
		ct = getContentPane();
		label = new JLabel();
		label2 = new JLabel();
		ct.add("North", label);
		ct.add("South", label2);
		
		addMouseMotionListener(this);
		
		setTitle("Test3_1");
		setSize(700, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		label2.setText("pX="+arg0.getX()+" pY="+arg0.getY());
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		label.setText("X="+arg0.getX()+" Y="+arg0.getY());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new test3();
	}
}
*/


/**@author 김서희
 * 1번 자리의 사람이 기본 화면에서 1단계 또는 2단계로 화면을 확대하거나 그대로 기본화면을 지키는 경우
 * drag 사용
 */

package test3;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class test3 extends JFrame implements MouseMotionListener {
	Container ct;
	JLabel label, label2;
	int x=600, y=600;
	int what;
	int msX, msY, mmX, mmY;// 마우스 클릭 시작x, 마우스 클릭시작y, 마우스 드래그x, 마우스 드래그y
	
	public test3() {
		ct = getContentPane();
		label = new JLabel();
		label2 = new JLabel();
		ct.add("North", label);
		ct.add("South", label2);
		
		what=0; // 지금 현재 무슨 단계에 있는지 체크(기본0, 1단계1, 2단계2)
		
		int nfc1=1, nfc2=1, nfc3=1, nfc4=1;// nfc는 각 자리의 이름. nfc1,2는 화면이 거꾸로 나와야 함
		
		if(nfc1==1 && nfc2==1 && nfc3==1 && nfc4==1) { // 1번, 2번, 3번, 4번  자리에 앉았을 경우
			MyPanel4 panel4 = new MyPanel4(x, y); // 사각형을 그리기 위한 클래스 호출
			ct.add(panel4);
		}
		
		addMouseMotionListener(this); // drag
		
		setTitle("Test3_2");
		setSize(700, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		label2.setText("pX="+arg0.getX()+" pY="+arg0.getY());
		mmX = arg0.getX();
		mmY = arg0.getY();
		
		if(what==0 && msX>=x/2-50 && msX<=x/2+50 && msY>=y/2-50 && msY<=y/2+50) {
			// 기본
			if(mmX>=x/2-50 && mmX<=x/2+50 && mmY>=y/2-30 && mmY<=y/2+50) {
				MyPanel4 panel4 = new MyPanel4(x, y); // 사각형을 그리기 위한 클래스 호출
				ct.add(panel4);
				what=0;
			}
			// 1단계
			if(mmX>=(x/2)*1.3-50 && mmX<=(x/2)*1.3+50 && mmY>=(y/2)*1.3-50 && mmY<=(y/2)*1.3+50) {
				Sit1Up1 su = new Sit1Up1(x, y);
				ct.add(su);
				what=1;
			}
			// 2단계
			if(mmX>=(x/2)*1.6-50 && mmX<=(x/2)*1.6+50 && mmY>=(y/2)*1.6-50 && mmY<=(y/2)*1.6+50) {
				Sit1Up2 su = new Sit1Up2(x, y);
				ct.add(su);
				what=2;
			}
		}
		
		// 1단계에서 기본, 1단계, 2단계로
		if(what==1 && msX>=(x/2)*1.3-50 && msX<=(x/2)*1.3+50 && msY>=(y/2)*1.3-50 && msY<=(y/2)*1.3+50) {
			// 기본
			if(mmX>=x/2-50 && mmX<=x/2+50 && mmY>=y/2-30 && mmY<=y/2+50) {
				MyPanel4 panel4 = new MyPanel4(x, y); // 사각형을 그리기 위한 클래스 호출
				ct.add(panel4);
				what=0;
			}
			// 1단계
			if(mmX>=(x/2)*1.3-50 && mmX<=(x/2)*1.3+50 && mmY>=(y/2)*1.3-50 && mmY<=(y/2)*1.3+50) {
				Sit1Up1 su = new Sit1Up1(x, y);
				ct.add(su);
				what=1;
			}
			// 2단계
			if(mmX>=(x/2)*1.6-50 && mmX<=(x/2)*1.6+50 && mmY>=(y/2)*1.6-50 && mmY<=(y/2)*1.6+50) {
				Sit1Up2 su = new Sit1Up2(x, y);
				ct.add(su);
				what=2;
			}
		}
		
		// 2단계에서 기본, 1단계, 2단계로
		if(what==2 && msX>=(x/2)*1.6-50 && msX<=(x/2)*1.6+50 && msY>=(y/2)*1.6-50 && msY<=(y/2)*1.6+50) {
			// 기본
			if(mmX>=x/2-50 && mmX<=x/2+50 && mmY>=y/2-30 && mmY<=y/2+50) {
				MyPanel4 panel4 = new MyPanel4(x, y); // 사각형을 그리기 위한 클래스 호출
				ct.add(panel4);
				what=0;
			}
			// 1단계
			if(mmX>=(x/2)*1.3-50 && mmX<=(x/2)*1.3+50 && mmY>=(y/2)*1.3-50 && mmY<=(y/2)*1.3+50) {
				Sit1Up1 su = new Sit1Up1(x, y);
				ct.add(su);
				what=1;
			}
			// 2단계
			if(mmX>=(x/2)*1.6-50 && mmX<=(x/2)*1.6+50 && mmY>=(y/2)*1.6-50 && mmY<=(y/2)*1.6+50) {
				Sit1Up2 su = new Sit1Up2(x, y);
				ct.add(su);
				what=2;
			}
		} 
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		label.setText("ST="+e.getX()+" SY="+e.getY());
		msX = e.getX();
		msY = e.getY();
	}

	public static void main(String[] args) {
		new test3();
	}
}



/**@author 김서희
 * 1번 자리의 사람이 기본 화면에서 1단계 또는 2단계로 화면을 확대하거나 그대로 기본화면을 지키는 경우
 * press, release 사용
 */
/*
package test3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test3 extends JFrame implements MouseListener {
	Container ct;
	JLabel label, label2;
	int x=600, y=600;
	int msX, msY, meX, meY; // 마우스 클릭 시작x, 마우스 클릭시작y, 마우스 release x, 마우스 release y
	int what;
	
	public test3() {
		ct = getContentPane();
		label = new JLabel();
		label2 = new JLabel();
		ct.add("North", label);
		ct.add("South", label2);
		
		what=0; // 지금 현재 무슨 단계에 있는지 체크(기본0, 1단계1, 2단계2)
		
		int nfc1=1, nfc2=1, nfc3=1, nfc4=1;// nfc는 각 자리의 이름. nfc1,2는 화면이 거꾸로 나와야 함
		
		if(nfc1==1 && nfc2==1 && nfc3==1 && nfc4==1) { // 1번, 2번, 3번, 4번  자리에 앉았을 경우
			MyPanel4 panel4 = new MyPanel4(x, y); // 사각형을 그리기 위한 클래스 호출
			ct.add(panel4);
		}
		
		addMouseListener(this); // press, release
		
		setTitle("Test3_2");
		setSize(700, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new test3();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		label.setText("X="+e.getX()+" Y="+e.getY());
		msX = e.getX();
		msY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		label2.setText("pX="+e.getX()+" pY="+e.getY());
		meX = e.getX();
		meY = e.getY();
		
		if(what==0 && msX>=x/2-50 && msX<=x/2+50 && msY>=y/2-50 && msY<=y/2+50) {
			// 기본
			if(meX>=x/2-50 && meX<=x/2+50 && meY>=y/2-30 && meY<=y/2+50) {
				MyPanel4 panel4 = new MyPanel4(x, y); // 사각형을 그리기 위한 클래스 호출
				ct.add(panel4);
				what =0;
			}
			// 1단계
			if(meX>=(x/2)*1.3-50 && meX<=(x/2)*1.3+50 && meY>=(y/2)*1.3-50 && meY<=(y/2)*1.3+50) {
				Sit1Up1 su = new Sit1Up1(x, y);
				ct.add(su);
				what=1;
			}
			// 2단계
			if(meX>=(x/2)*1.6-50 && meX<=(x/2)*1.6+50 && meY>=(y/2)*1.6-50 && meY<=(y/2)*1.6+50) {
				Sit1Up2 su = new Sit1Up2(x, y);
				ct.add(su);
				what=2;
			}
		}
		
		// 1단계에서 기본, 1단계, 2단계로
		if(what==1 && msX>=(x/2)*1.3-50 && msX<=(x/2)*1.3+50 && msY>=(y/2)*1.3-50 && msY<=(y/2)*1.3+50) {
			// 기본
			if(meX>=x/2-50 && meX<=x/2+50 && meY>=y/2-30 && meY<=y/2+50) {
				MyPanel4 panel4 = new MyPanel4(x, y); // 사각형을 그리기 위한 클래스 호출
				ct.add(panel4);
				what=0;
			}
			// 1단계
			if(meX>=(x/2)*1.3-50 && meX<=(x/2)*1.3+50 && meY>=(y/2)*1.3-50 && meY<=(y/2)*1.3+50) {
				Sit1Up1 su = new Sit1Up1(x, y);
				ct.add(su);
				what=1;
			}
			// 2단계
			if(meX>=(x/2)*1.6-50 && meX<=(x/2)*1.6+50 && meY>=(y/2)*1.6-50 && meY<=(y/2)*1.6+50) {
				Sit1Up2 su = new Sit1Up2(x, y);
				ct.add(su);
				what=2;
			}
		}
		
		// 2단계에서 기본, 1단계, 2단계로
		if(what==2 && msX>=(x/2)*1.6-50 && msX<=(x/2)*1.6+50 && msY>=(y/2)*1.6-50 && msY<=(y/2)*1.6+50) {
			// 기본
			if(meX>=x/2-50 && meX<=x/2+50 && meY>=y/2-30 && meY<=y/2+50) {
				MyPanel4 panel4 = new MyPanel4(x, y); // 사각형을 그리기 위한 클래스 호출
				ct.add(panel4);
				what=0;
			}
			// 1단계
			if(meX>=(x/2)*1.3-50 && meX<=(x/2)*1.3+50 && meY>=(y/2)*1.3-50 && meY<=(y/2)*1.3+50) {
				Sit1Up1 su = new Sit1Up1(x, y);
				ct.add(su);
				what=1;
			}
			// 2단계
			if(meX>=(x/2)*1.6-50 && meX<=(x/2)*1.6+50 && meY>=(y/2)*1.6-50 && meY<=(y/2)*1.6+50) {
				Sit1Up2 su = new Sit1Up2(x, y);
				ct.add(su);
				what=2;
			}
		} 
	}
}
*/



