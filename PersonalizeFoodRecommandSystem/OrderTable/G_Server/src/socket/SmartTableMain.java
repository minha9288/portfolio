package socket;

import java.net.ServerSocket;
import java.net.Socket;

public class SmartTableMain {
	
	public static final int ServerPort = 9286;
	public static final String ServerIP = "127.0.0.1";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServerSocket serverSocket = null;
		try{
			serverSocket = new ServerSocket(ServerPort);
			System.out.println("S: Waiting...");
			while(true){
				Socket client = serverSocket.accept();
				System.out.println("S: Receiving...");
				Thread desktopServerThread = new SocketConn(client);
				desktopServerThread.start();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
