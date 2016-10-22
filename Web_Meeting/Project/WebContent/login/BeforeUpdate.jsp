<%-- 김서희  수정하기로 가기 전 회원 확인을 위해 비밀번호 확인 --%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<script src="../js/modernizr.custom.63321.js"></script>
<style>
@import url(http://fonts.googleapis.com/css?family=Raleway:400,700);

body {
	background: #7f9b4e url(../images/bg2.jpg) no-repeat center top;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	background-size: cover;
}

.container>header h1,.container>header h2 {
	color: #fff;
	text-shadow: 0 1px 1px rgba(0, 0, 0, 0.7);
}
</style>
<%@page import="com.greenlight.member.MemberBean" %>
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
<%
String userId=(String)session.getAttribute("userIdKey");
MemberBean mbean = userMgr.getMember(userId);
%>
<title>회 원 확 인</title>
<script>
function EmptyCheck() {
	if(document.beforeUpdateForm.userPassword.value == "") {
		alert("비밀번호를 입력해주세요.");
		document.beforeUpdateForm.userPassword.focus();
		return;
	}
	document.beforeUpdateForm.submit();
}
</script>
</head>
<body>
<div class="container">
	<br>
	<p align="right"><a href="Logout.jsp">로그아웃&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></p>
	<header>
		<h1><strong>GreenLight</strong></h1>
	</header>
	<section class="main">
	<%
	if (userId == null) {
	%>
		<script>
		location.href="Login.jsp";
		</script>
	<%} else { %>
	<form class="form-4" name="beforeUpdateForm" method="post" action="BeforeUpdateProc.jsp">
		<h1>회원확인</h1>
		<p>
			<label for="name">이름</label><input type="text" name="userName" value="<%=mbean.getUserName()%>" readonly>
		</p>
		<p>
			<label for="id">ID</label><input type="text" name="userId" value="<%=userId %>" readonly>
		</p>
		<p>
			<label for="password">비밀번호</label><input type="password" name="userPassword" placeholder="비밀번호를 입력해주세요.">
		</p>
		<p>
			<img src="../images/okbtn.png" alt="확인 " onclick="javascript:EmptyCheck()" onmouseover="this.style.cursor='pointer'">
		</p>
		<p align="right">
			<a href="../menu.jsp">돌아가기</a>
		</p>
	</form>
	<%} %>
	</section>
</div>
</body>
</html>