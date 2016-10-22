

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class CalendarView_K extends JPanel {
	
	public CalendarView_K(PatientInfoBean_K bean, float score_total) {
		// TODO Auto-generated constructor stub
		
		JPanel panel = new JPanel();
		

		//������
				String[][] resultCalendar_age1 = {{"��������", "2", "�̴۱� ����", "2", "�Ҽҵ���", "1"},
						{"��������", "3", "�̴۱� ����", "3", "�Ҽҵ���", "2"},
						{"��������", "4", "�̴۱� ����", "4", "�Ҽҵ���", "4"}
				};
				
				//�Ƶ�
				String[][] resultCalendar_age2 = {{"��������", "3", "�̴۱� ����", "3", "�Ҽҵ���", "1"},
						{"��������", "4", "�̴۱� ����", "4", "�Ҽҵ���", "2"},
						{"��������", "4", "�̴۱� ����", "4", "�Ҽҵ���", "4"}
				};
				
				//û�ҳ�
				String[][] resultCalendar_age3 = {{"��������", "3", "�̴۱� ����", "2", "�Ҽҵ���", "1"},
						{"��������", "4", "�̴۱� ����", "3", "�Ҽҵ���", "2"},
						{"��������", "6", "�̴۱� ����", "4", "�Ҽҵ���", "4"}
				};
				
				//û��
				String[][] resultCalendar_age4 = {{"��������", "3", "�̴۱� ����", "2", "�Ҽҵ���", "1", "���̸�", "1"},
						{"��������", "4", "�̴۱� ����", "3", "�Ҽҵ���", "2", "���̸�", "1"},
						{"��������", "6", "�̴۱� ����", "4", "�Ҽҵ���", "1", "���̸�", "1"}
				};
				//���, ���				
				String[][] resultCalendar_age5 = {{"��������", "2", "�̴۱� ����", "2", "���̸�", "1"},
						{"��������", "3", "�̴۱� ����", "3", "���̸�", "2"},
						{"��������", "4", "�̴۱� ����", "4", "���̸�", "3"}
				};
				
				
				
				
				
				
				String[] result_preg = {"ġ��ĩ�� ���", "�ӻ�� ��������", "���༺�� ������ġ�� ���", "���� ���� ����"};
				
				String[] result_imp = {"ġ��ĩ�� ���", "�����÷ν� ���", "ġ�������� ���", "��ġ���ڱر� ���", "������� ��� ����"};
				
				String[] result_den = {"��ġ�� ĩ�� ���", "��ġ������ ���"};
				
		JPanel schedulePanel = new JPanel();
		TitledBorder TB = new TitledBorder("���� �޷�");
		TB.setTitleJustification(TitledBorder.CENTER);
		schedulePanel.setBorder(TB);
		
		JTextArea P1 = new JTextArea(10,10);
		P1.setEditable(false);
		JTextArea P2 = new JTextArea(10,10);
		P2.setEditable(false);
		JTextArea P3 = new JTextArea(10,10);
		P3.setEditable(false);
		JTextArea P4 = new JTextArea(10,10);
		P4.setEditable(false);
		JTextArea P5 = new JTextArea(10,10);
		P5.setEditable(false);
		JTextArea P6 = new JTextArea(10,10);
		P6.setEditable(false);
		JTextArea P7 = new JTextArea(10,10);
		P7.setEditable(false);
		JTextArea P8 = new JTextArea(10,10);
		P8.setEditable(false);
		JTextArea P9 = new JTextArea(10,10);
		P9.setEditable(false);
		JTextArea P10 = new JTextArea(10,10);
		P10.setEditable(false);
		JTextArea P11 = new JTextArea(10,10);
		P11.setEditable(false);
		JTextArea P12 = new JTextArea(10,10);
		P12.setEditable(false);
		Border border = LineBorder.createGrayLineBorder();
		P1.setBorder(border);
		P2.setBorder(border);
		P3.setBorder(border);
		P4.setBorder(border);
		P5.setBorder(border);
		P6.setBorder(border);
		P7.setBorder(border);
		P8.setBorder(border);
		P9.setBorder(border);
		P10.setBorder(border);
		P11.setBorder(border);
		P12.setBorder(border);
		P1.setPreferredSize(new Dimension(200, 150));
		P2.setPreferredSize(new Dimension(200, 150));
		P3.setPreferredSize(new Dimension(200, 150));
		P4.setPreferredSize(new Dimension(200, 150));
		P5.setPreferredSize(new Dimension(200, 150));
		P6.setPreferredSize(new Dimension(200, 150));
		P7.setPreferredSize(new Dimension(200, 150));
		P8.setPreferredSize(new Dimension(200, 150));
		P9.setPreferredSize(new Dimension(200, 150));
		P10.setPreferredSize(new Dimension(200, 150));
		P11.setPreferredSize(new Dimension(200, 150));
		P12.setPreferredSize(new Dimension(200, 150));

		// ��ġ ����!
		schedulePanel.setLayout(new GridBagLayout());
		schedulePanel.add(P1, new GridBagConstraints(0, 0, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		schedulePanel.add(P2, new GridBagConstraints(1, 0, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		schedulePanel.add(P3, new GridBagConstraints(2, 0, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		schedulePanel.add(P4, new GridBagConstraints(3, 0, 1, 1, 
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		schedulePanel.add(P5, new GridBagConstraints(0, 1, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		schedulePanel.add(P6, new GridBagConstraints(1, 1, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		schedulePanel.add(P7, new GridBagConstraints(2, 1, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		schedulePanel.add(P8, new GridBagConstraints(3, 1, 1, 1, 
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		schedulePanel.add(P9, new GridBagConstraints(0, 2, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		schedulePanel.add(P10, new GridBagConstraints(1, 2, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		schedulePanel.add(P11, new GridBagConstraints(2, 2, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		schedulePanel.add(P12, new GridBagConstraints(3, 2, 1, 1, 
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		
				
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
		
		//StringBuilder str = new StringBuilder("");
		//StringBuilder str_w = new StringBuilder("");
		ArrayList<String> str = new ArrayList<String>();
			
		
		switch(agePart){
		
		case 1:
			for(int j = 0; j<resultCalendar_age1[scorePart].length; j++){
				str.add(resultCalendar_age1[scorePart][j]);
				//str_w.append(resultInt_age1[scorePart][j] + " ");
			}
			break;
			
		case 2:
			for(int j = 0; j<resultCalendar_age2[scorePart].length; j++){
				str.add(resultCalendar_age2[scorePart][j]);
			}
			break;
			
		case 3:
			for(int j = scorePart ;  j<resultCalendar_age3[scorePart].length; j++){
					str.add(resultCalendar_age3[scorePart][j]);
			}
			break;
			
		case 4:
			for(int j = 0; j<resultCalendar_age4[scorePart].length; j++){
				str.add(resultCalendar_age4[scorePart][j]);
			}
			if(bean.getPregnancy() == "during pregnancy"){
				for(int i=0; i<4; i++){
					str.add(result_preg[i]);
				}
			}
			break;
			
		case 5:
			for(int j = 0; j<resultCalendar_age5[scorePart].length; j++){
				str.add(resultCalendar_age5[scorePart][j]);
			}
			/*
			if(bean.getGingivitis() != "None"){
				str = null;
				for(int j = 0; j<resultCalendar_age5_per[scorePart].length; j++){
					str.add(resultCalendar_age5_per[scorePart][j]);
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
					str.add(result_imp[i]);
				}
			}
			if(bean.getPartialDenture() != "None" ){
				for(int i = 0; i<2; i++){
					str.add(result_den[i]);
				}
			}
			*/
			break;
			
		case 6:
			for(int j = 0; j<resultCalendar_age5[scorePart].length; j++){
				str.add(resultCalendar_age5[scorePart][j]);
			}
			/*
			if(bean.getGingivitis() != "None"){
				str = null;
				for(int j = 0; j<resultCalendar_age5_per[scorePart].length; j++){
					str.add(resultCalendar_age5_per[scorePart][j]);
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
					str.add(result_imp[i]);
				}
			}
			if(bean.getPartialDenture() != "None" ){
				for(int i = 0; i<2; i++){
					str.add(result_den[i]);
				}
			}
			*/
			break;
		}
		System.out.println("str3 : " + str);
		
		StringBuilder strP1 = new StringBuilder("");
		StringBuilder strP2 = new StringBuilder("");
		StringBuilder strP3 = new StringBuilder("");
		StringBuilder strP4 = new StringBuilder("");
		StringBuilder strP5 = new StringBuilder("");
		StringBuilder strP6 = new StringBuilder("");
		StringBuilder strP7 = new StringBuilder("");
		StringBuilder strP8 = new StringBuilder("");
		StringBuilder strP9 = new StringBuilder("");
		StringBuilder strP10 = new StringBuilder("");
		StringBuilder strP11 = new StringBuilder("");
		StringBuilder strP12 = new StringBuilder("");
		
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH);
		String monthView = "";
		switch(month){
		case 1:
			monthView = "1��";
			break;
		case 2:
			monthView = "2��";
			break;
		case 3:
			monthView = "3��";
			break;
		case 4:
			monthView = "4��";
			break;
		case 5:
			monthView = "5��";
			break;
		case 6:
			monthView = "6��";
			break;
		case 7:
			monthView = "7��";
			break;
		case 8:
			monthView = "8��";
			break;
		case 9:
			monthView = "9��";
			break;
		case 10:
			monthView = "10��";
			break;
		case 11:
			monthView = "11��";
			break;
		case 12:
			monthView = "12��";
			break;			
		}
		strP1.append("\n" + "   < " + monthView + " >" + "\n\n");
		month++;
		if(month>12){month = 1;}
		switch(month){
		case 1:
			monthView = "1��";
			break;
		case 2:
			monthView = "2��";
			break;
		case 3:
			monthView = "3��";
			break;
		case 4:
			monthView = "4��";
			break;
		case 5:
			monthView = "5��";
			break;
		case 6:
			monthView = "6��";
			break;
		case 7:
			monthView = "7��";
			break;
		case 8:
			monthView = "8��";
			break;
		case 9:
			monthView = "9��";
			break;
		case 10:
			monthView = "10��";
			break;
		case 11:
			monthView = "11��";
			break;
		case 12:
			monthView = "12��";
			break;			
		}
		strP2.append("\n" + "   < "+ monthView + " >" + "\n\n");
		month++;
		if(month>12){month = 1;}
		switch(month){
		case 1:
			monthView = "1��";
			break;
		case 2:
			monthView = "2��";
			break;
		case 3:
			monthView = "3��";
			break;
		case 4:
			monthView = "4��";
			break;
		case 5:
			monthView = "5��";
			break;
		case 6:
			monthView = "6��";
			break;
		case 7:
			monthView = "7��";
			break;
		case 8:
			monthView = "8��";
			break;
		case 9:
			monthView = "9��";
			break;
		case 10:
			monthView = "10��";
			break;
		case 11:
			monthView = "11��";
			break;
		case 12:
			monthView = "12��";
			break;	
		}
		strP3.append("\n" + "   < " + monthView + " >" + "\n\n");
		month++;
		if(month>12){month = 1;}
		switch(month){
		case 1:
			monthView = "1��";
			break;
		case 2:
			monthView = "2��";
			break;
		case 3:
			monthView = "3��";
			break;
		case 4:
			monthView = "4��";
			break;
		case 5:
			monthView = "5��";
			break;
		case 6:
			monthView = "6��";
			break;
		case 7:
			monthView = "7��";
			break;
		case 8:
			monthView = "8��";
			break;
		case 9:
			monthView = "9��";
			break;
		case 10:
			monthView = "10��";
			break;
		case 11:
			monthView = "11��";
			break;
		case 12:
			monthView = "12��";
			break;	
		}
		strP4.append("\n" + "   < " + monthView + " >" + "\n\n");
		month++;
		if(month>12){month = 1;}
		switch(month){
		case 1:
			monthView = "1��";
			break;
		case 2:
			monthView = "2��";
			break;
		case 3:
			monthView = "3��";
			break;
		case 4:
			monthView = "4��";
			break;
		case 5:
			monthView = "5��";
			break;
		case 6:
			monthView = "6��";
			break;
		case 7:
			monthView = "7��";
			break;
		case 8:
			monthView = "8��";
			break;
		case 9:
			monthView = "9��";
			break;
		case 10:
			monthView = "10��";
			break;
		case 11:
			monthView = "11��";
			break;
		case 12:
			monthView = "12��";
			break;		
		}
		strP5.append("\n" + "   < " + monthView + " >" + "\n\n");
		month++;
		if(month>12){month = 1;}
		switch(month){
		case 1:
			monthView = "1��";
			break;
		case 2:
			monthView = "2��";
			break;
		case 3:
			monthView = "3��";
			break;
		case 4:
			monthView = "4��";
			break;
		case 5:
			monthView = "5��";
			break;
		case 6:
			monthView = "6��";
			break;
		case 7:
			monthView = "7��";
			break;
		case 8:
			monthView = "8��";
			break;
		case 9:
			monthView = "9��";
			break;
		case 10:
			monthView = "10��";
			break;
		case 11:
			monthView = "11��";
			break;
		case 12:
			monthView = "12��";
			break;	
		}
		strP6.append("\n" + "   < " + monthView + " >" + "\n\n");
		month++;
		if(month>12){month = 1;}
		switch(month){
		case 1:
			monthView = "1��";
			break;
		case 2:
			monthView = "2��";
			break;
		case 3:
			monthView = "3��";
			break;
		case 4:
			monthView = "4��";
			break;
		case 5:
			monthView = "5��";
			break;
		case 6:
			monthView = "6��";
			break;
		case 7:
			monthView = "7��";
			break;
		case 8:
			monthView = "8��";
			break;
		case 9:
			monthView = "9��";
			break;
		case 10:
			monthView = "10��";
			break;
		case 11:
			monthView = "11��";
			break;
		case 12:
			monthView = "12��";
			break;	
		}
		strP7.append("\n" + "   < " + monthView + " >" + "\n\n");
		month++;
		if(month>12){month = 1;}
		switch(month){
		case 1:
			monthView = "1��";
			break;
		case 2:
			monthView = "2��";
			break;
		case 3:
			monthView = "3��";
			break;
		case 4:
			monthView = "4��";
			break;
		case 5:
			monthView = "5��";
			break;
		case 6:
			monthView = "6��";
			break;
		case 7:
			monthView = "7��";
			break;
		case 8:
			monthView = "8��";
			break;
		case 9:
			monthView = "9��";
			break;
		case 10:
			monthView = "10��";
			break;
		case 11:
			monthView = "11��";
			break;
		case 12:
			monthView = "12��";
			break;	
		}
		strP8.append("\n" + "   < " + monthView + " >" + "\n\n");
		month++;
		if(month>12){month = 1;}
		switch(month){
		case 1:
			monthView = "1��";
			break;
		case 2:
			monthView = "2��";
			break;
		case 3:
			monthView = "3��";
			break;
		case 4:
			monthView = "4��";
			break;
		case 5:
			monthView = "5��";
			break;
		case 6:
			monthView = "6��";
			break;
		case 7:
			monthView = "7��";
			break;
		case 8:
			monthView = "8��";
			break;
		case 9:
			monthView = "9��";
			break;
		case 10:
			monthView = "10��";
			break;
		case 11:
			monthView = "11��";
			break;
		case 12:
			monthView = "12��";
			break;	
		}
		strP9.append("\n" + "   < " + monthView + " >" + "\n\n");
		month++;
		if(month>12){month = 1;}
		switch(month){
		case 1:
			monthView = "1��";
			break;
		case 2:
			monthView = "2��";
			break;
		case 3:
			monthView = "3��";
			break;
		case 4:
			monthView = "4��";
			break;
		case 5:
			monthView = "5��";
			break;
		case 6:
			monthView = "6��";
			break;
		case 7:
			monthView = "7��";
			break;
		case 8:
			monthView = "8��";
			break;
		case 9:
			monthView = "9��";
			break;
		case 10:
			monthView = "10��";
			break;
		case 11:
			monthView = "11��";
			break;
		case 12:
			monthView = "12��";
			break;	
		}
		strP10.append("\n" + "   < " + monthView + " >"+ "\n\n");
		month++;
		if(month>12){month = 1;}
		switch(month){
		case 1:
			monthView = "1��";
			break;
		case 2:
			monthView = "2��";
			break;
		case 3:
			monthView = "3��";
			break;
		case 4:
			monthView = "4��";
			break;
		case 5:
			monthView = "5��";
			break;
		case 6:
			monthView = "6��";
			break;
		case 7:
			monthView = "7��";
			break;
		case 8:
			monthView = "8��";
			break;
		case 9:
			monthView = "9��";
			break;
		case 10:
			monthView = "10��";
			break;
		case 11:
			monthView = "11��";
			break;
		case 12:
			monthView = "12��";
			break;	
		}
		strP11.append("\n" + "   < " + monthView + " >" + "\n\n");
		month++;
		if(month>12){month = 1;}
		switch(month){
		case 1:
			monthView = "1��";
			break;
		case 2:
			monthView = "2��";
			break;
		case 3:
			monthView = "3��";
			break;
		case 4:
			monthView = "4��";
			break;
		case 5:
			monthView = "5��";
			break;
		case 6:
			monthView = "6��";
			break;
		case 7:
			monthView = "7��";
			break;
		case 8:
			monthView = "8��";
			break;
		case 9:
			monthView = "9��";
			break;
		case 10:
			monthView = "10��";
			break;
		case 11:
			monthView = "11��";
			break;
		case 12:
			monthView = "12��";
			break;	
		}
		strP12.append("\n" + "   < " + monthView + " >" + "\n\n");
		month++;
		
		
		
		switch(str.get(1)){
		case "2":
			strP1.append("   " + str.get(0) + "\n");
			strP7.append("   " + str.get(0) + "\n");
			break;
		case "3":
			strP1.append("   " + str.get(0) + "\n");
			strP5.append("   " + str.get(0) + "\n");
			strP9.append("   " + str.get(0) + "\n");
			break;
		case "4":
			strP1.append("   " + str.get(0) + "\n");
			strP4.append("   " + str.get(0) + "\n");
			strP7.append("   " + str.get(0) + "\n");
			strP10.append("   " + str.get(0) + "\n");
			break;
		case "6":
			strP1.append("   " + str.get(0) + "\n");
			strP3.append("   " + str.get(0) + "\n");
			strP5.append("   " + str.get(0) + "\n");
			strP7.append("   " + str.get(0) + "\n");
			strP9.append("   " + str.get(0) + "\n");
			strP11.append("   " + str.get(0) + "\n");
			break;
		}
		
		switch(str.get(3)){
		case "2":
			switch(str.get(1)){
			case "2":
				strP1.append("   " + str.get(2) + "\n");
				strP7.append("   " + str.get(2) + "\n");
				break;
			case "3":
				strP1.append("   " + str.get(2) + "\n");
				strP5.append("   " + str.get(2) + "\n");
				break;
			case "4":
				strP1.append("   " + str.get(2) + "\n");
				strP7.append("   " + str.get(2) + "\n");
				break;
			case "6":
				strP1.append("   " + str.get(2) + "\n");
				strP7.append("   " + str.get(2) + "\n");
				break;
			}
			break;
		case "3":
			switch(str.get(1)){
			case "3":
				strP1.append("   " + str.get(2) + "\n");
				strP5.append("   " + str.get(2) + "\n");
				strP9.append("   " + str.get(2) + "\n");
				break;
			case "4":
				strP1.append("   " + str.get(2) + "\n");
				strP4.append("   " + str.get(2) + "\n");
				strP7.append("   " + str.get(2) + "\n");
				break;
			case "6":
				strP1.append("   " + str.get(2) + "\n");
				strP5.append("   " + str.get(2) + "\n");
				strP9.append("   " + str.get(2) + "\n");
				break;
			}
			break;
		case "4":
			switch(str.get(1)){
			case "4":
				strP1.append("   " + str.get(2) + "\n");
				strP4.append("   " + str.get(2) + "\n");
				strP7.append("   " + str.get(2) + "\n");
				strP10.append("   " + str.get(2) + "\n");
				break;
			case "6":
				strP1.append("   " + str.get(2) + "\n");
				strP3.append("   " + str.get(2) + "\n");
				strP7.append("   " + str.get(2) + "\n");
				strP11.append("   " + str.get(2) + "\n");
				break;
			}
			
			break;
		}
		
		switch(str.get(5)){
		case "1":
			strP1.append("   " + str.get(4) + "\n");
			break;
		case "2":
			switch(str.get(1)){
			case "2":
				strP1.append("   " + str.get(4) + "\n");
				strP7.append("   " + str.get(4) + "\n");
				break;
			case "3":
				strP1.append("   " + str.get(4) + "\n");
				strP5.append("   " + str.get(4) + "\n");
				break;
			case "4":
				strP1.append("   " + str.get(4) + "\n");
				strP7.append("   " + str.get(4) + "\n");
				break;
			case "6":
				strP1.append("   " + str.get(4) + "\n");
				strP7.append("   " + str.get(4) + "\n");
				break;
			}
			break;
		case "3":
			switch(str.get(1)){
			case "3":
				strP1.append("   " + str.get(4) + "\n");
				strP5.append("   " + str.get(4) + "\n");
				strP9.append("   " + str.get(4) + "\n");
				break;
			case "4":
				strP1.append("   " + str.get(4) + "\n");
				strP4.append("   " + str.get(4) + "\n");
				strP7.append("   " + str.get(4) + "\n");
				break;
			case "6":
				strP1.append("   " + str.get(4) + "\n");
				strP5.append("   " + str.get(4) + "\n");
				strP9.append("   " + str.get(4) + "\n");
				break;
			}
			break;
		case "4":
			switch(str.get(1)){
			case "4":
				strP1.append("   " + str.get(4) + "\n");
				strP4.append("   " + str.get(4) + "\n");
				strP7.append("   " + str.get(4) + "\n");
				strP10.append("   " + str.get(4) + "\n");
				break;
			case "6":
				strP1.append("   " + str.get(4) + "\n");
				strP3.append("   " + str.get(4) + "\n");
				strP7.append("   " + str.get(4) + "\n");
				strP11.append("   " + str.get(4) + "\n");
				break;
			}
			break;
		case "6":
			strP1.append("   " + str.get(4) + "\n");
			strP3.append("   " + str.get(4) + "\n");
			strP5.append("   " + str.get(4) + "\n");
			strP7.append("   " + str.get(4) + "\n");
			strP9.append("   " + str.get(4) + "\n");
			strP11.append("   " + str.get(4) + "\n");
			break;
		}
		
		/*
		for(int i=0; i<strP1.size(); i++){
			P1.setText(strP1.toString());
		}
		*/
		P1.setText(strP1.toString());
		P2.setText(strP2.toString());
		P3.setText(strP3.toString());
		P4.setText(strP4.toString());
		P5.setText(strP5.toString());
		P6.setText(strP6.toString());
		P7.setText(strP7.toString());
		P8.setText(strP8.toString());
		P9.setText(strP9.toString());
		P10.setText(strP10.toString());
		P11.setText(strP11.toString());
		P12.setText(strP12.toString());
		
		
		
		panel.add(schedulePanel);
		this.add(panel);
		
		System.out.println(str.get(0));
		System.out.println(str.get(1));
		System.out.println(str.get(2));
	}
}
