import java.util.ArrayList;
import java.util.Vector;


public class SearchSimilarUser {
	
	//User 정보
	String c_user_id="";
	String c_user_nation;
	String c_user_sex;
	int c_user_age;
	String c_user_type; 
	
	float c_point_challenge;  //0~100
	float c_point_stable;  //0~100
	float c_point_healthy;  //0~100
	float c_point_price;  //0~100
	
	
	UserMgr userMgr = new UserMgr();
	UserBean bean = new UserBean();
	SimilarSearchMgr similarsearchMgr = new SimilarSearchMgr();
	Vector<UserBean> similar_user_list = new Vector<UserBean>();
	ArrayList<String> user_id_list = new ArrayList<String>();
	
	boolean isExist = false;
	
	//스테레오 가중치
	int rate_nation;
	int rate_sex;
	int rate_age;
	
	
	public SearchSimilarUser(){
		
		//사용자의 행동유형 정보를 받아온다.
		bean = userMgr.getUser("1005");
		/*
		System.out.println(bean.getUser_id());
		System.out.println(bean.getUser_age());
		System.out.println(bean.getUser_sex());
		System.out.println(bean.getUser_type());
		System.out.println(bean.getUser_nation());
		*/
		
		
		c_user_nation = bean.getUser_nation();
		c_user_sex = bean.getUser_sex();
		c_user_age = bean.getUser_age();
		c_user_type = bean.getUser_type();
		
		
		//사용자와 행동유형이 일치하는 타 고객정보 데이터를 가져온다. (Max 200개)
		//  & 행동유형에 따라 부여된 스테레오 가중치만큼의 타 고객정보 데이터를 필터링한다. (Max 100개)
		getSimilarPeople();
		
		
	}
	
	
	public void getSimilarPeople(){
		//similar_user_list = similarsearchMgr.getSimilarTypePeople(c_point_challenge*4, c_point_stable*4, c_point_healthy*4, c_point_price*4);
		
		//사용자와 같은 행동유형의 다른 사용자 정보를 가져온다.
		similar_user_list = similarsearchMgr.getSimilarTypePeople(c_user_type);
		//해당 내용을 임시테이블에 저장한다.
		similarsearchMgr.saveSimilarTypePeople(similar_user_list);
		
		for(int i=0; i<similar_user_list.size(); i++){
			UserBean id_bean = new UserBean();
			id_bean = similar_user_list.get(i);
			user_id_list.add(id_bean.getUser_id());
		}
		
		
		
		//행동유형에 따른 스테레오타입 가중치 값을 설정한다.
		saveStreoWeight();
		
		//사용자와 유사한 스테레오 정보를 가진 사용자를 필터링한다.
		similar_user_list = similarsearchMgr.getSimilarStreoPeople(c_user_age, c_user_sex, c_user_nation, rate_age, rate_sex, rate_nation);
		//해당 내용을 임시테이블에 저장한다.
		similarsearchMgr.saveSimilarStreoPeople(similar_user_list);
		
		
		
//		
//		getTypePoint();
//		
//		ArrayList<String> getUserId = new ArrayList<String>();
//		for(int i=0; i<similar_user_list.size(); i++){
//			UserBean getUser = similar_user_list.get(i);
//			getUserId.add(getUser.getUser_id());
//			System.out.println(getUserId);
//		}
//		return getUserId;
	}

	public void saveStreoWeight(){
		switch(bean.getUser_type()){
		case "도전형":
			rate_nation = 10;
			rate_sex = 60;
			rate_age = 30;
			break;
		case "도전가격형":
		case "도전건강형":
		case "도전가격건강형":
			rate_nation = 10;
			rate_sex = 30;
			rate_age = 60;
			break;
		case "안전형":
			rate_nation = 70;
			rate_sex = 10;
			rate_age = 20;
			break;
		case "안전가격형":
		case "안전건강형":
		case "안전가격건강형":
			rate_nation = 40;
			rate_sex = 20;
			rate_age = 40;
			break;
		}
	}
	
	public void getTypePoint(){
		float[] point = userMgr.getUserTypePoint(c_user_id);
		c_point_challenge = point[0];
		c_point_stable = point[1];
		c_point_price = point[2];
		c_point_healthy = point[3];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new SearchSimilarUser();

	}

}
