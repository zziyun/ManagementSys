import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Project extends JFrame implements ActionListener {
	private static final int SelectColumn = 0;
	Container cp;
	JTextField tClass, tNum, tName, tCN, tKor, tEng, tMath;
	JButton btnAdd, btnDel, btnMod, btnSave, btnOpen;
	JCheckBox btnNew1, btnNew2,btnNew3;
	DefaultTableModel model;
	JTable table;
	JScrollPane jsp;
	int SelectRow = -1;
	int sum;
	double avg;
	SubFrame sf;
	SubFrame2 sf2;
	SubFrame3 sf3;
	String grade;

	Project(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//��� �������� �� �ݴ´�
		cp = this.getContentPane();//������ ���� �⺻�г��� �ִ�
		cp.setBackground(Color.GRAY);//�⺻ �г� �� ����
		this.setBounds(100, 100, 900, 500);//��ġ�� ũ�� ����

		//�� â�� ����� "���� �׷���"�� �����ִ� ������������ ����
		sf = new SubFrame("���� �׷���");
		sf.close.addActionListener(this);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setDesign();
		this.setVisible(true);
	}
	public void setCoulmnSize(JTable t){
		t.getTableHeader().setReorderingAllowed(false);      
		t.getColumnModel().getColumn(1).setPreferredWidth(25);
		t.getColumnModel().getColumn(3).setPreferredWidth(120);
	}
	public void setDesign() {

		//������ ��� �г� ����
		JPanel pTop = new JPanel();
		pTop.setBackground(Color.LIGHT_GRAY);
		//�⺻ �г��� ���ʿ� ��ġ��Ų��
		cp.add("North", pTop);

		//������ �ϴ� �г� ����
		JPanel pBottom = new JPanel();
		pBottom.setBackground(Color.LIGHT_GRAY);
		pBottom.setLayout(new BorderLayout());
		//�⺻ �г��� ���ʿ� ��ġ��Ų��
		cp.add("South", pBottom);

		//��,��ȣ,�̸�,��ȭ��ȣ,���������� �Է��� �� �ִ� �ؽ�Ʈ�ʵ带 �������ش�
		tName = new JTextField(5);
		tClass = new JTextField(3);
		tNum = new JTextField(3);
		tCN = new JTextField(10);
		tKor = new JTextField(3);
		tEng = new JTextField(3);
		tMath = new JTextField(3);

		JPanel jp3 = new JPanel();
		jp3.setBackground(Color.LIGHT_GRAY);

		jp3.add(new JLabel("����/�̰�: "));
		jp3.add(tClass);
		jp3.add(new JLabel("��ȣ: "));
		jp3.add(tNum);
		jp3.add(new JLabel("�̸�: "));
		jp3.add(tName);
		jp3.add(new JLabel("��ȭ��ȣ: "));
		jp3.add(tCN);
		jp3.add(new JLabel("����: "));
		jp3.add(tKor);
		jp3.add(new JLabel("����: "));
		jp3.add(tEng);
		jp3.add(new JLabel("����: "));
		jp3.add(tMath);

		//pTop�г��� �߾ӿ� ��ġ��Ų��
		pTop.add(jp3, BorderLayout.CENTER);

		//JTable �� ��ܿ� ������ title�� �������ش�
		String[] title = { "����/�̰�", "��ȣ", "�̸�", "��ȭ��ȣ", "����", "����", "����", "����", "���", "����" };
		model = new DefaultTableModel(title, 0);
		table = new JTable(model);
		setCoulmnSize(table);
		// JTable ����Ǿ��ִ� ���� ��� ���
		table.setAutoCreateRowSorter(true);
		table.addMouseListener(new TableEvent());

		//���̺� ��ũ�� ����
		jsp = new JScrollPane(table);
		cp.add("Center", jsp);

		//������ ����,����,����,����,���� �� �� �ִ� ��ư�� �������ش�
		btnAdd = new JButton("�߰�");
		btnDel = new JButton("����");
		btnMod = new JButton("����");
		btnSave = new JButton("����");
		btnOpen = new JButton("����");
		
		//�׷����� ������ �� �� �ִ� üũ�ڽ��� �������ش�
		btnNew1 = new JCheckBox("���� �׷���");
		btnNew2 = new JCheckBox("������ �׷���");
		btnNew3 = new JCheckBox("���� ����");

		//��ư�� ���� ������ �߰��ϴ� ���� JTable�� ����� ����Ͽ� �߰����ش�
		btnAdd.setToolTipText("�Է�â���� �Է� �� �߰���ư Ŭ��");
		btnDel.setToolTipText("������ ���ϴ� �л� Ŭ�� �� ������ư Ŭ��");
		btnMod.setToolTipText("������ ���ϴ� �л� Ŭ�� �� �Է�â���� �����۾��� �ϰ� ������ư Ŭ��");
		btnSave.setToolTipText("�����ư Ŭ�� �� ���ϴ� �����̸� �Է��� ����");
		btnOpen.setToolTipText("�����ư Ŭ�� �� ���ϴ� ���� �ҷ�����");

		//�г��� �ΰ��� ������ ������ �ش��ϴ� ��ư�� ��ġ�� �������ش�
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		
		jp1.add(btnAdd);
		jp1.add(btnDel);
		jp1.add(btnMod);
		jp1.add(btnSave);
		jp1.add(btnOpen);
		
		jp2.add(btnNew1);
		jp2.add(btnNew2);
		jp2.add(btnNew3);

		pBottom.add(jp1, BorderLayout.CENTER);
		pBottom.add(jp2, BorderLayout.EAST);

		//��� ��ư�� �׼Ǹ����ʸ� �޾��ش�
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		btnMod.addActionListener(this);
		btnSave.addActionListener(this);
		btnOpen.addActionListener(this);
		btnNew1.addActionListener(this);
		btnNew2.addActionListener(this);
		btnNew3.addActionListener(this);

	}

	class TableEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			//���õ� ��� ���� �ε����� �����ϴ� ������ �迭
			SelectRow = table.getSelectedRow();
			SelectRow = table.getSelectedRow();
			tClass.setText((String) table.getValueAt(SelectRow, 0));
			tNum.setText((String) table.getValueAt(SelectRow, 1));
			tName.setText((String) table.getValueAt(SelectRow, 2));
			tCN.setText((String) table.getValueAt(SelectRow, 3));
			tKor.setText((String) table.getValueAt(SelectRow, 4));
			tEng.setText((String) table.getValueAt(SelectRow, 5));
			tMath.setText((String) table.getValueAt(SelectRow, 6));
		}
	}
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == btnAdd) {//"�߰�"��ư�� ������
			Vector<String> vc = new Vector<String>();
			vc.add(tClass.getText());
			vc.add(tNum.getText());
			vc.add(tName.getText());
			vc.add(tCN.getText());
			vc.add(tKor.getText());
			vc.add(tEng.getText());
			vc.add(tMath.getText());
			
			//���� ���
			sum = Integer.parseInt(tKor.getText()) + Integer.parseInt(tEng.getText())
			+ Integer.parseInt(tMath.getText());
			vc.add(String.valueOf(sum));
			
			//��� ���
			avg = (double) sum / 3.0;
			avg = Math.round((sum / 3.0) * 100) / 100.0;//�Ҽ��� ���ڸ� ���ϱ��� ���Ѵ�
			vc.add(String.valueOf(avg));
			
			//���� ���� ���Ͽ� ���� �ڵ� �ο�
			if (avg > 90) {
				grade = "A";
			} else if (avg > 70) {
				grade = "B";
			} else if (avg > 50) {
				grade = "C";
			} else if (avg > 30) {
				grade = "D";
			} else if (avg > 0) {
				grade = "F";
			} else {
				grade = "NONE";
			}
			vc.add(String.valueOf(grade));
			
			model.addRow(vc);

			//�Է°��� �����ش�
			tClass.setText("");
			tNum.setText("");
			tName.setText("");
			tCN.setText("");
			tKor.setText("");
			tEng.setText("");
			tMath.setText("");

			//Ŭ������ Ŀ���� �̵���
			tClass.requestFocus();
			
		} else if (ob == btnDel) {//"����" ��ư�� ������ ��
			if (SelectRow < 0) {
				JOptionPane.showMessageDialog(this, "������ �л��� �����ϼ���", "Error", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String msg = table.getValueAt(SelectRow, 2) + " �����Ͻðڽ��ϱ�?";
			int ans = JOptionPane.showConfirmDialog(this, msg, "�����մϴ�", JOptionPane.YES_NO_OPTION);
			if (ans == JOptionPane.YES_OPTION) {
				model.removeRow(SelectRow);
				JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�");
				SelectRow = -1;
			}
			
		} else if (ob == btnMod) {//"����"��ư�� ������ ��
			if (SelectRow < 0) {
				JOptionPane.showMessageDialog(this, "������ �л��� �����ϼ���", "Error", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			int ans = JOptionPane.showConfirmDialog(this, table.getValueAt(SelectRow, 2) + " �����Ͻðڽ��ϱ�?", "����",
					JOptionPane.YES_NO_OPTION);
			
			if (ans == JOptionPane.YES_OPTION) {
				
				//���� ���
				sum = Integer.parseInt(tKor.getText()) + Integer.parseInt(tEng.getText())
				+ Integer.parseInt(tMath.getText());
				
				//��� ���
				avg = Math.round((sum / 3.0) * 100) / 100.0;
				
				//�������� �����Ͽ� ���� �ڵ� �ο�
				if (avg > 90) {
					grade = "A";
				} else if (avg > 70) {
					grade = "B";
				} else if (avg > 50) {
					grade = "C";
				} else if (avg > 30) {
					grade = "D";
				} else if (avg > 0) {
					grade = "F";
				} else {
					grade = "NONE";
				}

				//���̺� �Է°��� �־��ش�
				model.setValueAt(tClass.getText(), SelectRow, 0);
				model.setValueAt(tNum.getText(), SelectRow, 1);
				model.setValueAt(tName.getText(), SelectRow, 2);
				model.setValueAt(tCN.getText(), SelectRow, 3);
				model.setValueAt(tKor.getText(), SelectRow, 4);
				model.setValueAt(tEng.getText(), SelectRow, 5);
				model.setValueAt(tMath.getText(), SelectRow, 6);
				model.setValueAt(String.valueOf(sum), SelectRow, 7);
				model.setValueAt(String.valueOf(avg), SelectRow, 8);
				model.setValueAt(String.valueOf(grade), SelectRow, 9);

				//SelectRow �ʱ�ȭ
				SelectRow = -1;

				//�Է°��� �����ش�
				tClass.setText("");
				tNum.setText("");
				tName.setText("");
				tCN.setText("");
				tKor.setText("");
				tEng.setText("");
				tMath.setText("");

				//Ŭ������ Ŀ���� �̵���
				tClass.requestFocus();
			}
			
		} else if (ob == btnSave) {//"����" ��ư�� ������ ��
			
			FileDialog dig = new FileDialog(this, "���� ����", FileDialog.SAVE);
			dig.setVisible(true);
			
			if (dig.getFile() == null) {
				return;
			}
			
			String fileName = dig.getDirectory() + dig.getFile();
			
			FileWriter fw = null;
			
			try {
				fw = new FileWriter(fileName);
				fw.write(String.valueOf(model.getRowCount())+"\n");
				fw.write(String.valueOf(model.getColumnCount())+"\n");
				for (int i = 0; i < model.getRowCount(); i++) {
					for (int j = 0; j < model.getColumnCount(); j++) {
						String data = (String) model.getValueAt(i, j);
						fw.write(data + "\n");
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {//���ܰ� �߻����� �ʾƵ� ������ ȣ��ȴ�
				
				try {
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		} else if (ob == btnOpen) {//"����"��ư�� ������ ��
			
			FileDialog dig = new FileDialog(this, "���� ����", FileDialog.LOAD);
			dig.setVisible(true);
			
			if (dig.getFile() == null) {
				return;
			}
			
			String fileName = dig.getDirectory() + dig.getFile();
			FileReader fr = null;
			BufferedReader br = null;
			
			try {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				
				//���Ϸκ��� ��� ���� ������ �о �ݺ����� ������
				int row = Integer.parseInt(br.readLine());
				int col = Integer.parseInt(br.readLine());
				
				String[] str = new String[col];
				
				//���̺��� ���� �����͸� ��� ���� �� �� �߰����ش�
				model.setRowCount(0);
				
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < col; j++) {
						String data = br.readLine();
						str[j] = data;
					}
					model.addRow(str);
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else if (ob == btnNew1) {//"���� �׷���" üũ�ڽ��� ������
			sf.setLocation(970,40);
			sf.setVisible(true);
			
		} else if (ob == sf.close) {//"close"��ư�� ������
			btnNew1.setSelected(false);//"���� �׷���" üũ�ڽ� ������ ��ҵȴ�
			sf.dispose();
			
		} else if (ob == btnNew2) {//"������ �׷���"��ư�� ������
			sf2 = new SubFrame2("������ �׷���");
			sf2.close.addActionListener(this);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			sf2.setSize(600, 480);
			sf2.setLocation(970, 550);
			sf2.setVisible(true);
			
		}else if (ob == sf2.close) {//"close"��ư�� ������
			btnNew2.setSelected(false);//"������ �׷���" üũ�ڽ� ������ ��ҵȴ�
			sf2.dispose();
			
		}	else if (ob == btnNew3) {//"���� ����" ��ư�� ������
			sf3 = new SubFrame3("���� ����");
			sf3.close.addActionListener(this);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			sf3.setSize(550, 550);
			sf3.setLocation(1250, 300);
			sf3.setVisible(true);
			
		} else if (ob == sf3.close) {//"close"��ư�� ������
			btnNew3.setSelected(false);//"���� ����" üũ�ڽ� ������ ��ҵȴ�
			sf3.dispose();
		}
	}

	class SubFrame extends JFrame {//"���� �׷���" �� ��Ÿ���� ���� ������
		Canvas can;
		Button close;

		public SubFrame(String title) {
			super(title);
			setSize(600, 500);
			JPanel jp = new JPanel();

			myPanel drawingPanel = new myPanel();
			add(drawingPanel, BorderLayout.CENTER);
			
			//"����","����","����"�� ���� ���� ��ư�� �������ش�
			JRadioButton rb1 = new JRadioButton("����");
			JRadioButton rb2 = new JRadioButton("����");
			JRadioButton rb3 = new JRadioButton("����");

			ButtonGroup group = new ButtonGroup();
			group.add(rb1);
			group.add(rb2);
			group.add(rb3);

			rb1.addActionListener(new ActionListener() {//"����"��ư ���ý�
				public void actionPerformed(ActionEvent e) {
					int row = table.getRowCount();
					Object[] val = new Object[row];
					Vector<Integer> vector = new Vector<Integer>();
					
					//���� ���� �����͸� �������ش�
					for (int i = 0; i < val.length; i++) {
						vector.add(Integer.parseInt((String) table.getValueAt(i, 4)));
					}
					
					int a[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
					
					for (int i = 0; i < vector.size(); i++) {
						if (vector.elementAt(i) == 100)//100 ��
							a[9] += 1;
						else if (vector.elementAt(i) >= 90)//90-99��
							a[8]++;
						else if (vector.elementAt(i) >= 80)//80-89��
							a[7]++;
						else if (vector.elementAt(i) >= 70)//70-79��
							a[6]++;
						else if (vector.elementAt(i) >= 60)//60-69��
							a[5]++;
						else if (vector.elementAt(i) >= 50)//50-59��
							a[4]++;
						else if (vector.elementAt(i) >= 40)//40-49��
							a[3]++;
						else if (vector.elementAt(i) >= 30)//30-39��
							a[2]++;
						else if (vector.elementAt(i) >= 20)//20-29��
							a[1]++;
						else if (vector.elementAt(i) >= 10)//10-19��
							a[0]++;
					}//���� ���ý�, �ּ������� 10���̹Ƿ� 10�� �̸��� �������� �ʴ´�
					
					drawingPanel.setColor(new Color(255, 85, 85));//�׷��� ���� ����
					drawingPanel.setScores(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9]);
					drawingPanel.repaint();
				}
			});
			
			rb2.addActionListener(new ActionListener() {//"���� "��ư ���ý�
				public void actionPerformed(ActionEvent e) {
					
					int row = table.getRowCount();
					Object[] val = new Object[row];
					Vector<Integer> vector = new Vector<Integer>();
					
					//���� ���� �����͸� �������ش�
					for (int i = 0; i < val.length; i++) {
						vector.add(Integer.parseInt((String) table.getValueAt(i, 5)));
					}
					
					int b[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
					
					for (int i = 0; i < vector.size(); i++) {
						if (vector.elementAt(i) == 100)
							b[9] += 1;
						else if (vector.elementAt(i) >= 90)
							b[8]++;
						else if (vector.elementAt(i) >= 80)
							b[7]++;
						else if (vector.elementAt(i) >= 70)
							b[6]++;
						else if (vector.elementAt(i) >= 60)
							b[5]++;
						else if (vector.elementAt(i) >= 50)
							b[4]++;
						else if (vector.elementAt(i) >= 40)
							b[3]++;
						else if (vector.elementAt(i) >= 30)
							b[2]++;
						else if (vector.elementAt(i) >= 20)
							b[1]++;
						else if (vector.elementAt(i) >= 10)
							b[0]++;
					}
					
					drawingPanel.setColor(new Color(85, 170, 85));
					drawingPanel.setScores(b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7], b[8], b[9]);
					drawingPanel.repaint();

				}
			});
			
			rb3.addActionListener(new ActionListener() {//"���� "��ư ���ý�
				public void actionPerformed(ActionEvent e) {
					
					int row = table.getRowCount();
					Object[] val = new Object[row];
					Vector<Integer> vector = new Vector<Integer>();
					
					//���� ���� �����͸� �������ش�
					for (int i = 0; i < val.length; i++) {
						vector.add(Integer.parseInt((String) table.getValueAt(i, 6)));
					}
					
					int c[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
					
					for (int i = 0; i < vector.size(); i++) {
						if (vector.elementAt(i) == 100)
							c[9] += 1;
						else if (vector.elementAt(i) >= 90)
							c[8]++;
						else if (vector.elementAt(i) >= 80)
							c[7]++;
						else if (vector.elementAt(i) >= 70)
							c[6]++;
						else if (vector.elementAt(i) >= 60)
							c[5]++;
						else if (vector.elementAt(i) >= 50)
							c[4]++;
						else if (vector.elementAt(i) >= 40)
							c[3]++;
						else if (vector.elementAt(i) >= 30)
							c[2]++;
						else if (vector.elementAt(i) >= 20)
							c[1]++;
						else if (vector.elementAt(i) >= 10)
							c[0]++;
					}
					
					drawingPanel.setColor(new Color(85, 85, 225));
					drawingPanel.setScores(c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
					drawingPanel.repaint();
				}
			});
			
			//"����","����","����" ���� ��ư�� �߰����ش�
			jp.add(rb1);
			jp.add(rb2);
			jp.add(rb3);

			//��ư�� ���ʿ� ��ġ�����ش�
			add(jp, "North");
			
			//"CLOSE"��ư�� ���ʿ� ��ġ�����ش�
			close = new Button("CLOSE");
			add(close, "South");
			
		}

		class myPanel extends JPanel {
			int a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
			Color color;

			public void paint(Graphics g) {
				
				g.clearRect(0, 0, getWidth(), getHeight());
				
				// �⺻ ����� �� �߱�
				g.drawLine(100, 350, 500, 350);
				for (int cnt = 1; cnt < 11; cnt++) {
					g.drawString(cnt + "", 70, 355 - 27 * cnt);
					g.drawLine(100, 350 - 27 * cnt, 500, 350 - 27 * cnt);
				}
				g.drawLine(100, 50, 100, 350);
				g.drawString("10", 110, 370);
				g.drawString("20", 151, 370);
				g.drawString("30", 192, 370);
				g.drawString("40", 233, 370);
				g.drawString("50", 274, 370);
				g.drawString("60", 315, 370);
				g.drawString("70", 356, 370);
				g.drawString("80", 397, 370);
				g.drawString("90", 438, 370);
				g.drawString("100", 475, 370);
				g.setColor(color);

				
				//������ ���� �׷��� ����
				g.fillRect(110, 350 - a1 * 27, 10, a1 * 27);
				g.fillRect(151, 350 - a2 * 27, 10, a2 * 27);
				g.fillRect(192, 350 - a3 * 27, 10, a3 * 27);
				g.fillRect(233, 350 - a4 * 27, 10, a4 * 27);
				g.fillRect(274, 350 - a5 * 27, 10, a5 * 27);
				g.fillRect(315, 350 - a6 * 27, 10, a6 * 27);
				g.fillRect(356, 350 - a7 * 27, 10, a7 * 27);
				g.fillRect(397, 350 - a8 * 27, 10, a8 * 27);
				g.fillRect(438, 350 - a9 * 27, 10, a9 * 27);
				g.fillRect(480, 350 - a10 * 27, 10, a10 * 27);

			}

			void setScores(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int a10) {
				this.a1 = a1;
				this.a2 = a2;
				this.a3 = a3;
				this.a4 = a4;
				this.a5 = a5;
				this.a6 = a6;
				this.a7 = a7;
				this.a8 = a8;
				this.a9 = a9;
				this.a10 = a10;
			}

			void setColor(Color color) {
				this.color = color;
			}
		}

	}

	class SubFrame2 extends JFrame {//"������ �׷���" �� ��Ÿ���� ���� ������
		Canvas can;
		Button close;

		private int[] arcAngle = new int[5];
		
		//�׷��� ���� ����
		private Color[] color = { new Color(247, 163, 91), new Color(124, 181, 236), new Color(233, 91, 129),
				new Color(100,100,100), new Color(158, 225, 91) };
		
		private String[] itemName = { "A", "B", "C", "D", "F" };
		Checkbox cb1, cb2, cb3, cb4, cb5;
		CheckboxGroup group;
		private ChartPanel chartPanel = new ChartPanel();
		private int a, b, c, d, f;

		public SubFrame2(String title) {
			super("������ �׷���");
			
			can = new Canvas();
			add(can, "Center");
			
			close = new Button("CLOSE");
			add(close, "South");

			Container c = getContentPane();
			c.add(chartPanel, BorderLayout.CENTER);
			
			drawChart();
		}

		private void drawChart() {
			int row = table.getRowCount();
			int sum = 0;
			
			//���� ������ �迭 ����
			Object[] val = new Object[row];
			
			for (int j = 9; j < 10; j++) {
				for (int i = 0; i < val.length; i++) {
					
					//������ ����Ǵ� ���� ��� ���� �迭�� �����Ѵ�
					val[i] = table.getValueAt(i, j);

					//����� ���� ������ ���ڿ��� ���Ͽ� �� ���ڿ��� �����ϸ� ������ �������
					if ("A".equals(val[i])) {
						a = a + 1;
					} else if ("B".equals(val[i])) {
						b = b + 1;
					} else if ("C".equals(val[i])) {
						c = c + 1;
					} else if ("D".equals(val[i])) {
						d = d + 1;
					} else if ("F".equals(val[i])) {
						f = f + 1;
					}
				}

			}
			
			//��ü �л� ��
			sum = a + b + c + d + f;
			
			if (sum == 0)
				return;

			//������� �� ������ ������ �̿��Ͽ� �� ������ �׷������� �����ϰ� �Ǵ� ������ ����Ѵ�
			arcAngle[0] = (int) Math.round(a / (double) sum * 360);
			arcAngle[1] = (int) Math.round(b / (double) sum * 360);
			arcAngle[2] = (int) Math.round(c / (double) sum * 360);
			arcAngle[3] = (int) Math.round(d / (double) sum * 360);
			arcAngle[4] = (int) Math.round(f / (double) sum * 360);

			chartPanel.repaint();
		}

		private class ChartPanel extends JPanel {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int startAngle = 0;
				
				//�� ������ �����θ� �����ϴ��� �гο� ������ش�
				for (int i = 0; i < 5; i++) {
					g.setColor(color[i]);
					g.drawString(itemName[i] + " " + Math.round(arcAngle[i] * 100. / 360.) + "%", 50 + i * 100, 20);
				}
				
				//�� ������ �����ϴ� ������ �������� ������Ʈ�� �׷� ����Ѵ�
				for (int i = 0; i < 5; i++) {
					g.setColor(color[i]);
					g.fillArc(130, 70, 300, 300, startAngle, arcAngle[i]);
					startAngle = startAngle + arcAngle[i];
				}
			}
		}

	}
	
	class SubFrame3 extends JFrame {//"���� ����" �� ��Ÿ���� ���� ������
		
		//�޺��ڽ� �ȿ� �� ����
		private	String[] line = { "���� �ֿ����", "���� �����", "���� �����", "���� �����", "���� �����","���� ����" };

		private ImageIcon[] images={//���� �̹��������� ������´�
				new ImageIcon("images/�ֿ����.jpg"),
				new ImageIcon("images/�����.jpg"),
				new ImageIcon("images/���� �����.jpg"),
				new ImageIcon("images/���п����.jpg"),
				new ImageIcon("images/���� �����.jpg"),
				new ImageIcon("images/��������.jpg")
		};
		
		private JLabel imageLabel=new JLabel(images[0]);
		private JComboBox<String> Line=new JComboBox<String>(line);
		private JPanel jp = new JPanel();
		
		Canvas can;
		Button close;

		 public SubFrame3(String title) {
			super("���� ���� ����");
			Container c= getContentPane();
			c.setLayout(new BorderLayout());
			
			jp.setLayout(new FlowLayout(FlowLayout.LEFT,30,30));

			//���� �̹����� ��θ� �������� ���ʿ� ��Ÿ���ش٤�
			JLabel la = new JLabel("���� ���: D:\\JAVA\\Example2\\images");			
			add(la,"North");
			
			//�޺��ڽ��� �̹����� jp�гο� �߰��Ѵ�
			jp.add(Line);
			jp.add(imageLabel);

			//jp�г��� �������� ���Ϳ� ��ġ��Ų��
			c.add(jp,"Center");

			close = new Button("CLOSE");
			c.add(close, "South");
			
			Line.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox<String> cb=(JComboBox<String>)e.getSource();

					int index= cb.getSelectedIndex();//������ �������� �ε�����ȣ �˾Ƴ���

					imageLabel.setIcon(images[index]);//�̹����� ���̺� ������Ʈ�� ����Ѵ�
				}
			});
		}
	}
	
	
	public static void main(String[] args) {
		new Project("����ó�����α׷�");
	}
}