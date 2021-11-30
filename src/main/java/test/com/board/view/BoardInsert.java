package test.com.board.view;
 
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
import test.com.board.control.BoardDAO;
import test.com.board.control.BoardVO;
import test.com.board.model.BoardDAOImpl;
 
public class BoardInsert extends JFrame {
    private JTextField title;
    private JTextField writer;
 
    public BoardInsert() {
        setBounds(new Rectangle(600, 0, 450, 280));
        setTitle("게시글등록");
        //setIconImage(Toolkit.getDefaultToolkit().getImage(BoardInsert.class.getResource("/images/icon_editer.png")));
        getContentPane().setLayout(null);
 
        JLabel lblNewLabel = new JLabel("글제목");
        lblNewLabel.setBounds(12, 25, 57, 15);
        getContentPane().add(lblNewLabel);
 
        title = new JTextField("글제목입니다.");
        title.setBounds(81, 22, 340, 21);
        getContentPane().add(title);
        title.setColumns(10);
 
        JLabel lblNewLabel_1 = new JLabel("글내용");
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        getContentPane().add(lblNewLabel_1);
 
        JTextArea textArea = new JTextArea("글내용 글내용..");
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setBounds(81, 53, 340, 69);
        getContentPane().add(textArea);
 
        JLabel lblNewLabel_2 = new JLabel("작성자");
        lblNewLabel_2.setBounds(12, 140, 57, 15);
        getContentPane().add(lblNewLabel_2);
 
        writer = new JTextField("익명");
        writer.setBounds(81, 137, 116, 21);
        getContentPane().add(writer);
        writer.setColumns(10);
 
        JButton btnWrite = new JButton("작성완료");
        btnWrite.setBounds(81, 180, 116, 23);
        btnWrite.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardDAO dao = new BoardDAOImpl();
                BoardVO vo = new BoardVO();
 
                String titles = title.getText();
                String txtarea = textArea.getText();
                String name = writer.getText();
 
                vo.setTitle(titles);
                vo.setContent(txtarea);
                vo.setName(name);
 
                dao.insert(vo);
                dispose();
 
            }
        });
        getContentPane().add(btnWrite);
 
        // JButton btnList = new JButton("리스트");
        // btnList.setIcon(new
        // ImageIcon(BoardInsert.class.getResource("/images/icon_docs_01.png")));
        // btnList.setBounds(190, 180, 97, 23);
        // btnList.addActionListener(new ActionListener() {
        //
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // new BoardList();
        // }
        // });
        // getContentPane().add(btnList);
 
        JButton btnClose = new JButton("닫기");
        //btnClose.setIcon(new ImageIcon(BoardInsert.class.getResource("/images/icon_stop_01.png")));
        btnClose.setBounds(209, 180, 97, 23);
        btnClose.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
 
                setVisible(false);
 
            }
        });
        getContentPane().add(btnClose);
 
        setVisible(true);
 
    }
}


