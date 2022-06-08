package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JdbcUtil;
import test.vo.MovieVo;

public class MovieDao {
	private static MovieDao instance=new MovieDao();
	private MovieDao() {}
	public static MovieDao getInstance() {
		return instance;
	}
	public ArrayList<MovieVo> listAll() {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<MovieVo> list= new ArrayList<MovieVo>();
		try{
			con=JdbcUtil.getCon();
			String sql="select * from movie";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				MovieVo vo=new MovieVo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				list.add(vo);
			}
			return list;
		}catch(SQLException s){
			s.printStackTrace();
			return null;
		}finally{
			JdbcUtil.close(con, ps, rs);
		}
	}
	public MovieVo selectM(int mnum) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from movie where mnum=?";
		try{
			con=JdbcUtil.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, mnum);
			rs=ps.executeQuery();
			while(rs.next()){
				MovieVo vo=new MovieVo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				return vo;
			}
			return null;
		}catch(SQLException s){
			s.printStackTrace();
			return null;
		}finally{
			JdbcUtil.close(con, ps, rs);
		}
	}
}
