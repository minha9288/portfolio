<%-- 김서희   회원가입 처리 --%>
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
	alert("회원가입이 되었습니다.");
	location.href="../menu.jsp";
</script>
<%} else { %>
<script>
	alert("회원가입이 되지 않았습니다. 다시 시도해 주세요.");
	history.back();
</script>
<%} %>