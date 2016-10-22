//package Recommand;
//
//import java.util.ArrayList;
//import java.util.Random;
//import java.util.Vector;
//
//import bean.OtherUser;
//import bean.User;
//
//public class RecommandAlgorithm {
//	
//	//user ����
//	String c_user_id;
//	String c_user_type;
//	int c_user_age;
//	String c_user_sex;
//	String c_user_nation;
//	
//	//���׷��� ����ġ
//	int rate_nation;
//	int rate_sex;
//	int rate_age;
//	
//	SimilarSearchMgr similarsearchMgr = new SimilarSearchMgr();
//	Vector<OtherUser> similar_user_list = new Vector<OtherUser>();
//	
//	FoodSearchMgr foodsearchMgr = new FoodSearchMgr();
//	ArrayList<String> food_list = new ArrayList<String>();
//	
//	public RecommandAlgorithm(User user){
//		
//		// 1.����� id���� ������ �о�´�.
//		c_user_id = user.getId();
//		c_user_age = user.getAge();
//		c_user_sex = user.getSex();
//		
//		
//		// 2. ������� �ൿ������ Ȯ���Ѵ�.
//		//c_user_type = user.getType();
//		switch(c_user_type){
//		case "������":
//			Rec_Similar(); //2������
//			Rec_Bestseller();
//			break;
//		case "����������":
//			Rec_Similar(); //2��
//			Rec_Price();
//			break;
//		case "�����ǰ���":
//			Rec_Similar(); //2��
//			Rec_Healthy();
//			break;
//		case "�������ݰǰ���":
//			Rec_Similar(); //1��
//			Rec_Price();
//			Rec_Healthy();
//			break;
//		case "������":
//			Rec_SNS();
//			Rec_Bestseller(); //2��
//			break;
//		case "����������":
//			Rec_Price();
//			Rec_SNS();
//			Rec_Bestseller();
//			break;
//		case "�����ǰ���":
//			Rec_Healthy();
//			Rec_SNS();
//			Rec_Bestseller();
//			break;
//		case "�������ݰǰ���":
//			Rec_Price();
//			Rec_Healthy();
//			Rec_Bestseller();
//			break;
//		}	
//	}
//	
//	public void Rec_Similar(){
//		
//		//����ڿ� ���� �ൿ������ �ٸ� ����� ������ �����´�.
//		similar_user_list = similarsearchMgr.getSimilarTypePeople(c_user_type);
//		
//		//�ش� ������ �ӽ����̺� �����Ѵ�.
//		similarsearchMgr.saveSimilarTypePeople(similar_user_list);
//
//		//�ൿ������ ���� ���׷���Ÿ�� ����ġ ���� �����Ѵ�.
//		saveStreoWeight();
//		
//		//����ڿ� ������ ���׷��� ������ ���� ����ڸ� ���͸��Ѵ�.
//		similar_user_list = similarsearchMgr.getSimilarStreoPeople(c_user_age, c_user_sex, c_user_nation, rate_age, rate_sex, rate_nation);
//		
//		//�ش� ������ �ӽ����̺� �����Ѵ�.
//		similarsearchMgr.saveSimilarStreoPeople(similar_user_list);
//		
//		//���Ͽ�����κ�
//		
//		//��� �޴� ����
//		
//	}
//	
//	public void saveStreoWeight(){
//		switch(c_user_type){
//		case "������":
//			rate_nation = 10;
//			rate_sex = 60;
//			rate_age = 30;
//			break;
//		case "����������":
//		case "�����ǰ���":
//		case "�������ݰǰ���":
//			rate_nation = 10;
//			rate_sex = 30;
//			rate_age = 60;
//			break;
//		case "������":
//			rate_nation = 70;
//			rate_sex = 10;
//			rate_age = 20;
//			break;
//		case "����������":
//		case "�����ǰ���":
//		case "�������ݰǰ���":
//			rate_nation = 40;
//			rate_sex = 20;
//			rate_age = 40;
//			break;
//		}
//	}
//	
//	public void Rec_Price(){
//		//������������ ���� ���� �� �� ���� �޴��� �����´�.
//		food_list = foodsearchMgr.getLowPrice();
//		
//		//�� �� �������� �ϳ��� ����
//		Random random = new Random();
//		int randomNum = random.nextInt(3);
//		//������ = food_list.get(randomNum);
//		
//		//��� �޴� ����
//		
//	}
//	
//	public void Rec_Healthy(){
//		//������������ �ǰ��޴��� �ش��ϴ� ����Ʈ�� �����´�.
//		food_list = foodsearchMgr.getHealthy();
//		
//		//�� �� �������� �ϳ��� ����
//		Random random = new Random();
//		int randomNum = random.nextInt(food_list.size());
//		//������ = food_list.get(randomNum);
//		
//		//��� �޴� ����
//	}
//	
//	public void Rec_SNS(){
//		//������� facebook���� �㺭�� ������ �����´�.
//		
//		//�м��� ���� ��ȣ�ϴ� ������ �ľ��Ѵ�. 
//		SNSAnalysis analysis = new SNSAnalysis("����� �����ʾ�, ����䵵 ���־�  �ع����� ������, ");
//		//String ������ = analysis.printHashMap();
//		
//		//��� �޴� ����
//		
//		//if(������=="0")
//			Rec_Bestseller();
//			
//		//��� �޴� ����
//
//	}
//	
//	public void Rec_Bestseller(){
//		CheckBestseller bestSeller = new CheckBestseller();
//		
//		
//		bestSeller.Nation();
//		bestSeller.Sex();
//		bestSeller.Age();
//	}
//	
//	
//	//���� ����� 3�� return�ϱ�!
//}
