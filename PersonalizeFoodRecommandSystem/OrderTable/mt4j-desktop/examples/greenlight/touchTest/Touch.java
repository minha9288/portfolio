package greenlight.touchTest;

import advanced.flickrMT.FlickrScene;
import org.mt4j.MTApplication;

public class Touch extends MTApplication{
	
	private static final long serialVersionUID = 1L;


	public static void main(String args[]){
		initialize();
	}
	
	@Override
	public void startUp(){
		this.addScene(new TouchScene(this, "Touch Scene", "menu1", "menu2", "menu3", "menu4"));
	}

}
