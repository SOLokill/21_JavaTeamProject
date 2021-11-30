package frames;

import dto.*;

import java.net.SocketException;

import DB.*;
public class ControlFrame {
	private Member member;
	
	public ControlFrame()  {
		Member member = new Member();
		Login login = new Login();
		member.setId("SHUAI1593");
		member.setPassword("RLAEHDDNR12");
		Member loginMember = login.memberLogin(member);
		
		
//		MainFrame mf = new MainFrame(loginMember);
//		mf.setVisible(true);
		
		new LoginUI();
		
		
//		 if (member != null) {
//			ChatClient chatt = new ChatClient(member);
//			chatt.setVisible(true);
//		}else {
//			//로그인 화면 연결
//		}
	}
	
	public static void main(String[] args) {
		new ControlFrame();
	}
}
