package com.greenlight.chatting;


import javax.swing.*; //for GUI
import java.io.*;  //for read-write
import java.net.*;  //for Socket
import java.awt.*;  //for GUI
import java.util.ArrayList; //for ArrayList
import java.util.Iterator;  //for Iterator
import java.awt.event.*; //for ActionListener
import java.util.*;

public class ChatServer{
	
	JTextArea from_client;
	ArrayList clientOutputStreams;
	PrintWriter writer;
	

	public static void main(String args[]){
		new ChatServer().go();
	}

	
	public void go(){		
		//================================
		// GUI부분
		//=================================
		JFrame frame = new JFrame("Server");
		JPanel panel = new JPanel();
		
		from_client = new JTextArea(15,28);
		JScrollPane scroll = new JScrollPane(from_client);
		panel.add(scroll);

		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(350, 400);
		frame.setVisible(true);
		
		
		//===========================================
		// 연결부분
		//===========================================
		clientOutputStreams = new ArrayList();
		try{
			ServerSocket serverSock = new ServerSocket(5050);
			while(true){
				Socket clientSocket = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				clientOutputStreams.add(writer);
				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();
				//System.out.println("got a connection");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public class ClientHandler implements Runnable{
		BufferedReader reader;
		Socket sock;
		
		public ClientHandler(Socket clientSocket){
			try{
				sock = clientSocket;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(isReader);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		public void run(){
			String message;
			try{
				while((message = reader.readLine()) != null){
					System.out.println(message);
					from_client.append(message + " /from client \n" );				
					tellClient(message);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	public void tellClient(String message){
		Iterator it = clientOutputStreams.iterator();
		while(it.hasNext()){
			try{
				writer = (PrintWriter)it.next();
				writer.println(message);
				writer.flush();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
}