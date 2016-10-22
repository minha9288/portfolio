package basic.javaGUI;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.globalProcessors.CursorTracer;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.ToolsMath;
import org.mt4j.util.math.Vector3D;

import processing.core.PApplet;
import processing.core.PImage;
import org.mt4j.components.visibleComponents.widgets.MTImage;


public class SwingIntegrationScene extends AbstractScene {
	
	private PApplet pa;
	
	public SwingIntegrationScene(AbstractMTApplication mtApplication, String name) {
		super(mtApplication, name);
		CursorTracer c = new CursorTracer(mtApplication, this);
		registerGlobalInputProcessor(c);
		int count = 2;
		
		pa = new PApplet();
		
		
		for (int i = 0; i < count; i++) {
			
			/*
			String imageUrl = "C:\\SmartTable\\aaa.PNG";
			MTImage photo = new MTImage(pa, pa.loadImage(imageUrl));
			photo.addGestureListener(DragProcessor.class, new InertiaDragAction());
			getCanvas().addChild(photo);
			photo.setPositionGlobal(new Vector3D(ToolsMath.getRandom(0, mtApplication.width), ToolsMath.getRandom(0, mtApplication.height)));		

			
			PImage img = pa.loadImage("C:\\SmartTable\\3.png");
			MTRectangle i1 = new MTRectangle(pa, img);
			i1.addGestureListener(DragProcessor.class, new InertiaDragAction());
			getCanvas().addChild(i1);
			i1.setPositionGlobal(new Vector3D(ToolsMath.getRandom(0, mtApplication.width), ToolsMath.getRandom(0, mtApplication.height)));	
			*/
			
			MTRectangle ir = new MTRectangle(pa, pa.loadImage("C:\\SmartTable\\aaa.PNG"));
			ir.setFillColor(new MTColor(ToolsMath.getRandom(50,255),ToolsMath.getRandom(50,255),ToolsMath.getRandom(50,255)));
			ir.addGestureListener(DragProcessor.class, new InertiaDragAction());
			getCanvas().addChild(ir);
			ir.setPositionGlobal(new Vector3D(ToolsMath.getRandom(0, mtApplication.width), ToolsMath.getRandom(0, mtApplication.height)));

			
			
			MTRectangle r = new MTRectangle(mtApplication,0,0,ToolsMath.getRandom(50, 250),ToolsMath.getRandom(50, 250));
			r.setFillColor(new MTColor(ToolsMath.getRandom(50,255),ToolsMath.getRandom(50,255),ToolsMath.getRandom(50,255)));
			r.addGestureListener(DragProcessor.class, new InertiaDragAction());
			getCanvas().addChild(r);
			r.setPositionGlobal(new Vector3D(ToolsMath.getRandom(0, mtApplication.width), ToolsMath.getRandom(0, mtApplication.height)));
		}
	}
	
	public void onEnter() {}
	
	public void onLeave() {}

}

