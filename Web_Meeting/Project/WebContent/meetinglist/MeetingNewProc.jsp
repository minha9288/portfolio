<%-- 김서희    회의개설 처리 --%>
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
	alert("회의이름이 중복됩니다. 이름을 바꾸어 다시 시도해주세요.");
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
			alert("회의가 개설되었습니다.");
			location.href="test.jsp?meetingName=<%=meetingName %>";
		</script>
		<% } else {%>
		<script>
			alert("회의가 개설되지 않았습니다. 다시 시도해주세요.");
			history.back();
		</script>
		<%} 
	} else {
		%>
		<script>
			alert("회의가 개설되지 않았습니다. 다시 시도해주세요.");
			history.back();
		</script>
	<%} 
}%>