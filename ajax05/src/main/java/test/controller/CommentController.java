package test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import test.dao.CommentsDao;
import test.vo.CommentsVo;
@WebServlet("/comm/list")
public class CommentController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int mnum=Integer.parseInt(req.getParameter("mnum"));
		CommentsDao dao=CommentsDao.getInstance();
		 ArrayList<CommentsVo> list=dao.select(mnum);
		//영화에 대한 댓글을 xml로 응답하기
		//http://localhost:8081/ajax02/comm/list?mnum=1
		JSONArray jarr=new JSONArray();
		for(CommentsVo vo:list) {
			JSONObject ob=new JSONObject();
			ob.put("num",vo.getId());
			ob.put("mnum",vo.getMnum());
			ob.put("id", vo.getId());
			ob.put("comments", vo.getComments());
			jarr.put(ob);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
		
	}
}
