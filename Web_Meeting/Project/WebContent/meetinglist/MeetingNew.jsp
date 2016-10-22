<%-- 김서희    회의 개설 --%>
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
<jsp:useBean id="confMgr" class="com.greenlight.db.ConfListMgr"/>
	
<%request.setCharacterEncoding("euc-kr");
String userId = (String) session.getAttribute("userIdKey"); %>
<title>회 의 개 설</title>
<script>
var isChecked="f";
var nowID="";
function MeetingCheck() {
	if(document.ConfNewForm.meetingName.value=="") {
		alert("회의 이름을 입력해주세요.");
		document.ConfNewForm.meetingName.focus();
		return;
	}
	document.ConfNewForm.submit();
}
</script>
</head>
<body>
<div class="container">
	<br>
	<p align="right">
		<a href="../login/BeforeUpdate.jsp">회원정보수정&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
		<a href="../login/Logout.jsp">로그아웃&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
	</p>
	<header>
		<h1><strong>GreenLight</strong></h1>
	</header>
	<section class="main">
	<%
	if (userId == null) {
	%>
		<script>
		location.href="../login/Login.jsp";
		</script>
	<%} else { %>
	<form class="form-4" name="ConfNewForm" method="post" action="MeetingNewProc.jsp">
		<h1>회의 개설</h1>
		<p>
			<label for="name">ID</label><input type="text" name="meetingName" placeholder="희의 이름을 입력해주세요.">
		</p>
		<p>
			<img src="../images/newconfbtn.png" alt="회의개설" onclick="javascript:MeetingCheck()" onmouseover="this.style.cursor='pointer'">
		</p>
		<p align="right">
			<a href="../menu.jsp">돌아가기</a>
		</p>
		<%} %>
	</form>
	</section>
</div>
</body>
</html>