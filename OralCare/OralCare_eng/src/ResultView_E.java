

import java.awt.TextArea;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ResultView_E extends JPanel {
	
	
	
	
	public ResultView_E(PatientInfoBean_E bean, float score_total) {
		// TODO Auto-generated constructor stub
		
		JPanel panel = new JPanel();
		

		//영유아
				String[][] result_age1 = {{"Oral exam : 2 times (every 6 months)", "Tooth brushing Instruction : 2 times", "Fluoride application : 1 times", "Sealant : every tooth with deep pit", ""},
						{"Oral exam : 3 times (every 4 months)", "Tooth brushing Instruction : 3 times", "Fluoride application : 2 times", "Sealant : every tooth with deep pit", ""},
						{"Oral exam : 4 times (every 3 months)", "Tooth brushing Instruction : 4 times", "Fluoride application : 4 times", "Fluoride mouth rinse : everyday", "Sealant : every tooth with deep pit"}
				};
				
				//아동
				String[][] result_age2 = {{"Oral exam : 3 times (every 4 months)", "Tooth brushing Instruction : 3 times", "Fluoride application : 1 times", "Sealant : every tooth with deep pit", ""},
						{"Oral exam : 4 times (every 3 months)", "Tooth brushing Instruction : 4 times", "Fluoride application : 2 times", "Sealant : every tooth with deep pit", ""},
						{"Oral exam : 4 times (every 3 months)", "Tooth brushing Instruction : 4 times", "Fluoride application : 4 times", "Fluoride mouth rinse : everyday", "Sealant : every tooth with deep pit"}
				};
				
				//청소년
				String[][] result_age3 = {{"Oral exam : 3 times (every 4 months)", "Tooth brushing Instruction : 2 times", "Fluoride application : 1 times(year)", "Sealant : every tooth with deep pit"},
						{"Oral exam : 4 times (every 3 months)", "Tooth brushing Instruction : 3 times", "Fluoride application : 2 times(every 6 months)", "Sealant : every tooth with deep pit", "Scaling 1 times"},
						{"Oral exam : 6 times (every 2 months)", "Tooth brushing Instruction : 4 times(every 3 months)", "Fluoride application : 4 times(every 3 months)", "Fluoride mouth rinse : everyday", "Sealant : every tooth with deep pit", "Scaling : 2 times(every 6 months)"}
				};
				
				//청년
				String[][] result_age4 = {{"Oral exam : 3 times (every 4 months)", "Tooth brushing Instruction : 2 times", "Fluoride application : 1 times", "Scaling : 1 times"},
						{"Oral exam : 4 times (every 3 months)", "Tooth brushing Instruction : 3 times", "Fluoride application : 2 times(every 6 months)", "Scaling 1 times"},
						{"Oral exam : 6 times (every 2 months)", "Tooth brushing Instruction : 4 times(every 3 months)", "Fluoride application : 1 times", "Scaling : 1 times"}
				};
				
				//장년, 노년
				String[][] result_age5 = {{"Oral exam : 2 times (every 6 months)", "Tooth brushing Instruction : 2 times (every 6 months)", "Scaling : 1 times (year)"},
						{"Oral exam : 3 times (every 4 months)", "Tooth brushing Instruction : 3 times (every 4 months)", "Scaling : 2 times (every 6 months)"},
						{"Oral exam : 4 times (every 3 months)", "Tooth brushing Instruction : 4 times (every 3 months)", "Scaling : 3 times (every 4 months)", "fluoride application on hypersensitive tooth"}
				};
				
				
				
				String[] result_mal = {"Orthodontic tooth brush", "Interdental brush", "Water pik(recommend)", "Rubber tip", "PMTC 2 times"};
				
				String[][] result_age4_per = {{"Oral exam : 3 times (every 4 months)", "Tooth brushing Instruction : 2 times", "Fluoride application : 1 times", "Scaling : 1 times", "Interdental brush"},
						{"Oral exam : 4 times (every 3 months)", "Tooth brushing Instruction : 3 times", "Fluoride application : 2 times(every 6 months)", "Scaling 1 times", "Interdental brush", "Gingival massager"},
						{"Oral exam : 6 times (every 2 months)", "Tooth brushing Instruction : 4 times(every 3 months)", "Fluoride application : 1 times", "Scaling : 1 times", "PMTC 2 times", "Interdental brush", "Gingival massager or Rubber tip recommend", "Water pik(recommend)"}
				};
				
				String[][] result_age5_per = {{"Oral exam : 3 times (every 4 months)", "Tooth brushing Instruction : 2 times (every 6 months)", "Scaling : 1 times (year)", "Interdental brush"},
						{"Oral exam : 4 times (every 3 months)", "Tooth brushing Instruction : 3 times (every 4 months)", "Scaling : 2 times (every 6 months)", "Interdental brush", "Gingival massager"},
						{"Oral exam : 6 times (every 2 months)", "Tooth brushing Instruction : 4 times (every 3 months)", "Scaling : 3 times (every 4 months)", "Interdental brush", "Rubber tip", "Superfloss", "Gingival massager", "Water pik(recommend)"}
				};
				
				String[] result_preg = {"Interdental brush", "Oral health education for pregnant woman", "Cosmetic mouth rinse", "oral malodor control"};
				
				String[] result_imp = {"Interdental brush", "Superfloss", "Gingival massager", "Rubber tip", "Water pik(recommend)"};
				
				String[] result_den = {"Denture brush", "Denture cleaning tablet"};
		
				
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
			if(bean.getMalocclusion() == "On treatment"){
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
			if(bean.getMalocclusion() == "On treatment"){
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
			if(bean.getMalocclusion() == "On treatment"){
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
			if(bean.getPregnancy() == "during pregnancy"){
				for(int i=0; i<4; i++){
					str.append(result_preg[i] + "\n");
				}
			}
			break;
			
		case 5:
			for(int j = 0; j<result_age5[scorePart].length; j++){
				str.append(result_age5[scorePart][j] + "\n");
			}
			if(bean.getGingivitis() != "None"){
				str = null;
				for(int j = 0; j<result_age5_per[scorePart].length; j++){
					str.append(result_age5_per[scorePart][j] + "\n");
				}
			}
			if(bean.getProsthesis_need() == "Need bridge and wearing" || bean.getDenture_need() == "Need and wearing"){
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
			if(bean.getPartialDenture() != "None" ){
				for(int i = 0; i<2; i++){
					str.append(result_den[i] + "\n");
				}
			}
			break;
			
		case 6:
			for(int j = 0; j<result_age5[scorePart].length; j++){
				str.append(result_age5[scorePart][j] + "\n");
			}
			if(bean.getGingivitis() != "None"){
				str = null;
				for(int j = 0; j<result_age5_per[scorePart].length; j++){
					str.append(result_age5_per[scorePart][j] + "\n");
				}
			}
			if(bean.getProsthesis_need() == "Need bridge and wearing" || bean.getDenture_need() == "Need and wearing"){
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
			if(bean.getPartialDenture() != "None" ){
				for(int i = 0; i<2; i++){
					str.append(result_den[i] + "\n");
				}
			}
			break;
		}
		System.out.println("str3 : " + str);
		System.out.println("str3 : " + str.toString());
		TextArea view = new TextArea("", 10, 30, TextArea.SCROLLBARS_NONE);
		view.setEditable(false);
		view.setText(str.toString());
		panel.add(view);
		this.add(panel);
		
		System.out.println(str);
	}
}
