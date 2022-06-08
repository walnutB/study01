<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//server1.jsp
	String name="홍길동";
	int	age=10;
	String phone="010-111-1234";
	
	JSONObject json= new JSONObject();
	json.put("name",name);
	json.put("age",age);
	json.put("phone",phone);
	
	response.setContentType("text/plain;cherset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.print(json);
%>