package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	public static Connection getCon() {
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@jebyu_high?TNS_ADMIN=/Users/gim-yeonhui/Java-run/Wallet_jebyu";
			con=DriverManager.getConnection(url,"SCOTT","tiger123456A");
			return con;
		}catch(ClassNotFoundException cf) {
			System.out.println("드라이버로딩실패: "+cf.getMessage());
		}catch(SQLException e) {
			System.out.println("db접속실패: "+e.getMessage());
		}
		return null;
	}
	public static void close(Connection con) {
		try {
			if(con!=null)con.close();
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}
	public static void close(Statement stmt) {
		try {
			if(stmt!=null)stmt.close();
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			if(rs!=null)rs.close();
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}
	public static void close(Connection con, Statement stmt,ResultSet rs) {
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(con!=null)con.close();
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}
}
