<%-- �輭��    ȸ�ǰ��� ó�� --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="meetingMgr" class="com.greenlight.db.ConfListMgr"/>
<%
request.setCharacterEncoding("euc-kr");
String meetingName=request.getParameter("meetingName");
String userId=(String)session.getAttribute("userIdKey");
boolean result=meetingMgr.nameChecking(meetingName);



if(result) {%>
	<script>
	alert("ȸ���̸��� �ߺ��˴ϴ�. �̸��� �ٲپ� �ٽ� �õ����ּ���.");
	location.href="MeetingNew.jsp";
	</script>
<%}
else {
	boolean result1=meetingMgr.insertMeeting(meetingName, userId);
	if(result1) {
		int meetingId=meetingMgr.getId(meetingName);
		boolean result2=meetingMgr.insertEntered(meetingId, userId);
		if(result2) {%>
		<script>
			alert("ȸ�ǰ� �����Ǿ����ϴ�.");
			location.href="test.jsp?meetingName=<%=meetingName %>";
		</script>
		<% } else {%>
		<script>
			alert("ȸ�ǰ� �������� �ʾҽ��ϴ�. �ٽ� �õ����ּ���.");
			history.back();
		</script>
		<%} 
	} else {
		%>
		<script>
			alert("ȸ�ǰ� �������� �ʾҽ��ϴ�. �ٽ� �õ����ּ���.");
			history.back();
		</script>
	<%} 
}%>