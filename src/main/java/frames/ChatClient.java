package frames;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import DB.DBUtil;
import dto.Member;
import vo.MessageVO;

public class ChatClient extends JFrame implements KeyListener {
	private JTextField chatInput;
	static JTextArea chatGround;
	private JButton sendBtn;
	private JLabel title;
	static Member member;
	static Socket socket;
	static Member client;

	public ChatClient(Member loginMember, Member clientMember) {
		member = loginMember;
		client = clientMember;
		chatInput = new JTextField(15);
		chatGround = new JTextArea();
		title = new JLabel(member.getName() + "," + client.getName() + "의 채팅방");
		JScrollPane scrollPane = new JScrollPane(chatGround);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		chatGround.setEditable(false);
		sendBtn = new JButton("전송");
		sendBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == sendBtn) {

				}
			}
		});
		JPanel chattingPane = new JPanel(new BorderLayout());
		chattingPane.add("Center", chatInput);
		chattingPane.add("East", sendBtn);
		Container con = this.getContentPane();
		con.add("Center", scrollPane);
		con.add("South", chattingPane);
		con.add("North", title);

		setBounds(0, 0, 300, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);
		chatInput.addKeyListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			DBUtil util = new DBUtil();
			Connection conn = null;
			conn = util.open();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "SELECT ROOMNO FROM (SELECT LISTAGG(STUDENTNO, ',') WITHIN GROUP(ORDER BY ROOMNO, STUDENTNO) AS CLIENT, ROOMNO FROM PARTICIPANTS GROUP BY ROOMNO) WHERE CLIENT = ? || ',' ||?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, client.getStudentNo() > member.getStudentNo() ? member.getStudentNo() : client.getStudentNo());
			ps.setInt(2, client.getStudentNo() < member.getStudentNo() ? member.getStudentNo() : client.getStudentNo());
			rs = ps.executeQuery();
			int roomNo = 0;
			if(rs.next()) {
				roomNo = rs.getInt("ROOMNO");
			}
			if (roomNo == 0) {
				sql = "INSERT INTO CHATTING_ROOM (ROOMNO, ROOMNM) VALUES (CHATTING_ROOM_SQ.NEXTVAL, '"+title+"')";
				ps = conn.prepareStatement(sql);
				ps.executeQuery();
				sql = "INSERT INTO PARTICIPANTS VALUES(PARTICIPANTS_SQ.NEXTVAL, ?, CHATTING_ROOM_SQ.CURRVAL)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, loginMember.getStudentNo());
				ps.executeQuery();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, clientMember.getStudentNo());
				ps.executeQuery();
			}else{
				sql = "SELECT NAME, CONTENT FROM CHATTING C INNER JOIN MEMBER M ON C.STUDENTNO = M.STUDENTNO WHERE C.ROOMNO = ? ORDER BY CHATINDEX";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, roomNo);
				rs = ps.executeQuery();
				while(rs.next()) {
					String name = rs.getString("NAME");
					String content = rs.getString("CONTENT");
					String message = name + " : " + content + "\n";
					chatGround.append(message);
				}
				
			}
			
			
			
			util.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		try {
//			InetAddress local = InetAddress.getLocalHost();
//			String ip = local.getHostAddress();
//			socket = new Socket(ip, 1593);
//			ChatClient cc = new ChatClient(member);
//
//		} catch (Exception e) {
//			System.out.println("접속 실패");
//			e.printStackTrace();
//		}
//
//	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			if (e.getKeyCode() == e.VK_ENTER) {
				InetAddress local = InetAddress.getLocalHost();
//				String ip = local.getHostAddress();
				String ip = "192.168.219.102";
				socket = new Socket(ip, 1593);
				String data = chatInput.getText();
				ObjectOutputStream osw = new ObjectOutputStream(socket.getOutputStream());
//				List<HashMap<Member, String>> message = new ArrayList<HashMap<Member, String>>();
				List<MessageVO> message = new ArrayList<MessageVO>();
				MessageVO messagevo = new MessageVO();
				messagevo.setMember(member);
				messagevo.setText(data);
//				HashMap<Member, String> text = new HashMap<Member, String>();
//				text.put(member, data);
//				message.add(text);
				message.add(messagevo);
				osw.writeObject(message);
				chatInput.setText("");
				chatGround.append(messagevo.getMember().getName() + " : " + messagevo.getText() + "\n");
//				chatGround.append(member.getName() + " : " + message.get(0).get(member) + "\n");
				DBUtil util = new DBUtil();
				Connection conn = null;
				conn = util.open();
				PreparedStatement ps = null;
				ResultSet rs = null;
				
				String sql = "SELECT ROOMNO FROM (SELECT LISTAGG(STUDENTNO, ',') WITHIN GROUP(ORDER BY ROOMNO, STUDENTNO) AS CLIENT, ROOMNO FROM PARTICIPANTS GROUP BY ROOMNO) WHERE CLIENT = ? || ',' ||?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, client.getStudentNo() > member.getStudentNo() ? member.getStudentNo() : client.getStudentNo());
				ps.setInt(2, client.getStudentNo() < member.getStudentNo() ? member.getStudentNo() : client.getStudentNo());
				rs = ps.executeQuery();
				int roomNo = 0;
				if(rs.next()) {
					roomNo = rs.getInt("ROOMNO");
				}
				
				sql = "INSERT INTO CHATTING VALUES(CHATTING_SQ.NEXTVAL, ?, ?, ?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, member.getStudentNo());
				ps.setString(2, data);
				ps.setInt(3, roomNo);
				rs = ps.executeQuery();
				
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
