package com.example.gettimeline;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import android.util.Log;
import android.widget.Toast;

public class ReadText {
	
	private static final String serverIP = "192.168.1.6"; //�� ip �Է��ϱ�
	private static final int serverPort = 9284;
	
	
	String timelineText ;
	String checkMenu[] = {"�Ұ���"};
	String expressLike[] = {"����"};
	String checkText = "";
	String readMenu;
	String likeMenu; 
	
	ReadText(){
		
	}

	
	public String ReadText(String text){
		
		System.out.println("���� ��� ����");
		
		timelineText = text;
		
		//checkMenu�� ��񿡼� ������ ���� �̸� ����
		//run();
		
		
		try{
			for(int i=0 ; i<checkMenu.length ; i++){
				
				checkText = checkMenu[i];
				if(timelineText.matches(".*"+checkText+".*")){
					readMenu = checkText;
				}else{
					System.out.println("��� ����");
				}
				
				checkText = expressLike[i];
				if(timelineText.matches(".*"+checkText+".*")){
					likeMenu = readMenu;
				}else{
					System.out.println("���� ��� ����");
				}
				
				
				
			}
		}catch(PatternSyntaxException e){
			System.out.println(e);
		}
		
		return likeMenu;
	}
	
	
/*
	public void run() {
		// TODO Auto-generated method stub
		try {
			InetAddress serverAddr = InetAddress.getByName(serverIP);

			Log.d("TCP", "C: Connecting...");
//			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//			StrictMode.setThreadPolicy(policy);
			Socket socket = new Socket(serverAddr, serverPort);

			try {

				//�����°�
				ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
				out.writeObject("getMenuName");
				
				Log.d("TCP", "C: Sent.");
				Log.d("TCP", "C: Done.");

				ObjectInputStream in = new ObjectInputStream(
						socket.getInputStream());
				ArrayList<String> getList = (ArrayList<String>) in.readObject();
				System.out.println(getList);

			} catch (Exception e) {
				Log.e("TCP", "C: Error1", e);
			} finally {
				socket.close();
			}
		} catch (Exception e) {
			Log.e("TCP", "C: Error2", e);
		}
	}
	*/
	
}