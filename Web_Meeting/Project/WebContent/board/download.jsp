<%@ page contentType="application;charset=euc-kr"%>
<jsp:useBean id="bMgr" class="com.greenlight.db.BoardMgr" />
<%
	  bMgr.downLoad(request, response,out,pageContext);
%>