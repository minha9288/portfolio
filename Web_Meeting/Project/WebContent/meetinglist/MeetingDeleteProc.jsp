<%-- �輭��   ȸ�� ������ ó���ϱ� --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<%@page import="com.greenlight.meetinglist.ConfListBean" %>
<jsp:useBean id="cMgr" class="com.greenlight.db.ConfListMgr"/>

<%
	int meetingId=Integer.parseInt(request.getParameter("meetingId"));
	boolean result = cMgr.exitMeeting(meetingId);
	if(result){
%>
		<script>
		alert("ȸ�ǿ��� ���Խ��ϴ�.");	
		location.href="MeetingList.jsp";
		</script>
<%
	} else {
		%>
		<script>
		alert("ȸ�ǿ��� ������ ���߽��ϴ�.");
		location.href="MeetingList.jsp";
		</script>
		<%
	}
%>