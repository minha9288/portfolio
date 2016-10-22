package greenlight.touchTest;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.flickProcessor.FlickEvent;
import org.mt4j.input.inputProcessors.componentProcessors.flickProcessor.FlickProcessor;
import org.mt4j.input.inputProcessors.globalProcessors.CursorTracer;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.sceneManagement.transition.BlendTransition;
import org.mt4j.sceneManagement.transition.FadeTransition;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.font.FontManager;
import org.mt4j.util.math.ToolsMath;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.opengl.GLFBO;

public class test extends AbstractScene{
	private AbstractMTApplication mtApp;
	
	public test(AbstractMTApplication mtApplication, String name) {
		super(mtApplication, name);
		this.mtApp = mtApplication;
		
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
	}
}
