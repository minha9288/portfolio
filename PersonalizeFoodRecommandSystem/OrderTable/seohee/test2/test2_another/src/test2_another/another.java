package test2_another;
import java.awt.*;
import javax.swing.*;
public class another extends JFrame{
 
 JPanel tabLeft = new JPanel();
 JPanel tabCenter = new JPanel();
 JPanel tabBottom = new JPanel();
 
 
 
 JPanel leftPl1 = new JPanel();
 JPanel leftPl2 = new JPanel();
 
 JSplitPane splR = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,
   tabCenter, tabBottom);
 JSplitPane splL = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
     true, tabLeft, splR);
 Container con;

 public another(){
  super("title");
  init();
//  start();
  this.setSize(600,480);
  Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
  Dimension dimFrm = this.getSize();
    
  int xpos = (int)(dimScreen.getWidth()/2 - dimFrm.getWidth()/2);
  int ypos = (int)(dimScreen.getHeight()/2 - dimFrm.getHeight()/2);
  this.setLocation(xpos, ypos);
    
  this.setVisible(true);
  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
 }
 
 public void init(){
  con = this.getContentPane();
  // 창 크기에 따른 탭 모양 변형 방지(스크롤 생성)
  tabLeft.add(leftPl1);
  tabLeft.add(leftPl2);
//  splL.setDividerSize(10);
  splL.setDividerLocation(200);
  splL.setResizeWeight(0.3);
  splR.setDividerLocation(300);
//  spl.setContinuousLayout(false);
  splL.setOneTouchExpandable(true);
  con.add(splL);
 }
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  new another();
 }

}
