package greenlight.touchTest;

import java.util.ArrayList;

public class RecommandAlgorithm {
	
	ArrayList<String> recMenu = new ArrayList<String>();
	
	public ArrayList<String> getRecommandAlgorithmResult(){
		System.out.println("µî¾î¿È");
		recMenu.add("Àüº¹Á×");
		recMenu.add("ºÒ°í±â");
		recMenu.add("±èÄ¡Âî°³");
		recMenu.add("¼öÁ¤°ú");
		System.out.println(recMenu);
		return recMenu;
	}

}
