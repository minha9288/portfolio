package test4;

import javax.swing.*;
import java.awt.*;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class test4 extends JFrame {
	Container ct;
	
	test4() {
		Translate.setClientId("rlatjgml38");
		Translate.setClientSecret("nOFqHOM+LLYxO9SFbV+8Sp7cplsPf6xYJoJfY6qpdsI=");
		
		ct = getContentPane();
		JLabel l = new JLabel();
		
		try {
			//String translated = Translate.execute("�ȳ��ϼ���. �ʷϺ� �Դϴ�.", Language.KOREAN, Language.ENGLISH);
			 String translated = Translate.execute("�� �̷���", Language.ENGLISH);
			
			System.out.println(translated);
			l.setText(translated);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		ct.add(l);
		
		setTitle("Test1_4");
		setSize(1024, 705);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new test4();
	}
}

