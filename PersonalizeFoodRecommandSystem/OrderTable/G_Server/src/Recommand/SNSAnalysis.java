//package Recommand;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.regex.PatternSyntaxException;
//
//import kr.ac.kaist.swrc.jhannanum.comm.Eojeol;
//import kr.ac.kaist.swrc.jhannanum.comm.Sentence;
//import kr.ac.kaist.swrc.jhannanum.hannanum.Workflow;
//import kr.ac.kaist.swrc.jhannanum.hannanum.WorkflowFactory;
//
//public class SNSAnalysis {
//	//�ܾ�����, �󵵼���������
//	static ArrayList<String> foodName;
//	static ArrayList<String> likeExpression;
//	HashMap<String, String> food_opinion;
//	List hash_list;
//	boolean nag_expression = false;
//	
//	//������ (Ű���� ������� �Ϸ�)
//	public SNSAnalysis(String document)
//	{
//		//Workflow workflow = WorkflowFactory.getPredefinedWorkflow(WorkflowFactory.WORKFLOW_NOUN_EXTRACTOR);
//		Workflow workflow = WorkflowFactory.getPredefinedWorkflow(WorkflowFactory.WORKFLOW_POS_SIMPLE_09);
//		
//		//DB���� ���� �̸� �޾ƿ� (matching check�� ���� �ʿ�)
//		getFoodNameFromDB();
//		food_opinion = new HashMap<>();
//		hash_list = new ArrayList();
//		
//		try 
//		{
//			/* Activate the work flow in the thread mode */
//			workflow.activateWorkflow(true);
//			
//			/* Analysis using the work flow */
//			workflow.analyze(document);
//			
//			LinkedList<Sentence> resultList = workflow.getResultOfDocument(new Sentence(0, 0, false));
//			for (Sentence s : resultList) 
//			{
//				Eojeol[] eojeolArray = s.getEojeols();
//				for (int i = 0; i < eojeolArray.length; i++) 
//				{
//					if (eojeolArray[i].length > 0)
//					{
//						String[] morphemes = eojeolArray[i].getMorphemes();
//						for (int j = 0; j < morphemes.length; j++)
//						{
//							//����Ű���尡 �����̸��� ��ġ�� ��� ����
//							if(matchCheck(morphemes[j])){
//									food_opinion.put("food", morphemes[j]);
//							}
//							
//							//����Ű���尡 ��ȣǥ���� ��ġ�� ��� ����
//							if(likeCheck(morphemes[j])){
//								//'��~' Ȯ��
//								System.out.println("mor : " + morphemes[j]);
//								char[] c = morphemes[j].toCharArray();
//								if(c[0]=='��'){
//									nag_expression = true;
//									System.out.println("1���� true : " + nag_expression);
//								}
//								
//								//'~�ʴ�' Ȯ��
//								if(j+2<morphemes.length && morphemes[j+2].matches("��")){
//									nag_expression = true;
//									System.out.println("2���� true : " + nag_expression);
//									
//								}
//								
//								
//								if(nag_expression!=true && (String)food_opinion.get("food")!=null){
//									food_opinion.put("opinion", morphemes[j]);
//									hash_list.add(food_opinion);
//									food_opinion = new HashMap<>();
//								}
//
//								nag_expression = false;
//								System.out.println("�ٽ� false : " + nag_expression);
//							}
//						}
//					}
//				}//end of eojeolArray
//			}//end of resultList
//			
//			workflow.close();
//			
//		}
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//			System.exit(0);
//		}
//		
//		/* Shutdown the work flow */
//		workflow.close();
//		
//	}
//	
//	public void getFoodNameFromDB(){
//		FoodSearchMgr foodsearchMgr = new FoodSearchMgr();
//		
//		foodName = new ArrayList<String>();
//		foodName = foodsearchMgr.getFoodName();
//		
//		likeExpression = new ArrayList<String>();
//		likeExpression = foodsearchMgr.getDic_Opinion();
//	}
//
//
//
//	//�����̸� üũ
//	public static boolean matchCheck(String readKeyword){
//		
//		String matchKeyword = "";
//		boolean matching = false;
//		
//		try{
//			for(int i=0 ; i<foodName.size() ; i++){
//				if(readKeyword.matches(".*"+foodName.get(i)+".*")){
//					matchKeyword = foodName.get(i);				
//					System.out.println(matchKeyword);
//					matching = true;
//				}
//			}
//		}catch(PatternSyntaxException e){
//			System.out.println(e);
//		}
//		return matching;
//	}
//	
//	
//	//��ȣ�ǰ� üũ
//	public static boolean likeCheck(String readKeyword){
//		
//		String matchKeyword = "";
//		boolean matching = false;
//		boolean nagative = false;
//		String nagative1 = "��";
//		String nagative2 = "��";
//		
//		
//		try{
//			for(int i=0 ; i<likeExpression.size() ; i++){
//				if(readKeyword.matches(".*"+likeExpression.get(i)+".*")){
//					matchKeyword = likeExpression.get(i);				
//					System.out.println(matchKeyword);
//					matching = true;
//				}
//			}
//		}catch(PatternSyntaxException e){
//			System.out.println(e);
//		}
//		return matching;
//	}
//
//	
//	
//	//HashMap ���
//	public String printHashMap(){
//		HashMap getHash = new HashMap();
//		String hFood = null;
//		String hOpinion = null;
//		String resultFood = null;
//		
//		for(int i=0; i<hash_list.size(); i++){
//			getHash = (HashMap)hash_list.get(i);
//			hFood = (String)getHash.get("food");
//			hOpinion = (String)getHash.get("opinion");
//			System.out.println("food : " + hFood + " opinion : " + hOpinion);
//			resultFood = hFood;
//		}
//		if(resultFood==null)
//			resultFood="0";
//			
//		System.out.println(hFood);
//		return resultFood;
//	}
//	
//
//	
//}
