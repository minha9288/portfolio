<%-- �輭��   ȸ�� �����ϱ� ó���ϱ� --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<%@page import="com.greenlight.meetinglist.ConfListBean" %>
<jsp:useBean id="cMgr" class="com.greenlight.db.ConfListMgr"/>

<%
	request.setCharacterEncoding("euc-kr");
	String userId = (String) session.getAttribute("userIdKey");
	int meetingId=Integer.parseInt(request.getParameter("meetingId"));
	boolean result0 = cMgr.deleteMeetingCheck(meetingId, userId);
	if(result0) {
		boolean result = cMgr.deleteMeeting(meetingId, userId);
		if(result){%>
			<script>
				alert("ȸ�Ǹ� �����߽��ϴ�.");	
				location.href="MeetingList.jsp";
			</script>
		<%} else {%>
		<script>
			alert("ȸ�Ǹ� �������� ���߽��ϴ�.");
			location.href="MeetingList.jsp";
		</script>
		<% } 
	}else {
		%>
		<script>
		alert("ȸ�Ǹ� ������ ������ �����ϴ�.");
		location.href="MeetingList.jsp";
		</script>
	<%
	}
%>

