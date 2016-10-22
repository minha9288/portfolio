<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
<jsp:useBean id="meetingMgr" class="com.greenlight.db.ConfListMgr"/>

<%
	request.setCharacterEncoding("EUC-KR");

	int meetingId= ((Integer) session.getAttribute("meetingId")).intValue();
	String search_id = request.getParameter("search_id");
	
	if(userMgr.idChecking(search_id)){
		meetingMgr.insertEntered(meetingId, search_id);
		out.println("<script>alert('초대완료되었습니다');</script>");
	}
	else{
		out.println("<script>alert('ID를 확인해주세요');</script>");
	}
	
%>
<script>location.href = "meeting_attend.jsp"</script>
</head>
<body>

</body>
</html>