
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;
import java.awt.font.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class MJB extends JFrame implements ActionListener{
	
	JLabel lblCom, lblUser, lblVS, lblResult, lblScore;
	JButton btnmook, btnjji, btnbba, reset, exit;
	int win=0, draw=0, lose=0;
	String exitresult="0½Â0¹«0ÆÐ";
	
	ImageIcon Icon[] = { new ImageIcon("images/mook.jpg"),
			new ImageIcon("images/jji.jpg"), new ImageIcon("images/bba.jpg"), new ImageIcon("images/first.jpg")};
	ImageIcon smallIcon[] = { new ImageIcon("images/small_mook.png"),
			new ImageIcon("images/small_jji.png"), new ImageIcon("images/small_bba.png")};
	
	public MJB(){
		setBounds(100,100,800,800);
		setTitle("°¡À§¹ÙÀ§º¸ °ÔÀÓ");
		setLayout(null);
		
		Container ct = getContentPane();
		ct.setBackground(Color.white);
		
		lblScore = new JLabel("0½Â  0¹« 0ÆÐ");
		lblScore.setFont(new Font("±¼¸²",Font.BOLD,20));
		lblScore.setBounds(360,50,500,100);
		
		lblCom = new JLabel(new ImageIcon("images/first.jpg"));
		lblCom.setBounds(50,150,300,380);
		
		lblVS = new JLabel("VS");
		lblVS.setFont(new Font("±¼¸²",Font.BOLD,20));
		lblVS.setBounds(400,200,50,300);
		
		lblUser = new JLabel(new ImageIcon("images/first.jpg"));
		lblUser.setBounds(450,150,300,380);
		
		lblResult = new JLabel();
		lblResult.setFont(new Font("±¼¸²",Font.BOLD,20));
		lblResult.setBounds(380,0,200,300);
		
		btnmook = new JButton(smallIcon[0]);
		btnmook.setBounds(50,600,150,150);
		btnjji = new JButton(smallIcon[1]);
		btnjji.setBounds(325,600,150,150);
		btnbba = new JButton(smallIcon[2]);
		btnbba.setBounds(600,600,150,150);
		
		btnmook.addActionListener(this);
		btnjji.addActionListener(this);
		btnbba.addActionListener(this);
		
		reset = new JButton("Àç½ÃÀÛ");
		reset.setBounds(400,0,200,50);
		reset.addActionListener(this);
		
		exit = new JButton("±×¸¸ÇÏ±â");
		exit.setBounds(600,0,200,50);
		exit.addActionListener(this);
		
		
		ct.add(lblCom);
		ct.add(lblUser);
		ct.add(lblVS);
		ct.add(btnmook);
		ct.add(btnjji);
		ct.add(btnbba);
		ct.add(lblResult);
		ct.add(lblScore);
		ct.add(reset);
		ct.add(exit);
		
		setVisible(true);
	}
	
	public static void main(String[] args){
		new MJB();
	}
	
	public void chkResult(int user){

		int com = (int)((Math.random()*3))+1; 
		lblCom.setIcon(Icon[com-1]);
		if(com==user){
			draw++;
			lblResult.setText("ºñ°å´Ù");
		}else if((com==1&&user==3)||(com==2&&user==1)||(com==3&&user==2)){
			win++;
			lblResult.setText("ÀÌ°å´Ù");
		}else if((com==1&&user==2)||(com==2&&user==3)||(com==3&&user==1)){
			lose++;
			lblResult.setText("Á³´Ù");
		}else{
			lblResult.setText("¹¹³Ä");
		}
		lblScore.setText(win+"½Â "+draw+"¹« "+lose+"ÆÐ");
		exitresult = win+"½Â "+draw+"¹« "+lose+"ÆÐ";
	}
	
	public void actionPerformed(ActionEvent e){
		Object ob = e.getSource();
		
		if(ob==btnmook){
			lblUser.setIcon(Icon[0]);
			chkResult(1);
		}else if(ob==btnjji){
			lblUser.setIcon(Icon[1]);
			chkResult(2);
		}else if(ob==btnbba){
			lblUser.setIcon(Icon[2]);
			chkResult(3);
		}else if(ob==reset){
			draw=0;
			win=0;
			lose=0;
			lblCom.setIcon(Icon[3]);
			lblUser.setIcon(Icon[3]);
			lblResult.setText("");
			lblScore.setText(win+"½Â "+draw+"¹« "+lose+"ÆÐ");
			
		}else if(ob==exit){
			JOptionPane.showMessageDialog(null, "Áö±Ý±îÁöÀÇ °á°ú´Â"+exitresult, "ÃÑ °á°ú", JOptionPane.CLOSED_OPTION ); 
			draw=0;
			win=0;
			lose=0;
			lblCom.setIcon(Icon[3]);
			lblUser.setIcon(Icon[3]);
			lblResult.setText("");
			lblScore.setText(win+"½Â "+draw+"¹« "+lose+"ÆÐ");
		}
	}
	

}
