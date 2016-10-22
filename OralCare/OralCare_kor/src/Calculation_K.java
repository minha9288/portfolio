
import javax.swing.JPanel;


public class Calculation_K extends JPanel{
	
	public float[] Calculation_K(PatientInfoBean_K bean){
		
		float score_toothNumber_basic = 0;
		float score_toothNumber = 0; //ġ�� �� ����
		float score_dentalCaries_basic = 0;
		float score_dentalCaries = 0; //ġ�ƿ�� ����
		float score_periodontalStatus_basic = 0;
		float score_periodontalStatus = 0; //ġ�ֻ��� ����
		float score_otherMouthStatus_basic = 0;
		float score_otherMouthStatus = 0; //��Ÿ�������� ����
		float score_mouthHabit_basic = 0;
		float score_mouthHabit = 0; //������������ ����
		float score_total = 0;
		
		
		//PatientInfoBean bean = new PatientInfoBean();
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
		
		System.out.println("agePart : " + agePart);
		
		int x=0, y=0;
		switch(agePart){
		case 1: //������
			x=0; y=0;
			
			/*ġ�� �� ����*/
			score_toothNumber = 20;
			score_toothNumber_basic = 20;
			score_toothNumber = bean.getBabyTooth();
			if(score_toothNumber >= 20)
				score_toothNumber = 20;
			
			/*ġ�ƿ�� ����*/
			score_dentalCaries = 40;
			score_dentalCaries_basic = 40;
			
			//��� ����
			score_dentalCaries -= bean.getLeaving();
			if(score_dentalCaries <= 28)
				score_dentalCaries = 28;
			
			if(bean.getTreatment() >= 16)
				score_dentalCaries = (float) (score_dentalCaries - 8);
			else
				score_dentalCaries = (float) (score_dentalCaries - (bean.getTreatment() * 0.5));
			
			//��� �߻� ���ɼ� ���� ����
			if(bean.getSulcus() >= 10)
				score_dentalCaries -= 10;
			else
				score_dentalCaries -= bean.getSulcus();
			
			if(bean.getPlaque_score() == "���� ����")
				score_dentalCaries -= 0;
			else if(bean.getPlaque_score() == "����")
				score_dentalCaries -= 1;
			else if(bean.getPlaque_score() == "����")
				score_dentalCaries -= 2;
			else if(bean.getPlaque_score() == "����")
				score_dentalCaries -= 3;
			else if(bean.getPlaque_score() == "�ſ츹��")
				score_dentalCaries -= 4;
			
			if(bean.getSugar_frequency() == "������")
				score_dentalCaries -= 0;
			else if (bean.getSugar_frequency() == "1ȸ ����")
				score_dentalCaries -= 1;
			else if (bean.getSugar_frequency() == "2ȸ ����")
				score_dentalCaries -= 2;
			else if (bean.getSugar_frequency() == "3ȸ ����")
				score_dentalCaries -= 3;
			else if (bean.getSugar_frequency() == "4ȸ �̻�")
				score_dentalCaries -= 4;
			
			
			if(bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				x -= 2;
			else if ((bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
					|| (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����"))
				x -= 1;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				x -= 0;
			
			if(bean.getSnyder() == "+++")
				y -= 2;
			else if (bean.getSnyder() == "++" || bean.getSnyder()=="+")
				y -= 1;
			else if (bean.getSnyder() == "-")
				y -= 0;
			
			if(x<=y)
				score_dentalCaries -= x;
			else
				score_dentalCaries -= y;
			
			
			
			/*ġ�ֻ��� ����*/
			score_periodontalStatus = 10;
			score_periodontalStatus_basic = 10;
			
			//ġ�鼼�ո�
			if(bean.getPlaque_score() == "���Ǿ���")
				score_periodontalStatus -= 0;
			else if(bean.getPlaque_score() == "����")
				score_periodontalStatus -= 1;
			else if(bean.getPlaque_score() == "����")
				score_periodontalStatus -= 2;
			else if(bean.getPlaque_score() == "����")
				score_periodontalStatus -= 3;
			else if(bean.getPlaque_score() == "�ſ� ����")
				score_periodontalStatus -= 4;
			
			//ġ��
			if(bean.getTartar() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getTartar() == "�ణ")
				score_periodontalStatus -= 1;
			else if(bean.getTartar() == "����")
				score_periodontalStatus -= 2;
			else if(bean.getTartar() == "����")
				score_periodontalStatus -= 3;
			else if(bean.getTartar() == "�ſ츹��")
				score_periodontalStatus -= 3;
			
			//ġ����
			if(bean.getGingivitis() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getGingivitis() == "1����")
				score_periodontalStatus -= 1;
			else if(bean.getGingivitis() == "2����")
				score_periodontalStatus -= 2;
			else if(bean.getGingivitis() == "3����")
				score_periodontalStatus -= 3;
			else if(bean.getGingivitis() == "4�����̻�")
				score_periodontalStatus -= 3;
			
			
			/*��Ÿ�������� ����*/
			score_otherMouthStatus = 12;
			score_otherMouthStatus_basic = 12;
			
			//��������
			if(bean.getMalocclusion() == "������")
				score_otherMouthStatus -= 0;
			else if(bean.getMalocclusion() == "�ణ����" || bean.getMalocclusion() == "������")
				score_otherMouthStatus -= 1;
			else if(bean.getMalocclusion() == "��������")
				score_otherMouthStatus -= 2;
			
			//ġ������,ȸ��
			if(bean.getOdontoclasis() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getOdontoclasis() == "1����")
				score_otherMouthStatus -= 1;
			else if(bean.getOdontoclasis() == "2����")
				score_otherMouthStatus -= 2;
			
			//��������
			if(bean.getInfection() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getInfection() == "1����")
				score_otherMouthStatus -= 1;
			else if(bean.getInfection() == "2�����̻�")
				score_otherMouthStatus -= 2;
			
			//����
			if(bean.getBad_breath() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getBad_breath() == "�̾�" || bean.getBad_breath() == "�ټҽ���(ġ����)")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "�ɰ�(��ġ��)")
				score_otherMouthStatus -= 2;
			
			//������ȯ
//			if(bean.getDisease_num() == "None")
//				score_otherMouthStatus -= 0;
//			else if(bean.getDisease_num() == "�̾�(On treatment)")
//				score_otherMouthStatus -= 2;
//			else if(bean.getDisease_num() == "�ɰ�(��ġ��)")
//				score_otherMouthStatus -= 4;
			
			
			/*������������ ����*/
			score_mouthHabit = 18;
			score_mouthHabit_basic = 18;
			
			//�̴۱� ����
			if(bean.getBrush_num() == "3ȸ")
				score_mouthHabit -= 0;
			else if(bean.getBrush_num() == "2ȸ")
				score_mouthHabit -= 2;
			else if(bean.getBrush_num() == "1ȸ")
				score_mouthHabit -= 4;
			else if(bean.getBrush_num() == "�ȴ۾���")
				score_mouthHabit -= 6;
				
			//�� ���� ��
			if(bean.getSugar_frequency() == "�ȴ۾���")
				score_mouthHabit -= 0;
			else if(bean.getSugar_frequency() == "1ȸ ����")
				score_mouthHabit -= 2;
			else if(bean.getSugar_frequency() == "2ȸ ����")
				score_mouthHabit -= 4;
			else if(bean.getSugar_frequency() == "3ȸ ����")
				score_mouthHabit -= 6;
			else if(bean.getSugar_frequency() == "4ȸ �̻�")
				score_mouthHabit -= 6;
			
			//ġ������ ���
			if(bean.getChew_food() == "�κ�, ��")
				score_mouthHabit -= 4;
			else if(bean.getChew_food() == "��, ���")
				score_mouthHabit -= 3;
			else if(bean.getChew_food() == "��ġ, ���")
				score_mouthHabit -= 2;
			else if(bean.getChew_food() == "���(����)")
				score_mouthHabit -= 1;
			else if(bean.getChew_food() == "������¡��, ����")
				score_mouthHabit -= 0;
			
			//ġ�� �湮
			if(bean.getVisit() == "2ȸ �̻�")
				score_mouthHabit -= 0;
			else if(bean.getVisit() == "1ȸ")
				score_mouthHabit -= 1;
			else if(bean.getVisit() == "�� �� ����")
				score_mouthHabit -= 2;
			
			//���� �ջ�
			score_total = score_toothNumber + score_dentalCaries + score_periodontalStatus 
					+ score_otherMouthStatus + score_mouthHabit; 
			
			System.out.println("score_toothNumber : " + score_toothNumber);
			System.out.println("score_dentalCaries : " + score_dentalCaries);
			System.out.println("score_periodontalStatus : " + score_periodontalStatus);
			System.out.println("score_otherMouthStatus : " + score_otherMouthStatus);
			System.out.println("score_mouthHabit : " + score_mouthHabit);
			System.out.println("score_total : " + score_total);
			
			break;
			
			
		case 2: //�Ƶ�
			x=0; y=0;
			
			/*ġ�� �� ����*/
			score_toothNumber = 20;
			score_toothNumber_basic = 20;
			if(bean.getBabyTooth() >= 20)
				score_toothNumber = 0;
			else
				score_toothNumber -= (bean.getBabyTooth() + bean.getLosePermanentTooth_back() + bean.getLosePermanentTooth_front());
			
			/*ġ�ƿ�� ����*/
			score_dentalCaries = 32;
			score_dentalCaries_basic = 32;
			//��� ����
			if(bean.getLeaving() >= 12)
				score_dentalCaries -= 12;
			else
				score_dentalCaries -= bean.getLeaving();
			
			if(bean.getTreatment() >= 16)
				score_dentalCaries -= 8;
			else
				score_dentalCaries = (float) (score_dentalCaries - (bean.getTreatment() * 0.5));
			
			//��� �߻� ���ɼ� ���� ����
			if(bean.getSulcus() == 0)
				score_dentalCaries -= 0;
			else if(bean.getSulcus() == 1)
				score_dentalCaries -= 1;
			else if(bean.getSulcus() == 2)
				score_dentalCaries -= 2;
			else if(bean.getSulcus() >= 3)
				score_dentalCaries -= 3;

			if(bean.getSugar_frequency() == "������")
				score_dentalCaries -= 0;
			else if (bean.getSugar_frequency() == "1ȸ ����")
				score_dentalCaries -= 0.5;
			else if (bean.getSugar_frequency() == "2ȸ ����")
				score_dentalCaries -= 1;
			else if (bean.getSugar_frequency() == "3ȸ ����")
				score_dentalCaries -= 1.5;
			else if (bean.getSugar_frequency() == "4ȸ �̻�")
				score_dentalCaries -= 2;
			
			if(bean.getPlaque_score() == "���Ǿ���" || bean.getPlaque_score() == "����")
				score_dentalCaries -= 0;
			else if(bean.getPlaque_score() == "����")
				score_dentalCaries -= 1;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "�ſ� ����")
				score_dentalCaries -= 2;
			
			
			
			if(bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				x -= 3;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				x -= 2;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				x -= 1;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				x -= 0;
			
			if(bean.getSnyder() == "+++")
				y -= 3;
			else if (bean.getSnyder() == "++")
				y -= 2;
			else if (bean.getSnyder() == "+")
				y -= 1;
			else if (bean.getSnyder() == "-")
				y -= 0;
			
			if(x<=y)
				score_dentalCaries -= x;
			else
				score_dentalCaries -= y;
			
			if( bean.getBrush_num() == "2ȸ �̻�")
				score_dentalCaries -= 0;
			else if(bean.getBrush_num() == "1ȸ")
				score_dentalCaries -= 1;
			else if(bean.getBrush_num() == "�ȴ۾���")
				score_dentalCaries -= 2;
			
			
			/*ġ�ֻ��� ����*/
			score_periodontalStatus = 10;
			score_periodontalStatus_basic = 10;
			
			//ġ�鼼�ո�
			if(bean.getPlaque_score() == "���Ǿ���" || bean.getPlaque_score() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getPlaque_score() == "����")
				score_periodontalStatus -= 1;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "�ſ� ����")
				score_periodontalStatus -= 2;
			
			//ġ��
			if(bean.getTartar() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getTartar() == "�ణ" || bean.getTartar() == "����" || bean.getTartar() == "����" || bean.getTartar() == "�ſ츹��")
				score_periodontalStatus -= 1;
			
			//ġ����
			if(bean.getGingivitis() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getGingivitis() == "1����")
				score_periodontalStatus -= 1;
			else if(bean.getGingivitis() == "2����" || bean.getGingivitis() == "3����" || bean.getGingivitis() == "4�����̻�")
				score_periodontalStatus -= 2;
			
			if(bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus -= 3;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus -= 2;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus -= 1;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus -= 0;
			
			
			if(bean.getBrush_num() == "2ȸ �̻�")
				score_periodontalStatus -= 0;
			else if(bean.getBrush_num() == "1ȸ")
				score_periodontalStatus -= 1;
			else if(bean.getBrush_num() == "�ȴ۾���")
				score_periodontalStatus -= 2;
			
			
			
			/*��Ÿ�������� ����*/
			score_otherMouthStatus = 18;
			score_otherMouthStatus_basic = 18;
			
			//��������
			if(bean.getMalocclusion() == "������")
				score_otherMouthStatus -= 0;
			else if(bean.getMalocclusion() == "�ణ����")
				score_otherMouthStatus -= 1;
			else if(bean.getMalocclusion() == "������")
				score_otherMouthStatus -= 2;
			else if(bean.getMalocclusion() == "��������")
				score_otherMouthStatus -= 3;
			
			//ġ������,ȸ��
			if(bean.getOdontoclasis() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getOdontoclasis() == "1����")
				score_otherMouthStatus -= 2;
			else if(bean.getOdontoclasis() == "2����")
				score_otherMouthStatus -= 4;
			else if(bean.getOdontoclasis() == "3�����̻�")
				score_otherMouthStatus -= 6;
			
			//��������
			if(bean.getInfection() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getInfection() == "1����")
				score_otherMouthStatus -= 2;
			else if(bean.getInfection() == "2�����̻�")
				score_otherMouthStatus -= 4;
			
			//����
			if(bean.getBad_breath() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getBad_breath() == "�̾�")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "�ټҽ���(ġ����)")
				score_otherMouthStatus -= 2;
			else if(bean.getBad_breath() == "�ɰ�(��ġ��)")
				score_otherMouthStatus -= 3;
			
			//������ȯ
			if(bean.getDisease_num() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getDisease_num() == "������ ġ����")
				score_otherMouthStatus -= 1;
			else if(bean.getDisease_num() == "������ �� ġ��")
				score_otherMouthStatus -= 2;
			
			
			/*������������ ����*/
			score_mouthHabit = 20;
			score_mouthHabit_basic = 20;
			
			//�̴۱� ����
			if(bean.getBrush_num() == "2ȸ �̻�")
				score_mouthHabit -= 0;
			else if(bean.getBrush_num() == "1ȸ")
				score_mouthHabit -= 1;
			else if(bean.getBrush_num() == "�ȴ۾���")
				score_mouthHabit -= 2;
			
			//�̴۱� ���
			if(bean.getBrushMethod() == "ȸ����")
				score_mouthHabit -= 0;
			else if(bean.getBrushMethod() == "�̼���")
				score_mouthHabit -= 1;
			else if(bean.getBrushMethod() == "Ȳ����")
				score_mouthHabit -= 2;
			
			//�̴۱� �ñ�
			if(bean.getBrush_time() == "�ַ� �� ��")
				score_mouthHabit -= 0;
			else if(bean.getBrush_time() == "���� ����, ���� ����")
				score_mouthHabit -= 1;
			else if(bean.getBrush_time() == "�ַ� �� ��")
				score_mouthHabit -= 2;
				
				
			//�� ���� ��
			if(bean.getSugar_frequency() == "������")
				score_mouthHabit -= 0;
			else if(bean.getSugar_frequency() == "1ȸ ����")
				score_mouthHabit -= 0.5;
			else if(bean.getSugar_frequency() == "2ȸ ����")
				score_mouthHabit -= 1;
			else if(bean.getSugar_frequency() == "3ȸ ����")
				score_mouthHabit -= 1.5;
			else if(bean.getSugar_frequency() == "4ȸ �̻�")
				score_mouthHabit -= 2;
			
			//ġ������ ���
			if(bean.getChew_food() == "�κ�, ��")
				score_mouthHabit -= 4;
			else if(bean.getChew_food() == "��, ���")
				score_mouthHabit -= 3;
			else if(bean.getChew_food() == "��ġ, ���")
				score_mouthHabit -= 2;
			else if(bean.getChew_food() == "���(����)")
				score_mouthHabit -= 1;
			else if(bean.getChew_food() == "������¡��, ����")
				score_mouthHabit -= 0;
			
			//ġ�� �湮
			if(bean.getCare_product() == "2���� �̻� ���")
				score_mouthHabit -= 0;
			else if(bean.getCare_product() == "1���� ���")
				score_mouthHabit -= 1;
			else if(bean.getCare_product() == "������� ����")
				score_mouthHabit -= 2;
			
			//������ȯ
			if(bean.getDisease_kind() == "����")
				score_mouthHabit -= 0;
			else if(bean.getDisease_kind() == "�ټ� ����(��)")
				score_mouthHabit -= 1;
			else if(bean.getDisease_kind() == "�ɰ��� ����(�索)")
				score_mouthHabit -= 2;
			
			//���� �ջ�
			score_total = score_toothNumber + score_dentalCaries + score_periodontalStatus 
					+ score_otherMouthStatus + score_mouthHabit; 
			
			System.out.println("score_toothNumber : " + score_toothNumber);
			System.out.println("score_dentalCaries : " + score_dentalCaries);
			System.out.println("score_periodontalStatus : " + score_periodontalStatus);
			System.out.println("score_otherMouthStatus : " + score_otherMouthStatus);
			System.out.println("score_mouthHabit : " + score_mouthHabit);
			System.out.println("score_total : " + score_total);
			
			break;
			
			
		case 3: //û�ҳ�
			x=0; y=0;
			
			/*ġ�� �� ����*/
			score_toothNumber = 20;
			score_toothNumber_basic = 20;
			score_toothNumber -= ((bean.getLosePermanentTooth_back() * 5) + (bean.getLosePermanentTooth_front() * 4));
			if(score_toothNumber <= 0)
				score_toothNumber = 0;
			
			/*ġ�ƿ�� ����*/
			score_dentalCaries = 35;
			score_dentalCaries_basic = 35;
			
			//��� ����
			if(bean.getLeaving() >= 12)
				score_dentalCaries -= 12;
			else
				score_dentalCaries -= bean.getLeaving();
			
			if(bean.getTreatment() >= 16)
				score_dentalCaries -= 8;
			else
				score_dentalCaries = (float) (score_dentalCaries - (bean.getTreatment() * 0.5));
			
			//��� �߻� ���ɼ� ���� ����
			if(bean.getSulcus() == 0)
				score_dentalCaries -= 0;
			else if(bean.getSulcus() == 1)
				score_dentalCaries -= 1;
			else if(bean.getSulcus() >= 2)
				score_dentalCaries -= 2;

			if(bean.getPlaque_score() == "���Ǿ���")
				score_dentalCaries -= 0;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "����")
				score_dentalCaries -= 1;
			else if(bean.getPlaque_score() == "����")
				score_dentalCaries -= 2;
			else if(bean.getPlaque_score() == "�ſ� ����")
				score_dentalCaries -= 3;
			
			if(bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				x -= 3;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				x -= 2;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				x -= 1;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				x -= 0;
			
			if(bean.getSnyder() == "+++")
				y -= 3;
			else if (bean.getSnyder() == "++")
				y -= 2;
			else if (bean.getSnyder() == "+")
				y -= 1;
			else if (bean.getSnyder() == "-")
				y -= 0;
			
			if(x<=y)
				score_dentalCaries -= x;
			else
				score_dentalCaries -= y;
			
			if( bean.getBrush_num() == "2ȸ �̻�")
				score_dentalCaries -= 0;
			else if(bean.getBrush_num() == "1ȸ")
				score_dentalCaries -= 1;
			else if(bean.getBrush_num() == "�ȴ۾���")
				score_dentalCaries -= 2;
			
			//�̴۱� ���
			if(bean.getBrushMethod() == "ȸ����")
				score_dentalCaries -= 0;
			else if(bean.getBrushMethod() == "�̼���")
				score_dentalCaries -= 1;
			else if(bean.getBrushMethod() == "Ȳ����")
				score_dentalCaries -= 2;
			
			//�� ���� ��
			if(bean.getSugar_frequency() == "������")
				score_dentalCaries -= 0;
			else if(bean.getSugar_frequency() == "1ȸ ����")
				score_dentalCaries -= 1;
			else if(bean.getSugar_frequency() == "2ȸ ����")
				score_dentalCaries -= 2;
			else if(bean.getSugar_frequency() == "3ȸ ����")
				score_dentalCaries -= 3;
			
			
			/*ġ�ֻ��� ����*/
			score_periodontalStatus = 12;
			score_periodontalStatus_basic = 12;
			
			//ġ�鼼�ո�
			if(bean.getPlaque_score() == "���Ǿ���")
				score_periodontalStatus -= 0;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "����")
				score_periodontalStatus -= 1;
			else if(bean.getPlaque_score() == "����")
				score_periodontalStatus -= 2;
			else if(bean.getPlaque_score() == "�ſ� ����")
				score_periodontalStatus -= 3;
			
			//ġ��
			if(bean.getTartar() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getTartar() == "�ణ" || bean.getTartar() == "����")
				score_periodontalStatus -= 1;
			else if(bean.getTartar() == "����" || bean.getTartar() == "�ſ츹��")
				score_periodontalStatus -= 2;
			
			//ġ�ֳ�
			if(bean.getPeriodontal() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getPeriodontal() == "1����")
				score_periodontalStatus -= 1;
			else if(bean.getPeriodontal() == "2����")
				score_periodontalStatus -= 2;
			else if(bean.getPeriodontal() == "3����")
				score_periodontalStatus -= 3;
			else if(bean.getPeriodontal() == "4����" || bean.getPeriodontal() == "5����" || bean.getPeriodontal() == "6�����̻�")
				score_periodontalStatus -= 4;
			
			
			if(bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				x = -2;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����"
					|| bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				x = -1;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				x = 0;
			
			if(bean.getGingivitis() == "����")
				y = 0;
			else if(bean.getGingivitis() == "1����" || bean.getGingivitis() == "2����")
				y = -1;
			else if(bean.getGingivitis() == "3����" || bean.getGingivitis() == "4�����̻�")
				y = -2;
			
			if(x<=y)
				score_periodontalStatus -= x;
			else
				score_periodontalStatus -= y;
			
			x=0; y=0;
			//ġ�Ƶ��� �Ǵ� ������ȯ
			if(bean.getMobility() == "����")
				x = 0;
			else if(bean.getMobility() == "1��" || bean.getMobility() == "2��" || bean.getMobility() == "3��" || bean.getMobility() == "4��" || bean.getMobility() == "5���̻�")
				x = -1;
			
			if(bean.getDisease_kind() == "����")
				y = 0;
			else if(bean.getDisease_kind() == "�ټ� ����(��)" || bean.getDisease_kind() == "�ɰ��� ����(�索)")
				y = -1;
			if(x<=y)
				score_periodontalStatus -= x;
			else
				score_periodontalStatus -= y;
			
			
			
			
			/*��Ÿ�������� ����*/
			score_otherMouthStatus = 15;
			score_otherMouthStatus_basic = 15;
			
			//��������
			if(bean.getMalocclusion() == "������")
				score_otherMouthStatus -= 0;
			else if(bean.getMalocclusion() == "�ణ����")
				score_otherMouthStatus -= 1;
			else if(bean.getMalocclusion() == "������")
				score_otherMouthStatus -= 2;
			else if(bean.getMalocclusion() == "��������")
				score_otherMouthStatus -= 3;
			
			//����� �̻�
			if(bean.getMandibular() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getMandibular() == "���� �� �Ϳ��� �Ҹ�")
				score_otherMouthStatus -= 1;
			else if(bean.getMandibular() == "���� �� ����")
				score_otherMouthStatus -= 2;
			else if(bean.getMandibular() == "���� ���")
				score_otherMouthStatus -= 3;
			
			//ġ������,ȸ��
			if(bean.getOdontoclasis() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getOdontoclasis() == "1����")
				score_otherMouthStatus -= 1;
			else if(bean.getOdontoclasis() == "2����" || bean.getOdontoclasis() == "3�����̻�")
				score_otherMouthStatus -= 2;
			
			//����
			if(bean.getBad_breath() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getBad_breath() == "�̾�")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "�ټҽ���(ġ����)")
				score_otherMouthStatus -= 2;
			else if(bean.getBad_breath() == "�ɰ�(��ġ��)")
				score_otherMouthStatus -= 3;
			
			//��������
			if(bean.getInfection() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getInfection() == "1����")
				score_otherMouthStatus -= 1;
			else if(bean.getInfection() == "2�����̻�")
				score_otherMouthStatus -= 2;
			
			//�ɹ̿���
			if(bean.getAesthetic() == "��������")
				score_otherMouthStatus -= 0;
			else if(bean.getAesthetic() == "�ణ����")
				score_otherMouthStatus -= 1;
			else if(bean.getAesthetic() == "���ѹ���")
				score_otherMouthStatus -= 2;
			
			
			/*������������ ����*/
			score_mouthHabit = 18;
			score_mouthHabit_basic = 18;
			
			//ġ�鼼�ո�
			if(bean.getPlaque_score() == "���Ǿ���")
				score_mouthHabit -= 0;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "����")
				score_mouthHabit -= 1;
			else if(bean.getPlaque_score() == "����")
				score_mouthHabit -= 2;
			else if(bean.getPlaque_score() == "�ſ� ����")
				score_mouthHabit -= 3;			

			//�ܰ� ����
			if(bean.getSugar_frequency() == "None")
				score_mouthHabit -= 0;
			else if(bean.getSugar_frequency() == "1ȸ ����")
				score_mouthHabit -= 0.5;
			else if(bean.getSugar_frequency() == "2ȸ ����")
				score_mouthHabit -= 1;
			else if(bean.getSugar_frequency() == "3ȸ ����")
				score_mouthHabit -= 1.5;
			else if(bean.getSugar_frequency() == "4 times or more")
				score_mouthHabit -= 2;
			
			//�̴۱� ����
			if(bean.getBrush_num() == "2ȸ �̻�")
				score_mouthHabit -= 0;
			else if(bean.getBrush_num() == "1ȸ")
				score_mouthHabit -= 1;
			else if(bean.getBrush_num() == "�ȴ۾���")
				score_mouthHabit -= 2;
			
			//�̴۱� ���
			if(bean.getBrushMethod() == "ȸ����")
				score_mouthHabit -= 0;
			else if(bean.getBrushMethod() == "�̼���")
				score_mouthHabit -= 1;
			else if(bean.getBrushMethod() == "Ȳ����")
				score_mouthHabit -= 2;
			
			//�������� ����
			if(bean.getPrevention_visit() == "3 things or more")
				score_mouthHabit -= 0;
			else if(bean.getPrevention_visit() == "2 things")
				score_mouthHabit -= 1;
			else if(bean.getPrevention_visit() == "1 thing")
				score_mouthHabit -= 2;
			else if(bean.getPrevention_visit() == "None")
				score_mouthHabit -= 3;
			
			//���� ġ�� �湮
			if(bean.getVisit() == "����")
				score_mouthHabit -= 0;
			else if(bean.getVisit() == "����")
				score_mouthHabit -= 1;
			
			//�������Ǳ��� ����
			if(bean.getLearn() == "Have")
				score_mouthHabit -= 0;
			else if(bean.getLearn() == "None")
				score_mouthHabit -= 1;
			
			//������ȯ
			if(bean.getDisease_kind() == "����")
				score_mouthHabit -= 0;
			else if(bean.getDisease_kind() == "�ټ� ����(��)")
				score_mouthHabit -= 1;
			else if(bean.getDisease_kind() == "�ɰ��� ����(�索)")
				score_mouthHabit -= 2;
			
			//���� �ջ�
			score_total = score_toothNumber + score_dentalCaries + score_periodontalStatus 
					+ score_otherMouthStatus + score_mouthHabit; 
			
			System.out.println("score_toothNumber : " + score_toothNumber);
			System.out.println("score_dentalCaries : " + score_dentalCaries);
			System.out.println("score_periodontalStatus : " + score_periodontalStatus);
			System.out.println("score_otherMouthStatus : " + score_otherMouthStatus);
			System.out.println("score_mouthHabit : " + score_mouthHabit);
			System.out.println("score_total : " + score_total);
			
			break;


		case 4: //û��

			/*ġ�� �� ����*/
			score_toothNumber = 15;
			score_toothNumber_basic = 15;
		
			if(bean.getPermanentTooth() >= 28)
				score_toothNumber -= 0;
			else if(bean.getPermanentTooth() == 27)
				score_toothNumber -= 2;
			else if(bean.getPermanentTooth() == 26)
				score_toothNumber -= 3.5;
			else if(bean.getPermanentTooth() == 25)
				score_toothNumber -= 5;
			else if(bean.getPermanentTooth() == 24)
				score_toothNumber -= 6;
			else if(bean.getPermanentTooth() == 23)
				score_toothNumber -= 7;
			else if(bean.getPermanentTooth() == 22)
				score_toothNumber -= 8;
			else if(bean.getPermanentTooth() == 21)
				score_toothNumber -= 9;
			else if(bean.getPermanentTooth() == 20)
				score_toothNumber -= 10;
			else if(bean.getPermanentTooth() == 19)
				score_toothNumber -= 11;
			else if(bean.getPermanentTooth() == 18)
				score_toothNumber -= 12;
			else if(bean.getPermanentTooth() == 17)
				score_toothNumber -= 12.5;
			else if(bean.getPermanentTooth() == 16)
				score_toothNumber -= 13;
			else if(bean.getPermanentTooth() == 15)
				score_toothNumber -= 13.5;
			else if(bean.getPermanentTooth() == 14)
				score_toothNumber -= 14;
			else if(bean.getPermanentTooth() <= 13 || bean.getPermanentTooth() >= 11)
				score_toothNumber -= 14.5;
			else if(bean.getPermanentTooth() <= 10)
				score_toothNumber -= 15;
			
			score_toothNumber += (bean.getImplant() * 0.5);
			score_toothNumber += (bean.getDentures() * 0.3);
			
			if(score_toothNumber>=15)
				score_toothNumber = 15;
			
			
			/*ġ�ƿ�� ����*/
			score_dentalCaries = 30;
			score_dentalCaries_basic = 30;
			
			//��� ����
			if(bean.getLeaving() >= 12)
				score_dentalCaries -= 12;
			else
				score_dentalCaries -= bean.getLeaving();
			
			if(bean.getTreatment() >= 16)
				score_dentalCaries -= 8;
			else
				score_dentalCaries = (float) (score_dentalCaries - (bean.getTreatment() * 0.5));
			
			//��� �߻� ���ɼ� ���� ����
			if(bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_dentalCaries -= 2;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����"
					|| bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_dentalCaries -= 1;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_dentalCaries -= 0;			

			if(bean.getPlaque_score() == "���Ǿ���")
				score_dentalCaries -= 0;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "����")
				score_dentalCaries -= 1;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "�ſ� ����")
				score_dentalCaries -= 2;
			
			if(bean.getSaliva()=="����" && bean.getConsistency() == "����")
				score_dentalCaries -= 0;
			else if(bean.getSaliva() == "����" && bean.getConsistency() == "����")
				score_dentalCaries -= 1;
			else if(bean.getSaliva() == "����" && bean.getConsistency() == "����")
				score_dentalCaries -= 2;
			else if(bean.getSaliva() == "����" && bean.getConsistency() == "����")
				score_dentalCaries -= 3;
			
			if(bean.getXerostomia() == "Slight" || bean.getXerostomia() == "Severe")
				score_dentalCaries -= 4;
			
			if(bean.getSugar_frequency() == "None")
				score_dentalCaries -= 0;
			else if(bean.getSugar_frequency() == "1ȸ ����")
				score_dentalCaries -= 1;
			else if(bean.getSugar_frequency() == "2 times or more")
				score_dentalCaries -= 2;
			
			
			/*ġ�ֻ��� ����*/
			score_periodontalStatus = 20;
			score_periodontalStatus_basic = 20;
			
			//ġ�ֳ�
			if(bean.getPeriodontal() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getPeriodontal() == "1����")
				score_periodontalStatus -= 1;
			else if(bean.getPeriodontal() == "2����")
				score_periodontalStatus -= 2;
			else if(bean.getPeriodontal() == "3����")
				score_periodontalStatus -= 3;
			else if(bean.getPeriodontal() == "4����" || bean.getPeriodontal() == "5����" || bean.getPeriodontal() == "6�����̻�")
				score_periodontalStatus -= 3;
			
			//ġ�Ƶ��� 
			if(bean.getMobility() == "����")
				score_periodontalStatus = 0;
			else if(bean.getMobility() == "1��")
				score_periodontalStatus = -1;
			else if(bean.getMobility() == "2��")
				score_periodontalStatus = -2;
			else if(bean.getMobility() == "3��" || bean.getMobility() == "4��" || bean.getMobility() == "5���̻�")
				score_periodontalStatus = -3;
			
			//ġ�鼼�ո�
			if(bean.getPlaque_score() == "���Ǿ���")
				score_periodontalStatus -= 0;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "����")
				score_periodontalStatus -= 1;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "�ſ� ����")
				score_periodontalStatus -= 2;
			
			//ġ��
			if(bean.getTartar() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getTartar() == "�ణ")
				score_periodontalStatus -= 1;
			else if(bean.getTartar() == "����")
				score_periodontalStatus -= 2;
			else if(bean.getTartar() == "����" || bean.getTartar() == "�ſ츹��")
				score_periodontalStatus -= 3;
			
			if(bean.getScaling() == "Received in last year")
				score_periodontalStatus -= 0;
			else if(bean.getScaling() == "Received in last 2 years")
				score_periodontalStatus -= 1;
			else if(bean.getScaling() == "None")
				score_periodontalStatus -= 2;
			
			if(bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus = -3;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus = -2;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus = -1;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus = -0;
			
			if(bean.getDisease_num() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getDisease_num() == "1��")
				score_periodontalStatus -= 1;
			else if(bean.getDisease_num() == "2��")
				score_periodontalStatus -= 2;
			else if(bean.getDisease_num() == "3��")
				score_periodontalStatus -= 3;
			else if(bean.getDisease_num() == "4�� �̻�")
				score_periodontalStatus -= 4;
			
			
			/*��Ÿ�������� ����*/
			score_otherMouthStatus = 15;
			score_otherMouthStatus_basic = 15;
			
			//��������
			if(bean.getMalocclusion() == "������")
				score_otherMouthStatus -= 0;
			else if(bean.getMalocclusion() == "�ణ����")
				score_otherMouthStatus -= 1;
			else if(bean.getMalocclusion() == "������")
				score_otherMouthStatus -= 1;
			else if(bean.getMalocclusion() == "��������")
				score_otherMouthStatus -= 2;
			
			//�ø���/������
			if(bean.getDazzling() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getDazzling() == "1����")
				score_otherMouthStatus -= 1;
			else if(bean.getDazzling() == "2����")
				score_otherMouthStatus -= 2;
			else if(bean.getDazzling() == "3�����̻�")
				score_otherMouthStatus -= 3;
			
			//����� �̻�
			if(bean.getMandibular() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getMandibular() == "���� �� �Ϳ��� �Ҹ�")
				score_otherMouthStatus -= 1;
			else if(bean.getMandibular() == "���� �� ����")
				score_otherMouthStatus -= 2;
			else if(bean.getMandibular() == "���� ���")
				score_otherMouthStatus -= 2;
			
			if(bean.getWisdomTooth_pain() == "���� ����")
				score_otherMouthStatus -= 0;
			else if(bean.getWisdomTooth_pain() == "�ټ� ����")
				score_otherMouthStatus -= 1;
			else if(bean.getWisdomTooth_pain() == "���� ����")
				score_otherMouthStatus -= 2;
			
			//ġ������,ȸ��
			if(bean.getOdontoclasis() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getOdontoclasis() == "1����")
				score_otherMouthStatus -= 1;
			else if(bean.getOdontoclasis() == "2����" || bean.getOdontoclasis() == "3�����̻�")
				score_otherMouthStatus -= 2;
			
			//��������
			if(bean.getInfection() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getInfection() == "1����")
				score_otherMouthStatus -= 1;
			else if(bean.getInfection() == "2�����̻�")
				score_otherMouthStatus -= 2;
			
			//����
			if(bean.getBad_breath() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getBad_breath() == "�̾�")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "�ټҽ���(ġ����)")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "�ɰ�(��ġ��)")
				score_otherMouthStatus -= 2;
			
			
			/*������������ ����*/
			score_mouthHabit = 20;
			score_mouthHabit_basic = 20;
			
			//ġ�鼼�ո�
			if(bean.getPlaque_score() == "���Ǿ���")
				score_mouthHabit -= 0;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "����")
				score_mouthHabit -= 1;
			else if(bean.getPlaque_score() == "����")
				score_mouthHabit -= 2;
			else if(bean.getPlaque_score() == "�ſ� ����")
				score_mouthHabit -= 2;		
			
			//�̴۱� ����
			if(bean.getBrush_num() == "2ȸ �̻�")
				score_mouthHabit -= 0;
			else if(bean.getBrush_num() == "1ȸ")
				score_mouthHabit -= 1;
			else if(bean.getBrush_num() == "�ȴ۾���")
				score_mouthHabit -= 2;
			
			//�̴۱� ���
			if(bean.getBrushMethod() == "ȸ����")
				score_mouthHabit -= 0;
			else if(bean.getBrushMethod() == "�̼���")
				score_mouthHabit -= 1;
			else if(bean.getBrushMethod() == "Ȳ����")
				score_mouthHabit -= 2;
			
			if(bean.getChew_food() == "������¡��, ����")
				score_mouthHabit -= 0;
			else if(bean.getChew_food() == "���(����)")
				score_mouthHabit -= 1;
			else if(bean.getChew_food() == "��ġ, ���")
				score_mouthHabit -= 2;
			else if(bean.getChew_food() == "��, ���")
				score_mouthHabit -= 3;
			else if(bean.getChew_food() == "�κ�, ��")
				score_mouthHabit -= 4;
			
			if(bean.getAesthetic() == "��������")
				score_mouthHabit -= 0;
			else if(bean.getAesthetic() == "�ణ����")
				score_mouthHabit -= 1;
			else if(bean.getAesthetic() == "���ѹ���")
				score_mouthHabit -= 2;
			
			if(bean.getScaling() == "Received in last year")
				score_mouthHabit -= 0;
			else if(bean.getScaling() == "Received in last 2 years")
				score_mouthHabit -= 1;
			else if(bean.getScaling() == "None")
				score_mouthHabit -= 2;
			
			if(bean.getVisit() == "���� 1�Ⱓ 2ȸ �湮")
				score_mouthHabit -= 0;
			else if(bean.getVisit() == "���� 1�Ⱓ 1ȸ �湮")
				score_mouthHabit -= 1;
			else if(bean.getVisit() == "���� ���� ����")
				score_mouthHabit -= 2;
			
			if(bean.getCare_product() == "2���� �̻� ���")
				score_mouthHabit -= 0;
			else if(bean.getCare_product() == "1���� ���")
				score_mouthHabit -= 1;
			else if(bean.getCare_product() == "������� ����")
				score_mouthHabit -= 2;
			
			if(bean.getDrink_and_smoke() == "None")
				score_mouthHabit -= 0;
			else if(bean.getDrink_and_smoke() == "One of the both")
				score_mouthHabit -= 1;
			else if(bean.getDrink_and_smoke() == "Both")
				score_mouthHabit -= 2;
			
			
			//���� �ջ�
			score_total = score_toothNumber + score_dentalCaries + score_periodontalStatus 
					+ score_otherMouthStatus + score_mouthHabit; 
			
			System.out.println("score_toothNumber : " + score_toothNumber);
			System.out.println("score_dentalCaries : " + score_dentalCaries);
			System.out.println("score_periodontalStatus : " + score_periodontalStatus);
			System.out.println("score_otherMouthStatus : " + score_otherMouthStatus);
			System.out.println("score_mouthHabit : " + score_mouthHabit);
			System.out.println("score_total : " + score_total);
			
			break;
			
			
		case 5: //���

			/*ġ�� �� ����*/
			score_toothNumber = 19;
			score_toothNumber_basic = 19;
		
			if(bean.getPermanentTooth() >= 28)
				score_toothNumber -= 0;
			else if(bean.getPermanentTooth() == 27)
				score_toothNumber -= 2;
			else if(bean.getPermanentTooth() == 26)
				score_toothNumber -= 4;
			else if(bean.getPermanentTooth() == 25)
				score_toothNumber -= 5.5;
			else if(bean.getPermanentTooth() == 24)
				score_toothNumber -= 7;
			else if(bean.getPermanentTooth() == 23)
				score_toothNumber -= 8;
			else if(bean.getPermanentTooth() == 22)
				score_toothNumber -= 9;
			else if(bean.getPermanentTooth() == 21)
				score_toothNumber -= 10;
			else if(bean.getPermanentTooth() == 20)
				score_toothNumber -= 11;
			else if(bean.getPermanentTooth() == 19)
				score_toothNumber -= 12;
			else if(bean.getPermanentTooth() == 18)
				score_toothNumber -= 12.5;
			else if(bean.getPermanentTooth() == 17)
				score_toothNumber -= 13;
			else if(bean.getPermanentTooth() == 16)
				score_toothNumber -= 13.5;
			else if(bean.getPermanentTooth() == 15)
				score_toothNumber -= 14;
			else if(bean.getPermanentTooth() == 14)
				score_toothNumber -= 14.5;
			else if(bean.getPermanentTooth() <= 13 || bean.getPermanentTooth() >= 12)
				score_toothNumber -= 15;
			else if(bean.getPermanentTooth() <= 11 || bean.getPermanentTooth() >= 10)
				score_toothNumber -= 15.5;
			else if(bean.getPermanentTooth() <= 9 || bean.getPermanentTooth() >= 8)
				score_toothNumber -= 16;
			else if(bean.getPermanentTooth() <= 7 || bean.getPermanentTooth() >= 6)
				score_toothNumber -= 16.5;
			else if(bean.getPermanentTooth() <= 5 || bean.getPermanentTooth() >= 4)
				score_toothNumber -= 17;
			else if(bean.getPermanentTooth() == 3)
				score_toothNumber -= 17.5;
			else if(bean.getPermanentTooth() == 2)
				score_toothNumber -= 18;
			else if(bean.getPermanentTooth() == 1)
				score_toothNumber -= 18.5;
			else if(bean.getPermanentTooth() == 0)
				score_toothNumber -= 19;
			
			score_toothNumber += (bean.getImplant() * 0.5);
			score_toothNumber += (bean.getDentures() * 0.3);
			
			if(score_toothNumber>=19)
				score_toothNumber = 19;
			
			
			/*ġ�ƿ�� ����*/
			score_dentalCaries = 26;
			score_dentalCaries_basic = 26;
			
			//��� ����
			if(bean.getLeaving() >= 10)
				score_dentalCaries -= 10;
			else
				score_dentalCaries -= bean.getLeaving();
			
			if(bean.getTreatment() >= 12)
				score_dentalCaries -= 6;
			else
				score_dentalCaries = (float) (score_dentalCaries - (bean.getTreatment() * 0.5));
			
			//��� �߻� ���ɼ� ���� ����
			if(bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_dentalCaries -= 2;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����"
					|| bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_dentalCaries -= 1;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_dentalCaries -= 0;	
			
			if(bean.getSnyder() == "-")
				score_dentalCaries -= 0;
			else if(bean.getSnyder() == "+")
				score_dentalCaries -= 1;
			else if(bean.getSnyder() == "++" || bean.getSnyder() == "+++")
				score_dentalCaries -= 2;

			if(bean.getPlaque_score() == "���Ǿ���")
				score_dentalCaries -= 0;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "����")
				score_dentalCaries -= 1;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "�ſ� ����")
				score_dentalCaries -= 2;
			
			if(bean.getSugar_frequency() == "None")
				score_dentalCaries -= 0;
			else if(bean.getSugar_frequency() == "1 time")
				score_dentalCaries -= 1;
			else if(bean.getSugar_frequency() == "2 times or more")
				score_dentalCaries -= 2;
			
			
			if(bean.getPartialDenture() == "����")
				score_dentalCaries -= 0;
			else if(bean.getPartialDenture() == "��,�� �� �ϳ� ����")
				score_dentalCaries -= 2;
			else if(bean.getPartialDenture() == "��,�� �� �� ����")
				score_dentalCaries -= 4;
			else if(bean.getPartialDenture() == "�� �ȸ���")
				score_dentalCaries -= 4;
			
			
			
			/*ġ�ֻ��� ����*/
			score_periodontalStatus = 20;
			score_periodontalStatus_basic = 20;
			
			//ġ�ֳ�
			if(bean.getPeriodontal() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getPeriodontal() == "1����")
				score_periodontalStatus -= 1;
			else if(bean.getPeriodontal() == "2����")
				score_periodontalStatus -= 2;
			else if(bean.getPeriodontal() == "3����")
				score_periodontalStatus -= 3;
			else if(bean.getPeriodontal() == "4����" || bean.getPeriodontal() == "5����" || bean.getPeriodontal() == "6�����̻�")
				score_periodontalStatus -= 3;
			
			//ġ�Ƶ��� 
			if(bean.getMobility() == "����")
				score_periodontalStatus = 0;
			else if(bean.getMobility() == "1��")
				score_periodontalStatus -= 1;
			else if(bean.getMobility() == "2��")
				score_periodontalStatus -= 2;
			else if(bean.getMobility() == "3��")
				score_periodontalStatus -= 3;
			else if(bean.getMobility() == "4��")
				score_periodontalStatus -= 3;
			else if(bean.getMobility() == "5���̻�")
				score_periodontalStatus -= 3;
			
			//ġ�鼼�ո�
			if(bean.getPlaque_score() == "���Ǿ���")
				score_periodontalStatus -= 0;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "����")
				score_periodontalStatus -= 1;
			else if(bean.getPlaque_score() == "����")
				score_periodontalStatus -= 2;
			else if( bean.getPlaque_score() == "�ſ� ����")
				score_periodontalStatus -= 3;
			
			//ġ��
			if(bean.getTartar() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getTartar() == "�ణ")
				score_periodontalStatus -= 1;
			else if(bean.getTartar() == "����")
				score_periodontalStatus -= 2;
			else if(bean.getTartar() == "����")
				score_periodontalStatus -= 3;
			else if(bean.getTartar() == "�ſ츹��")
				score_periodontalStatus -= 4;
			
			if(bean.getGingivitis() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getGingivitis() == "1����")
				score_periodontalStatus -= 0.5;
			else if(bean.getGingivitis() == "2����")
				score_periodontalStatus -= 1;
			else if(bean.getGingivitis() == "3����")
				score_periodontalStatus -= 1.5;
			else if(bean.getGingivitis() == "4�����̻�")
				score_periodontalStatus -= 2;
			
			if(bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus -= 3;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus -= 2;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus -= 1;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus -= 0;

			if(bean.getDisease_num() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getDisease_num() == "1��")
				score_periodontalStatus -= 0.5;
			else if(bean.getDisease_num() == "2��")
				score_periodontalStatus -= 1;
			else if(bean.getDisease_num() == "3��")
				score_periodontalStatus -= 1.5;
			else if(bean.getDisease_num() == "4�� �̻�")
				score_periodontalStatus -= 2;	
			
			
			/*��Ÿ�������� ����*/
			score_otherMouthStatus = 17;
			score_otherMouthStatus_basic = 17;
			
			//�ø���/������
			if(bean.getDazzling() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getDazzling() == "1����")
				score_otherMouthStatus -= 1;
			else if(bean.getDazzling() == "2����")
				score_otherMouthStatus -= 2;
			else if(bean.getDazzling() == "3�����̻�")
				score_otherMouthStatus -= 3;
			
			//����� �̻�
			if(bean.getMandibular() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getMandibular() == "���� �� �Ϳ��� �Ҹ�")
				score_otherMouthStatus -= 1;
			else if(bean.getMandibular() == "���� �� ����")
				score_otherMouthStatus -= 2;
			else if(bean.getMandibular() == "���� ���")
				score_otherMouthStatus -= 2;
			
			//ġ������,ȸ��
			if(bean.getOdontoclasis() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getOdontoclasis() == "1����" || bean.getOdontoclasis() == "2����" || bean.getOdontoclasis() == "3�����̻�")
				score_otherMouthStatus -= 1;
			
			//��������
			if(bean.getInfection() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getInfection() == "1����")
				score_otherMouthStatus -= 1;
			else if(bean.getInfection() == "2�����̻�")
				score_otherMouthStatus -= 2;
			
			//����
			if(bean.getBad_breath() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getBad_breath() == "�̾�")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "�ټҽ���(ġ����)")
				score_otherMouthStatus -= 2;
			else if(bean.getBad_breath() == "�ɰ�(��ġ��)")
				score_otherMouthStatus -= 3;
			
			if(bean.getXerostomia() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getXerostomia() == "Slight")
				score_otherMouthStatus -= 1;
			else if(bean.getXerostomia() == "Severe")
				score_otherMouthStatus -= 2;
			
			if(bean.getProsthesis_need() == "�ʿ����")
				score_otherMouthStatus -= 0;
			else if(bean.getProsthesis_need() == "��ö�� �ʿ� �� ����")
				score_otherMouthStatus -= 1;
			else if(bean.getProsthesis_need() == "��ġ �ʿ� �� ����")
				score_otherMouthStatus -= 2;
			else if(bean.getProsthesis_need() == "�ʿ��ϳ� ������")
				score_otherMouthStatus -= 3;			
			
			
			
			
			
			/*������������ ����*/
			score_mouthHabit = 18;
			score_mouthHabit_basic = 18;
			
			//ġ�鼼�ո�
			if(bean.getPlaque_score() == "���Ǿ���")
				score_mouthHabit -= 0;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "����")
				score_mouthHabit -= 1;
			else if(bean.getPlaque_score() == "����")
				score_mouthHabit -= 2;
			else if(bean.getPlaque_score() == "�ſ� ����")
				score_mouthHabit -= 2;	
			
			if(bean.getChew_food() == "������¡��, ����")
				score_mouthHabit -= 0;
			else if(bean.getChew_food() == "���(����)")
				score_mouthHabit -= 1;
			else if(bean.getChew_food() == "��ġ, ���")
				score_mouthHabit -= 2;
			else if(bean.getChew_food() == "��, ���")
				score_mouthHabit -= 3;
			else if(bean.getChew_food() == "�κ�, ��")
				score_mouthHabit -= 4;
			
			if(bean.getAesthetic() == "��������")
				score_mouthHabit -= 0;
			else if(bean.getAesthetic() == "�ణ����")
				score_mouthHabit -= 0;
			else if(bean.getAesthetic() == "���ѹ���")
				score_mouthHabit -= 1;
			
			if(bean.getScaling() == "Received in last year")
				score_mouthHabit -= 0;
			else if(bean.getScaling() == "Received in last 2 years")
				score_periodontalStatus -= 1;
			else if(bean.getScaling() == "Received in last 2~3 years")
				score_mouthHabit -= 2;
			else if(bean.getScaling() == "None")
				score_mouthHabit -= 3;
			
			if(bean.getBad_breath() == "����")
				score_mouthHabit -= 0;
			else if(bean.getBad_breath() == "�̾�" || bean.getBad_breath() == "�ټҽ���(ġ����)")
				score_mouthHabit -= 1;
			else if(bean.getBad_breath() == "�ɰ�(��ġ��)")
				score_mouthHabit -= 2;
		
			if(bean.getCare_product() == "Use 2 or more")
				score_mouthHabit -= 0;
			else if(bean.getCare_product() == "Use 1")
				score_mouthHabit -= 1;
			else if(bean.getCare_product() == "None")
				score_mouthHabit -= 2;
			
			if(bean.getDrinking() == "drinking")
				score_mouthHabit -= 1;
			
			if(bean.getSmoking() == "Smoker")
				score_mouthHabit -= 1;
			
			if(bean.getDisease_num() == "����")
				score_mouthHabit -= 0;
			else if(bean.getDisease_num() == "1��")
				score_mouthHabit -= 0.5;
			else if(bean.getDisease_num() == "2��")
				score_mouthHabit -= 1;
			else if(bean.getDisease_num() == "3��")
				score_mouthHabit -= 1.5;
			else if(bean.getDisease_num() == "4�� �̻�")
				score_mouthHabit -= 2;
			
			
			//���� �ջ�
			score_total = score_toothNumber + score_dentalCaries + score_periodontalStatus 
					+ score_otherMouthStatus + score_mouthHabit; 
			
			System.out.println("score_toothNumber : " + score_toothNumber);
			System.out.println("score_dentalCaries : " + score_dentalCaries);
			System.out.println("score_periodontalStatus : " + score_periodontalStatus);
			System.out.println("score_otherMouthStatus : " + score_otherMouthStatus);
			System.out.println("score_mouthHabit : " + score_mouthHabit);
			System.out.println("score_total : " + score_total);
			
			break;
			
			
		case 6: //���

			/*ġ�� �� ����*/
			score_toothNumber = 20;
			score_toothNumber_basic = 20;
		
			if(bean.getPermanentTooth() >= 28)
				score_toothNumber -= 0;
			else if(bean.getPermanentTooth() == 27)
				score_toothNumber -= 2;
			else if(bean.getPermanentTooth() == 26)
				score_toothNumber -= 4;
			else if(bean.getPermanentTooth() == 25)
				score_toothNumber -= 5;
			else if(bean.getPermanentTooth() == 24)
				score_toothNumber -= 6;
			else if(bean.getPermanentTooth() == 23)
				score_toothNumber -= 7;
			else if(bean.getPermanentTooth() == 22)
				score_toothNumber -= 8;
			else if(bean.getPermanentTooth() == 21)
				score_toothNumber -= 9;
			else if(bean.getPermanentTooth() == 20)
				score_toothNumber -= 10;
			else if(bean.getPermanentTooth() == 19)
				score_toothNumber -= 11;
			else if(bean.getPermanentTooth() == 18)
				score_toothNumber -= 12;
			else if(bean.getPermanentTooth() == 17)
				score_toothNumber -= 13;
			else if(bean.getPermanentTooth() == 16)
				score_toothNumber -= 13.5;
			else if(bean.getPermanentTooth() == 15)
				score_toothNumber -= 14;
			else if(bean.getPermanentTooth() == 14)
				score_toothNumber -= 14.5;
			else if(bean.getPermanentTooth() == 13)
				score_toothNumber -= 15;
			else if(bean.getPermanentTooth() == 12)
				score_toothNumber -= 15.5;
			else if(bean.getPermanentTooth() == 11)
				score_toothNumber -= 16;
			else if(bean.getPermanentTooth() == 10)
				score_toothNumber -= 16.5;
			else if(bean.getPermanentTooth() == 9)
				score_toothNumber -= 17;
			else if(bean.getPermanentTooth() == 8)
				score_toothNumber -= 17;
			else if(bean.getPermanentTooth() == 7)
				score_toothNumber -= 17.5;
			else if(bean.getPermanentTooth() == 6)
				score_toothNumber -= 18;
			else if(bean.getPermanentTooth() == 5)
				score_toothNumber -= 18.5;
			else if(bean.getPermanentTooth() == 4)
				score_toothNumber -= 19;
			else if(bean.getPermanentTooth() == 3)
				score_toothNumber -= 19.5;
			else if(bean.getPermanentTooth() <= 2)
				score_toothNumber -= 20;
			
			score_toothNumber += (bean.getImplant() * 0.5);
			score_toothNumber += (bean.getDentures() * 0.3);
			
			if(score_toothNumber>=20)
				score_toothNumber = 20;
			
			
			/*ġ�ƿ�� ����*/
			score_dentalCaries = 17;
			score_dentalCaries_basic = 17;
			
			//��� ����
			if(bean.getLeaving() >= 10)
				score_dentalCaries -= 5;
			else
				score_dentalCaries -= (bean.getLeaving() * 0.5);
			
			if(bean.getTreatment() >= 6)
				score_dentalCaries -= 3;
			else
				score_dentalCaries = (float) (score_dentalCaries - (bean.getTreatment() * 0.3));
			
			if(bean.getSaliva()=="����" && bean.getConsistency() == "����")
				score_dentalCaries -= 0;
			else if(bean.getSaliva() == "����" && bean.getConsistency() == "����")
				score_dentalCaries -= 1;
			else if(bean.getSaliva() == "����" && bean.getConsistency() == "����")
				score_dentalCaries -= 2;
			else if(bean.getSaliva() == "����" && bean.getConsistency() == "����")
				score_dentalCaries -= 3;
			
			if(bean.getPlaque_score() == "���Ǿ���")
				score_dentalCaries -= 0;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "����")
				score_dentalCaries -= 0.5;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "�ſ� ����")
				score_dentalCaries -= 1;
			
			if(bean.getPartialDenture() == "����")
				score_dentalCaries -= 0;
			else if(bean.getPartialDenture() == "��,�� �� �ϳ� ����")
				score_dentalCaries -= 1;
			else if(bean.getPartialDenture() == "��,�� �� �� ����")
				score_dentalCaries -= 2;
			else if(bean.getPartialDenture() == "�� �ȸ���")
				score_dentalCaries -= 3;
			
			
			if(bean.getSugar_frequency() == "None")
				score_dentalCaries -= 0;
			else if(bean.getSugar_frequency() == "1ȸ ����")
				score_dentalCaries -= 0.5;
			else if(bean.getSugar_frequency() == "Times")
				score_dentalCaries -= 1;
			
			//��� �߻� ���ɼ� ���� ����
			if(bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_dentalCaries -= 0;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����"
					|| bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_dentalCaries -= 0.5;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_dentalCaries -= 1;	
			
			
			/*ġ�ֻ��� ����*/
			score_periodontalStatus = 27;
			score_periodontalStatus_basic = 27;
			
			//ġ�鼼�ո�
			if(bean.getPlaque_score() == "���Ǿ���")
				score_periodontalStatus -= 0;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "����")
				score_periodontalStatus -= 1;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "�ſ� ����")
				score_periodontalStatus -= 2;
			
			//ġ��
			if(bean.getTartar() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getTartar() == "�ణ")
				score_periodontalStatus -= 1;
			else if(bean.getTartar() == "����")
				score_periodontalStatus -= 2;
			else if(bean.getTartar() == "����")
				score_periodontalStatus -= 3;
			else if(bean.getTartar() == "�ſ츹��")
				score_periodontalStatus -= 3;
			
			//ġ�ֳ�
			if(bean.getPeriodontal() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getPeriodontal() == "1����")
				score_periodontalStatus -= 1;
			else if(bean.getPeriodontal() == "2����")
				score_periodontalStatus -= 2;
			else if(bean.getPeriodontal() == "3����")
				score_periodontalStatus -= 3;
			else if(bean.getPeriodontal() == "4����")
				score_periodontalStatus -= 4;
			else if(bean.getPeriodontal() == "5����")
				score_periodontalStatus -= 5;
			else if(bean.getPeriodontal() == "6�����̻�")
				score_periodontalStatus -= 6;
			
			if(bean.getScaling() == "Received last year")
				score_periodontalStatus -= 0;
			else if(bean.getScaling() == "Received in last 2 years")
				score_periodontalStatus -= 1;
			else if(bean.getScaling() == "Received in last 2~3 years")
				score_periodontalStatus -= 2;
			else if(bean.getScaling() == "None")
				score_periodontalStatus -= 2;
			
			//ġ�Ƶ��� 
			if(bean.getMobility() == "����")
				score_periodontalStatus = 0;
			else if(bean.getMobility() == "1��")
				score_periodontalStatus -= 1;
			else if(bean.getMobility() == "2��")
				score_periodontalStatus -= 2;
			else if(bean.getMobility() == "3��")
				score_periodontalStatus -= 3;
			else if(bean.getMobility() == "4��")
				score_periodontalStatus -= 4;
			else if(bean.getMobility() == "5���̻�")
				score_periodontalStatus -= 5;
			
			if(bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus = -3;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus = -2;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus = -1;
			else if (bean.getMicroscope_gu_amount() == "����" && bean.getMicroscope_gu_movement() == "����")
				score_periodontalStatus = -0;
			
			//�̴۱� ����
			if(bean.getBrush_num() == "2ȸ �̻�")
				score_mouthHabit -= 0;
			else if(bean.getBrush_num() == "1ȸ")
				score_mouthHabit -= 1;
			else if(bean.getBrush_num() == "�ȴ۾���")
				score_mouthHabit -= 2;
			
			if(bean.getDisease_num() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getDisease_num() == "1��")
				score_periodontalStatus -= 1;
			else if(bean.getDisease_num() == "2��")
				score_periodontalStatus -= 2;
			else if(bean.getDisease_num() == "3��")
				score_periodontalStatus -= 2;
			else if(bean.getDisease_num() == "4�� �̻�")
				score_periodontalStatus -= 2;	
			
			
			/*��Ÿ�������� ����*/
			score_otherMouthStatus = 15;
			score_otherMouthStatus_basic = 15;
			
			//ġ������,ȸ��
			if(bean.getOdontoclasis() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getOdontoclasis() == "1����")
				score_otherMouthStatus -= 1;
			else if(bean.getOdontoclasis() == "2����" || bean.getOdontoclasis() == "3�����̻�")
				score_otherMouthStatus -= 2;
			
			//��������
			if(bean.getInfection() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getInfection() == "1����")
				score_otherMouthStatus -= 1;
			else if(bean.getInfection() == "2�����̻�")
				score_otherMouthStatus -= 2;
			
			//����� �̻�
			if(bean.getMandibular() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getMandibular() == "���� �� �Ϳ��� �Ҹ�")
				score_otherMouthStatus -= 1;
			else if(bean.getMandibular() == "���� �� ����")
				score_otherMouthStatus -= 2;
			else if(bean.getMandibular() == "���� ���")
				score_otherMouthStatus -= 2;
			
			//����
			if(bean.getBad_breath() == "����")
				score_otherMouthStatus -= 0;
			else if(bean.getBad_breath() == "�̾�")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "�ټҽ���(ġ����)")
				score_otherMouthStatus -= 2;
			else if(bean.getBad_breath() == "�ɰ�(��ġ��)")
				score_otherMouthStatus -= 3;
			
			if(bean.getDenture_need() == "�ʿ����")
				score_otherMouthStatus -= 0;
			else if(bean.getDenture_need() == "�ʿ��ϸ� ������")
				score_otherMouthStatus -= 1;
			else if(bean.getDenture_need() == "�ʿ��ϳ� ������")
				score_otherMouthStatus -= 2;
			
			if(bean.getDisease_num() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getDisease_num() == "1��")
				score_periodontalStatus -= 1;
			else if(bean.getDisease_num() == "2��")
				score_periodontalStatus -= 2;
			else if(bean.getDisease_num() == "3��")
				score_periodontalStatus -= 3;
			else if(bean.getDisease_num() == "4�� �̻�")
				score_periodontalStatus -= 4;	

		
			/*������������ ����*/
			score_mouthHabit = 21;
			score_mouthHabit_basic = 21;
			
			//ġ�鼼�ո�
			if(bean.getPlaque_score() == "���Ǿ���")
				score_mouthHabit -= 0;
			else if(bean.getPlaque_score() == "����" || bean.getPlaque_score() == "����")
				score_mouthHabit -= 1;
			else if(bean.getPlaque_score() == "����")
				score_mouthHabit -= 2;
			else if(bean.getPlaque_score() == "�ſ� ����")
				score_mouthHabit -= 2;	

			if(bean.getChew_food() == "�κ�, ��")
				score_mouthHabit -= 4;
			else if(bean.getChew_food() == "��, ���")
				score_mouthHabit -= 3;
			else if(bean.getChew_food() == "��ġ, ���")
				score_mouthHabit -= 2;
			else if(bean.getChew_food() == "���(����)")
				score_mouthHabit -= 1;
			else if(bean.getChew_food() == "������¡��, ����")
				score_mouthHabit -= 0;
			
			
			
			if(bean.getAesthetic() == "��������")
				score_mouthHabit -= 0;
			else if(bean.getAesthetic() == "�ణ����")
				score_mouthHabit -= 1;
			else if(bean.getAesthetic() == "���ѹ���")
				score_mouthHabit -= 2;
			
			if(bean.getVisit() == "���� 1�Ⱓ 2ȸ �湮")
				score_mouthHabit -= 0;
			else if(bean.getVisit() == "���� 1�Ⱓ 1ȸ �湮")
				score_mouthHabit -= 1;
			else if(bean.getVisit() == "���� ���� ����")
				score_mouthHabit -= 2;
			
			//�̴۱� ����
			if(bean.getBrush_num() == "2ȸ �̻�")
				score_mouthHabit -= 0;
			else if(bean.getBrush_num() == "1ȸ")
				score_mouthHabit -= 1;
			else if(bean.getBrush_num() == "�ȴ۾���")
				score_mouthHabit -= 2;
			
			if(bean.getDisease_num() == "����")
				score_periodontalStatus -= 0;
			else if(bean.getDisease_num() == "1��")
				score_periodontalStatus -= 0.5;
			else if(bean.getDisease_num() == "2��")
				score_periodontalStatus -= 1;
			else if(bean.getDisease_num() == "3��")
				score_periodontalStatus -= 1.5;
			else if(bean.getDisease_num() == "4�� �̻�")
				score_periodontalStatus -= 2;
			
			if(bean.getDrinking() == "None")
				score_mouthHabit -= 0;
			else if(bean.getDrinking() == "1~2 per a week")
				score_mouthHabit -= 1;
			else if(bean.getDrinking() == "more than 3 times/week")
				score_mouthHabit -= 2;
			
			if(bean.getSmoking() == "None")
				score_mouthHabit -= 0;
			else if(bean.getSmoking() == "Sometimes")
				score_mouthHabit -= 1;
			else if(bean.getSmoking() == "Everyday")
				score_mouthHabit -= 2;
			
			if(bean.getCare_product() == "Use 2 or more")
				score_mouthHabit -= 0;
			else if(bean.getCare_product() == "Use 1")
				score_mouthHabit -= 1;
			else if(bean.getCare_product() == "None")
				score_mouthHabit -= 2;
			
			
			
			//���� �ջ�
			score_total = score_toothNumber + score_dentalCaries + score_periodontalStatus 
					+ score_otherMouthStatus + score_mouthHabit; 
			
			System.out.println("score_toothNumber : " + score_toothNumber);
			System.out.println("score_dentalCaries : " + score_dentalCaries);
			System.out.println("score_periodontalStatus : " + score_periodontalStatus);
			System.out.println("score_otherMouthStatus : " + score_otherMouthStatus);
			System.out.println("score_mouthHabit : " + score_mouthHabit);
			System.out.println("score_total : " + score_total);
			
			break;
			
			
		case 7: //�� ��
			break;
		}
		
		//ResultView result = new ResultView(bean, score_total);
		float[] score_result = {score_toothNumber_basic, score_toothNumber, 
				score_dentalCaries_basic, score_dentalCaries, 
				score_periodontalStatus_basic, score_periodontalStatus, 
				score_otherMouthStatus_basic, score_otherMouthStatus, 
				score_mouthHabit_basic, score_mouthHabit, 
				score_total};
		
		
		return score_result;
		
	}

}
