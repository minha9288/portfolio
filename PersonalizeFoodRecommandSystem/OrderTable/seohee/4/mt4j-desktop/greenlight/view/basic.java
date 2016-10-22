package view;

import java.awt.event.KeyEvent;
import java.net.Socket;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.widgets.MTList;
import org.mt4j.components.visibleComponents.widgets.MTSceneMenu;
import org.mt4j.components.visibleComponents.widgets.MTSceneWindow;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.globalProcessors.CursorTracer;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.sceneManagement.transition.BlendTransition;
import org.mt4j.sceneManagement.transition.FadeTransition;
import org.mt4j.util.MTColor;
import org.mt4j.util.font.FontManager;
import org.mt4j.util.font.IFont;
import org.mt4j.util.logging.ILogger;
import org.mt4j.util.logging.MTLoggerFactory;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.opengl.GLFBO;

import advanced.mtShell.ICreateScene;


public class basic extends AbstractScene {
	/** The Constant logger. */
	private static final ILogger logger = MTLoggerFactory.getLogger(basic.class.getName());
	static{
		logger.setLevel(ILogger.INFO);
	}
	
	/** The app. */
	private AbstractMTApplication app;
	
	/** The has fbo. */
	private boolean hasFBO;
	
	/** The list. */
	private MTList list;
	
	/** The font. */
	private IFont font;
	
	/** The list width. */
	private float listWidth;
	
	/** The list height. */
	private int listHeight;
	
	/** The switch directly to scene. */
	private boolean switchDirectlyToScene = false;
	
	public basic(AbstractMTApplication mtApplication, String name) {
		super(mtApplication, name);
		this.app = mtApplication;
		this.hasFBO = GLFBO.isSupported(app);
		this.switchDirectlyToScene = !this.hasFBO || switchDirectlyToScene;
		
		this.registerGlobalInputProcessor(new CursorTracer(app, this));
		
		this.setClearColor(new MTColor(0,0,0,255));
		
		//CREATE LIST
		list = new MTList(mtApplication,0, 0, listWidth, listHeight, 40);
		
		font = FontManager.getInstance().createFont(app, "SansSerif", 18, MTColor.WHITE);
		
		this.person4();
		
		getCanvas().addChild(list);
		list.rotateZ(list.getCenterPointLocal(), -90, TransformSpace.LOCAL);
		list.setPositionGlobal(new Vector3D(app.width/2f, app.height/2f));
		getCanvas().setFrustumCulling(true); 
		
		//Scene transition effect
		if (this.hasFBO){
			this.setTransition(new BlendTransition(app, 730));	
		}else{
			this.setTransition(new FadeTransition(app, 730));	
		}
	}
	
	public void person4() {
		this.processGestureEvent_1(new ICreateScene() {
			public Iscene getNewScene() {
				return new view(app, "1-1");
			}
			public String getTitle() {
				return "table1";
			}
		});
		
		this.processGestureEvent_2(new ICreateScene() {
			public Iscene getNewScene() {
				return new view(app, "1-2");
			}
			public String getTitle() {
				return "table2";
			}
		});
		
		this.processGestureEvent_3(new ICreateScene() {
			public Iscene getNewScene() {
				return new view(app, "1-3");
			}
			public String getTitle() {
				return "table3";
			}
		});
		
		this.processGestureEvent_4(new ICreateScene() {
			public Iscene getNewScene() {
				return new view(app, "1-4");
			}
			public String getTitle() { // ������ ���� ���̴� �۾�
				return "table4";
			}
		});
		
	}
	
	
	public boolean processGestureEvent_1(final ICreateScene createScene) {
		final Iscene scene = createScene.getNewScene();
							
		if (!switchDirectlyToScene){//We have FBO support -> show scene in a window first
						
			if (hasFBO && scene instanceof AbstractScene){
				((AbstractScene) scene).setTransition(new BlendTransition(app, 300));	
			}
				
			final MTSceneWindow sceneWindow = new MTSceneWindow(app, scene, 1, 1);

			sceneWindow.setFillColor(new MTColor(50,50,50,200));
			sceneWindow.scaleGlobal(0.5f, 0.5f, 0.5f, sceneWindow.getCenterPointGlobal());
			sceneWindow.setPositionGlobal(new Vector3D(app.width-app.width/4, app.height-app.height/4));
			sceneWindow.addGestureListener(DragProcessor.class, new InertiaDragAction());
			sceneWindow.rotateX(new Vector3D(app.width/2f, app.height/2f), 180f); 
			sceneWindow.rotateY(new Vector3D(app.width/2f, app.height/2f), 180f); 
			sceneWindow.setPickable(false); // ȭ�� �̵� �ȵǰ� ����
			getCanvas().addChild(sceneWindow);
		}else {
			//No FBO available -> change to the new scene fullscreen directly			
			float menuWidth = 64;
			float menuHeight = 64;
			MTSceneMenu sceneMenu = 
			new MTSceneMenu(app, scene, app.width-menuWidth, app.height-menuHeight, menuWidth, menuHeight);
			sceneMenu.addToScene();
						
			app.addScene(scene);
			app.pushScene();
			app.changeScene(scene);
		}
		return false;
	}
	
	public boolean processGestureEvent_2(final ICreateScene createScene) {
		final Iscene scene = createScene.getNewScene();
							
		if (!switchDirectlyToScene){//We have FBO support -> show scene in a window first
						
			if (hasFBO && scene instanceof AbstractScene){
				((AbstractScene) scene).setTransition(new BlendTransition(app, 300));	
			}
				
			final MTSceneWindow sceneWindow = new MTSceneWindow(app, scene, 1, 1); // applet, scene, �׵θ� ���εβ�, �׵θ� ���εβ�
			// �׵θ� �β��� 1�̰ų� 50 �̷��� xǥ�ö� �ִ�ȭ ǥ�ð� �ִµ� 5�� 10�̸� ���� �۾�����.
			sceneWindow.setFillColor(new MTColor(50,50,50,200)); // �׵θ� ��
			sceneWindow.scaleGlobal(0.5f, 0.5f, 0.5f, sceneWindow.getCenterPointGlobal()); // ȭ���� ũ��(1�̸� ��üȭ��) ������, ������, ?, ?	
			sceneWindow.setPositionGlobal(new Vector3D(app.width-app.width/1.333f, app.height-app.height/4)); // ȭ�� ��ġ
			sceneWindow.addGestureListener(DragProcessor.class, new InertiaDragAction());
			sceneWindow.rotateX(new Vector3D(app.width/2f, app.height/2f), 180f); // ȭ�� x������ ������
			sceneWindow.rotateY(new Vector3D(app.width/2f, app.height/2f), 180f); // ȭ�� Y������ ������
			sceneWindow.setPickable(false); // ȭ�� �̵� �ȵǰ� ����
			getCanvas().addChild(sceneWindow);
		}else {
			//No FBO available -> change to the new scene fullscreen directly			
			float menuWidth = 64;
			float menuHeight = 64;
			MTSceneMenu sceneMenu = 
			new MTSceneMenu(app, scene, app.width-menuWidth, app.height-menuHeight, menuWidth, menuHeight);
			sceneMenu.addToScene();
						
			app.addScene(scene);
			app.pushScene();
			app.changeScene(scene);
		}
		return false;
	}
	
	/**
	 * ū ȭ�鿡 ���� ȭ�� ����
	 * @param createScene
	 * @return
	 */
	public boolean processGestureEvent_4(final ICreateScene createScene) {
		final Iscene scene = createScene.getNewScene();
							
		if (!switchDirectlyToScene){//We have FBO support -> show scene in a window first
						
			if (hasFBO && scene instanceof AbstractScene){
				((AbstractScene) scene).setTransition(new BlendTransition(app, 300));	
			}
				
			final MTSceneWindow sceneWindow = new MTSceneWindow(app, scene, 1, 1); // applet, scene, �׵θ� ���εβ�, �׵θ� ���εβ�
			// �׵θ� �β��� 1�̰ų� 50 �̷��� xǥ�ö� �ִ�ȭ ǥ�ð� �ִµ� 5�� ����.(�̻���...)
			sceneWindow.setFillColor(new MTColor(50,50,50,200)); // �׵θ� ��
			sceneWindow.scaleGlobal(0.5f, 0.5f, 0.5f, sceneWindow.getCenterPointGlobal()); // ȭ���� ũ��(1�̸� ��üȭ��) ������, ������, ?, ?	
			sceneWindow.setPositionGlobal(new Vector3D(app.width-app.width/4, app.height-app.height/4)); // ȭ�� ��ġ
			sceneWindow.addGestureListener(DragProcessor.class, new InertiaDragAction());
			sceneWindow.setPickable(false); // ȭ�� �̵� �ȵǰ� ����
			getCanvas().addChild(sceneWindow);
		}else{
			//No FBO available -> change to the new scene fullscreen directly			
			float menuWidth = 64;
			float menuHeight = 64;
			MTSceneMenu sceneMenu = 
			new MTSceneMenu(app, scene, app.width-menuWidth, app.height-menuHeight, menuWidth, menuHeight);
			sceneMenu.addToScene();
						
			app.addScene(scene);
			app.pushScene();
			app.changeScene(scene);
		}
		return false;
	}
	
	/**
	 * ū ȭ�鿡 ���� ȭ�� ����
	 * @param createScene
	 * @return
	 */
	public boolean processGestureEvent_3(final ICreateScene createScene) {
		final Iscene scene = createScene.getNewScene();
							
		if (!switchDirectlyToScene){//We have FBO support -> show scene in a window first
						
			if (hasFBO && scene instanceof AbstractScene){
				((AbstractScene) scene).setTransition(new BlendTransition(app, 300));	
			}
				
			final MTSceneWindow sceneWindow = new MTSceneWindow(app, scene,1, 1); // applet, scene, �׵θ� ���εβ�, �׵θ� ���εβ�
			// �׵θ� �β��� 1�̰ų� 50 �̷��� xǥ�ö� �ִ�ȭ ǥ�ð� �ִµ� 5�� ����.(�̻���...)
			sceneWindow.setFillColor(new MTColor(50,50,50,200)); // �׵θ� ��
			sceneWindow.scaleGlobal(0.5f, 0.5f, 0.5f, sceneWindow.getCenterPointGlobal()); // ȭ���� ũ��(1�̸� ��üȭ��) ������, ������, ?, ?	
			sceneWindow.setPositionGlobal(new Vector3D(app.width-app.width/1.333f, app.height-app.height/4)); // ȭ�� ��ġ
			sceneWindow.addGestureListener(DragProcessor.class, new InertiaDragAction());
			sceneWindow.setPickable(false); // ȭ�� �̵� �ȵǰ� ����
			getCanvas().addChild(sceneWindow);
		}else{
			//No FBO available -> change to the new scene fullscreen directly			
			float menuWidth = 64;
			float menuHeight = 64;
			MTSceneMenu sceneMenu = 
			new MTSceneMenu(app, scene, app.width-menuWidth, app.height-menuHeight, menuWidth, menuHeight);
			sceneMenu.addToScene();
						
			app.addScene(scene);
			app.pushScene();
			app.changeScene(scene);
		}
		return false;
	}
	
	public void onEnter() {
		getMTApplication().registerKeyEvent(this);
	}
	
	public void onLeave() {	
		getMTApplication().unregisterKeyEvent(this);
	}
	
	/**
	 * Key event.
	 * 
	 * @param e the e
	 */
	public void keyEvent(KeyEvent e){
		int evtID = e.getID();
		if (evtID != KeyEvent.KEY_PRESSED)
			return;
		switch (e.getKeyCode()){
		case KeyEvent.VK_F:
			System.out.println("FPS: " + getMTApplication().frameRate);
			break;
		case KeyEvent.VK_M:
			System.out.println("Max memory: " + Runtime.getRuntime().maxMemory() + " <-> Free memory: " + Runtime.getRuntime().freeMemory());
			break;	
		case KeyEvent.VK_C:
			getMTApplication().invokeLater(new Runnable() {
				public void run() {
				}
			});
			break;
		default:
			break;
		}
	}

}