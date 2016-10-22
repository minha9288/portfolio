

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRootPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;



/**
 * Making a Multi-Window Program.
 * @author cdea
 */
public class MultiWindowGUI extends JDesktopPane implements AppSetup {
	int count1=0, count2=0, count3=0, count4=0, count=0;
	JInternalFrame inframe1_12, inframe1_34; // 1명이 앉을 경우
	JInternalFrame inframe2_12, inframe2_13_14_23_24, inframe2_34; // 2명이 앉았을 경우
	JInternalFrame inframe3_123_124, inframe3_134_234; // 3명이 앉았을 경우
	JInternalFrame inframe4;

    public MultiWindowGUI() {
    	// 데스크탑 안에서 드래그할 수 있게 설정
    	// 그런데 없어도 드래그는 됨...
        setDragMode(JDesktopPane.LIVE_DRAG_MODE);
        //setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }

    public void apply(final JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem new1 = new JMenuItem("1");
        JMenuItem new2 = new JMenuItem("2");
        JMenuItem new3 = new JMenuItem("3");
        JMenuItem new4 = new JMenuItem("4");
        
        final JDesktopPane desktop = this;
        
        new1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// 1명이면서 1번자리에 앉는 경우
        		if(count1!=1 && count==0) {	
        			inframe1_12 = new InternalFrame_for_one_12(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe1_12.getUI()).setNorthPane(null);
        	        inframe1_12.setBorder(null);
        	        
        	        
        			inframe1_12.setVisible(true);
        			desktop.add(inframe1_12);
        			try {
        				inframe1_12.setSelected(true);
        			} catch (PropertyVetoException pve) { }
        			
        			count1 = 1;
        			count++;
        		}
        		
        		// 2명이서 2번자리에 앉은 뒤 1번자리에 앉는 경우
        		if(count1!=1 && count2==1 && count==1) {
        			desktop.remove(inframe1_12);
        			
        			inframe2_12 = new InternalFrame_for_two_12s_1(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_12.getUI()).setNorthPane(null);
        	        inframe2_12.setBorder(null);
        	        
        	        inframe2_12.setVisible(true);
        	        desktop.add(inframe2_12);
        	        try {
        	        	inframe2_12.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe2_12 = new InternalFrame_for_two_12s_2(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_12.getUI()).setNorthPane(null);
        	        inframe2_12.setBorder(null);
        	        
        	        inframe2_12.setVisible(true);
        	        desktop.add(inframe2_12);
        	        try {
        	        	inframe2_12.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count1 = 1;
        			count++;
        		}
        		
        		// 2명이서 3번 또는 4번자리에 앉은 뒤 1번자리에 앉는 경우
        		if(count1!=1 && (count3==1 || count4==1) && count==1) {
        			desktop.remove(inframe1_34);
        			
        			inframe2_13_14_23_24 = new InternalFrame_for_two_13_14_23_24s_12(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_13_14_23_24.getUI()).setNorthPane(null);
        	        inframe2_13_14_23_24.setBorder(null);
        	        
        	        inframe2_13_14_23_24.setVisible(true);
        	        desktop.add(inframe2_13_14_23_24);
        	        try {
        	        	inframe2_13_14_23_24.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe2_13_14_23_24 = new InternalFrame_for_two_13_14_23_24s_34(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_13_14_23_24.getUI()).setNorthPane(null);
        	        inframe2_13_14_23_24.setBorder(null);
        	        
        	        inframe2_13_14_23_24.setVisible(true);
        	        desktop.add(inframe2_13_14_23_24);
        	        try {
        	        	inframe2_13_14_23_24.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        			
        			count1 = 1;
        			count++;
        		}
        		
        		// 3명이 2,3번자리 또는 2,4번 자리에 앉은 뒤 1번자리에 앉을 경우
        		if((count3==1 || count4==1) && count2==1 && count1!=1 && count==2) {
        			//desktop.remove(inframe2_1);
        			
        			inframe3_123_124 = new InternalFrame_for_three_123_124s_1(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_123_124.getUI()).setNorthPane(null);
        	        inframe3_123_124.setBorder(null);
        	        
        	        inframe3_123_124.setVisible(true);
        	        desktop.add(inframe3_123_124);
        	        try {
        	        	inframe3_123_124.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_123_124 = new InternalFrame_for_three_123_124s_2(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_123_124.getUI()).setNorthPane(null);
        	        inframe3_123_124.setBorder(null);
        	        
        	        inframe3_123_124.setVisible(true);
        	        desktop.add(inframe3_123_124);
        	        try {
        	        	inframe3_123_124.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_123_124 = new InternalFrame_for_three_123_124s_34(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_123_124.getUI()).setNorthPane(null);
        	        inframe3_123_124.setBorder(null);
        	        
        	        inframe3_123_124.setVisible(true);
        	        desktop.add(inframe3_123_124);
        	        try {
        	        	inframe3_123_124.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count1 = 1;
        			count++;
        		}
        		
        		// 3명이 3,4번자에 앉은 뒤 1번자리에 앉을 경우
        		if(count3==1 && count4==1 && count1!=1 && count==2) {
        			//desktop.remove(inframe2_12);
        			
        			inframe3_134_234 = new InternalFrame_for_three_134_234s_12(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_134_234.getUI()).setNorthPane(null);
        	        inframe3_134_234.setBorder(null);
        	        
        	        inframe3_134_234.setVisible(true);
        	        desktop.add(inframe3_134_234);
        	        try {
        	        	inframe3_134_234.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_134_234 = new InternalFrame_for_three_134_234s_3(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_134_234.getUI()).setNorthPane(null);
        	        inframe3_134_234.setBorder(null);
        	        
        	        inframe3_134_234.setVisible(true);
        	        desktop.add(inframe3_134_234);
        	        try {
        	        	inframe3_134_234.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_134_234 = new InternalFrame_for_three_134_234s_4(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_134_234.getUI()).setNorthPane(null);
        	        inframe3_134_234.setBorder(null);
        	        
        	        inframe3_134_234.setVisible(true);
        	        desktop.add(inframe3_134_234);
        	        try {
        	        	inframe3_134_234.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count1 = 1;
        			count++;
        		}
        		
        		// 4명의 사람이 2,3,4번자리에 앉은 뒤 1번자리에 앉는 경우
        		if(count2==1 && count3==1 && count4==1 && count1!=1 && count==3) {
        			//desktop.remove(inframe2_12);
        			
        			inframe4 = new InternalFrame_for_three_123_124s_1(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe4 = new InternalFrame_for_three_123_124s_2(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe4 = new InternalFrame_for_three_134_234s_3(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe4 = new InternalFrame_for_three_134_234s_4(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count1 = 1;
        			count++;
        		}
        	}
        });
        
        new2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// 1명이면서 2번자리에 앉는 경우
        		if(count2!=1 && count==0) {	
        			inframe1_12 = new InternalFrame_for_one_12(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe1_12.getUI()).setNorthPane(null);
        	        inframe1_12.setBorder(null);
        	        
        			inframe1_12.setVisible(true);
        			desktop.add(inframe1_12);
        			try {
        				inframe1_12.setSelected(true);
        			} catch (PropertyVetoException pve) { }
        			
        			count2 = 1;
        			count++;
        		}
        		
        		// 2명이서 1번자리에 앉은 뒤 2번자리에 앉는 경우
        		if(count1==1 && count2!=1 && count==1) {
        			desktop.remove(inframe1_12);
        			
        			inframe2_12 = new InternalFrame_for_two_12s_1(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_12.getUI()).setNorthPane(null);
        	        inframe2_12.setBorder(null);
        	        
        	        inframe2_12.setVisible(true);
        	        desktop.add(inframe2_12);
        	        try {
        	        	inframe2_12.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe2_12 = new InternalFrame_for_two_12s_2(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_12.getUI()).setNorthPane(null);
        	        inframe2_12.setBorder(null);
        	        
        	        inframe2_12.setVisible(true);
        	        desktop.add(inframe2_12);
        	        try {
        	        	inframe2_12.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count2=1;
        	        count++;
        		}
        		
        		// 2명이서 3번 또는 4번자리에 앉은 뒤 2번자리에 앉는 경우
        		if(count2!=1 && (count3==1 || count4==1) && count==1) {
        			desktop.remove(inframe1_34);
        			
        			inframe2_13_14_23_24 = new InternalFrame_for_two_13_14_23_24s_12(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_13_14_23_24.getUI()).setNorthPane(null);
        	        inframe2_13_14_23_24.setBorder(null);
        	        
        	        inframe2_13_14_23_24.setVisible(true);
        	        desktop.add(inframe2_13_14_23_24);
        	        try {
        	        	inframe2_13_14_23_24.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe2_13_14_23_24 = new InternalFrame_for_two_13_14_23_24s_34(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_13_14_23_24.getUI()).setNorthPane(null);
        	        inframe2_13_14_23_24.setBorder(null);
        	        
        	        inframe2_13_14_23_24.setVisible(true);
        	        desktop.add(inframe2_13_14_23_24);
        	        try {
        	        	inframe2_13_14_23_24.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        			
        			count2 = 1;
        			count++;
        		}
        		
        		// 3명이 1,3번자리 또는 1,4번 자리에 앉은 뒤 2번자리에 앉을 경우
        		if((count3==1 || count4==1) && count1==1 && count2!=1 && count==2) {
        			//desktop.remove(inframe2_1);
        			
        			inframe3_123_124 = new InternalFrame_for_three_123_124s_1(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_123_124.getUI()).setNorthPane(null);
        	        inframe3_123_124.setBorder(null);
        	        
        	        inframe3_123_124.setVisible(true);
        	        desktop.add(inframe3_123_124);
        	        try {
        	        	inframe3_123_124.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_123_124 = new InternalFrame_for_three_123_124s_2(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_123_124.getUI()).setNorthPane(null);
        	        inframe3_123_124.setBorder(null);
        	        
        	        inframe3_123_124.setVisible(true);
        	        desktop.add(inframe3_123_124);
        	        try {
        	        	inframe3_123_124.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_123_124 = new InternalFrame_for_three_123_124s_34(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_123_124.getUI()).setNorthPane(null);
        	        inframe3_123_124.setBorder(null);
        	        
        	        inframe3_123_124.setVisible(true);
        	        desktop.add(inframe3_123_124);
        	        try {
        	        	inframe3_123_124.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count2 = 1;
        			count++;
        		}
        		
        		// 3명이 3,4번자에 앉은 뒤 2번자리에 앉을 경우
        		if(count3==1 && count4==1 && count2!=1 && count==2) {
        			//desktop.remove(inframe2_12);
        			
        			inframe3_134_234 = new InternalFrame_for_three_134_234s_12(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_134_234.getUI()).setNorthPane(null);
        	        inframe3_134_234.setBorder(null);
        	        
        	        inframe3_134_234.setVisible(true);
        	        desktop.add(inframe3_134_234);
        	        try {
        	        	inframe3_134_234.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_134_234 = new InternalFrame_for_three_134_234s_3(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_134_234.getUI()).setNorthPane(null);
        	        inframe3_134_234.setBorder(null);
        	        
        	        inframe3_134_234.setVisible(true);
        	        desktop.add(inframe3_134_234);
        	        try {
        	        	inframe3_134_234.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_134_234 = new InternalFrame_for_three_134_234s_4(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_134_234.getUI()).setNorthPane(null);
        	        inframe3_134_234.setBorder(null);
        	        
        	        inframe3_134_234.setVisible(true);
        	        desktop.add(inframe3_134_234);
        	        try {
        	        	inframe3_134_234.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count2 = 1;
        			count++;
        		}
        		
        		// 4명의 사람이 1,3,4번자리에 앉은 뒤 2번자리에 앉는 경우
        		if(count1==1 && count3==1 && count4==1 && count2!=1 && count==3) {
        			//desktop.remove(inframe2_12);
        			
        			inframe4 = new InternalFrame_for_three_123_124s_1(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe4 = new InternalFrame_for_three_123_124s_2(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe4 = new InternalFrame_for_three_134_234s_3(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe4 = new InternalFrame_for_three_134_234s_4(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count2 = 1;
        			count++;
        		}
        	}
        });
        
        new3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// 1명이면서 3번자리에 앉는 경우
        		if(count3!=1 && count==0) {	
        			inframe1_34 = new InternalFrame_for_one_34(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe1_34.getUI()).setNorthPane(null);
        	        inframe1_34.setBorder(null);
        	        
        			inframe1_34.setVisible(true);
        			desktop.add(inframe1_34);
        			try {
        				inframe1_34.setSelected(true); 
        			} catch (PropertyVetoException pve) { }
        			
        			count3 = 1;
        			count++;
        		}
        		
        		// 2명이서 4번자리에 앉은 뒤 3번자리에 앉는 경우
        		if(count3!=1 && count4==1 && count==1) {
        			desktop.remove(inframe1_34);
        			
        			inframe2_34 = new InternalFrame_for_two_34s_3(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_34.getUI()).setNorthPane(null);
        	        inframe2_34.setBorder(null);
        	        
        	        inframe2_34.setVisible(true);
        	        desktop.add(inframe2_34);
        	        try {
        	        	inframe2_34.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe2_34 = new InternalFrame_for_two_34s_4(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_34.getUI()).setNorthPane(null);
        	        inframe2_34.setBorder(null);
        	        
        	        inframe2_34.setVisible(true);
        	        desktop.add(inframe2_34);
        	        try {
        	        	inframe2_34.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count3=1;
        	        count++;
        		}
        		
        		// 2명이서 1번자리에 앉은 뒤 3번자리에 앉는 경우
        		if((count1==1 || count2==1) && count3!=1 && count==1) {
        			desktop.remove(inframe1_12);
        			
        			inframe2_13_14_23_24 = new InternalFrame_for_two_13_14_23_24s_12(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_13_14_23_24.getUI()).setNorthPane(null);
        	        inframe2_13_14_23_24.setBorder(null);
        	        
        	        inframe2_13_14_23_24.setVisible(true);
        	        desktop.add(inframe2_13_14_23_24);
        	        try {
        	        	inframe2_13_14_23_24.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe2_13_14_23_24 = new InternalFrame_for_two_13_14_23_24s_34(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_13_14_23_24.getUI()).setNorthPane(null);
        	        inframe2_13_14_23_24.setBorder(null);
        	        
        	        inframe2_13_14_23_24.setVisible(true);
        	        desktop.add(inframe2_13_14_23_24);
        	        try {
        	        	inframe2_13_14_23_24.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        			
        			count3 = 1;
        			count++;
        		}
        		
        		// 3명이 1,2번자리에 앉은 뒤 3번자리에 앉을 경우
        		if(count1==1 && count2==1 && count3!=1 && count==2) {
        			desktop.remove(inframe2_12);
        			
        			inframe3_123_124 = new InternalFrame_for_three_123_124s_1(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_123_124.getUI()).setNorthPane(null);
        	        inframe3_123_124.setBorder(null);
        	        
        	        inframe3_123_124.setVisible(true);
        	        desktop.add(inframe3_123_124);
        	        try {
        	        	inframe3_123_124.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_123_124 = new InternalFrame_for_three_123_124s_2(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_123_124.getUI()).setNorthPane(null);
        	        inframe3_123_124.setBorder(null);
        	        
        	        inframe3_123_124.setVisible(true);
        	        desktop.add(inframe3_123_124);
        	        try {
        	        	inframe3_123_124.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_123_124 = new InternalFrame_for_three_123_124s_34(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_123_124.getUI()).setNorthPane(null);
        	        inframe3_123_124.setBorder(null);
        	        
        	        inframe3_123_124.setVisible(true);
        	        desktop.add(inframe3_123_124);
        	        try {
        	        	inframe3_123_124.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count3 = 1;
        			count++;
        		}
        		
        		// 3명이 1,4번자리 또는 2,4번 자리에 앉은 뒤 3번자리에 앉을 경우
        		if((count1==1 || count2==1) && count4==1 && count3!=1 && count==2) {
        			//desktop.remove(inframe2_12);
        			
        			inframe3_134_234 = new InternalFrame_for_three_134_234s_12(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_134_234.getUI()).setNorthPane(null);
        	        inframe3_134_234.setBorder(null);
        	        
        	        inframe3_134_234.setVisible(true);
        	        desktop.add(inframe3_134_234);
        	        try {
        	        	inframe3_134_234.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_134_234 = new InternalFrame_for_three_134_234s_3(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_134_234.getUI()).setNorthPane(null);
        	        inframe3_134_234.setBorder(null);
        	        
        	        inframe3_134_234.setVisible(true);
        	        desktop.add(inframe3_134_234);
        	        try {
        	        	inframe3_134_234.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_134_234 = new InternalFrame_for_three_134_234s_4(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_134_234.getUI()).setNorthPane(null);
        	        inframe3_134_234.setBorder(null);
        	        
        	        inframe3_134_234.setVisible(true);
        	        desktop.add(inframe3_134_234);
        	        try {
        	        	inframe3_134_234.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count3 = 1;
        			count++;
        		}
        		
        		// 4명의 사람이 2,1,4번자리에 앉은 뒤 3번자리에 앉는 경우
        		if(count2==1 && count1==1 && count4==1 && count3!=1 && count==3) {
        			//desktop.remove(inframe2_12);
        			
        			inframe4 = new InternalFrame_for_three_123_124s_1(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe4 = new InternalFrame_for_three_123_124s_2(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe4 = new InternalFrame_for_three_134_234s_3(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe4 = new InternalFrame_for_three_134_234s_4(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count3 = 1;
        			count++;
        		}
        	}
        });
        
        new4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// 1명이면서 4번자리에 앉는 경우
        		if(count4!=1 && count==0) {	
        			inframe1_34 = new InternalFrame_for_one_34(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe1_34.getUI()).setNorthPane(null);
        	        inframe1_34.setBorder(null);
        	        
        			inframe1_34.setVisible(true);
        			desktop.add(inframe1_34);
        			try {
        				inframe1_34.setSelected(true);
        			} catch (PropertyVetoException pve) { }
        			
        			count4 = 1;
        			count++;
        		}
        		
        		// 2명이서 3번자리에 앉은 뒤 4번자리에 앉는 경우
        		if(count3==1 && count4!=1 && count==1) {
        			desktop.remove(inframe1_34);
        			
        			inframe2_34 = new InternalFrame_for_two_34s_3(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_34.getUI()).setNorthPane(null);
        	        inframe2_34.setBorder(null);
        	        
        	        inframe2_34.setVisible(true);
        	        desktop.add(inframe2_34);
        	        try {
        	        	inframe2_34.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe2_34 = new InternalFrame_for_two_34s_4(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_34.getUI()).setNorthPane(null);
        	        inframe2_34.setBorder(null);
        	        
        	        inframe2_34.setVisible(true);
        	        desktop.add(inframe2_34);
        	        try {
        	        	inframe2_34.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count4=1;
        	        count++;
        		}
        		
        		// 2명이서 1번자리에 앉은 뒤 4번자리에 앉는 경우
        		if((count1==1 || count2==1) && count3!=1 && count==1) {
        			desktop.remove(inframe1_12);
        			
        			inframe2_13_14_23_24 = new InternalFrame_for_two_13_14_23_24s_12(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_13_14_23_24.getUI()).setNorthPane(null);
        	        inframe2_13_14_23_24.setBorder(null);
        	        
        	        inframe2_13_14_23_24.setVisible(true);
        	        desktop.add(inframe2_13_14_23_24);
        	        try {
        	        	inframe2_13_14_23_24.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe2_13_14_23_24 = new InternalFrame_for_two_13_14_23_24s_34(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe2_13_14_23_24.getUI()).setNorthPane(null);
        	        inframe2_13_14_23_24.setBorder(null);
        	        
        	        inframe2_13_14_23_24.setVisible(true);
        	        desktop.add(inframe2_13_14_23_24);
        	        try {
        	        	inframe2_13_14_23_24.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        			
        			count4 = 1;
        			count++;
        		}
        		
        		// 3명이 1,2번자리에 앉은 뒤 4번자리에 앉을 경우
        		if(count1==1 && count2==1 && count4!=1 && count==2) {
        			desktop.remove(inframe2_12);
        			
        			inframe3_123_124 = new InternalFrame_for_three_123_124s_1(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_123_124.getUI()).setNorthPane(null);
        	        inframe3_123_124.setBorder(null);
        	        
        	        inframe3_123_124.setVisible(true);
        	        desktop.add(inframe3_123_124);
        	        try {
        	        	inframe3_123_124.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_123_124 = new InternalFrame_for_three_123_124s_2(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_123_124.getUI()).setNorthPane(null);
        	        inframe3_123_124.setBorder(null);
        	        
        	        inframe3_123_124.setVisible(true);
        	        desktop.add(inframe3_123_124);
        	        try {
        	        	inframe3_123_124.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_123_124 = new InternalFrame_for_three_123_124s_34(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_123_124.getUI()).setNorthPane(null);
        	        inframe3_123_124.setBorder(null);
        	        
        	        inframe3_123_124.setVisible(true);
        	        desktop.add(inframe3_123_124);
        	        try {
        	        	inframe3_123_124.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count4 = 1;
        			count++;
        		}
        		
        		// 3명이 1,3번자리 또는 2,3번 자리에 앉은 뒤 4번자리에 앉을 경우
        		if((count1==1 || count2==1) && count3==1 && count4!=1 && count==2) {
        			//desktop.remove(inframe2_12);
        			
        			inframe3_134_234 = new InternalFrame_for_three_134_234s_12(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_134_234.getUI()).setNorthPane(null);
        	        inframe3_134_234.setBorder(null);
        	        
        	        inframe3_134_234.setVisible(true);
        	        desktop.add(inframe3_134_234);
        	        try {
        	        	inframe3_134_234.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_134_234 = new InternalFrame_for_three_134_234s_3(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_134_234.getUI()).setNorthPane(null);
        	        inframe3_134_234.setBorder(null);
        	        
        	        inframe3_134_234.setVisible(true);
        	        desktop.add(inframe3_134_234);
        	        try {
        	        	inframe3_134_234.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe3_134_234 = new InternalFrame_for_three_134_234s_4(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe3_134_234.getUI()).setNorthPane(null);
        	        inframe3_134_234.setBorder(null);
        	        
        	        inframe3_134_234.setVisible(true);
        	        desktop.add(inframe3_134_234);
        	        try {
        	        	inframe3_134_234.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count4 = 1;
        			count++;
        		}
        		
        		// 4명의 사람이 2,3,1번자리에 앉은 뒤 4번자리에 앉는 경우
        		if(count2==1 && count3==1 && count1==1 && count4!=1 && count==3) {
        			//desktop.remove(inframe2_12);
        			
        			inframe4 = new InternalFrame_for_three_123_124s_1(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe4 = new InternalFrame_for_three_123_124s_2(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe4 = new InternalFrame_for_three_134_234s_3(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        inframe4 = new InternalFrame_for_three_134_234s_4(frame.getSize());
        			
        			// title bar remove
        			putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        	        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        	        ((BasicInternalFrameUI)inframe4.getUI()).setNorthPane(null);
        	        inframe4.setBorder(null);
        	        
        	        inframe4.setVisible(true);
        	        desktop.add(inframe4);
        	        try {
        	        	inframe4.setSelected(true);
        	        } catch(PropertyVetoException pve) { }
        	        
        	        count4 = 1;
        			count++;
        		}
        	}
        });
        
        menu.add(new1);
        menu.add(new2);
        menu.add(new3);
        menu.add(new4);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        final JDesktopPane c = new MultiWindowGUI();
        c.setPreferredSize(new Dimension(433, 312)); // 데스크탑 크기
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("MultiWindow", c);
    }
    
}

// 데스크탑 안에 띄워지는 윈도우
// 한명이 1번 혹은 2번자리에 앉는 경우
class InternalFrame_for_one_12 extends JInternalFrame {

    static int count = 0;
    
    public InternalFrame_for_one_12(Dimension frameSize) {
        super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("1번 또는 2번 자리");
        
        // 글씨 뒤집기
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180도
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width, frameSize.height); // 윈도우 크기

        // Stagger windows
        setLocation(0, 0); // 윈도우가 뜨는 위치
    }
}

// 한명이 3번 혹은 4번자리에 앉는 경우
class InternalFrame_for_one_34 extends JInternalFrame{
	static int count = 0;
	public InternalFrame_for_one_34(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("1번 또는 2번 자리");
        
		add(la);
        
        setSize(frameSize.width, frameSize.height); // 윈도우 크기

        // Stagger windows
        setLocation(0, 0); // 윈도우가 뜨는 위치
	}
}

// 2명이 1, 2번자리에 앉을 때 1번자리의 화면
class InternalFrame_for_two_12s_1 extends JInternalFrame{
	static int count = 0;
	public InternalFrame_for_two_12s_1(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("1번 자리");
        
        // 글씨 뒤집기
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180도
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width/2, frameSize.height); // 윈도우 크기

        // Stagger windows
        setLocation(0, 0); // 윈도우가 뜨는 위치
	}
}

// 2명이 1, 2번 자리에 앉았을 때 2번자리의 화면
class InternalFrame_for_two_12s_2 extends JInternalFrame{
	static int count = 0;
	public InternalFrame_for_two_12s_2(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("2번 자리");
        
        // 글씨 뒤집기
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180도
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width/2, frameSize.height); // 윈도우 크기

        // Stagger windows
        setLocation(frameSize.width/2, 0); // 윈도우가 뜨는 위치
	}
}

//2명이 3, 4번자리에 앉을 때 3번자리의 화면
class InternalFrame_for_two_34s_3 extends JInternalFrame{
	static int count = 0;
	public InternalFrame_for_two_34s_3(Dimension frameSize) {
		super("Window #" + (count++),
             true, //resizable
             true, //closable
             true, //maximizable
             true);//iconifiable
		setLayout(new BorderLayout());
		JLabel la = new JLabel("3번 자리");
     
		add(la);
     
		setSize(frameSize.width/2, frameSize.height); // 윈도우 크기

		// Stagger windows
		setLocation(0, 0); // 윈도우가 뜨는 위치
	}
}

//2명이 3, 4번 자리에 앉았을 때 4번자리의 화면
class InternalFrame_for_two_34s_4 extends JInternalFrame{
	static int count = 0;
	public InternalFrame_for_two_34s_4(Dimension frameSize) {
		super("Window #" + (count++),
             true, //resizable
             true, //closable
             true, //maximizable
             true);//iconifiable
		setLayout(new BorderLayout());
		JLabel la = new JLabel("4번 자리");
     
		add(la);
     
		setSize(frameSize.width/2, frameSize.height); // 윈도우 크기

		// Stagger windows
		setLocation(frameSize.width/2, 0); // 윈도우가 뜨는 위치
	}
}

// 2명중 1명이 1번 또는 2번자리에 앉은 경우 (나머지 1명은 3번 또는 4번자리에 앉음)
class InternalFrame_for_two_13_14_23_24s_12 extends JInternalFrame{
	static int count = 0;
	public InternalFrame_for_two_13_14_23_24s_12(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("1번 또는 2번 자리");
        
        // 글씨 뒤집기
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180도
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width, frameSize.height/2); // 윈도우 크기

        // Stagger windows
        setLocation(0, 0); // 윈도우가 뜨는 위치
	}
}

// 2명중 1명이 3번 또는 4번자리에 앉은 경우(나머지 1명은 1번 또는 2번자리에 앉음)
class InternalFrame_for_two_13_14_23_24s_34 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_two_13_14_23_24s_34(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("3번또는 4번 자리");
        
     // 글씨 뒤집기
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180도
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width, frameSize.height/2); // 윈도우 크기

        // Stagger windows
        setLocation(0, frameSize.height/2); // 윈도우가 뜨는 위치
	}
}

// 3명이 1,2번 자리에 앉은 뒤 3번 또는 4번자리에 앉을 때 1번화면
// 4명이 1,2,3,4번 자리에 앉을 경우 1번 화면
class InternalFrame_for_three_123_124s_1 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_three_123_124s_1(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("1번 자리");
        
        // 글씨 뒤집기
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180도
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width/2, frameSize.height/2); // 윈도우 크기

        // Stagger windows
        setLocation(0, 0); // 윈도우가 뜨는 위치
	}
}

// 3명이 1,2번 자리에 앉은 뒤 3번 또는 4번자리에 앉을 때 2번화면
// 4명이 1,2,3,4번 자리에 앉을 경우 2번화면
class InternalFrame_for_three_123_124s_2 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_three_123_124s_2(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("2번 자리");
        
        // 글씨 뒤집기
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180도
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width/2, frameSize.height/2); // 윈도우 크기

        // Stagger windows
        setLocation(frameSize.width/2, 0); // 윈도우가 뜨는 위치
	}
}

// 3명이 1,2번자리에 앉은 뒤 3번 또는 4번자리에 앉을 때 3번 또는 4번 화면
class InternalFrame_for_three_123_124s_34 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_three_123_124s_34(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("3번 또는 4번 자리");
        
		add(la);
        
        setSize(frameSize.width, frameSize.height/2); // 윈도우 크기

        // Stagger windows
        setLocation(0, frameSize.height/2); // 윈도우가 뜨는 위치
	}
}

// 3명이 1,3번자리 또는 2,3번 자리에 앉은 뒤 4번 자리에 앉을 경우 1번 또는 2번 화면
class InternalFrame_for_three_134_234s_12 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_three_134_234s_12(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("1번 또는 2번 자리");
        
        // 글씨 뒤집기
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180도
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width, frameSize.height/2); // 윈도우 크기

        // Stagger windows
        setLocation(0, 0); // 윈도우가 뜨는 위치
	}
}

// 3명이 1,3번자리 또는 2,3번 자리에 앉은 뒤 4번 자리에 앉을 경우 3번 화면
// 4명이 1,2,3,4번 자리에 앉을 경우 3번화면
class InternalFrame_for_three_134_234s_3 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_three_134_234s_3(Dimension frameSize) {
		super("Window #" + (count++),
             true, //resizable
             true, //closable
             true, //maximizable
             true);//iconifiable
		setLayout(new BorderLayout());
		JLabel la = new JLabel("3번 자리");
     
		add(la);
     
		setSize(frameSize.width/2, frameSize.height/2); // 윈도우 크기

		// Stagger windows
		setLocation(0, frameSize.height/2); // 윈도우가 뜨는 위치
	}
}

// 3명이 1,3번자리 또는 2,3번 자리에 앉은 뒤 4번 자리에 앉을 경우 4번 화면
// 4명이 1,2,3,4번 자리에 앉을 경우 4번화면
class InternalFrame_for_three_134_234s_4 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_three_134_234s_4(Dimension frameSize) {
		super("Window #" + (count++),
           true, //resizable
           true, //closable
           true, //maximizable
           true);//iconifiable
		setLayout(new BorderLayout());
		JLabel la = new JLabel("4번 자리");
   
		add(la);
   
		setSize(frameSize.width/2, frameSize.height/2); // 윈도우 크기

		// Stagger windows
		setLocation(frameSize.width/2, frameSize.height/2); // 윈도우가 뜨는 위치
	}
}


