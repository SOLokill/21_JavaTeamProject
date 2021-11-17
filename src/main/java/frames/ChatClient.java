package frames;

import javax.swing.*;
import java.awt.*;

public class ChatClient extends JFrame{
	private JTextField chatInput;
	private JTextArea chatGround;
	private JButton sendBtn;
	private JLabel title;
	
	public ChatClient(){
		chatInput = new JTextField(15);
		chatGround = new JTextArea();
		title = new JLabel("상대방 이름");
		JScrollPane scrollPane = new JScrollPane(chatGround);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		chatGround.setEditable(false);
		sendBtn = new JButton("전송");
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		new ChatClient();
	}
}
