<%-- �輭��   ȸ������ ó�� --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
<jsp:useBean id="bean" class="com.greenlight.member.MemberBean"/>
<jsp:setProperty name="bean" property="*"/>
<%
request.setCharacterEncoding("euc-kr");
String userId=request.getParameter("userId");
boolean result = userMgr.insertUser(bean);
if(result) {
	session.setAttribute("userIdKey", userId);
%>
<script>
	alert("ȸ�������� �Ǿ����ϴ�.");
	location.href="../menu.jsp";
</script>
<%} else { %>
<script>
	alert("ȸ�������� ���� �ʾҽ��ϴ�. �ٽ� �õ��� �ּ���.");
	history.back();
</script>
<%} %>