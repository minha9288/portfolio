<%-- 김서희   로그인 --%>
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
<title>로 그 인</title>
<script type="text/javascript">
	function LoginCheck() {
		if(document.loginForm.userId.value == "") {
			alert("아이디를 입력해 주세요.");
			document.loginForm.userId.focus();
			return;
		}
		if (document.loginForm.userPassword.value == "") {
			alert("비밀번호를 입력해 주세요.");
			document.loginForm.userPassword.focus();
			return;
		}
		document.loginForm.submit();
	}
</script>

</head>
<body>
<div class="container">
	<header>
		<h1><strong>GreenLight</strong></h1>
	</header>
	<section class="main">
	<%
	if (userId != null) {
	%>
		<script>
		location.href="../menu.jsp";
		</script>
	<%} else { %>
	<form name="loginForm" class="form-4" method="post" action="LoginProc.jsp">
		<h1>Login</h1>
		<p>
			<label for="id">ID</label><input type="text" name="userId" placeholder="ID를 입력해주세요.">
		</p>
		<p>
			<label for="password">비밀번호</label><input type="password" name="userPassword" placeholder="비밀번호를 입력해주세요.">
		</p>
		<p>
			<img src="../images/loginbtn.PNG" alt="로그인 " onclick="javascript:LoginCheck()" onmouseover="this.style.cursor='pointer'">
		</p>
		<p align="right">
			<a href="Member.jsp">회원가입</a>
		</p>
	</form>
	<%} %>
	</section>
</div>
</body>
</html>