package com.greenlight.chatting;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class ChatClient {

	Socket sock;
	BufferedReader reader;
	PrintWriter writer;
	String userName="";
	String insert="";

	//socket연결
	public void setUpNetworking(){
		try{
			sock = new Socket("127.0.0.1", 5050);
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("networking established");
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}

	
	//chat_input.jsp에서 전송버튼 눌렀을 때 메세지 전송 수행하는 함수
	public void actionPerformed(String text){
		try{				
			writer.println(text);
			writer.flush();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//reader객체 전달
	public BufferedReader re(){
		return reader;
	}
}
