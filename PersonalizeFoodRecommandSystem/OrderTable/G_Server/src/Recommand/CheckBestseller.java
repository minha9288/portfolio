package Recommand;

import java.sql.*;
import java.util.ArrayList;
import java.io.*;

public class CheckBestseller {
	Connection conn;
	Statement stmt = null;
	
	String ko, jp, cn, usa, en, fr; //����
	String asia,  america, europ;  //���
	String user_nation, user_id, user_sex, user_age;
	int food_id;
	int user_age2;//string�� int��
	String food_id2;
	
	//** �ִ�
	String best; //����Ʈ����
	//* ���� �ִ�
	String korean_best;
	String japanese_best;
	String chinese_best;
	String american_best;
	String english_best;
	String french_best;
	//* ��躰 �ִ�
	String asian_best;
	String european_best;
		
    //* ���� �ִ�
	String woman_best;
	String man_best;
	
	//* ���� �ִ�
	String age_best, age10_best, age20_best, age30_best, age40_best, age50_best, age60_best, age70_best, age80_best, age90_best;

	
	int max; 
	
	ArrayList<String> storage; //�����̸����� ����
	
	public CheckBestseller() {		
		try {
			Class.forName("com.mysql.jdbc.Driver"); // MySQL ����̹� �ε�
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root","1111"); // JDBC ����
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC ����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("DB ���� ����");
		}	
	}
	
	
	public void Nation(){
		
		try{ //DB�� ���� ����� �������� ����
			
			Statement stmt = conn.createStatement(); // DB�� SQL�� ������ �� �մ� Statement��ü ����
			Statement stmt2 = conn.createStatement();
			
			String query = "select * from user";
			String query2 = "select * from ordered";
			String query3 = "select user_nation, food_id "
					+ "from user,ordered "
					+ "where user.user_id=ordered.user_id ";
			
           	ResultSet rs = stmt.executeQuery(query3); //user���̺�
           	ResultSet rs2 = stmt2.executeQuery(query2); //ordered���̺�
           	
           	int ko1=0;
           	int ko2=0;
           	int ko3=0;
           	int ko4=0;
           	int ko5=0;
           	int ko6=0;
           	int ko7=0;
           	int ko8=0;
           	int ko9=0;
           	int ko10=0;
           	
           	int jp1=0;
           	int jp2=0;
           	int jp3=0;
           	int jp4=0;
           	int jp5=0;
           	int jp6=0;
           	int jp7=0;
           	int jp8=0;
           	int jp9=0;
           	int jp10=0;
           	
           	int ch1=0;
           	int ch2=0;
           	int ch3=0;
           	int ch4=0;
           	int ch5=0;
           	int ch6=0;
           	int ch7=0;
           	int ch8=0;
           	int ch9=0;
           	int ch10=0;
           	
           	int us1=0;
           	int us2=0;
           	int us3=0;
           	int us4=0;
           	int us5=0;
           	int us6=0;
           	int us7=0;
           	int us8=0;
           	int us9=0;
           	int us10=0;
 	
           	int fr1=0;
           	int fr2=0;
           	int fr3=0;
           	int fr4=0;
           	int fr5=0;
           	int fr6=0;
           	int fr7=0;
           	int fr8=0;
           	int fr9=0;
           	int fr10=0;
           	
        	int test=0;
        	//String food_id2;
        	
        	while(rs.next()){

				user_nation = rs.getString("user_nation");
				food_id = rs.getInt("food_id");
				String food_id2 = Integer.toString(food_id);
				
				if(user_nation.equals("ko")){
					test=1;
					if(rs.getInt("food_id")==1){  //1�� �ȵǰ� 2�� ��.. 
						ko1++;;
					}
					else if(rs.getInt("food_id")==2){
						ko2++;
					}
					else if(rs.getInt("food_id")==3){
						ko3++;
					}
					else if(rs.getInt("food_id")==4){
						ko4++;
					}
					else if(rs.getInt("food_id")==5){
						ko5++;
					}
					else if(rs.getInt("food_id")==6){
						ko6++;
					}
					else if(rs.getInt("food_id")==7){
						ko7++;
					}
					else if(rs.getInt("food_id")==8){
						ko8++;
					}
					else if(rs.getInt("food_id")==9){
						ko9++;
					}
					else if(rs.getInt("food_id")==10){
						ko10++;
					}
					int ko[]={ko1, ko2, ko3, ko4, ko5, ko6, ko7, ko8, ko9, ko10};
		            
		            max=ko[0];
		            for(int i=0; i<ko.length; i++){
		            	if(ko[i]> max)
		            		max = ko[i];
		            }
		            if(max==ko1){
						korean_best = "�����";
					}
					else if(max==ko2){
						korean_best = "�Ұ�ⵤ��";
					}
					else if(max==ko3){
						korean_best = "��õ�߰���";
					}
					else if(max==ko4){
						korean_best = "�δ��";
					}
					else if(max==ko5){
						korean_best = "�����";
					}
					else if(max==ko6){
						korean_best = "�ع�����";
					}
					else if(max==ko7){
						korean_best = "�ع���";
					}
					else if(max==ko8){
						korean_best = "�ż���";
					}
					else if(max==ko9){
						korean_best = "ȭ����";
					}
					else if(max==ko10){
						korean_best = "���߶�����";
					}
				}
				else if(user_nation.equals("jp")){
					if(rs.getInt("food_id")==1){  //1�� �ȵǰ� 2�� ��.. 
						jp1++;
					}
					else if(rs.getInt("food_id")==2){
						jp2++;
					}
					else if(rs.getInt("food_id")==3){
						jp3++;
					}
					else if(rs.getInt("food_id")==4){
						jp4++;
					}
					else if(rs.getInt("food_id")==5){
						jp5++;
					}
					else if(rs.getInt("food_id")==6){
						jp6++;
					}
					else if(rs.getInt("food_id")==7){
						jp7++;
					}
					else if(rs.getInt("food_id")==8){
						jp8++;
					}
					else if(rs.getInt("food_id")==9){
						jp9++;
					}
					else if(rs.getInt("food_id")==10){
						jp10++;
					}
					int jp[]={jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8,jp9,jp10};
		            
		            max=jp[0];
		            for(int i=0; i<jp.length; i++){
		            	if(jp[i]> max)
		            		max = jp[i];
		            }
		            if(max==jp1){
		            	japanese_best = "�����";
					}
					else if(max==jp2){
						japanese_best = "�Ұ�ⵤ��";
					}
					else if(max==jp3){
						japanese_best = "��õ�߰���";
					}
					else if(max==jp4){
						japanese_best = "�δ��";
					}
					else if(max==jp5){
						japanese_best = "�����";
					}
					else if(max==jp6){
						japanese_best = "�ع�����";
					}
					else if(max==jp7){
						japanese_best = "�ع���";
					}
					else if(max==jp8){
						japanese_best = "�ż���";
					}
					else if(max==jp9){
						japanese_best = "ȭ����";
					}
					else if(max==jp10){
						japanese_best = "���߶�����";
					}
				}
				else if(user_nation.equals("ch")){
					if(rs.getInt("food_id")==1){  //1�� �ȵǰ� 2�� ��.. 
						ch1++;
					}
					else if(rs.getInt("food_id")==2){
						ch2++;
					}
					else if(rs.getInt("food_id")==3){
						ch3++;
					}
					else if(rs.getInt("food_id")==4){
						ch4++;
					}
					else if(rs.getInt("food_id")==5){
						ch5++;
					}
					else if(rs.getInt("food_id")==6){
						ch6++;
					}
					else if(rs.getInt("food_id")==7){
						ch7++;
					}
					else if(rs.getInt("food_id")==8){
						ch8++;
					}
					else if(rs.getInt("food_id")==9){
						ch9++;
					}
					else if(rs.getInt("food_id")==10){
						ch10++;
					}
					int cn[]={ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8,ch9,ch10};
		            
		            max=cn[0];
		            for(int i=0; i<cn.length; i++){
		            	if(cn[i]> max)
		            		max = cn[i];
		            }
		            if(max==ch1){
		            	chinese_best = "�����";
					}
					else if(max==ch2){
						chinese_best = "�Ұ�ⵤ��";
					}
					else if(max==ch3){
						chinese_best = "��õ�߰���";
					}
					else if(max==ch4){
						chinese_best = "�δ��";
					}
					else if(max==ch5){
						chinese_best = "�����";
					}
					else if(max==ch6){
						chinese_best = "�ع�����";
					}
					else if(max==ch7){
						chinese_best = "�ع���";
					}
					else if(max==ch8){
						chinese_best = "�ż���";
					}
					else if(max==ch9){
						chinese_best = "ȭ����";
					}
					else if(max==ch10){
						chinese_best = "���߶�����";
					}
				}
				
				if(user_nation.equals("us")){ //�̱�
					if(rs.getInt("food_id")==1){ //�����
						us1++;
					}
					else if(rs.getInt("food_id")==2){ 
						us2++;
					}
					else if(rs.getInt("food_id")==3){
						us3++;
					}
					else if(rs.getInt("food_id")==4){
						us4++;
					}
					else if(rs.getInt("food_id")==5){
						us5++;
					}
					else if(rs.getInt("food_id")==6){
						us6++;
					}
					else if(rs.getInt("food_id")==7){
						us7++;
					}
					else if(rs.getInt("food_id")==8){
						us8++;
					}
					else if(rs.getInt("food_id")==9){
						us9++;
					}
					else if(rs.getInt("food_id")==10){
						us10++;
					}
					int us[]={us1,us2,us3,us4,us5,us6,us7,us8,us9,us10};
		            
		            max=us[0];
		            for(int i=0; i<us.length; i++){
		            	if(us[i]> max)
		            		max =us[i];
		            }
		            if(max==us1){
		            	american_best = "�����";
					}
					else if(max==us2){
						american_best = "�Ұ�ⵤ��";
					}
					else if(max==us3){
						american_best = "��õ�߰���";
					}
					else if(max==us4){
						american_best = "�δ��";
					}
					else if(max==us5){
						american_best = "�����";
					}
					else if(max==us6){
						american_best = "�ع�����";
					}
					else if(max==us7){
						american_best = "�ع���";
					}
					else if(max==us8){
						american_best = "�ż���";
					}
					else if(max==us9){
						american_best = "ȭ����";
					}
					else if(max==us10){
						american_best = "���߶�����";
					}
				}
				
				if(user_nation.equals("fr")){ //������
					if(rs.getInt("food_id")==1){ //�����
						fr1++;
					}
					else if(rs.getInt("food_id")==2){ 
						fr2++;
					}
					else if(rs.getInt("food_id")==3){
						fr3++;
					}
					else if(rs.getInt("food_id")==4){
						fr4++;
					}
					else if(rs.getInt("food_id")==5){
						fr5++;
					}
					else if(rs.getInt("food_id")==6){
						fr6++;
					}
					else if(rs.getInt("food_id")==7){
						fr7++;
					}
					else if(rs.getInt("food_id")==8){
						fr8++;
					}
					else if(rs.getInt("food_id")==9){
						fr9++;
					}
					else if(rs.getInt("food_id")==10){
						fr10++;
					}
					int fr[]={fr1,fr2,fr3,fr4,fr5,fr6,fr7,fr8,fr9,fr10};
		            
		            max=fr[0];
		            for(int i=0; i<fr.length; i++){
		            	if(fr[i]> max)
		            		max = fr[i];
		            }
		            if(max==fr1){
		            	french_best = "�����";
					}
					else if(max==fr2){
						french_best = "�Ұ�ⵤ��";
					}
					else if(max==fr3){
						french_best = "��õ�߰���";
					}
					else if(max==fr4){
						french_best = "�δ��";
					}
					else if(max==fr5){
						french_best = "�����";
					}
					else if(max==fr6){
						french_best = "�ع�����";
					}
					else if(max==fr7){
						french_best = "�ع���";
					}
					else if(max==fr8){
						french_best = "�ż���";
					}
					else if(max==fr9){
						french_best = "ȭ����";
					}
					else if(max==fr10){
						french_best = "���߶�����";
					}
				}
				//System.out.println(user_nation+" "+food_id);
        	}//while

			System.out.println("\n����>");
			System.out.println("�ѱ�-"+korean_best);
			System.out.println("�Ϻ�-"+japanese_best);
			System.out.println("�߱�-"+chinese_best);
			System.out.println("�̱�-"+american_best);
			System.out.println("������-"+french_best);

		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void Sex(){
		
		try{ //DB�� ���� ����� �������� ����
			
			Statement stmt = conn.createStatement(); // DB�� SQL�� ������ �� �մ� Statement��ü ����
		
			String query = "select user_sex, food_id "
					+ "from user,ordered "
					+ "where user.user_id=ordered.user_id ";
			
           	ResultSet rs = stmt.executeQuery(query); // ���̺��� ��� ������ �˻�
	
           	int w1=0;
           	int w2=0;
           	int w3=0;
           	int w4=0;
           	int w5=0;
           	int w6=0;
           	int w7=0;
           	int w8=0;
           	int w9=0;
           	int w10=0;
           	
           	int m1=0;
           	int m2=0;
           	int m3=0;
           	int m4=0;
           	int m5=0;
           	int m6=0;
           	int m7=0;
           	int m8=0;
           	int m9=0;
           	int m10=0;
           	
        	
			while(rs.next()){
				user_sex = rs.getString("user_sex");
				if(user_sex.equals("f")){ //����
					if(rs.getInt("food_id")==1){ 
						w1++;
					}
					else if(rs.getInt("food_id")==2){ 
						w2++;
					}
					else if(rs.getInt("food_id")==3){
						w3++;
					}
					else if(rs.getInt("food_id")==4){
						w4++;
					}
					else if(rs.getInt("food_id")==5){
						w5++;
					}
					else if(rs.getInt("food_id")==6){ 
						w6++;
					}
					else if(rs.getInt("food_id")==7){ 
						w7++;
					}
					else if(rs.getInt("food_id")==8){
						w8++;
					}
					else if(rs.getInt("food_id")==9){
						w9++;
					}
					else if(rs.getInt("food_id")==10){
						w10++;
					}
					int w[]={w1, w2, w3, w4, w5, w6, w7, w8, w9, w10};
		            
		            max=w[0];
		            for(int i=0; i<w.length; i++){
		            	if(w[i]> max)
		            		max = w[i];
		            }
		            if(max==w1){
		            	woman_best = "�����";
					}
					else if(max==w2){
						woman_best = "�Ұ�ⵤ��";
					}
					else if(max==w3){
						woman_best = "��õ�߰���";
					}
					else if(max==w4){
						woman_best = "�δ��";
					}
					else if(max==w5){
						woman_best = "�����";
					}
					else if(max==w6){
						woman_best = "�ع�����";
					}
					else if(max==w7){
						woman_best = "�ع���";
					}
					else if(max==w8){
						woman_best = "�ż���";
					}
					else if(max==w9){
						woman_best = "ȭ����";
					}
					else if(max==w10){
						woman_best = "���߶�����";
					}	
				}
				else if(user_sex.equals("m")){ //����
					if(rs.getInt("food_id")==1){ 
						m1++;
					}
					else if(rs.getInt("food_id")==2){ 
						m2++;
					}
					else if(rs.getInt("food_id")==3){
						m3++;
					}
					else if(rs.getInt("food_id")==4){
						m4++;
					}
					else if(rs.getInt("food_id")==5){
						m5++;
					}
					else if(rs.getInt("food_id")==6){ 
						m6++;
					}
					else if(rs.getInt("food_id")==7){ 
						m7++;
					}
					else if(rs.getInt("food_id")==8){
						m8++;
					}
					else if(rs.getInt("food_id")==9){
						m9++;
					}
					else if(rs.getInt("food_id")==10){
						m10++;
					}
					int m[]={m1, m2, m3, m4, m5, m6, m7, m8, m9, m10};
		            
		            max=m[0];
		            for(int i=0; i<m.length; i++){
		            	if(m[i]> max)
		            		max = m[i];
		            }
		            if(max==m1){
		            	man_best = "�����";
					}
					else if(max==m2){
						man_best = "�Ұ�ⵤ��";
					}
					else if(max==m3){
						man_best = "��õ�߰���";
					}
					else if(max==m4){
						man_best = "�δ��";
					}
					else if(max==m5){
						man_best = "�����";
					}
					else if(max==m6){
						man_best = "�ع�����";
					}
					else if(max==m7){
						man_best = "�ع���";
					}
					else if(max==m8){
						man_best = "�ż���";
					}
					else if(max==m9){
						man_best = "ȭ����";
					}
					else if(max==m10){
						man_best = "���߶�����";
					}	
				}
			}
			System.out.println("\n����>");
			System.out.println("����-"+woman_best);
			System.out.println("����-"+man_best);

		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void Age(){
	
		try{ //DB�� ���� ����� �������� ����
		
			Statement stmt = conn.createStatement(); // DB�� SQL�� ������ �� �մ� Statement��ü ����
		
			String query = "select user_age, food_id "
					+ "from user,ordered "
					+ "where user.user_id=ordered.user_id ";
			
	       	ResultSet rs = stmt.executeQuery(query); // ���̺��� ��� ������ �˻�
	
	       	//int age[] = {0,0,0,0,0,0,0,0,0,0};
	       	/*
	       	int age1;
	       	int age2;
	       	int age3;
	       	int age4;
	       	int age5;
	       	int age6;
	       	int age7;
	       	int age8;
	       	int age9;
	       	int age10;
	       	*/
	       	
	       	int age1=0;
	       	int age2=0;
	       	int age3=0;
	       	int age4=0;
	       	int age5=0;
	       	int age6=0;
	       	int age7=0;
	       	int age8=0;
	       	int age9=0; 
	       	int age10=0;
	       	/*
	       	int age61=0;
	       	int age62=0;
	       	int age63=0;
	       	int age64=0;
	       	int age65=0;
	       	int age66=0;
	       	int age67=0;
	       	int age68=0;
	       	int age69=0; 
	       	int age610=0;
	       	*/
	       	int test=0;
	    	
			while(rs.next()){
				user_age = rs.getString("user_age");
				user_age2 = Integer.parseInt(user_age);
				
				if(1 <= user_age2 && user_age2 < 20){ //10��
					
					age1=age2=age3=age4=age5=age6=age7=age8=age9=age10=0;
					if(rs.getInt("food_id")==1){ 
						age1++;
					}
					else if(rs.getInt("food_id")==2){ 
						age2++;
					}
					else if(rs.getInt("food_id")==3){
						age3++;
					}
					else if(rs.getInt("food_id")==4){
						age4++;
					}
					else if(rs.getInt("food_id")==5){
						age5++;
					}
					else if(rs.getInt("food_id")==6){ 
						age6++;
					}
					else if(rs.getInt("food_id")==7){ 
						age7++;
					}
					else if(rs.getInt("food_id")==8){
						age8++;
					}
					else if(rs.getInt("food_id")==9){
						age9++;
					}
					else if(rs.getInt("food_id")==10){
						age10++;
					}
					int age[]={age1, age2, age3, age4, age5, age6, age7, age8, age9, age10};
		            
		            max=age[0];
		            for(int i=0; i<age.length; i++){
		            	if(age[i]> max)
		            		max = age[i];
		            }
		            if(max==age1){
		            	age10_best = "�����";
					}
					else if(max==age2){
						age10_best = "�Ұ�ⵤ��";
					}
					else if(max==age3){
						age10_best = "��õ�߰���";
					}
					else if(max==age4){
						age10_best = "�δ��";
					}
					else if(max==age5){
						age10_best = "�����";
					}
					else if(max==age6){
						age10_best = "�ع�����";
					}
					else if(max==age7){
						age10_best = "�ع���";
					}
					else if(max==age8){
						age10_best = "�ż���";
					}
					else if(max==age9){
						age10_best = "ȭ����";
					}
					else if(max==age10){
						age10_best = "���߶�����";
					}	
				}
				
				else if(20 <= user_age2 && user_age2 < 30){ //20
					
					age1=age2=age3=age4=age5=age6=age7=age8=age9=age10=0;
					
					if(rs.getInt("food_id")==1){ 
						age1++;
					}
					else if(rs.getInt("food_id")==2){ 
						age2++;
					}
					else if(rs.getInt("food_id")==3){
						age3++;
					}
					else if(rs.getInt("food_id")==4){
						age4++;
					}
					else if(rs.getInt("food_id")==5){
						age5++;
					}
					else if(rs.getInt("food_id")==6){ 
						age6++;
					}
					else if(rs.getInt("food_id")==7){ 
						age7++;
					}
					else if(rs.getInt("food_id")==8){
						age8++;
					}
					else if(rs.getInt("food_id")==9){
						age9++;
					}
					else if(rs.getInt("food_id")==10){
						age10++;
					}
					int age[]={age1, age2, age3, age4, age5, age6, age7, age8, age9, age10};
		            
		            max=age[0];
		            for(int i=0; i<age.length; i++){
		            	if(age[i]> max)
		            		max = age[i];
		            }
		            if(max==age1){
		            	age20_best = "�����";
					}
					else if(max==age2){
						age20_best = "�Ұ�ⵤ��";
					}
					else if(max==age3){
						age20_best = "��õ�߰���";
					}
					else if(max==age4){
						age20_best = "�δ��";
					}
					else if(max==age5){
						age20_best = "�����";
					}
					else if(max==age6){
						age20_best = "�ع�����";
					}
					else if(max==age7){
						age20_best = "�ع���";
					}
					else if(max==age8){
						age20_best = "�ż���";
					}
					else if(max==age9){
						age20_best = "ȭ����";
					}
					else if(max==age10){
						age20_best = "���߶�����";
					}	
				}
				
				else if(30 <= user_age2 && user_age2 < 40){ //30��
					
					//age1=age2=age3=age4=age5=age6=age7=age8=age9=age10=0;
    	
					if(rs.getInt("food_id")==1){ 
						age1++;
					}
					else if(rs.getInt("food_id")==2){ 
						age2++;
					}
					else if(rs.getInt("food_id")==3){
						age3++;
					}
					else if(rs.getInt("food_id")==4){
						age4++;
					}
					else if(rs.getInt("food_id")==5){
						age5++;
					}
					else if(rs.getInt("food_id")==6){ 
						age6++;
					}
					else if(rs.getInt("food_id")==7){ 
						age7++;
					}
					else if(rs.getInt("food_id")==8){
						age8++;
					}
					else if(rs.getInt("food_id")==9){
						age9++;
					}
					else if(rs.getInt("food_id")==10){
						age10++;
					}
					int age[]={age1, age2, age3, age4, age5, age6, age7, age8, age9, age10};
		            
		            max=age[0];
		            for(int i=0; i<age.length; i++){
		            	if(age[i]> max)
		            		max = age[i];
		            }
		            if(max==age1){
		            	age30_best = "�����";
					}
					else if(max==age2){
						age30_best = "�Ұ�ⵤ��";
					}
					else if(max==age3){
						age30_best = "��õ�߰���";
					}
					else if(max==age4){
						age30_best = "�δ��";
					}
					else if(max==age5){
						age30_best = "�����";
					}
					else if(max==age6){
						age30_best = "�ع�����";
					}
					else if(max==age7){
						age30_best = "�ع���";
					}
					else if(max==age8){
						age30_best = "�ż���";
					}
					else if(max==age9){
						age30_best = "ȭ����";
					}
					else if(max==age10){
						age30_best = "���߶�����";
					}
		           
				}
				else if(user_age2 >=40 && user_age2 < 50){ //40��
					
					age1=age2=age3=age4=age5=age6=age7=age8=age9=age10=0;
					
					if(rs.getInt("food_id")==1){ 
						age1++;
					}
					else if(rs.getInt("food_id")==2){ 
						age2++;
						
					}
					else if(rs.getInt("food_id")==3){
						
						age3++;
					}
					else if(rs.getInt("food_id")==4){
						age4++;
					}
					else if(rs.getInt("food_id")==5){
						age5++;
						
					}
					else if(rs.getInt("food_id")==6){ 
						age6++;
					}
					else if(rs.getInt("food_id")==7){ 
						age7++;
					}
					else if(rs.getInt("food_id")==8){
						age8++;
					}
					else if(rs.getInt("food_id")==9){
						age9++;
					}
					else if(rs.getInt("food_id")==10){
						age10++;
					}
					int age[]={age1, age2, age3, age4, age5, age6, age7, age8, age9, age10};
		            
					
		            max=age[0];
		            for(int i=0; i<age.length; i++){
		            	if(age[i]> max)
		            		max = age[i];
		            }
		            if(max==age1){
		            	age40_best = "�����";
					}
					else if(max==age2){
						age40_best = "�Ұ�ⵤ��";
					}
					else if(max==age3){
						age40_best = "��õ�߰���";
					}
					else if(max==age4){
						age40_best = "�δ��";
					}
					else if(max==age5){
						age40_best = "�����";
					}
					else if(max==age6){
						age40_best = "�ع�����";
					}
					else if(max==age7){
						age40_best = "�ع���";
					}
					else if(max==age8){
						age40_best = "�ż���";
					}
					else if(max==age9){
						age40_best = "ȭ����";
					}
					else if(max==age10){
						age40_best = "���߶�����";
					}
				}		
				else if(user_age2 >=50 && user_age2 < 60){ //50��
					
					age1=age2=age3=age4=age5=age6=age7=age8=age9=age10=0;
					//age61=age62=age63=age64=age65=age66=age67=age68=age69=age610=0;
					if(rs.getInt("food_id")==1){ 
						age1++;
					}
					else if(rs.getInt("food_id")==2){ 
						age2++;
					}
					else if(rs.getInt("food_id")==3){
						
						age3++;
					}
					else if(rs.getInt("food_id")==4){
						age4++;
					}
					else if(rs.getInt("food_id")==5){
						age5++;
						
					}
					else if(rs.getInt("food_id")==6){ 
						age6++;
					}
					else if(rs.getInt("food_id")==7){ 
						age7++;
					}
					else if(rs.getInt("food_id")==8){
						age8++;
					}
					else if(rs.getInt("food_id")==9){
						age9++;
					}
					else if(rs.getInt("food_id")==10){
						age10++;
					}
					int age[]={age1, age2, age3, age4, age5, age6, age7, age8, age9, age10};

					
		            max=age[0];
		            for(int i=0; i<age.length; i++){
		            	if(age[i]> max)
		            		max = age[i];
		            }
		            if(max==age1){
		            	age50_best = "�����";
					}
					else if(max==age2){
						age50_best = "�Ұ�ⵤ��";
					}
					else if(max==age3){
						age50_best = "��õ�߰���";
					}
					else if(max==age4){
						age50_best = "�δ��";
					}
					else if(max==age5){
						age50_best = "�����";
					}
					else if(max==age6){
						age50_best = "�ع�����";
					}
					else if(max==age7){
						age50_best = "�ع���";
					}
					else if(max==age8){
						age50_best = "�ż���";
					}
					else if(max==age9){
						age50_best = "ȭ����";
					}
					else if(max==age10){
						age50_best = "���߶�����";
					}
		           
				}
				else if(user_age2 >=60 && user_age2 < 70){ //60��

					//age1=age2=age3=age4=age5=age6=age7=age8=age9=age10=0;
					
					if(rs.getInt("food_id")==1){ 
						age1++;
					}
					else if(rs.getInt("food_id")==2){ 
						age2++;
						
					}
					else if(rs.getInt("food_id")==3){
						
						age3++;
					}
					else if(rs.getInt("food_id")==4){
						age4++;
					}
					else if(rs.getInt("food_id")==5){
						age5++;
						
					}
					else if(rs.getInt("food_id")==6){ 
						age6++;
					}
					else if(rs.getInt("food_id")==7){ 
						age7++;
					}
					else if(rs.getInt("food_id")==8){
						age8++;
					}
					else if(rs.getInt("food_id")==9){
						age9++;
					}
					else if(rs.getInt("food_id")==10){
						age10++;
					}
					int age[]={age1, age2, age3, age4, age5, age6, age7, age8, age9, age10};

					
		            max=age[0];
		            for(int i=0; i<age.length; i++){
		            	if(age[i]> max)
		            		max = age[i];
		            }
		            if(max==age1){
		            	age60_best = "�����";
					}
					else if(max==age2){
						age60_best = "�Ұ�ⵤ��";
					}
					else if(max==age3){
						age60_best = "��õ�߰���";
					}
					else if(max==age4){
						age60_best = "�δ��";
					}
					else if(max==age5){
						age60_best = "�����";
					}
					else if(max==age6){
						age60_best = "�ع�����";
					}
					else if(max==age7){
						age60_best = "�ع���";
					}
					else if(max==age8){
						age60_best = "�ż���";
					}
					else if(max==age9){
						age60_best = "ȭ����";
					}
					else if(max==age10){
						age60_best = "���߶�����";
					}
				}
				else { //70��

					age1=age2=age3=age4=age5=age6=age7=age8=age9=age10=0;
					
					if(rs.getInt("food_id")==1){ 
						age1++;
					}
					else if(rs.getInt("food_id")==2){ 
						age2++;
					}
					else if(rs.getInt("food_id")==3){	
						age3++;
					}
					else if(rs.getInt("food_id")==4){
						age4++;
					}
					else if(rs.getInt("food_id")==5){
						age5++;	
					}
					else if(rs.getInt("food_id")==6){ 
						age6++;
					}
					else if(rs.getInt("food_id")==7){ 
						age7++;
					}
					else if(rs.getInt("food_id")==8){
						age8++;
					}
					else if(rs.getInt("food_id")==9){
						age9++;
					}
					else if(rs.getInt("food_id")==10){
						age10++;
					}
					int age[]={age1, age2, age3, age4, age5, age6, age7, age8, age9, age10};

					
		            max=age[0];
		            for(int i=0; i<age.length; i++){
		            	if(age[i]> max)
		            		max = age[i];
		            }
		            if(max==age1){
		            	age70_best = "�����";
					}
					else if(max==age2){
						age70_best = "�Ұ�ⵤ��";
					}
					else if(max==age3){
						age70_best = "��õ�߰���";
					}
					else if(max==age4){
						age70_best = "�δ��";
					}
					else if(max==age5){
						age70_best = "�����";
					}
					else if(max==age6){
						age70_best = "�ع�����";
					}
					else if(max==age7){
						age70_best = "�ع���";
					}
					else if(max==age8){
						age70_best = "�ż���";
					}
					else if(max==age9){
						age70_best = "ȭ����";
					}
					else if(max==age10){
						age70_best = "���߶�����";
					}
				}
			}//
			
			System.out.println("\n���̺�>");
			System.out.println("10��-"+age10_best);
			System.out.println("20��-"+age20_best);
			System.out.println("30��-"+age30_best);
			System.out.println("40��-"+age40_best);
			System.out.println("50��-"+age50_best);
			System.out.println("60��-"+age60_best);
			System.out.println("70��-"+age70_best);
			
			conn.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
