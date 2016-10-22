

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AddCheckPanel_K extends JPanel {
	
	JLabel la_tartar, la_gingivitis, 
			la_micro_sal_Amount, la_micro_sal_Movement, la_micro_gu_Amount, la_micro_gu_Movement,
			la_snyder,
			la_malocclusion, la_odontoclasis, la_infection, la_badBreath, la_disease,
			la_brushMethod, la_periodontal, la_mobility, la_mandibular, la_aesthetic,
			la_saliva, la_consistency, la_dazzling, la_wisdomTooth_pain, 
			la_partialDenture, la_prosthesis_need, la_denture_need ;
	JComboBox info_tartar, info_gingivitis, 
			info_micro_sal_Amount, info_micro_sal_Movement, info_micro_gu_Amount, info_micro_gu_Movement,
			info_snyder,
			info_malocclusion, info_odontoclasis, info_infection, info_badBreath, info_disease,
			info_brushMethod, info_periodontal, info_mobility, info_mandibular, info_aesthetic,
			info_saliva, info_consistency, info_dazzling, info_wisdomTooth_pain,
			info_partialDenture, info_prosthesis_need, info_denture_need;
	
	int age = 0;
	PatientInfoBean_K bean = new PatientInfoBean_K();
	
	public AddCheckPanel_K(int sendAge){
		
		age = sendAge;
		
		la_tartar = new JLabel("ġ��");
		info_tartar = new JComboBox(new String[] {"����", "�ణ", "����", "����", "�ſ츹��"});
		la_gingivitis = new JLabel("ġ����");
		info_gingivitis = new JComboBox(new String[] {"����", "1����", "2����", "3����", "4�����̻�"});
		
		
		la_micro_sal_Amount = new JLabel("����, ������ ��");
		info_micro_sal_Amount = new JComboBox(new String[] {"����", "����"});
		la_micro_sal_Movement = new JLabel("����, ������ Ȱ����");
		info_micro_sal_Movement = new JComboBox(new String[] {"����", "����"});
		
		la_micro_gu_Amount = new JLabel("����, ���� ��");
		info_micro_gu_Amount = new JComboBox(new String[] {"����", "����"});
		la_micro_gu_Movement = new JLabel("����, ���� Ȱ����");
		info_micro_gu_Movement = new JComboBox(new String[] { "����", "����"});

		la_snyder = new JLabel("�����̴�");
		info_snyder = new JComboBox(new String[] {"+++", "++", "+", "-"});
		
		la_malocclusion = new JLabel("��������");
		info_malocclusion = new JComboBox(new String[] {"������", "�ణ����", "������", "��������"});
		la_odontoclasis = new JLabel("ġ������");
		info_odontoclasis = new JComboBox(new String[] {"����", "1����", "2����", "3�����̻�"});
		la_infection = new JLabel("��������");
		info_infection = new JComboBox(new String[] {"����", "1����", "2�����̻�"});
		la_badBreath = new JLabel("����");
		info_badBreath = new JComboBox(new String[] {"����", "�̾�", "�ټҽ���(ġ����)", "�ɰ�(��ġ��)"});

		la_brushMethod = new JLabel("�̴۱� ���");
		info_brushMethod = new JComboBox(new String[] {"ȸ����", "�̼���", "Ȳ����"});
		
		la_periodontal = new JLabel("ġ�ֳ�");
		info_periodontal = new JComboBox(new String[] {"����", "1����", "2����", "3����", "4����", "5����", "6�����̻�"});
		la_mobility = new JLabel("ġ�Ƶ���");
		info_mobility = new JComboBox(new String[] {"����", "1��", "2��", "3��", "4��", "5���̻�"});
		la_mandibular = new JLabel("����� �̻�");
		info_mandibular = new JComboBox(new String[] {"����", "���� �� �Ϳ��� �Ҹ�", "���� �� ����", "���� ���"});
		la_aesthetic = new JLabel("�ɹ̿���");
		info_aesthetic = new JComboBox(new String[] {"��������", "�ణ����", "���ѹ���"});
		
		la_saliva = new JLabel("Ÿ�� ��");
		info_saliva = new JComboBox(new String[] {"����", "����"});
		la_consistency = new JLabel("������");
		info_consistency = new JComboBox(new String[] {"����", "����"});
		la_dazzling = new JLabel("�ø���/������");
		info_dazzling = new JComboBox(new String[] {"����", "1����", "2����", "3�����̻�"});
		la_wisdomTooth_pain = new JLabel("����� ���� ����");
		info_wisdomTooth_pain = new JComboBox(new String[] {"���� ����", "�ټ� ����", "���� ����"});
		
		la_partialDenture = new JLabel("������ġ ��������");
		info_partialDenture = new JComboBox(new String[] {"����", "��,�� �� �ϳ� ����", "��,�� �� �� ����", "�� �ȸ���"});
		la_prosthesis_need = new JLabel("��ö��/��ġ �ʿ�");
		info_prosthesis_need = new JComboBox(new String[] {"�ʿ����", "��ö�� �ʿ� �� ����", "��ġ �ʿ� �� ����", "�ʿ��ϳ� ������"});
		la_denture_need = new JLabel("Ʋ�� �ʿ䵵");
		info_denture_need = new JComboBox(new String[] {"�ʿ����", "�ʿ��ϸ� ������", "�ʿ��ϳ� ������"});
		
	
		
		
		if(0 <= age && age < 6) {
			
			// ��ġ ����!
			this.setLayout(new GridBagLayout());
			this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_malocclusion, new GridBagConstraints(0, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_malocclusion, new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_odontoclasis, new GridBagConstraints(2, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_odontoclasis   , new GridBagConstraints(3, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_infection  , new GridBagConstraints(4, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_infection  , new GridBagConstraints(5, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
		}	
		else if (6 <= age && age < 12) {
			
			// ��ġ ����!
			this.setLayout(new GridBagLayout());
			this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_malocclusion, new GridBagConstraints(0, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_malocclusion, new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_odontoclasis, new GridBagConstraints(2, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_odontoclasis   , new GridBagConstraints(3, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_infection  , new GridBagConstraints(4, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_infection  , new GridBagConstraints(5, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_brushMethod , new GridBagConstraints(2, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_brushMethod    , new GridBagConstraints(3, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
		}
		else if (12 <= age && age < 20) {
			// ��ġ ����!
			this.setLayout(new GridBagLayout());
			this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_malocclusion, new GridBagConstraints(0, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_malocclusion, new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_odontoclasis, new GridBagConstraints(2, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_odontoclasis   , new GridBagConstraints(3, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_infection  , new GridBagConstraints(4, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_infection  , new GridBagConstraints(5, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_brushMethod , new GridBagConstraints(2, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_brushMethod    , new GridBagConstraints(3, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_periodontal   , new GridBagConstraints(0, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_periodontal   , new GridBagConstraints(1, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_mobility, new GridBagConstraints(2, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mobility    , new GridBagConstraints(3, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_mandibular , new GridBagConstraints(0, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mandibular , new GridBagConstraints(1, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_aesthetic  , new GridBagConstraints(2, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_aesthetic  , new GridBagConstraints(3, 5, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
		}
		else if (20 <= age && age < 40) {
			
			// ��ġ ����!
			this.setLayout(new GridBagLayout());
			this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_malocclusion, new GridBagConstraints(0, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_malocclusion, new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_odontoclasis, new GridBagConstraints(2, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_odontoclasis   , new GridBagConstraints(3, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_infection  , new GridBagConstraints(4, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_infection  , new GridBagConstraints(5, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_brushMethod , new GridBagConstraints(2, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_brushMethod    , new GridBagConstraints(3, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_periodontal   , new GridBagConstraints(0, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_periodontal   , new GridBagConstraints(1, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_mobility, new GridBagConstraints(2, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mobility    , new GridBagConstraints(3, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_mandibular , new GridBagConstraints(0, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mandibular , new GridBagConstraints(1, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_aesthetic  , new GridBagConstraints(2, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_aesthetic  , new GridBagConstraints(3, 5, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_saliva  , new GridBagConstraints(0, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_saliva     , new GridBagConstraints(1, 6, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_consistency    , new GridBagConstraints(2, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_consistency    , new GridBagConstraints(3, 6, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_dazzling , new GridBagConstraints(0, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_dazzling     , new GridBagConstraints(1, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_wisdomTooth_pain  , new GridBagConstraints(2, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_wisdomTooth_pain  , new GridBagConstraints(3, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			
		}
		else if (40 <= age && age < 65) {
			
			// ��ġ ����!
			this.setLayout(new GridBagLayout());
			this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_odontoclasis, new GridBagConstraints(0, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_odontoclasis   , new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_infection  , new GridBagConstraints(2, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_infection  , new GridBagConstraints(3, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_periodontal   , new GridBagConstraints(0, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_periodontal   , new GridBagConstraints(1, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_mobility, new GridBagConstraints(2, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mobility    , new GridBagConstraints(3, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_mandibular , new GridBagConstraints(0, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mandibular , new GridBagConstraints(1, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_aesthetic  , new GridBagConstraints(2, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_aesthetic  , new GridBagConstraints(3, 5, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_dazzling  , new GridBagConstraints(0, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_aesthetic  , new GridBagConstraints(1, 6, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_partialDenture  , new GridBagConstraints(0, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_partialDenture   , new GridBagConstraints(1, 7, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_prosthesis_need   , new GridBagConstraints(2, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_prosthesis_need, new GridBagConstraints(3, 7, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
		}
		else if (age > 64) {
			
			// ��ġ ����!
			this.setLayout(new GridBagLayout());
			this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			
			this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_odontoclasis, new GridBagConstraints(0, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_odontoclasis   , new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_infection  , new GridBagConstraints(2, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_infection  , new GridBagConstraints(3, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_periodontal   , new GridBagConstraints(0, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_periodontal   , new GridBagConstraints(1, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_mobility, new GridBagConstraints(2, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mobility    , new GridBagConstraints(3, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_mandibular , new GridBagConstraints(0, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_mandibular , new GridBagConstraints(1, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			this.add(la_aesthetic  , new GridBagConstraints(2, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_aesthetic  , new GridBagConstraints(3, 5, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_saliva  , new GridBagConstraints(0, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_saliva     , new GridBagConstraints(1, 6, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_consistency    , new GridBagConstraints(2, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_consistency    , new GridBagConstraints(3, 6, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
			this.add(la_partialDenture  , new GridBagConstraints(0, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_partialDenture   , new GridBagConstraints(1, 7, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			this.add(la_denture_need   , new GridBagConstraints(2, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 2), 0, 0));
			this.add(info_denture_need, new GridBagConstraints(3, 7, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
				2, 2, 2, 20), 0, 0));
			
		}
	}
		
		
		public AddCheckPanel_K(int sendAge, PatientInfoBean_K input_bean){
			
			age = sendAge;
			bean = input_bean;
			
			la_tartar = new JLabel("ġ��");
			info_tartar = new JComboBox(new String[] {"����", "�ణ", "����", "����", "�ſ츹��"});
			info_tartar.setSelectedItem(bean.getTartar());
			la_gingivitis = new JLabel("ġ����");
			info_gingivitis = new JComboBox(new String[] {"����", "1����", "2����", "3����", "4�����̻�"});
			info_gingivitis.setSelectedItem(bean.getGingivitis());
			
			la_micro_sal_Amount = new JLabel("����, ������ ��");
			info_micro_sal_Amount = new JComboBox(new String[] {"����", "����"});
			info_micro_sal_Amount.setSelectedItem(bean.getMicroscope_sal_amount());
			la_micro_sal_Movement = new JLabel("����, ������ Ȱ����");
			info_micro_sal_Movement = new JComboBox(new String[] {"����", "����"});
			info_micro_sal_Movement.setSelectedItem(bean.getMicroscope_sal_movement());
			
			la_micro_gu_Amount = new JLabel("����, ���� ��");
			info_micro_gu_Amount = new JComboBox(new String[] {"����", "����"});
			info_micro_gu_Amount.setSelectedItem(bean.getMicroscope_gu_amount());
			la_micro_gu_Movement = new JLabel("����, ���� Ȱ����");
			info_micro_gu_Movement = new JComboBox(new String[] { "����", "����"});
			info_micro_gu_Movement.setSelectedItem(bean.getMicroscope_sal_movement());

			la_snyder = new JLabel("�����̴�");
			info_snyder = new JComboBox(new String[] {"+++", "++", "+", "-"});
			info_snyder.setSelectedItem(bean.getSnyder());
			
			la_malocclusion = new JLabel("��������");
			info_malocclusion = new JComboBox(new String[] {"������", "�ణ����", "������", "��������"});
			info_malocclusion.setSelectedItem(bean.getMalocclusion());
			la_odontoclasis = new JLabel("ġ������");
			info_odontoclasis = new JComboBox(new String[] {"����", "1����", "2����", "3�����̻�"});
			info_odontoclasis.setSelectedItem(bean.getOdontoclasis());
			la_infection = new JLabel("��������");
			info_infection = new JComboBox(new String[] {"����", "1����", "2�����̻�"});
			info_infection.setSelectedItem(bean.getInfection());
			la_badBreath = new JLabel("����");
			info_badBreath = new JComboBox(new String[] {"����", "�̾�", "�ټҽ���(ġ����)", "�ɰ�(��ġ��)"});
			info_badBreath.setSelectedItem(bean.getBad_breath());

			la_brushMethod = new JLabel("�̴۱� ���");
			info_brushMethod = new JComboBox(new String[] {"ȸ����", "�̼���", "Ȳ����"});
			info_brushMethod.setSelectedItem(bean.getBrushMethod());
			
			la_periodontal = new JLabel("ġ�ֳ�");
			info_periodontal = new JComboBox(new String[] {"����", "1����", "2����", "3����", "4����", "5����", "6�����̻�"});
			info_periodontal.setSelectedItem(bean.getPeriodontal());
			la_mobility = new JLabel("ġ�Ƶ���");
			info_mobility = new JComboBox(new String[] {"����", "1��", "2��", "3��", "4��", "5���̻�"});
			info_mobility.setSelectedItem(bean.getMobility());
			la_mandibular = new JLabel("����� �̻�");
			info_mandibular = new JComboBox(new String[] {"����", "���� �� �Ϳ��� �Ҹ�", "���� �� ����", "���� ���"});
			info_mandibular.setSelectedItem(bean.getMandibular());
			la_aesthetic = new JLabel("�ɹ̿���");
			info_aesthetic = new JComboBox(new String[] {"��������", "�ణ����", "���ѹ���"});
			info_aesthetic.setSelectedItem(bean.getAesthetic());
			
			la_saliva = new JLabel("Ÿ�� ��");
			info_saliva = new JComboBox(new String[] {"����", "����"});
			info_saliva.setSelectedItem(bean.getSaliva());
			la_consistency = new JLabel("������");
			info_consistency = new JComboBox(new String[] {"����", "����"});
			info_consistency.setSelectedItem(bean.getConsistency());
			la_dazzling = new JLabel("�ø���/������");
			info_dazzling = new JComboBox(new String[] {"����", "1����", "2����", "3�����̻�"});
			info_dazzling.setSelectedItem(bean.getDazzling());
			la_wisdomTooth_pain = new JLabel("����� ���� ����");
			info_wisdomTooth_pain = new JComboBox(new String[] {"���� ����", "�ټ� ����", "���� ����"});
			info_wisdomTooth_pain.setSelectedItem(bean.getWisdomTooth_pain());
			
			la_partialDenture = new JLabel("������ġ ��������");
			info_partialDenture = new JComboBox(new String[] {"����", "��,�� �� �ϳ� ����", "��,�� �� �� ����", "�� �ȸ���"});
			info_partialDenture.setSelectedItem(bean.getPartialDenture());
			la_prosthesis_need = new JLabel("��ö��/��ġ �ʿ�");
			info_prosthesis_need = new JComboBox(new String[] {"�ʿ����", "��ö�� �ʿ� �� ����", "��ġ �ʿ� �� ����", "�ʿ��ϳ� ������"});
			info_prosthesis_need.setSelectedItem(bean.getProsthesis_need());
			la_denture_need = new JLabel("Ʋ�� �ʿ䵵");
			info_denture_need = new JComboBox(new String[] {"�ʿ����", "�ʿ��ϸ� ������", "�ʿ��ϳ� ������"});
			info_denture_need.setSelectedItem(bean.getDenture_need());
		
			
			
			if(0 <= age && age < 6) {
				
				// ��ġ ����!
				this.setLayout(new GridBagLayout());
				this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				
				this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_malocclusion, new GridBagConstraints(0, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_malocclusion, new GridBagConstraints(1, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_odontoclasis, new GridBagConstraints(2, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_odontoclasis   , new GridBagConstraints(3, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_infection  , new GridBagConstraints(4, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_infection  , new GridBagConstraints(5, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
			}	
			else if (6 <= age && age < 12) {
				
				// ��ġ ����!
				this.setLayout(new GridBagLayout());
				this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				
				this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_malocclusion, new GridBagConstraints(0, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_malocclusion, new GridBagConstraints(1, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_odontoclasis, new GridBagConstraints(2, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_odontoclasis   , new GridBagConstraints(3, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_infection  , new GridBagConstraints(4, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_infection  , new GridBagConstraints(5, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_brushMethod , new GridBagConstraints(2, 3, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_brushMethod    , new GridBagConstraints(3, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
			}
			else if (12 <= age && age < 20) {
				// ��ġ ����!
				this.setLayout(new GridBagLayout());
				this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				
				this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_malocclusion, new GridBagConstraints(0, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_malocclusion, new GridBagConstraints(1, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_odontoclasis, new GridBagConstraints(2, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_odontoclasis   , new GridBagConstraints(3, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_infection  , new GridBagConstraints(4, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_infection  , new GridBagConstraints(5, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_brushMethod , new GridBagConstraints(2, 3, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_brushMethod    , new GridBagConstraints(3, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_periodontal   , new GridBagConstraints(0, 4, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_periodontal   , new GridBagConstraints(1, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_mobility, new GridBagConstraints(2, 4, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_mobility    , new GridBagConstraints(3, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_mandibular , new GridBagConstraints(0, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_mandibular , new GridBagConstraints(1, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				this.add(la_aesthetic  , new GridBagConstraints(2, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_aesthetic  , new GridBagConstraints(3, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
			}
			else if (20 <= age && age < 40) {
				
				// ��ġ ����!
				this.setLayout(new GridBagLayout());
				this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				
				this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_malocclusion, new GridBagConstraints(0, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_malocclusion, new GridBagConstraints(1, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_odontoclasis, new GridBagConstraints(2, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_odontoclasis   , new GridBagConstraints(3, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_infection  , new GridBagConstraints(4, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_infection  , new GridBagConstraints(5, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_brushMethod , new GridBagConstraints(2, 3, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_brushMethod    , new GridBagConstraints(3, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_periodontal   , new GridBagConstraints(0, 4, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_periodontal   , new GridBagConstraints(1, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_mobility, new GridBagConstraints(2, 4, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_mobility    , new GridBagConstraints(3, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_mandibular , new GridBagConstraints(0, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_mandibular , new GridBagConstraints(1, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				this.add(la_aesthetic  , new GridBagConstraints(2, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_aesthetic  , new GridBagConstraints(3, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_saliva  , new GridBagConstraints(0, 6, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_saliva     , new GridBagConstraints(1, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_consistency    , new GridBagConstraints(2, 6, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_consistency    , new GridBagConstraints(3, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_dazzling , new GridBagConstraints(0, 7, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_dazzling     , new GridBagConstraints(1, 7, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				this.add(la_wisdomTooth_pain  , new GridBagConstraints(2, 7, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_wisdomTooth_pain  , new GridBagConstraints(3, 7, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				
				
			}
			else if (40 <= age && age < 65) {
				
				// ��ġ ����!
				this.setLayout(new GridBagLayout());
				this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				
				this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_odontoclasis, new GridBagConstraints(0, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_odontoclasis   , new GridBagConstraints(1, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_infection  , new GridBagConstraints(2, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_infection  , new GridBagConstraints(3, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_periodontal   , new GridBagConstraints(0, 4, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_periodontal   , new GridBagConstraints(1, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_mobility, new GridBagConstraints(2, 4, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_mobility    , new GridBagConstraints(3, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_mandibular , new GridBagConstraints(0, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_mandibular , new GridBagConstraints(1, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				this.add(la_aesthetic  , new GridBagConstraints(2, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_aesthetic  , new GridBagConstraints(3, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_dazzling  , new GridBagConstraints(0, 6, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_aesthetic  , new GridBagConstraints(1, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_partialDenture  , new GridBagConstraints(0, 7, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_partialDenture   , new GridBagConstraints(1, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_prosthesis_need   , new GridBagConstraints(2, 7, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_prosthesis_need, new GridBagConstraints(3, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
			}
			else if (age > 64) {
				
				// ��ġ ����!
				this.setLayout(new GridBagLayout());
				this.add(la_micro_gu_Amount , new GridBagConstraints(0, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_micro_gu_Amount , new GridBagConstraints(1, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_micro_gu_Movement  , new GridBagConstraints(2, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_micro_gu_Movement  , new GridBagConstraints(3, 0, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_snyder, new GridBagConstraints(4, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_snyder, new GridBagConstraints(5, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				
				this.add(la_tartar , new GridBagConstraints(0, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_tartar, new GridBagConstraints(1, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				this.add(la_gingivitis, new GridBagConstraints(2, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_gingivitis, new GridBagConstraints(3, 1, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_odontoclasis, new GridBagConstraints(0, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_odontoclasis   , new GridBagConstraints(1, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_infection  , new GridBagConstraints(2, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_infection  , new GridBagConstraints(3, 2, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_badBreath   , new GridBagConstraints(0, 3, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_badBreath   , new GridBagConstraints(1, 3, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_periodontal   , new GridBagConstraints(0, 4, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_periodontal   , new GridBagConstraints(1, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_mobility, new GridBagConstraints(2, 4, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_mobility    , new GridBagConstraints(3, 4, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_mandibular , new GridBagConstraints(0, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_mandibular , new GridBagConstraints(1, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 20), 0, 0));
				this.add(la_aesthetic  , new GridBagConstraints(2, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_aesthetic  , new GridBagConstraints(3, 5, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_saliva  , new GridBagConstraints(0, 6, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_saliva     , new GridBagConstraints(1, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_consistency    , new GridBagConstraints(2, 6, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_consistency    , new GridBagConstraints(3, 6, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
				this.add(la_partialDenture  , new GridBagConstraints(0, 7, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_partialDenture   , new GridBagConstraints(1, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				this.add(la_denture_need   , new GridBagConstraints(2, 7, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
				this.add(info_denture_need, new GridBagConstraints(3, 7, 1, 1, 0, 0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
					2, 2, 2, 20), 0, 0));
				
			}
		

	}

}
