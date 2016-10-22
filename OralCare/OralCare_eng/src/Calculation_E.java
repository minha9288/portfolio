
import javax.swing.JPanel;


public class Calculation_E extends JPanel{
	
	public float[] Calculation_E(PatientInfoBean_E bean){
		
		float score_toothNumber_basic = 0;
		float score_toothNumber = 0; //치아 수 점수
		float score_dentalCaries_basic = 0;
		float score_dentalCaries = 0; //치아우식 점수
		float score_periodontalStatus_basic = 0;
		float score_periodontalStatus = 0; //치주상태 점수
		float score_otherMouthStatus_basic = 0;
		float score_otherMouthStatus = 0; //기타구강상태 점수
		float score_mouthHabit_basic = 0;
		float score_mouthHabit = 0; //구강관리습관 점수
		float score_total = 0;
		
		
		//PatientInfoBean bean = new PatientInfoBean();
		int agePart;
		int age = 0;
		age = bean.getAge();
		
		//연령군 구분
		if(0 <= age && age < 6) //영유아
			agePart = 1;		
		else if (6 <= age && age < 12) //아동
			agePart = 2;
		else if (12 <= age && age < 20) //청소년
			agePart = 3;
		else if (20 <= age && age < 40) //청년
			agePart = 4;
		else if (40 <= age && age < 65) //장년
			agePart = 5;
		else if (age > 64) //노년
			agePart = 6;
		else
			agePart = 7;
		
		System.out.println("agePart : " + agePart);
		
		int x=0, y=0;
		switch(agePart){
		case 1: //영유아
			x=0; y=0;
			
			/*치아 수 점수*/
			score_toothNumber = 20;
			score_toothNumber_basic = 20;
			score_toothNumber = bean.getBabyTooth();
			if(score_toothNumber >= 20)
				score_toothNumber = 20;
			
			/*치아우식 점수*/
			score_dentalCaries = 40;
			score_dentalCaries_basic = 40;
			
			//우식 상태
			score_dentalCaries -= bean.getLeaving();
			if(score_dentalCaries <= 28)
				score_dentalCaries = 28;
			
			if(bean.getTreatment() >= 16)
				score_dentalCaries = (float) (score_dentalCaries - 8);
			else
				score_dentalCaries = (float) (score_dentalCaries - (bean.getTreatment() * 0.5));
			
			//우식 발생 가능성 예측 점수
			if(bean.getSulcus() >= 10)
				score_dentalCaries -= 10;
			else
				score_dentalCaries -= bean.getSulcus();
			
			if(bean.getPlaque_score() == "Clean")
				score_dentalCaries -= 0;
			else if(bean.getPlaque_score() == "A few")
				score_dentalCaries -= 1;
			else if(bean.getPlaque_score() == "Normal")
				score_dentalCaries -= 2;
			else if(bean.getPlaque_score() == "A little dirty")
				score_dentalCaries -= 3;
			else if(bean.getPlaque_score() == "Vert dirty")
				score_dentalCaries -= 4;
			
			if(bean.getSugar_frequency() == "None")
				score_dentalCaries -= 0;
			else if (bean.getSugar_frequency() == "A time")
				score_dentalCaries -= 1;
			else if (bean.getSugar_frequency() == "2 times")
				score_dentalCaries -= 2;
			else if (bean.getSugar_frequency() == "3 times")
				score_dentalCaries -= 3;
			else if (bean.getSugar_frequency() == "4 times or more")
				score_dentalCaries -= 4;
			
			
			if(bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "High")
				x -= 2;
			else if ((bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "Low")
					|| (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "High"))
				x -= 1;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "Low")
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
			
			
			
			/*치주상태 점수*/
			score_periodontalStatus = 10;
			score_periodontalStatus_basic = 10;
			
			//치면세균막
			if(bean.getPlaque_score() == "Clean")
				score_periodontalStatus -= 0;
			else if(bean.getPlaque_score() == "A few")
				score_periodontalStatus -= 1;
			else if(bean.getPlaque_score() == "Normal")
				score_periodontalStatus -= 2;
			else if(bean.getPlaque_score() == "A little dirty")
				score_periodontalStatus -= 3;
			else if(bean.getPlaque_score() == "Very dirty")
				score_periodontalStatus -= 4;
			
			//치석
			if(bean.getTartar() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getTartar() == "A few")
				score_periodontalStatus -= 1;
			else if(bean.getTartar() == "Moderate")
				score_periodontalStatus -= 2;
			else if(bean.getTartar() == "Severe")
				score_periodontalStatus -= 3;
			else if(bean.getTartar() == "Numerousness")
				score_periodontalStatus -= 3;
			
			//치은염
			if(bean.getGingivitis() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getGingivitis() == "1 portion")
				score_periodontalStatus -= 1;
			else if(bean.getGingivitis() == "2 portion")
				score_periodontalStatus -= 2;
			else if(bean.getGingivitis() == "3 portion")
				score_periodontalStatus -= 3;
			else if(bean.getGingivitis() == "4 or more")
				score_periodontalStatus -= 3;
			
			
			/*기타구강상태 점수*/
			score_otherMouthStatus = 12;
			score_otherMouthStatus_basic = 12;
			
			//부정교합
			if(bean.getMalocclusion() == "Normal")
				score_otherMouthStatus -= 0;
			else if(bean.getMalocclusion() == "Slight" || bean.getMalocclusion() == "On treatment")
				score_otherMouthStatus -= 1;
			else if(bean.getMalocclusion() == "Severe")
				score_otherMouthStatus -= 2;
			
			//치아파절,회상
			if(bean.getOdontoclasis() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getOdontoclasis() == "1 portion")
				score_otherMouthStatus -= 1;
			else if(bean.getOdontoclasis() == "2 or more")
				score_otherMouthStatus -= 2;
			
			//구강감염
			if(bean.getInfection() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getInfection() == "1 portion")
				score_otherMouthStatus -= 1;
			else if(bean.getInfection() == "2 or more")
				score_otherMouthStatus -= 2;
			
			//구취
			if(bean.getBad_breath() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getBad_breath() == "Slight" || bean.getBad_breath() == "Severe on treatment")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "Severe without treatment")
				score_otherMouthStatus -= 2;
			
			//전신질환
//			if(bean.getDisease_num() == "None")
//				score_otherMouthStatus -= 0;
//			else if(bean.getDisease_num() == "미약(On treatment)")
//				score_otherMouthStatus -= 2;
//			else if(bean.getDisease_num() == "심각(미치료)")
//				score_otherMouthStatus -= 4;
			
			
			/*구강관리습관 점수*/
			score_mouthHabit = 18;
			score_mouthHabit_basic = 18;
			
			//이닦기 습관
			if(bean.getBrush_num() == "3 times")
				score_mouthHabit -= 0;
			else if(bean.getBrush_num() == "2 times")
				score_mouthHabit -= 2;
			else if(bean.getBrush_num() == "A time")
				score_mouthHabit -= 4;
			else if(bean.getBrush_num() == "None")
				score_mouthHabit -= 6;
				
			//당 섭취 빈도
			if(bean.getSugar_frequency() == "None")
				score_mouthHabit -= 0;
			else if(bean.getSugar_frequency() == "A time")
				score_mouthHabit -= 2;
			else if(bean.getSugar_frequency() == "2 times")
				score_mouthHabit -= 4;
			else if(bean.getSugar_frequency() == "3 times")
				score_mouthHabit -= 6;
			else if(bean.getSugar_frequency() == "4 times or more")
				score_mouthHabit -= 6;
			
			//치아저작 기능
			if(bean.getChew_food() == "dough, rice soup")
				score_mouthHabit -= 4;
			else if(bean.getChew_food() == "boiled rice, fried egg")
				score_mouthHabit -= 3;
			else if(bean.getChew_food() == "Kimchi, apple")
				score_mouthHabit -= 2;
			else if(bean.getChew_food() == "meat")
				score_mouthHabit -= 1;
			else if(bean.getChew_food() == "dried squired, candy")
				score_mouthHabit -= 0;
			
			//치과 방문
			if(bean.getVisit() == "2 times or more")
				score_mouthHabit -= 0;
			else if(bean.getVisit() == "1 time")
				score_mouthHabit -= 1;
			else if(bean.getVisit() == "None")
				score_mouthHabit -= 2;
			
			//최종 합산
			score_total = score_toothNumber + score_dentalCaries + score_periodontalStatus 
					+ score_otherMouthStatus + score_mouthHabit; 
			
			System.out.println("score_toothNumber : " + score_toothNumber);
			System.out.println("score_dentalCaries : " + score_dentalCaries);
			System.out.println("score_periodontalStatus : " + score_periodontalStatus);
			System.out.println("score_otherMouthStatus : " + score_otherMouthStatus);
			System.out.println("score_mouthHabit : " + score_mouthHabit);
			System.out.println("score_total : " + score_total);
			
			break;
			
			
		case 2: //아동
			x=0; y=0;
			
			/*치아 수 점수*/
			score_toothNumber = 20;
			score_toothNumber_basic = 20;
			if(bean.getBabyTooth() >= 20)
				score_toothNumber = 0;
			else
				score_toothNumber -= (bean.getBabyTooth() + bean.getLosePermanentTooth_back() + bean.getLosePermanentTooth_front());
			
			/*치아우식 점수*/
			score_dentalCaries = 32;
			score_dentalCaries_basic = 32;
			//우식 상태
			if(bean.getLeaving() >= 12)
				score_dentalCaries -= 12;
			else
				score_dentalCaries -= bean.getLeaving();
			
			if(bean.getTreatment() >= 16)
				score_dentalCaries -= 8;
			else
				score_dentalCaries = (float) (score_dentalCaries - (bean.getTreatment() * 0.5));
			
			//우식 발생 가능성 예측 점수
			if(bean.getSulcus() == 0)
				score_dentalCaries -= 0;
			else if(bean.getSulcus() == 1)
				score_dentalCaries -= 1;
			else if(bean.getSulcus() == 2)
				score_dentalCaries -= 2;
			else if(bean.getSulcus() >= 3)
				score_dentalCaries -= 3;

			if(bean.getSugar_frequency() == "None")
				score_dentalCaries -= 0;
			else if (bean.getSugar_frequency() == "A time")
				score_dentalCaries -= 0.5;
			else if (bean.getSugar_frequency() == "2 times")
				score_dentalCaries -= 1;
			else if (bean.getSugar_frequency() == "3 times")
				score_dentalCaries -= 1.5;
			else if (bean.getSugar_frequency() == "4 times or more")
				score_dentalCaries -= 2;
			
			if(bean.getPlaque_score() == "Clean" || bean.getPlaque_score() == "A few")
				score_dentalCaries -= 0;
			else if(bean.getPlaque_score() == "Normal")
				score_dentalCaries -= 1;
			else if(bean.getPlaque_score() == "A little dirty" || bean.getPlaque_score() == "Very dirty")
				score_dentalCaries -= 2;
			
			
			
			if(bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "High")
				x -= 3;
			else if (bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "Low")
				x -= 2;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "High")
				x -= 1;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "Low")
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
			
			if( bean.getBrush_num() == "2 times or more")
				score_dentalCaries -= 0;
			else if(bean.getBrush_num() == "A time")
				score_dentalCaries -= 1;
			else if(bean.getBrush_num() == "None")
				score_dentalCaries -= 2;
			
			
			/*치주상태 점수*/
			score_periodontalStatus = 10;
			score_periodontalStatus_basic = 10;
			
			//치면세균막
			if(bean.getPlaque_score() == "Clean" || bean.getPlaque_score() == "A few")
				score_periodontalStatus -= 0;
			else if(bean.getPlaque_score() == "Normal")
				score_periodontalStatus -= 1;
			else if(bean.getPlaque_score() == "A little dirty" || bean.getPlaque_score() == "Very dirty")
				score_periodontalStatus -= 2;
			
			//치석
			if(bean.getTartar() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getTartar() == "A few" || bean.getTartar() == "Moderate" || bean.getTartar() == "Severe" || bean.getTartar() == "Numerousness")
				score_periodontalStatus -= 1;
			
			//치은염
			if(bean.getGingivitis() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getGingivitis() == "1 portion")
				score_periodontalStatus -= 1;
			else if(bean.getGingivitis() == "2 portion" || bean.getGingivitis() == "3 portion" || bean.getGingivitis() == "4 or more")
				score_periodontalStatus -= 2;
			
			if(bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "High")
				score_periodontalStatus -= 3;
			else if (bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "Low")
				score_periodontalStatus -= 2;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "High")
				score_periodontalStatus -= 1;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "Low")
				score_periodontalStatus -= 0;
			
			
			if(bean.getBrush_num() == "2 times or more")
				score_periodontalStatus -= 0;
			else if(bean.getBrush_num() == "A time")
				score_periodontalStatus -= 1;
			else if(bean.getBrush_num() == "None")
				score_periodontalStatus -= 2;
			
			
			
			/*기타구강상태 점수*/
			score_otherMouthStatus = 18;
			score_otherMouthStatus_basic = 18;
			
			//부정교합
			if(bean.getMalocclusion() == "Normal")
				score_otherMouthStatus -= 0;
			else if(bean.getMalocclusion() == "Slight")
				score_otherMouthStatus -= 1;
			else if(bean.getMalocclusion() == "On treatment")
				score_otherMouthStatus -= 2;
			else if(bean.getMalocclusion() == "Severe")
				score_otherMouthStatus -= 3;
			
			//치아파절,회상
			if(bean.getOdontoclasis() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getOdontoclasis() == "1 portion")
				score_otherMouthStatus -= 2;
			else if(bean.getOdontoclasis() == "2 portion")
				score_otherMouthStatus -= 4;
			else if(bean.getOdontoclasis() == "3 or more")
				score_otherMouthStatus -= 6;
			
			//구강감염
			if(bean.getInfection() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getInfection() == "1 portion")
				score_otherMouthStatus -= 2;
			else if(bean.getInfection() == "2 or more")
				score_otherMouthStatus -= 4;
			
			//구취
			if(bean.getBad_breath() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getBad_breath() == "Slight")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "Severe on treatment")
				score_otherMouthStatus -= 2;
			else if(bean.getBad_breath() == "Severe without treatment")
				score_otherMouthStatus -= 3;
			
			//전신질환
			if(bean.getDisease_num() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getDisease_num() == "Have, is being treated")
				score_otherMouthStatus -= 1;
			else if(bean.getDisease_num() == "Have, isn't treated")
				score_otherMouthStatus -= 2;
			
			
			/*구강관리습관 점수*/
			score_mouthHabit = 20;
			score_mouthHabit_basic = 20;
			
			//이닦기 습관
			if(bean.getBrush_num() == "2 times or more")
				score_mouthHabit -= 0;
			else if(bean.getBrush_num() == "A time")
				score_mouthHabit -= 1;
			else if(bean.getBrush_num() == "None")
				score_mouthHabit -= 2;
			
			//이닦기 방법
			if(bean.getBrushMethod() == "Rolling")
				score_mouthHabit -= 0;
			else if(bean.getBrushMethod() == "Un-skilled rolling")
				score_mouthHabit -= 1;
			else if(bean.getBrushMethod() == "Horizontal rolling")
				score_mouthHabit -= 2;
			
			//이닦기 시기
			if(bean.getBrush_time() == "After meal")
				score_mouthHabit -= 0;
			else if(bean.getBrush_time() == "Sometimes, before/after meal")
				score_mouthHabit -= 1;
			else if(bean.getBrush_time() == "Before meal")
				score_mouthHabit -= 2;
				
				
			//당 섭취 빈도
			if(bean.getSugar_frequency() == "None")
				score_mouthHabit -= 0;
			else if(bean.getSugar_frequency() == "A time")
				score_mouthHabit -= 0.5;
			else if(bean.getSugar_frequency() == "2 times")
				score_mouthHabit -= 1;
			else if(bean.getSugar_frequency() == "3 times")
				score_mouthHabit -= 1.5;
			else if(bean.getSugar_frequency() == "4 times or more")
				score_mouthHabit -= 2;
			
			//치아저작 기능
			if(bean.getChew_food() == "dough, rice soup")
				score_mouthHabit -= 4;
			else if(bean.getChew_food() == "boiled rice, fried egg")
				score_mouthHabit -= 3;
			else if(bean.getChew_food() == "Kimchi, apple")
				score_mouthHabit -= 2;
			else if(bean.getChew_food() == "meat")
				score_mouthHabit -= 1;
			else if(bean.getChew_food() == "dried squired, candy")
				score_mouthHabit -= 0;
			
			//치과 방문
			if(bean.getPrevention_visit() == "Both")
				score_mouthHabit -= 0;
			else if(bean.getVisit() == "1 thing")
				score_mouthHabit -= 1;
			else if(bean.getVisit() == "None")
				score_mouthHabit -= 2;
			
			//전신질환
			if(bean.getDisease_kind() == "None")
				score_mouthHabit -= 0;
			else if(bean.getDisease_kind() == "Slight/moderate(obesity)")
				score_mouthHabit -= 1;
			else if(bean.getDisease_kind() == "Severe(diabet)")
				score_mouthHabit -= 2;
			
			//최종 합산
			score_total = score_toothNumber + score_dentalCaries + score_periodontalStatus 
					+ score_otherMouthStatus + score_mouthHabit; 
			
			System.out.println("score_toothNumber : " + score_toothNumber);
			System.out.println("score_dentalCaries : " + score_dentalCaries);
			System.out.println("score_periodontalStatus : " + score_periodontalStatus);
			System.out.println("score_otherMouthStatus : " + score_otherMouthStatus);
			System.out.println("score_mouthHabit : " + score_mouthHabit);
			System.out.println("score_total : " + score_total);
			
			break;
			
			
		case 3: //청소년
			x=0; y=0;
			
			/*치아 수 점수*/
			score_toothNumber = 20;
			score_toothNumber_basic = 20;
			score_toothNumber -= ((bean.getLosePermanentTooth_back() * 5) + (bean.getLosePermanentTooth_front() * 4));
			if(score_toothNumber <= 0)
				score_toothNumber = 0;
			
			/*치아우식 점수*/
			score_dentalCaries = 35;
			score_dentalCaries_basic = 35;
			
			//우식 상태
			if(bean.getLeaving() >= 12)
				score_dentalCaries -= 12;
			else
				score_dentalCaries -= bean.getLeaving();
			
			if(bean.getTreatment() >= 16)
				score_dentalCaries -= 8;
			else
				score_dentalCaries = (float) (score_dentalCaries - (bean.getTreatment() * 0.5));
			
			//우식 발생 가능성 예측 점수
			if(bean.getSulcus() == 0)
				score_dentalCaries -= 0;
			else if(bean.getSulcus() == 1)
				score_dentalCaries -= 1;
			else if(bean.getSulcus() >= 2)
				score_dentalCaries -= 2;

			if(bean.getPlaque_score() == "Clean")
				score_dentalCaries -= 0;
			else if(bean.getPlaque_score() == "A few" || bean.getPlaque_score() == "Normal")
				score_dentalCaries -= 1;
			else if(bean.getPlaque_score() == "A little dirty")
				score_dentalCaries -= 2;
			else if(bean.getPlaque_score() == "Very dirty")
				score_dentalCaries -= 3;
			
			if(bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "High")
				x -= 3;
			else if (bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "Low")
				x -= 2;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "High")
				x -= 1;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "Low")
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
			
			if( bean.getBrush_num() == "2 times or more")
				score_dentalCaries -= 0;
			else if(bean.getBrush_num() == "A time")
				score_dentalCaries -= 1;
			else if(bean.getBrush_num() == "None")
				score_dentalCaries -= 2;
			
			//이닦기 방법
			if(bean.getBrushMethod() == "Rolling")
				score_dentalCaries -= 0;
			else if(bean.getBrushMethod() == "Un-skilled rolling")
				score_dentalCaries -= 1;
			else if(bean.getBrushMethod() == "Horizontal rolling")
				score_dentalCaries -= 2;
			
			//당 섭취 빈도
			if(bean.getSugar_frequency() == "None")
				score_dentalCaries -= 0;
			else if(bean.getSugar_frequency() == "A time")
				score_dentalCaries -= 1;
			else if(bean.getSugar_frequency() == "2 times")
				score_dentalCaries -= 2;
			else if(bean.getSugar_frequency() == "3 times")
				score_dentalCaries -= 3;
			
			
			/*치주상태 점수*/
			score_periodontalStatus = 12;
			score_periodontalStatus_basic = 12;
			
			//치면세균막
			if(bean.getPlaque_score() == "Clean")
				score_periodontalStatus -= 0;
			else if(bean.getPlaque_score() == "A few" || bean.getPlaque_score() == "Normal")
				score_periodontalStatus -= 1;
			else if(bean.getPlaque_score() == "A little dirty")
				score_periodontalStatus -= 2;
			else if(bean.getPlaque_score() == "Very dirty")
				score_periodontalStatus -= 3;
			
			//치석
			if(bean.getTartar() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getTartar() == "A few" || bean.getTartar() == "Moderate")
				score_periodontalStatus -= 1;
			else if(bean.getTartar() == "Severe" || bean.getTartar() == "Numerousness")
				score_periodontalStatus -= 2;
			
			//치주낭
			if(bean.getPeriodontal() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getPeriodontal() == "1 portion")
				score_periodontalStatus -= 1;
			else if(bean.getPeriodontal() == "2 portion")
				score_periodontalStatus -= 2;
			else if(bean.getPeriodontal() == "3 portion")
				score_periodontalStatus -= 3;
			else if(bean.getPeriodontal() == "4 portion" || bean.getPeriodontal() == "5 portion" || bean.getPeriodontal() == "6 or more")
				score_periodontalStatus -= 4;
			
			
			if(bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "High")
				x = -2;
			else if (bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "Low"
					|| bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "High")
				x = -1;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "Low")
				x = 0;
			
			if(bean.getGingivitis() == "None")
				y = 0;
			else if(bean.getGingivitis() == "1 portion" || bean.getGingivitis() == "2 portion")
				y = -1;
			else if(bean.getGingivitis() == "3 portion" || bean.getGingivitis() == "4 or more")
				y = -2;
			
			if(x<=y)
				score_periodontalStatus -= x;
			else
				score_periodontalStatus -= y;
			
			x=0; y=0;
			//치아동요 또는 전신질환
			if(bean.getMobility() == "None")
				x = 0;
			else if(bean.getMobility() == "1 tooth" || bean.getMobility() == "2 teeth" || bean.getMobility() == "3 teeth" || bean.getMobility() == "4 teeth" || bean.getMobility() == "5 or more")
				x = -1;
			
			if(bean.getDisease_kind() == "None")
				y = 0;
			else if(bean.getDisease_kind() == "Slight/moderate(obesity)" || bean.getDisease_kind() == "Severe(diabet)")
				y = -1;
			if(x<=y)
				score_periodontalStatus -= x;
			else
				score_periodontalStatus -= y;
			
			
			
			
			/*기타구강상태 점수*/
			score_otherMouthStatus = 15;
			score_otherMouthStatus_basic = 15;
			
			//부정교합
			if(bean.getMalocclusion() == "Normal")
				score_otherMouthStatus -= 0;
			else if(bean.getMalocclusion() == "Slight")
				score_otherMouthStatus -= 1;
			else if(bean.getMalocclusion() == "On treatment")
				score_otherMouthStatus -= 2;
			else if(bean.getMalocclusion() == "Severe")
				score_otherMouthStatus -= 3;
			
			//약관절 이상
			if(bean.getMandibular() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getMandibular() == "Clicking sound")
				score_otherMouthStatus -= 1;
			else if(bean.getMandibular() == "Pain")
				score_otherMouthStatus -= 2;
			else if(bean.getMandibular() == "Severe TMJ trouble")
				score_otherMouthStatus -= 3;
			
			//치아파절,회상
			if(bean.getOdontoclasis() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getOdontoclasis() == "1 portion")
				score_otherMouthStatus -= 1;
			else if(bean.getOdontoclasis() == "2 portion" || bean.getOdontoclasis() == "3 or more")
				score_otherMouthStatus -= 2;
			
			//구취
			if(bean.getBad_breath() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getBad_breath() == "Slight")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "Severe on treatment")
				score_otherMouthStatus -= 2;
			else if(bean.getBad_breath() == "Severe without treatment")
				score_otherMouthStatus -= 3;
			
			//구강감염
			if(bean.getInfection() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getInfection() == "1 portion")
				score_otherMouthStatus -= 1;
			else if(bean.getInfection() == "2 or more")
				score_otherMouthStatus -= 2;
			
			//심미요인
			if(bean.getAesthetic() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getAesthetic() == "Slight")
				score_otherMouthStatus -= 1;
			else if(bean.getAesthetic() == "Severe")
				score_otherMouthStatus -= 2;
			
			
			/*구강관리습관 점수*/
			score_mouthHabit = 18;
			score_mouthHabit_basic = 18;
			
			//치면세균막
			if(bean.getPlaque_score() == "Clean")
				score_mouthHabit -= 0;
			else if(bean.getPlaque_score() == "A few" || bean.getPlaque_score() == "Normal")
				score_mouthHabit -= 1;
			else if(bean.getPlaque_score() == "A little dirty")
				score_mouthHabit -= 2;
			else if(bean.getPlaque_score() == "Very dirty")
				score_mouthHabit -= 3;			

			//단것 섭취
			if(bean.getSugar_frequency() == "None")
				score_mouthHabit -= 0;
			else if(bean.getSugar_frequency() == "A time")
				score_mouthHabit -= 0.5;
			else if(bean.getSugar_frequency() == "2 times")
				score_mouthHabit -= 1;
			else if(bean.getSugar_frequency() == "3 times")
				score_mouthHabit -= 1.5;
			else if(bean.getSugar_frequency() == "4 times or more")
				score_mouthHabit -= 2;
			
			//이닦기 습관
			if(bean.getBrush_num() == "2 times or more")
				score_mouthHabit -= 0;
			else if(bean.getBrush_num() == "A time")
				score_mouthHabit -= 1;
			else if(bean.getBrush_num() == "None")
				score_mouthHabit -= 2;
			
			//이닦기 방법
			if(bean.getBrushMethod() == "Rolling")
				score_mouthHabit -= 0;
			else if(bean.getBrushMethod() == "Un-skilled rolling")
				score_mouthHabit -= 1;
			else if(bean.getBrushMethod() == "Horizontal rolling")
				score_mouthHabit -= 2;
			
			//예방진료 여부
			if(bean.getPrevention_visit() == "3 things or more")
				score_mouthHabit -= 0;
			else if(bean.getPrevention_visit() == "2 things")
				score_mouthHabit -= 1;
			else if(bean.getPrevention_visit() == "1 thing")
				score_mouthHabit -= 2;
			else if(bean.getPrevention_visit() == "None")
				score_mouthHabit -= 3;
			
			//정기 치과 방문
			if(bean.getVisit() == "Have")
				score_mouthHabit -= 0;
			else if(bean.getVisit() == "None")
				score_mouthHabit -= 1;
			
			//구강보건교육 여부
			if(bean.getLearn() == "Have")
				score_mouthHabit -= 0;
			else if(bean.getLearn() == "None")
				score_mouthHabit -= 1;
			
			//전신질환
			if(bean.getDisease_kind() == "None")
				score_mouthHabit -= 0;
			else if(bean.getDisease_kind() == "Slight/moderate(obesity)")
				score_mouthHabit -= 1;
			else if(bean.getDisease_kind() == "Severe(diabet)")
				score_mouthHabit -= 2;
			
			//최종 합산
			score_total = score_toothNumber + score_dentalCaries + score_periodontalStatus 
					+ score_otherMouthStatus + score_mouthHabit; 
			
			System.out.println("score_toothNumber : " + score_toothNumber);
			System.out.println("score_dentalCaries : " + score_dentalCaries);
			System.out.println("score_periodontalStatus : " + score_periodontalStatus);
			System.out.println("score_otherMouthStatus : " + score_otherMouthStatus);
			System.out.println("score_mouthHabit : " + score_mouthHabit);
			System.out.println("score_total : " + score_total);
			
			break;


		case 4: //청년

			/*치아 수 점수*/
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
			
			
			/*치아우식 점수*/
			score_dentalCaries = 30;
			score_dentalCaries_basic = 30;
			
			//우식 상태
			if(bean.getLeaving() >= 12)
				score_dentalCaries -= 12;
			else
				score_dentalCaries -= bean.getLeaving();
			
			if(bean.getTreatment() >= 16)
				score_dentalCaries -= 8;
			else
				score_dentalCaries = (float) (score_dentalCaries - (bean.getTreatment() * 0.5));
			
			//우식 발생 가능성 예측 점수
			if(bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "High")
				score_dentalCaries -= 2;
			else if (bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "Low"
					|| bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "High")
				score_dentalCaries -= 1;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "Low")
				score_dentalCaries -= 0;			

			if(bean.getPlaque_score() == "Clean")
				score_dentalCaries -= 0;
			else if(bean.getPlaque_score() == "A few" || bean.getPlaque_score() == "Normal")
				score_dentalCaries -= 1;
			else if(bean.getPlaque_score() == "A little dirty" || bean.getPlaque_score() == "Very dirty")
				score_dentalCaries -= 2;
			
			if(bean.getSaliva()=="Many" && bean.getConsistency() == "Low")
				score_dentalCaries -= 0;
			else if(bean.getSaliva() == "Many" && bean.getConsistency() == "High")
				score_dentalCaries -= 1;
			else if(bean.getSaliva() == "A few" && bean.getConsistency() == "Low")
				score_dentalCaries -= 2;
			else if(bean.getSaliva() == "A few" && bean.getConsistency() == "High")
				score_dentalCaries -= 3;
			
			if(bean.getXerostomia() == "Slight" || bean.getXerostomia() == "Severe")
				score_dentalCaries -= 4;
			
			if(bean.getSugar_frequency() == "None")
				score_dentalCaries -= 0;
			else if(bean.getSugar_frequency() == "A time")
				score_dentalCaries -= 1;
			else if(bean.getSugar_frequency() == "2 times or more")
				score_dentalCaries -= 2;
			
			
			/*치주상태 점수*/
			score_periodontalStatus = 20;
			score_periodontalStatus_basic = 20;
			
			//치주낭
			if(bean.getPeriodontal() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getPeriodontal() == "1 portion")
				score_periodontalStatus -= 1;
			else if(bean.getPeriodontal() == "2 portion")
				score_periodontalStatus -= 2;
			else if(bean.getPeriodontal() == "3 portion")
				score_periodontalStatus -= 3;
			else if(bean.getPeriodontal() == "4 portion" || bean.getPeriodontal() == "5 portion" || bean.getPeriodontal() == "6 or more")
				score_periodontalStatus -= 3;
			
			//치아동요 
			if(bean.getMobility() == "None")
				score_periodontalStatus = 0;
			else if(bean.getMobility() == "1 tooth")
				score_periodontalStatus = -1;
			else if(bean.getMobility() == "2 teeth")
				score_periodontalStatus = -2;
			else if(bean.getMobility() == "3 teeth" || bean.getMobility() == "4 teeth" || bean.getMobility() == "5 or more")
				score_periodontalStatus = -3;
			
			//치면세균막
			if(bean.getPlaque_score() == "Clean")
				score_periodontalStatus -= 0;
			else if(bean.getPlaque_score() == "A few" || bean.getPlaque_score() == "Normal")
				score_periodontalStatus -= 1;
			else if(bean.getPlaque_score() == "A little dirty" || bean.getPlaque_score() == "Very dirty")
				score_periodontalStatus -= 2;
			
			//치석
			if(bean.getTartar() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getTartar() == "A few")
				score_periodontalStatus -= 1;
			else if(bean.getTartar() == "Moderate")
				score_periodontalStatus -= 2;
			else if(bean.getTartar() == "Severe" || bean.getTartar() == "Numerousness")
				score_periodontalStatus -= 3;
			
			if(bean.getScaling() == "Received in last year")
				score_periodontalStatus -= 0;
			else if(bean.getScaling() == "Received in last 2 years")
				score_periodontalStatus -= 1;
			else if(bean.getScaling() == "None")
				score_periodontalStatus -= 2;
			
			if(bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "High")
				score_periodontalStatus = -3;
			else if (bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "Low")
				score_periodontalStatus = -2;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "High")
				score_periodontalStatus = -1;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "Low")
				score_periodontalStatus = -0;
			
			if(bean.getDisease_num() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getDisease_num() == "One thing")
				score_periodontalStatus -= 1;
			else if(bean.getDisease_num() == "Two")
				score_periodontalStatus -= 2;
			else if(bean.getDisease_num() == "Three")
				score_periodontalStatus -= 3;
			else if(bean.getDisease_num() == "Four or more")
				score_periodontalStatus -= 4;
			
			
			/*기타구강상태 점수*/
			score_otherMouthStatus = 15;
			score_otherMouthStatus_basic = 15;
			
			//부정교합
			if(bean.getMalocclusion() == "Normal")
				score_otherMouthStatus -= 0;
			else if(bean.getMalocclusion() == "Slight")
				score_otherMouthStatus -= 1;
			else if(bean.getMalocclusion() == "On treatment")
				score_otherMouthStatus -= 1;
			else if(bean.getMalocclusion() == "Severe")
				score_otherMouthStatus -= 2;
			
			//시린니/마모증
			if(bean.getDazzling() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getDazzling() == "1 portion")
				score_otherMouthStatus -= 1;
			else if(bean.getDazzling() == "2 portion")
				score_otherMouthStatus -= 2;
			else if(bean.getDazzling() == "3 or more")
				score_otherMouthStatus -= 3;
			
			//약관절 이상
			if(bean.getMandibular() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getMandibular() == "Clicking sound")
				score_otherMouthStatus -= 1;
			else if(bean.getMandibular() == "Pain")
				score_otherMouthStatus -= 2;
			else if(bean.getMandibular() == "Severe TMJ trouble")
				score_otherMouthStatus -= 2;
			
			if(bean.getWisdomTooth_pain() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getWisdomTooth_pain() == "Slight")
				score_otherMouthStatus -= 1;
			else if(bean.getWisdomTooth_pain() == "Severe")
				score_otherMouthStatus -= 2;
			
			//치아파절,회상
			if(bean.getOdontoclasis() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getOdontoclasis() == "1 portion")
				score_otherMouthStatus -= 1;
			else if(bean.getOdontoclasis() == "2 portion" || bean.getOdontoclasis() == "3 or more")
				score_otherMouthStatus -= 2;
			
			//구강감염
			if(bean.getInfection() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getInfection() == "1 portion")
				score_otherMouthStatus -= 1;
			else if(bean.getInfection() == "2 or more")
				score_otherMouthStatus -= 2;
			
			//구취
			if(bean.getBad_breath() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getBad_breath() == "Slight")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "Severe on treatment")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "Severe without treatment")
				score_otherMouthStatus -= 2;
			
			
			/*구강관리습관 점수*/
			score_mouthHabit = 20;
			score_mouthHabit_basic = 20;
			
			//치면세균막
			if(bean.getPlaque_score() == "Clean")
				score_mouthHabit -= 0;
			else if(bean.getPlaque_score() == "A few" || bean.getPlaque_score() == "Normal")
				score_mouthHabit -= 1;
			else if(bean.getPlaque_score() == "A little dirty")
				score_mouthHabit -= 2;
			else if(bean.getPlaque_score() == "Very dirty")
				score_mouthHabit -= 2;		
			
			//이닦기 습관
			if(bean.getBrush_num() == "2 times or more")
				score_mouthHabit -= 0;
			else if(bean.getBrush_num() == "A time")
				score_mouthHabit -= 1;
			else if(bean.getBrush_num() == "None")
				score_mouthHabit -= 2;
			
			//이닦기 방법
			if(bean.getBrushMethod() == "Rolling")
				score_mouthHabit -= 0;
			else if(bean.getBrushMethod() == "Un-skilled rolling")
				score_mouthHabit -= 1;
			else if(bean.getBrushMethod() == "Horizontal rolling")
				score_mouthHabit -= 2;
			
			if(bean.getChew_food() == "dried squired, candy")
				score_mouthHabit -= 0;
			else if(bean.getChew_food() == "meat")
				score_mouthHabit -= 1;
			else if(bean.getChew_food() == "Kimchi, apple")
				score_mouthHabit -= 2;
			else if(bean.getChew_food() == "boiled rice, fried egg")
				score_mouthHabit -= 3;
			else if(bean.getChew_food() == "dough, rice soup")
				score_mouthHabit -= 4;
			
			if(bean.getAesthetic() == "None")
				score_mouthHabit -= 0;
			else if(bean.getAesthetic() == "Slight")
				score_mouthHabit -= 1;
			else if(bean.getAesthetic() == "Severe")
				score_mouthHabit -= 2;
			
			if(bean.getScaling() == "Received in last year")
				score_mouthHabit -= 0;
			else if(bean.getScaling() == "Received in last 2 years")
				score_mouthHabit -= 1;
			else if(bean.getScaling() == "None")
				score_mouthHabit -= 2;
			
			if(bean.getVisit() == "two or more visit in last a year")
				score_mouthHabit -= 0;
			else if(bean.getVisit() == "a visit in last a year")
				score_mouthHabit -= 1;
			else if(bean.getVisit() == "None")
				score_mouthHabit -= 2;
			
			if(bean.getCare_product() == "Use 2 or more")
				score_mouthHabit -= 0;
			else if(bean.getCare_product() == "Use 1")
				score_mouthHabit -= 1;
			else if(bean.getCare_product() == "None")
				score_mouthHabit -= 2;
			
			if(bean.getDrink_and_smoke() == "None")
				score_mouthHabit -= 0;
			else if(bean.getDrink_and_smoke() == "One of the both")
				score_mouthHabit -= 1;
			else if(bean.getDrink_and_smoke() == "Both")
				score_mouthHabit -= 2;
			
			
			//최종 합산
			score_total = score_toothNumber + score_dentalCaries + score_periodontalStatus 
					+ score_otherMouthStatus + score_mouthHabit; 
			
			System.out.println("score_toothNumber : " + score_toothNumber);
			System.out.println("score_dentalCaries : " + score_dentalCaries);
			System.out.println("score_periodontalStatus : " + score_periodontalStatus);
			System.out.println("score_otherMouthStatus : " + score_otherMouthStatus);
			System.out.println("score_mouthHabit : " + score_mouthHabit);
			System.out.println("score_total : " + score_total);
			
			break;
			
			
		case 5: //장년

			/*치아 수 점수*/
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
			
			
			/*치아우식 점수*/
			score_dentalCaries = 26;
			score_dentalCaries_basic = 26;
			
			//우식 상태
			if(bean.getLeaving() >= 10)
				score_dentalCaries -= 10;
			else
				score_dentalCaries -= bean.getLeaving();
			
			if(bean.getTreatment() >= 12)
				score_dentalCaries -= 6;
			else
				score_dentalCaries = (float) (score_dentalCaries - (bean.getTreatment() * 0.5));
			
			//우식 발생 가능성 예측 점수
			if(bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "High")
				score_dentalCaries -= 2;
			else if (bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "Low"
					|| bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "High")
				score_dentalCaries -= 1;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "Low")
				score_dentalCaries -= 0;	
			
			if(bean.getSnyder() == "-")
				score_dentalCaries -= 0;
			else if(bean.getSnyder() == "+")
				score_dentalCaries -= 1;
			else if(bean.getSnyder() == "++" || bean.getSnyder() == "+++")
				score_dentalCaries -= 2;

			if(bean.getPlaque_score() == "Clean")
				score_dentalCaries -= 0;
			else if(bean.getPlaque_score() == "A few" || bean.getPlaque_score() == "Normal")
				score_dentalCaries -= 1;
			else if(bean.getPlaque_score() == "A little dirty" || bean.getPlaque_score() == "Very dirty")
				score_dentalCaries -= 2;
			
			if(bean.getSugar_frequency() == "None")
				score_dentalCaries -= 0;
			else if(bean.getSugar_frequency() == "1 time")
				score_dentalCaries -= 1;
			else if(bean.getSugar_frequency() == "2 times or more")
				score_dentalCaries -= 2;
			
			
			if(bean.getPartialDenture() == "None")
				score_dentalCaries -= 0;
			else if(bean.getPartialDenture() == "One portion of the upper and lower")
				score_dentalCaries -= 2;
			else if(bean.getPartialDenture() == "Both protion of the upper and lower")
				score_dentalCaries -= 4;
			else if(bean.getPartialDenture() == "Severe")
				score_dentalCaries -= 4;
			
			
			
			/*치주상태 점수*/
			score_periodontalStatus = 20;
			score_periodontalStatus_basic = 20;
			
			//치주낭
			if(bean.getPeriodontal() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getPeriodontal() == "1 portion")
				score_periodontalStatus -= 1;
			else if(bean.getPeriodontal() == "2 portion")
				score_periodontalStatus -= 2;
			else if(bean.getPeriodontal() == "3 portion")
				score_periodontalStatus -= 3;
			else if(bean.getPeriodontal() == "4 portion" || bean.getPeriodontal() == "5 portion" || bean.getPeriodontal() == "6 or more")
				score_periodontalStatus -= 3;
			
			//치아동요 
			if(bean.getMobility() == "None")
				score_periodontalStatus = 0;
			else if(bean.getMobility() == "1 tooth")
				score_periodontalStatus -= 1;
			else if(bean.getMobility() == "2 teeth")
				score_periodontalStatus -= 2;
			else if(bean.getMobility() == "3 teeth")
				score_periodontalStatus -= 3;
			else if(bean.getMobility() == "4 teeth")
				score_periodontalStatus -= 3;
			else if(bean.getMobility() == "5 or more")
				score_periodontalStatus -= 3;
			
			//치면세균막
			if(bean.getPlaque_score() == "Clean")
				score_periodontalStatus -= 0;
			else if(bean.getPlaque_score() == "A few" || bean.getPlaque_score() == "Normal")
				score_periodontalStatus -= 1;
			else if(bean.getPlaque_score() == "A little dirty")
				score_periodontalStatus -= 2;
			else if( bean.getPlaque_score() == "Very dirty")
				score_periodontalStatus -= 3;
			
			//치석
			if(bean.getTartar() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getTartar() == "A few")
				score_periodontalStatus -= 1;
			else if(bean.getTartar() == "Moderate")
				score_periodontalStatus -= 2;
			else if(bean.getTartar() == "Severe")
				score_periodontalStatus -= 3;
			else if(bean.getTartar() == "Numerousness")
				score_periodontalStatus -= 4;
			
			if(bean.getGingivitis() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getGingivitis() == "1 portion")
				score_periodontalStatus -= 0.5;
			else if(bean.getGingivitis() == "2 portion")
				score_periodontalStatus -= 1;
			else if(bean.getGingivitis() == "3 portion")
				score_periodontalStatus -= 1.5;
			else if(bean.getGingivitis() == "4 or more")
				score_periodontalStatus -= 2;
			
			if(bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "High")
				score_periodontalStatus -= 3;
			else if (bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "Low")
				score_periodontalStatus -= 2;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "High")
				score_periodontalStatus -= 1;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "Low")
				score_periodontalStatus -= 0;

			if(bean.getDisease_num() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getDisease_num() == "One thing")
				score_periodontalStatus -= 0.5;
			else if(bean.getDisease_num() == "Two")
				score_periodontalStatus -= 1;
			else if(bean.getDisease_num() == "Three")
				score_periodontalStatus -= 1.5;
			else if(bean.getDisease_num() == "Four or more")
				score_periodontalStatus -= 2;	
			
			
			/*기타구강상태 점수*/
			score_otherMouthStatus = 17;
			score_otherMouthStatus_basic = 17;
			
			//시린니/마모증
			if(bean.getDazzling() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getDazzling() == "1 portion")
				score_otherMouthStatus -= 1;
			else if(bean.getDazzling() == "2 portion")
				score_otherMouthStatus -= 2;
			else if(bean.getDazzling() == "3 or more")
				score_otherMouthStatus -= 3;
			
			//약관절 이상
			if(bean.getMandibular() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getMandibular() == "Clicking sound")
				score_otherMouthStatus -= 1;
			else if(bean.getMandibular() == "Pain")
				score_otherMouthStatus -= 2;
			else if(bean.getMandibular() == "Severe TMJ trouble")
				score_otherMouthStatus -= 2;
			
			//치아파절,회상
			if(bean.getOdontoclasis() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getOdontoclasis() == "1 portion" || bean.getOdontoclasis() == "2 portion" || bean.getOdontoclasis() == "3 or more")
				score_otherMouthStatus -= 1;
			
			//구강감염
			if(bean.getInfection() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getInfection() == "1 portion")
				score_otherMouthStatus -= 1;
			else if(bean.getInfection() == "2 or more")
				score_otherMouthStatus -= 2;
			
			//구취
			if(bean.getBad_breath() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getBad_breath() == "Slight")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "Severe on treatment")
				score_otherMouthStatus -= 2;
			else if(bean.getBad_breath() == "Severe without treatment")
				score_otherMouthStatus -= 3;
			
			if(bean.getXerostomia() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getXerostomia() == "Slight")
				score_otherMouthStatus -= 1;
			else if(bean.getXerostomia() == "Severe")
				score_otherMouthStatus -= 2;
			
			if(bean.getProsthesis_need() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getProsthesis_need() == "Need bridge and wearing")
				score_otherMouthStatus -= 1;
			else if(bean.getProsthesis_need() == "Need Denture and wearing")
				score_otherMouthStatus -= 2;
			else if(bean.getProsthesis_need() == "Need but not wearing")
				score_otherMouthStatus -= 3;			
			
			
			
			
			
			/*구강관리습관 점수*/
			score_mouthHabit = 18;
			score_mouthHabit_basic = 18;
			
			//치면세균막
			if(bean.getPlaque_score() == "Clean")
				score_mouthHabit -= 0;
			else if(bean.getPlaque_score() == "A few" || bean.getPlaque_score() == "Normal")
				score_mouthHabit -= 1;
			else if(bean.getPlaque_score() == "A little dirty")
				score_mouthHabit -= 2;
			else if(bean.getPlaque_score() == "Very dirty")
				score_mouthHabit -= 2;	
			
			if(bean.getChew_food() == "dried squired, candy")
				score_mouthHabit -= 0;
			else if(bean.getChew_food() == "meat")
				score_mouthHabit -= 1;
			else if(bean.getChew_food() == "Kimchi, apple")
				score_mouthHabit -= 2;
			else if(bean.getChew_food() == "boiled rice, fried egg")
				score_mouthHabit -= 3;
			else if(bean.getChew_food() == "dough, rice soup")
				score_mouthHabit -= 4;
			
			if(bean.getAesthetic() == "None")
				score_mouthHabit -= 0;
			else if(bean.getAesthetic() == "Slight")
				score_mouthHabit -= 0;
			else if(bean.getAesthetic() == "Severe")
				score_mouthHabit -= 1;
			
			if(bean.getScaling() == "Received in last year")
				score_mouthHabit -= 0;
			else if(bean.getScaling() == "Received in last 2 years")
				score_periodontalStatus -= 1;
			else if(bean.getScaling() == "Received in last 2~3 years")
				score_mouthHabit -= 2;
			else if(bean.getScaling() == "None")
				score_mouthHabit -= 3;
			
			if(bean.getBad_breath() == "None")
				score_mouthHabit -= 0;
			else if(bean.getBad_breath() == "Slight" || bean.getBad_breath() == "Severe on treatment")
				score_mouthHabit -= 1;
			else if(bean.getBad_breath() == "Severe without treatment")
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
			
			if(bean.getDisease_num() == "None")
				score_mouthHabit -= 0;
			else if(bean.getDisease_num() == "One thing")
				score_mouthHabit -= 0.5;
			else if(bean.getDisease_num() == "Two")
				score_mouthHabit -= 1;
			else if(bean.getDisease_num() == "Three")
				score_mouthHabit -= 1.5;
			else if(bean.getDisease_num() == "Four or more")
				score_mouthHabit -= 2;
			
			
			//최종 합산
			score_total = score_toothNumber + score_dentalCaries + score_periodontalStatus 
					+ score_otherMouthStatus + score_mouthHabit; 
			
			System.out.println("score_toothNumber : " + score_toothNumber);
			System.out.println("score_dentalCaries : " + score_dentalCaries);
			System.out.println("score_periodontalStatus : " + score_periodontalStatus);
			System.out.println("score_otherMouthStatus : " + score_otherMouthStatus);
			System.out.println("score_mouthHabit : " + score_mouthHabit);
			System.out.println("score_total : " + score_total);
			
			break;
			
			
		case 6: //노년

			/*치아 수 점수*/
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
			
			
			/*치아우식 점수*/
			score_dentalCaries = 17;
			score_dentalCaries_basic = 17;
			
			//우식 상태
			if(bean.getLeaving() >= 10)
				score_dentalCaries -= 5;
			else
				score_dentalCaries -= (bean.getLeaving() * 0.5);
			
			if(bean.getTreatment() >= 6)
				score_dentalCaries -= 3;
			else
				score_dentalCaries = (float) (score_dentalCaries - (bean.getTreatment() * 0.3));
			
			if(bean.getSaliva()=="Many" && bean.getConsistency() == "Low")
				score_dentalCaries -= 0;
			else if(bean.getSaliva() == "Many" && bean.getConsistency() == "High")
				score_dentalCaries -= 1;
			else if(bean.getSaliva() == "A few" && bean.getConsistency() == "Low")
				score_dentalCaries -= 2;
			else if(bean.getSaliva() == "A few" && bean.getConsistency() == "High")
				score_dentalCaries -= 3;
			
			if(bean.getPlaque_score() == "Clean")
				score_dentalCaries -= 0;
			else if(bean.getPlaque_score() == "A few" || bean.getPlaque_score() == "Normal")
				score_dentalCaries -= 0.5;
			else if(bean.getPlaque_score() == "A little dirty" || bean.getPlaque_score() == "Very dirty")
				score_dentalCaries -= 1;
			
			if(bean.getPartialDenture() == "None")
				score_dentalCaries -= 0;
			else if(bean.getPartialDenture() == "One portion of the upper and lower")
				score_dentalCaries -= 1;
			else if(bean.getPartialDenture() == "Both protion of the upper and lower")
				score_dentalCaries -= 2;
			else if(bean.getPartialDenture() == "Severe")
				score_dentalCaries -= 3;
			
			
			if(bean.getSugar_frequency() == "None")
				score_dentalCaries -= 0;
			else if(bean.getSugar_frequency() == "A time")
				score_dentalCaries -= 0.5;
			else if(bean.getSugar_frequency() == "Times")
				score_dentalCaries -= 1;
			
			//우식 발생 가능성 예측 점수
			if(bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "High")
				score_dentalCaries -= 0;
			else if (bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "Low"
					|| bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "High")
				score_dentalCaries -= 0.5;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "Low")
				score_dentalCaries -= 1;	
			
			
			/*치주상태 점수*/
			score_periodontalStatus = 27;
			score_periodontalStatus_basic = 27;
			
			//치면세균막
			if(bean.getPlaque_score() == "Clean")
				score_periodontalStatus -= 0;
			else if(bean.getPlaque_score() == "A few" || bean.getPlaque_score() == "Normal")
				score_periodontalStatus -= 1;
			else if(bean.getPlaque_score() == "A little dirty" || bean.getPlaque_score() == "Very dirty")
				score_periodontalStatus -= 2;
			
			//치석
			if(bean.getTartar() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getTartar() == "A few")
				score_periodontalStatus -= 1;
			else if(bean.getTartar() == "Moderate")
				score_periodontalStatus -= 2;
			else if(bean.getTartar() == "Severe")
				score_periodontalStatus -= 3;
			else if(bean.getTartar() == "Numerousness")
				score_periodontalStatus -= 3;
			
			//치주낭
			if(bean.getPeriodontal() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getPeriodontal() == "1 portion")
				score_periodontalStatus -= 1;
			else if(bean.getPeriodontal() == "2 portion")
				score_periodontalStatus -= 2;
			else if(bean.getPeriodontal() == "3 portion")
				score_periodontalStatus -= 3;
			else if(bean.getPeriodontal() == "4 portion")
				score_periodontalStatus -= 4;
			else if(bean.getPeriodontal() == "5 portion")
				score_periodontalStatus -= 5;
			else if(bean.getPeriodontal() == "6 or more")
				score_periodontalStatus -= 6;
			
			if(bean.getScaling() == "Received last year")
				score_periodontalStatus -= 0;
			else if(bean.getScaling() == "Received in last 2 years")
				score_periodontalStatus -= 1;
			else if(bean.getScaling() == "Received in last 2~3 years")
				score_periodontalStatus -= 2;
			else if(bean.getScaling() == "None")
				score_periodontalStatus -= 2;
			
			//치아동요 
			if(bean.getMobility() == "None")
				score_periodontalStatus = 0;
			else if(bean.getMobility() == "1 tooth")
				score_periodontalStatus -= 1;
			else if(bean.getMobility() == "2 teeth")
				score_periodontalStatus -= 2;
			else if(bean.getMobility() == "3 teeth")
				score_periodontalStatus -= 3;
			else if(bean.getMobility() == "4 teeth")
				score_periodontalStatus -= 4;
			else if(bean.getMobility() == "5 or more")
				score_periodontalStatus -= 5;
			
			if(bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "High")
				score_periodontalStatus = -3;
			else if (bean.getMicroscope_gu_amount() == "Many" && bean.getMicroscope_gu_movement() == "Low")
				score_periodontalStatus = -2;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "High")
				score_periodontalStatus = -1;
			else if (bean.getMicroscope_gu_amount() == "A few" && bean.getMicroscope_gu_movement() == "Low")
				score_periodontalStatus = -0;
			
			//이닦기 습관
			if(bean.getBrush_num() == "2 times")
				score_mouthHabit -= 0;
			else if(bean.getBrush_num() == "A time")
				score_mouthHabit -= 1;
			else if(bean.getBrush_num() == "None")
				score_mouthHabit -= 2;
			
			if(bean.getDisease_num() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getDisease_num() == "One thing")
				score_periodontalStatus -= 1;
			else if(bean.getDisease_num() == "Two")
				score_periodontalStatus -= 2;
			else if(bean.getDisease_num() == "Three")
				score_periodontalStatus -= 2;
			else if(bean.getDisease_num() == "Four or more")
				score_periodontalStatus -= 2;	
			
			
			/*기타구강상태 점수*/
			score_otherMouthStatus = 15;
			score_otherMouthStatus_basic = 15;
			
			//치아파절,회상
			if(bean.getOdontoclasis() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getOdontoclasis() == "1 portion")
				score_otherMouthStatus -= 1;
			else if(bean.getOdontoclasis() == "2 portion" || bean.getOdontoclasis() == "3 or more")
				score_otherMouthStatus -= 2;
			
			//구강감염
			if(bean.getInfection() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getInfection() == "1 portion")
				score_otherMouthStatus -= 1;
			else if(bean.getInfection() == "2 or more")
				score_otherMouthStatus -= 2;
			
			//약관절 이상
			if(bean.getMandibular() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getMandibular() == "Clicking sound")
				score_otherMouthStatus -= 1;
			else if(bean.getMandibular() == "Pain")
				score_otherMouthStatus -= 2;
			else if(bean.getMandibular() == "Severe TMJ trouble")
				score_otherMouthStatus -= 2;
			
			//구취
			if(bean.getBad_breath() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getBad_breath() == "Slight")
				score_otherMouthStatus -= 1;
			else if(bean.getBad_breath() == "Severe on treatment")
				score_otherMouthStatus -= 2;
			else if(bean.getBad_breath() == "Severe without treatment")
				score_otherMouthStatus -= 3;
			
			if(bean.getDenture_need() == "None")
				score_otherMouthStatus -= 0;
			else if(bean.getDenture_need() == "Need and wearing")
				score_otherMouthStatus -= 1;
			else if(bean.getDenture_need() == "Need but not wearing")
				score_otherMouthStatus -= 2;
			
			if(bean.getDisease_num() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getDisease_num() == "One thing")
				score_periodontalStatus -= 1;
			else if(bean.getDisease_num() == "Two")
				score_periodontalStatus -= 2;
			else if(bean.getDisease_num() == "Three")
				score_periodontalStatus -= 3;
			else if(bean.getDisease_num() == "Four or more")
				score_periodontalStatus -= 4;	

		
			/*구강관리습관 점수*/
			score_mouthHabit = 21;
			score_mouthHabit_basic = 21;
			
			//치면세균막
			if(bean.getPlaque_score() == "Clean")
				score_mouthHabit -= 0;
			else if(bean.getPlaque_score() == "A few" || bean.getPlaque_score() == "Normal")
				score_mouthHabit -= 1;
			else if(bean.getPlaque_score() == "A little dirty")
				score_mouthHabit -= 2;
			else if(bean.getPlaque_score() == "Very dirty")
				score_mouthHabit -= 2;	
			
			if(bean.getChew_food() == "dried squired, candy")
				score_mouthHabit -= 0;
			else if(bean.getChew_food() == "meat")
				score_mouthHabit -= 1;
			else if(bean.getChew_food() == "Kimchi, apple")
				score_mouthHabit -= 2;
			else if(bean.getChew_food() == "boiled rice, fried egg")
				score_mouthHabit -= 3;
			else if(bean.getChew_food() == "dough")
				score_mouthHabit -= 4;
			else if(bean.getChew_food() == "rice soup, yoghurt")
				score_mouthHabit -= 5;
			
			if(bean.getAesthetic() == "None")
				score_mouthHabit -= 0;
			else if(bean.getAesthetic() == "Slight")
				score_mouthHabit -= 1;
			else if(bean.getAesthetic() == "Severe")
				score_mouthHabit -= 2;
			
			if(bean.getVisit() == "a visit in last a year")
				score_mouthHabit -= 0;
			else if(bean.getVisit() == "a visit in last 2 years")
				score_mouthHabit -= 1;
			else if(bean.getVisit() == "None")
				score_mouthHabit -= 2;
			
			//이닦기 습관
			if(bean.getBrush_num() == "2 times")
				score_mouthHabit -= 0;
			else if(bean.getBrush_num() == "A time")
				score_mouthHabit -= 1;
			else if(bean.getBrush_num() == "None")
				score_mouthHabit -= 2;
			
			if(bean.getDisease_num() == "None")
				score_periodontalStatus -= 0;
			else if(bean.getDisease_num() == "One thing")
				score_periodontalStatus -= 0.5;
			else if(bean.getDisease_num() == "Two")
				score_periodontalStatus -= 1;
			else if(bean.getDisease_num() == "Three")
				score_periodontalStatus -= 1.5;
			else if(bean.getDisease_num() == "Four or more")
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
			
			
			
			//최종 합산
			score_total = score_toothNumber + score_dentalCaries + score_periodontalStatus 
					+ score_otherMouthStatus + score_mouthHabit; 
			
			System.out.println("score_toothNumber : " + score_toothNumber);
			System.out.println("score_dentalCaries : " + score_dentalCaries);
			System.out.println("score_periodontalStatus : " + score_periodontalStatus);
			System.out.println("score_otherMouthStatus : " + score_otherMouthStatus);
			System.out.println("score_mouthHabit : " + score_mouthHabit);
			System.out.println("score_total : " + score_total);
			
			break;
			
			
		case 7: //그 외
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
