<%-- �輭��  �����ϱ�� ���� �� ȸ�� Ȯ���� ���� ��й�ȣ Ȯ�� --%>
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
<title>ȸ �� Ȯ ��</title>
<script>
function EmptyCheck() {
	if(document.beforeUpdateForm.userPassword.value == "") {
		alert("��й�ȣ�� �Է����ּ���.");
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
	<p align="right"><a href="Logout.jsp">�α׾ƿ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></p>
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
		<h1>ȸ��Ȯ��</h1>
		<p>
			<label for="name">�̸�</label><input type="text" name="userName" value="<%=mbean.getUserName()%>" readonly>
		</p>
		<p>
			<label for="id">ID</label><input type="text" name="userId" value="<%=userId %>" readonly>
		</p>
		<p>
			<label for="password">��й�ȣ</label><input type="password" name="userPassword" placeholder="��й�ȣ�� �Է����ּ���.">
		</p>
		<p>
			<img src="../images/okbtn.png" alt="Ȯ�� " onclick="javascript:EmptyCheck()" onmouseover="this.style.cursor='pointer'">
		</p>
		<p align="right">
			<a href="../menu.jsp">���ư���</a>
		</p>
	</form>
	<%} %>
	</section>
</div>
</body>
</html>