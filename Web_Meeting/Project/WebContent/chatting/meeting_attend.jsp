<html>
<head>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<jsp:useBean id="meetingMgr" class="com.greenlight.db.MeetingMgr"/>
<%@ page import="java.util.*" %>

</head>
<body background="../images/chat_attend_bg.jpg">

<table border="0">
	<tr>
		<td><b>ȸ�� ������</b><br></td>
	</tr>
	<tr>
		<td>
			<form name="searchForm" action="inviteProc.jsp">
			<input type="text" name="search_id" value="ID�� �Է��ϼ���">
			<input type="submit" name="invite" value="�ʴ�">
	</form>
	<hr color="black">

	<%
		int meetingId= ((Integer) session.getAttribute("meetingId")).intValue();
		String attendList;
		Vector list = meetingMgr.enteredMeeting(meetingId);
		for(int i=0; i<list.size(); i++){
			attendList = (String) list.elementAt(i);
	%>
			<%=attendList %><br>
	<%	
		}
	%>
		
		<td>
	</tr>
</table>

</body>
</html>