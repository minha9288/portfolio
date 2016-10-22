<%-- �輭��   ȸ������ --%>
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
<title>ȸ �� �� ��</title>
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
<script>
var isChecked="fal";
var nowID="";
function MemberCheck() {
	/*��ĭ�� �ִ��� Ȯ��*/
	if(document.memberForm.userName.value == "") {
		alert("�̸��� �Է����ּ���.");
		document.memberForm.userName.focus();
		return;
	}
	if(document.memberForm.userId.value == "") {
		alert("ID�� �Է����ּ���.");
		document.memberForm.userId.focus();
		return;
	}
	if(document.memberForm.userPassword.value == "") {
		alert("��й�ȣ�� �Է����ּ���.");
		document.memberForm.userPassword.focus();
		return;
	}
	if(document.memberForm.userPasswordCheck.value == "") {
		alert("���Ȯ���� �Է����ּ���.");
		document.memberForm.userPasswordCheck.focus();
		return;
	}
	if(document.memberForm.userPhone.value == "") {
		alert("��ȭ��ȣ�� �Է����ּ���.");
		document.memberForm.userPhone.focus();
		return;
	}
	if(document.memberForm.userEmail.value == "") {
		alert("Email�� �Է����ּ���.");
		document.memberForm.userEmail.focus();
		return;
	}
	/* ��й�ȣ�� ������ �´��� Ȯ�� */
    if(document.memberForm.userPassword.value.length<6) {
          alert("��й�ȣ�� ������ �߸��Ǿ����ϴ�. �ٽ� �ۼ��� �ּ���.");
          document.memberForm.userPassword.value="";
	      document.memberForm.userPassword.focus();
	      return;
    }
	/* ��й�ȣ�� ���Ȯ���� ��ġ�ϴ��� Ȯ�� */
	if(document.memberForm.userPassword.value != document.memberForm.userPasswordCheck.value) {
		alert("��й�ȣ�� ���Ȯ���� ��ġ���� �ʽ��ϴ�. �ٽ� �ۼ��� �ּ���.");
		document.memberForm.userPasswordCheck.value="";
		document.memberForm.userPasswordCheck.focus();
		return;
	}
	/* ��ȭ��ȣ�� ������ �´��� Ȯ�� */
	if(isNaN(document.memberForm.userPhone.value)) {
		alert("��ȭ��ȣ���� ���ڸ� �Է����ּ���.");
		document.memberForm.userPhone.value="";
		document.memberForm.userPhone.focus();
		return;
	}
	/* email�� ������ �´��� Ȯ�� */
	var str=document.memberForm.userEmail.value;	   
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
          document.memberForm.userEmail.value="";
	      document.memberForm.userEmail.focus();
	      return;
    }
    /* id�ߺ�Ȯ���� �ߴ��� Ȯ�� */
    if(isChecked=="fal") {
    	alert("ID�ߺ�Ȯ���� ���ּ���.");
    	document.memberForm.userId.focus();
    	return;
    }
    /* id�ߺ�Ȯ�� �� ID�� �����ߴ��� Ȯ��*/
    if(nowID!=document.memberForm.userId.value) {
    	alert("ID�ߺ�Ȯ���� ���ּ���."+nowID);
    	document.memberForm.userId.focus();
    	isChecked="fal";
    	return;
    }
    document.memberForm.submit();
}
function idCheck() {
	if(document.memberForm.userId.value=="") {
		alert("ID�� �Է����ּ���.");
		document.memberForm.userId.focus();
		return;
	}
	window.open("IdCheck.jsp?id="+document.memberForm.userId.value, "IDCheck", "width=300, height=150");
}
</script>
</head>
<body>
<div class="container">
	<header>
		<h1><strong>GreenLight</strong></h1>
	</header>
	<section class="main">
	<form class="form-4" name="memberForm" method="post" action="MemberProc.jsp">
		<h1>ȸ������</h1>
		<p>
			<label for="name">�̸�</label><input type="text" name="userName" placeholder="�̸��� �Է����ּ���.">
		</p>
		<p>
			<label for="id">ID</label><input type="text" name="userId" placeholder="ID�� �Է����ּ���.(������, ����)" style="ime-mode:disabled;">
			<img src="../images/idcheckbtn.png" alt="ID�ߺ�Ȯ��" onclick="idCheck()" onmouseover="this.style.cursor='pointer'">
		</p>
		<p>
			<label for="password">��й�ȣ</label><input type="password" name="userPassword" placeholder="��й�ȣ�� �Է����ּ���.(6�ڸ� �̻�)">
		</p>
		<p>
			<label for="passwordcheck">���Ȯ��</label><input type="password" name="userPasswordCheck" placeholder="��й�ȣ�� ���Է����ּ���.">
		</p>
		<p>
			<label for="phone">��ȭ��ȣ</label><input type="text" name="userPhone" maxlength=11 placeholder="��ȭ��ȣ�� �Է����ּ���.('-'����)">
		</p>
		<p>
			<label for="email">�̸���</label><input type="text" name="userEmail" placeholder="�̸����� �Է����ּ���.">
		</p>
		<p>
			<img src="../images/memberbtn.png" alt="ȸ������" onclick="javascript:MemberCheck()" onmouseover="this.style.cursor='pointer'">
			<img src="../images/resetbtn.png" alt="�ٽþ���" onclick="reset()" onmouseover="this.style.cursor='pointer'">
		</p>
		<p align="right">
			<a href="Login.jsp">LOGIN</a>
		</p>
	</form>
	</section>
</div>
</body>
</html>