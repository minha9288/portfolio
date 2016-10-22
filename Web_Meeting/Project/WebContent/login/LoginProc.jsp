<%-- 김서희   로그인 처리 --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
<%
	request.setCharacterEncoding("euc-kr");
	String userId=request.getParameter("userId");
	String userPassword=request.getParameter("userPassword");

	boolean result = userMgr.loginMember(userId, userPassword);
	String name = userMgr.returnName(userId);  //민하 추가 - 채팅방에 이름받기
	
	if(result) {
		session.setAttribute("userIdKey", userId); 
		session.setAttribute("userName", name);  // 민하 추가

%>
		<script>location.href="../menu.jsp"; </script>
	<% } else { %>
		<script>
		alert("ID 또는 비밀번호가 틀렸습니다. 다시 로그인 해주세요.");
		location.href="Login.jsp";
		</script>
	<% } %>