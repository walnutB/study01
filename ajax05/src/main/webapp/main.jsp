<%@page import="db.JdbcUtil"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
%>
<h1>영화정보</h1>
<ul>
	<c:forEach var="vo" items="${list }">
	<li>${vo.title }<a href="detail?mnum=${vo.mnum }">상세보기</a></li>
	</c:forEach>
</ul>
</body>
</html>