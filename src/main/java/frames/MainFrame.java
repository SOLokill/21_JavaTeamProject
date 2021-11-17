package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

	public MainFrame() {
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

		// 로그인 버튼
		JButton btnLogin = new JButton("로그인");
		btnLogin.setBounds(1300, 50, 100, 40);
		btnLogin.setBackground(Color.white);
		btnLogin.setFont(new Font("나눔고딕", Font.PLAIN, 17));
		groundPane.add(btnLogin);

		// 메뉴 카테고리 (JPanel+JLabel에 액션이벤트)
		JPanel categoryPane = new JPanel();
		categoryPane.setBackground(new Color(153,204,255,100));
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
		JPanel noticePane=new JPanel();
		noticePane.setBackground(new Color(153,204,255,20));
		noticePane.setBounds(10, 150, 400, 350);
		groundPane.add(noticePane);
		noticePane.setLayout(null);
		
		JLabel ntTitle=new JLabel("학내 공지사항");
		ntTitle.setFont(new Font("나눔고딕", Font.BOLD, 26));
		noticePane.add(ntTitle);
		ntTitle.setBounds(10,10,200,26);
		
		Notices nt=new Notices();
		String[] notices=nt.Notices();
		JLabel[] nts=new JLabel[10];
		for (int i = 0; i < notices.length; i++) {
			nts[i] = new JLabel(notices[i]);
			nts[i].setFont(new Font("나눔고딕", Font.PLAIN, 17));
			nts[i].setBounds(10,50+(i*25),380,18);
			noticePane.add(nts[i]);
		}
		

		// 자유게시판

		// 알 수도 있는 사람
		JPanel recommPeoplePane = new JPanel();
		recommPeoplePane.setBackground(Color.LIGHT_GRAY);
		recommPeoplePane.setBounds(1080, 150, 400, 350);
		groundPane.add(recommPeoplePane);
		JLabel kimsoul = new JLabel("soul kim 19학번");
		kimsoul.setBounds(0, 0, 10, 10);
		recommPeoplePane.add(kimsoul);
		
		// 구직 사이트 바로가기 
		JPanel jobSitePane = new JPanel();
		jobSitePane.setBackground(Color.yellow);
		jobSitePane.setBounds(1080, 510, 400, 330);
		jobSitePane.setLayout(null);
		groundPane.add(jobSitePane);
		
		
		JLabel jsTitle=new JLabel("구직 사이트 바로가기");
		jsTitle.setFont(new Font("나눔고딕", Font.BOLD, 26));
		jobSitePane.add(jsTitle);
		jsTitle.setBounds(10, 10, 250, 30);
		
		ImageIcon[] jobsites = new ImageIcon[4];
		String[] sites = { "jobkorea", "jobplanet", "saramin", "worknet"};

		for (int i = 0; i < sites.length; i++) {
			jobsites[i] = new ImageIcon("images/" + sites[i] + ".jpg");
		}	
		
		JLabel[] bannerLabel=new JLabel[4];
		
		for(int i=0;i<4;i++) {
			bannerLabel[i]=new JLabel();
			bannerLabel[i].setIcon(jobsites[i]);
			jobSitePane.add(bannerLabel[i]);
		}
		
		bannerLabel[0].setBounds(10,50,150,30);
		bannerLabel[1].setBounds(170,50,150,30);
		bannerLabel[2].setBounds(10,160,150,30);
		bannerLabel[3].setBounds(170,160,180,50);

		//하이퍼링크 달아야함 
		
		
		
		
		
		

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

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}

}
