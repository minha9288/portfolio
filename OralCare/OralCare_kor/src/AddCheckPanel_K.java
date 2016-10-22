

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
		
		la_tartar = new JLabel("치석");
		info_tartar = new JComboBox(new String[] {"없음", "약간", "있음", "많음", "매우많음"});
		la_gingivitis = new JLabel("치은염");
		info_gingivitis = new JComboBox(new String[] {"없음", "1부위", "2부위", "3부위", "4부위이상"});
		
		
		la_micro_sal_Amount = new JLabel("살사균, 나선균 양");
		info_micro_sal_Amount = new JComboBox(new String[] {"많음", "적음"});
		la_micro_sal_Movement = new JLabel("살사균, 나선균 활동성");
		info_micro_sal_Movement = new JComboBox(new String[] {"높음", "낮음"});
		
		la_micro_gu_Amount = new JLabel("구균, 간균 양");
		info_micro_gu_Amount = new JComboBox(new String[] {"많음", "적음"});
		la_micro_gu_Movement = new JLabel("구균, 간균 활동성");
		info_micro_gu_Movement = new JComboBox(new String[] { "높음", "낮음"});

		la_snyder = new JLabel("스나이더");
		info_snyder = new JComboBox(new String[] {"+++", "++", "+", "-"});
		
		la_malocclusion = new JLabel("부정교합");
		info_malocclusion = new JComboBox(new String[] {"정상교합", "약간부정", "교정중", "부정교합"});
		la_odontoclasis = new JLabel("치아파절");
		info_odontoclasis = new JComboBox(new String[] {"없음", "1부위", "2부위", "3부위이상"});
		la_infection = new JLabel("구강감염");
		info_infection = new JComboBox(new String[] {"없음", "1부위", "2부위이상"});
		la_badBreath = new JLabel("구취");
		info_badBreath = new JComboBox(new String[] {"없음", "미약", "다소심함(치료중)", "심각(미치료)"});

		la_brushMethod = new JLabel("이닦기 방법");
		info_brushMethod = new JComboBox(new String[] {"회전법", "미숙달", "황마법"});
		
		la_periodontal = new JLabel("치주낭");
		info_periodontal = new JComboBox(new String[] {"없음", "1부위", "2부위", "3부위", "4부위", "5부위", "6부위이상"});
		la_mobility = new JLabel("치아동요");
		info_mobility = new JComboBox(new String[] {"없음", "1개", "2개", "3개", "4개", "5개이상"});
		la_mandibular = new JLabel("약관절 이상");
		info_mandibular = new JComboBox(new String[] {"없음", "개구 시 귀에서 소리", "개구 시 통증", "개구 장애"});
		la_aesthetic = new JLabel("심미요인");
		info_aesthetic = new JComboBox(new String[] {"문제없음", "약간문제", "심한문제"});
		
		la_saliva = new JLabel("타액 양");
		info_saliva = new JComboBox(new String[] {"많음", "적음"});
		la_consistency = new JLabel("점조도");
		info_consistency = new JComboBox(new String[] {"낮음", "높음"});
		la_dazzling = new JLabel("시린니/마모증");
		info_dazzling = new JComboBox(new String[] {"없음", "1부위", "2부위", "3부위이상"});
		la_wisdomTooth_pain = new JLabel("사랑니 통증 상태");
		info_wisdomTooth_pain = new JComboBox(new String[] {"증상 없음", "다소 문제", "자주 문제"});
		
		la_partialDenture = new JLabel("국소의치 장착여부");
		info_partialDenture = new JComboBox(new String[] {"없음", "상,하 중 하나 있음", "상,하 둘 다 있음", "잘 안맞음"});
		la_prosthesis_need = new JLabel("보철물/의치 필요");
		info_prosthesis_need = new JComboBox(new String[] {"필요없음", "보철물 필요 및 장착", "의치 필요 및 장착", "필요하나 미장착"});
		la_denture_need = new JLabel("틀니 필요도");
		info_denture_need = new JComboBox(new String[] {"필요없음", "필요하며 장착중", "필요하나 미장착"});
		
	
		
		
		if(0 <= age && age < 6) {
			
			// 위치 지정!
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
			
			// 위치 지정!
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
			// 위치 지정!
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
			
			// 위치 지정!
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
			
			// 위치 지정!
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
			
			// 위치 지정!
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
			
			la_tartar = new JLabel("치석");
			info_tartar = new JComboBox(new String[] {"없음", "약간", "있음", "많음", "매우많음"});
			info_tartar.setSelectedItem(bean.getTartar());
			la_gingivitis = new JLabel("치은염");
			info_gingivitis = new JComboBox(new String[] {"없음", "1부위", "2부위", "3부위", "4부위이상"});
			info_gingivitis.setSelectedItem(bean.getGingivitis());
			
			la_micro_sal_Amount = new JLabel("살사균, 나선균 양");
			info_micro_sal_Amount = new JComboBox(new String[] {"많음", "적음"});
			info_micro_sal_Amount.setSelectedItem(bean.getMicroscope_sal_amount());
			la_micro_sal_Movement = new JLabel("살사균, 나선균 활동성");
			info_micro_sal_Movement = new JComboBox(new String[] {"높음", "낮음"});
			info_micro_sal_Movement.setSelectedItem(bean.getMicroscope_sal_movement());
			
			la_micro_gu_Amount = new JLabel("구균, 간균 양");
			info_micro_gu_Amount = new JComboBox(new String[] {"많음", "적음"});
			info_micro_gu_Amount.setSelectedItem(bean.getMicroscope_gu_amount());
			la_micro_gu_Movement = new JLabel("구균, 간균 활동성");
			info_micro_gu_Movement = new JComboBox(new String[] { "높음", "낮음"});
			info_micro_gu_Movement.setSelectedItem(bean.getMicroscope_sal_movement());

			la_snyder = new JLabel("스나이더");
			info_snyder = new JComboBox(new String[] {"+++", "++", "+", "-"});
			info_snyder.setSelectedItem(bean.getSnyder());
			
			la_malocclusion = new JLabel("부정교합");
			info_malocclusion = new JComboBox(new String[] {"정상교합", "약간부정", "교정중", "부정교합"});
			info_malocclusion.setSelectedItem(bean.getMalocclusion());
			la_odontoclasis = new JLabel("치아파절");
			info_odontoclasis = new JComboBox(new String[] {"없음", "1부위", "2부위", "3부위이상"});
			info_odontoclasis.setSelectedItem(bean.getOdontoclasis());
			la_infection = new JLabel("구강감염");
			info_infection = new JComboBox(new String[] {"없음", "1부위", "2부위이상"});
			info_infection.setSelectedItem(bean.getInfection());
			la_badBreath = new JLabel("구취");
			info_badBreath = new JComboBox(new String[] {"없음", "미약", "다소심함(치료중)", "심각(미치료)"});
			info_badBreath.setSelectedItem(bean.getBad_breath());

			la_brushMethod = new JLabel("이닦기 방법");
			info_brushMethod = new JComboBox(new String[] {"회전법", "미숙달", "황마법"});
			info_brushMethod.setSelectedItem(bean.getBrushMethod());
			
			la_periodontal = new JLabel("치주낭");
			info_periodontal = new JComboBox(new String[] {"없음", "1부위", "2부위", "3부위", "4부위", "5부위", "6부위이상"});
			info_periodontal.setSelectedItem(bean.getPeriodontal());
			la_mobility = new JLabel("치아동요");
			info_mobility = new JComboBox(new String[] {"없음", "1개", "2개", "3개", "4개", "5개이상"});
			info_mobility.setSelectedItem(bean.getMobility());
			la_mandibular = new JLabel("약관절 이상");
			info_mandibular = new JComboBox(new String[] {"없음", "개구 시 귀에서 소리", "개구 시 통증", "개구 장애"});
			info_mandibular.setSelectedItem(bean.getMandibular());
			la_aesthetic = new JLabel("심미요인");
			info_aesthetic = new JComboBox(new String[] {"문제없음", "약간문제", "심한문제"});
			info_aesthetic.setSelectedItem(bean.getAesthetic());
			
			la_saliva = new JLabel("타액 양");
			info_saliva = new JComboBox(new String[] {"많음", "적음"});
			info_saliva.setSelectedItem(bean.getSaliva());
			la_consistency = new JLabel("점조도");
			info_consistency = new JComboBox(new String[] {"낮음", "높음"});
			info_consistency.setSelectedItem(bean.getConsistency());
			la_dazzling = new JLabel("시린니/마모증");
			info_dazzling = new JComboBox(new String[] {"없음", "1부위", "2부위", "3부위이상"});
			info_dazzling.setSelectedItem(bean.getDazzling());
			la_wisdomTooth_pain = new JLabel("사랑니 통증 상태");
			info_wisdomTooth_pain = new JComboBox(new String[] {"증상 없음", "다소 문제", "자주 문제"});
			info_wisdomTooth_pain.setSelectedItem(bean.getWisdomTooth_pain());
			
			la_partialDenture = new JLabel("국소의치 장착여부");
			info_partialDenture = new JComboBox(new String[] {"없음", "상,하 중 하나 있음", "상,하 둘 다 있음", "잘 안맞음"});
			info_partialDenture.setSelectedItem(bean.getPartialDenture());
			la_prosthesis_need = new JLabel("보철물/의치 필요");
			info_prosthesis_need = new JComboBox(new String[] {"필요없음", "보철물 필요 및 장착", "의치 필요 및 장착", "필요하나 미장착"});
			info_prosthesis_need.setSelectedItem(bean.getProsthesis_need());
			la_denture_need = new JLabel("틀니 필요도");
			info_denture_need = new JComboBox(new String[] {"필요없음", "필요하며 장착중", "필요하나 미장착"});
			info_denture_need.setSelectedItem(bean.getDenture_need());
		
			
			
			if(0 <= age && age < 6) {
				
				// 위치 지정!
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
				
				// 위치 지정!
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
				// 위치 지정!
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
				
				// 위치 지정!
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
				
				// 위치 지정!
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
				
				// 위치 지정!
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
