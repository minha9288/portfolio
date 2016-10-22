/**@author �輭��
 * ���콺�� �����̴� ��ǥ�� �巡���ϴ� ��ǥ�� ȭ�� ��, �Ʒ��� ǥ��
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


/**@author �輭��
 * 1�� �ڸ��� ����� �⺻ ȭ�鿡�� 1�ܰ� �Ǵ� 2�ܰ�� ȭ���� Ȯ���ϰų� �״�� �⺻ȭ���� ��Ű�� ���
 * drag ���
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
	int msX, msY, mmX, mmY;// ���콺 Ŭ�� ����x, ���콺 Ŭ������y, ���콺 �巡��x, ���콺 �巡��y
	
	public test3() {
		ct = getContentPane();
		label = new JLabel();
		label2 = new JLabel();
		ct.add("North", label);
		ct.add("South", label2);
		
		what=0; // ���� ���� ���� �ܰ迡 �ִ��� üũ(�⺻0, 1�ܰ�1, 2�ܰ�2)
		
		int nfc1=1, nfc2=1, nfc3=1, nfc4=1;// nfc�� �� �ڸ��� �̸�. nfc1,2�� ȭ���� �Ųٷ� ���;� ��
		
		if(nfc1==1 && nfc2==1 && nfc3==1 && nfc4==1) { // 1��, 2��, 3��, 4��  �ڸ��� �ɾ��� ���
			MyPanel4 panel4 = new MyPanel4(x, y); // �簢���� �׸��� ���� Ŭ���� ȣ��
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
			// �⺻
			if(mmX>=x/2-50 && mmX<=x/2+50 && mmY>=y/2-30 && mmY<=y/2+50) {
				MyPanel4 panel4 = new MyPanel4(x, y); // �簢���� �׸��� ���� Ŭ���� ȣ��
				ct.add(panel4);
				what=0;
			}
			// 1�ܰ�
			if(mmX>=(x/2)*1.3-50 && mmX<=(x/2)*1.3+50 && mmY>=(y/2)*1.3-50 && mmY<=(y/2)*1.3+50) {
				Sit1Up1 su = new Sit1Up1(x, y);
				ct.add(su);
				what=1;
			}
			// 2�ܰ�
			if(mmX>=(x/2)*1.6-50 && mmX<=(x/2)*1.6+50 && mmY>=(y/2)*1.6-50 && mmY<=(y/2)*1.6+50) {
				Sit1Up2 su = new Sit1Up2(x, y);
				ct.add(su);
				what=2;
			}
		}
		
		// 1�ܰ迡�� �⺻, 1�ܰ�, 2�ܰ��
		if(what==1 && msX>=(x/2)*1.3-50 && msX<=(x/2)*1.3+50 && msY>=(y/2)*1.3-50 && msY<=(y/2)*1.3+50) {
			// �⺻
			if(mmX>=x/2-50 && mmX<=x/2+50 && mmY>=y/2-30 && mmY<=y/2+50) {
				MyPanel4 panel4 = new MyPanel4(x, y); // �簢���� �׸��� ���� Ŭ���� ȣ��
				ct.add(panel4);
				what=0;
			}
			// 1�ܰ�
			if(mmX>=(x/2)*1.3-50 && mmX<=(x/2)*1.3+50 && mmY>=(y/2)*1.3-50 && mmY<=(y/2)*1.3+50) {
				Sit1Up1 su = new Sit1Up1(x, y);
				ct.add(su);
				what=1;
			}
			// 2�ܰ�
			if(mmX>=(x/2)*1.6-50 && mmX<=(x/2)*1.6+50 && mmY>=(y/2)*1.6-50 && mmY<=(y/2)*1.6+50) {
				Sit1Up2 su = new Sit1Up2(x, y);
				ct.add(su);
				what=2;
			}
		}
		
		// 2�ܰ迡�� �⺻, 1�ܰ�, 2�ܰ��
		if(what==2 && msX>=(x/2)*1.6-50 && msX<=(x/2)*1.6+50 && msY>=(y/2)*1.6-50 && msY<=(y/2)*1.6+50) {
			// �⺻
			if(mmX>=x/2-50 && mmX<=x/2+50 && mmY>=y/2-30 && mmY<=y/2+50) {
				MyPanel4 panel4 = new MyPanel4(x, y); // �簢���� �׸��� ���� Ŭ���� ȣ��
				ct.add(panel4);
				what=0;
			}
			// 1�ܰ�
			if(mmX>=(x/2)*1.3-50 && mmX<=(x/2)*1.3+50 && mmY>=(y/2)*1.3-50 && mmY<=(y/2)*1.3+50) {
				Sit1Up1 su = new Sit1Up1(x, y);
				ct.add(su);
				what=1;
			}
			// 2�ܰ�
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



/**@author �輭��
 * 1�� �ڸ��� ����� �⺻ ȭ�鿡�� 1�ܰ� �Ǵ� 2�ܰ�� ȭ���� Ȯ���ϰų� �״�� �⺻ȭ���� ��Ű�� ���
 * press, release ���
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
	int msX, msY, meX, meY; // ���콺 Ŭ�� ����x, ���콺 Ŭ������y, ���콺 release x, ���콺 release y
	int what;
	
	public test3() {
		ct = getContentPane();
		label = new JLabel();
		label2 = new JLabel();
		ct.add("North", label);
		ct.add("South", label2);
		
		what=0; // ���� ���� ���� �ܰ迡 �ִ��� üũ(�⺻0, 1�ܰ�1, 2�ܰ�2)
		
		int nfc1=1, nfc2=1, nfc3=1, nfc4=1;// nfc�� �� �ڸ��� �̸�. nfc1,2�� ȭ���� �Ųٷ� ���;� ��
		
		if(nfc1==1 && nfc2==1 && nfc3==1 && nfc4==1) { // 1��, 2��, 3��, 4��  �ڸ��� �ɾ��� ���
			MyPanel4 panel4 = new MyPanel4(x, y); // �簢���� �׸��� ���� Ŭ���� ȣ��
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
			// �⺻
			if(meX>=x/2-50 && meX<=x/2+50 && meY>=y/2-30 && meY<=y/2+50) {
				MyPanel4 panel4 = new MyPanel4(x, y); // �簢���� �׸��� ���� Ŭ���� ȣ��
				ct.add(panel4);
				what =0;
			}
			// 1�ܰ�
			if(meX>=(x/2)*1.3-50 && meX<=(x/2)*1.3+50 && meY>=(y/2)*1.3-50 && meY<=(y/2)*1.3+50) {
				Sit1Up1 su = new Sit1Up1(x, y);
				ct.add(su);
				what=1;
			}
			// 2�ܰ�
			if(meX>=(x/2)*1.6-50 && meX<=(x/2)*1.6+50 && meY>=(y/2)*1.6-50 && meY<=(y/2)*1.6+50) {
				Sit1Up2 su = new Sit1Up2(x, y);
				ct.add(su);
				what=2;
			}
		}
		
		// 1�ܰ迡�� �⺻, 1�ܰ�, 2�ܰ��
		if(what==1 && msX>=(x/2)*1.3-50 && msX<=(x/2)*1.3+50 && msY>=(y/2)*1.3-50 && msY<=(y/2)*1.3+50) {
			// �⺻
			if(meX>=x/2-50 && meX<=x/2+50 && meY>=y/2-30 && meY<=y/2+50) {
				MyPanel4 panel4 = new MyPanel4(x, y); // �簢���� �׸��� ���� Ŭ���� ȣ��
				ct.add(panel4);
				what=0;
			}
			// 1�ܰ�
			if(meX>=(x/2)*1.3-50 && meX<=(x/2)*1.3+50 && meY>=(y/2)*1.3-50 && meY<=(y/2)*1.3+50) {
				Sit1Up1 su = new Sit1Up1(x, y);
				ct.add(su);
				what=1;
			}
			// 2�ܰ�
			if(meX>=(x/2)*1.6-50 && meX<=(x/2)*1.6+50 && meY>=(y/2)*1.6-50 && meY<=(y/2)*1.6+50) {
				Sit1Up2 su = new Sit1Up2(x, y);
				ct.add(su);
				what=2;
			}
		}
		
		// 2�ܰ迡�� �⺻, 1�ܰ�, 2�ܰ��
		if(what==2 && msX>=(x/2)*1.6-50 && msX<=(x/2)*1.6+50 && msY>=(y/2)*1.6-50 && msY<=(y/2)*1.6+50) {
			// �⺻
			if(meX>=x/2-50 && meX<=x/2+50 && meY>=y/2-30 && meY<=y/2+50) {
				MyPanel4 panel4 = new MyPanel4(x, y); // �簢���� �׸��� ���� Ŭ���� ȣ��
				ct.add(panel4);
				what=0;
			}
			// 1�ܰ�
			if(meX>=(x/2)*1.3-50 && meX<=(x/2)*1.3+50 && meY>=(y/2)*1.3-50 && meY<=(y/2)*1.3+50) {
				Sit1Up1 su = new Sit1Up1(x, y);
				ct.add(su);
				what=1;
			}
			// 2�ܰ�
			if(meX>=(x/2)*1.6-50 && meX<=(x/2)*1.6+50 && meY>=(y/2)*1.6-50 && meY<=(y/2)*1.6+50) {
				Sit1Up2 su = new Sit1Up2(x, y);
				ct.add(su);
				what=2;
			}
		} 
	}
}
*/



