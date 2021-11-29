package DB;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import dto.Member;
public class Login {
	 Scanner sc = new Scanner(System.in);
	 
	 /**
	  * 회원가입 기능
	  */
	public void SignIn() {
		try {
			InetAddress local = InetAddress.getLocalHost();
			String ip = local.getHostAddress();
			DBUtil util = new DBUtil();
			Connection conn = null;
			conn = util.open();
			PreparedStatement ps = null;
			System.out.print("학번을 입력하세요 : ");
			int studentNo = sc.nextInt();
			System.out.print("\n사용할 아이디를 입력하세요 : ");
			String id = sc.next();
			System.out.print("\n사용할 비밀번호를 입력하세요 : ");
			String password = sc.next();
			System.out.print("\n본명을 입력하세요 : ");
			String name = sc.next();
			System.out.print("\n학년을 입력하세요 : ");
			int grade = sc.nextInt();
			System.out.print("\n전공을 입력하세요 : ");
			String subject = sc.next();
			String job = "";
			if (grade > 4 || grade < 1) {
				job = "학생";
			}else {
				System.out.print("\n현재 직업을 입력하세요 : ");
				job = sc.next();
			}
			String sql = "INSERT INTO MEMBER(STUDENTNO, ID, PASSWORD, NAME, GRADE, SUBJECT, IP, JOB)\r\n"
					+ "VALUES ("+studentNo+", '"+id+"', '"+password+"', '"+name+"', "+grade+", '"+subject+"', '"+ip+"', '"+job+"');";
			ps = conn.prepareStatement(sql);
			ResultSet rs = null;
			rs = ps.executeQuery();
			util.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 로그인 기능
	 * @param member
	 */
	public Member memberLogin(Member member) {
		Member loginMember = new Member();
		try {
			System.out.println(member.getId());
			System.out.println(member.getPassword());
			DBUtil util = new DBUtil();
			Connection conn = null;
			conn = util.open();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "SELECT STUDENTNO, ID, NAME, GRADE, SUBJECT, JOB FROM MEMBER WHERE ID = ? AND PASSWORD = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getId());
			ps.setString(2, member.getPassword());
			rs = ps.executeQuery();
			while(rs.next()) {
				loginMember.setStudentNo(rs.getInt("STUDENTNO"));
				loginMember.setId(rs.getString("ID"));
				loginMember.setName(rs.getString("NAME"));
				loginMember.setGrade(rs.getInt("GRADE"));
				loginMember.setSubject(rs.getString("SUBJECT"));
				loginMember.setJob(rs.getString("JOB"));
			}
			util.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("123123123" + loginMember.getName());
		return loginMember;
		
	}
}