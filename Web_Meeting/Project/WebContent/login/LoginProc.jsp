<%-- �輭��   �α��� ó�� --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
<%
	request.setCharacterEncoding("euc-kr");
	String userId=request.getParameter("userId");
	String userPassword=request.getParameter("userPassword");

	boolean result = userMgr.loginMember(userId, userPassword);
	String name = userMgr.returnName(userId);  //���� �߰� - ä�ù濡 �̸��ޱ�
	
	if(result) {
		session.setAttribute("userIdKey", userId); 
		session.setAttribute("userName", name);  // ���� �߰�

%>
		<script>location.href="../menu.jsp"; </script>
	<% } else { %>
		<script>
		alert("ID �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �α��� ���ּ���.");
		location.href="Login.jsp";
		</script>
	<% } %>