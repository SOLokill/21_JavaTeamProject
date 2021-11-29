package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class RegisterUI extends JFrame {
	
	public RegisterUI() {
	setTitle("회원가입");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JPanel groundPane = new JPanel();
	setContentPane(groundPane);
	groundPane.setBackground(Color.white);
	setResizable(false);
	groundPane.setLayout(null);
	
	//중앙에 회원가입 패널
	JPanel LoginArea=new JPanel();
	LoginArea.setBackground(Color.white);
	LoginArea.setBounds(400,0,700,850);
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
	
	
	Font font1=new Font("맑은 고딕",Font.PLAIN,25);
	Font font25=new Font("맑은 고딕", Font.PLAIN, 25);
	Font font27=new Font("맑은 고딕", Font.BOLD, 27);
	
	//입력
	JLabel text1=new JLabel("회원정보를 입력해주세요.");
	text1.setFont(font25);
	LoginArea.add(text1);
	text1.setBounds(90,170,500,36);
	
	JLabel text2=new JLabel("아이디/비밀번호");
	text2.setFont(font27);
	text2.setBounds(90,230,350,25);
	LoginArea.add(text2);
	
	JTextField id=new JTextField("아이디",400);
	JPasswordField password=new JPasswordField(400);
	LoginArea.add(id);
	id.setBounds(90,270,450,40);
	LoginArea.add("비밀번호",password);
	password.setBounds(90,309,450,40);
	id.setFont(font25);
	password.setFont(font25);
	
	JLabel text3=new JLabel("이름");
	text3.setFont(font27);
	text3.setBounds(90,360,100,27);
	LoginArea.add(text3);
	
	JTextField name=new JTextField("이름",400);
	LoginArea.add(name);
	name.setBounds(90,400,450,40);
	name.setFont(font25);
	
	JLabel text4=new JLabel("학교 정보");
	text4.setFont(font27);
	text4.setBounds(90,450,150,30);
	LoginArea.add(text4);
	
	JTextField studentNo=new JTextField("학번",400);
	JTextField grade=new JTextField("학년",400);
	JTextField subject=new JTextField("학부(과)",400);

	LoginArea.add(studentNo);
	studentNo.setBounds(90,500,450,40);
	LoginArea.add(grade);
	grade.setBounds(90,539,450,40);
	LoginArea.add(subject);
	subject.setBounds(90,578,450,40);
	
	studentNo.setFont(font25);
	grade.setFont(font25);
	subject.setFont(font25);
	
	JLabel text5=new JLabel("회사");
	text5.setFont(font27);
	text5.setBounds(90,630,100,27);
	LoginArea.add(text5);
	
	JTextField job=new JTextField(400);
	LoginArea.add(job);
	job.setBounds(90,670,450,40);
	job.setFont(font25);


	//btnSignIn
	JButton btnSignIn=new JButton("회원 등록");
	btnSignIn.setFont(new Font("맑은 고딕",Font.BOLD,27));
	btnSignIn.setForeground(Color.white);
	btnSignIn.setBackground(new Color(000,153,255));
	LoginArea.add(btnSignIn);
	btnSignIn.setBounds(250,730,200,50);
	
	btnSignIn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	});
	

	setSize(1500, 900);
	setVisible(true);
}
	
	
	public static void main(String[] args) {
		RegisterUI signIn=new RegisterUI();
	}
}