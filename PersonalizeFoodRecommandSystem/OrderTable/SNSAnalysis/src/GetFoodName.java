
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import kr.ac.kaist.swrc.jhannanum.comm.Eojeol;
import kr.ac.kaist.swrc.jhannanum.comm.Sentence;
import kr.ac.kaist.swrc.jhannanum.hannanum.Workflow;
import kr.ac.kaist.swrc.jhannanum.hannanum.WorkflowFactory;

public class GetFoodName 
{
	//단어저장, 빈도수순위저장
	static ArrayList<String> foodName;
	static ArrayList<String> likeExpression;
	HashMap<String, String> food_opinion;
	List hash_list;
	boolean nag_expression = false;
	
	//생성자 (키워드 추출까지 완료)
	public GetFoodName(String document)
	{
		//Workflow workflow = WorkflowFactory.getPredefinedWorkflow(WorkflowFactory.WORKFLOW_NOUN_EXTRACTOR);
		Workflow workflow = WorkflowFactory.getPredefinedWorkflow(WorkflowFactory.WORKFLOW_POS_SIMPLE_09);
		
		//DB에서 음식 이름 받아옴 (matching check를 위해 필요)
		getFoodNameFromDB();
		food_opinion = new HashMap<>();
		hash_list = new ArrayList();
		
		try 
		{
			/* Activate the work flow in the thread mode */
			workflow.activateWorkflow(true);
			
			/* Analysis using the work flow */
			workflow.analyze(document);
			
			LinkedList<Sentence> resultList = workflow.getResultOfDocument(new Sentence(0, 0, false));
			for (Sentence s : resultList) 
			{
				Eojeol[] eojeolArray = s.getEojeols();
				for (int i = 0; i < eojeolArray.length; i++) 
				{
					if (eojeolArray[i].length > 0)
					{
						String[] morphemes = eojeolArray[i].getMorphemes();
						for (int j = 0; j < morphemes.length; j++)
						{
							//추출키워드가 음식이름과 일치할 경우 수행
							if(matchCheck(morphemes[j])){
									food_opinion.put("food", morphemes[j]);
							}
							
							//추출키워드가 선호표현과 일치할 경우 수행
							if(likeCheck(morphemes[j])){
								//'안~' 확인
								char[] c = morphemes[j].toCharArray();
								if(c[0]=='안'){
									nag_expression = true;
								}
								
								//'~않다' 확인
								if(j+2<morphemes.length && morphemes[j+2].matches("않")){
									nag_expression = true;
								}
								
								
								if(nag_expression!=true && (String)food_opinion.get("food")!=null){
									food_opinion.put("opinion", morphemes[j]);
									hash_list.add(food_opinion);
									food_opinion = new HashMap<>();
								}

								nag_expression = false;
							}
						}
					}
				}//end of eojeolArray
			}//end of resultList
			
			workflow.close();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
		
		/* Shutdown the work flow */
		workflow.close();
		
	}
	
	public void getFoodNameFromDB(){
		FoodMgr foodMgr = new FoodMgr();
		
		foodName = new ArrayList<String>();
		foodName = foodMgr.getFoodName();
		
		likeExpression = new ArrayList<String>();
		likeExpression = foodMgr.getDic_Opinion();
	}



	//음식이름 체크
	public static boolean matchCheck(String readKeyword){
		
		String matchKeyword = "";
		boolean matching = false;
		
		try{
			for(int i=0 ; i<foodName.size() ; i++){
				if(readKeyword.matches(".*"+foodName.get(i)+".*")){
					matchKeyword = foodName.get(i);				
					System.out.println(matchKeyword);
					matching = true;
				}
			}
		}catch(PatternSyntaxException e){
			System.out.println(e);
		}
		return matching;
	}
	
	
	//선호의견 체크
	public static boolean likeCheck(String readKeyword){
		
		String matchKeyword = "";
		boolean matching = false;
		boolean nagative = false;
		String nagative1 = "않";
		String nagative2 = "안";
		
		
		try{
			for(int i=0 ; i<likeExpression.size() ; i++){
				if(readKeyword.matches(".*"+likeExpression.get(i)+".*")){
					matchKeyword = likeExpression.get(i);				
					System.out.println(matchKeyword);
					matching = true;
				}
			}
		}catch(PatternSyntaxException e){
			System.out.println(e);
		}
		return matching;
	}

	
	
	//HashMap 출력
	public void printHashMap(){
		HashMap getHash = new HashMap();
		String hFood = null;
		String hOpinion = null;
		String resultFood = null;
		
		for(int i=0; i<hash_list.size(); i++){
			getHash = (HashMap)hash_list.get(i);
			hFood = (String)getHash.get("food");
			hOpinion = (String)getHash.get("opinion");
			System.out.println("food : " + hFood + " opinion : " + hOpinion);
			resultFood = hFood;
		}
		System.out.println("선호음식 : " + hFood);
	}
	

	
}
