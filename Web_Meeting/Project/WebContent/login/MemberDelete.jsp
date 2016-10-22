<%-- 김서희   회원 탈퇴하기 --%>
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
<%
	  request.setCharacterEncoding("euc-kr");
	  String userId = (String) session.getAttribute("userIdKey");
%>
<title>탈 퇴 하 기</title>
</head>
<body>
<div class="container">
	<br>
	<p align="right">
		<a href="Logout.jsp">로그아웃&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
	</p>
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
	<form class="form-4" name="loginForm" method="post" action="MemberDeleteProc.jsp">
		<h3 align="center">정말로 탈퇴하시겠습니까?</h3>
		<br>
		<p>
			<img src="../images/memberdeletebtn.png" alt="탈퇴하기" onclick="submit()" onmouseover="this.style.cursor='pointer'">
			<img src="../images/nobtn.png" alt="취소 " onClick="javascript:location.href='../menu.jsp'" onmouseover="this.style.cursor='pointer'">
		</p>
	</form>
	<%} %>
	</section>
</div>
</body>
</html>