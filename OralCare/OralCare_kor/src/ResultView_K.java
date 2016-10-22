

import java.awt.TextArea;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ResultView_K extends JPanel {
	
	
	public ResultView_K(PatientInfoBean_K bean, float score_total) {
		// TODO Auto-generated constructor stub
		
		JPanel panel = new JPanel();
		

		//영유아
				String[][] result_age1 = {{"구강검진 : 2 회 (6개월마다 약속)", "이닦기 교습 : 2 회", "불소도포 : 1 회", "씰런트 : 깊은 열구치아 모두 씰런트", ""},
						{"구강검진 : 3 회 (4개월마다 약속)", "이닦기 교습 : 3 회", "불소도포 : 2 회", "씰런트 : 깊은 열구치아 모두 씰런트", ""},
						{"구강검진 : 4 회 (3개월마다 약속)", "이닦기 교습 : 4 회", "불소도포 : 4 회", "불소용액양치 : 매일", "씰런트 : 깊은 열구치아 모두 씰런트"}
				};
				
				//아동
				String[][] result_age2 = {{"구강검진 : 3 회 (4개월마다 약속)", "이닦기 교습 : 3 회", "불소도포 : 1 회", "씰런트 : 깊은 열구치아 모두 씰런트", ""},
						{"구강검진 : 4 회 (3개월마다 약속)", "이닦기 교습 : 4 회", "불소도포 : 2 회", "씰런트 : 깊은 열구치아 모두 씰런트", ""},
						{"구강검진 : 4 회 (3개월마다 약속)", "이닦기 교습 : 4 회", "불소도포 : 4 회", "불소용액양치 : 매일", "씰런트 : 깊은 열구치아 모두 씰런트"}
				};
				
				//청소년
				String[][] result_age3 = {{"구강검진 : 3 회 (4개월마다 약속)", "이닦기 교습 : 2 회", "불소도포 : 1 회(년)", "씰런트 : 깊은 열구치아 모두 씰런트"},
						{"구강검진 : 4 회 (3개월마다 약속)", "이닦기 교습 : 3 회", "불소도포 : 2 회(6개월마다 약속)", "씰런트 : 깊은 열구치아 모두 씰런트", "스켈링 1 회"},
						{"구강검진 : 6 회 (2개월마다 약속)", "이닦기 교습 : 4 회(3개월마다 약속)", "불소도포 : 4 회(3개월마다 약속)", "불소용액양치 : 매일", "씰런트 : 깊은 열구치아 모두 씰런트", "스켈링 : 2 회(6개월마다 약속)"}
				};
				
				//청년
				String[][] result_age4 = {{"구강검진 : 3 회 (4개월마다 약속)", "이닦기 교습 : 2 회", "불소도포 : 1 회", "스켈링 : 1 회"},
						{"구강검진 : 4 회 (3개월마다 약속)", "이닦기 교습 : 3 회", "불소도포 : 2 회(6개월마다 약속)", "스켈링 1 회"},
						{"구강검진 : 6 회 (2개월마다 약속)", "이닦기 교습 : 4 회(3개월마다 약속)", "불소도포 : 1 회", "스켈링 : 1 회"}
				};
				
				//장년, 노년
				String[][] result_age5 = {{"구강검진 : 2 회 (6개월마다 약속)", "이닦기 교습 : 2 회 (6개월마다 약속)", "스켈링 : 1 회 (년)"},
						{"구강검진 : 3 회 (4개월마다 약속)", "이닦기 교습 : 3 회 (4개월마다 약속)", "스켈링 : 2 회 (6개월마다 약속)"},
						{"구강검진 : 4 회 (3개월마다 약속)", "이닦기 교습 : 4 회 (3개월마다 약속)", "스켈링 : 3 회 (4개월마다 약속)", "시린니 증상시 불소도포"}
				};
				
				
				
				String[] result_mal = {"교정칫솔 사용", "치간칫솔 사용", "물사출기 사용 권장", "고무치간자극기 사용", "물사출기 사용권장"};
				
				/*
				String[][] result_age4_per = {{"구강검진 : 3 회 (4개월마다 약속)", "이닦기 교습 : 2 회", "불소도포 : 1 회", "스켈링 : 1 회", "치간칫솔 사용"},
						{"구강검진 : 4 회 (3개월마다 약속)", "이닦기 교습 : 3 회", "불소도포 : 2 회(6개월마다 약속)", "스켈링 1 회", "Interdental brush", "Gingival massager"},
						{"구강검진 : 6 회 (2개월마다 약속)", "이닦기 교습 : 4 회(3개월마다 약속)", "불소도포 : 1 회", "스켈링 : 1 회", "PMTC 2 회", "Interdental brush", "Gingival massager or Rubber tip recommend", "Water pik(recommend)"}
				};
				
				String[][] result_age5_per = {{"구강검진 : 3 회 (4개월마다 약속)", "이닦기 교습 : 2 회 (6개월마다 약속)", "스켈링 : 1 회 (년)", "Interdental brush"},
						{"구강검진 : 4 회 (3개월마다 약속)", "이닦기 교습 : 3 회 (4개월마다 약속)", "스켈링 : 2 회 (6개월마다 약속)", "Interdental brush", "Gingival massager"},
						{"구강검진 : 6 회 (2개월마다 약속)", "이닦기 교습 : 4 회 (3개월마다 약속)", "스켈링 : 3 회 (4개월마다 약속)", "Interdental brush", "Rubber tip", "Superfloss", "Gingival massager", "Water pik(recommend)"}
				};
				*/
				
				String[] result_preg = {"치간칫솔 사용", "임산부 구강 교육", "구강양치액 사용", "구취 조절 진료"};
				
				String[] result_imp = {"치간칫솔 사용", "수퍼플로스 사용", "치은맛사져 사용", "고무치간자극기 사용", "물사출기 사용 권장"};
				
				String[] result_den = {"의치용 칫솔", "의치세정제"};
		
				
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
			if(bean.getMalocclusion() == "교정중"){
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
			if(bean.getMalocclusion() == "교정중"){
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
			if(bean.getMalocclusion() == "교정중"){
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
			if(bean.getPregnancy() == "임신중"){
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
			if(bean.getProsthesis_need() == "보철물 필요 및 장착" || bean.getProsthesis_need() == "의치 필요 및 장착" || bean.getDenture_need() == "필요하며 장착중"){
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
			if(bean.getPartialDenture() != "없음" ){
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
			if(bean.getProsthesis_need() == "보철물 필요 및 장착" || bean.getProsthesis_need() == "의치 필요 및 장착" || bean.getDenture_need() == "필요하며 장착중"){
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
			if(bean.getPartialDenture() != "없음" ){
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
