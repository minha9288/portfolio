<%-- �輭��  ȸ�� ������ --%>
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
	int meetingId=Integer.parseInt(request.getParameter("meetingId"));
%>
<title>ȸ �� �� �� ��</title>
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
	<div class="form-4">
		<h3 align="center">������ �����ðڽ��ϱ�?</h3>
		<br>
		<p>
			<img src="../images/meetingexitbtn.png" alt="������" onclick="javascript:location.href='MeetingDeleteProc.jsp?meetingId=<%=meetingId %>'" onmouseover="this.style.cursor='pointer'">
			<img src="../images/nobtn.png" alt="��� " onClick="javascript:location.href='MeetingList.jsp'" onmouseover="this.style.cursor='pointer'">
		</p>
	</div>
	<%} %>
	</section>
</div>
</body>
</html>