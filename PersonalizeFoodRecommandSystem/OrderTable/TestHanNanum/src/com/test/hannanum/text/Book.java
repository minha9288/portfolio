package com.test.hannanum.text;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import javax.naming.ldap.SortControl;

import kr.ac.kaist.swrc.jhannanum.comm.Eojeol;
import kr.ac.kaist.swrc.jhannanum.comm.Sentence;
import kr.ac.kaist.swrc.jhannanum.hannanum.Workflow;
import kr.ac.kaist.swrc.jhannanum.hannanum.WorkflowFactory;

public class Book 
{
	/*
	public Book(String path)
	{
		
	}
	*/
	//단어저장, 빈도수순위저장
	LinkedList<Word> words = new LinkedList<Word>();
	LinkedList<Integer> frequencyRank = new LinkedList<Integer>();
	
	//생성자 (키워드 추출까지 완료)
	public Book(String document)
	{
		//Workflow workflow = WorkflowFactory.getPredefinedWorkflow(WorkflowFactory.WORKFLOW_NOUN_EXTRACTOR);
		Workflow workflow = WorkflowFactory.getPredefinedWorkflow(WorkflowFactory.WORKFLOW_POS_SIMPLE_09);
		
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
							//중복검사 후 없으면 단어추가, 있으면 빈도 수 증가
							if ( redundancyCheck(morphemes[j]) )
								words.add(new Word(morphemes[j]));
							else
							{
								words.get( redundancyIndex(morphemes[j]) ).upFrequency();
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
		
		wordFrequencyWorkflow();
	}
	
	//단어 중복검사 메서드
	private boolean redundancyCheck(String string)
	{
		boolean result = true;
		
		for(int index=0; index<words.size(); index++)
		{
			if( string.equals( words.get(index).getWord() ) )
			{
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	//단어 중복 index반환 메서드
	private int redundancyIndex(String string)
	{
		int resultIndex = 0;
		
		for(int index=0; index<words.size(); index++)
		{
			if( string.equals( words.get(index).getWord() ) )
			{
				resultIndex = index;
				break;
			}
		}
		
		return resultIndex;
	}
	
	//빈도수 조사 메서드
	private void wordFrequencyWorkflow()
	{
		//flag 초기화
		boolean [] flags = new boolean[words.size()];
		int [] wordFrequencys = new int[words.size()];
		for(int i=0; i<flags.length; i++)
		{
			flags[i] = true;
			wordFrequencys[i] = words.get(i).getFrequency();
		}
		Arrays.sort(wordFrequencys);
		//frequencyRank에 빈도수 제일 적은 수부터 index저장
		for(int i=0; i<wordFrequencys.length; i++)
		{
			for(int j=0; j<words.size(); j++)
			{
				if(flags[j] && (wordFrequencys[i] == words.get(j).getFrequency()) )
				{
					frequencyRank.add(new Integer(j));
					flags[j] = false;
				}
			}
		}//end of frequencyRank
		
		Collections.reverse(frequencyRank);
	}
	
	//단어 출력
	public void wordsPrint()
	{
		for(int i=0; i<words.size(); i++)
		{
			System.out.println( i + ". 키워드 : " + (words.get(i)).getWord() + ", 빈도수 : " + words.get(i).getFrequency());
		}
	}
	
	//상위 20개 키워드 출력
	public void frequencyRank20()
	{
		for(int i=0; i < 20; i++)
		{
			System.out.println( i + ". 키워드 : " + (words.get(frequencyRank.get(i))).getWord() 
								+ ", 빈도수 : " + words.get(frequencyRank.get(i)).getFrequency());
		}
	}
}
