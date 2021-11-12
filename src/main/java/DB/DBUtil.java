package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Connection conn = null;

	/**
	 * oracle 서버 연결
	 * 
	 * @return 연결 객체를 반환합니다.
	 */
	public Connection open() {
		String url = "";
		String id = "";
		String pw = "";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DBUtil.open()");
			return conn;
		} catch (Exception e) {
			System.out.println("DBUtil.error()");
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * DB연결 종료
	 */
	public void close() {
		try {
			if(pstmt != null) pstmt.close();
            if(rs != null) rs.close();
            if(conn != null) conn.close();
			System.out.println("DBUtil.close()");
		} catch (Exception e) {
			System.out.println("DBUtil.closeError()");
			e.printStackTrace();
		}
	}
}
