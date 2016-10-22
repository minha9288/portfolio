package view;

import java.net.Socket;

import org.mt4j.MTApplication;


public class start_basic extends MTApplication {
	private static final long serialVersionUID = 1L;

	public static void main(String args[]){
		try {
			
	    	initialize();
	    } catch(Exception e) {
	    	System.out.println("E: " + e.getMessage());
	    }
	}
	
	@Override
	public void startUp(){
		this.addScene(new basic(this, "Multi-Touch Shell Scene"));
	}
	
}
