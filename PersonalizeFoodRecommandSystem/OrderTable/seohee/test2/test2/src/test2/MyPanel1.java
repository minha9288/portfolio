/**@author 김서희
 * 1명만 자리에 앉은 경우
 */
package test2;

import javax.swing.*;
import java.awt.*;

class MyPanel1 extends JPanel {
	int nfc_x, nfc_y;
	int nfc_x_s, nfc_y_s;
	public MyPanel1(int x, int y) {
		nfc_x = x;
		nfc_y = y;
		nfc_x_s = 0;
		nfc_y_s = 0;
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.drawRect(nfc_x_s, nfc_y_s, nfc_x, nfc_y); // 사각형을 그림
	}
}

