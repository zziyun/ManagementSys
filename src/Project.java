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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//모든 프레임을 다 닫는다
		cp = this.getContentPane();//프레임 위에 기본패널이 있다
		cp.setBackground(Color.GRAY);//기본 패널 색 설정
		this.setBounds(100, 100, 900, 500);//위치와 크기 설정

		//새 창을 띄워서 "과목별 그래프"를 보여주는 서브프레임을 생성
		sf = new SubFrame("과목별 그래프");
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

		//프레임 상단 패널 생성
		JPanel pTop = new JPanel();
		pTop.setBackground(Color.LIGHT_GRAY);
		//기본 패널의 북쪽에 위치시킨다
		cp.add("North", pTop);

		//프레임 하단 패널 생성
		JPanel pBottom = new JPanel();
		pBottom.setBackground(Color.LIGHT_GRAY);
		pBottom.setLayout(new BorderLayout());
		//기본 패널의 남쪽에 위치시킨다
		cp.add("South", pBottom);

		//반,번호,이름,전화번호,시험점수를 입력할 수 있는 텍스트필드를 생성해준다
		tName = new JTextField(5);
		tClass = new JTextField(3);
		tNum = new JTextField(3);
		tCN = new JTextField(10);
		tKor = new JTextField(3);
		tEng = new JTextField(3);
		tMath = new JTextField(3);

		JPanel jp3 = new JPanel();
		jp3.setBackground(Color.LIGHT_GRAY);

		jp3.add(new JLabel("문과/이과: "));
		jp3.add(tClass);
		jp3.add(new JLabel("번호: "));
		jp3.add(tNum);
		jp3.add(new JLabel("이름: "));
		jp3.add(tName);
		jp3.add(new JLabel("전화번호: "));
		jp3.add(tCN);
		jp3.add(new JLabel("국어: "));
		jp3.add(tKor);
		jp3.add(new JLabel("영어: "));
		jp3.add(tEng);
		jp3.add(new JLabel("수학: "));
		jp3.add(tMath);

		//pTop패널의 중앙에 위치시킨다
		pTop.add(jp3, BorderLayout.CENTER);

		//JTable 맨 상단에 쓰여진 title을 지정해준다
		String[] title = { "문과/이과", "번호", "이름", "전화번호", "국어", "영어", "수학", "총점", "평균", "학점" };
		model = new DefaultTableModel(title, 0);
		table = new JTable(model);
		setCoulmnSize(table);
		// JTable 내장되어있는 정렬 기능 사용
		table.setAutoCreateRowSorter(true);
		table.addMouseListener(new TableEvent());

		//테이블에 스크롤 생성
		jsp = new JScrollPane(table);
		cp.add("Center", jsp);

		//정보를 수정,삭제,수정,저장,열기 할 수 있는 버튼을 생성해준다
		btnAdd = new JButton("추가");
		btnDel = new JButton("삭제");
		btnMod = new JButton("수정");
		btnSave = new JButton("저장");
		btnOpen = new JButton("열기");
		
		//그래프와 상장을 볼 수 있는 체크박스를 생성해준다
		btnNew1 = new JCheckBox("과목별 그래프");
		btnNew2 = new JCheckBox("학점별 그래프");
		btnNew3 = new JCheckBox("상장 형식");

		//버튼에 대한 설명을 추가하는 글을 JTable의 기능을 사용하여 추가해준다
		btnAdd.setToolTipText("입력창에서 입력 후 추가버튼 클릭");
		btnDel.setToolTipText("삭제를 원하는 학생 클릭 후 삭제버튼 클릭");
		btnMod.setToolTipText("수정을 원하는 학생 클릭 후 입력창에서 수정작업을 하고 수정버튼 클릭");
		btnSave.setToolTipText("저장버튼 클릭 후 원하는 파일이름 입력후 저장");
		btnOpen.setToolTipText("열기버튼 클릭 후 원하는 파일 불러오기");

		//패널을 두개로 나누어 각각에 해당하는 버튼의 위치를 설정해준다
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

		//모든 버튼에 액션리스너를 달아준다
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
			//선택된 모든 행의 인덱스를 포함하는 정수의 배열
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
		if (ob == btnAdd) {//"추가"버튼을 누르면
			Vector<String> vc = new Vector<String>();
			vc.add(tClass.getText());
			vc.add(tNum.getText());
			vc.add(tName.getText());
			vc.add(tCN.getText());
			vc.add(tKor.getText());
			vc.add(tEng.getText());
			vc.add(tMath.getText());
			
			//총점 계산
			sum = Integer.parseInt(tKor.getText()) + Integer.parseInt(tEng.getText())
			+ Integer.parseInt(tMath.getText());
			vc.add(String.valueOf(sum));
			
			//평균 계산
			avg = (double) sum / 3.0;
			avg = Math.round((sum / 3.0) * 100) / 100.0;//소수점 두자리 이하까지 구한다
			vc.add(String.valueOf(avg));
			
			//학점 기준 정하여 학점 자동 부여
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

			//입력값을 지워준다
			tClass.setText("");
			tNum.setText("");
			tName.setText("");
			tCN.setText("");
			tKor.setText("");
			tEng.setText("");
			tMath.setText("");

			//클래스로 커서를 이동함
			tClass.requestFocus();
			
		} else if (ob == btnDel) {//"삭제" 버튼을 눌렀을 떄
			if (SelectRow < 0) {
				JOptionPane.showMessageDialog(this, "삭제할 학생을 선택하세요", "Error", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String msg = table.getValueAt(SelectRow, 2) + " 삭제하시겠습니까?";
			int ans = JOptionPane.showConfirmDialog(this, msg, "삭제합니다", JOptionPane.YES_NO_OPTION);
			if (ans == JOptionPane.YES_OPTION) {
				model.removeRow(SelectRow);
				JOptionPane.showMessageDialog(this, "삭제되었습니다");
				SelectRow = -1;
			}
			
		} else if (ob == btnMod) {//"수정"버튼을 눌렀을 때
			if (SelectRow < 0) {
				JOptionPane.showMessageDialog(this, "수정할 학생을 선택하세요", "Error", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			int ans = JOptionPane.showConfirmDialog(this, table.getValueAt(SelectRow, 2) + " 수정하시겠습니까?", "수정",
					JOptionPane.YES_NO_OPTION);
			
			if (ans == JOptionPane.YES_OPTION) {
				
				//총점 계산
				sum = Integer.parseInt(tKor.getText()) + Integer.parseInt(tEng.getText())
				+ Integer.parseInt(tMath.getText());
				
				//평균 계산
				avg = Math.round((sum / 3.0) * 100) / 100.0;
				
				//학점기준 설정하여 학점 자동 부여
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

				//테이블에 입력값을 넣어준다
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

				//SelectRow 초기화
				SelectRow = -1;

				//입력값을 지워준다
				tClass.setText("");
				tNum.setText("");
				tName.setText("");
				tCN.setText("");
				tKor.setText("");
				tEng.setText("");
				tMath.setText("");

				//클래스로 커서를 이동함
				tClass.requestFocus();
			}
			
		} else if (ob == btnSave) {//"저장" 버튼을 눌렀을 때
			
			FileDialog dig = new FileDialog(this, "파일 저장", FileDialog.SAVE);
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
			} finally {//예외가 발생하지 않아도 무조건 호출된다
				
				try {
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		} else if (ob == btnOpen) {//"열기"버튼을 눌렀을 떄
			
			FileDialog dig = new FileDialog(this, "파일 열기", FileDialog.LOAD);
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
				
				//파일로부터 행과 열의 갯수를 읽어서 반복문을 돌린다
				int row = Integer.parseInt(br.readLine());
				int col = Integer.parseInt(br.readLine());
				
				String[] str = new String[col];
				
				//테이블의 이전 데이터를 모두 삭제 한 후 추가해준다
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
			
		} else if (ob == btnNew1) {//"과목별 그래프" 체크박스를 누르면
			sf.setLocation(970,40);
			sf.setVisible(true);
			
		} else if (ob == sf.close) {//"close"버튼을 누르면
			btnNew1.setSelected(false);//"과목별 그래프" 체크박스 선택이 취소된다
			sf.dispose();
			
		} else if (ob == btnNew2) {//"학점별 그래프"버튼을 누르면
			sf2 = new SubFrame2("학점별 그래프");
			sf2.close.addActionListener(this);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			sf2.setSize(600, 480);
			sf2.setLocation(970, 550);
			sf2.setVisible(true);
			
		}else if (ob == sf2.close) {//"close"버튼을 누르면
			btnNew2.setSelected(false);//"학점별 그래프" 체크박스 선택이 취소된다
			sf2.dispose();
			
		}	else if (ob == btnNew3) {//"상장 형식" 버튼을 누르면
			sf3 = new SubFrame3("상장 형식");
			sf3.close.addActionListener(this);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			sf3.setSize(550, 550);
			sf3.setLocation(1250, 300);
			sf3.setVisible(true);
			
		} else if (ob == sf3.close) {//"close"버튼을 누르면
			btnNew3.setSelected(false);//"상장 형식" 체크박스 선택이 취소된다
			sf3.dispose();
		}
	}

	class SubFrame extends JFrame {//"과목별 그래프" 를 나타내는 서브 프레임
		Canvas can;
		Button close;

		public SubFrame(String title) {
			super(title);
			setSize(600, 500);
			JPanel jp = new JPanel();

			myPanel drawingPanel = new myPanel();
			add(drawingPanel, BorderLayout.CENTER);
			
			//"국어","영어","수학"에 대한 라디오 버튼을 생성해준다
			JRadioButton rb1 = new JRadioButton("국어");
			JRadioButton rb2 = new JRadioButton("영어");
			JRadioButton rb3 = new JRadioButton("수학");

			ButtonGroup group = new ButtonGroup();
			group.add(rb1);
			group.add(rb2);
			group.add(rb3);

			rb1.addActionListener(new ActionListener() {//"국어"버튼 선택시
				public void actionPerformed(ActionEvent e) {
					int row = table.getRowCount();
					Object[] val = new Object[row];
					Vector<Integer> vector = new Vector<Integer>();
					
					//국어 열의 데이터를 저장해준다
					for (int i = 0; i < val.length; i++) {
						vector.add(Integer.parseInt((String) table.getValueAt(i, 4)));
					}
					
					int a[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
					
					for (int i = 0; i < vector.size(); i++) {
						if (vector.elementAt(i) == 100)//100 점
							a[9] += 1;
						else if (vector.elementAt(i) >= 90)//90-99점
							a[8]++;
						else if (vector.elementAt(i) >= 80)//80-89점
							a[7]++;
						else if (vector.elementAt(i) >= 70)//70-79점
							a[6]++;
						else if (vector.elementAt(i) >= 60)//60-69점
							a[5]++;
						else if (vector.elementAt(i) >= 50)//50-59점
							a[4]++;
						else if (vector.elementAt(i) >= 40)//40-49점
							a[3]++;
						else if (vector.elementAt(i) >= 30)//30-39점
							a[2]++;
						else if (vector.elementAt(i) >= 20)//20-29점
							a[1]++;
						else if (vector.elementAt(i) >= 10)//10-19점
							a[0]++;
					}//시험 응시시, 최소점수가 10점이므로 10점 미만은 존재하지 않는다
					
					drawingPanel.setColor(new Color(255, 85, 85));//그래프 색상 설정
					drawingPanel.setScores(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9]);
					drawingPanel.repaint();
				}
			});
			
			rb2.addActionListener(new ActionListener() {//"영어 "버튼 선택시
				public void actionPerformed(ActionEvent e) {
					
					int row = table.getRowCount();
					Object[] val = new Object[row];
					Vector<Integer> vector = new Vector<Integer>();
					
					//영어 열의 데이터를 저장해준다
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
			
			rb3.addActionListener(new ActionListener() {//"수학 "버튼 선택시
				public void actionPerformed(ActionEvent e) {
					
					int row = table.getRowCount();
					Object[] val = new Object[row];
					Vector<Integer> vector = new Vector<Integer>();
					
					//수학 열의 데이터를 저장해준다
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
			
			//"국어","영어","수학" 라디오 버튼을 추가해준다
			jp.add(rb1);
			jp.add(rb2);
			jp.add(rb3);

			//버튼은 북쪽에 위치시켜준다
			add(jp, "North");
			
			//"CLOSE"버튼은 남쪽에 위치시켜준다
			close = new Button("CLOSE");
			add(close, "South");
			
		}

		class myPanel extends JPanel {
			int a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
			Color color;

			public void paint(Graphics g) {
				
				g.clearRect(0, 0, getWidth(), getHeight());
				
				// 기본 토대의 선 긋기
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

				
				//점수별 막대 그래프 생성
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

	class SubFrame2 extends JFrame {//"학점별 그래프" 를 나타내는 서브 프레임
		Canvas can;
		Button close;

		private int[] arcAngle = new int[5];
		
		//그래프 색상 지정
		private Color[] color = { new Color(247, 163, 91), new Color(124, 181, 236), new Color(233, 91, 129),
				new Color(100,100,100), new Color(158, 225, 91) };
		
		private String[] itemName = { "A", "B", "C", "D", "F" };
		Checkbox cb1, cb2, cb3, cb4, cb5;
		CheckboxGroup group;
		private ChartPanel chartPanel = new ChartPanel();
		private int a, b, c, d, f;

		public SubFrame2(String title) {
			super("학점별 그래프");
			
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
			
			//값을 저장할 배열 생성
			Object[] val = new Object[row];
			
			for (int j = 9; j < 10; j++) {
				for (int i = 0; i < val.length; i++) {
					
					//학점이 저장되는 열의 모든 값을 배열에 저장한다
					val[i] = table.getValueAt(i, j);

					//저장된 값과 학점의 문자열을 비교하여 두 문자열이 동일하면 갯수를 세어나간다
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
			
			//전체 학생 수
			sum = a + b + c + d + f;
			
			if (sum == 0)
				return;

			//세어놓은 각 학점의 갯수를 이용하여 각 학점이 그래프에서 차지하게 되는 각도를 계산한다
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
				
				//각 학점이 몇프로를 차지하는지 패널에 출력해준다
				for (int i = 0; i < 5; i++) {
					g.setColor(color[i]);
					g.drawString(itemName[i] + " " + Math.round(arcAngle[i] * 100. / 360.) + "%", 50 + i * 100, 20);
				}
				
				//각 학점이 차지하는 비율을 바탕으로 파이차트를 그려 출력한다
				for (int i = 0; i < 5; i++) {
					g.setColor(color[i]);
					g.fillArc(130, 70, 300, 300, startAngle, arcAngle[i]);
					startAngle = startAngle + arcAngle[i];
				}
			}
		}

	}
	
	class SubFrame3 extends JFrame {//"상장 형식" 를 나타내는 서브 프레임
		
		//콤보박스 안에 들어갈 내용
		private	String[] line = { "성적 최우수상", "성적 우수상", "국어 우수상", "수학 우수상", "영어 우수상","성적 향상상" };

		private ImageIcon[] images={//상장 이미지파일을 가지고온다
				new ImageIcon("images/최우수상.jpg"),
				new ImageIcon("images/우수상.jpg"),
				new ImageIcon("images/국어 우수상.jpg"),
				new ImageIcon("images/수학우수상.jpg"),
				new ImageIcon("images/영어 우수상.jpg"),
				new ImageIcon("images/성적향상상.jpg")
		};
		
		private JLabel imageLabel=new JLabel(images[0]);
		private JComboBox<String> Line=new JComboBox<String>(line);
		private JPanel jp = new JPanel();
		
		Canvas can;
		Button close;

		 public SubFrame3(String title) {
			super("상장 형식 보기");
			Container c= getContentPane();
			c.setLayout(new BorderLayout());
			
			jp.setLayout(new FlowLayout(FlowLayout.LEFT,30,30));

			//상장 이미지의 경로를 프레임의 북쪽에 나타내준다ㄴ
			JLabel la = new JLabel("파일 경로: D:\\JAVA\\Example2\\images");			
			add(la,"North");
			
			//콤보박스와 이미지를 jp패널에 추가한다
			jp.add(Line);
			jp.add(imageLabel);

			//jp패널은 프레임의 센터에 위치시킨다
			c.add(jp,"Center");

			close = new Button("CLOSE");
			c.add(close, "South");
			
			Line.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox<String> cb=(JComboBox<String>)e.getSource();

					int index= cb.getSelectedIndex();//선택한 아이템의 인덱스번호 알아내기

					imageLabel.setIcon(images[index]);//이미지를 레이블 컴포넌트에 출력한다
				}
			});
		}
	}
	
	
	public static void main(String[] args) {
		new Project("성적처리프로그램");
	}
}