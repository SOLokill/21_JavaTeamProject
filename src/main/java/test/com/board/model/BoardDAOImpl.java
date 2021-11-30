package test.com.board.model;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import test.com.board.control.BoardDAO;
import test.com.board.control.BoardVO;
import test.com.board.view.BoardList2;
 
public class BoardDAOImpl implements BoardDAO {
 
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
 
    public static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@kdw.cb0dtwejkkxo.us-east-2.rds.amazonaws.com:1521:KDW";
    public static final String USERID = "kdw";
    public static final String USERPWD = "javaproject";
 
    public BoardDAOImpl() {
        try {
            Class.forName(DRIVER_NAME);
           
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("drive connect failed...");
        }
    }
 
    @Override
    public int insert(BoardVO vo) {
        try {
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            String sql = "insert into board(num, title, content, writer, regdate) values (SEQ_BOARD_NUM.NEXTVAL, ?, ?, ?, SYSDATE )";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setString(3, vo.getName());
 
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return 0;
    }
 
    @Override
    public int update(BoardVO vo) {
        try {
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            String sql = "update board set title=?, content=?, writer=?, regdate=SYSDATE where num=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setString(3, vo.getName());
            pstmt.setInt(4, vo.getNum());
 
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return 0;
    }
 
    @Override
    public int delete(BoardVO vo) {
        try {
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            String sql = "delete from board where num=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.getNum());
 
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return 0;
    }
 
    @Override
    public BoardVO search(BoardVO vo) {
 
        return null;
    }
 
    @Override
    public List<BoardVO> select() {
 
        List<BoardVO> list = new ArrayList<BoardVO>();
 
        try {
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            String sql = "select num, title, content, writer, regdate from board order by num desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
 
            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setNum(rs.getInt("num"));
                vo.setTitle(rs.getString("title"));
                vo.setContent(rs.getString("content"));
                vo.setName(rs.getString("writer"));
                vo.setRegDate(rs.getDate("regdate"));
 
                list.add(vo);
               
            }
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 
            try {
                pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
 
        return list;
    }
 
    @Override
    public void search(String search, String searchString) {
        List<BoardVO> list = new ArrayList<BoardVO>();
 
        try {
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            String sql = "select num, title, content, writer, regdate from board where " + search + " like '%"
                    + searchString + "%' order by num desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
 
            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setNum(rs.getInt("num"));
                vo.setTitle(rs.getString("title"));
                vo.setContent(rs.getString("content"));
                vo.setName(rs.getString("writer"));
                vo.setRegDate(rs.getDate("regdate"));
 
                list.add(vo);
               
            }
           
            new BoardList2(list);
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 
            try {
                pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
 
       
    }
 
}


