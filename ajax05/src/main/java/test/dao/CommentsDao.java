package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JdbcUtil;
import test.vo.CommentsVo;

public class CommentsDao {
	private static CommentsDao instance=new CommentsDao();
	private CommentsDao() {}
	public static CommentsDao getInstance() {
		return instance;
	}
	public ArrayList<CommentsVo> listAll(){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<CommentsVo> list= new ArrayList<CommentsVo>();
		try{
			con=JdbcUtil.getCon();
			String sql="select * from comments";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				CommentsVo vo=new CommentsVo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));
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
	public ArrayList<CommentsVo> select(int mnum) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from comments where mnum=?";
		try{
			con=JdbcUtil.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, mnum);
			rs=ps.executeQuery();
			ArrayList<CommentsVo> list=new ArrayList<CommentsVo>();
			while(rs.next()){
				CommentsVo vo=new CommentsVo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));
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
	public int insert(CommentsVo vo) {
		Connection con=null;
		PreparedStatement ps=null;
		String sql="insert into comments values(comments_seq.nextval,?,?,?)";
		try{
			con=JdbcUtil.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, vo.getMnum());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getComments());
			int n=ps.executeUpdate();
			return n;
		}catch(SQLException s){
			s.printStackTrace();
			return -1;
		}finally{
			JdbcUtil.close(con, ps, null);
		}
	}
}
