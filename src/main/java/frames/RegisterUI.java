package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import DB.DBUtil;
import DB.Login;
import dto.Member;


public class RegisterUI extends JFrame {
	JTextField tfId; 
	JPasswordField tfPassword;
	JTextField tfName;
	JTextField tfStudentNo;
	JTextField tfGrade;
	JTextField tfSubject;
	JTextField tfJob;
	
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
	//tfId,tfPassword,tfName,tfStudentNo.tfGrade,tfSubject,tfJob
	JLabel text1=new JLabel("회원정보를 입력해주세요.");
	text1.setFont(font25);
	LoginArea.add(text1);
	text1.setBounds(90,170,500,36);
	
	JLabel text2=new JLabel("아이디/비밀번호");
	text2.setFont(font27);
	text2.setBounds(90,230,350,25);
	LoginArea.add(text2);
	
	JTextField tfId=new JTextField("아이디",400);
	JPasswordField tfPassword=new JPasswordField(400);
	LoginArea.add(tfId);
	tfId.setBounds(90,270,450,40);
	LoginArea.add("비밀번호",tfPassword);
	tfPassword.setBounds(90,309,450,40);
	tfId.setFont(font25);
	tfPassword.setFont(font25);
	
	JLabel text3=new JLabel("이름");
	text3.setFont(font27);
	text3.setBounds(90,360,100,27);
	LoginArea.add(text3);
	
	JTextField tfName=new JTextField("이름",400);
	LoginArea.add(tfName);
	tfName.setBounds(90,400,450,40);
	tfName.setFont(font25);
	
	JLabel text4=new JLabel("학교 정보(학번,학년,학부(과)");
	text4.setFont(font27);
	text4.setBounds(90,450,150,30);
	LoginArea.add(text4);
	
	JTextField tfStudentNo=new JTextField(400);
	JTextField tfGrade=new JTextField(400);
	JTextField tfSubject=new JTextField(400);

	LoginArea.add(tfStudentNo);
	tfStudentNo.setBounds(90,500,450,40);
	LoginArea.add(tfGrade);
	tfGrade.setBounds(90,539,450,40);
	LoginArea.add(tfSubject);
	tfSubject.setBounds(90,578,450,40);
	
	tfStudentNo.setFont(font25);
	tfGrade.setFont(font25);
	tfSubject.setFont(font25);
	
	JLabel text5=new JLabel("회사");
	text5.setFont(font27);
	text5.setBounds(90,630,100,27);
	LoginArea.add(text5);
	
	JTextField tfJob=new JTextField(400);
	LoginArea.add(tfJob);
	tfJob.setBounds(90,670,450,40);
	tfJob.setFont(font25);


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
			try {
				InetAddress local = InetAddress.getLocalHost();
				String ip = local.getHostAddress();
				DBUtil util = new DBUtil();
				Connection conn = null;
				conn = util.open();
				PreparedStatement ps = null;
				String stdNo=tfStudentNo.getText().trim();
				int studentNo = Integer.parseInt(stdNo);
				String id = tfId.getText().trim();
				
				//암호화된 비밀번호 get하기
				 String pw = "";
				  
				//tf_pw 필드에서 패스워드를 얻어옴, char[] 배열에 저장
				char[] secret_pw = tfPassword.getPassword(); 

				//secret_pw 배열에 저장된 암호의 자릿수 만큼 for문 돌리면서 cha 에 한 글자씩 저장
				     for(char cha : secret_pw){         
				         Character.toString(cha);       //cha 에 저장된 값 string으로 변환
				       //pw 에 저장하기, pw 에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자
				         pw += (pw.equals("")) ? ""+cha+"" : ""+cha+"";   
				     }

				    

				String password =pw;
				String name =tfName.getText().trim();
				
				String g=tfGrade.getText().trim();
				int grade = Integer.parseInt(g);
				String subject = tfSubject.getText().trim();
				String job = "";
				if (grade > 4 || grade < 1) {
					job = "학생";
				}else {
					job = tfJob.getText().trim();
				}
				String sql ="INSERT INTO \"KDW\".\"MEMBER\" (STUDENTNO, ID, PASSWORD, NAME, GRADE, SUBJECT, IP, JOB) VALUES ("+studentNo+", '"+id+"', '"+password+"', '"+name+"', "+grade+", '"+subject+"', '"+ip+"', '"+job+"')\r\n"
						+ "";
				ps = conn.prepareStatement(sql);
				ResultSet rs = null;
				rs = ps.executeQuery();
				util.close();
				dispose();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	});
	

	setSize(1500, 900);
	setVisible(true);
}
	
	
//	public static void main(String[] args) {
//		RegisterUI signIn=new RegisterUI();
//	}
}
