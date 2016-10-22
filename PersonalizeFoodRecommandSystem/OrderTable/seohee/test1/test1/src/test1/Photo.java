package test1;

import javax.swing.*;
import java.awt.*;

public class Photo extends JPanel {
	Image img1;
	
	public Photo(String dir) {
		//dir(이미지의 주소)에 있는 이미지를 얻어옴
		img1 = Toolkit.getDefaultToolkit().getImage(dir);
	}
	public void paint(Graphics g) {
		super.paint(g);
		
		//이미지를 뒤집어서 화면에 draw
		g.drawImage(img1, 0, 100, 100, 0, 0, 0, img1.getWidth(this), img1.getHeight(this), this); // 이미지 경로, 보여질 x여백, y값, x값, y여백, 실제사진의 x값 시작, y값 시작, x값 끝, y값 끝, this
	}
}