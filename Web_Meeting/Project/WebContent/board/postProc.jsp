<%@ page contentType="text/html; charset=EUC-KR"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="bMgr" class="com.greenlight.db.BoardMgr"/>
<%
	  bMgr.insertBoard(request);
	  response.sendRedirect("list.jsp");
%>