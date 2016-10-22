<%-- �輭��    ȸ�� ���� --%>
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
<title>ȸ �� �� ��</title>
<script>
var isChecked="f";
var nowID="";
function MeetingCheck() {
	if(document.ConfNewForm.meetingName.value=="") {
		alert("ȸ�� �̸��� �Է����ּ���.");
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
		<a href="../login/BeforeUpdate.jsp">ȸ����������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
		<a href="../login/Logout.jsp">�α׾ƿ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
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
		<h1>ȸ�� ����</h1>
		<p>
			<label for="name">ID</label><input type="text" name="meetingName" placeholder="���� �̸��� �Է����ּ���.">
		</p>
		<p>
			<img src="../images/newconfbtn.png" alt="ȸ�ǰ���" onclick="javascript:MeetingCheck()" onmouseover="this.style.cursor='pointer'">
		</p>
		<p align="right">
			<a href="../menu.jsp">���ư���</a>
		</p>
		<%} %>
	</form>
	</section>
</div>
</body>
</html>