package controller;

import bean.Food;
import bean.User;

public class GreenLightManager {

	private static GreenLightManager manager=null;
	private GreenLightManager(){}
	public static GreenLightManager getInstance(){
		if(manager.equals(null))
			synchronized (GreenLightManager.class) {
				if(manager.equals(null))
					manager=new GreenLightManager();
			}
		return manager;
	}
	
}
