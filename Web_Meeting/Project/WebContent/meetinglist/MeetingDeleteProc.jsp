<%-- 김서희   회의 나가기 처리하기 --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<%@page import="com.greenlight.meetinglist.ConfListBean" %>
<jsp:useBean id="cMgr" class="com.greenlight.db.ConfListMgr"/>

<%
	int meetingId=Integer.parseInt(request.getParameter("meetingId"));
	boolean result = cMgr.exitMeeting(meetingId);
	if(result){
%>
		<script>
		alert("회의에서 나왔습니다.");	
		location.href="MeetingList.jsp";
		</script>
<%
	} else {
		%>
		<script>
		alert("회의에서 나가지 못했습니다.");
		location.href="MeetingList.jsp";
		</script>
		<%
	}
%>