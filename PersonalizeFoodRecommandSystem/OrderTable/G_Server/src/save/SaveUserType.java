package save;

import java.util.ArrayList;
import java.util.Vector;


public class SaveUserType {
	
	
	TypeSetSearchMgr typeMgr = new TypeSetSearchMgr();
	
	float point_challenge =0;
	float point_stable = 0;
	float point_price = 0;
	float point_healthy = 0;
	
	String user_id = "";
	float kind_restMenu = 0;
	float num_restHealtyMenu = 0;
	float kind_userOrderMenu = 0;
	float num_userOrderMenu = 0;
	float num_userOrderHealthyMenu = 0;
	float avg_rest = 0;
	float avg_user = 0;
	
	float temp = 0;
	float standard = 0;
	
	String type_challenge_vs_stable = "";
	boolean type_isPrice = false;
	boolean type_isHealthy = false;
	
	
	public SaveUserType(String c_user_id){
		user_id = c_user_id;
		
		getVariableValues();
		
		checkTypePoint();
		
		savePoint_and_Type();
		
	}
	
	
	public void getVariableValues(){
		kind_restMenu = typeMgr.kind_RestuarantMenu();
		num_userOrderMenu = typeMgr.num_userOrderMenu(user_id);
		kind_userOrderMenu = typeMgr.kind_userOrderMenu(user_id);
		
		avg_rest = typeMgr.avg_RestuarantMenu();
		avg_user = typeMgr.avg_userOrderMenu(user_id);
		
		num_restHealtyMenu = typeMgr.num_RestuarantHealthyMenu();
		num_userOrderHealthyMenu = typeMgr.num_userOrderHealthyMenu(user_id);		
	}
	
	
	
	public void checkTypePoint(){
		
		/* 도전형 안전형 체크 */
		temp = (float) (((kind_userOrderMenu/kind_restMenu)*0.5) + ((kind_userOrderMenu/num_userOrderMenu*0.5))) * 100;
		standard = (float) ((((1/kind_restMenu)*0.5) + ((1/1*0.5))) * 100)/50;
		point_challenge = temp/standard;
		point_stable = 100 - point_challenge;
		
		
		/* 가격형 체크 */
		//처음온사람 = 0
		System.out.println("avg_user : " + avg_user);
		System.out.println("avg_rest : " + avg_rest);
		point_price = avg_rest - avg_user;
		
		/* 건강형 체크 */
		System.out.println("num_userOrderHealthyMenu : " + num_userOrderHealthyMenu);
		System.out.println("num_userOrderMenu : " + num_userOrderMenu);
		point_healthy = (float)(num_userOrderHealthyMenu / num_userOrderMenu)*100;
	
	}
	
	public void savePoint_and_Type(){
		
		float[] point = {point_challenge, point_stable, point_price, point_healthy};
		
		
		if(point_challenge >= point_stable)
			type_challenge_vs_stable = "도전형";
		else
			type_challenge_vs_stable = "안전형";
		
		if(point_price<=avg_rest)
			type_isPrice = true;
		
		if(point_healthy>=80)
			type_isHealthy = true;
		
		typeMgr.save_userTypePoint(user_id, point, type_challenge_vs_stable, type_isPrice, type_isHealthy);
		
			
	}
	
}
