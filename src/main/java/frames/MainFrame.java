package frames;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import java.io.IOException;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DB.DBUtil;
import DB.Login;
import dto.Member;

import test.com.board.control.BoardDAO;
import test.com.board.control.BoardVO;
import test.com.board.model.BoardDAOImpl;
import test.com.board.view.BoardInsert;
import test.com.board.view.BoardUpdate;

public class MainFrame extends JFrame {

	private JTable table;
	private JTextField searchString;

	public MainFrame(Member member) {
		setTitle("성공인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel groundPane = new JPanel();
		setContentPane(groundPane);
		groundPane.setBackground(Color.white);

		setResizable(false);
		groundPane.setLayout(null);

		// 상단 (검색 입력창(돋보기 이미지+JTextField),로고 이미지,로그인 버튼)

		// 검색 입력창
		// 돋보기
		ImageIcon icon1 = new ImageIcon("images/돋보기.png");
		Image image1 = icon1.getImage();
		Image image5 = image1.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		ImageIcon image6 = new ImageIcon(image5);
		JLabel imageLabel2 = new JLabel();
		imageLabel2.setIcon(image6);
		imageLabel2.setBounds(10, 50, 40, 40);
		groundPane.add(imageLabel2);

		// 검색
		RoundJTextField searchTF = new RoundJTextField(250);
		searchTF.setBounds(50, 50, 250, 40);
		groundPane.add(searchTF);

		// 로고
		ImageIcon icon = new ImageIcon("images/logo.jpg");
		Image image = icon.getImage();
		Image image2 = image.getScaledInstance(200, 90, java.awt.Image.SCALE_SMOOTH);
		ImageIcon image3 = new ImageIcon(image2);
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(image3);
		imageLabel.setBounds(650, 0, 200, 90);
		groundPane.add(imageLabel);

		JLabel welcome = new JLabel(member.getName() + "님 환영합니다!");
		welcome.setFont(new Font("나눔고딕", Font.PLAIN, 17));
		welcome.setBounds(1100, 50, 200, 40);
		groundPane.add(welcome);

		// 로그인 버튼
		JButton btnLogin = new JButton("로그아웃");
		btnLogin.setBounds(1300, 50, 100, 40);
		btnLogin.setBackground(Color.white);
		btnLogin.setFont(new Font("나눔고딕", Font.PLAIN, 17));
		groundPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginUI();
				dispose();
			}

		});

		// 메뉴 카테고리 (JPanel+JLabel에 액션이벤트)
		JPanel categoryPane = new JPanel();
		categoryPane.setBackground(new Color(153, 204, 255, 100));
		categoryPane.setBounds(10, 100, 1470, 40);
		groundPane.add(categoryPane);

		JLabel[] category = new JLabel[5];
		String[] cNames = { "공지사항", "자유게시판", "구직정보", "회사후기", "면접" };
		for (int i = 0; i < category.length; i++) {
			category[i] = new JLabel("  " + cNames[i] + "  |");
			category[i].setFont(new Font("나눔고딕", Font.BOLD, 26));
			categoryPane.add(category[i]);
		}

		// 공지사항!
		JPanel noticePane = new JPanel();
		noticePane.setBackground(new Color(153, 204, 255, 20));
		noticePane.setBounds(10, 150, 400, 350);
		groundPane.add(noticePane);
		noticePane.setLayout(null);

		JLabel ntTitle = new JLabel("학내 공지사항");
		ntTitle.setFont(new Font("나눔고딕", Font.BOLD, 26));
		noticePane.add(ntTitle);
		ntTitle.setBounds(10, 10, 200, 26);

		Notices nt = new Notices();
		String[] notices = nt.Notices();
		JLabel[] nts = new JLabel[10];
		for (int i = 0; i < notices.length; i++) {
			nts[i] = new JLabel(notices[i]);
			nts[i].setFont(new Font("나눔고딕", Font.PLAIN, 17));
			nts[i].setBounds(10, 50 + (i * 25), 380, 18);
			noticePane.add(nts[i]);
		}

		// 회북이
		JPanel left = new JPanel();
		left.setBackground(new Color(153, 204, 255, 20));
		left.setBounds(10, 510, 400, 330);
		groundPane.add(left);
		left.setLayout(null);

		ImageIcon iconh = new ImageIcon("images/회북이.jpg");
		Image imagehh=iconh.getImage();
		Image imagehhh=imagehh.getScaledInstance(400, 330, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageH=new ImageIcon(imagehhh);
		JLabel imageT = new JLabel();
		imageT.setIcon(imageH);
		left.add(imageT);
		imageT.setBounds(0,0,400,330);
		
		

		// 자유게시판

		JPanel freeBoard = new JPanel();
		freeBoard.setBounds(420, 150, 640, 700);
		freeBoard.setLayout(null);
		freeBoard.setBackground(Color.white);
		freeBoard.setBorder(new TitledBorder(new LineBorder(Color.black, 2)));

		JLabel fbTitle = new JLabel("익명게시판");
		fbTitle.setFont(new Font("나눔고딕", Font.BOLD, 26));
		freeBoard.add(fbTitle);
		fbTitle.setBounds(10, 10, 200, 26);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 79, 600, 600);
		freeBoard.add(scrollPane);

		BoardDAO dao = new BoardDAOImpl();
		List<BoardVO> list = dao.select();

		String[] colNames = new String[] { "글번호", "제목", "내용...", "작성자", "작성일" };
		Object[][] rowDatas = new Object[list.size()][colNames.length];

		for (int i = 0; i < list.size(); i++) {
			rowDatas[i] = new Object[] { list.get(i).getNum(), list.get(i).getTitle(), list.get(i).getContent(),
					list.get(i).getName(), list.get(i).getRegDate() };
		}
		table = new JTable();
		table.setModel(new DefaultTableModel(rowDatas, colNames) {
			boolean[] columnEditables = new boolean[] { false, false, false, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(164);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(140);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int rowNum = table.getSelectedRow();
				BoardVO vos = new BoardVO();
				vos = list.get(rowNum);

				new BoardUpdate(vos);
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("검색조건");
		lblNewLabel.setBounds(186, 50, 56, 15);
		freeBoard.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "title", "content", "writer" }));
		comboBox.setBounds(244, 47, 74, 21);
		freeBoard.add(comboBox);

		searchString = new JTextField();
		searchString.setBounds(330, 47, 133, 21);
		freeBoard.add(searchString);
		searchString.setColumns(10);

		JButton btnSearch = new JButton("search..");
		btnSearch.setBounds(466, 47, 106, 23);
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BoardDAO dao = new BoardDAOImpl();
				dao.search(String.valueOf(comboBox.getSelectedItem()), searchString.getText());

				setVisible(false);

			}
		});
		freeBoard.add(btnSearch);

		JButton btnWrite = new JButton("글작성");
		btnWrite.setBounds(12, 47, 97, 23);
		btnWrite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new BoardInsert();

			}
		});

		freeBoard.add(btnWrite);

		ImageIcon iconr = new ImageIcon("images/refresh.png");
		Image imager = iconr.getImage();
		Image imager2 = imager.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageR = new ImageIcon(imager2);

		JButton rfLabel = new JButton();
		rfLabel.setIcon(iconr);
		rfLabel.setBounds(582, 47, 30, 30);
		freeBoard.add(rfLabel);
		rfLabel.setBorderPainted(false);
		rfLabel.setContentAreaFilled(false);

		rfLabel.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				table.setModel(new DefaultTableModel(rowDatas, colNames) {
					boolean[] columnEditables = new boolean[] { false, false, false, true, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(0).setPreferredWidth(45);
				table.getColumnModel().getColumn(1).setResizable(false);
				table.getColumnModel().getColumn(1).setPreferredWidth(200);
				table.getColumnModel().getColumn(2).setResizable(false);
				table.getColumnModel().getColumn(2).setPreferredWidth(164);
				table.getColumnModel().getColumn(4).setResizable(false);
				table.getColumnModel().getColumn(4).setPreferredWidth(140);

			}

		});

		groundPane.add(freeBoard);

		// 알 수도 있는 사람
		JPanel recommPeoplePane = new JPanel();
		recommPeoplePane.setBackground(Color.LIGHT_GRAY);
		recommPeoplePane.setBounds(1080, 150, 400, 350);
		groundPane.add(recommPeoplePane);
		if (member != null) {
			try {
				DBUtil util = new DBUtil();
				Connection conn = null;
				conn = util.open();
				PreparedStatement ps = null;
				ResultSet rs = null;
				String sql = "SELECT STUDENTNO, NAME FROM MEMBER WHERE STUDENTNO <> ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, member.getStudentNo());
				rs = ps.executeQuery();
				while (rs.next()) {
					Member client = new Member();
					client.setStudentNo(rs.getInt("STUDENTNO"));
					client.setName(rs.getString("NAME"));

					JButton memberBtn = new JButton(client.getStudentNo() + " " + client.getName());
					memberBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							System.out.println("=============" + member.getName());
							if (member != null) {

								ChatClient chattingRoom = new ChatClient(member, client);
								chattingRoom.setVisible(true);
							} else {
								Login signIn = new Login();
							}
						}
					});
					memberBtn.setBounds(0, 0, 10, 10);
					recommPeoplePane.add(memberBtn);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 구직 사이트 바로가기
		JPanel jobSitePane = new JPanel();
		jobSitePane.setBackground(new Color(153, 204, 255, 20));
		jobSitePane.setBounds(1080, 510, 400, 330);
		jobSitePane.setLayout(null);
		groundPane.add(jobSitePane);

		JLabel jsTitle = new JLabel("구직 사이트 바로가기");
		jsTitle.setFont(new Font("나눔고딕", Font.BOLD, 26));
		jobSitePane.add(jsTitle);
		jsTitle.setBounds(10, 10, 250, 30);

		ImageIcon[] jobsites = new ImageIcon[4];
		String[] sites = { "jobkorea", "jobplanet", "saramin", "worknet" };

		for (int i = 0; i < sites.length; i++) {
			jobsites[i] = new ImageIcon("images/" + sites[i] + ".jpg");
		}

		JLabel[] bannerLabel = new JLabel[4];

		for (int i = 0; i < 4; i++) {
			bannerLabel[i] = new JLabel();
			bannerLabel[i].setIcon(jobsites[i]);
			jobSitePane.add(bannerLabel[i]);
		}

		bannerLabel[0].setBounds(10, 90, 180, 30);
		bannerLabel[0].addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.jobkorea.co.kr/"));
				} catch (URISyntaxException | IOException ex) {
					// It looks like there's a problem
				}
			}

		});
		bannerLabel[1].setBounds(190, 140, 170, 30);
		bannerLabel[1].addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.jobplanet.co.kr/welcome/index"));
				} catch (URISyntaxException | IOException ex) {
					// It looks like there's a problem
				}
			}

		});
		bannerLabel[2].setBounds(10, 200, 150, 30);
		bannerLabel[2].addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.saramin.co.kr/zf_user/"));
				} catch (URISyntaxException | IOException ex) {
					// It looks like there's a problem
				}
			}

		});
		bannerLabel[3].setBounds(170, 240, 200, 50);
		bannerLabel[3].addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.work.go.kr/seekWantedMain.do"));
				} catch (URISyntaxException | IOException ex) {
					// It looks like there's a problem
				}
			}

		});

		// 미정 - 추천 게시물 이런거면 좋을듯?

		setSize(1500, 900);
		setVisible(true);
	}

	public class RoundJTextField extends JTextField {
		/**
		 * 검색창 세부 디자인 (모서리 둥글게) 돋보기 이미지 텍스트필드 안에 넣어야함
		 */
		private static final long serialVersionUID = 1L;
		private Shape shape;
		private Icon icon;

		public RoundJTextField(int size) {
			super(size);
			setOpaque(false);
			setText("검색 기능");

		}

		protected void paintComponent(Graphics g) {
			g.setColor(getBackground());
			g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
			super.paintComponent(g);
		}

		protected void paintBorder(Graphics g) {
			g.setColor(getForeground());
			g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
		}

		public boolean contains(int x, int y) {
			if (shape == null || !shape.getBounds().equals(getBounds())) {
				shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
			}
			return shape.contains(x, y);
		}

	}

//	public static void main(String[] args) {
//		MainFrame frame = new MainFrame();
//	}

}
