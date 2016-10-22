import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.swing.JOptionPane;


public class OralCare_K {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
		}
		Menu tf = new Menu();
	}
}
 

class Menu extends JFrame {
	JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP,
			JTabbedPane.SCROLL_TAB_LAYOUT);
	GridBagLayout gbl;
	GridBagConstraints gbc;
	JPanel jp;
	JPanel pS;
	// �������� Ŭ����
	String chart_num;
	String patientName;
	String gender;

	/* ȯ������ */
	JTextField CNF; // ��Ʈ��ȣ
	JTextField NF; // �̸�
	JTextField YF; // �⵵
	JTextField MF; // ��
	JTextField DF; // ��
	JTextField T1; // ��ȭ��ȣ ��
	JTextField T2; // ��ȭ��ȣ �߰�
	JTextField T3; // ��ȭ��ȣ ��
	JTextArea PA; // ���� ����
	ButtonGroup group; // ����
	JRadioButton rbMan;
	JRadioButton rbWoman;
	JPanel infoPanel;
	JButton btnInsert; // Ȯ�ι�ư
	JButton btnSurvey; // ������
	ButtonGroup group1, group2, group3, group4, group5, group6, group7, group8,
			group9, group10;

	/* �������� */
	JButton uploadBtn;
	JTextField pathCheck;
	JLabel p1, p2, p3;
	JLabel teethP1, teethP2, teethP3;
	JLabel plaqueP1, plaqueP2, plaqueP3;
	JComboBox info_plaque;
	

	/* ġ�� ���� ���� ���� */
	int redNum;
	int blueNum;
	int grayNum;
	int grayNum2;
	int blackNum;

	// ���� ����
	String who = "";

	JPanel tap4, tap6;
	ScrollPane tap1, tap2, tap3, tap5;
	boolean tapCheck = true;

	int tapNum;
	int sendAge = 0;
	JLabel checkImg;

	PatientInfoBean_K bean = new PatientInfoBean_K();

	float[] score = {};

	JPanel resultPanel, careSchedulePanel;
	ScrollPane scroll, scroll_result;
	JPanel addInfoPanel;
	AddCheckPanel_K addcheckPanel;

	String filePath = "";

	JTextField info_babyTooth, info_permanentTooth,
			info_losePermanentTooth_front, info_losePermanentTooth_back,
			info_implant, info_dentures, info_leaving, info_treatment,
			info_sulcus;

	// ���� �� �ϳ��� ������ �ϱ�
	boolean survey_check = true;

	URL teethcheckurl;
	ImageIcon filename1;
	
	CalendarView_K calView;
	
	//���帮��Ʈ
	ArrayList<PatientInfoBean_K> read_list;
	File file = null;

	public Menu() {

		super("New Dental Care System");
		JPanel maintab = new JPanel();
		maintab = SearchTab();
		tap1 = PatientInfo();
		tap2 = TeethPicture();
		tap3 = X_ray();
		tap4 = Plaque();
		tap5 = Result();
		tap6 = CareSchedule();

		jtp.addTab("����", maintab);
		jtp.addTab("ȸ������/����", tap1);
		jtp.addTab("�����Կ�", tap2);
		jtp.addTab("X-ray", tap3);
		jtp.addTab("ġ�鼼�ո�", tap4);
		jtp.addTab("���", tap5);
		jtp.addTab("����/����", tap6);

		// amyhee
		/*
		 * jtp.addChangeListener(new ChangeListener() { public void
		 * stateChanged(ChangeEvent arg0) { // TODO Auto-generated method stub
		 * tapNum = jtp.getSelectedIndex(); if (tapNum == 2 && tapCheck ==
		 * false) { sendAge = AgeCount(YF.getText()); if (sendAge >= 0 &&
		 * sendAge < 6) { filename = "images/agePart1.png"; try { image =
		 * ImageIO.read(new File(filename)); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } tapCheck = true;
		 * teethCheckPanel.add(new JLabel(new ImageIcon(image)));
		 * teethCheckPanel.add(check);
		 * 
		 * } else if ((sendAge >= 6 && sendAge < 12) && tapCheck == false) {
		 * filename = "images/agePart2.png"; try { image = ImageIO.read(new
		 * File(filename)); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } tapCheck = true;
		 * teethCheckPanel.add(new JLabel(new ImageIcon(image)));
		 * teethCheckPanel.add(check); } else if ((sendAge >= 12 && sendAge <
		 * 20) && tapCheck == false) { filename = "images/agePart3.png"; try {
		 * image = ImageIO.read(new File(filename)); } catch (IOException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); } tapCheck =
		 * true; teethCheckPanel.add(new JLabel(new ImageIcon(image))); // ����Ѵ�.
		 * teethCheckPanel.add(check); } else if (sendAge >= 20 && tapCheck ==
		 * false) { filename = "images/agePart4.png"; try { image =
		 * ImageIO.read(new File(filename)); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } tapCheck = true;
		 * teethCheckPanel.add(new JLabel(new ImageIcon(image)));
		 * teethCheckPanel.add(check); } else if (sendAge <= 0) {
		 * 
		 * } } } });
		 */

		jtp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				tapNum = jtp.getSelectedIndex();
				if (tapNum == 2 && tapCheck == true && sendAge != 0) {
					if ((sendAge >= 0 && sendAge < 6) && tapCheck == true) {
						who = "����";
						teethcheckurl = getClass().getClassLoader()
								.getResource("agePart1.png");
						try {
							image = ImageIO.read(teethcheckurl);
						} catch (IOException e) {
							// TODO �ڵ� ������ catch ����
							e.printStackTrace();
						}
						filename1 = new ImageIcon(image);
						tapCheck = false;
						checkImg.setIcon(filename1);
						info_babyTooth.setText("20");

					} else if ((sendAge >= 6 && sendAge < 12)
							&& tapCheck == true) {
						who = "�Ƶ�";
						teethcheckurl = getClass().getClassLoader()
								.getResource("agePart2.png");
						try {
							image = ImageIO.read(teethcheckurl);
						} catch (IOException e) {
							// TODO �ڵ� ������ catch ����
							e.printStackTrace();
						}
						filename1 = new ImageIcon(image);
						tapCheck = false;
						checkImg.setIcon(filename1);
						info_babyTooth.setText("20");
						info_permanentTooth.setText("24");

					} else if ((sendAge >= 12 && sendAge < 20)
							&& tapCheck == true) {
						who = "û�ҳ�";
						teethcheckurl = getClass().getClassLoader()
								.getResource("agePart3.png");
						try {
							image = ImageIO.read(teethcheckurl);
						} catch (IOException e) {
							// TODO �ڵ� ������ catch ����
							e.printStackTrace();
						}
						filename1 = new ImageIcon(image);
						tapCheck = false;
						checkImg.setIcon(filename1);
						info_permanentTooth.setText("28");

					} else if (sendAge >= 20 && tapCheck == true) {
						who = "û��";
						teethcheckurl = getClass().getClassLoader()
								.getResource("agePart4.png");
						try {
							image = ImageIO.read(teethcheckurl);
						} catch (IOException e) {
							// TODO �ڵ� ������ catch ����
							e.printStackTrace();
						}
						filename1 = new ImageIcon(image);
						tapCheck = false;
						checkImg.setIcon(filename1);
						info_permanentTooth.setText("28");
					} else if (sendAge <= 0) {

					}
				}
			}
		});

		this.add(jtp);

		this.setSize(1200, 700);
		this.setVisible(true);

	}

	// amyhee
	private Point keyChar;
	private int x, y;
	String filename;
	BufferedImage image = null;
	// amyhee
	JPanel teethCheckPanel;
	JPanel check;
	Color color = Color.red;

	class MyMouseListener implements MouseListener {
		public void mousePressed(MouseEvent me) {
			x = me.getX() - 10;
			y = me.getY() - 20;
			keyChar = new Point(x, y);
			floodFill(image, keyChar, Color.white, color);
			CountCheck(keyChar, color);
			teethCheckPanel.repaint();

		}

		public void mouseClicked(MouseEvent me) {
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	/* ���� ���� �������� boolean ���� �̤� */
	boolean red1 = true;
	boolean red2 = true;
	boolean red3 = true;
	boolean red4 = true;
	boolean red5 = true;
	boolean red6 = true;
	boolean red7 = true;
	boolean red8 = true;
	boolean red9 = true;
	boolean red10 = true;
	boolean red11 = true;
	boolean red12 = true;
	boolean red13 = true;
	boolean red14 = true;
	boolean red15 = true;
	boolean red16 = true;
	boolean red17 = true;
	boolean red18 = true;
	boolean red19 = true;
	boolean red20 = true;
	boolean red21 = true;
	boolean red22 = true;
	boolean red23 = true;
	boolean red24 = true;
	boolean red25 = true;
	boolean red26 = true;
	boolean red27 = true;
	boolean red28 = true;
	boolean red29 = true;
	boolean red30 = true;
	boolean red31 = true;
	boolean red32 = true;
	boolean red33 = true;
	boolean red34 = true;
	boolean red35 = true;
	boolean red36 = true;
	boolean red37 = true;
	boolean red38 = true;
	boolean red39 = true;
	boolean red40 = true;
	boolean red41 = true;
	boolean red42 = true;
	boolean red43 = true;
	boolean red44 = true;

	boolean blue1 = true;
	boolean blue2 = true;
	boolean blue3 = true;
	boolean blue4 = true;
	boolean blue5 = true;
	boolean blue6 = true;
	boolean blue7 = true;
	boolean blue8 = true;
	boolean blue9 = true;
	boolean blue10 = true;
	boolean blue11 = true;
	boolean blue12 = true;
	boolean blue13 = true;
	boolean blue14 = true;
	boolean blue15 = true;
	boolean blue16 = true;
	boolean blue17 = true;
	boolean blue18 = true;
	boolean blue19 = true;
	boolean blue20 = true;
	boolean blue21 = true;
	boolean blue22 = true;
	boolean blue23 = true;
	boolean blue24 = true;
	boolean blue25 = true;
	boolean blue26 = true;
	boolean blue27 = true;
	boolean blue28 = true;
	boolean blue29 = true;
	boolean blue30 = true;
	boolean blue31 = true;
	boolean blue32 = true;
	boolean blue33 = true;
	boolean blue34 = true;
	boolean blue35 = true;
	boolean blue36 = true;
	boolean blue37 = true;
	boolean blue38 = true;
	boolean blue39 = true;
	boolean blue40 = true;
	boolean blue41 = true;
	boolean blue42 = true;
	boolean blue43 = true;
	boolean blue44 = true;

	boolean gray1 = true;
	boolean gray2 = true;
	boolean gray3 = true;
	boolean gray4 = true;
	boolean gray5 = true;
	boolean gray6 = true;
	boolean gray7 = true;
	boolean gray8 = true;
	boolean gray9 = true;
	boolean gray10 = true;
	boolean gray11 = true;
	boolean gray12 = true;
	boolean gray13 = true;
	boolean gray14 = true;
	boolean gray15 = true;
	boolean gray16 = true;
	boolean gray17 = true;
	boolean gray18 = true;
	boolean gray19 = true;
	boolean gray20 = true;
	boolean gray21 = true;
	boolean gray22 = true;
	boolean gray23 = true;
	boolean gray24 = true;
	boolean gray25 = true;
	boolean gray26 = true;
	boolean gray27 = true;
	boolean gray28 = true;
	boolean gray29 = true;
	boolean gray30 = true;
	boolean gray31 = true;
	boolean gray32 = true;
	boolean gray33 = true;
	boolean gray34 = true;
	boolean gray35 = true;
	boolean gray36 = true;
	boolean gray37 = true;
	boolean gray38 = true;
	boolean gray39 = true;
	boolean gray40 = true;
	boolean gray41 = true;
	boolean gray42 = true;
	boolean gray43 = true;
	boolean gray44 = true;

	boolean black1 = true;
	boolean black2 = true;
	boolean black3 = true;
	boolean black4 = true;
	boolean black5 = true;
	boolean black6 = true;
	boolean black7 = true;
	boolean black8 = true;
	boolean black9 = true;
	boolean black10 = true;
	boolean black11 = true;
	boolean black12 = true;
	boolean black13 = true;
	boolean black14 = true;
	boolean black15 = true;
	boolean black16 = true;
	boolean black17 = true;
	boolean black18 = true;
	boolean black19 = true;
	boolean black20 = true;
	boolean black21 = true;
	boolean black22 = true;
	boolean black23 = true;
	boolean black24 = true;
	boolean black25 = true;
	boolean black26 = true;
	boolean black27 = true;
	boolean black28 = true;
	boolean black29 = true;
	boolean black30 = true;
	boolean black31 = true;
	boolean black32 = true;
	boolean black33 = true;
	boolean black34 = true;
	boolean black35 = true;
	boolean black36 = true;
	boolean black37 = true;
	boolean black38 = true;
	boolean black39 = true;
	boolean black40 = true;
	boolean black41 = true;
	boolean black42 = true;
	boolean black43 = true;
	boolean black44 = true;

	private void CountCheck(Point CheckPoint, Color color) {
		if (who.equals("����")) {
			CheckPoint.x = CheckPoint.x + 15;
			if (68 < CheckPoint.y && CheckPoint.y < 133) {
				if (77 < CheckPoint.x && CheckPoint.x < 152) {
					if (red1 == true && color == Color.red) {
						redNum++;
						red1 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue1 == true && color == Color.blue) {
						blueNum++;
						blue1 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray1 == true && color == Color.gray) {
						grayNum++;
						gray1 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black1 == true && color == Color.black) {
						blackNum++;
						black1 = false;
					}
				} else if (156 < CheckPoint.x && CheckPoint.x < 231) {
					if (red2 == true && color == Color.red) {
						redNum++;
						red2 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue2 == true && color == Color.blue) {
						blueNum++;
						blue2 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray2 == true && color == Color.gray) {
						grayNum++;
						gray2 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black2 == true && color == Color.black) {
						blackNum++;
						black2 = false;
					}

				} else if (233 < CheckPoint.x && CheckPoint.x < 286) {
					if (red3 == true && color == Color.red) {
						redNum++;
						red3 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue3 == true && color == Color.blue) {
						blueNum++;
						blue3 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray3 == true && color == Color.gray) {
						grayNum2++;
						gray3 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black3 == true && color == Color.black) {
						blackNum++;
						black3 = false;
					}

				} else if (288 < CheckPoint.x && CheckPoint.x < 342) {
					if (red4 == true && color == Color.red) {
						redNum++;
						red4 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue4 == true && color == Color.blue) {
						blueNum++;
						blue4 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray4 == true && color == Color.gray) {
						grayNum2++;
						gray4 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black4 == true && color == Color.black) {
						blackNum++;
						black4 = false;
					}

				} else if (344 < CheckPoint.x && CheckPoint.x < 397) {
					if (red5 == true && color == Color.red) {
						redNum++;
						red5 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue5 == true && color == Color.blue) {
						blueNum++;
						blue5 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray5 == true && color == Color.gray) {
						grayNum2++;
						gray5 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black5 == true && color == Color.black) {
						blackNum++;
						black5 = false;
					}
				} else if (399 < CheckPoint.x && CheckPoint.x < 453) {
					if (red6 == true && color == Color.red) {
						redNum++;
						red6 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue6 == true && color == Color.blue) {
						blueNum++;
						blue6 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray6 == true && color == Color.gray) {
						grayNum2++;
						gray6 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black6 == true && color == Color.black) {
						blackNum++;
						black6 = false;
					}
				} else if (455 < CheckPoint.x && CheckPoint.x < 509) {
					if (red7 == true && color == Color.red) {
						redNum++;
						red7 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue7 == true && color == Color.blue) {
						blueNum++;
						blue7 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray7 == true && color == Color.gray) {
						grayNum2++;
						gray7 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black7 == true && color == Color.black) {
						blackNum++;
						black7 = false;
					}
				} else if (512 < CheckPoint.x && CheckPoint.x < 565) {
					if (red8 == true && color == Color.red) {
						redNum++;
						red8 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}

					if (blue8 == true && color == Color.blue) {
						blueNum++;
						blue8 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray8 == true && color == Color.gray) {
						grayNum2++;
						gray8 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black8 == true && color == Color.black) {
						blackNum++;
						black8 = false;
					}
				} else if (568 < CheckPoint.x && CheckPoint.x < 643) {
					if (red9 == true && color == Color.red) {
						redNum++;
						red9 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue9 == true && color == Color.blue) {
						blueNum++;
						blue9 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray9 == true && color == Color.gray) {
						grayNum++;
						gray9 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black9 == true && color == Color.black) {
						blackNum++;
						black9 = false;
					}
				} else if (646 < CheckPoint.x && CheckPoint.x < 721) {
					if (red10 == true && color == Color.red) {
						redNum++;
						red10 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue10 == true && color == Color.blue) {
						blueNum++;
						blue10 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray10 == true && color == Color.gray) {
						grayNum++;
						gray10 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black10 == true && color == Color.black) {
						blackNum++;
						black10 = false;
					}
				}

			} else if (167 < CheckPoint.y && CheckPoint.y < 236) {
				if (77 < CheckPoint.x && CheckPoint.x < 152) {
					if (red11 == true && color == Color.red) {
						redNum++;
						red11 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue11 == true && color == Color.blue) {
						blueNum++;
						blue11 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray11 == true && color == Color.gray) {
						grayNum++;
						gray11 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black11 == true && color == Color.black) {
						blackNum++;
						black11 = false;
					}
				} else if (156 < CheckPoint.x && CheckPoint.x < 231) {
					if (red12 == true && color == Color.red) {
						redNum++;
						red12 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue12 == true && color == Color.blue) {
						blueNum++;
						blue12 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray12 == true && color == Color.gray) {
						grayNum++;
						gray12 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black12 == true && color == Color.black) {
						blackNum++;
						black12 = false;
					}

				} else if (233 < CheckPoint.x && CheckPoint.x < 286) {
					if (red13 == true && color == Color.red) {
						redNum++;
						red13 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue13 == true && color == Color.blue) {
						blueNum++;
						blue13 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray13 == true && color == Color.gray) {
						grayNum2++;
						gray13 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black13 == true && color == Color.black) {
						blackNum++;
						black13 = false;
					}
				} else if (288 < CheckPoint.x && CheckPoint.x < 342) {
					if (red14 == true && color == Color.red) {
						redNum++;
						red14 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue14 == true && color == Color.blue) {
						blueNum++;
						blue14 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray14 == true && color == Color.gray) {
						grayNum2++;
						gray14 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black14 == true && color == Color.black) {
						blackNum++;
						black14 = false;
					}

				} else if (344 < CheckPoint.x && CheckPoint.x < 397) {
					if (red15 == true && color == Color.red) {
						redNum++;
						red15 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue15 == true && color == Color.blue) {
						blueNum++;
						blue15 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray15 == true && color == Color.gray) {
						grayNum2++;
						gray15 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black15 == true && color == Color.black) {
						blackNum++;
						black15 = false;
					}
				} else if (399 < CheckPoint.x && CheckPoint.x < 453) {
					if (red16 == true && color == Color.red) {
						redNum++;
						red16 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue16 == true && color == Color.blue) {
						blueNum++;
						blue16 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray16 == true && color == Color.gray) {
						grayNum2++;
						gray16 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black16 == true && color == Color.black) {
						blackNum++;
						black16 = false;
					}
				} else if (455 < CheckPoint.x && CheckPoint.x < 509) {
					if (red17 == true && color == Color.red) {
						redNum++;
						red17 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue17 == true && color == Color.blue) {
						blueNum++;
						blue17 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray17 == true && color == Color.gray) {
						grayNum2++;
						gray17 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black17 == true && color == Color.black) {
						blackNum++;
						black17 = false;
					}
				} else if (512 < CheckPoint.x && CheckPoint.x < 565) {
					if (red18 == true && color == Color.red) {
						redNum++;
						red18 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue18 == true && color == Color.blue) {
						blueNum++;
						blue18 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray18 == true && color == Color.gray) {
						grayNum2++;
						gray18 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black18 == true && color == Color.black) {
						blackNum++;
						black18 = false;
					}
				} else if (568 < CheckPoint.x && CheckPoint.x < 643) {
					if (red19 == true && color == Color.red) {
						redNum++;
						red19 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue19 == true && color == Color.blue) {
						blueNum++;
						blue19 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray19 == true && color == Color.gray) {
						grayNum++;
						gray19 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black19 == true && color == Color.black) {
						blackNum++;
						black19 = false;
					}
				} else if (646 < CheckPoint.x && CheckPoint.x < 721) {
					if (red20 == true && color == Color.red) {
						redNum++;
						red20 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue20 == true && color == Color.blue) {
						blueNum++;
						blue20 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray20 == true && color == Color.gray) {
						grayNum++;
						gray20 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black20 == true && color == Color.black) {
						blackNum++;
						black20 = false;
					}
				}
			}
		}

		if (who.equals("�Ƶ�")) {
			CheckPoint.x = CheckPoint.x + 10;
			CheckPoint.y = CheckPoint.y + 5;
			if (24 < CheckPoint.y && CheckPoint.y < 77) {
				if (77 < CheckPoint.x && CheckPoint.x < 137) {
					if (red1 == true && color == Color.red) {
						redNum++;
						red1 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue1 == true && color == Color.blue) {
						blueNum++;
						blue1 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray1 == true && color == Color.gray) {
						grayNum++;
						gray1 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black1 == true && color == Color.black) {
						blackNum++;
						black1 = false;
					}
				} else if (140 < CheckPoint.x && CheckPoint.x < 200) {
					if (red2 == true && color == Color.red) {
						redNum++;
						red2 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue2 == true && color == Color.blue) {
						blueNum++;
						blue2 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray2 == true && color == Color.gray) {
						grayNum++;
						gray2 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black2 == true && color == Color.black) {
						blackNum++;
						black2 = false;
					}

				} else if (204 < CheckPoint.x && CheckPoint.x < 262) {
					if (red3 == true && color == Color.red) {
						redNum++;
						red3 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue3 == true && color == Color.blue) {
						blueNum++;
						blue3 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray3 == true && color == Color.gray) {
						grayNum++;
						gray3 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black3 == true && color == Color.black) {
						blackNum++;
						black3 = false;
					}

				} else if (266 < CheckPoint.x && CheckPoint.x < 308) {
					if (red4 == true && color == Color.red) {
						redNum++;
						red4 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue4 == true && color == Color.blue) {
						blueNum++;
						blue4 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray4 == true && color == Color.gray) {
						grayNum2++;
						gray4 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black4 == true && color == Color.black) {
						blackNum++;
						black4 = false;
					}

				} else if (310 < CheckPoint.x && CheckPoint.x < 352) {
					if (red5 == true && color == Color.red) {
						redNum++;
						red5 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue5 == true && color == Color.blue) {
						blueNum++;
						blue5 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray5 == true && color == Color.gray) {
						grayNum2++;
						gray5 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black5 == true && color == Color.black) {
						blackNum++;
						black5 = false;
					}
				} else if (355 < CheckPoint.x && CheckPoint.x < 397) {
					if (red6 == true && color == Color.red) {
						redNum++;
						red6 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue6 == true && color == Color.blue) {
						blueNum++;
						blue6 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray6 == true && color == Color.gray) {
						grayNum2++;
						gray6 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black6 == true && color == Color.black) {
						blackNum++;
						black6 = false;
					}
				} else if (401 < CheckPoint.x && CheckPoint.x < 441) {
					if (red7 == true && color == Color.red) {
						redNum++;
						red7 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue7 == true && color == Color.blue) {
						blueNum++;
						blue7 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray7 == true && color == Color.gray) {
						grayNum2++;
						gray7 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black7 == true && color == Color.black) {
						blackNum++;
						black7 = false;
					}
				} else if (445 < CheckPoint.x && CheckPoint.x < 487) {
					if (red8 == true && color == Color.red) {
						redNum++;
						red8 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}

					if (blue8 == true && color == Color.blue) {
						blueNum++;
						blue8 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray8 == true && color == Color.gray) {
						grayNum2++;
						gray8 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black8 == true && color == Color.black) {
						blackNum++;
						black8 = false;
					}
				} else if (491 < CheckPoint.x && CheckPoint.x < 532) {
					if (red9 == true && color == Color.red) {
						redNum++;
						red9 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue9 == true && color == Color.blue) {
						blueNum++;
						blue9 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray9 == true && color == Color.gray) {
						grayNum2++;
						gray9 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black9 == true && color == Color.black) {
						blackNum++;
						black9 = false;
					}
				} else if (536 < CheckPoint.x && CheckPoint.x < 595) {
					if (red10 == true && color == Color.red) {
						redNum++;
						red10 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue10 == true && color == Color.blue) {
						blueNum++;
						blue10 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray10 == true && color == Color.gray) {
						grayNum++;
						gray10 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black10 == true && color == Color.black) {
						blackNum++;
						black10 = false;
					}
				} else if (599 < CheckPoint.x && CheckPoint.x < 658) {
					if (red11 == true && color == Color.red) {
						redNum++;
						red11 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue11 == true && color == Color.blue) {
						blueNum++;
						blue11 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray11 == true && color == Color.gray) {
						grayNum++;
						gray11 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black11 == true && color == Color.black) {
						blackNum++;
						black11 = false;
					}
				} else if (661 < CheckPoint.x && CheckPoint.x < 721) {
					if (red12 == true && color == Color.red) {
						redNum++;
						red12 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue12 == true && color == Color.blue) {
						blueNum++;
						blue12 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray12 == true && color == Color.gray) {
						grayNum++;
						gray12 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black12 == true && color == Color.black) {
						blackNum++;
						black12 = false;
					}
				}

			} else if (82 < CheckPoint.y && CheckPoint.y < 137) {
				if (140 < CheckPoint.x && CheckPoint.x < 200) {
					if (red13 == true && color == Color.red) {
						redNum++;
						red13 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue13 == true && color == Color.blue) {
						blueNum++;
						blue13 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray13 == true && color == Color.gray) {
						grayNum++;
						gray13 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black13 == true && color == Color.black) {
						blackNum++;
						black13 = false;
					}

				} else if (204 < CheckPoint.x && CheckPoint.x < 262) {
					if (red14 == true && color == Color.red) {
						redNum++;
						red14 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue14 == true && color == Color.blue) {
						blueNum++;
						blue14 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray14 == true && color == Color.gray) {
						grayNum++;
						gray14 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black14 == true && color == Color.black) {
						blackNum++;
						black14 = false;
					}

				} else if (266 < CheckPoint.x && CheckPoint.x < 308) {
					if (red15 == true && color == Color.red) {
						redNum++;
						red15 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue15 == true && color == Color.blue) {
						blueNum++;
						blue15 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray15 == true && color == Color.gray) {
						grayNum2++;
						gray15 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black15 == true && color == Color.black) {
						blackNum++;
						black15 = false;
					}

				} else if (310 < CheckPoint.x && CheckPoint.x < 352) {
					if (red16 == true && color == Color.red) {
						redNum++;
						red16 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue16 == true && color == Color.blue) {
						blueNum++;
						blue16 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray16 == true && color == Color.gray) {
						grayNum2++;
						gray16 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black16 == true && color == Color.black) {
						blackNum++;
						black16 = false;
					}
				} else if (355 < CheckPoint.x && CheckPoint.x < 397) {
					if (red17 == true && color == Color.red) {
						redNum++;
						red17 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue17 == true && color == Color.blue) {
						blueNum++;
						blue17 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray17 == true && color == Color.gray) {
						grayNum2++;
						gray17 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black17 == true && color == Color.black) {
						blackNum++;
						black17 = false;
					}
				} else if (401 < CheckPoint.x && CheckPoint.x < 441) {
					if (red18 == true && color == Color.red) {
						redNum++;
						red18 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue18 == true && color == Color.blue) {
						blueNum++;
						blue18 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray18 == true && color == Color.gray) {
						grayNum2++;
						gray18 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black18 == true && color == Color.black) {
						blackNum++;
						black18 = false;
					}
				} else if (445 < CheckPoint.x && CheckPoint.x < 487) {
					if (red19 == true && color == Color.red) {
						redNum++;
						red19 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}

					if (blue19 == true && color == Color.blue) {
						blueNum++;
						blue19 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray19 == true && color == Color.gray) {
						grayNum2++;
						gray19 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black19 == true && color == Color.black) {
						blackNum++;
						black19 = false;
					}
				} else if (491 < CheckPoint.x && CheckPoint.x < 532) {
					if (red20 == true && color == Color.red) {
						redNum++;
						red20 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue20 == true && color == Color.blue) {
						blueNum++;
						blue20 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray20 == true && color == Color.gray) {
						grayNum2++;
						gray20 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black20 == true && color == Color.black) {
						blackNum++;
						black20 = false;
					}
				} else if (536 < CheckPoint.x && CheckPoint.x < 595) {
					if (red21 == true && color == Color.red) {
						redNum++;
						red21 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue21 == true && color == Color.blue) {
						blueNum++;
						blue21 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray21 == true && color == Color.gray) {
						grayNum++;
						gray21 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black21 == true && color == Color.black) {
						blackNum++;
						black21 = false;
					}
				} else if (599 < CheckPoint.x && CheckPoint.x < 658) {
					if (red22 == true && color == Color.red) {
						redNum++;
						red22 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue22 == true && color == Color.blue) {
						blueNum++;
						blue22 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray22 == true && color == Color.gray) {
						grayNum++;
						gray22 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black22 == true && color == Color.black) {
						blackNum++;
						black22 = false;
					}
				}
			} else if (173 < CheckPoint.y && CheckPoint.y < 218) {
				if (140 < CheckPoint.x && CheckPoint.x < 200) {
					if (red23 == true && color == Color.red) {
						redNum++;
						red23 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue23 == true && color == Color.blue) {
						blueNum++;
						blue23 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray23 == true && color == Color.gray) {
						grayNum++;
						gray23 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black23 == true && color == Color.black) {
						blackNum++;
						black23 = false;
					}

				} else if (204 < CheckPoint.x && CheckPoint.x < 262) {
					if (red24 == true && color == Color.red) {
						redNum++;
						red24 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue24 == true && color == Color.blue) {
						blueNum++;
						blue24 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray24 == true && color == Color.gray) {
						grayNum++;
						gray24 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black24 == true && color == Color.black) {
						blackNum++;
						black24 = false;
					}

				} else if (266 < CheckPoint.x && CheckPoint.x < 308) {
					if (red25 == true && color == Color.red) {
						redNum++;
						red25 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue25 == true && color == Color.blue) {
						blueNum++;
						blue25 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray25 == true && color == Color.gray) {
						grayNum2++;
						gray25 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black25 == true && color == Color.black) {
						blackNum++;
						black25 = false;
					}

				} else if (310 < CheckPoint.x && CheckPoint.x < 352) {
					if (red26 == true && color == Color.red) {
						redNum++;
						red26 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue26 == true && color == Color.blue) {
						blueNum++;
						blue26 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray26 == true && color == Color.gray) {
						grayNum2++;
						gray26 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black26 == true && color == Color.black) {
						blackNum++;
						black26 = false;
					}
				} else if (355 < CheckPoint.x && CheckPoint.x < 397) {
					if (red27 == true && color == Color.red) {
						redNum++;
						red27 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue27 == true && color == Color.blue) {
						blueNum++;
						blue27 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray27 == true && color == Color.gray) {
						grayNum2++;
						gray27 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black27 == true && color == Color.black) {
						blackNum++;
						black27 = false;
					}
				} else if (401 < CheckPoint.x && CheckPoint.x < 441) {
					if (red28 == true && color == Color.red) {
						redNum++;
						red28 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue28 == true && color == Color.blue) {
						blueNum++;
						blue28 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray28 == true && color == Color.gray) {
						grayNum2++;
						gray28 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black28 == true && color == Color.black) {
						blackNum++;
						black28 = false;
					}
				} else if (445 < CheckPoint.x && CheckPoint.x < 487) {
					if (red29 == true && color == Color.red) {
						redNum++;
						red29 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}

					if (blue29 == true && color == Color.blue) {
						blueNum++;
						blue29 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray29 == true && color == Color.gray) {
						grayNum2++;
						gray29 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black29 == true && color == Color.black) {
						blackNum++;
						black29 = false;
					}
				} else if (491 < CheckPoint.x && CheckPoint.x < 532) {
					if (red30 == true && color == Color.red) {
						redNum++;
						red30 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue30 == true && color == Color.blue) {
						blueNum++;
						blue30 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray30 == true && color == Color.gray) {
						grayNum2++;
						gray30 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black30 == true && color == Color.black) {
						blackNum++;
						black30 = false;
					}
				} else if (536 < CheckPoint.x && CheckPoint.x < 595) {
					if (red31 == true && color == Color.red) {
						redNum++;
						red31 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue31 == true && color == Color.blue) {
						blueNum++;
						blue31 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray31 == true && color == Color.gray) {
						grayNum++;
						gray31 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black31 == true && color == Color.black) {
						blackNum++;
						black31 = false;
					}
				} else if (599 < CheckPoint.x && CheckPoint.x < 658) {
					if (red32 == true && color == Color.red) {
						redNum++;
						red32 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue32 == true && color == Color.blue) {
						blueNum++;
						blue32 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray32 == true && color == Color.gray) {
						grayNum++;
						gray32 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_babyTooth.setText((Integer.parseInt(info_babyTooth.getText())-1)+"");
						invalidate();
					}
					if (black32 == true && color == Color.black) {
						blackNum++;
						black32 = false;
					}
				}
			} else if (224 < CheckPoint.y && CheckPoint.y < 279) {
				if (77 < CheckPoint.x && CheckPoint.x < 137) {
					if (red33 == true && color == Color.red) {
						redNum++;
						red33 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue33 == true && color == Color.blue) {
						blueNum++;
						blue33 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray33 == true && color == Color.gray) {
						grayNum++;
						gray33 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black33 == true && color == Color.black) {
						blackNum++;
						black33 = false;
					}
				} else if (140 < CheckPoint.x && CheckPoint.x < 200) {
					if (red34 == true && color == Color.red) {
						redNum++;
						red34 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue34 == true && color == Color.blue) {
						blueNum++;
						blue34 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray34 == true && color == Color.gray) {
						grayNum++;
						gray34 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black34 == true && color == Color.black) {
						blackNum++;
						black34 = false;
					}

				} else if (204 < CheckPoint.x && CheckPoint.x < 262) {
					if (red35 == true && color == Color.red) {
						redNum++;
						red35 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue35 == true && color == Color.blue) {
						blueNum++;
						blue35 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray35 == true && color == Color.gray) {
						grayNum++;
						gray35 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black35 == true && color == Color.black) {
						blackNum++;
						black35 = false;
					}

				} else if (266 < CheckPoint.x && CheckPoint.x < 308) {
					if (red36 == true && color == Color.red) {
						redNum++;
						red36 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue36 == true && color == Color.blue) {
						blueNum++;
						blue36 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray36 == true && color == Color.gray) {
						grayNum2++;
						gray36 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black36 == true && color == Color.black) {
						blackNum++;
						black36 = false;
					}

				} else if (310 < CheckPoint.x && CheckPoint.x < 352) {
					if (red37 == true && color == Color.red) {
						redNum++;
						red37 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue37 == true && color == Color.blue) {
						blueNum++;
						blue37 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray37 == true && color == Color.gray) {
						grayNum2++;
						gray37 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black37 == true && color == Color.black) {
						blackNum++;
						black37 = false;
					}
				} else if (355 < CheckPoint.x && CheckPoint.x < 397) {
					if (red38 == true && color == Color.red) {
						redNum++;
						red38 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue38 == true && color == Color.blue) {
						blueNum++;
						blue38 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray38 == true && color == Color.gray) {
						grayNum2++;
						gray38 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black38 == true && color == Color.black) {
						blackNum++;
						black38 = false;
					}
				} else if (401 < CheckPoint.x && CheckPoint.x < 441) {
					if (red39 == true && color == Color.red) {
						redNum++;
						red39 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue39 == true && color == Color.blue) {
						blueNum++;
						blue39 = false;
					}
					if (gray39 == true && color == Color.gray) {
						grayNum2++;
						gray39 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black39 == true && color == Color.black) {
						blackNum++;
						black39 = false;
					}
				} else if (445 < CheckPoint.x && CheckPoint.x < 487) {
					if (red40 == true && color == Color.red) {
						redNum++;
						red40 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}

					if (blue40 == true && color == Color.blue) {
						blueNum++;
						blue40 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray40 == true && color == Color.gray) {
						grayNum2++;
						gray40 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black40 == true && color == Color.black) {
						blackNum++;
						black40 = false;
					}
				} else if (491 < CheckPoint.x && CheckPoint.x < 532) {
					if (red41 == true && color == Color.red) {
						redNum++;
						red41 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue41 == true && color == Color.blue) {
						blueNum++;
						blue41 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray41 == true && color == Color.gray) {
						grayNum2++;
						gray41 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black41 == true && color == Color.black) {
						blackNum++;
						black41 = false;
					}
				} else if (536 < CheckPoint.x && CheckPoint.x < 595) {
					if (red42 == true && color == Color.red) {
						redNum++;
						red42 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue42 == true && color == Color.blue) {
						blueNum++;
						blue42 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray42 == true && color == Color.gray) {
						grayNum2++;
						gray42 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black42 == true && color == Color.black) {
						blackNum++;
						black42 = false;
					}
				} else if (599 < CheckPoint.x && CheckPoint.x < 658) {
					if (red43 == true && color == Color.red) {
						redNum++;
						red43 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue43 == true && color == Color.blue) {
						blueNum++;
						blue43 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray43 == true && color == Color.gray) {
						grayNum++;
						gray43 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black43 == true && color == Color.black) {
						blackNum++;
						black43 = false;
					}
				} else if (661 < CheckPoint.x && CheckPoint.x < 721) {
					if (red44 == true && color == Color.red) {
						redNum++;
						red44 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue44 == true && color == Color.blue) {
						blueNum++;
						blue44 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray44 == true && color == Color.gray) {
						grayNum++;
						gray44 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black44 == true && color == Color.black) {
						blackNum++;
						black44 = false;
					}

				}
			}
		}

		if (who.equals("û��")) {
			CheckPoint.x = CheckPoint.x + 10;
			if (90 < CheckPoint.y && CheckPoint.y < 139) {
				if (2 < CheckPoint.x && CheckPoint.x < 55) {
					if (red1 == true && color == Color.red) {
						redNum++;
						red1 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue1 == true && color == Color.blue) {
						blueNum++;
						blue1 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray1 == true && color == Color.gray) {
						//grayNum++;
						gray1 = false;
						//info_losePermanentTooth_back.setText(grayNum+"");
						//info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black1 == true && color == Color.black) {
						blackNum++;
						black1 = false;
					}
				} else if (57 < CheckPoint.x && CheckPoint.x < 110) {
					if (red2 == true && color == Color.red) {
						redNum++;
						red2 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue2 == true && color == Color.blue) {
						blueNum++;
						blue2 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray2 == true && color == Color.gray) {
						grayNum++;
						gray2 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black2 == true && color == Color.black) {
						blackNum++;
						black2 = false;
					}

				} else if (114 < CheckPoint.x && CheckPoint.x < 167) {
					if (red3 == true && color == Color.red) {
						redNum++;
						red3 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue3 == true && color == Color.blue) {
						blueNum++;
						blue3 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray3 == true && color == Color.gray) {
						grayNum++;
						gray3 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black3 == true && color == Color.black) {
						blackNum++;
						black3 = false;
					}

				} else if (170 < CheckPoint.x && CheckPoint.x < 223) {
					if (red4 == true && color == Color.red) {
						redNum++;
						red4 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue4 == true && color == Color.blue) {
						blueNum++;
						blue4 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray4 == true && color == Color.gray) {
						grayNum++;
						gray4 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black4 == true && color == Color.black) {
						blackNum++;
						black4 = false;
					}

				} else if (227 < CheckPoint.x && CheckPoint.x < 280) {
					if (red5 == true && color == Color.red) {
						redNum++;
						red5 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue5 == true && color == Color.blue) {
						blueNum++;
						blue5 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray5 == true && color == Color.gray) {
						grayNum++;
						gray5 = false;
						info_losePermanentTooth_front.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black5 == true && color == Color.black) {
						blackNum++;
						black5 = false;
					}
				} else if (283 < CheckPoint.x && CheckPoint.x < 319) {
					if (red6 == true && color == Color.red) {
						redNum++;
						red6 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue6 == true && color == Color.blue) {
						blueNum++;
						blue6 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray6 == true && color == Color.gray) {
						grayNum2++;
						gray6 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black6 == true && color == Color.black) {
						blackNum++;
						black6 = false;
					}
				} else if (323 < CheckPoint.x && CheckPoint.x < 358) {
					if (red7 == true && color == Color.red) {
						redNum++;
						red7 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue7 == true && color == Color.blue) {
						blueNum++;
						blue7 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray7 == true && color == Color.gray) {
						grayNum2++;
						gray7 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black7 == true && color == Color.black) {
						blackNum++;
						black7 = false;
					}
				} else if (361 < CheckPoint.x && CheckPoint.x < 398) {
					if (red8 == true && color == Color.red) {
						redNum++;
						red8 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}

					if (blue8 == true && color == Color.blue) {
						blueNum++;
						blue8 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray8 == true && color == Color.gray) {
						grayNum2++;
						gray8 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black8 == true && color == Color.black) {
						blackNum++;
						black8 = false;
					}
				} else if (401 < CheckPoint.x && CheckPoint.x < 437) {
					if (red9 == true && color == Color.red) {
						redNum++;
						red9 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue9 == true && color == Color.blue) {
						blueNum++;
						blue9 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray9 == true && color == Color.gray) {
						grayNum2++;
						gray9 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black9 == true && color == Color.black) {
						blackNum++;
						black9 = false;
					}
				} else if (440 < CheckPoint.x && CheckPoint.x < 478) {
					if (red10 == true && color == Color.red) {
						redNum++;
						red10 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue10 == true && color == Color.blue) {
						blueNum++;
						blue10 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray10 == true && color == Color.gray) {
						grayNum2++;
						gray10 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black10 == true && color == Color.black) {
						blackNum++;
						black10 = false;
					}
				} else if (480 < CheckPoint.x && CheckPoint.x < 518) {
					if (red11 == true && color == Color.red) {
						redNum++;
						red11 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue11 == true && color == Color.blue) {
						blueNum++;
						blue11 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray11 == true && color == Color.gray) {
						grayNum2++;
						gray11 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black11 == true && color == Color.black) {
						blackNum++;
						black11 = false;
					}
				} else if (521 < CheckPoint.x && CheckPoint.x < 573) {
					if (red12 == true && color == Color.red) {
						redNum++;
						red12 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue12 == true && color == Color.blue) {
						blueNum++;
						blue12 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray12 == true && color == Color.gray) {
						grayNum++;
						gray12 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black12 == true && color == Color.black) {
						blackNum++;
						black12 = false;
					}
				} else if (576 < CheckPoint.x && CheckPoint.x < 629) {
					if (red13 == true && color == Color.red) {
						redNum++;
						red13 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue13 == true && color == Color.blue) {
						blueNum++;
						blue13 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray13 == true && color == Color.gray) {
						grayNum++;
						gray13 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black13 == true && color == Color.black) {
						blackNum++;
						black13 = false;
					}

				} else if (632 < CheckPoint.x && CheckPoint.x < 685) {
					if (red14 == true && color == Color.red) {
						redNum++;
						red14 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue14 == true && color == Color.blue) {
						blueNum++;
						blue14 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray14 == true && color == Color.gray) {
						grayNum++;
						gray14 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black14 == true && color == Color.black) {
						blackNum++;
						black14 = false;
					}

				} else if (687 < CheckPoint.x && CheckPoint.x < 741) {
					if (red15 == true && color == Color.red) {
						redNum++;
						red15 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue15 == true && color == Color.blue) {
						blueNum++;
						blue15 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray15 == true && color == Color.gray) {
						grayNum++;
						gray15 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black15 == true && color == Color.black) {
						blackNum++;
						black15 = false;
					}

				} else if (743 < CheckPoint.x && CheckPoint.x < 796) {
					if (red16 == true && color == Color.red) {
					    redNum++;
						red16 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue16 == true && color == Color.blue) {
						blueNum++;
						blue16 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray16 == true && color == Color.gray) {
						//grayNum++;
						gray16 = false;
						//info_losePermanentTooth_back.setText(grayNum+"");
						//info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black16 == true && color == Color.black) {
						blackNum++;
						black16 = false;
					}
				}

			} else if (163 < CheckPoint.y && CheckPoint.y < 212) {
				if (2 < CheckPoint.x && CheckPoint.x < 55) {
					if (red17 == true && color == Color.red) {
						redNum++;
						red17 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue17 == true && color == Color.blue) {
						blueNum++;
						blue17 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray17 == true && color == Color.gray) {
						//grayNum++;
						gray17 = false;
						//info_losePermanentTooth_back.setText(grayNum+"");
						//info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black17 == true && color == Color.black) {
						blackNum++;
						black17 = false;
					}
				} else if (57 < CheckPoint.x && CheckPoint.x < 110) {
					if (red18 == true && color == Color.red) {
						redNum++;
						red18 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue18 == true && color == Color.blue) {
						blueNum++;
						blue18 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray18 == true && color == Color.gray) {
						grayNum++;
						gray18 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black18 == true && color == Color.black) {
						blackNum++;
						black18 = false;
					}

				} else if (114 < CheckPoint.x && CheckPoint.x < 167) {
					if (red19 == true && color == Color.red) {
						redNum++;
						red19 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue19 == true && color == Color.blue) {
						blueNum++;
						blue19 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray19 == true && color == Color.gray) {
						grayNum++;
						gray19 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black19 == true && color == Color.black) {
						blackNum++;
						black19 = false;
					}

				} else if (170 < CheckPoint.x && CheckPoint.x < 223) {
					if (red20 == true && color == Color.red) {
						redNum++;
						red20 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue20 == true && color == Color.blue) {
						blueNum++;
						blue20 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray20 == true && color == Color.gray) {
						grayNum++;
						gray20 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black20 == true && color == Color.black) {
						blackNum++;
						black20 = false;
					}

				} else if (227 < CheckPoint.x && CheckPoint.x < 280) {
					if (red21 == true && color == Color.red) {
						redNum++;
						red21 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue21 == true && color == Color.blue) {
						blueNum++;
						blue21 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray21 == true && color == Color.gray) {
						grayNum++;
						gray21 = false;
						info_losePermanentTooth_front.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black21 == true && color == Color.black) {
						blackNum++;
						black21 = false;
					}
				} else if (283 < CheckPoint.x && CheckPoint.x < 319) {
					if (red22 == true && color == Color.red) {
						redNum++;
						red22 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue22 == true && color == Color.blue) {
						blueNum++;
						blue22 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray22 == true && color == Color.gray) {
						grayNum2++;
						gray22 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black22 == true && color == Color.black) {
						blackNum++;
						black22 = false;
					}
				} else if (323 < CheckPoint.x && CheckPoint.x < 358) {
					if (red23 == true && color == Color.red) {
						redNum++;
						red23 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue23 == true && color == Color.blue) {
						blueNum++;
						blue23 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray23 == true && color == Color.gray) {
						grayNum2++;
						gray23 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black23 == true && color == Color.black) {
						blackNum++;
						black23 = false;
					}
				} else if (361 < CheckPoint.x && CheckPoint.x < 398) {
					if (red24 == true && color == Color.red) {
						redNum++;
						red24 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}

					if (blue24 == true && color == Color.blue) {
						blueNum++;
						blue24 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray24 == true && color == Color.gray) {
						grayNum2++;
						gray24 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black24 == true && color == Color.black) {
						blackNum++;
						black24 = false;
					}
				} else if (401 < CheckPoint.x && CheckPoint.x < 437) {
					if (red25 == true && color == Color.red) {
						redNum++;
						red25 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue25 == true && color == Color.blue) {
						blueNum++;
						blue25 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray25 == true && color == Color.gray) {
						grayNum2++;
						gray25 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black25 == true && color == Color.black) {
						blackNum++;
						black25 = false;
					}
				} else if (440 < CheckPoint.x && CheckPoint.x < 478) {
					if (red26 == true && color == Color.red) {
						redNum++;
						red26 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue26 == true && color == Color.blue) {
						blueNum++;
						blue26 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray26 == true && color == Color.gray) {
						grayNum2++;
						gray26 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black26 == true && color == Color.black) {
						blackNum++;
						black26 = false;
					}
				} else if (480 < CheckPoint.x && CheckPoint.x < 518) {
					if (red27 == true && color == Color.red) {
						redNum++;
						red27 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue27 == true && color == Color.blue) {
						blueNum++;
						blue27 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray27 == true && color == Color.gray) {
						grayNum2++;
						gray27 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black27 == true && color == Color.black) {
						blackNum++;
						black27 = false;
					}
				} else if (521 < CheckPoint.x && CheckPoint.x < 573) {
					if (red28 == true && color == Color.red) {
						redNum++;
						red28 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue28 == true && color == Color.blue) {
						blueNum++;
						blue28 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray28 == true && color == Color.gray) {
						grayNum++;
						gray28 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black28 == true && color == Color.black) {
						blackNum++;
						black28 = false;
					}
				} else if (576 < CheckPoint.x && CheckPoint.x < 629) {
					if (red29 == true && color == Color.red) {
						redNum++;
						red29 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue29 == true && color == Color.blue) {
						blueNum++;
						blue29 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray29 == true && color == Color.gray) {
						grayNum++;
						gray29 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black29 == true && color == Color.black) {
						blackNum++;
						black29 = false;
					}

				} else if (632 < CheckPoint.x && CheckPoint.x < 685) {
					if (red30 == true && color == Color.red) {
						redNum++;
						red30 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue30 == true && color == Color.blue) {
						blueNum++;
						blue30 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray30 == true && color == Color.gray) {
						grayNum++;
						gray30 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black30 == true && color == Color.black) {
						blackNum++;
						black30 = false;
					}

				} else if (687 < CheckPoint.x && CheckPoint.x < 741) {
					if (red31 == true && color == Color.red) {
						redNum++;
						red31 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue31 == true && color == Color.blue) {
						blueNum++;
						blue31 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray31 == true && color == Color.gray) {
						grayNum++;
						gray31 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black31 == true && color == Color.black) {
						blackNum++;
						black31 = false;
					}

				} else if (743 < CheckPoint.x && CheckPoint.x < 796) {
					if (red32 == true && color == Color.red) {
						redNum++;
						red32 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue32 == true && color == Color.blue) {
						blueNum++;
						blue32 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray32 == true && color == Color.gray) {
						//grayNum++;
						gray32 = false;
						//info_losePermanentTooth_back.setText(grayNum+"");
						//info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black32 == true && color == Color.black) {
						blackNum++;
						black32 = false;
					}
				}
			}
		}

		if (who.equals("û�ҳ�")) {
			CheckPoint.x = CheckPoint.x + 10;
			if (84 < CheckPoint.y && CheckPoint.y < 139) {
				if (27 < CheckPoint.x && CheckPoint.x < 84) {
					if (red1 == true && color == Color.red) {
						redNum++;
						red1 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue1 == true && color == Color.blue) {
						blueNum++;
						blue1 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray1 == true && color == Color.gray) {
						grayNum++;
						gray1 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black1 == true && color == Color.black) {
						blackNum++;
						black1 = false;
					}
				} else if (88 < CheckPoint.x && CheckPoint.x < 146) {
					if (red2 == true && color == Color.red) {
						redNum++;
						red2 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue2 == true && color == Color.blue) {
						blueNum++;
						blue2 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray2 == true && color == Color.gray) {
						grayNum++;
						gray2 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black2 == true && color == Color.black) {
						blackNum++;
						black2 = false;
					}

				} else if (149 < CheckPoint.x && CheckPoint.x < 207) {
					if (red3 == true && color == Color.red) {
						redNum++;
						red3 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue3 == true && color == Color.blue) {
						blueNum++;
						blue3 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray3 == true && color == Color.gray) {
						grayNum++;
						gray3 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black3 == true && color == Color.black) {
						blackNum++;
						black3 = false;
					}

				} else if (211 < CheckPoint.x && CheckPoint.x < 269) {
					if (red4 == true && color == Color.red) {
						redNum++;
						red4 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue4 == true && color == Color.blue) {
						blueNum++;
						blue4 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray4 == true && color == Color.gray) {
						grayNum++;
						gray4 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black4 == true && color == Color.black) {
						blackNum++;
						black4 = false;
					}

				} else if (272 < CheckPoint.x && CheckPoint.x < 313) {
					if (red5 == true && color == Color.red) {
						redNum++;
						red5 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue5 == true && color == Color.blue) {
						blueNum++;
						blue5 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray5 == true && color == Color.gray) {
						grayNum2++;
						gray5 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black5 == true && color == Color.black) {
						blackNum++;
						black5 = false;
					}
				} else if (315 < CheckPoint.x && CheckPoint.x < 355) {
					if (red6 == true && color == Color.red) {
						redNum++;
						red6 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue6 == true && color == Color.blue) {
						blueNum++;
						blue6 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray6 == true && color == Color.gray) {
						grayNum2++;
						gray6 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black6 == true && color == Color.black) {
						blackNum++;
						black6 = false;
					}
				} else if (358 < CheckPoint.x && CheckPoint.x < 398) {
					if (red7 == true && color == Color.red) {
						redNum++;
						red7 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue7 == true && color == Color.blue) {
						blueNum++;
						blue7 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray7 == true && color == Color.gray) {
						grayNum2++;
						gray7 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black7 == true && color == Color.black) {
						blackNum++;
						black7 = false;
					}
				} else if (402 < CheckPoint.x && CheckPoint.x < 439) {
					if (red8 == true && color == Color.red) {
						redNum++;
						red8 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}

					if (blue8 == true && color == Color.blue) {
						blueNum++;
						blue8 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray8 == true && color == Color.gray) {
						grayNum2++;
						gray8 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black8 == true && color == Color.black) {
						blackNum++;
						black8 = false;
					}
				} else if (444 < CheckPoint.x && CheckPoint.x < 483) {
					if (red9 == true && color == Color.red) {
						redNum++;
						red9 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue9 == true && color == Color.blue) {
						blueNum++;
						blue9 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray9 == true && color == Color.gray) {
						grayNum2++;
						gray9 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black9 == true && color == Color.black) {
						blackNum++;
						black9 = false;
					}
				} else if (488 < CheckPoint.x && CheckPoint.x < 529) {
					if (red10 == true && color == Color.red) {
						redNum++;
						red10 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue10 == true && color == Color.blue) {
						blueNum++;
						blue10 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray10 == true && color == Color.gray) {
						grayNum2++;
						gray10 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black10 == true && color == Color.black) {
						blackNum++;
						black10 = false;
					}
				} else if (531 < CheckPoint.x && CheckPoint.x < 589) {
					if (red11 == true && color == Color.red) {
						redNum++;
						red11 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue11 == true && color == Color.blue) {
						blueNum++;
						blue11 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray11 == true && color == Color.gray) {
						grayNum++;
						gray11 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black11 == true && color == Color.black) {
						blackNum++;
						black11 = false;
					}
				} else if (592 < CheckPoint.x && CheckPoint.x < 650) {
					if (red12 == true && color == Color.red) {
						redNum++;
						red12 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue12 == true && color == Color.blue) {
						blueNum++;
						blue12 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray12 == true && color == Color.gray) {
						grayNum++;
						gray12 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black12 == true && color == Color.black) {
						blackNum++;
						black12 = false;
					}
				} else if (655 < CheckPoint.x && CheckPoint.x < 711) {
					if (red13 == true && color == Color.red) {
						redNum++;
						red13 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue13 == true && color == Color.blue) {
						blueNum++;
						blue13 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray13 == true && color == Color.gray) {
						grayNum++;
						gray13 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black13 == true && color == Color.black) {
						blackNum++;
						black13 = false;
					}

				} else if (713 < CheckPoint.x && CheckPoint.x < 772) {
					if (red14 == true && color == Color.red) {
						redNum++;
						red14 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue14 == true && color == Color.blue) {
						blueNum++;
						blue14 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray14 == true && color == Color.gray) {
						grayNum++;
						gray14 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black14 == true && color == Color.black) {
						blackNum++;
						black14 = false;
					}
				}
			}

			else if (164 < CheckPoint.y && CheckPoint.y < 216) {
				if (27 < CheckPoint.x && CheckPoint.x < 84) {
					if (red15 == true && color == Color.red) {
						redNum++;
						red15 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue15 == true && color == Color.blue) {
						blueNum++;
						blue15 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray15 == true && color == Color.gray) {
						grayNum++;
						gray15 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black15 == true && color == Color.black) {
						blackNum++;
						black15 = false;
					}
				} else if (88 < CheckPoint.x && CheckPoint.x < 146) {
					if (red16 == true && color == Color.red) {
						redNum++;
						red16 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue16 == true && color == Color.blue) {
						blueNum++;
						blue16 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray16 == true && color == Color.gray) {
						grayNum++;
						gray16 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black16 == true && color == Color.black) {
						blackNum++;
						black16 = false;
					}

				} else if (149 < CheckPoint.x && CheckPoint.x < 207) {
					if (red17 == true && color == Color.red) {
						redNum++;
						red17 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue17 == true && color == Color.blue) {
						blueNum++;
						blue17 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray17 == true && color == Color.gray) {
						grayNum++;
						gray17 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black17 == true && color == Color.black) {
						blackNum++;
						black17 = false;
					}

				} else if (211 < CheckPoint.x && CheckPoint.x < 269) {
					if (red18 == true && color == Color.red) {
						redNum++;
						red18 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue18 == true && color == Color.blue) {
						blueNum++;
						blue18 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray18 == true && color == Color.gray) {
						grayNum++;
						gray18 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black18 == true && color == Color.black) {
						blackNum++;
						black18 = false;
					}

				} else if (272 < CheckPoint.x && CheckPoint.x < 313) {
					if (red19 == true && color == Color.red) {
						redNum++;
						red19 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue19 == true && color == Color.blue) {
						blueNum++;
						blue19 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray19 == true && color == Color.gray) {
						grayNum2++;
						gray19 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black19 == true && color == Color.black) {
						blackNum++;
						black19 = false;
					}
				} else if (315 < CheckPoint.x && CheckPoint.x < 355) {
					if (red20 == true && color == Color.red) {
						redNum++;
						red20 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue20 == true && color == Color.blue) {
						blueNum++;
						blue20 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray20 == true && color == Color.gray) {
						grayNum2++;
						gray20 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black20 == true && color == Color.black) {
						blackNum++;
						black20 = false;
					}
				} else if (358 < CheckPoint.x && CheckPoint.x < 398) {
					if (red21 == true && color == Color.red) {
						redNum++;
						red21 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue21 == true && color == Color.blue) {
						blueNum++;
						blue21 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray21 == true && color == Color.gray) {
						grayNum2++;
						gray21 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black21 == true && color == Color.black) {
						blackNum++;
						black21 = false;
					}
				} else if (402 < CheckPoint.x && CheckPoint.x < 439) {
					if (red22 == true && color == Color.red) {
						redNum++;
						red22 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}

					if (blue22 == true && color == Color.blue) {
						blueNum++;
						blue22 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray22 == true && color == Color.gray) {
						grayNum2++;
						gray22 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black22 == true && color == Color.black) {
						blackNum++;
						black22 = false;
					}
				} else if (444 < CheckPoint.x && CheckPoint.x < 483) {
					if (red23 == true && color == Color.red) {
						redNum++;
						red23 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue23 == true && color == Color.blue) {
						blueNum++;
						blue23 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray23 == true && color == Color.gray) {
						grayNum2++;
						gray23 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black23 == true && color == Color.black) {
						blackNum++;
						black23 = false;
					}
				} else if (488 < CheckPoint.x && CheckPoint.x < 529) {
					if (red24 == true && color == Color.red) {
						redNum++;
						red24 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue24 == true && color == Color.blue) {
						blueNum++;
						blue24 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray24 == true && color == Color.gray) {
						grayNum2++;
						gray24 = false;
						info_losePermanentTooth_front.setText(grayNum2+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black24 == true && color == Color.black) {
						blackNum++;
						black24 = false;
					}
				} else if (531 < CheckPoint.x && CheckPoint.x < 589) {
					if (red25 == true && color == Color.red) {
						redNum++;
						red25 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue25 == true && color == Color.blue) {
						blueNum++;
						blue25 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray25 == true && color == Color.gray) {
						grayNum++;
						gray25 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black25 == true && color == Color.black) {
						blackNum++;
						black25 = false;
					}
				} else if (592 < CheckPoint.x && CheckPoint.x < 650) {
					if (red26 == true && color == Color.red) {
						redNum++;
						red26 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue26 == true && color == Color.blue) {
						blueNum++;
						blue26 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray26 == true && color == Color.gray) {
						grayNum++;
						gray26 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black26 == true && color == Color.black) {
						blackNum++;
						black26 = false;
					}
				} else if (655 < CheckPoint.x && CheckPoint.x < 711) {
					if (red27 == true && color == Color.red) {
						redNum++;
						red27 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue27 == true && color == Color.blue) {
						blueNum++;
						blue27 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray27 == true && color == Color.gray) {
						grayNum++;
						gray27 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black27 == true && color == Color.black) {
						blackNum++;
						black27 = false;
					}

				} else if (713 < CheckPoint.x && CheckPoint.x < 772) {
					if (red28 == true && color == Color.red) {
						redNum++;
						red28 = false;
						info_leaving.setText(redNum+"");
						invalidate();
					}
					if (blue28 == true && color == Color.blue) {
						blueNum++;
						blue28 = false;
						info_treatment.setText(blueNum+"");
						invalidate();
					}
					if (gray28 == true && color == Color.gray) {
						grayNum++;
						gray28 = false;
						info_losePermanentTooth_back.setText(grayNum+"");
						info_permanentTooth.setText((Integer.parseInt(info_permanentTooth.getText())-1)+"");
						invalidate();
					}
					if (black28 == true && color == Color.black) {
						blackNum++;
						black28 = false;
					}
				}
			}
		}
	}

	private void floodFill(BufferedImage bitmap, Point fillStartPoint,
			Color targetColor, Color replacementColor) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(fillStartPoint);

		if (bitmap.getRGB(fillStartPoint.x, fillStartPoint.y) == Color.black
				.getRGB()) {
			targetColor = Color.black;
			replacementColor = Color.white;
		}
		if (bitmap.getRGB(fillStartPoint.x, fillStartPoint.y) == Color.gray
				.getRGB()) {
			targetColor = Color.gray;
			replacementColor = Color.white;
		}
		if (bitmap.getRGB(fillStartPoint.x, fillStartPoint.y) == Color.blue
				.getRGB()) {
			targetColor = Color.blue;
			replacementColor = Color.white;
		}
		if (bitmap.getRGB(fillStartPoint.x, fillStartPoint.y) == Color.red
				.getRGB()) {
			targetColor = Color.red;
			replacementColor = Color.white;
		}

		while (queue.size() > 0) {
			Point node = queue.poll();

			if (bitmap.getRGB(node.x, node.y) != targetColor.getRGB())
				continue;

			Point westNode = node;
			Point eastNode = new Point(node.x + 1, node.y);

			// ���ϵ� ��������
			Point southNode = node;
			Point northNode = new Point(node.x, node.y + 1);

			if (replacementColor == Color.gray) {
				while ((westNode.x > 0)
						&& ((bitmap.getRGB(westNode.x, westNode.y) == targetColor
								.getRGB()) || bitmap.getRGB(westNode.x,
								westNode.y) == Color.black.getRGB())) {
					if (bitmap.getRGB(westNode.x, westNode.y) == targetColor
							.getRGB()) {
						expandToUpDownNode(queue, bitmap, westNode,
								targetColor, replacementColor);
						westNode.x--;
					}
					if (bitmap.getRGB(westNode.x, westNode.y) == Color.black
							.getRGB()) {
						expandToUpDownNode(queue, bitmap, westNode,
								Color.black, replacementColor);
						westNode.x--;
					}
				}

				while ((southNode.y > 0)
						&& ((bitmap.getRGB(southNode.x, southNode.y) == targetColor
								.getRGB()) || bitmap.getRGB(southNode.x,
								southNode.y) == Color.black.getRGB())) {
					if (bitmap.getRGB(southNode.x, southNode.y) == targetColor
							.getRGB()) {
						expandToRightLeftNode(queue, bitmap, southNode,
								targetColor, replacementColor);
						southNode.y--;
					}
					if (bitmap.getRGB(southNode.x, southNode.y) == Color.black
							.getRGB()) {
						expandToRightLeftNode(queue, bitmap, southNode,
								Color.black, replacementColor);
						southNode.y--;
					}
				}

				while ((eastNode.x < bitmap.getWidth())
						&& ((bitmap.getRGB(eastNode.x, eastNode.y) == targetColor
								.getRGB()) || bitmap.getRGB(eastNode.x,
								eastNode.y) == Color.black.getRGB())) {
					if (bitmap.getRGB(eastNode.x, eastNode.y) == targetColor
							.getRGB()) {
						expandToUpDownNode(queue, bitmap, eastNode,
								targetColor, replacementColor);
						eastNode.x++;
					}
					if (bitmap.getRGB(eastNode.x, eastNode.y) == Color.black
							.getRGB()) {
						expandToUpDownNode(queue, bitmap, eastNode,
								Color.black, replacementColor);
						eastNode.x++;
					}
				}

				while ((northNode.y < bitmap.getHeight())
						&& ((bitmap.getRGB(northNode.x, northNode.y) == targetColor
								.getRGB()) || bitmap.getRGB(northNode.x,
								northNode.y) == Color.black.getRGB())) {
					if (bitmap.getRGB(northNode.x, northNode.y) == targetColor
							.getRGB()) {
						expandToRightLeftNode(queue, bitmap, northNode,
								targetColor, replacementColor);
						northNode.y++;
					}
					if (bitmap.getRGB(northNode.x, northNode.y) == Color.black
							.getRGB()) {
						expandToRightLeftNode(queue, bitmap, northNode,
								Color.black, replacementColor);
						northNode.y++;
					}
				}

			} else {
				while ((westNode.x > 0)
						&& (bitmap.getRGB(westNode.x, westNode.y) == targetColor
								.getRGB())) {
					expandToUpDownNode(queue, bitmap, westNode, targetColor,
							replacementColor);
					westNode.x--;
				}

				// ����
				while ((eastNode.x < bitmap.getWidth())
						&& (bitmap.getRGB(eastNode.x, eastNode.y) == targetColor
								.getRGB())) {
					expandToUpDownNode(queue, bitmap, eastNode, targetColor,
							replacementColor);
					eastNode.x++;
				}

			}
		}
	}

	private void expandToUpDownNode(Queue<Point> pointQueue,
			BufferedImage bitmap, Point node, Color targetColor,
			Color replacementColor) {
		bitmap.setRGB(node.x, node.y, replacementColor.getRGB());
		if ((node.y > 0)
				&& (bitmap.getRGB(node.x, node.y - 1) == targetColor.getRGB()))
			pointQueue.add(new Point(node.x, node.y - 1));
		if ((node.y < bitmap.getHeight())
				&& (bitmap.getRGB(node.x, node.y + 1) == targetColor.getRGB()))
			pointQueue.add(new Point(node.x, node.y + 1));
	}

	private void expandToRightLeftNode(Queue<Point> pointQueue,
			BufferedImage bitmap, Point node, Color targetColor,
			Color replacementColor) {
		bitmap.setRGB(node.x, node.y, replacementColor.getRGB());
		if ((node.x > 0)
				&& (bitmap.getRGB(node.x - 1, node.y) == targetColor.getRGB()))
			pointQueue.add(new Point(node.x - 1, node.y));
		if ((node.x < bitmap.getWidth())
				&& (bitmap.getRGB(node.x + 1, node.y) == targetColor.getRGB()))
			pointQueue.add(new Point(node.x + 1, node.y));
	}

	// ������� amyhee

	// amyhee ������ư�̺�Ʈ
	ActionListener radioButtonActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("��ġ")) {
				color = Color.red;

			} else if (e.getActionCommand().equals("����")) {
				color = Color.blue;

			} else if (e.getActionCommand().equals("��ġ")) {
				color = Color.gray;

			} else if (e.getActionCommand().equals("����ġġ��")) {
				color = Color.black;
			}
		}
	};

	ImageIcon logoImage;
	ImageIcon backImage;

	public JPanel SearchTab() {
		URL backURL = getClass().getClassLoader().getResource("back.png");
		backImage = new ImageIcon(backURL);
		URL logoURL = getClass().getClassLoader().getResource("logo.png");
		logoImage = new ImageIcon(logoURL);
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(backImage.getImage(), 0, 0, this.getWidth(),
						this.getHeight(), this);
				g.drawImage(logoImage.getImage(), 100, 60, this);
				setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
				super.paintComponent(g);
			}
		};
		
		JLabel image1 = new JLabel(backImage);
		JLabel image2 = new JLabel(new ImageIcon(logoURL));
		
		JPanel mainPanel = new JPanel();
		
		JButton newBtn = new JButton("�����ۼ�");
		newBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jtp.setSelectedIndex(1);
			}
		});
		
		JButton alreadyBtn = new JButton("�ҷ�����");
		alreadyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Excel Files", // ���� �̸��� â�� ��µ� ���ڿ�
						"xls"); // ���� ���ͷ� ���Ǵ� Ȯ����. *.xml �� ������
				chooser.setFileFilter(filter); // ���� ���̾�α׿� ���� ���� ����
				chooser.setMultiSelectionEnabled(false);//���� ���� �Ұ�

				// ���� ���̾�α� ���
				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) { // ����ڰ� â�� ������ �ݾҰų� ���
															// ��ư�� ���� ���
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�", "���",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				// ����ڰ� ������ �����ϰ� "����" ��ư�� ���� ���
				String readFilePath = chooser.getSelectedFile().getPath(); // ���� ��θ��� �˾ƿ´�.				
				ShowPatientInfo_K showPatientInfo = new ShowPatientInfo_K(readFilePath);
			}
		});
//		alreadyBtn.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});

		mainPanel.setLayout(new GridBagLayout());
//		mainPanel.add(image1, new GridBagConstraints(0, 0, 1, 1, 0, 0,
//				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
//						2, 2, 2, 2), 0, 0));
//		mainPanel.add(image2, new GridBagConstraints(1, 0, 3, 1, 0, 0,
//				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
//						2, 2, 2, 2), 0, 0));
		mainPanel.add(image2, new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		mainPanel.add(newBtn, new GridBagConstraints(1, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		mainPanel.add(alreadyBtn, new GridBagConstraints(2, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));

		return mainPanel;
	}

	public ScrollPane PatientInfo() {
		scroll = new ScrollPane();
		jp = new JPanel();
		infoPanel = new JPanel();
		JPanel surveyPanel = new JPanel();

		TitledBorder TB = new TitledBorder("ȸ������");
		TB.setTitleJustification(TitledBorder.CENTER);
		TitledBorder TB2 = new TitledBorder("����");
		TB2.setTitleJustification(TitledBorder.CENTER);
		infoPanel.setBorder(TB);
		surveyPanel.setBorder(TB2);

		JLabel CN = new JLabel("��Ʈ ��ȣ   ");
		CNF = new JTextField(5);

		JLabel Name = new JLabel("�̸�   ");
		NF = new JTextField(10);

		JLabel Birth = new JLabel("������� (��/��/��)  ");
		YF = new JTextField(6);
		MF = new JTextField(6);
		DF = new JTextField(6);
		JPanel pBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pBirth.add(YF);
		pBirth.add(new JLabel("/"));
		pBirth.add(MF);
		pBirth.add(new JLabel("/"));
		pBirth.add(DF);

		JLabel tel = new JLabel("��ȭ   ");
		JPanel pTel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		T1 = new JTextField(6);
		T2 = new JTextField(6);
		T3 = new JTextField(6);
		pTel.add(T1);
		pTel.add(new JLabel("-"));
		pTel.add(T2);
		pTel.add(new JLabel("-"));
		pTel.add(T3);

		JLabel Gender = new JLabel("����   ");
		JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rbMan = new JRadioButton("����", true);
		rbWoman = new JRadioButton("����", true);
		group = new ButtonGroup();
		group.add(rbMan);
		group.add(rbWoman);
		pGender.add(rbMan);
		pGender.add(rbWoman);

		JLabel Pur = new JLabel("���� ����   ");
		PA = new JTextArea(8, 25);

		JPanel pC = new JPanel();
		JLabel CL = new JLabel("Ȯ���� �����ø� ������ ���۵˴ϴ�.");
		btnInsert = new JButton("Ȯ��");
		pC.add(CL);
		pC.add(btnInsert);

		// ��ġ ����!
		infoPanel.setLayout(new GridBagLayout());
		// ��Ʈ��ȣȣ
		infoPanel.add(CN, new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		infoPanel.add(CNF, new GridBagConstraints(1, 0, 3, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		// �̸�
		infoPanel.add(Name, new GridBagConstraints(0, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		infoPanel.add(NF, new GridBagConstraints(1, 1, 3, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));

		// �������
		infoPanel.add(Birth, new GridBagConstraints(0, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		infoPanel.add(pBirth, new GridBagConstraints(1, 2, 3, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));

		// ��ȭ
		infoPanel.add(tel, new GridBagConstraints(0, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		infoPanel.add(pTel, new GridBagConstraints(1, 3, 3, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));

		// ����
		infoPanel.add(Gender, new GridBagConstraints(0, 4, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		infoPanel.add(pGender, new GridBagConstraints(1, 4, 3, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));

		// ��������
		infoPanel.add(Pur, new GridBagConstraints(0, 5, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		infoPanel.add(PA, new GridBagConstraints(1, 5, 3, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));

		// Ȯ��
		infoPanel.add(pC, new GridBagConstraints(1, 6, 4, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));

		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Age_check = Integer.parseInt(YF.getText());
				if (YF.getText().length() < 1 || Age_check > 2014
						|| Age_check < 1900) {
					JOptionPane.showMessageDialog(null,
							"Please enter your birth year!");
				} else {
					chart_num = CNF.getText();
					patientName = NF.getText();
					gender = getSelectedButtonText(group);

					// ���ɱ� �� ��������
					String Age = YF.getText();
					goSurvey(Age);
					infoPanel.getRootPane().validate();
				}
				infoPanel.getRootPane().validate();

			}
		});

		jp.add(infoPanel);
		scroll.add(jp);

		return scroll;
	}

	public int AgeCount(String age1) {
		int age = Integer.parseInt(age1);
		Calendar date = Calendar.getInstance();
		sendAge = (date.get(Calendar.YEAR) - age) + 1;
		return sendAge;
	}

	public JPanel goSurvey(String age1) {
		pS = new JPanel();
		int Age = AgeCount(age1);
		// ������
		if (0 <= Age && Age < 6) {

			TitledBorder TB = new TitledBorder("�����Ʊ� ��������");

			TB.setTitleJustification(TitledBorder.CENTER);

			JLabel j1 = new JLabel("1. ���� �Ϸ��� �� ���� Ƚ���� ?");
			JPanel pN1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JRadioButton r1_1 = new JRadioButton("������", false);
			JRadioButton r1_2 = new JRadioButton("1ȸ ����", false);
			JRadioButton r1_3 = new JRadioButton("2ȸ ����", false);
			JRadioButton r1_4 = new JRadioButton("3ȸ ����", false);
			JRadioButton r1_5 = new JRadioButton("4ȸ �̻�", false);
			
			group1 = new ButtonGroup();
			group1.add(r1_1);
			group1.add(r1_2);
			group1.add(r1_3);
			group1.add(r1_4);
			group1.add(r1_5);

			pN1.add(r1_1);
			pN1.add(r1_2);
			pN1.add(r1_3);
			pN1.add(r1_4);
			pN1.add(r1_5);

			JLabel j2 = new JLabel("2. ���� �Ϸ� �̸� ���� Ƚ���� ?");
			JPanel pN2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JRadioButton r2_1 = new JRadioButton("3ȸ", false);
			JRadioButton r2_2 = new JRadioButton("2ȸ", false);
			JRadioButton r2_3 = new JRadioButton("1ȸ", false);
			JRadioButton r2_4 = new JRadioButton("�ȴ۾���", false);

			group2 = new ButtonGroup();
			group2.add(r2_1);
			group2.add(r2_2);
			group2.add(r2_3);
			group2.add(r2_4);

			pN2.add(r2_1);
			pN2.add(r2_2);
			pN2.add(r2_3);
			pN2.add(r2_4);

			JLabel j3 = new JLabel("3. ������ ��� ���ı��� �� ���� �� �ִ°� ?");
			JPanel pN3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JRadioButton r3_1 = new JRadioButton("�κ�, ��", false);
			JRadioButton r3_2 = new JRadioButton("��, ���", false);
			JRadioButton r3_3 = new JRadioButton("��ġ, ���", false);
			JRadioButton r3_4 = new JRadioButton("����(����)", false);
			JRadioButton r3_5 = new JRadioButton("������¡��, ����", false);

			group3 = new ButtonGroup();
			group3.add(r3_1);
			group3.add(r3_2);
			group3.add(r3_3);
			group3.add(r3_4);
			group3.add(r3_5);

			pN3.add(r3_1);
			pN3.add(r3_2);
			pN3.add(r3_3);
			pN3.add(r3_4);
			pN3.add(r3_5);

			JLabel j4 = new JLabel("4. ���� �ϳⰣ ġ�� �湮 �� ���� �ִ°� ?");
			JPanel pN4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JRadioButton r4_1 = new JRadioButton("2ȸ �̻�", false);
			JRadioButton r4_2 = new JRadioButton("1ȸ", false);
			JRadioButton r4_3 = new JRadioButton("�� �� ����", false);

			group4 = new ButtonGroup();
			group4.add(r4_1);
			group4.add(r4_2);
			group4.add(r4_3);

			pN4.add(r4_1);
			pN4.add(r4_2);
			pN4.add(r4_3);

			JPanel pSb = new JPanel();
			btnSurvey = new JButton("����");
			pSb.add(btnSurvey);

			btnSurvey.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bean.setChart_num(CNF.getText());
					bean.setName(NF.getText());
					bean.setGender(gender);
					bean.setSugar_frequency(getSelectedButtonText(group1));
					bean.setBrush_num(getSelectedButtonText(group2));
					bean.setChew_food(getSelectedButtonText(group3));
					bean.setVisit(getSelectedButtonText(group4));

					tap2 = new ScrollPane();
					jtp.setSelectedIndex(2);
				}
			});

			pS.setBorder(TB);
			pS.setLayout(new GridBagLayout());
			pS.add(j1, new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					new Insets(6, 6, 0, 0), 0, 0));
			pS.add(pN1, new GridBagConstraints(0, 1, 1, 1, 0, 0,
					GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					new Insets(6, 6, 0, 0), 0, 0));
			pS.add(j2, new GridBagConstraints(0, 2, 1, 1, 0, 0,
					GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					new Insets(6, 6, 0, 0), 0, 0));
			pS.add(pN2, new GridBagConstraints(0, 3, 1, 1, 0, 0,
					GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					new Insets(6, 6, 0, 0), 0, 0));
			pS.add(j3, new GridBagConstraints(0, 4, 1, 1, 0, 0,
					GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					new Insets(6, 6, 0, 0), 0, 0));
			pS.add(pN3, new GridBagConstraints(0, 5, 1, 1, 0, 0,
					GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					new Insets(6, 6, 0, 0), 0, 0));
			pS.add(j4, new GridBagConstraints(0, 6, 1, 1, 0, 0,
					GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					new Insets(6, 6, 0, 0), 0, 0));
			pS.add(pN4, new GridBagConstraints(0, 7, 1, 1, 0, 0,
					GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
					new Insets(6, 6, 0, 0), 0, 0));
			pS.add(pSb, new GridBagConstraints(0, 8, 1, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
					new Insets(6, 6, 0, 0), 0, 0));
			jp.add(pS);
		}

		// �Ƶ�
		else if (6 <= Age && Age < 12) {

			TitledBorder TB = new TitledBorder("�Ƶ��� ��������");
			TB.setTitleJustification(TitledBorder.CENTER);

			// ������������
						JLabel j1 = new JLabel("1. ���� �Ϸ� �̸� ���� Ƚ���� ?");
						JPanel pN1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
						JRadioButton r1_1 = new JRadioButton("2ȸ �̻�", false);
						JRadioButton r1_2 = new JRadioButton("1ȸ", false);
						JRadioButton r1_3 = new JRadioButton("�ȴ۾���", false);


			group1 = new ButtonGroup();
			group1.add(r1_1);
			group1.add(r1_2);
			group1.add(r1_3);

			pN1.add(r1_1);
			pN1.add(r1_2);
			pN1.add(r1_3);

			JLabel j2 = new JLabel("2. �̴۱� �ñ�� ?");
			JPanel pN2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JRadioButton r2_1 = new JRadioButton("�ַ� �� ��", false);
			JRadioButton r2_2 = new JRadioButton("���� ����, ���� ����", false);
			JRadioButton r2_3 = new JRadioButton("�ַ� �� ��", false);
			group2 = new ButtonGroup();

			group2.add(r2_1);
			group2.add(r2_2);
			group2.add(r2_3);

			pN2.add(r2_1);
			pN2.add(r2_2);
			pN2.add(r2_3);

			JLabel j3 = new JLabel("3. ���� �� ���� �󵵴� ?");
			JPanel pN3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JRadioButton r3_1 = new JRadioButton("������");
			JRadioButton r3_2 = new JRadioButton("1ȸ ����");
			JRadioButton r3_3 = new JRadioButton("2ȸ ����");
			JRadioButton r3_4 = new JRadioButton("3ȸ ����");
			JRadioButton r3_5 = new JRadioButton("4ȸ �̻�");

			group3 = new ButtonGroup();
			group3.add(r3_1);
			group3.add(r3_2);
			group3.add(r3_3);
			group3.add(r3_4);
			group3.add(r3_5);

			pN3.add(r3_1);
			pN3.add(r3_2);
			pN3.add(r3_3);
			pN3.add(r3_4);
			pN3.add(r3_5);

			JLabel j4 = new JLabel("4. ������ ��� ���ı��� �� ���� �� �ִ°� ?");
			JPanel pN4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JRadioButton r4_1 = new JRadioButton("�κ�, ��", false);
			JRadioButton r4_2 = new JRadioButton("��, ���", false);
			JRadioButton r4_3 = new JRadioButton("��ġ, ���", false);
			JRadioButton r4_4 = new JRadioButton("����(����)", false);
			JRadioButton r4_5 = new JRadioButton("������¡��, ����", false);

			group4 = new ButtonGroup();
			group4.add(r4_1);
			group4.add(r4_2);
			group4.add(r4_3);
			group4.add(r4_4);
			group4.add(r4_5);
			pN4.add(r4_1);
			pN4.add(r4_2);
			pN4.add(r4_3);
			pN4.add(r4_4);
			pN4.add(r4_5);

			JLabel j5 = new JLabel("5. ������ȯ ���� ");
			JPanel pN5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JRadioButton r5_1 = new JRadioButton("����", false);
			JRadioButton r5_2 = new JRadioButton("������ ġ�� ��", false);
			JRadioButton r5_3 = new JRadioButton("������ �� ġ��", false);
			
			group5 = new ButtonGroup();
			group5.add(r5_1);
			group5.add(r5_2);
			group5.add(r5_3);
			pN5.add(r5_1);
			pN5.add(r5_2);
			pN5.add(r5_3);

			JLabel j6 = new JLabel("6. ������ȯ ���� ");
			JPanel pN6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JRadioButton r6_1 = new JRadioButton("����", false);
			JRadioButton r6_2 = new JRadioButton("�ټ� ����(��)", false);
			JRadioButton r6_3 = new JRadioButton("�ɰ��� ����(�索)", false);

			group6 = new ButtonGroup();
			group6.add(r6_1);
			group6.add(r6_2);
			group6.add(r6_3);

			pN6.add(r6_1);
			pN6.add(r6_2);
			pN6.add(r6_3);

			JLabel j7 = new JLabel("7. ���� �ϳⰣ ����ġ������ �޴� ���� : �Ҽҵ���/����Ʈ ");
			JPanel pN7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JRadioButton r7_1 = new JRadioButton("2���� ���", false);
			JRadioButton r7_2 = new JRadioButton("1����", false);
			JRadioButton r7_3 = new JRadioButton("�� �� ����", false);

			group7 = new ButtonGroup();
			group7.add(r7_1);
			group7.add(r7_2);
			group7.add(r7_3);

			pN7.add(r7_1);
			pN7.add(r7_2);
			pN7.add(r7_3);

			JPanel pSb = new JPanel();
			btnSurvey = new JButton("����");
			pSb.add(btnSurvey);

			btnSurvey.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bean.setChart_num(CNF.getText());
					bean.setName(NF.getText());
					bean.setGender(gender);
					bean.setBrush_num(getSelectedButtonText(group1));
					bean.setBrush_time(getSelectedButtonText(group2));
					bean.setSugar_frequency(getSelectedButtonText(group3));
					bean.setChew_food(getSelectedButtonText(group4));
					bean.setDisease_num(getSelectedButtonText(group5));
					bean.setDisease_kind(getSelectedButtonText(group6));
					bean.setPrevention_visit(getSelectedButtonText(group7));

					jtp.setSelectedIndex(2);
				}
			});

			pS.setBorder(TB);

			pS.setLayout(new GridBagLayout());

			pS.add(j1, new GridBagConstraints(0, 0, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN1, new GridBagConstraints(0, 1, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j2, new GridBagConstraints(0, 2, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN2, new GridBagConstraints(0, 3, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j3, new GridBagConstraints(0, 4, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN3, new GridBagConstraints(0, 5, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j4, new GridBagConstraints(0, 6, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN4, new GridBagConstraints(0, 7, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j5, new GridBagConstraints(0, 8, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN5, new GridBagConstraints(0, 9, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j6, new GridBagConstraints(0, 10, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN6, new GridBagConstraints(0, 11, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j7, new GridBagConstraints(0, 12, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN7, new GridBagConstraints(0, 13, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pSb, new GridBagConstraints(0, 16, 1, 1, 0, 0,

			GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			jp.add(pS);

		}
		// û�ҳ�

		else if (12 <= Age && Age < 20) {

			TitledBorder TB = new TitledBorder("û�ҳ�� ��������");
			TB.setTitleJustification(TitledBorder.CENTER);

			// ������������

						JLabel j1 = new JLabel("1. ���� �Ϸ� �̸� ���� Ƚ���� ?");

						JPanel pN1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

						JRadioButton r1_1 = new JRadioButton("2ȸ �̻�", false);

						JRadioButton r1_2 = new JRadioButton("1ȸ", false);

						JRadioButton r1_3 = new JRadioButton("�ȴ۾���", false);

			group1 = new ButtonGroup();

			group1.add(r1_1);

			group1.add(r1_2);

			group1.add(r1_3);

			// pN2.add(j2);

			pN1.add(r1_1);

			pN1.add(r1_2);

			pN1.add(r1_3);

			JLabel j2 = new JLabel("2. �� ���� �󵵴� ?");

			JPanel pN2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r2_1 = new JRadioButton("������");

			JRadioButton r2_2 = new JRadioButton("1ȸ ����");

			JRadioButton r2_3 = new JRadioButton("2ȸ ����");

			JRadioButton r2_4 = new JRadioButton("3ȸ ����");
			
			JRadioButton r2_5 = new JRadioButton("4ȸ �̻�");

			group2 = new ButtonGroup();

			group2.add(r2_1);

			group2.add(r2_2);

			group2.add(r2_3);

			group2.add(r2_4);

			group2.add(r2_5);

			pN2.add(r2_1);

			pN2.add(r2_2);

			pN2.add(r2_3);

			pN2.add(r2_4);

			pN2.add(r2_5);

			JLabel j4 = new JLabel("4. ���� ����ġ�� ���� ���� - �Ҽҵ���/����Ʈ/���̸�/�̴۱� ���� ");

			JPanel pN4 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r4_1 = new JRadioButton("3���� �̻�", false);

			JRadioButton r4_2 = new JRadioButton("2����", false);

			JRadioButton r4_3 = new JRadioButton("1����", false);

			JRadioButton r4_4 = new JRadioButton("����", false);

			group4 = new ButtonGroup();

			group4.add(r4_1);

			group4.add(r4_2);

			group4.add(r4_3);

			group4.add(r4_4);

			pN4.add(r4_1);

			pN4.add(r4_2);

			pN4.add(r4_3);

			pN4.add(r4_4);

			JLabel j5 = new JLabel("5. ���� ġ�� �湮 - ���� �ϳ� �� ����ġ�� ���� ���� ");

			JPanel pN5 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r5_1 = new JRadioButton("����", false);

			JRadioButton r5_2 = new JRadioButton("����", false);

			group5 = new ButtonGroup();

			group5.add(r5_1);

			group5.add(r5_2);

			pN5.add(r5_1);

			pN5.add(r5_2);

			
					JLabel j6 = new JLabel(

							"6. �������� ���� ���� - ���� �ϳ� �� ����/���� �������� ������ ���� �� ");

					JPanel pN6 = new JPanel(new FlowLayout(FlowLayout.LEFT));

					JRadioButton r6_1 = new JRadioButton("�ִ�", false);

					JRadioButton r6_2 = new JRadioButton("����", false);

			group6 = new ButtonGroup();

			group6.add(r6_1);

			group6.add(r6_2);

			pN6.add(r6_1);

			pN6.add(r6_2);

			JLabel j7 = new JLabel("7. ���Űǰ� ");

			JPanel pN7 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r7_1 = new JRadioButton("����", false);

			JRadioButton r7_2 = new JRadioButton("�ټ� ����(��)", false);

			JRadioButton r7_3 = new JRadioButton("�ɰ��� ����(�索)", false);
			group7 = new ButtonGroup();

			group7.add(r7_1);

			group7.add(r7_2);

			group7.add(r7_3);

			pN7.add(r7_1);

			pN7.add(r7_2);

			pN7.add(r7_3);

			JPanel pSb = new JPanel();

			btnSurvey = new JButton("����");

			pSb.add(btnSurvey);
			btnSurvey.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					bean.setChart_num(CNF.getText());
					bean.setName(NF.getText());
					bean.setGender(gender);
					bean.setBrush_num(getSelectedButtonText(group1));
					bean.setSugar_frequency(getSelectedButtonText(group2));
					bean.setPrevention_visit(getSelectedButtonText(group4));
					bean.setVisit(getSelectedButtonText(group5));
					bean.setLearn(getSelectedButtonText(group6));
					bean.setDisease_kind(getSelectedButtonText(group7));

					jtp.setSelectedIndex(2);
				}
			});

			pS.setBorder(TB);

			pS.setLayout(new GridBagLayout());

			pS.add(j1, new GridBagConstraints(0, 0, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN1, new GridBagConstraints(0, 1, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j2, new GridBagConstraints(0, 2, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN2, new GridBagConstraints(0, 3, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j4, new GridBagConstraints(0, 4, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN4, new GridBagConstraints(0, 5, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j5, new GridBagConstraints(0, 6, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN5, new GridBagConstraints(0, 7, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j6, new GridBagConstraints(0, 8, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN6, new GridBagConstraints(0, 9, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j7, new GridBagConstraints(0, 10, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN7, new GridBagConstraints(0, 11, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pSb, new GridBagConstraints(0, 12, 1, 1, 0, 0,

			GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			jp.add(pS);

		}
		// û��

		else if (20 <= Age && Age < 40) {


			TitledBorder TB = new TitledBorder("û��� ��������");

			TB.setTitleJustification(TitledBorder.CENTER);

			// ������������

			JLabel j1 = new JLabel("1. ���� �Ϸ� �̸� ���� Ƚ���� ?");

			JPanel pN1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r1_1 = new JRadioButton("2ȸ �̻�", false);

			JRadioButton r1_2 = new JRadioButton("1ȸ", false);

			JRadioButton r1_3 = new JRadioButton("�ȴ۾���", false);
			
			group1 = new ButtonGroup();

			group1.add(r1_1);

			group1.add(r1_2);

			group1.add(r1_3);

			// pN2.add(j2);

			pN1.add(r1_1);

			pN1.add(r1_2);

			pN1.add(r1_3);

			JLabel j2 = new JLabel("2. ���� �Ϸ� �� �� ���� �󵵴� ?");

			JPanel pN2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r2_1 = new JRadioButton("������");

			JRadioButton r2_2 = new JRadioButton("1ȸ ����");

			JRadioButton r2_3 = new JRadioButton("2ȸ �̻�");


			group2 = new ButtonGroup();

			group2.add(r2_1);

			group2.add(r2_2);

			group2.add(r2_3);

			pN2.add(r2_1);

			pN2.add(r2_2);

			pN2.add(r2_3);

			JLabel j3 = new JLabel("3. ���̸� ���� ���� ?");

			JPanel pN3 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r3_1 = new JRadioButton("���� 1�Ⱓ ���̸� ���� ���� ����", false);

			JRadioButton r3_2 = new JRadioButton("���� 2�Ⱓ ���̸� ���� ���� ����", false);

			JRadioButton r3_3 = new JRadioButton("���� 2�Ⱓ ���̸� ���� ���� ����", false);


			group3 = new ButtonGroup();

			group3.add(r3_1);

			group3.add(r3_2);

			group3.add(r3_3);

			pN3.add(r3_1);

			pN3.add(r3_2);

			pN3.add(r3_3);

			JLabel j4 = new JLabel(

					"4. ���� ����, ������ȯ - ������/�索/�ӽ�/��/������ȯ/��ȭ����ȯ/��Ÿ ������ȯ �߿� ");

			JPanel pN4 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r4_1 = new JRadioButton("����", false);

			JRadioButton r4_2 = new JRadioButton("1��", false);

			JRadioButton r4_3 = new JRadioButton("2��", false);

			JRadioButton r4_4 = new JRadioButton("3��", false);

			JRadioButton r4_5 = new JRadioButton("4�� �̻�", false);

			group4 = new ButtonGroup();

			group4.add(r4_1);

			group4.add(r4_2);

			group4.add(r4_3);

			group4.add(r4_4);

			group4.add(r4_5);

			pN4.add(r4_1);

			pN4.add(r4_2);

			pN4.add(r4_3);

			pN4.add(r4_4);

			pN4.add(r4_5);

			JLabel j5 = new JLabel("5. ���� ���� �� �� ���� �� �ִ� ������ ���� ?");

			JPanel pN5 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r5_1 = new JRadioButton("�κ�, ��", false);

			JRadioButton r5_2 = new JRadioButton("��, ���", false);

			JRadioButton r5_3 = new JRadioButton("��ġ, ���", false);

			JRadioButton r5_4 = new JRadioButton("����(����)", false);

			JRadioButton r5_5 = new JRadioButton("������¡��, ����", false);

			group5 = new ButtonGroup();

			group5.add(r5_1);

			group5.add(r5_2);

			group5.add(r5_3);

			group5.add(r5_4);

			group5.add(r5_5);

			pN5.add(r5_1);

			pN5.add(r5_2);

			pN5.add(r5_3);

			pN5.add(r5_4);

			pN5.add(r5_5);

			JLabel j6 = new JLabel("6. ���� ġ�� �湮 ");

			JPanel pN6 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r6_1 = new JRadioButton("���� 1�Ⱓ 2ȸ �湮", false);

			JRadioButton r6_2 = new JRadioButton("���� 1�Ⱓ 1ȸ �湮", false);

			JRadioButton r6_3 = new JRadioButton("���� ���� ����", false);

			group6 = new ButtonGroup();

			group6.add(r6_1);

			group6.add(r6_2);

			group6.add(r6_3);

			pN6.add(r6_1);

			pN6.add(r6_2);

			pN6.add(r6_3);

			JLabel j7 = new JLabel(

					"7. ���� �� ����ϰ� �ִ� ���� ? - ġ��/ġ��ĩ��/÷��ĩ��/�κ�ĩ��/�ո�������/�������/��ġ�� ");

			JPanel pN7 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r7_1 = new JRadioButton("2���� �̻� ���", false);

			JRadioButton r7_2 = new JRadioButton("1���� ���", false);

			JRadioButton r7_3 = new JRadioButton("������� ����", false);
			
			group7 = new ButtonGroup();

			group7.add(r7_1);

			group7.add(r7_2);

			group7.add(r7_3);

			pN7.add(r7_1);

			pN7.add(r7_2);

			pN7.add(r7_3);

			JLabel j8 = new JLabel("8. ����/���� (���ִ� �� 2-3�� �̻��� ����) ");

			JPanel pN8 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r8_1 = new JRadioButton("�Ѵ� �� ��", false);

			JRadioButton r8_2 = new JRadioButton("�� �� �ϳ�", false);

			JRadioButton r8_3 = new JRadioButton("�Ѵ� ��", false);

			group8 = new ButtonGroup();

			group8.add(r8_1);

			group8.add(r8_2);

			group8.add(r8_3);

			pN8.add(r8_1);

			pN8.add(r8_2);

			pN8.add(r8_3);

			JLabel j9 = new JLabel("9. �ӽſ��� ");

			JPanel pN9 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r9_1 = new JRadioButton("�ӽ���", false);

			JRadioButton r9_2 = new JRadioButton("�ӽ� �ƴ�", false);

			group9 = new ButtonGroup();

			group9.add(r9_1);

			group9.add(r9_2);

			pN9.add(r9_1);

			pN9.add(r9_2);

			JPanel pSb = new JPanel();

			btnSurvey = new JButton("����");

			pSb.add(btnSurvey);

			btnSurvey.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					bean.setChart_num(CNF.getText());
					bean.setName(NF.getText());
					bean.setGender(gender);
					bean.setBrush_num(getSelectedButtonText(group1));
					bean.setSugar_frequency(getSelectedButtonText(group2));
					bean.setScaling(getSelectedButtonText(group3));
					bean.setDisease_num(getSelectedButtonText(group4));
					bean.setChew_food(getSelectedButtonText(group5));
					bean.setVisit(getSelectedButtonText(group6));
					bean.setCare_product(getSelectedButtonText(group7));
					bean.setDrink_and_smoke(getSelectedButtonText(group8));
					bean.setPregnancy(getSelectedButtonText(group9));

					jtp.setSelectedIndex(2);
				}
			});

			pS.setBorder(TB);

			pS.setLayout(new GridBagLayout());

			pS.add(j1, new GridBagConstraints(0, 0, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN1, new GridBagConstraints(0, 1, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j2, new GridBagConstraints(0, 2, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN2, new GridBagConstraints(0, 3, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j3, new GridBagConstraints(0, 4, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN3, new GridBagConstraints(0, 5, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j4, new GridBagConstraints(0, 6, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN4, new GridBagConstraints(0, 7, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j5, new GridBagConstraints(0, 8, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN5, new GridBagConstraints(0, 9, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j6, new GridBagConstraints(0, 10, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN6, new GridBagConstraints(0, 11, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j7, new GridBagConstraints(0, 12, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN7, new GridBagConstraints(0, 13, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j8, new GridBagConstraints(0, 14, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN8, new GridBagConstraints(0, 15, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			
			if(gender=="����"){
				pS.add(j9, new GridBagConstraints(0, 16, 1, 1, 0, 0,

						GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

						new Insets(6, 6, 0, 0), 0, 0));

				pS.add(pN9, new GridBagConstraints(0, 17, 1, 1, 0, 0,

						GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

						new Insets(6, 6, 0, 0), 0, 0));
			}

			pS.add(pSb, new GridBagConstraints(0, 18, 1, 1, 0, 0,

			GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			jp.add(pS);

		}

		// ���

		else if (40 <= Age && Age < 65) {

			TitledBorder TB = new TitledBorder("��� ��������");
			TB.setTitleJustification(TitledBorder.CENTER);

			// ������������
			JLabel j1 = new JLabel("1. ���� �Ϸ� �� �� ���� �󵵴� ?");

			JPanel pN1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r1_1 = new JRadioButton("������");

			JRadioButton r1_2 = new JRadioButton("1ȸ ����");

			JRadioButton r1_3 = new JRadioButton("2ȸ �̻�");
			
			group1 = new ButtonGroup();

			group1.add(r1_1);

			group1.add(r1_2);

			group1.add(r1_3);

			pN1.add(r1_1);

			pN1.add(r1_2);

			pN1.add(r1_3);

			JLabel j2 = new JLabel("2. �������� ?");

			JPanel pN2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r2_1 = new JRadioButton("����");

			JRadioButton r2_2 = new JRadioButton("�̾��� ����");

			JRadioButton r2_3 = new JRadioButton("�ɰ��� ����");

			group2 = new ButtonGroup();

			group2.add(r2_1);

			group2.add(r2_2);

			group2.add(r2_3);

			pN2.add(r2_1);

			pN2.add(r2_2);

			pN2.add(r2_3);

			JLabel j3 = new JLabel("3. ���̸� ���� ���� ?");

			JPanel pN3 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r3_1 = new JRadioButton("���� 1�Ⱓ ���̸� ���� ���� ����", false);

			JRadioButton r3_2 = new JRadioButton("���� 2�⳻�� ���� ���� ����", false);

			JRadioButton r3_3 = new JRadioButton("���� 2-3�� ���� ���̸� ���� ���� ���� ����",

					false);

			JRadioButton r3_4 = new JRadioButton("���� ���� ����", false);


			group3 = new ButtonGroup();

			group3.add(r3_1);

			group3.add(r3_2);

			group3.add(r3_3);

			group3.add(r3_4);

			pN3.add(r3_1);

			pN3.add(r3_2);

			pN3.add(r3_3);

			pN3.add(r3_4);

			JLabel j4 = new JLabel(

					"4. ���� ����, ������ȯ - ������/�索/�ӽ�/��/������ȯ/��ȭ����ȯ/��Ÿ ������ȯ �߿� ");

			JPanel pN4 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r4_1 = new JRadioButton("����", false);

			JRadioButton r4_2 = new JRadioButton("1��", false);

			JRadioButton r4_3 = new JRadioButton("2��", false);

			JRadioButton r4_4 = new JRadioButton("3��", false);

			JRadioButton r4_5 = new JRadioButton("4�� �̻�", false);

			group4 = new ButtonGroup();

			group4.add(r4_1);

			group4.add(r4_2);

			group4.add(r4_3);

			group4.add(r4_4);

			group4.add(r4_5);

			pN4.add(r4_1);

			pN4.add(r4_2);

			pN4.add(r4_3);

			pN4.add(r4_4);

			pN4.add(r4_5);

			JLabel j5 = new JLabel("5. ���� ���� �� �� ���� �� �ִ� ������ ���� ?");

			JPanel pN5 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r5_1 = new JRadioButton("�κ�, ��", false);

			JRadioButton r5_2 = new JRadioButton("��, ���", false);

			JRadioButton r5_3 = new JRadioButton("��ġ, ���", false);

			JRadioButton r5_4 = new JRadioButton("����(����)", false);

			JRadioButton r5_5 = new JRadioButton("������¡��, ����", false);


			group5 = new ButtonGroup();

			group5.add(r5_1);

			group5.add(r5_2);

			group5.add(r5_3);

			group5.add(r5_4);

			group5.add(r5_5);

			pN5.add(r5_1);

			pN5.add(r5_2);

			pN5.add(r5_3);

			pN5.add(r5_4);

			pN5.add(r5_5);

			JLabel j6 = new JLabel("6. ���� ���� - �� 2-3�� �̻� �����ϴ� �� ");

			JPanel pN6 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r6_1 = new JRadioButton("���� ��", false);

			JRadioButton r6_2 = new JRadioButton("�� ��", false);

			group6 = new ButtonGroup();

			group6.add(r6_1);

			group6.add(r6_2);

			pN6.add(r6_1);

			pN6.add(r6_2);


			JLabel j7 = new JLabel(

					"7. ���� �� ����ϰ� �ִ� ���� ? - ġ��/ġ��ĩ��/÷��ĩ��/�κ�ĩ��/�ո�������/�������/��ġ�� ");

			JPanel pN7 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r7_1 = new JRadioButton("2���� �̻� ���", false);

			JRadioButton r7_2 = new JRadioButton("1���� ���", false);

			JRadioButton r7_3 = new JRadioButton("������� ����", false);

			group7 = new ButtonGroup();

			group7.add(r7_1);

			group7.add(r7_2);

			group7.add(r7_3);

			pN7.add(r7_1);

			pN7.add(r7_2);

			pN7.add(r7_3);


			JLabel j8 = new JLabel("8. ���� ���� ");

			JPanel pN8 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r8_1 = new JRadioButton("�� ��", false);

			JRadioButton r8_2 = new JRadioButton("������", false);

			group8 = new ButtonGroup();

			group8.add(r8_1);

			group8.add(r8_2);

			pN8.add(r8_1);

			pN8.add(r8_2);

			JPanel pSb = new JPanel();

			btnSurvey = new JButton("����");
			pSb.add(btnSurvey);

			btnSurvey.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					bean.setChart_num(CNF.getText());
					bean.setName(NF.getText());
					bean.setGender(gender);
					bean.setSugar_frequency(getSelectedButtonText(group1));
					bean.setXerostomia(getSelectedButtonText(group2));
					bean.setScaling(getSelectedButtonText(group3));
					bean.setDisease_num(getSelectedButtonText(group4));
					bean.setChew_food(getSelectedButtonText(group5));
					bean.setDrinking(getSelectedButtonText(group6));
					bean.setCare_product(getSelectedButtonText(group7));
					bean.setSmoking(getSelectedButtonText(group8));

					jtp.setSelectedIndex(2);
				}
			});

			pS.setBorder(TB);

			pS.setLayout(new GridBagLayout());

			pS.add(j1, new GridBagConstraints(0, 0, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN1, new GridBagConstraints(0, 1, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j2, new GridBagConstraints(0, 2, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN2, new GridBagConstraints(0, 3, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j3, new GridBagConstraints(0, 4, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN3, new GridBagConstraints(0, 5, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j4, new GridBagConstraints(0, 6, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN4, new GridBagConstraints(0, 7, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j5, new GridBagConstraints(0, 8, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN5, new GridBagConstraints(0, 9, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j6, new GridBagConstraints(0, 10, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN6, new GridBagConstraints(0, 11, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j7, new GridBagConstraints(0, 12, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j7, new GridBagConstraints(0, 13, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN7, new GridBagConstraints(0, 14, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j8, new GridBagConstraints(0, 15, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN8, new GridBagConstraints(0, 16, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pSb, new GridBagConstraints(0, 17, 1, 1, 0, 0,

			GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			jp.add(pS);

		}
		// ���

		else if (Age > 64) {

			TitledBorder TB = new TitledBorder("���� ��������");

			TB.setTitleJustification(TitledBorder.CENTER);

			// ������������

			JLabel j1 = new JLabel("1. ���� �Ϸ� �� �� ���� �󵵴� ?");

			JPanel pN1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r1_1 = new JRadioButton("������");

			JRadioButton r1_2 = new JRadioButton("1ȸ ����");

			JRadioButton r1_3 = new JRadioButton("2ȸ �̻�");
			group1 = new ButtonGroup();

			group1.add(r1_1);

			group1.add(r1_2);

			group1.add(r1_3);

			pN1.add(r1_1);

			pN1.add(r1_2);

			pN1.add(r1_3);


			JLabel j2 = new JLabel("2. ���� �Ϸ� �� �̴۱� Ƚ�� ?");

			JPanel pN2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r2_1 = new JRadioButton("2ȸ �̻�");

			JRadioButton r2_2 = new JRadioButton("1ȸ");

			JRadioButton r2_3 = new JRadioButton("�ȴ۾���");


			group2 = new ButtonGroup();

			group2.add(r2_1);

			group2.add(r2_2);

			group2.add(r2_3);

			pN2.add(r2_1);

			pN2.add(r2_2);

			pN2.add(r2_3);

			JLabel j3 = new JLabel("3. ���̸� ���� ���� ?");

			JPanel pN3 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r3_1 = new JRadioButton("���� 1�Ⱓ ���̸� ���� ���� ����", false);

			JRadioButton r3_2 = new JRadioButton("���� 2�⳻�� ���� ���� ����", false);

			JRadioButton r3_3 = new JRadioButton("���� 2-3�� ���� ���̸� ���� ���� ���� ����",

					false);

			JRadioButton r3_4 = new JRadioButton("���� ���� ����", false);

			group3 = new ButtonGroup();

			group3.add(r3_1);

			group3.add(r3_2);

			group3.add(r3_3);

			group3.add(r3_4);

			pN3.add(r3_1);

			pN3.add(r3_2);

			pN3.add(r3_3);

			pN3.add(r3_4);

			JLabel j4 = new JLabel(

					"4. ���� ����, ������ȯ - ������/�索/�ӽ�/��/������ȯ/��ȭ����ȯ/��Ÿ ������ȯ �߿� ");

			JPanel pN4 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r4_1 = new JRadioButton("����", false);

			JRadioButton r4_2 = new JRadioButton("1��", false);

			JRadioButton r4_3 = new JRadioButton("2��", false);

			JRadioButton r4_4 = new JRadioButton("3��", false);

			JRadioButton r4_5 = new JRadioButton("4�� �̻�", false);
			group4 = new ButtonGroup();

			group4.add(r4_1);

			group4.add(r4_2);

			group4.add(r4_3);

			group4.add(r4_4);

			group4.add(r4_5);

			pN4.add(r4_1);

			pN4.add(r4_2);

			pN4.add(r4_3);

			pN4.add(r4_4);

			pN4.add(r4_5);


			JLabel j5 = new JLabel("5. ���� ���� �� �� ���� �� �ִ� ������ ���� ?");

			JPanel pN5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
			JRadioButton r5_0 = new JRadioButton("��, ���Ʈ", false);

			JRadioButton r5_1 = new JRadioButton("�κ�", false);

			JRadioButton r5_2 = new JRadioButton("��, ���", false);

			JRadioButton r5_3 = new JRadioButton("��ġ, ���", false);

			JRadioButton r5_4 = new JRadioButton("����(����)", false);

			JRadioButton r5_5 = new JRadioButton("������¡��, ����", false);

			group5 = new ButtonGroup();

			group5.add(r5_0);

			group5.add(r5_1);

			group5.add(r5_2);

			group5.add(r5_3);

			group5.add(r5_4);

			group5.add(r5_5);

			pN5.add(r5_0);

			pN5.add(r5_1);

			pN5.add(r5_2);

			pN5.add(r5_3);

			pN5.add(r5_4);

			pN5.add(r5_5);


			JLabel j6 = new JLabel("6. ġ�ְ��� ���� ��ȯ ? - �索/��/������/��������/���� ��ȯ �� ");

			JPanel pN6 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r6_1 = new JRadioButton("����", false);

			JRadioButton r6_2 = new JRadioButton("1��", false);

			JRadioButton r6_3 = new JRadioButton("2�� �̻�", false);


			group6 = new ButtonGroup();

			group6.add(r6_1);

			group6.add(r6_2);

			group6.add(r6_3);

			pN6.add(r6_1);

			pN6.add(r6_2);

			pN6.add(r6_3);


			JLabel j7 = new JLabel("7. ���� ġ�� �湮 ���� ");

			JPanel pN7 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r7_1 = new JRadioButton("���� 1�� �̳��� ġ�� �湮 �� �� ����",

					false);

			JRadioButton r7_2 = new JRadioButton("���� 2�� ���� ġ�� �湮 �� �� ����", false);

			JRadioButton r7_3 = new JRadioButton("���� 2�Ⱓ ġ�� �湮�� �� ����", false);

			group7 = new ButtonGroup();

			group7.add(r7_1);

			group7.add(r7_2);

			group7.add(r7_3);

			pN7.add(r7_1);

			pN7.add(r7_2);

			pN7.add(r7_3);

			JLabel j8 = new JLabel("8. ���� ���� ");

			JPanel pN8 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r8_1 = new JRadioButton("�� ��", false);

			JRadioButton r8_2 = new JRadioButton("�ణ, �� 1-2ȸ", false);

			JRadioButton r8_3 = new JRadioButton("����, �� 3ȸ �̻�", false);


			group8 = new ButtonGroup();

			group8.add(r8_1);

			group8.add(r8_2);

			group8.add(r8_3);

			pN8.add(r8_1);

			pN8.add(r8_2);

			pN8.add(r8_3);


			JLabel j9 = new JLabel("9. ���� ���� ");

			JPanel pN9 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r9_1 = new JRadioButton("�� ��", false);

			JRadioButton r9_2 = new JRadioButton("����", false);

			JRadioButton r9_3 = new JRadioButton("����", false);

			group9 = new ButtonGroup();

			group9.add(r9_1);

			group9.add(r9_2);

			group9.add(r9_3);

			pN9.add(r9_1);

			pN9.add(r9_2);

			pN9.add(r9_3);

			JLabel j10 = new JLabel(

					"10. ���� �� ����ϴ� ���� ? - ġ��/ġ��ĩ��/�ո�������/Ʋ��ĩ��/������ġ��/�������/����ĩ�� ");

			JPanel pN10 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JRadioButton r10_1 = new JRadioButton("2���� �̻� ���", false);

			JRadioButton r10_2 = new JRadioButton("1���� ���", false);

			JRadioButton r10_3 = new JRadioButton("������� ����", false);


			group10 = new ButtonGroup();

			group10.add(r10_1);

			group10.add(r10_2);

			group10.add(r10_3);

			pN10.add(r10_1);

			pN10.add(r10_2);

			pN10.add(r10_3);

			JPanel pSb = new JPanel();

			btnSurvey = new JButton("����");

			pSb.add(btnSurvey);

			btnSurvey.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					bean.setChart_num(CNF.getText());
					bean.setName(NF.getText());
					bean.setGender(gender);
					bean.setSugar_frequency(getSelectedButtonText(group1));
					bean.setBrush_num(getSelectedButtonText(group2));
					bean.setScaling(getSelectedButtonText(group3));
					bean.setDisease_num(getSelectedButtonText(group4));
					bean.setChew_food(getSelectedButtonText(group5));
					bean.setVisit(getSelectedButtonText(group7));
					bean.setDrinking(getSelectedButtonText(group8));
					bean.setSmoking(getSelectedButtonText(group9));
					bean.setCare_product(getSelectedButtonText(group9));

					jtp.setSelectedIndex(2);
				}
			});

			pS.setBorder(TB);

			pS.setLayout(new GridBagLayout());

			pS.add(j1, new GridBagConstraints(0, 0, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN1, new GridBagConstraints(0, 1, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j2, new GridBagConstraints(0, 2, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN2, new GridBagConstraints(0, 3, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j3, new GridBagConstraints(0, 4, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN3, new GridBagConstraints(0, 5, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j4, new GridBagConstraints(0, 6, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN4, new GridBagConstraints(0, 7, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j5, new GridBagConstraints(0, 8, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN5, new GridBagConstraints(0, 9, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j6, new GridBagConstraints(0, 10, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN6, new GridBagConstraints(0, 11, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j7, new GridBagConstraints(0, 12, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN7, new GridBagConstraints(0, 13, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j8, new GridBagConstraints(0, 14, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN8, new GridBagConstraints(0, 15, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j9, new GridBagConstraints(0, 16, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN9, new GridBagConstraints(0, 17, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(j10, new GridBagConstraints(0, 18, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pN10, new GridBagConstraints(0, 19, 1, 1, 0, 0,

			GridBagConstraints.WEST, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			pS.add(pSb, new GridBagConstraints(0, 20, 1, 1, 0, 0,

			GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,

			new Insets(6, 6, 0, 0), 0, 0));

			jp.add(pS);

		}

		return pS;

	}

	public ScrollPane TeethPicture() {

		scroll = new ScrollPane();

		JPanel theethPanel = new JPanel();
		JPanel theethPicturePanel = new JPanel();
		TitledBorder TB = new TitledBorder("���� ����");
		TB.setTitleJustification(TitledBorder.CENTER);
		theethPicturePanel.setBorder(TB);

		JButton uploadBtn1 = new JButton("upload1");
		// uploadBtn1.addActionListener(new OpenActionListener());
		uploadBtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Image Files", // ���� �̸��� â�� ��µ� ���ڿ�
						"jpg", "gif", "PNG"); // ���� ���ͷ� ���Ǵ� Ȯ����. *.jpg. *.gif��
												// ������
				chooser.setFileFilter(filter); // ���� ���̾�α׿� ���� ���� ����
				chooser.setMultiSelectionEnabled(false);//���� ���� �Ұ�

				// ���� ���̾�α� ���
				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) { // ����ڰ� â�� ������ �ݾҰų� ���
															// ��ư�� ���� ���
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�", "���",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// ����ڰ� ������ �����ϰ� "����" ��ư�� ���� ���
				filePath = chooser.getSelectedFile().getPath(); // ���� ��θ���
																// �˾ƿ´�.
				

				teethP1.setIcon(new ImageIcon(((new ImageIcon(filePath))
						.getImage()).getScaledInstance(300, 250,
						java.awt.Image.SCALE_SMOOTH))); // ������ �ε��Ͽ� �̹��� ���̺���
														// ����Ѵ�.
				teethP1.addMouseListener(new EnlargeListener());
			}
		});
		JButton uploadBtn2 = new JButton("upload2");
		// uploadBtn2.addActionListener(new OpenActionListener());
		uploadBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Image Files", // ���� �̸��� â�� ��µ� ���ڿ�
						"jpg", "gif", "PNG"); // ���� ���ͷ� ���Ǵ� Ȯ����. *.jpg. *.gif��
												// ������
				chooser.setFileFilter(filter); // ���� ���̾�α׿� ���� ���� ����
				chooser.setMultiSelectionEnabled(false);//���� ���� �Ұ�

				// ���� ���̾�α� ���
				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) { // ����ڰ� â�� ������ �ݾҰų� ���
															// ��ư�� ���� ���
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�", "���",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// ����ڰ� ������ �����ϰ� "����" ��ư�� ���� ���
				filePath = chooser.getSelectedFile().getPath(); // ���� ��θ���
																// �˾ƿ´�.
				
				teethP2.setIcon(new ImageIcon(((new ImageIcon(filePath))
						.getImage()).getScaledInstance(300, 250,
						java.awt.Image.SCALE_SMOOTH))); // ������ �ε��Ͽ� �̹��� ���̺���
														// ����Ѵ�.
				teethP2.addMouseListener(new EnlargeListener());
			}
		});
		JButton uploadBtn3 = new JButton("upload3");
		// uploadBtn3.addActionListener(new OpenActionListener());
		uploadBtn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Image Files", // ���� �̸��� â�� ��µ� ���ڿ�
						"jpg", "gif", "PNG"); // ���� ���ͷ� ���Ǵ� Ȯ����. *.jpg. *.gif��
												// ������
				chooser.setFileFilter(filter); // ���� ���̾�α׿� ���� ���� ����
				chooser.setMultiSelectionEnabled(false);//���� ���� �Ұ�

				// ���� ���̾�α� ���
				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) { // ����ڰ� â�� ������ �ݾҰų� ���
															// ��ư�� ���� ���
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�", "���",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// ����ڰ� ������ �����ϰ� "����" ��ư�� ���� ���
				filePath = chooser.getSelectedFile().getPath(); // ���� ��θ���
																// �˾ƿ´�.
				
				teethP3.setIcon(new ImageIcon(((new ImageIcon(filePath))
						.getImage()).getScaledInstance(300, 250,
						java.awt.Image.SCALE_SMOOTH))); // ������ �ε��Ͽ� �̹��� ���̺���
														// ����Ѵ�.
				teethP3.addMouseListener(new EnlargeListener());
			}
		});

		// �̹��� ��� ĭ
		teethP1 = new JLabel();
		teethP2 = new JLabel();
		teethP3 = new JLabel();
		Border border = LineBorder.createGrayLineBorder();
		teethP1.setBorder(border);
		teethP2.setBorder(border);
		teethP3.setBorder(border);
		teethP1.setPreferredSize(new Dimension(250, 200));
		teethP2.setPreferredSize(new Dimension(250, 200));
		teethP3.setPreferredSize(new Dimension(250, 200));

		// ��ġ ����!
		theethPicturePanel.setLayout(new GridBagLayout());
		theethPicturePanel.add(uploadBtn1, new GridBagConstraints(0, 0, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		theethPicturePanel.add(uploadBtn2, new GridBagConstraints(2, 0, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		theethPicturePanel.add(uploadBtn3, new GridBagConstraints(4, 0, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		theethPicturePanel.add(teethP1, new GridBagConstraints(0, 1, 2, 2, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		theethPicturePanel.add(teethP2, new GridBagConstraints(2, 1, 2, 2, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		theethPicturePanel.add(teethP3, new GridBagConstraints(4, 1, 2, 2, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));

		// ġ�� üũ! amyhee
		teethCheckPanel = new JPanel();
		TitledBorder TB3 = new TitledBorder("ġ�� üũ");
		TB3.setTitleJustification(TitledBorder.CENTER);
		teethCheckPanel.setBorder(TB3);

		ButtonGroup g1 = new ButtonGroup();
		check = new JPanel();
		check.setLayout(new BoxLayout(check, BoxLayout.Y_AXIS));
		JRadioButton num1 = new JRadioButton("��ġ", true);
		JRadioButton num2 = new JRadioButton("����", false);
		JRadioButton num3 = new JRadioButton("��ġ", false);
		JRadioButton num4 = new JRadioButton("����ġġ��", false);
		num1.addActionListener(radioButtonActionListener);
		num2.addActionListener(radioButtonActionListener);
		num3.addActionListener(radioButtonActionListener);
		num4.addActionListener(radioButtonActionListener);
		g1.add(num1);
		g1.add(num2);
		g1.add(num3);
		g1.add(num4);
		check.add(num1);
		check.add(num2);
		check.add(num3);
		check.add(num4);

		teethCheckPanel.addMouseListener(new MyMouseListener());

		teethcheckurl = getClass().getClassLoader().getResource("agePart0.png");
		ImageIcon filename1 = new ImageIcon(teethcheckurl);
		// filename = "images/agePart0.png";
		// try {
		// image = ImageIO.read(new File(filename));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		checkImg = new JLabel(filename1);
		teethCheckPanel.add(checkImg);
		teethCheckPanel.add(check);

		JPanel teethInfoPanel = new JPanel();
		TitledBorder TB2 = new TitledBorder("���� ����");
		TB2.setTitleJustification(TitledBorder.CENTER);
		teethInfoPanel.setBorder(TB2);

		JLabel la_babyTooth = new JLabel("��ġ ����");
		info_babyTooth = new JTextField(5);
		info_babyTooth.setText("0");
		JLabel la_permanentTooth = new JLabel("����ġ ����(����� ����)");
		info_permanentTooth = new JTextField(5);
		info_permanentTooth.setText("0");
		JLabel la_losePermanentTooth_front = new JLabel(
				"����ġ ��� ����(�մ�)");
		info_losePermanentTooth_front = new JTextField(5);
		info_losePermanentTooth_front.setText("0");
		JLabel la_losePermanentTooth_back = new JLabel(
				"����ġ ��� ����(��ݴ�)");
		info_losePermanentTooth_back = new JTextField(5);
		info_losePermanentTooth_back.setText("0");
		JLabel la_implant = new JLabel("�ΰ�ġ�� ����");
		info_implant = new JTextField(5);
		info_implant.setText("0");
		JLabel la_dentures = new JLabel("Ʋ�� ����");
		info_dentures = new JTextField(5);
		info_dentures.setText("0");
		JLabel la_leaving = new JLabel("��Ĺ�ġ ġ�� ����");
		info_leaving = new JTextField(5);
		info_leaving.setText("0");
		JLabel la_treatment = new JLabel("���� ġ�� ����");
		info_treatment = new JTextField(5);
		info_treatment.setText("0");
		JLabel la_sulcus = new JLabel("���� ���� ġ�� ����");
		info_sulcus = new JTextField(5);
		info_sulcus.setText("0");

		JButton nextBtn = new JButton("����");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bean.setBabyTooth(Integer.parseInt(info_babyTooth.getText()));
				bean.setPermanentTooth(Integer.parseInt(info_permanentTooth
						.getText()));
				bean.setLosePermanentTooth_front(Integer
						.parseInt(info_losePermanentTooth_front.getText()));
				bean.setLosePermanentTooth_back(Integer
						.parseInt(info_losePermanentTooth_back.getText()));
				bean.setImplant(Integer.parseInt(info_implant.getText()));
				bean.setDentures(Integer.parseInt(info_dentures.getText()));
				bean.setLeaving(Integer.parseInt(info_leaving.getText()));
				bean.setTreatment(Integer.parseInt(info_treatment.getText()));
				bean.setSulcus(Integer.parseInt(info_sulcus.getText()));

				addcheckPanel = new AddCheckPanel_K(sendAge);
				addInfoPanel.add(addcheckPanel);

				jtp.setSelectedIndex(3);
			}
		});

		// ��ġ ����!
		teethInfoPanel.setLayout(new GridBagLayout());
		teethInfoPanel.add(la_babyTooth, new GridBagConstraints(0, 0, 1, 1, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		teethInfoPanel.add(info_babyTooth, new GridBagConstraints(1, 0, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 20), 0, 0));

		teethInfoPanel.add(la_permanentTooth, new GridBagConstraints(0, 1, 1,
				1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		teethInfoPanel.add(info_permanentTooth, new GridBagConstraints(1, 1, 1,
				1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 20), 0, 0));
		teethInfoPanel.add(la_losePermanentTooth_front, new GridBagConstraints(
				2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		teethInfoPanel.add(info_losePermanentTooth_front,
				new GridBagConstraints(3, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 20), 0, 0));
		teethInfoPanel.add(la_losePermanentTooth_back, new GridBagConstraints(
				4, 1, 1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		teethInfoPanel.add(info_losePermanentTooth_back,
				new GridBagConstraints(5, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 20), 0, 0));

		teethInfoPanel.add(la_implant, new GridBagConstraints(0, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		teethInfoPanel.add(info_implant, new GridBagConstraints(1, 2, 1, 1, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 20), 0, 0));
		teethInfoPanel.add(la_dentures, new GridBagConstraints(2, 2, 1, 1, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		teethInfoPanel.add(info_dentures, new GridBagConstraints(3, 2, 1, 1, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 20), 0, 0));

		teethInfoPanel.add(la_leaving, new GridBagConstraints(0, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		teethInfoPanel.add(info_leaving, new GridBagConstraints(1, 3, 1, 1, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 20), 0, 0));
		teethInfoPanel.add(la_treatment, new GridBagConstraints(2, 3, 1, 1, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		teethInfoPanel.add(info_treatment, new GridBagConstraints(3, 3, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 20), 0, 0));
		teethInfoPanel.add(la_sulcus, new GridBagConstraints(4, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		teethInfoPanel.add(info_sulcus, new GridBagConstraints(5, 3, 1, 1, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 20), 0, 0));

		theethPanel.setLayout(new GridBagLayout());
		theethPanel.add(theethPicturePanel, new GridBagConstraints(0, 0, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		theethPanel.add(teethCheckPanel, new GridBagConstraints(0, 1, 1, 1, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		theethPanel.add(teethInfoPanel, new GridBagConstraints(0, 2, 1, 1, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		theethPanel.add(nextBtn, new GridBagConstraints(0, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						20, 700, 20, 20), 0, 0));

		scroll.add(theethPanel);

		return scroll;

	}

	public ScrollPane X_ray() {
		ScrollPane scroll = new ScrollPane();

		JPanel xrayPanel = new JPanel();
		JPanel xrayPicturePanel = new JPanel();
		TitledBorder TB = new TitledBorder("X-ray");
		TB.setTitleJustification(TitledBorder.CENTER);
		xrayPicturePanel.setBorder(TB);

		JButton uploadBtn = new JButton("upload");

		uploadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Image Files", // ���� �̸��� â�� ��µ� ���ڿ�
						"jpg", "gif", "PNG"); // ���� ���ͷ� ���Ǵ� Ȯ����. *.jpg. *.gif��
												// ������
				chooser.setFileFilter(filter); // ���� ���̾�α׿� ���� ���� ����
				chooser.setMultiSelectionEnabled(false);//���� ���� �Ұ�

				// ���� ���̾�α� ���
				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) { // ����ڰ� â�� ������ �ݾҰų� ���
															// ��ư�� ���� ���
					JOptionPane.showMessageDialog(null, "doesn't select file",
							"warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				// ����ڰ� ������ �����ϰ� "����" ��ư�� ���� ���
				String filePath = chooser.getSelectedFile().getPath(); // ����
																		// ��θ���
																		// �˾ƿ´�.
				p1.setIcon(new ImageIcon(((new ImageIcon(filePath)).getImage())
						.getScaledInstance(500, 350,
								java.awt.Image.SCALE_SMOOTH))); // ������ �ε��Ͽ� �̹���
																// ���̺��� ����Ѵ�.
			}
		});

		JTextField pathCheck = new JTextField(25);

		// �̹��� ��� ĭ
		p1 = new JLabel();
		Border border = LineBorder.createGrayLineBorder();
		p1.setBorder(border);
		p1.setPreferredSize(new Dimension(500, 350));

		// ��ġ ����!
		xrayPicturePanel.setLayout(new GridBagLayout());
		xrayPicturePanel.add(uploadBtn, new GridBagConstraints(0, 0, 1, 1, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		xrayPicturePanel.add(pathCheck, new GridBagConstraints(1, 0, 2, 1, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		xrayPicturePanel.add(p1, new GridBagConstraints(0, 1, 3, 3, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));

		// JPanel addInfoPanel = new JPanel();
		addcheckPanel = new AddCheckPanel_K(sendAge);
		addInfoPanel = new JPanel();
		TitledBorder TB2 = new TitledBorder("�߰� �˻�");
		TB2.setTitleJustification(TitledBorder.CENTER);
		addInfoPanel.setBorder(TB2);
		// addInfoPanel.add(addcheckPanel);

		JButton nextBtn = new JButton("����");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bean.setTartar((String) addcheckPanel.info_tartar
						.getSelectedItem());
				bean.setGingivitis((String) addcheckPanel.info_gingivitis
						.getSelectedItem());
				bean.setMicroscope_gu_amount((String) addcheckPanel.info_micro_gu_Amount
						.getSelectedItem());
				bean.setMicroscope_gu_movement((String) addcheckPanel.info_micro_gu_Movement
						.getSelectedItem());
				bean.setSnyder((String) addcheckPanel.info_snyder
						.getSelectedItem());
				bean.setMalocclusion((String) addcheckPanel.info_malocclusion
						.getSelectedItem());
				bean.setOdontoclasis((String) addcheckPanel.info_odontoclasis
						.getSelectedItem());
				bean.setInfection((String) addcheckPanel.info_infection
						.getSelectedItem());
				bean.setBad_breath((String) addcheckPanel.info_badBreath
						.getSelectedItem());
				bean.setBrushMethod((String) addcheckPanel.info_brushMethod
						.getSelectedItem());
				bean.setPeriodontal((String) addcheckPanel.info_periodontal
						.getSelectedItem());
				bean.setMobility((String) addcheckPanel.info_mobility
						.getSelectedItem());
				bean.setMandibular((String) addcheckPanel.info_mandibular
						.getSelectedItem());
				bean.setAesthetic((String) addcheckPanel.info_aesthetic
						.getSelectedItem());
				bean.setSaliva((String) addcheckPanel.info_saliva
						.getSelectedItem());
				bean.setConsistency((String) addcheckPanel.info_consistency
						.getSelectedItem());
				bean.setDazzling((String) addcheckPanel.info_dazzling
						.getSelectedItem());
				bean.setWisdomTooth_pain((String) addcheckPanel.info_wisdomTooth_pain
						.getSelectedItem());
				bean.setPartialDenture((String) addcheckPanel.info_partialDenture
						.getSelectedItem());
				bean.setProsthesis_need((String) addcheckPanel.info_prosthesis_need
						.getSelectedItem());
				bean.setDenture_need((String) addcheckPanel.info_denture_need
						.getSelectedItem());

				jtp.setSelectedIndex(4);
			}
		});

		xrayPanel.setLayout(new GridBagLayout());
		xrayPanel.add(xrayPicturePanel, new GridBagConstraints(0, 0, 1, 1, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		xrayPanel.add(addInfoPanel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		xrayPanel.add(nextBtn, new GridBagConstraints(0, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						20, 700, 20, 20), 0, 0));

		scroll.add(xrayPanel);

		return scroll;
	}

	public JPanel Plaque() {
		JPanel plaquePanel = new JPanel();

		JPanel plaquePicturePanel = new JPanel();
		TitledBorder TB = new TitledBorder("ġ�鼼�ո�����");
		TB.setTitleJustification(TitledBorder.CENTER);
		plaquePicturePanel.setBorder(TB);

		JButton uploadBtn1 = new JButton("upload1");
		// uploadBtn1.addActionListener(new OpenActionListener());
		uploadBtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Image Files", // ���� �̸��� â�� ��µ� ���ڿ�
						"jpg", "gif", "PNG"); // ���� ���ͷ� ���Ǵ� Ȯ����. *.jpg. *.gif��
												// ������
				chooser.setFileFilter(filter); // ���� ���̾�α׿� ���� ���� ����
				chooser.setMultiSelectionEnabled(false);//���� ���� �Ұ�

				// ���� ���̾�α� ���
				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) { // ����ڰ� â�� ������ �ݾҰų� ���
															// ��ư�� ���� ���
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�", "���",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// ����ڰ� ������ �����ϰ� "����" ��ư�� ���� ���
				filePath = chooser.getSelectedFile().getPath(); // ���� ��θ���
																// �˾ƿ´�.

				plaqueP1.setIcon(new ImageIcon(((new ImageIcon(filePath))
						.getImage()).getScaledInstance(300, 250,
						java.awt.Image.SCALE_SMOOTH))); // ������ �ε��Ͽ� �̹��� ���̺���
														// ����Ѵ�.
				plaqueP1.addMouseListener(new EnlargeListener());
			}
		});
		JButton uploadBtn2 = new JButton("upload2");
		// uploadBtn2.addActionListener(new OpenActionListener());
		uploadBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Image Files", // ���� �̸��� â�� ��µ� ���ڿ�
						"jpg", "gif", "PNG"); // ���� ���ͷ� ���Ǵ� Ȯ����. *.jpg. *.gif��
												// ������
				chooser.setFileFilter(filter); // ���� ���̾�α׿� ���� ���� ����
				chooser.setMultiSelectionEnabled(false);//���� ���� �Ұ�

				// ���� ���̾�α� ���
				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) { // ����ڰ� â�� ������ �ݾҰų� ���
															// ��ư�� ���� ���
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�", "���",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// ����ڰ� ������ �����ϰ� "����" ��ư�� ���� ���
				filePath = chooser.getSelectedFile().getPath(); // ���� ��θ���
																// �˾ƿ´�.
				plaqueP2.setIcon(new ImageIcon(((new ImageIcon(filePath))
						.getImage()).getScaledInstance(300, 250,
						java.awt.Image.SCALE_SMOOTH))); // ������ �ε��Ͽ� �̹��� ���̺���
														// ����Ѵ�.
				plaqueP2.addMouseListener(new EnlargeListener());
			}
		});
		JButton uploadBtn3 = new JButton("upload3");
		// uploadBtn3.addActionListener(new OpenActionListener());
		uploadBtn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Image Files", // ���� �̸��� â�� ��µ� ���ڿ�
						"jpg", "gif", "PNG"); // ���� ���ͷ� ���Ǵ� Ȯ����. *.jpg. *.gif��
												// ������
				chooser.setFileFilter(filter); // ���� ���̾�α׿� ���� ���� ����
				chooser.setMultiSelectionEnabled(false);//���� ���� �Ұ�

				// ���� ���̾�α� ���
				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) { // ����ڰ� â�� ������ �ݾҰų� ���
															// ��ư�� ���� ���
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�", "���",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// ����ڰ� ������ �����ϰ� "����" ��ư�� ���� ���
				filePath = chooser.getSelectedFile().getPath(); // ���� ��θ���
																// �˾ƿ´�.
				plaqueP3.setIcon(new ImageIcon(((new ImageIcon(filePath))
						.getImage()).getScaledInstance(300, 250,
						java.awt.Image.SCALE_SMOOTH))); // ������ �ε��Ͽ� �̹��� ���̺���
														// ����Ѵ�.
				plaqueP3.addMouseListener(new EnlargeListener());
			}
		});

		// �̹��� ��� ĭ
		plaqueP1 = new JLabel();
		plaqueP2 = new JLabel();
		plaqueP3 = new JLabel();
		Border border = LineBorder.createGrayLineBorder();
		plaqueP1.setBorder(border);
		plaqueP2.setBorder(border);
		plaqueP3.setBorder(border);
		plaqueP1.setPreferredSize(new Dimension(250, 200));
		plaqueP2.setPreferredSize(new Dimension(250, 200));
		plaqueP3.setPreferredSize(new Dimension(250, 200));

		// ��ġ ����!
		plaquePicturePanel.setLayout(new GridBagLayout());
		plaquePicturePanel.add(uploadBtn1, new GridBagConstraints(0, 0, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		plaquePicturePanel.add(uploadBtn2, new GridBagConstraints(2, 0, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		plaquePicturePanel.add(uploadBtn3, new GridBagConstraints(4, 0, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		plaquePicturePanel.add(plaqueP1, new GridBagConstraints(0, 1, 2, 2, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		plaquePicturePanel.add(plaqueP2, new GridBagConstraints(2, 1, 2, 2, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		plaquePicturePanel.add(plaqueP3, new GridBagConstraints(4, 1, 2, 2, 0,
				0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));

		JPanel sPanel = new JPanel();
		JLabel la_plaque = new JLabel("ġ�鼼�ո� ����");
		info_plaque = new JComboBox(new String[] {"����", "����", "����", "�ټҺҰ�", "�ſ�Ұ�"});
		sPanel.add(la_plaque, new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		sPanel.add(info_plaque, new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));

		JButton nextBtn = new JButton("����");
		plaquePanel.add(nextBtn);
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bean.setPlaque_score((String) info_plaque.getSelectedItem());
				Calculation_K cal = new Calculation_K();
				bean.setAge(sendAge);
				score = cal.Calculation_K(bean);

				// Jpanel spiderPanel = new JPanel();
				SpiderChart_K spider = new SpiderChart_K(score[1], score[3],
						score[5], score[7], score[9]);

				JPanel panel = new JPanel();
				JLabel l1 = new JLabel("ġ�� �� ���� : ");
				JLabel l2 = new JLabel();

				l2.setText(String.valueOf(score[1]) + " / "
						+ String.valueOf(score[0]));
				JLabel l3 = new JLabel("��� ���� ���� : ");
				JLabel l4 = new JLabel();
				l4.setText(String.valueOf(score[3]) + " / "
						+ String.valueOf(score[2]));
				JLabel l5 = new JLabel("ġ�� ���� ���� : ");
				JLabel l6 = new JLabel();
				l6.setText(String.valueOf(score[5]) + " / "
						+ String.valueOf(score[4]));
				JLabel l7 = new JLabel("��Ÿ ���� ���� ���� : ");
				JLabel l8 = new JLabel();
				l8.setText(String.valueOf(score[7]) + " / "
						+ String.valueOf(score[6]));
				JLabel l9 = new JLabel("���� ���� �ɷ� ���� : ");
				JLabel l10 = new JLabel();
				l10.setText(String.valueOf(score[9]) + " / "
						+ String.valueOf(score[8]));
				JLabel l11 = new JLabel("���� ���� : ");
				JLabel l12 = new JLabel();
				l12.setText(String.valueOf(score[10]) + " / " + "100");

				panel.setLayout(new GridBagLayout());
				panel.add(l1, new GridBagConstraints(0, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				panel.add(l2, new GridBagConstraints(1, 0, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				panel.add(l3, new GridBagConstraints(0, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				panel.add(l4, new GridBagConstraints(1, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				panel.add(l5, new GridBagConstraints(0, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				panel.add(l6, new GridBagConstraints(1, 2, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				panel.add(l7, new GridBagConstraints(0, 3, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				panel.add(l8, new GridBagConstraints(1, 3, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				panel.add(l9, new GridBagConstraints(0, 4, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				panel.add(l10, new GridBagConstraints(1, 4, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				panel.add(l11, new GridBagConstraints(0, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				panel.add(l12, new GridBagConstraints(1, 5, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));

				ResultView_K view = new ResultView_K(bean, score[10]);

				JButton nextBtn = new JButton("����");
				nextBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jtp.setSelectedIndex(6);
						calView = new CalendarView_K(bean, score[10]);
						
						JButton resetBtn = new JButton("�ٽ��ϱ�");
						resetBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Menu menu = new Menu();
							}
						});
						
						JButton saveBtn = new JButton("�����ϱ�");
						saveBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								JFileChooser chooser = new JFileChooser();
								FileNameExtensionFilter filter = new FileNameExtensionFilter(
										".xls", // ���� �̸��� â�� ��µ� ���ڿ�
										"xls"); // ���� ���ͷ� ���Ǵ� Ȯ����. *.xml �� ������
								chooser.setFileFilter(filter); // ���� ���̾�α׿� ���� ���� ����
								chooser.setMultiSelectionEnabled(false);//���� ���� �Ұ�


								// ���� ���̾�α� ���
								int ret = chooser.showSaveDialog(null);
								if (ret != JFileChooser.APPROVE_OPTION) { // ����ڰ� â�� ������ �ݾҰų� ���
																			// ��ư�� ���� ���
									JOptionPane.showMessageDialog(null, "�����θ� �������� �����̽��ϴ�.", "���",
											JOptionPane.WARNING_MESSAGE);
									return;
								}
								// ����ڰ� ������ �����ϰ� "����" ��ư�� ���� ���
								//String saveFilePath = chooser.getSelectedFile().toString() + chooser.getFileFilter().getDescription(); // ���� ��θ��� �˾ƿ´�.				
								String saveFilePath = chooser.getSelectedFile().toString();
								if(saveFilePath.contains(".xls")){
									if(saveFilePath.endsWith("x"))
										saveFilePath = saveFilePath.replace("xlsx", "xls");
									else
										saveFilePath = chooser.getSelectedFile().toString();
								}
								else
									saveFilePath = chooser.getSelectedFile().toString() + chooser.getFileFilter().getDescription();
								
								saveToExcel(saveFilePath);
							}
						});
						
						careSchedulePanel.setLayout(new GridBagLayout());
						careSchedulePanel.add(calView, new GridBagConstraints(0, 0, 1, 1, 0, 0,
								GridBagConstraints.NORTH, GridBagConstraints.BOTH,
								new Insets(2, 2, 2, 2), 0, 0));
						careSchedulePanel.add(saveBtn, new GridBagConstraints(1, 1, 1, 1, 0,
								0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
								new Insets(2, 2, 2, 2), 0, 0));
						careSchedulePanel.add(resetBtn, new GridBagConstraints(2, 1, 1, 1, 0,
								0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
								new Insets(2, 2, 2, 2), 0, 0));

						tap6 = new JPanel();
					}

				});

				JButton resetBtn = new JButton("�ٽ��ϱ�");
				resetBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Menu menu = new Menu();
					}
				});

				resultPanel.setLayout(new GridBagLayout());
				resultPanel.add(spider, new GridBagConstraints(0, 0, 2, 1, 0,
						0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				resultPanel.add(panel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				resultPanel.add(view, new GridBagConstraints(1, 1, 1, 1, 0, 0,
						GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(2, 2, 2, 2), 0, 0));
				resultPanel.add(nextBtn, new GridBagConstraints(0, 2, 1, 1, 0,
						0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(20, 20, 20, 20), 0, 0));
				resultPanel.add(resetBtn, new GridBagConstraints(1, 2, 1, 1, 0,
						0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
						new Insets(20, 20, 20, 20), 0, 0));

				tap5 = new ScrollPane();

				jtp.setSelectedIndex(5);
			}
		});

		plaquePanel.setLayout(new GridBagLayout());
		plaquePanel.add(plaquePicturePanel, new GridBagConstraints(0, 0, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		plaquePanel.add(sPanel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		plaquePanel.add(nextBtn, new GridBagConstraints(0, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						20, 700, 20, 20), 0, 0));

		return plaquePanel;
	}

	class OpenActionListener implements ActionListener {
		JFileChooser chooser;
		String actionName;

		OpenActionListener() {
			chooser = new JFileChooser(); // ���� ���̾�α� ����
		}

		public void actionPerformed(ActionEvent e) {

			actionName = e.getActionCommand();

			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"Image Files", // ���� �̸��� â�� ��µ� ���ڿ�
					"jpg", "gif", "PNG"); // ���� ���ͷ� ���Ǵ� Ȯ����. *.jpg. *.gif�� ������
			chooser.setFileFilter(filter); // ���� ���̾�α׿� ���� ���� ����
			chooser.setMultiSelectionEnabled(false);//���� ���� �Ұ�

			// ���� ���̾�α� ���
			int ret = chooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) { // ����ڰ� â�� ������ �ݾҰų� ��� ��ư��
														// ���� ���
				JOptionPane.showMessageDialog(null, "doesn't select file",
						"warning", JOptionPane.WARNING_MESSAGE);
				return;
			}

			// ����ڰ� ������ �����ϰ� "����" ��ư�� ���� ���
			filePath = chooser.getSelectedFile().getPath(); // ���� ��θ���
															// �˾ƿ´�.
			switch (actionName) {
			case "upload1":
				if (tapNum == 2) {
					teethP1.setIcon(new ImageIcon(((new ImageIcon(filePath))
							.getImage()).getScaledInstance(300, 250,
							java.awt.Image.SCALE_SMOOTH))); // ������ �ε��Ͽ� �̹��� ���̺���
															// ����Ѵ�.
					teethP1.addMouseListener(new EnlargeListener());
				} else if (tapNum == 4) {
					plaqueP1.setIcon(new ImageIcon(((new ImageIcon(filePath))
							.getImage()).getScaledInstance(300, 250,
							java.awt.Image.SCALE_SMOOTH))); // ������ �ε��Ͽ� �̹��� ���̺���
															// ����Ѵ�.
					plaqueP1.addMouseListener(new EnlargeListener());
				}
				break;
			case "upload2":
				if (tapNum == 2) {
					teethP2.setIcon(new ImageIcon(((new ImageIcon(filePath))
							.getImage()).getScaledInstance(300, 250,
							java.awt.Image.SCALE_SMOOTH))); // ������ �ε��Ͽ� �̹��� ���̺���
															// ����Ѵ�.
					teethP2.addMouseListener(new EnlargeListener());
				} else if (tapNum == 4) {
					plaqueP2.setIcon(new ImageIcon(((new ImageIcon(filePath))
							.getImage()).getScaledInstance(300, 250,
							java.awt.Image.SCALE_SMOOTH))); // ������ �ε��Ͽ� �̹��� ���̺���
															// ����Ѵ�.
					plaqueP2.addMouseListener(new EnlargeListener());
				}
				break;
			case "upload3":
				if (tapNum == 2) {
					teethP3.setIcon(new ImageIcon(((new ImageIcon(filePath))
							.getImage()).getScaledInstance(300, 250,
							java.awt.Image.SCALE_SMOOTH))); // ������ �ε��Ͽ� �̹��� ���̺���
															// ����Ѵ�.
					teethP3.addMouseListener(new EnlargeListener());
				} else if (tapNum == 4) {
					plaqueP3.setIcon(new ImageIcon(((new ImageIcon(filePath))
							.getImage()).getScaledInstance(300, 250,
							java.awt.Image.SCALE_SMOOTH))); // ������ �ε��Ͽ� �̹��� ���̺���
															// ����Ѵ�.
					plaqueP3.addMouseListener(new EnlargeListener());
				}
				break;
			}
		}
	}

	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}
		return null;
	}

	class EnlargeListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			EnlargePicture_K large = new EnlargePicture_K(filePath);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	public ScrollPane Result() {
		ScrollPane scroll = new ScrollPane();
		resultPanel = new JPanel();

		scroll.add(resultPanel);
		return scroll;
	}
	
	public JPanel CareSchedule() {
		JPanel panel = new JPanel();
		careSchedulePanel = new JPanel();
				
		panel.add(careSchedulePanel);
		return panel;
	}
	
	public ArrayList<PatientInfoBean_K> readExcelList(){
		read_list = new ArrayList<PatientInfoBean_K>();
		if(!file.isFile()){
			System.out.println("���Ͼ���");
			JOptionPane.showMessageDialog(null, "������ �����ϴ�", "���", JOptionPane.WARNING_MESSAGE);
		}else{
			
		Workbook workbook = null;
		Sheet sheet = null;
		
		try{
			workbook = Workbook.getWorkbook(file);
			sheet = workbook.getSheet(0);
			
			int rowCount = sheet.getRows();
			System.out.println("in readExcel - row : " + rowCount);
			int colCount = sheet.getColumns();
			
			
			if(rowCount<0)
				System.out.println("�о���� ���� �����ϴ�");
			
			Cell cell = null;
			String tempCast = null;
			
			for(int i=1; i<rowCount; i++){
				PatientInfoBean_K bean = new PatientInfoBean_K();
				cell = sheet.getCell(0,i);
				bean.setChart_num(cell.getContents());
				
				cell = sheet.getCell(1,i);
				bean.setName(cell.getContents());

				cell = sheet.getCell(2,i);
				bean.setAge(Integer.parseInt(cell.getContents()));

				cell = sheet.getCell(3,i);
				bean.setGender(cell.getContents());

				cell = sheet.getCell(4,i);
				bean.setBabyTooth(Integer.parseInt(cell.getContents()));

				cell = sheet.getCell(5,i);
				bean.setPermanentTooth(Integer.parseInt(cell.getContents()));

				cell = sheet.getCell(6,i);
				bean.setLosePermanentTooth_front(Integer.parseInt(cell.getContents()));

				cell = sheet.getCell(7,i);
				bean.setLosePermanentTooth_back(Integer.parseInt(cell.getContents()));

				cell = sheet.getCell(8,i);
				bean.setImplant(Integer.parseInt(cell.getContents()));

				cell = sheet.getCell(9,i);
				bean.setDentures(Integer.parseInt(cell.getContents()));

				cell = sheet.getCell(10,i);
				bean.setLeaving(Integer.parseInt(cell.getContents()));

				cell = sheet.getCell(11,i);
				bean.setTreatment(Integer.parseInt(cell.getContents()));

				cell = sheet.getCell(12,i);
				bean.setSulcus(Integer.parseInt(cell.getContents()));

				cell = sheet.getCell(13,i);
				bean.setPlaque_score(cell.getContents());

				cell = sheet.getCell(14,i);
				bean.setTartar(cell.getContents());

				cell = sheet.getCell(15,i);
				bean.setGingivitis(cell.getContents());

				cell = sheet.getCell(16,i);
				bean.setMicroscope_sal_amount(cell.getContents());

				cell = sheet.getCell(17,i);
				bean.setMicroscope_sal_movement(cell.getContents());

				cell = sheet.getCell(18,i);
				bean.setMicroscope_gu_amount(cell.getContents());

				cell = sheet.getCell(19,i);
				bean.setMicroscope_gu_movement(cell.getContents());

				cell = sheet.getCell(20,i);
				bean.setSnyder(cell.getContents());

				cell = sheet.getCell(21,i);
				bean.setMalocclusion(cell.getContents());

				cell = sheet.getCell(22,i);
				bean.setOdontoclasis(cell.getContents());

				cell = sheet.getCell(23,i);
				bean.setInfection(cell.getContents());

				cell = sheet.getCell(24,i);
				bean.setBad_breath(cell.getContents());

				cell = sheet.getCell(25,i);
				bean.setBrushMethod(cell.getContents());

				cell = sheet.getCell(26,i);
				bean.setPeriodontal(cell.getContents());

				cell = sheet.getCell(27,i);
				bean.setMobility(cell.getContents());

				cell = sheet.getCell(28,i);
				bean.setMandibular(cell.getContents());

				cell = sheet.getCell(29,i);
				bean.setAesthetic(cell.getContents());

				cell = sheet.getCell(30,i);
				bean.setSaliva(cell.getContents());

				cell = sheet.getCell(31,i);
				bean.setConsistency(cell.getContents());

				cell = sheet.getCell(32,i);
				bean.setWisdomTooth_pain(cell.getContents());

				cell = sheet.getCell(33,i);
				bean.setDazzling(cell.getContents());

				cell = sheet.getCell(34,i);
				bean.setPartialDenture(cell.getContents());

				cell = sheet.getCell(35,i);
				bean.setProsthesis_need(cell.getContents());

				cell = sheet.getCell(36,i);
				bean.setDenture_need(cell.getContents());

				cell = sheet.getCell(37,i);
				bean.setSugar_frequency(cell.getContents());

				cell = sheet.getCell(38,i);
				bean.setBrush_num(cell.getContents());

				cell = sheet.getCell(39,i);
				bean.setChew_food(cell.getContents());

				cell = sheet.getCell(40,i);
				bean.setBrush_time(cell.getContents());

				cell = sheet.getCell(41,i);
				bean.setDisease_num(cell.getContents());

				cell = sheet.getCell(42,i);
				bean.setDisease_kind(cell.getContents());

				cell = sheet.getCell(43,i);
				bean.setPrevention_visit(cell.getContents());

				cell = sheet.getCell(44,i);
				bean.setVisit(cell.getContents());

				cell = sheet.getCell(45,i);
				bean.setScaling(cell.getContents());

				cell = sheet.getCell(46,i);
				bean.setCare_product(cell.getContents());

				cell = sheet.getCell(47,i);
				bean.setDrinking(cell.getContents());

				cell = sheet.getCell(48,i);
				bean.setSmoking(cell.getContents());
				
				cell = sheet.getCell(49,i);
				bean.setDrink_and_smoke(cell.getContents());

				cell = sheet.getCell(50,i);
				bean.setXerostomia(cell.getContents());
				
				cell = sheet.getCell(51,i);
				bean.setLearn(cell.getContents());
				
				cell = sheet.getCell(52,i);
				bean.setPregnancy(cell.getContents());	
				
				read_list.add(bean);
				
			}
			
		} catch(Exception e){
			System.out.println(e.getMessage());
		} finally {
			workbook.close();
		}
		}
		
		
		return read_list;
	}
	
	public class readExcel_show implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			readExcelList();
		}
	}
	
	public void saveToExcel(String filePath){
			ArrayList<PatientInfoBean_K> readList = null;
			file = new File(filePath);
			if(!file.isFile()){
				System.out.println("���Ͼ���");
				readList = new ArrayList<PatientInfoBean_K>();
				readList.add(bean);
			}else{
				readList = readExcelList();
				System.out.println("readExcelList.size : " + readList.size());
				readList.add(bean);
			}
			
			// �������� ��ü ����
	        WritableWorkbook workbook = null;
	         
	        // ��Ʈ ��ü ����
	        WritableSheet sheet = null;
	         
	        // �� ��ü ����
	        Label label = null;
	         
	        
	        try{
	        	// ���� ����
	            workbook = Workbook.createWorkbook(file);
	             
	            // ��Ʈ ����
	            workbook.createSheet("sheet1", 0);
	            sheet = workbook.getSheet(0);
	            
	            label = new Label(0, 0, "��Ʈ��ȣ");
	            sheet.addCell(label);
	            
	            label = new Label(1, 0, "�̸�");
	            sheet.addCell(label);
	            
	            label = new Label(2, 0, "����");
	            sheet.addCell(label);
	            
	            label = new Label(3, 0, "����");
	            sheet.addCell(label);
	            
	            label = new Label(4, 0, "��ġ����");
	            sheet.addCell(label);
	            
	            label = new Label(5, 0, "����ġ����");
	            sheet.addCell(label);
	            
	            label = new Label(6, 0, "�մ� ���ġ�� ����");
	            sheet.addCell(label);
	            
	            label = new Label(7, 0, "��ݴ� ���ġ�� ����");
	            sheet.addCell(label);
	            
	            label = new Label(8, 0, "�ΰ�ġ�� ����");
	            sheet.addCell(label);
	            
	            label = new Label(9, 0, "Ʋ�� ����");
	            sheet.addCell(label);
	            
	            label = new Label(10, 0, "��Ĺ�ġġ�� ����");
	            sheet.addCell(label);
	            
	            label = new Label(11, 0, "����ġ�� ����");
	            sheet.addCell(label);
	            
	            label = new Label(12, 0, "���� ���� ġ�� ����");
	            sheet.addCell(label);
	            
	            label = new Label(13, 0, "ġ�鼼�ո� ����");
	            sheet.addCell(label);
	            
	            label = new Label(14, 0, "ġ��");
	            sheet.addCell(label);
	            
	            label = new Label(15, 0, "ġ����");
	            sheet.addCell(label);
	            
	            label = new Label(16, 0, "���� ��");
	            sheet.addCell(label);
	            
	            label = new Label(17, 0, "���� Ȱ����");
	            sheet.addCell(label);
	            
	            label = new Label(18, 0, "���� ��");
	            sheet.addCell(label);
	            
	            label = new Label(19, 0, "���� Ȱ����");
	            sheet.addCell(label);
	            
	            label = new Label(20, 0, "�����̴� �˻�");
	            sheet.addCell(label);
	            
	            label = new Label(21, 0, "��������");
	            sheet.addCell(label);
	            
	            label = new Label(22, 0, "ġ������, �ܻ�");
	            sheet.addCell(label);
	            
	            label = new Label(23, 0, "��������");
	            sheet.addCell(label);
	            
	            label = new Label(24, 0, "����");
	            sheet.addCell(label);

	            label = new Label(25, 0, "�̴۱� ���");
	            sheet.addCell(label);

	            label = new Label(26, 0, "ġ�ֳ�");
	            sheet.addCell(label);

	            label = new Label(27, 0, "ġ�Ƶ���");
	            sheet.addCell(label);

	            label = new Label(28, 0, "����� �̻�");
	            sheet.addCell(label);

	            label = new Label(29, 0, "�ɹ̿���");
	            sheet.addCell(label);

	            label = new Label(30, 0, "Ÿ�� ��");
	            sheet.addCell(label);

	            label = new Label(31, 0, "������");
	            sheet.addCell(label);

	            label = new Label(32, 0, "����� ����");
	            sheet.addCell(label);

	            label = new Label(33, 0, "�ø���");
	            sheet.addCell(label);

	            label = new Label(34, 0, "������ġ ��������");
	            sheet.addCell(label);

	            label = new Label(35, 0, "��ö��, ��ġ �ʿ�");
	            sheet.addCell(label);

	            label = new Label(36, 0, "Ʋ�� �ʿ䵵");
	            sheet.addCell(label);

	            label = new Label(37, 0, "�缷��");
	            sheet.addCell(label);

	            label = new Label(38, 0, "�̴��� Ƚ��");
	            sheet.addCell(label);

	            label = new Label(39, 0, "ġ������ ���");
	            sheet.addCell(label);

	            label = new Label(40, 0, "�̴۱� �ñ�");
	            sheet.addCell(label);

	            label = new Label(41, 0, "������ȯ ����");
	            sheet.addCell(label);

	            label = new Label(42, 0, "������ȯ ����");
	            sheet.addCell(label);

	            label = new Label(43, 0, "�������� ����");
	            sheet.addCell(label);

	            label = new Label(44, 0, "ġ���湮 ����");
	            sheet.addCell(label);

	            label = new Label(45, 0, "�����ϸ� ����");
	            sheet.addCell(label);

	            label = new Label(46, 0, "������ǰ���");
	            sheet.addCell(label);

	            label = new Label(47, 0, "���ֿ���");
	            sheet.addCell(label);

	            label = new Label(48, 0, "��������");
	            sheet.addCell(label);

	            label = new Label(49, 0, "����&����");
	            sheet.addCell(label);

	            label = new Label(50, 0, "����������");
	            sheet.addCell(label);

	            label = new Label(51, 0, "���Ǳ��� ����");
	            sheet.addCell(label);

	            label = new Label(52, 0, "�ӽſ���");
	            sheet.addCell(label);
	            
	            System.out.println("readList-Size : " + readList.size());
	            for(int i=0; i<readList.size(); i++){
	            	PatientInfoBean_K temp_bean = readList.get(i);
	            	
	            	label = new Label(0, (i+1), (String)temp_bean.getChart_num());
	            	sheet.addCell(label);
	            	
	            	label = new Label(1, (i+1), (String)temp_bean.getName());
	            	sheet.addCell(label);
	            	
	            	label = new Label(2, (i+1), temp_bean.getAge()+"");
	            	sheet.addCell(label);
	            	
	            	label = new Label(3, (i+1), temp_bean.getGender());
	            	sheet.addCell(label);
	            	
	            	label = new Label(4, (i+1), temp_bean.getBabyTooth()+"");
	            	sheet.addCell(label);
	            	
	            	label = new Label(5, (i+1), temp_bean.getPermanentTooth()+"");
	            	sheet.addCell(label);
	            	
	            	label = new Label(6, (i+1), temp_bean.getLosePermanentTooth_front()+"");
	            	sheet.addCell(label);
	            	
	            	label = new Label(7, (i+1), temp_bean.getLosePermanentTooth_back()+"");
	            	sheet.addCell(label);
	            	
	            	label = new Label(8, (i+1), temp_bean.getImplant()+"");
	            	sheet.addCell(label);
	            	
	            	label = new Label(9, (i+1), temp_bean.getDentures()+"");
	            	sheet.addCell(label);
	            	
	            	label = new Label(10, (i+1), temp_bean.getLeaving()+"");
	            	sheet.addCell(label);
	            	
	            	label = new Label(11, (i+1), temp_bean.getTreatment()+"");
	            	sheet.addCell(label);
	            	
	            	label = new Label(12, (i+1), temp_bean.getSulcus()+"");
	            	sheet.addCell(label);
	            	
	            	label = new Label(13, (i+1), temp_bean.getPlaque_score());
	            	sheet.addCell(label);
	            	
	            	label = new Label(14, (i+1), temp_bean.getTartar());
	            	sheet.addCell(label);
	            	
	            	label = new Label(15, (i+1), temp_bean.getGingivitis());
	            	sheet.addCell(label);
	            	
	            	label = new Label(16, (i+1), temp_bean.getMicroscope_sal_amount());
	            	sheet.addCell(label);
	            	
	            	label = new Label(17, (i+1), temp_bean.getMicroscope_sal_movement());
	            	sheet.addCell(label);
	            	
	            	label = new Label(18, (i+1), temp_bean.getMicroscope_gu_amount());
	            	sheet.addCell(label);
	            	
	            	label = new Label(19, (i+1), temp_bean.getMicroscope_gu_movement());
	            	sheet.addCell(label);
	            	
	            	label = new Label(20, (i+1), temp_bean.getSnyder());
	            	sheet.addCell(label);
	            	
	            	label = new Label(21, (i+1), temp_bean.getMalocclusion());
	            	sheet.addCell(label);
	            	
	            	label = new Label(22, (i+1), temp_bean.getOdontoclasis());
	            	sheet.addCell(label);
	            	
	            	label = new Label(23, (i+1), temp_bean.getInfection());
	            	sheet.addCell(label);
	            	
	            	label = new Label(24, (i+1), temp_bean.getBad_breath());
	            	sheet.addCell(label);
	            	
	            	label = new Label(25, (i+1), temp_bean.getBrushMethod());
	            	sheet.addCell(label);
	            	
	            	label = new Label(26, (i+1), temp_bean.getPeriodontal());
	            	sheet.addCell(label);
	            	
	            	label = new Label(27, (i+1), temp_bean.getMobility());
	            	sheet.addCell(label);
	            	
	            	label = new Label(28, (i+1), temp_bean.getMandibular());
	            	sheet.addCell(label);
	            	
	            	label = new Label(29, (i+1), temp_bean.getAesthetic());
	            	sheet.addCell(label);
	            	
	            	label = new Label(30, (i+1), temp_bean.getSaliva());
	            	sheet.addCell(label);
	            	
	            	label = new Label(31, (i+1), temp_bean.getConsistency());
	            	sheet.addCell(label);
	            	
	            	label = new Label(32, (i+1), temp_bean.getWisdomTooth_pain());
	            	sheet.addCell(label);
	            	
	            	label = new Label(33, (i+1), temp_bean.getDazzling());
	            	sheet.addCell(label);
	            	
	            	label = new Label(34, (i+1), temp_bean.getPartialDenture());
	            	sheet.addCell(label);
	            	
	            	label = new Label(35, (i+1), temp_bean.getProsthesis_need());
	            	sheet.addCell(label);
	            	
	            	label = new Label(36, (i+1), temp_bean.getDenture_need());
	            	sheet.addCell(label);
	            	
	            	label = new Label(37, (i+1), temp_bean.getSugar_frequency());
	            	sheet.addCell(label);
	            	
	            	label = new Label(38, (i+1), temp_bean.getBrush_num());
	            	sheet.addCell(label);
	            	
	            	label = new Label(39, (i+1), temp_bean.getChew_food());
	            	sheet.addCell(label);
	            	
	            	label = new Label(40, (i+1), temp_bean.getBrush_time());
	            	sheet.addCell(label);
	            	
	            	label = new Label(41, (i+1), temp_bean.getDisease_num());
	            	sheet.addCell(label);
	            	
	            	label = new Label(42, (i+1), temp_bean.getDisease_kind());
	            	sheet.addCell(label);
	            	
	            	label = new Label(43, (i+1), temp_bean.getPrevention_visit());
	            	sheet.addCell(label);
	            	
	            	label = new Label(44, (i+1), temp_bean.getVisit());
	            	sheet.addCell(label);
	            	
	            	label = new Label(45, (i+1), temp_bean.getScaling());
	            	sheet.addCell(label);
	            	
	            	label = new Label(46, (i+1), temp_bean.getCare_product());
	            	sheet.addCell(label);
	            	
	            	label = new Label(47, (i+1), temp_bean.getDrinking());
	            	sheet.addCell(label);
	            	
	            	label = new Label(48, (i+1), temp_bean.getSmoking());
	            	sheet.addCell(label);
	            	
	            	label = new Label(49, (i+1), temp_bean.getDrink_and_smoke());
	            	sheet.addCell(label);
	            	
	            	label = new Label(50, (i+1), temp_bean.getXerostomia());
	            	sheet.addCell(label);
	            	
	            	label = new Label(51, (i+1), temp_bean.getLearn());
	            	sheet.addCell(label);
	            	
	            	label = new Label(52, (i+1), temp_bean.getPregnancy());
	            	sheet.addCell(label);
	            	
	            }
	            
	            workbook.write();
	            workbook.close();
	            JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
	            
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	}
}