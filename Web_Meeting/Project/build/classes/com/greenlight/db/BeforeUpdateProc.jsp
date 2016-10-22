<%-- 김서희   수정하기로 가기 전 비밀번호가 맞는지 확인 처리 --%>
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
		alert("비밀번호가 틀렸습니다. 다시 써주세요.");
		location.href="BeforeUpdate.jsp";
		</script>
	<% } %>