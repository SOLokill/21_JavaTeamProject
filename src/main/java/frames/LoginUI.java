package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class LoginUI extends JFrame {
	
	public LoginUI() {
	setTitle("로그인");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JPanel groundPane = new JPanel();
	setContentPane(groundPane);
	groundPane.setBackground(Color.white);
	setResizable(false);
	groundPane.setLayout(null);
	
	//중앙에 로그인 패널
	JPanel LoginArea=new JPanel();
	LoginArea.setBackground(Color.white);
	LoginArea.setBounds(400,150,700,600);
	LoginArea.setBorder(new TitledBorder(new LineBorder(Color.gray,1)));
	groundPane.add(LoginArea);
	LoginArea.setLayout(null);
	
	//로고
	ImageIcon icon = new ImageIcon("images/logo.jpg");
	Image image = icon.getImage();
	Image image2 = image.getScaledInstance(200, 90, java.awt.Image.SCALE_SMOOTH);
	ImageIcon image3 = new ImageIcon(image2);
	JLabel imageLabel = new JLabel();
	imageLabel.setIcon(image3);
	imageLabel.setBounds(250, 50, 200, 90);
	LoginArea.add(imageLabel);
	
	//안내
	
	Font font25=new Font("맑은 고딕", Font.PLAIN, 25);
	JLabel text1=new JLabel("로그인이 필요한 서비스입니다.");
	text1.setFont(new Font("맑은 고딕", Font.BOLD, 27));
	LoginArea.add(text1);
	text1.setBounds(50,190,500,36);
	JLabel text2=new JLabel("성공人 회원이 아니면, 지금 ");
	text2.setFont(font25);
	text2.setBounds(50,230,350,25);
	LoginArea.add(text2);
	JLabel text3=new JLabel("회원가입");
	text3.setFont(font25);
	text3.setForeground(new Color(000,153,255));
	text3.setBounds(370,230,100,25);
	LoginArea.add(text3);
	JLabel text4=new JLabel("을 해주세요.");
	text4.setFont(font25);
	text4.setBounds(470,230,350,25);
	LoginArea.add(text4);
	
	//tf
	JTextField id=new JTextField(400);
	JPasswordField password=new JPasswordField(400);
	LoginArea.add(id);
	id.setBounds(50,330,430,60);
	LoginArea.add(password);
	password.setBounds(50,389,430,60);
	
	Font font1=new Font("맑은 고딕",Font.PLAIN,30);
	id.setFont(font1);
	password.setFont(font1);
	
	//btnLogin
	JButton btnLogin=new JButton("로그인");
	btnLogin.setFont(new Font("맑은 고딕",Font.BOLD,36));
	btnLogin.setForeground(Color.white);
	btnLogin.setBackground(new Color(000,153,255));
	LoginArea.add(btnLogin);
	btnLogin.setBounds(479,330,170,120);
	
	//회원가입 | 아이디/비밀번호 찾기 
	JLabel lbRegister=new JLabel("회원가입");
	lbRegister.setFont(font25);
	lbRegister.setBounds(50,490,100,25);
	LoginArea.add(lbRegister);
	
	JLabel l=new JLabel("|");
	l.setFont(font25);
	l.setBounds(160,490,10,25);
	LoginArea.add(l);
	
	JLabel searchIDPW=new JLabel("아이디/비밀번호 찾기");
	searchIDPW.setFont(font25);
	searchIDPW.setBounds(180,490,250,25);
	LoginArea.add(searchIDPW);


	setSize(1500, 900);
	setVisible(true);
}
	
	
	public static void main(String[] args) {
		LoginUI login=new LoginUI();
	}
}