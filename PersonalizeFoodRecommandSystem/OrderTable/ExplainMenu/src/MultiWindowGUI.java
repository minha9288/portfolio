

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
	JInternalFrame inframe1_12, inframe1_34; // 1���� ���� ���
	JInternalFrame inframe2_12, inframe2_13_14_23_24, inframe2_34; // 2���� �ɾ��� ���
	JInternalFrame inframe3_123_124, inframe3_134_234; // 3���� �ɾ��� ���
	JInternalFrame inframe4;

    public MultiWindowGUI() {
    	// ����ũž �ȿ��� �巡���� �� �ְ� ����
    	// �׷��� ��� �巡�״� ��...
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
        		// 1���̸鼭 1���ڸ��� �ɴ� ���
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
        		
        		// 2���̼� 2���ڸ��� ���� �� 1���ڸ��� �ɴ� ���
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
        		
        		// 2���̼� 3�� �Ǵ� 4���ڸ��� ���� �� 1���ڸ��� �ɴ� ���
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
        		
        		// 3���� 2,3���ڸ� �Ǵ� 2,4�� �ڸ��� ���� �� 1���ڸ��� ���� ���
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
        		
        		// 3���� 3,4���ڿ� ���� �� 1���ڸ��� ���� ���
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
        		
        		// 4���� ����� 2,3,4���ڸ��� ���� �� 1���ڸ��� �ɴ� ���
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
        		// 1���̸鼭 2���ڸ��� �ɴ� ���
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
        		
        		// 2���̼� 1���ڸ��� ���� �� 2���ڸ��� �ɴ� ���
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
        		
        		// 2���̼� 3�� �Ǵ� 4���ڸ��� ���� �� 2���ڸ��� �ɴ� ���
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
        		
        		// 3���� 1,3���ڸ� �Ǵ� 1,4�� �ڸ��� ���� �� 2���ڸ��� ���� ���
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
        		
        		// 3���� 3,4���ڿ� ���� �� 2���ڸ��� ���� ���
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
        		
        		// 4���� ����� 1,3,4���ڸ��� ���� �� 2���ڸ��� �ɴ� ���
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
        		// 1���̸鼭 3���ڸ��� �ɴ� ���
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
        		
        		// 2���̼� 4���ڸ��� ���� �� 3���ڸ��� �ɴ� ���
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
        		
        		// 2���̼� 1���ڸ��� ���� �� 3���ڸ��� �ɴ� ���
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
        		
        		// 3���� 1,2���ڸ��� ���� �� 3���ڸ��� ���� ���
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
        		
        		// 3���� 1,4���ڸ� �Ǵ� 2,4�� �ڸ��� ���� �� 3���ڸ��� ���� ���
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
        		
        		// 4���� ����� 2,1,4���ڸ��� ���� �� 3���ڸ��� �ɴ� ���
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
        		// 1���̸鼭 4���ڸ��� �ɴ� ���
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
        		
        		// 2���̼� 3���ڸ��� ���� �� 4���ڸ��� �ɴ� ���
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
        		
        		// 2���̼� 1���ڸ��� ���� �� 4���ڸ��� �ɴ� ���
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
        		
        		// 3���� 1,2���ڸ��� ���� �� 4���ڸ��� ���� ���
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
        		
        		// 3���� 1,3���ڸ� �Ǵ� 2,3�� �ڸ��� ���� �� 4���ڸ��� ���� ���
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
        		
        		// 4���� ����� 2,3,1���ڸ��� ���� �� 4���ڸ��� �ɴ� ���
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
        c.setPreferredSize(new Dimension(433, 312)); // ����ũž ũ��
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("MultiWindow", c);
    }
    
}

// ����ũž �ȿ� ������� ������
// �Ѹ��� 1�� Ȥ�� 2���ڸ��� �ɴ� ���
class InternalFrame_for_one_12 extends JInternalFrame {

    static int count = 0;
    
    public InternalFrame_for_one_12(Dimension frameSize) {
        super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("1�� �Ǵ� 2�� �ڸ�");
        
        // �۾� ������
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180��
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width, frameSize.height); // ������ ũ��

        // Stagger windows
        setLocation(0, 0); // �����찡 �ߴ� ��ġ
    }
}

// �Ѹ��� 3�� Ȥ�� 4���ڸ��� �ɴ� ���
class InternalFrame_for_one_34 extends JInternalFrame{
	static int count = 0;
	public InternalFrame_for_one_34(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("1�� �Ǵ� 2�� �ڸ�");
        
		add(la);
        
        setSize(frameSize.width, frameSize.height); // ������ ũ��

        // Stagger windows
        setLocation(0, 0); // �����찡 �ߴ� ��ġ
	}
}

// 2���� 1, 2���ڸ��� ���� �� 1���ڸ��� ȭ��
class InternalFrame_for_two_12s_1 extends JInternalFrame{
	static int count = 0;
	public InternalFrame_for_two_12s_1(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("1�� �ڸ�");
        
        // �۾� ������
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180��
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width/2, frameSize.height); // ������ ũ��

        // Stagger windows
        setLocation(0, 0); // �����찡 �ߴ� ��ġ
	}
}

// 2���� 1, 2�� �ڸ��� �ɾ��� �� 2���ڸ��� ȭ��
class InternalFrame_for_two_12s_2 extends JInternalFrame{
	static int count = 0;
	public InternalFrame_for_two_12s_2(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("2�� �ڸ�");
        
        // �۾� ������
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180��
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width/2, frameSize.height); // ������ ũ��

        // Stagger windows
        setLocation(frameSize.width/2, 0); // �����찡 �ߴ� ��ġ
	}
}

//2���� 3, 4���ڸ��� ���� �� 3���ڸ��� ȭ��
class InternalFrame_for_two_34s_3 extends JInternalFrame{
	static int count = 0;
	public InternalFrame_for_two_34s_3(Dimension frameSize) {
		super("Window #" + (count++),
             true, //resizable
             true, //closable
             true, //maximizable
             true);//iconifiable
		setLayout(new BorderLayout());
		JLabel la = new JLabel("3�� �ڸ�");
     
		add(la);
     
		setSize(frameSize.width/2, frameSize.height); // ������ ũ��

		// Stagger windows
		setLocation(0, 0); // �����찡 �ߴ� ��ġ
	}
}

//2���� 3, 4�� �ڸ��� �ɾ��� �� 4���ڸ��� ȭ��
class InternalFrame_for_two_34s_4 extends JInternalFrame{
	static int count = 0;
	public InternalFrame_for_two_34s_4(Dimension frameSize) {
		super("Window #" + (count++),
             true, //resizable
             true, //closable
             true, //maximizable
             true);//iconifiable
		setLayout(new BorderLayout());
		JLabel la = new JLabel("4�� �ڸ�");
     
		add(la);
     
		setSize(frameSize.width/2, frameSize.height); // ������ ũ��

		// Stagger windows
		setLocation(frameSize.width/2, 0); // �����찡 �ߴ� ��ġ
	}
}

// 2���� 1���� 1�� �Ǵ� 2���ڸ��� ���� ��� (������ 1���� 3�� �Ǵ� 4���ڸ��� ����)
class InternalFrame_for_two_13_14_23_24s_12 extends JInternalFrame{
	static int count = 0;
	public InternalFrame_for_two_13_14_23_24s_12(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("1�� �Ǵ� 2�� �ڸ�");
        
        // �۾� ������
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180��
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width, frameSize.height/2); // ������ ũ��

        // Stagger windows
        setLocation(0, 0); // �����찡 �ߴ� ��ġ
	}
}

// 2���� 1���� 3�� �Ǵ� 4���ڸ��� ���� ���(������ 1���� 1�� �Ǵ� 2���ڸ��� ����)
class InternalFrame_for_two_13_14_23_24s_34 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_two_13_14_23_24s_34(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("3���Ǵ� 4�� �ڸ�");
        
     // �۾� ������
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180��
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width, frameSize.height/2); // ������ ũ��

        // Stagger windows
        setLocation(0, frameSize.height/2); // �����찡 �ߴ� ��ġ
	}
}

// 3���� 1,2�� �ڸ��� ���� �� 3�� �Ǵ� 4���ڸ��� ���� �� 1��ȭ��
// 4���� 1,2,3,4�� �ڸ��� ���� ��� 1�� ȭ��
class InternalFrame_for_three_123_124s_1 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_three_123_124s_1(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("1�� �ڸ�");
        
        // �۾� ������
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180��
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width/2, frameSize.height/2); // ������ ũ��

        // Stagger windows
        setLocation(0, 0); // �����찡 �ߴ� ��ġ
	}
}

// 3���� 1,2�� �ڸ��� ���� �� 3�� �Ǵ� 4���ڸ��� ���� �� 2��ȭ��
// 4���� 1,2,3,4�� �ڸ��� ���� ��� 2��ȭ��
class InternalFrame_for_three_123_124s_2 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_three_123_124s_2(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("2�� �ڸ�");
        
        // �۾� ������
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180��
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width/2, frameSize.height/2); // ������ ũ��

        // Stagger windows
        setLocation(frameSize.width/2, 0); // �����찡 �ߴ� ��ġ
	}
}

// 3���� 1,2���ڸ��� ���� �� 3�� �Ǵ� 4���ڸ��� ���� �� 3�� �Ǵ� 4�� ȭ��
class InternalFrame_for_three_123_124s_34 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_three_123_124s_34(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("3�� �Ǵ� 4�� �ڸ�");
        
		add(la);
        
        setSize(frameSize.width, frameSize.height/2); // ������ ũ��

        // Stagger windows
        setLocation(0, frameSize.height/2); // �����찡 �ߴ� ��ġ
	}
}

// 3���� 1,3���ڸ� �Ǵ� 2,3�� �ڸ��� ���� �� 4�� �ڸ��� ���� ��� 1�� �Ǵ� 2�� ȭ��
class InternalFrame_for_three_134_234s_12 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_three_134_234s_12(Dimension frameSize) {
		super("Window #" + (count++),
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        setLayout(new BorderLayout());
        JLabel la = new JLabel("1�� �Ǵ� 2�� �ڸ�");
        
        // �۾� ������
        java.awt.geom.AffineTransform rotate;
		la.setBorder(new javax.swing.border.EmptyBorder(40, 100, 40, 4)); // Creates an empty border with the specified insets. (top, left, bottom, right)
		double theta = (1.0) * Math.PI; // 180��
		rotate = java.awt.geom.AffineTransform.getRotateInstance(theta);
		Font rotatedFont;
		rotatedFont=la.getFont().deriveFont(rotate);
		la.setFont(rotatedFont);
		add(la);
        
        setSize(frameSize.width, frameSize.height/2); // ������ ũ��

        // Stagger windows
        setLocation(0, 0); // �����찡 �ߴ� ��ġ
	}
}

// 3���� 1,3���ڸ� �Ǵ� 2,3�� �ڸ��� ���� �� 4�� �ڸ��� ���� ��� 3�� ȭ��
// 4���� 1,2,3,4�� �ڸ��� ���� ��� 3��ȭ��
class InternalFrame_for_three_134_234s_3 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_three_134_234s_3(Dimension frameSize) {
		super("Window #" + (count++),
             true, //resizable
             true, //closable
             true, //maximizable
             true);//iconifiable
		setLayout(new BorderLayout());
		JLabel la = new JLabel("3�� �ڸ�");
     
		add(la);
     
		setSize(frameSize.width/2, frameSize.height/2); // ������ ũ��

		// Stagger windows
		setLocation(0, frameSize.height/2); // �����찡 �ߴ� ��ġ
	}
}

// 3���� 1,3���ڸ� �Ǵ� 2,3�� �ڸ��� ���� �� 4�� �ڸ��� ���� ��� 4�� ȭ��
// 4���� 1,2,3,4�� �ڸ��� ���� ��� 4��ȭ��
class InternalFrame_for_three_134_234s_4 extends JInternalFrame{
	static int count=0;
	public InternalFrame_for_three_134_234s_4(Dimension frameSize) {
		super("Window #" + (count++),
           true, //resizable
           true, //closable
           true, //maximizable
           true);//iconifiable
		setLayout(new BorderLayout());
		JLabel la = new JLabel("4�� �ڸ�");
   
		add(la);
   
		setSize(frameSize.width/2, frameSize.height/2); // ������ ũ��

		// Stagger windows
		setLocation(frameSize.width/2, frameSize.height/2); // �����찡 �ߴ� ��ġ
	}
}


