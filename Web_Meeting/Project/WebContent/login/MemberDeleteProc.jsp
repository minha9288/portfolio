<%-- ±è¼­Èñ   È¸¿ø Å»Åð Ã³¸®ÇÏ±â --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<%@page import="com.greenlight.member.MemberBean" %>
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>

<%
	String id=(String)session.getAttribute("userIdKey");
	boolean result = userMgr.deleteMember(id);
	if(result){
%>
		<script>
		alert("È¸¿øÅ»Åð¸¦ Çß½À´Ï´Ù.");	
		location.href="Login.jsp";
		</script>
<%
		session.invalidate();
	} else {
		%>
		<script>
		alert("È¸¿øÅ»Åð¿¡ ½ÇÆÐÇß½À´Ï´Ù.");
		location.href="../menu.jsp";
		</script>
		<%
	}
%>