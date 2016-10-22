package please;

import java.awt.event.KeyEvent;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.gestureAction.TapAndHoldVisualizer;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldProcessor;
import org.mt4j.input.inputProcessors.globalProcessors.CursorTracer;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.ToolsMath;
import org.mt4j.util.math.Vector3D;

public class please extends AbstractScene {
	private AbstractMTApplication mtApp;
	
	public please(AbstractMTApplication mtApplication, String name) {
		super(mtApplication, name);
		this.mtApp = mtApplication;
		this.setClearColor(new MTColor(255, 255, 255, 255)); // 화면 배경 색
		
		
		// 도형 드래그 하는 부분
		CursorTracer c = new CursorTracer(mtApplication, this);
		registerGlobalInputProcessor(c);
		int count = 2;
		for (int i = 0; i < count; i++) {
			MTRectangle r = new MTRectangle(mtApplication,0,0,ToolsMath.getRandom(50, 250),ToolsMath.getRandom(50, 250));
			r.setFillColor(new MTColor(ToolsMath.getRandom(50,255),ToolsMath.getRandom(50,255),ToolsMath.getRandom(50,255)));
			r.addGestureListener(DragProcessor.class, new InertiaDragAction());
			getCanvas().addChild(r);
			r.setPositionGlobal(new Vector3D(mtApp.width/2f, mtApp.height/2f));
		}
		
		
		
		//Add tap&hold gesture to clear all tails
		TapAndHoldProcessor tapAndHold = new TapAndHoldProcessor(mtApplication);
		tapAndHold.setMaxFingerUpDist(10);
		tapAndHold.setHoldTime(3000);
		
		//Add touch feedback
		this.registerGlobalInputProcessor(new CursorTracer(mtApp, this));
	}

	public void keyEvent(KeyEvent e){
		if (e.getID() != KeyEvent.KEY_PRESSED)
			return;
		switch (e.getKeyCode()){
		case KeyEvent.VK_F:
			System.out.println("FPS: " + mtApp.frameRate);
			break;
		case KeyEvent.VK_PLUS:
			getSceneCam().zoomAmount(5);
			getSceneCam().update();
			break;
		default:
			break;
		}
	}
	
	public void onEnter() {
		getMTApplication().registerKeyEvent(this);
	}
	
	public void onLeave() {	
		getMTApplication().unregisterKeyEvent(this);
	}

}
