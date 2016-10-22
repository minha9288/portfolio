<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<%@ page import="java.util.*" %>

<title>Insert title here</title>
</head>
<%
	//미팅id 설정
	String meetingId_Str = request.getParameter("meetingId");
	int meetingId = Integer.parseInt(meetingId_Str);
	System.out.println(meetingId);
	session.setAttribute("meetingId", meetingId);

	
	String userId = (String) session.getAttribute("userIdKey");
	
	//채팅방 참여자 목록 저장
	String listNum = "list" + meetingId + "";
		Vector list = (Vector)application.getAttribute(listNum);  
		 if(list == null){          
		  list= new Vector();
		 }
	     list.add(userId);
	     application.setAttribute(listNum,list);
%>


		
<frameset cols = "20%,50%,30%">
	<frameset rows = "60%,*">
		<frame src = "list_meeting.jsp" >
		<frame src = "chatting/meeting_attend.jsp">
	</frameset>
	
	<frame src = "chatting/board.jsp">
	
	<frameset rows = "80%, *" border="0">
		<!-- <frame src = "chatting/chat_attend.jsp">  -->
		<frame src = "chatting/chat_view.jsp">
		<frame src = "chatting/chat_input.jsp">
	</frameset>
</frameset>
</html>