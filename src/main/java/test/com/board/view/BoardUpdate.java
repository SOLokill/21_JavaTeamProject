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
 
public class BoardUpdate extends JFrame {
 
    private JTextField title;
    private JTextField writer;
 
    public BoardUpdate(BoardVO vo) {
 
        final int num = vo.getNum();
 
        setBounds(new Rectangle(600, 0, 450, 280));
        setTitle("게시글 수정");
        getContentPane().setLayout(null);
 
        JLabel lblNewLabel = new JLabel("글제목");
        lblNewLabel.setBounds(12, 25, 57, 15);
        getContentPane().add(lblNewLabel);
 
        title = new JTextField(vo.getTitle());
        title.setBounds(81, 22, 340, 21);
        getContentPane().add(title);
        title.setColumns(10);
 
        JLabel lblNewLabel_1 = new JLabel("글내용");
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        getContentPane().add(lblNewLabel_1);
 
        JTextArea textArea = new JTextArea(vo.getContent());
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setBounds(81, 53, 340, 69);
        getContentPane().add(textArea);
 
        JLabel lblNewLabel_2 = new JLabel("작성자");
        lblNewLabel_2.setBounds(12, 140, 57, 15);
        getContentPane().add(lblNewLabel_2);
 
        writer = new JTextField(vo.getName());
        writer.setBounds(81, 137, 116, 21);
        getContentPane().add(writer);
        writer.setColumns(10);
 
        JButton btnWrite = new JButton("글수정");
        btnWrite.setBounds(81, 180, 97, 23);
        btnWrite.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardDAO dao = new BoardDAOImpl();
                BoardVO vo = new BoardVO();
 
                String titles = title.getText();
                String txtarea = textArea.getText();
                String name = writer.getText();
                vo.setNum(num);
                vo.setTitle(title.getText());
                vo.setContent(textArea.getText());
                vo.setName(writer.getText());
 
                dao.update(vo);
 
                setVisible(false);
 
            }
        });
        getContentPane().add(btnWrite);
 
        JButton btnDel = new JButton("글삭제");
        btnDel.setBounds(190, 180, 97, 23);
        btnDel.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardDAO dao = new BoardDAOImpl();
                BoardVO vo = new BoardVO();
 
                vo.setNum(num);
 
                dao.delete(vo);
 
                setVisible(false);
            }
        });
        getContentPane().add(btnDel);
 
        JButton btnClose = new JButton("닫기");
        btnClose.setBounds(299, 180, 97, 23);
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


