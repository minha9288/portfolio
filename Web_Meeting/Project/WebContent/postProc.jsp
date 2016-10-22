 <%@ page contentType="text/html; charset=EUC-KR"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="bMgr" class="com.greenlight.db.WebhardMgr2"/>
<%
	  bMgr.insertBoard(request);
	  response.sendRedirect("list_meeting.jsp");
%>