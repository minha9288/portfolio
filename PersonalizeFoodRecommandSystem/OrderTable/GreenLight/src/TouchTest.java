import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class TouchTest extends JFrame {

	ImageIcon menuImg1, menuImg2;
	JLabel label1, label2;
	
	Image buffImage;
	Graphics buffg;
	
	boolean menuChange;
	
	int nowSelect = 0;  //기본메뉴 1, 후보메뉴  2,3,4,5
	
	public TouchTest(){
		
		menuImg1 = new ImageIcon("images/menu.jpg");
		menuImg2 = new ImageIcon("images/material.jpg");
		label1 = new JLabel(menuImg1);
		//label2 = new JLabel(menuImg2);
		
		label1.addMouseListener(new MouseHandler());
		//label2.addMouseListener(new MouseHandler());
		add(label1);
		//add(label2);
		
		setTitle("메뉴설명 테스트");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		
		setVisible(true);
		
	}
	
	class MouseHandler implements MouseListener{
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == menuImg1){
			System.out.println("dd");
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
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new TouchTest();

	}
	

}
