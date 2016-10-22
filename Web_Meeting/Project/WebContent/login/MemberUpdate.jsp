<%-- 김서희   회원 정보 수정하기 --%>
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
<title>회 원 정 보 수 정</title>
<script type="text/javascript">
function MemberUpdateCheck() {
	/* 빈 칸이 있는지 확인 */
	if(document.memberUpdateForm.userPassword.value == "") {
		alert("비밀번호를 입력해주세요.");
		document.memberUpdateForm.userPassword.focus();
		return;
	}
	if(document.memberUpdateForm.userPasswordCheck.value == "") {
		alert("비번확인을 입력해주세요.");
		document.memberUpdateForm.userPasswordCheck.focus();
		return;
	}
	if(document.memberUpdateForm.userPhone.value == "") {
		alert("전화번호를 입력해주세요.");
		document.memberUpdateForm.userPhone.focus();
		return;
	}
	if(document.memberUpdateForm.userEmail.value == "") {
		alert("Email을 입력해주세요.");
		document.memberUpdateForm.userEmail.focus();
		return;
	}
	/* 비밀번호의 형식이 맞는지 확인 */
    if (document.memberUpdateForm.userPassword.value.length<6) {
          alert("비밀번호의 형식이 잘못되었습니다. 다시 작성해 주세요.");
          document.memberUpdateForm.userPassword.value="";
	      document.memberUpdateForm.userPassword.focus();
		  return;
    }
	/* 비밀번호와 비번확인이 일치하는지 확인 */
	if(document.memberUpdateForm.userPassword.value != document.memberUpdateForm.userPasswordCheck.value) {
		alert("비밀번호와 비번확인이 일치하지 않습니다. 다시 작성해 주세요.");
		document.memberUpdateForm.userPasswordCheck.value="";
		document.memberUpdateForm.userPasswordCheck.focus();
		return;
	}
	/* 전화번호의 형식이 맞는지 확인 */
	if(isNaN(document.memberUpdateForm.userPhone.value)) {
		alert("전화번호에는 숫자만 입력해주세요.");
		document.memberUpdateForm.userPhone.value="";
		document.memberUpdateForm.userPhone.focus();
		return;
	}
	/* email의 형식이 맞는지 확인 */
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
          alert("email의 형식이 잘못되었습니다. 다시 작성해 주세요.");
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
	<form class="form-4" name="memberUpdateForm" method="post" action="MemberUpdateProc.jsp">
		<h1>회원정보수정</h1>
		<p>
			<label for="name">이름</label><input type="text" name="userName" value="<%=bean.getUserName()%>" readonly>
		</p>
		<p>
			<label for="id">ID</label><input type="text" name="userId" value="<%=userId %>" readonly>
		</p>
		<p>
			<label for="password">비밀번호</label><input type="password" name="userPassword" placeholder="비밀번호를 입력해주세요.(6자리 이상)">
		</p>
		<p>
			<label for="passwordcheck">비번확인</label><input type="password" name="userPasswordCheck" placeholder="비밀번호를 재입력해주세요.">
		</p>
		<p>
			<label for="phone">전화번호</label><input type="text" name="userPhone" maxlength=11 value="<%=bean.getUserPhone()%>" placeholder="전화번호를 입력해주세요.('-'제외)">
		</p>
		<p>
			<label for="email">이메일</label><input type="text" name="userEmail" value="<%=bean.getUserEmail()%>" placeholder="이메일을 입력해주세요.">
		</p>
		<p>
			<img src="../images/memberupdatebtn.png" alt="수정하기" onclick="javascript:MemberUpdateCheck()" onmouseover="this.style.cursor='pointer'">
			<img src="../images/memberdeletebtn.png" alt="탈퇴하기 " onclick="javascript:location.href='MemberDelete.jsp'" onmouseover="this.style.cursor='pointer'">
		</p>
		<p align="right">
			<a href="../menu.jsp">돌아가기</a>
		</p>
	</form>
	<%} %>
	</section>
</div>
</body>
</html>