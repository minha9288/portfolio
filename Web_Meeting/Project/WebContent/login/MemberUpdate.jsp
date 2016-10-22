<%-- �輭��   ȸ�� ���� �����ϱ� --%>
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
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"></jsp:useBean>
<%
String userId=(String)session.getAttribute("userIdKey");
MemberBean bean = userMgr.getMember(userId);
%>
<title>ȸ �� �� �� �� ��</title>
<script type="text/javascript">
function MemberUpdateCheck() {
	/* �� ĭ�� �ִ��� Ȯ�� */
	if(document.memberUpdateForm.userPassword.value == "") {
		alert("��й�ȣ�� �Է����ּ���.");
		document.memberUpdateForm.userPassword.focus();
		return;
	}
	if(document.memberUpdateForm.userPasswordCheck.value == "") {
		alert("���Ȯ���� �Է����ּ���.");
		document.memberUpdateForm.userPasswordCheck.focus();
		return;
	}
	if(document.memberUpdateForm.userPhone.value == "") {
		alert("��ȭ��ȣ�� �Է����ּ���.");
		document.memberUpdateForm.userPhone.focus();
		return;
	}
	if(document.memberUpdateForm.userEmail.value == "") {
		alert("Email�� �Է����ּ���.");
		document.memberUpdateForm.userEmail.focus();
		return;
	}
	/* ��й�ȣ�� ������ �´��� Ȯ�� */
    if (document.memberUpdateForm.userPassword.value.length<6) {
          alert("��й�ȣ�� ������ �߸��Ǿ����ϴ�. �ٽ� �ۼ��� �ּ���.");
          document.memberUpdateForm.userPassword.value="";
	      document.memberUpdateForm.userPassword.focus();
		  return;
    }
	/* ��й�ȣ�� ���Ȯ���� ��ġ�ϴ��� Ȯ�� */
	if(document.memberUpdateForm.userPassword.value != document.memberUpdateForm.userPasswordCheck.value) {
		alert("��й�ȣ�� ���Ȯ���� ��ġ���� �ʽ��ϴ�. �ٽ� �ۼ��� �ּ���.");
		document.memberUpdateForm.userPasswordCheck.value="";
		document.memberUpdateForm.userPasswordCheck.focus();
		return;
	}
	/* ��ȭ��ȣ�� ������ �´��� Ȯ�� */
	if(isNaN(document.memberUpdateForm.userPhone.value)) {
		alert("��ȭ��ȣ���� ���ڸ� �Է����ּ���.");
		document.memberUpdateForm.userPhone.value="";
		document.memberUpdateForm.userPhone.focus();
		return;
	}
	/* email�� ������ �´��� Ȯ�� */
	var str=document.memberUpdateForm.userEmail.value;	   
    var atPos = str.indexOf('@');
    var atLastPos = str.lastIndexOf('@');
    var dotPos = str.indexOf('.'); 
    var spacePos = str.indexOf(' ');
    var commaPos = str.indexOf(',');
    var eMailSize = str.length;
    if (atPos > 1 && atPos == atLastPos && 
	   dotPos > 3 && spacePos == -1 && commaPos == -1 
	   && atPos + 1 < dotPos && dotPos + 1 < eMailSize){}
    else {
          alert("email�� ������ �߸��Ǿ����ϴ�. �ٽ� �ۼ��� �ּ���.");
	      document.memberoForm.userEmail.focus();
		  return;
    }
	document.memberUpdateForm.submit();
}
</script>
</head>
<body>
<div class="container">
	<br>
	<p align="right">
		<a href="Logout.jsp">�α׾ƿ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
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
	<form class="form-4" name="memberUpdateForm" method="post" action="MemberUpdateProc.jsp">
		<h1>ȸ����������</h1>
		<p>
			<label for="name">�̸�</label><input type="text" name="userName" value="<%=bean.getUserName()%>" readonly>
		</p>
		<p>
			<label for="id">ID</label><input type="text" name="userId" value="<%=userId %>" readonly>
		</p>
		<p>
			<label for="password">��й�ȣ</label><input type="password" name="userPassword" placeholder="��й�ȣ�� �Է����ּ���.(6�ڸ� �̻�)">
		</p>
		<p>
			<label for="passwordcheck">���Ȯ��</label><input type="password" name="userPasswordCheck" placeholder="��й�ȣ�� ���Է����ּ���.">
		</p>
		<p>
			<label for="phone">��ȭ��ȣ</label><input type="text" name="userPhone" maxlength=11 value="<%=bean.getUserPhone()%>" placeholder="��ȭ��ȣ�� �Է����ּ���.('-'����)">
		</p>
		<p>
			<label for="email">�̸���</label><input type="text" name="userEmail" value="<%=bean.getUserEmail()%>" placeholder="�̸����� �Է����ּ���.">
		</p>
		<p>
			<img src="../images/memberupdatebtn.png" alt="�����ϱ�" onclick="javascript:MemberUpdateCheck()" onmouseover="this.style.cursor='pointer'">
			<img src="../images/memberdeletebtn.png" alt="Ż���ϱ� " onclick="javascript:location.href='MemberDelete.jsp'" onmouseover="this.style.cursor='pointer'">
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