

import java.awt.TextArea;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ResultView_K extends JPanel {
	
	
	public ResultView_K(PatientInfoBean_K bean, float score_total) {
		// TODO Auto-generated constructor stub
		
		JPanel panel = new JPanel();
		

		//������
				String[][] result_age1 = {{"�������� : 2 ȸ (6�������� ���)", "�̴۱� ���� : 2 ȸ", "�Ҽҵ��� : 1 ȸ", "����Ʈ : ���� ����ġ�� ��� ����Ʈ", ""},
						{"�������� : 3 ȸ (4�������� ���)", "�̴۱� ���� : 3 ȸ", "�Ҽҵ��� : 2 ȸ", "����Ʈ : ���� ����ġ�� ��� ����Ʈ", ""},
						{"�������� : 4 ȸ (3�������� ���)", "�̴۱� ���� : 4 ȸ", "�Ҽҵ��� : 4 ȸ", "�Ҽҿ�׾�ġ : ����", "����Ʈ : ���� ����ġ�� ��� ����Ʈ"}
				};
				
				//�Ƶ�
				String[][] result_age2 = {{"�������� : 3 ȸ (4�������� ���)", "�̴۱� ���� : 3 ȸ", "�Ҽҵ��� : 1 ȸ", "����Ʈ : ���� ����ġ�� ��� ����Ʈ", ""},
						{"�������� : 4 ȸ (3�������� ���)", "�̴۱� ���� : 4 ȸ", "�Ҽҵ��� : 2 ȸ", "����Ʈ : ���� ����ġ�� ��� ����Ʈ", ""},
						{"�������� : 4 ȸ (3�������� ���)", "�̴۱� ���� : 4 ȸ", "�Ҽҵ��� : 4 ȸ", "�Ҽҿ�׾�ġ : ����", "����Ʈ : ���� ����ġ�� ��� ����Ʈ"}
				};
				
				//û�ҳ�
				String[][] result_age3 = {{"�������� : 3 ȸ (4�������� ���)", "�̴۱� ���� : 2 ȸ", "�Ҽҵ��� : 1 ȸ(��)", "����Ʈ : ���� ����ġ�� ��� ����Ʈ"},
						{"�������� : 4 ȸ (3�������� ���)", "�̴۱� ���� : 3 ȸ", "�Ҽҵ��� : 2 ȸ(6�������� ���)", "����Ʈ : ���� ����ġ�� ��� ����Ʈ", "���̸� 1 ȸ"},
						{"�������� : 6 ȸ (2�������� ���)", "�̴۱� ���� : 4 ȸ(3�������� ���)", "�Ҽҵ��� : 4 ȸ(3�������� ���)", "�Ҽҿ�׾�ġ : ����", "����Ʈ : ���� ����ġ�� ��� ����Ʈ", "���̸� : 2 ȸ(6�������� ���)"}
				};
				
				//û��
				String[][] result_age4 = {{"�������� : 3 ȸ (4�������� ���)", "�̴۱� ���� : 2 ȸ", "�Ҽҵ��� : 1 ȸ", "���̸� : 1 ȸ"},
						{"�������� : 4 ȸ (3�������� ���)", "�̴۱� ���� : 3 ȸ", "�Ҽҵ��� : 2 ȸ(6�������� ���)", "���̸� 1 ȸ"},
						{"�������� : 6 ȸ (2�������� ���)", "�̴۱� ���� : 4 ȸ(3�������� ���)", "�Ҽҵ��� : 1 ȸ", "���̸� : 1 ȸ"}
				};
				
				//���, ���
				String[][] result_age5 = {{"�������� : 2 ȸ (6�������� ���)", "�̴۱� ���� : 2 ȸ (6�������� ���)", "���̸� : 1 ȸ (��)"},
						{"�������� : 3 ȸ (4�������� ���)", "�̴۱� ���� : 3 ȸ (4�������� ���)", "���̸� : 2 ȸ (6�������� ���)"},
						{"�������� : 4 ȸ (3�������� ���)", "�̴۱� ���� : 4 ȸ (3�������� ���)", "���̸� : 3 ȸ (4�������� ���)", "�ø��� ����� �Ҽҵ���"}
				};
				
				
				
				String[] result_mal = {"����ĩ�� ���", "ġ��ĩ�� ���", "������� ��� ����", "��ġ���ڱر� ���", "������� ������"};
				
				/*
				String[][] result_age4_per = {{"�������� : 3 ȸ (4�������� ���)", "�̴۱� ���� : 2 ȸ", "�Ҽҵ��� : 1 ȸ", "���̸� : 1 ȸ", "ġ��ĩ�� ���"},
						{"�������� : 4 ȸ (3�������� ���)", "�̴۱� ���� : 3 ȸ", "�Ҽҵ��� : 2 ȸ(6�������� ���)", "���̸� 1 ȸ", "Interdental brush", "Gingival massager"},
						{"�������� : 6 ȸ (2�������� ���)", "�̴۱� ���� : 4 ȸ(3�������� ���)", "�Ҽҵ��� : 1 ȸ", "���̸� : 1 ȸ", "PMTC 2 ȸ", "Interdental brush", "Gingival massager or Rubber tip recommend", "Water pik(recommend)"}
				};
				
				String[][] result_age5_per = {{"�������� : 3 ȸ (4�������� ���)", "�̴۱� ���� : 2 ȸ (6�������� ���)", "���̸� : 1 ȸ (��)", "Interdental brush"},
						{"�������� : 4 ȸ (3�������� ���)", "�̴۱� ���� : 3 ȸ (4�������� ���)", "���̸� : 2 ȸ (6�������� ���)", "Interdental brush", "Gingival massager"},
						{"�������� : 6 ȸ (2�������� ���)", "�̴۱� ���� : 4 ȸ (3�������� ���)", "���̸� : 3 ȸ (4�������� ���)", "Interdental brush", "Rubber tip", "Superfloss", "Gingival massager", "Water pik(recommend)"}
				};
				*/
				
				String[] result_preg = {"ġ��ĩ�� ���", "�ӻ�� ���� ����", "������ġ�� ���", "���� ���� ����"};
				
				String[] result_imp = {"ġ��ĩ�� ���", "�����÷ν� ���", "ġ�������� ���", "��ġ���ڱر� ���", "������� ��� ����"};
				
				String[] result_den = {"��ġ�� ĩ��", "��ġ������"};
		
				
		int agePart;
		int age = 0;
		age = bean.getAge();
		
		//���ɱ� ����
		if(0 <= age && age < 6) //������
			agePart = 1;		
		else if (6 <= age && age < 12) //�Ƶ�
			agePart = 2;
		else if (12 <= age && age < 20) //û�ҳ�
			agePart = 3;
		else if (20 <= age && age < 40) //û��
			agePart = 4;
		else if (40 <= age && age < 65) //���
			agePart = 5;
		else if (age > 64) //���
			agePart = 6;
		else
			agePart = 7;
		
		int scorePart=0;
		if(score_total>=90)
			scorePart = 0;
		else if(score_total>=70 && score_total<90)
			scorePart = 1;
		else if(score_total<70)
			scorePart = 2;
		
		StringBuilder str = new StringBuilder("");
			
		
		switch(agePart){
		
		case 1:
			
			for(int j = 0; j<result_age1[scorePart].length; j++){
				str.append(result_age1[scorePart][j] + "\n");
			}
			break;
			
		case 2:
			for(int j = 0; j<result_age2[scorePart].length; j++){
				str.append(result_age2[scorePart][j] + "\n");
			}
			if(bean.getMalocclusion() == "������"){
				int a = 0;
				if(scorePart == 0)
					a = 0;
				else if(scorePart == 1)
					a = 1;
				else if(scorePart == 2)
					a = 2;
				
				for(int i = 0; i<a; i++){
					str.append(result_mal[i] + "\n");
				}
			}
			break;
			
		case 3:
			
			for(int i = scorePart ;  i<result_age3.length; i++){
				for(int j = 0; j<result_age3[i].length; j++){
					str.append(result_age3[i][j] + "\n");
				}
			}
			if(bean.getMalocclusion() == "������"){
				int a = 0;
				if(scorePart == 0)
					a = 1;
				else if(scorePart == 1)
					a = 2;
				else if(scorePart == 2)
					a = 4;
				
				for(int i = 0; i<a; i++){
					str.append(result_mal[i] + "\n");
				}
			}
			break;
			
		case 4:

			for(int j = 0; j<result_age4[scorePart].length; j++){
				str.append(result_age4[scorePart][j] + "\n");
			}
			if(bean.getMalocclusion() == "������"){
				int a = 0;
				if(scorePart == 0)
					a = 0;
				else if(scorePart == 1)
					a = 2;
				else if(scorePart == 2)
					a = 4;
				
				for(int i = 0; i<a; i++){
					str.append(result_mal[i] + "\n");
				}
			}
			if(bean.getPregnancy() == "�ӽ���"){
				for(int i=0; i<4; i++){
					str.append(result_preg[i] + "\n");
				}
			}
			break;
			
		case 5:
			for(int j = 0; j<result_age5[scorePart].length; j++){
				str.append(result_age5[scorePart][j] + "\n");
			}
			/*
			if(bean.getGingivitis() != "None"){
				str = null;
				for(int j = 0; j<result_age5_per[scorePart].length; j++){
					str.append(result_age5_per[scorePart][j] + "\n");
				}
			}
			*/
			if(bean.getProsthesis_need() == "��ö�� �ʿ� �� ����" || bean.getProsthesis_need() == "��ġ �ʿ� �� ����" || bean.getDenture_need() == "�ʿ��ϸ� ������"){
				int a = 0;
				if(scorePart == 0)
					a = 0;
				else if(scorePart == 1)
					a = 2;
				else if(scorePart == 2)
					a = 4;
				
				for(int i = 0; i<a; i++){
					str.append(result_imp[i] + "\n");
				}
			}
			if(bean.getPartialDenture() != "����" ){
				for(int i = 0; i<2; i++){
					str.append(result_den[i] + "\n");
				}
			}
			break;
			
		case 6:
			for(int j = 0; j<result_age5[scorePart].length; j++){
				str.append(result_age5[scorePart][j] + "\n");
			}
			/*
			if(bean.getGingivitis() != "None"){
				str = null;
				for(int j = 0; j<result_age5_per[scorePart].length; j++){
					str.append(result_age5_per[scorePart][j] + "\n");
				}
			}
			*/
			if(bean.getProsthesis_need() == "��ö�� �ʿ� �� ����" || bean.getProsthesis_need() == "��ġ �ʿ� �� ����" || bean.getDenture_need() == "�ʿ��ϸ� ������"){
				int a = 0;
				if(scorePart == 0)
					a = 0;
				else if(scorePart == 1)
					a = 2;
				else if(scorePart == 2)
					a = 4;
				
				for(int i = 0; i<a; i++){
					str.append(result_imp[i] + "\n");
				}
			}
			if(bean.getPartialDenture() != "����" ){
				for(int i = 0; i<2; i++){
					str.append(result_den[i] + "\n");
				}
			}
			break;
		}
		TextArea view = new TextArea("", 10, 30, TextArea.SCROLLBARS_NONE);
		view.setEditable(false);
		view.setText(str.toString());
		panel.add(view);
		this.add(panel);
		
	}
}
