import java.awt.*;

import javax.swing.*;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ExplainMenu extends JFrame implements MouseListener, MouseMotionListener {
	
	Image menuImg, materialImg, explainbox;
	int menuImg_x = 100, menuImg_y = 100, menuImg_w = 150, menuImg_h = 150;
	int explainbox_x = 50, explainbox_y = 400, explainbox_w = 400, explainbox_h = 50;
	
	boolean menuImgClick, explainIn;
	
	Image buffImage;
	Graphics buffg;
	
	String menuExplain = "";


	public ExplainMenu(){
		
		init();
		
		
		setTitle("�޴����� �׽�Ʈ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setVisible(true);
		
	}
	
	public void init(){
		menuImg = new ImageIcon("./images/menu.jpg").getImage();
		materialImg = new ImageIcon("./images/material.jpg").getImage();
		explainbox = new ImageIcon("./images/explainbox.jpg").getImage();
		
		menuImgClick = false;
		explainIn = false;
	}
	
	public void paint(Graphics g){
		buffImage = createImage(500, 500);
		buffg = buffImage.getGraphics();
		update(g);
	}
	
	public void update(Graphics g){
		showMenu();
		showExplainbox();
		g.drawImage(buffImage, 0, 0, this);
	}
	
	public void showMenu(){
		//��õ���� ��ġ��
		buffg.drawImage(menuImg, menuImg_x, menuImg_y, this);
	}
	
	public void showExplainbox(){
		buffg.drawImage(explainbox, explainbox_x, explainbox_y, this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new ExplainMenu();

	}
	
	
	/* Mouse, MouseMotion Event ó�� �Լ� */
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if((menuImg_x <= arg0.getX() && arg0.getX() <= menuImg_x + menuImg_w) && (menuImg_y <= arg0.getY() && arg0.getY() <= menuImg_y + menuImg_h))
			menuImgClick = true;
		
		System.out.println(menuImgClick);
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(menuImgClick){
			System.out.println("�޴�����");
			if((explainbox_x <= arg0.getX() && arg0.getX() <= explainbox_x + explainbox_w) 
					&& (explainbox_y <= arg0.getY() && arg0.getY() <= explainbox_y + explainbox_h)){
				explainIn = true;
				menuExplainView();
			}
			menuImgClick = false;
		}
	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//���� ����
	public void menuExplainView(){
		//��� ����
		//�ش� �޴��� �´� �ʵ� �� ��������
		//String menuExplain ������ ����
		//ȭ�鿡 ���̱�
	}



}
