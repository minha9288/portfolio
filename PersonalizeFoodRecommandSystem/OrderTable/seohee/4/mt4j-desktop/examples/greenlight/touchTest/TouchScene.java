package greenlight.touchTest;

import java.util.ArrayList;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.widgets.buttons.MTImageButton;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;
import org.mt4j.util.camera.MTCamera;
import org.mt4j.util.math.ToolsMath;
import org.mt4j.util.math.Vector3D;

import processing.core.PImage;

public class TouchScene extends AbstractScene {
	
	private AbstractMTApplication app;
	MTComponent topLayer;
	
	MTImageButton menu1Img;
	
	public TouchScene(AbstractMTApplication mtAppl, String name) {
		super(mtAppl, name);
		this.app = mtAppl;
		
		topLayer = new MTComponent(app, "top layer group", new MTCamera(app));
		
		//Load from classpath
				PImage keyboardImg = app.loadImage("advanced" + AbstractMTApplication.separator + "flickrMT"+ AbstractMTApplication.separator + "data"+ AbstractMTApplication.separator 
						+ "keyb128.png");
				
				//final MTImageButton keyboardButton = new MTImageButton(app, keyboardImg);
				final MTImageButton keyboardButton = new MTImageButton(app, app.loadImage("C:\\Users\\김서희\\Pictures\\2.PNG"));
				keyboardButton.setFillColor(new MTColor(255,255,255,200));
				keyboardButton.setName("KeyboardButton");
				keyboardButton.setNoStroke(true);
				keyboardButton.translateGlobal(new Vector3D(100, 300));
				topLayer.addChild(keyboardButton);	
				
				keyboardButton.addGestureListener(TapProcessor.class, new IGestureEventListener() {
					@Override
					public boolean processGestureEvent(MTGestureEvent ge) {
						TapEvent te = (TapEvent)ge;
						switch (te.getTapID()) {
						case TapEvent.TAPPED:
									
							final MTImageButton plusButton = new MTImageButton(app, app.loadImage("C:\\Users\\김서희\\Pictures\\3.PNG"));
							plusButton.setFillColor(new MTColor(255,255,255,200));
							plusButton.setName("KeyboardButton");
							plusButton.setNoStroke(true);
							plusButton.translateGlobal(new Vector3D(ToolsMath.getRandom(0, 1000), ToolsMath.getRandom(0,1000)));
							topLayer.addChild(plusButton);	
							
							break;
						default:
							break;
						}
						return true;
					}
				});
				
				this.getCanvas().addChild(topLayer);
	}
	
	public TouchScene(AbstractMTApplication mtAppl, String name, 
			String menu1, String menu2, String menu3, String menu4){
		//메뉴 이름 관련 매개변수 리스트로 받아오기. (대표추천메뉴 4개, 서브추천메뉴 x개)
		super(mtAppl, name);
		this.app = mtAppl;
		
		topLayer = new MTComponent(app, "top layer group", new MTCamera(app));
		
		//추천메뉴 4가지 이름 받아오기 (이름==파일명)
		ArrayList<String> recMenuImgName = new ArrayList<String>();
		for(int i=0; i<4; i++){
			//추천메뉴 4가지 이름 받아오기
			String imgName = (i+1+1) + "";
			recMenuImgName.add(imgName);
		}
		
		//메뉴 4가지 해당 이미지 로딩
		menu1Img = new MTImageButton(app, app.loadImage("C:\\Users\\김서희\\Pictures\\" + recMenuImgName.get(0) + ".PNG"));
		menu1Img.setName("menu1Img");
		menu1Img.setNoStroke(true);
		menu1Img.translateGlobal(new Vector3D(50, 200));
		topLayer.addChild(menu1Img);	
		final MTImageButton menu2Img = new MTImageButton(app, app.loadImage("C:\\Users\\김서희\\Pictures\\" + recMenuImgName.get(1) + ".PNG"));
		menu2Img.setName("menu2Img");
		menu2Img.setNoStroke(true);
		menu2Img.translateGlobal(new Vector3D(300, 200));
		topLayer.addChild(menu2Img);
		final MTImageButton menu3Img = new MTImageButton(app, app.loadImage("C:\\Users\\김서희\\Pictures\\" + recMenuImgName.get(2) + ".PNG"));
		menu3Img.setName("menu3Img");
		menu3Img.setNoStroke(true);
		menu3Img.translateGlobal(new Vector3D(550, 200));
		topLayer.addChild(menu3Img);
		final MTImageButton menu4Img = new MTImageButton(app, app.loadImage("C:\\Users\\김서희\\Pictures\\" + recMenuImgName.get(3) + ".PNG"));
		menu4Img.setName("menu4Img");
		menu4Img.setNoStroke(true);
		menu4Img.translateGlobal(new Vector3D(800, 200));
		topLayer.addChild(menu4Img);
		
		//++리스너 메뉴당 하나로 합치기 추가해야함!!
		/*
		menu1Img.addGestureListener(TapProcessor.class, new IGestureEventListener() {
			@Override
			public boolean processGestureEvent(MTGestureEvent ge) {
				TapEvent te = (TapEvent)ge;
				switch (te.getTapID()) {
				case TapEvent.TAPPED:
					final MTImageButton plusButton = new MTImageButton(app, app.loadImage("C:\\HONG\\OrderTable\\ImageFiles\\menu2_s.jpg"));
					plusButton.setName("plusButton");
					plusButton.setNoStroke(true);
					plusButton.translateGlobal(new Vector3D(100, 400));
					topLayer.addChild(plusButton);
					
					plusButton.addGestureListener(TapProcessor.class, new IGestureEventListener() {
						@Override
						public boolean processGestureEvent(MTGestureEvent ge) {
							TapEvent te = (TapEvent)ge;
							switch (te.getTapID()) {
							case TapEvent.TAPPED:
								menu1Img = new MTImageButton(app, app.loadImage("C:\\HONG\\OrderTable\\ImageFiles\\menu2.jpg"));
								menu1Img.translateGlobal(new Vector3D(50, 200));
								topLayer.addChild(menu1Img);
								break;
							default:
								break;
							}
							return true;
							}
						});
					
					break;
					
				default:
					break;
				}
				return true;
				}
			});
		*/
			/*
		menu1Img.addGestureListener(TapProcessor.class, new IGestureEventListener() {
			@Override
			public boolean processGestureEvent(MTGestureEvent ge) {
				TapEvent te = (TapEvent)ge;
				switch (te.getTapID()) {
				case TapEvent.TAPPED:
					final MTImageButton plusButton = new MTImageButton(app, app.loadImage("C:\\Users\\김서희\\Pictures\\18.jpg"));
					plusButton.setFillColor(new MTColor(255,255,255,200));
					plusButton.setNoStroke(true);
					plusButton.translateGlobal(new Vector3D(100, 400));
					topLayer.addChild(plusButton);
					
					break;
					
				default:
					break;
				}
				return true;
				}
			});
			
				/*
				menuImg1Button.addGestureListener(TapProcessor.class, new IGestureEventListener() {
					@Override
					public boolean processGestureEvent(MTGestureEvent ge) {
						TapEvent te = (TapEvent)ge;
						switch (te.getTapID()) {
						case TapEvent.TAPPED:
									
							final MTImageButton plusplusButton = new MTImageButton(app, app.loadImage("C:\\HONG\\OrderTable\\ImageFiles\\menu2.jpg"));
							plusplusButton.setFillColor(new MTColor(255,255,255,200));
							plusplusButton.setName("KeyboardButton");
							plusplusButton.setNoStroke(true);
							plusplusButton.translateGlobal(new Vector3D(ToolsMath.getRandom(0, 1000), ToolsMath.getRandom(0,1000)));
							topLayer.addChild(plusplusButton);	
							
							break;
						default:
							break;
						}
						return true;
					}
				});
				*/
				this.getCanvas().addChild(topLayer);
	}
}

