import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class MenuClick extends JFrame implements MouseListener {

	Image menuImg1, menuImg2;
	
	Image buffImage;
	Graphics buffg;
	
	boolean menuChange;
	
	int nowSelect = 0;  //�⺻�޴� 1, �ĺ��޴�  2,3,4,5
	
	public MenuClick(){
		
		menuImg1 = new ImageIcon("./images/menu.jpg").getImage();
		menuImg2 = new ImageIcon("./images/material.jpg").getImage();
		
		menuChange = false;
				setTitle("�޴����� �׽�Ʈ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(683, 384);
		
		addMouseListener(this);
		
		setVisible(true);
		
	}
	
	public void paint(Graphics g){
		buffImage = createImage(683, 384);
		buffg = buffImage.getGraphics();
		update(g);
	}
	
	public void update(Graphics g){
		showMenu();
		g.drawImage(buffImage, 0, 0, this);
	}
	
	public void showMenu(){
		//��õ���� ��ġ��
		String menuImg = "";
		
		buffg.drawImage(menuImg1, 200, 100, this);
		
		if(menuChange){
			
			for(int i=0; i<5; i++){
				menuImg = "menuImg" + i + "";
				
				
			}
			
			switch(nowSelect){
			case 1: 
				buffg.drawImage(menuImg2, 100, 300, this);
				break;
			}
			buffg.drawImage(menuImg2, 100, 300, this);
			repaint();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if((100 < arg0.getX() && arg0.getX() <= 250) && (100 < arg0.getY() && arg0.getY() <= 250)){
			System.out.println("ddd");
			menuChange = true;
			showMenu();
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new MenuClick();

	}
	

}