<%-- �輭��   ȸ�� Ż�� ó���ϱ� --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<%@page import="com.greenlight.member.MemberBean" %>
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>

<%
	String id=(String)session.getAttribute("userIdKey");
	boolean result = userMgr.deleteMember(id);
	if(result){
%>
		<script>
		alert("ȸ��Ż�� �߽��ϴ�.");	
		location.href="Login.jsp";
		</script>
<%
		session.invalidate();
	} else {
		%>
		<script>
		alert("ȸ��Ż�� �����߽��ϴ�.");
		location.href="../menu.jsp";
		</script>
		<%
	}
%>