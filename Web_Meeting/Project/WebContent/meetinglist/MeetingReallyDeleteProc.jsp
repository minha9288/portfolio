<%-- 김서희   회의 삭제하기 처리하기 --%>
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
				alert("회의를 삭제했습니다.");	
				location.href="MeetingList.jsp";
			</script>
		<%} else {%>
		<script>
			alert("회의를 삭제하지 못했습니다.");
			location.href="MeetingList.jsp";
		</script>
		<% } 
	}else {
		%>
		<script>
		alert("회의를 삭제할 권한이 없습니다.");
		location.href="MeetingList.jsp";
		</script>
	<%
	}
%>

