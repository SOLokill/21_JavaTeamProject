package socket;

import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.Member;
import frames.ChatClient;
import vo.MessageVO;
public class SocketMain {
	static ArrayList<Socket> list = new ArrayList<Socket>(); // 사용자가 몇명이 될지 모르니까 ArrayList로 선언
	static Socket socket = null;

//	public SocketMain(Socket socket) {
//		this.socket = socket;
//		list.add(socket);
//	}
//	
//	public void run(){
//		try {
//			System.out.println("사용자 접속");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;

		BufferedWriter bw = null;
		int port = 1593;

		try {
			serverSocket = new ServerSocket(port);
			while (true) {

				InetAddress local = InetAddress.getLocalHost();
				String ip = local.getHostAddress();
				System.out.println(ip);

//				if (socket == null) {
					socket = serverSocket.accept();
//				}
				System.out.println("소켓 서버를 시작합니다.");
				ObjectInputStream isr = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				System.out.println("소켓 서버 시작 완료");
//				List<HashMap<Member,String>> message = new ArrayList<HashMap<Member,String>>();
				List<MessageVO> message = new ArrayList<MessageVO>();
				message = (List<MessageVO>) isr.readObject();
//				message = (List<HashMap<Member, String>>) isr.readObject();
				System.out.println(message.get(0).getMember().getName() + " : "+message.get(0).getText());
				oos.writeObject(message);
				isr.close();
			}

		} catch (Exception e) { 
			e.printStackTrace();
		}

	}
}
