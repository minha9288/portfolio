package test5;

import javax.swing.*;
import java.awt.*;

public class picture extends JPanel {
	Image img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11;
	String pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11;
	
	public picture(String pic_1, String pic_2, String pic_3, String pic_4, String pic_5
			, String pic_6, String pic_7, String pic_8, String pic_9, String pic_10, String pic_11) {
		pic1 = pic_1; pic2 = pic_2; pic3 = pic_3; pic4 = pic_4; pic5 = pic_5; pic6 = pic_6;
		pic7 = pic_7; pic8 = pic_8; pic9 = pic_9; pic10 = pic_10; pic11 = pic_11;
		
		img1 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+pic1+".jpg");
		img2 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+pic2+".jpg");
		img3 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+pic3+".jpg");
		img4 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+pic4+".jpg");
		img5 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+pic5+".jpg");
		img6 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+pic6+".jpg");
		img7 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+pic7+".jpg");
		img8 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+pic8+".jpg");
		img9 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+pic9+".jpg");
		img10 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+pic10+".jpg");
		img11 = Toolkit.getDefaultToolkit().getImage("C:\\SmartTable\\test5\\"+pic11+".jpg");
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(img1, 0, 0, 50, 50, this);
		g.drawImage(img2, 50, 0, 50, 50, this);
		g.drawImage(img3, 100, 0, 50, 50, this);
		g.drawImage(img4, 150, 0, 50, 50, this);
		g.drawImage(img5, 200, 0, 50, 50, this);
		g.drawImage(img6, 50, 50, 50, 50, this);
		g.drawImage(img7, 100, 50, 50, 50, this);
		g.drawImage(img8, 150, 50, 50, 50, this);
		g.drawImage(img9, 200, 50, 50, 50, this);
		g.drawImage(img10, 50, 100, 50, 50, this);
		g.drawImage(img11, 100, 100, 50, 50, this);

		
	}
}
