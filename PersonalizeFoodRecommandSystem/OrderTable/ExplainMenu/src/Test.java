import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Test extends JComponent implements MouseListener, MouseMotionListener
{
	//�簢������
	Rectangle box;

	//���콺 �巡�� üũ
	boolean isDragged;

	//���콺 ��������ǥ
	int offX, offY;

	public Test(){

		//�簢������ ����(100x80 size)
		box = new Rectangle(0,0,100,80);

		//���� �巡�� ���� ����
		isDragged = false;

		//���콺 ������ ���
		addMouseListener(this);
		addMouseMotionListener(this);

	}

	//������Ʈ ������
	public void paintComponent(Graphics g){
		
		//�簢�� �׸� ���� ����
		g.setColor(Color.red);

		//�簢�� �׸�
		g.drawRect(box.x,box.y,box.width,box.height);

		//�簢���� �̵��ϱ� ���Ͽ� �簢���� x,y ��ǥ�� �簢�� �� Ŭ���� ���콺�� ��ǥ�� �ʿ��ϴ�

	}

 	public void mousePressed(MouseEvent me){
		//�簢�� �ȿ����� �̺�Ʈ �۾� ����
		if(box.contains(new Point(me.getX(),me.getY()))){
			//#1 ���콺 ��ư ����
			//�簢���� ���콺 Ŭ�� ��� ��ǥ�� ����
			//���� ���콺 ��ũ�� ��ǥ���� �簢�� ��ġ ��ǥ�� ���̸� ����
			offX = me.getX() - box.x;
			offY = me.getY() - box.y;
			
			//�巡�� ������ ǥ��
			isDragged = true;

		}
	}
 	
	public void mouseReleased(MouseEvent me){
		//���콺 ��ư�� ������Ǹ� �巡�� ��� ����
		isDragged = false;
	}
	public void mouseDragged(MouseEvent me){
		
		//�巡�� ����� ��쿡�� �簢�� �̵���Ŵ
		if(isDragged){
			box.x = me.getX() - offX;
			box.y = me.getY() - offY;
		}
		repaint();
	
	}
	public void mouseMoved(MouseEvent me){}
	public void mouseClicked(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}

	//���α׷� ����
	public static void main(String[] args){

		JFrame f = new JFrame("http://sexy.pe.kr");
		f.setBounds(0,0,300,300);
		f.add(new Test());
		f.setVisible(true);
	}
}