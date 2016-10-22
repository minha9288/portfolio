<%-- 김서희  회의 목록 --%>
<html>
<%@page import="com.greenlight.member.MemberBean" %>
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
<%
	  request.setCharacterEncoding("euc-kr");
	  String userId = (String) session.getAttribute("userIdKey");
	  MemberBean mbean = userMgr.getMember(userId);
%>
<head>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="../favicon.ico">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../css/menubarstyle.css">
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

<%@page import="com.greenlight.meetinglist.EnteredBean" %>
<%@page import="com.greenlight.meetinglist.ConfListBean" %>
<%@page import="java.util.Vector" %>
<jsp:useBean id="cMgr" class="com.greenlight.db.ConfListMgr"/>
<%
	request.setCharacterEncoding("EUC-KR");

	int listSize=0; //현재 읽어온 게시물의 수

	Vector<EnteredBean> vlist = null;
	Vector<ConfListBean> vclist = null;
	String meetingName=null;
	int meetingId=0;
%>
<title>회 의 목 록</title>
</head>
<body>
<div align="center">
	<ul class="menu">

		<li><a href="../menu.jsp">Home</a></li>
		<li><a href="#">Meeting</a></li>
		<li><a href="../WebHard/list.jsp">Web Hard</a>
		<li><a href="../cal.jsp">Calendar</a></li>
		<li><a href="../board/list.jsp">A Bulletin Board</a></li>
		<li><a href="../login/BeforeUpdate.jsp">회원정보수정</a></li>
		<li><a href="../login/Logout.jsp">로그아웃</a></li>
	</ul>
	
</div>
<div class="container">
	<br>
	
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
		<h1><%=mbean.getUserName() %>님의 회의 목록</h1></div>
				<%
				vlist = cMgr.getEnteredList(userId);
				listSize = vlist.size();
				if(vlist.isEmpty()) {%>
					<p align="center">참여중인 회의가 없습니다.</p>
				<%} else {
				%><table align="center" border=1>
					<tr align="center">
						<td>이 름</td><td>주 소</td><td>나가기</td><td>삭제하기</td>
					</tr>
					<tr align="center"><%
						for (int j = 0;j<listSize; j++) {
					 		EnteredBean bean = vlist.get(j);
							int bmeetingId = bean.getMeetingId();
							vclist = cMgr.getConfList(bmeetingId);
							ConfListBean cbean = vclist.get(0);
							String bmeetingName = cbean.getMeetingName();
						%>
						<td>
							<%=bmeetingName %>
						</td>
						<td>
							
							&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="window.open('../meeting.jsp?meetingId=<%=bmeetingId %>')">http://www.greenlight.com/?meetingId=<%=bmeetingId %></a>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							<img src="../images/exitbtn.png" alt="나가기 " onclick="javascript:location.href='MeetingDelete.jsp?meetingId=<%=bmeetingId %>'" onmouseover="this.style.cursor='pointer'" >
						</td>
						<td>
							<img src="../images/reallydeletebtn.png" alt="삭제하기" onclick="javascript:location.href='MeetingReallyDelete.jsp?meetingId=<%=bmeetingId %>'" onmouseover="this.style.cursor='pointer'" >
						</td>
					</tr>
					<%} %>
				
			</table>
			<%} %>
			<div class="form-4">
				<img src="../images/newconfbtn.png" alt="회의개설 " onclick="javascript:location.href='MeetingNew.jsp'" onmouseover="this.style.cursor='pointer'">
			</div>
	<%} %>
	</section>
</div>
</body>
</html>