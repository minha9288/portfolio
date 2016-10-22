<%@ page contentType="application;charset=euc-kr"%>
<jsp:useBean id="bMgr" class="com.greenlight.db.WebhardMgr" />
<%
	  bMgr.downLoad(request, response,out,pageContext);
%> 