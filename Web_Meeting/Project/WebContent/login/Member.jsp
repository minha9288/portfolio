<%-- 김서희   회원가입 --%>
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
<title>회 원 가 입</title>
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
<script>
var isChecked="fal";
var nowID="";
function MemberCheck() {
	/*빈칸이 있는지 확인*/
	if(document.memberForm.userName.value == "") {
		alert("이름을 입력해주세요.");
		document.memberForm.userName.focus();
		return;
	}
	if(document.memberForm.userId.value == "") {
		alert("ID를 입력해주세요.");
		document.memberForm.userId.focus();
		return;
	}
	if(document.memberForm.userPassword.value == "") {
		alert("비밀번호를 입력해주세요.");
		document.memberForm.userPassword.focus();
		return;
	}
	if(document.memberForm.userPasswordCheck.value == "") {
		alert("비번확인을 입력해주세요.");
		document.memberForm.userPasswordCheck.focus();
		return;
	}
	if(document.memberForm.userPhone.value == "") {
		alert("전화번호를 입력해주세요.");
		document.memberForm.userPhone.focus();
		return;
	}
	if(document.memberForm.userEmail.value == "") {
		alert("Email을 입력해주세요.");
		document.memberForm.userEmail.focus();
		return;
	}
	/* 비밀번호의 형식이 맞는지 확인 */
    if(document.memberForm.userPassword.value.length<6) {
          alert("비밀번호의 형식이 잘못되었습니다. 다시 작성해 주세요.");
          document.memberForm.userPassword.value="";
	      document.memberForm.userPassword.focus();
	      return;
    }
	/* 비밀번호와 비번확인이 일치하는지 확인 */
	if(document.memberForm.userPassword.value != document.memberForm.userPasswordCheck.value) {
		alert("비밀번호와 비번확인이 일치하지 않습니다. 다시 작성해 주세요.");
		document.memberForm.userPasswordCheck.value="";
		document.memberForm.userPasswordCheck.focus();
		return;
	}
	/* 전화번호의 형식이 맞는지 확인 */
	if(isNaN(document.memberForm.userPhone.value)) {
		alert("전화번호에는 숫자만 입력해주세요.");
		document.memberForm.userPhone.value="";
		document.memberForm.userPhone.focus();
		return;
	}
	/* email의 형식이 맞는지 확인 */
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
          alert("email의 형식이 잘못되었습니다. 다시 작성해 주세요.");
          document.memberForm.userEmail.value="";
	      document.memberForm.userEmail.focus();
	      return;
    }
    /* id중복확인을 했는지 확인 */
    if(isChecked=="fal") {
    	alert("ID중복확인을 해주세요.");
    	document.memberForm.userId.focus();
    	return;
    }
    /* id중복확인 후 ID를 변경했는지 확인*/
    if(nowID!=document.memberForm.userId.value) {
    	alert("ID중복확인을 해주세요."+nowID);
    	document.memberForm.userId.focus();
    	isChecked="fal";
    	return;
    }
    document.memberForm.submit();
}
function idCheck() {
	if(document.memberForm.userId.value=="") {
		alert("ID를 입력해주세요.");
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
		<h1>회원가입</h1>
		<p>
			<label for="name">이름</label><input type="text" name="userName" placeholder="이름을 입력해주세요.">
		</p>
		<p>
			<label for="id">ID</label><input type="text" name="userId" placeholder="ID를 입력해주세요.(영문자, 숫자)" style="ime-mode:disabled;">
			<img src="../images/idcheckbtn.png" alt="ID중복확인" onclick="idCheck()" onmouseover="this.style.cursor='pointer'">
		</p>
		<p>
			<label for="password">비밀번호</label><input type="password" name="userPassword" placeholder="비밀번호를 입력해주세요.(6자리 이상)">
		</p>
		<p>
			<label for="passwordcheck">비번확인</label><input type="password" name="userPasswordCheck" placeholder="비밀번호를 재입력해주세요.">
		</p>
		<p>
			<label for="phone">전화번호</label><input type="text" name="userPhone" maxlength=11 placeholder="전화번호를 입력해주세요.('-'제외)">
		</p>
		<p>
			<label for="email">이메일</label><input type="text" name="userEmail" placeholder="이메일을 입력해주세요.">
		</p>
		<p>
			<img src="../images/memberbtn.png" alt="회원가입" onclick="javascript:MemberCheck()" onmouseover="this.style.cursor='pointer'">
			<img src="../images/resetbtn.png" alt="다시쓰기" onclick="reset()" onmouseover="this.style.cursor='pointer'">
		</p>
		<p align="right">
			<a href="Login.jsp">LOGIN</a>
		</p>
	</form>
	</section>
</div>
</body>
</html>