<%-- �輭��   �����ϱ�� ���� �� ��й�ȣ�� �´��� Ȯ�� ó�� --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
<%
	request.setCharacterEncoding("euc-kr");
	String userId=request.getParameter("userId");
	String userPassword=request.getParameter("userPassword");
	
	boolean result = userMgr.passwordCheck(userId, userPassword);
	if(result) {%>
		<script>
		location.href="MemberUpdate.jsp";
		</script>
	<% } else { %>
		<script>
		alert("��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� ���ּ���.");
		location.href="BeforeUpdate.jsp";
		</script>
	<% } %>