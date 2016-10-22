<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<%
request.setCharacterEncoding("euc-kr");
String meetingName=request.getParameter("meetingName"); 
%>
<title>Insert title here</title>
</head>
<body>
	<br>
	<p align="right">
		<a href="../login/BeforeUpdate.jsp">회원정보수정&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
		<a href="../login/Logout.jsp">로그아웃&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
	</p>
	<p><%=meetingName %></p>
</body>
</html>