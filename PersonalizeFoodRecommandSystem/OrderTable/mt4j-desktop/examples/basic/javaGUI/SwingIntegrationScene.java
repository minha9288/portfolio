package basic.javaGUI;

import java.util.ArrayList;
import java.util.List;

import org.mt4j.AbstractMTApplication;
import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.input.IMTEventListener;
import org.mt4j.input.MTEvent;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.input.inputProcessors.globalProcessors.CursorTracer;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.sceneManagement.IPreDrawAction;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.font.FontManager;
import org.mt4j.util.font.IFont;
import org.mt4j.util.math.ToolsMath;
import org.mt4j.util.math.Vector3D;

import processing.core.PApplet;
import processing.core.PImage;

import org.mt4j.components.visibleComponents.widgets.MTImage;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.components.visibleComponents.widgets.buttons.MTImageButton;
import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;

public class SwingIntegrationScene extends AbstractScene{
	
	//test
	
	private MTComponent fotoContainer;
	private MTComponent pictureLayer;
	private AbstractMTApplication app;
	private static PImage closeButtonImage;
	
	private List<MTImage> mtFotos;
	private PApplet pa;
	
	public SwingIntegrationScene(AbstractMTApplication mtApplication, String name) {
		super(mtApplication, name);
		CursorTracer c = new CursorTracer(mtApplication, this);
		registerGlobalInputProcessor(c);
		int count = 2;
		
		
		
		//test
		pa = new PApplet();
		mtFotos = new ArrayList<MTImage>();
		/*
		MTImage photo = new MTImage(pa, pa.loadImage("C:\\HONG\\image1.jpg"));
		photo.setName("image1");
		mtFotos.add(photo);
		
		photo = new MTImage(pa, pa.loadImage("C:\\HONG\\Lighthouse.jpg"));
		photo.setName("Lighthouse");
		mtFotos.add(photo);
		*/
		
		//String imageUrl;
	
		for (int i = 0; i < count; i++) {
			
			String imageUrl = "C:\\HONG\\Jsp\\image"+i+".png";
			MTImage photo = new MTImage(pa, pa.loadImage(imageUrl));
			photo.addGestureListener(DragProcessor.class, new InertiaDragAction());
			getCanvas().addChild(photo);
			photo.setPositionGlobal(new Vector3D(ToolsMath.getRandom(0, mtApplication.width), ToolsMath.getRandom(0, mtApplication.height)));		
			
			/*
			PImage img = pa.loadImage("C:\\HONG\\Lighthouse.jpg");
			MTRectangle i1 = new MTRectangle(pa, img);
			i1.addGestureListener(DragProcessor.class, new InertiaDragAction());
			getCanvas().addChild(i1);
			i1.setPositionGlobal(new Vector3D(ToolsMath.getRandom(0, mtApplication.width), ToolsMath.getRandom(0, mtApplication.height)));	
			*/
			MTRectangle r = new MTRectangle(mtApplication,0,0,ToolsMath.getRandom(50, 250),ToolsMath.getRandom(50, 250));
			r.setFillColor(new MTColor(ToolsMath.getRandom(50,255),ToolsMath.getRandom(50,255),ToolsMath.getRandom(50,255)));
			r.addGestureListener(DragProcessor.class, new InertiaDragAction());
			getCanvas().addChild(r);
			r.setPositionGlobal(new Vector3D(ToolsMath.getRandom(0, mtApplication.width), ToolsMath.getRandom(0, mtApplication.height)));				
		}
	}
	
	public void onLeave() {}

}

