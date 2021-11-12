package frames;

import javax.swing.*;
import java.awt.*;

public class ChatClient extends JFrame{
	private JTextField chatInput;
	private JTextArea chatGround;
	private JButton sendBtn;
	
	public ChatClient(){
		chatInput = new JTextField(30);
		chatGround = new JTextArea();
		JScrollPane backgroundPane = new JScrollPane(chatGround);
		backgroundPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		chatGround.setEditable(false);
		sendBtn = new JButton("전송");
		JPanel chattingPane = new JPanel(new BorderLayout());
		chattingPane.add("Center", chatInput);
		chattingPane.add("East", sendBtn);
		Container con = this.getContentPane();
		con.add("Center", chatInput);
		con.add("South", chattingPane);
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
