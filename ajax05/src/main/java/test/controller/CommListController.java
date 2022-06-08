package test.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.CommentsDao;
import test.vo.CommentsVo;

public class CommListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int mnum=Integer.parseInt(req.getParameter("mnum"));
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));
		CommentsDao dao=CommentsDao.getInstance();
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		ArrayList<CommentsVo> list=dao.cList(mnum,startRow,endRow);
		int pageCount=(int)Math.ceil(dao.getCount(mnum)/5.0);
		int startPage=(pageNum-1)/5*5+1;
		int endPage=startPage+4;
		
	}
}
