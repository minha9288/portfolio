package com.test.hannanum.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import com.test.hannanum.text.Book;

public class Main
{
	public static void main(String[] args)
	{
		/*
		String testString ="";
		byte buffer[] = new byte[1000];
		
		try 
		{
			FileInputStream fis = new FileInputStream("C:\\Users\\GunHee\\Desktop\\도서정보도서텍본\\그리고 아무도 없었다 - 추리.txt");
			Reader reader = new InputStreamReader(fis);
			BufferedReader in = new BufferedReader(reader);
			
			StringBuilder builder = new StringBuilder();
			int ch;
			while ( (ch = in.read()) != -1 )
			{
				builder.append((char)ch);
			}
			
			testString += builder.toString();
			fis.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println(testString);
		*/
		
		
		Book test = new Book("좋지않아, 안좋아, 안맛있어, 맛있지않아, 맛있지 않아");
		
		test.wordsPrint();
		
	}
}
