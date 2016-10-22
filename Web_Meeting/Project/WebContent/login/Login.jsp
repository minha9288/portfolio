<%-- �輭��   �α��� --%>
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
<title>�� �� ��</title>
<script type="text/javascript">
	function LoginCheck() {
		if(document.loginForm.userId.value == "") {
			alert("���̵� �Է��� �ּ���.");
			document.loginForm.userId.focus();
			return;
		}
		if (document.loginForm.userPassword.value == "") {
			alert("��й�ȣ�� �Է��� �ּ���.");
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
			<label for="id">ID</label><input type="text" name="userId" placeholder="ID�� �Է����ּ���.">
		</p>
		<p>
			<label for="password">��й�ȣ</label><input type="password" name="userPassword" placeholder="��й�ȣ�� �Է����ּ���.">
		</p>
		<p>
			<img src="../images/loginbtn.PNG" alt="�α��� " onclick="javascript:LoginCheck()" onmouseover="this.style.cursor='pointer'">
		</p>
		<p align="right">
			<a href="Member.jsp">ȸ������</a>
		</p>
	</form>
	<%} %>
	</section>
</div>
</body>
</html>