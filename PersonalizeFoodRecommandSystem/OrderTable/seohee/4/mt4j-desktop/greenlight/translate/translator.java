package translate;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class translator {
	String text_after_translate;
	
	public String translating(String text_before_translate, String nation) {
		
		
		Translate.setClientId("rlatjgml38"); // ID
		Translate.setClientSecret("nOFqHOM+LLYxO9SFbV+8Sp7cplsPf6xYJoJfY6qpdsI="); // Secret
		try {
			if(nation.equals("kr"))
				text_after_translate = Translate.execute(text_before_translate, Language.KOREAN); // ������ �ѱ��� ��� �ѱ���� ����
			else if(nation.equals("cn"))
				text_after_translate = Translate.execute(text_before_translate, Language.CHINESE_SIMPLIFIED); // ������ �߱��� ��� �߱���� ����
			else if(nation.equals("jp"))
				text_after_translate = Translate.execute(text_before_translate, Language.JAPANESE); // ������ �Ϻ��� ��� �Ϻ���� ����
			else if(nation.equals("us") || nation.equals("uk"))
				text_after_translate = Translate.execute(text_before_translate, Language.ENGLISH); // ������ �����̳� �̱��� ��� �Ϻ���� ����
			else if(nation.equals("fr"))
				text_after_translate = Translate.execute(text_before_translate, Language.FRENCH); // ������ �������� ��� ��������� ����
		} catch (Exception e) {
			e.printStackTrace();
			text_after_translate = e.toString();
		}
		
		System.out.println(text_after_translate);
		return text_after_translate;
	}
}


/* import javax.swing.*;
import java.awt.*;

public class viewer extends JFrame {
	Container ct;
	viewer() {
		ct = getContentPane();
		String text_before_translate = "���ý�"; // �ѱ���� �� ��Ÿ ����
		String text_after_translate = null; // ������ ��Ÿ ����
		String nation = "uk"; // ������� ��������(������ ������ ���) �ѱ�(kr),�߱�(cn),�Ϻ�(jp),�̱�(us),����(uk),������(fr)  	
		
		JLabel label_after_translate = new JLabel(); // ȭ�鿡 ǥ�� �� ������ ������ ������ JLabel
		
		translator tr = new translator(); // �����Ⱑ �ִ� Ŭ����
		text_after_translate = tr.translating(text_before_translate, nation); // �����ϴ� �޼ҵ�
		label_after_translate.setText(text_after_translate);
		
		ct.add(label_after_translate); // ������ ������ ȭ�鿡 ���
		
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		new viewer();
	}
}
*/
