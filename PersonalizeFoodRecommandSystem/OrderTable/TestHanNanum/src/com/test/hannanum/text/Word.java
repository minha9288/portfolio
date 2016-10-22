package com.test.hannanum.text;

public class Word 
{
	String word;
	int frequency;
	
	public Word(String _word) 
	{
		this.word = _word;
		this.frequency = 1;
	}

	//get,set
	public String getWord() 
	{
		return word;
	}

	public int getFrequency() 
	{
		return frequency;
	}

	public void upFrequency() 
	{
		this.frequency++;
	}
	
	
}
