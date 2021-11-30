package test.com.board.view;
 
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
 
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
 
import test.com.board.control.BoardDAO;
import test.com.board.control.BoardVO;
import test.com.board.model.BoardDAOImpl;
 
public class BoardList extends JFrame {
    private JTable table;
    private JTextField searchString;
    public BoardList() {
        setTitle("Board List");
        setBounds(new Rectangle(0, 0, 600, 350));
        getContentPane().setLayout(null);
       
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 49, 560, 189);
        getContentPane().add(scrollPane);
       
        BoardDAO dao = new BoardDAOImpl();
        List<BoardVO> list = dao.select();
       
        String[] colNames = new String[] {"글번호", "제목", "내용...", "작성자", "작성일"};
        Object[][] rowDatas = new Object[list.size()][colNames.length];
       
        for (int i = 0; i < list.size(); i++) {
            rowDatas[i] = new Object[] {
                    list.get(i).getNum(),
                    list.get(i).getTitle(),
                    list.get(i).getContent(),
                    list.get(i).getName(),
                    list.get(i).getRegDate()
            };
        }
        table = new JTable();
        table.setModel(new DefaultTableModel(rowDatas,colNames) {
            boolean[] columnEditables = new boolean[] {
                false, false, false, true, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(45);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(2).setPreferredWidth(164);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(4).setPreferredWidth(140);
       
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                int rowNum = table.getSelectedRow();
                BoardVO vos = new BoardVO();
                vos = list.get(rowNum);
               
                new BoardUpdate(vos);
            }
        });
        scrollPane.setViewportView(table);
       
        JLabel lblNewLabel = new JLabel("검색조건");
        lblNewLabel.setBounds(186, 20, 56, 15);
        getContentPane().add(lblNewLabel);
       
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"title", "content", "writer"}));
        comboBox.setBounds(244, 17, 74, 21);
        getContentPane().add(comboBox);
       
        searchString = new JTextField();
        searchString.setBounds(330, 17, 133, 21);
        getContentPane().add(searchString);
        searchString.setColumns(10);
       
        JButton btnSearch = new JButton("search..");
        btnSearch.setBounds(466, 16, 106, 23);
        btnSearch.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardDAO dao = new BoardDAOImpl();
                dao.search(String.valueOf(comboBox.getSelectedItem()), searchString.getText());
               
                setVisible(false);
               
            }
        });
        getContentPane().add(btnSearch);
       
       
        JButton btnWrite = new JButton("글작성");
        btnWrite.setBounds(475, 248, 97, 23);
        btnWrite.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                new BoardInsert();
               
            }
        });
       
        getContentPane().add(btnWrite);
       
        setVisible(true);
    }
}


