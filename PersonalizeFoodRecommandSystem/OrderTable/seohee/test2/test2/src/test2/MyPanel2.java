/**@author �輭��
 * 2���� �ڸ��� ���� ���
 */
package test2;

import javax.swing.*;
import java.awt.*;

class MyPanel2 extends JPanel {
	int nfc_x1, nfc_y1;
	int nfc_x_s1, nfc_y_s1;
	int nfc_x2, nfc_y2;
	int nfc_x_s2, nfc_y_s2;
	public MyPanel2(int x, int y) {
		nfc_x1 = x;
		nfc_y1 = y/2;
		nfc_x_s1 = 0;
		nfc_y_s1 = 0;
		nfc_x2 = x;
		nfc_y2 = y/2;
		nfc_x_s2 = 0;
		nfc_y_s2 = y/2;
	}
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.black);
		g.drawRect(nfc_x_s1, nfc_y_s1, nfc_x1, nfc_y1); // �簢���� �׸�
		g.drawRect(nfc_x_s2, nfc_y_s2, nfc_x2, nfc_y2); 
	}
}