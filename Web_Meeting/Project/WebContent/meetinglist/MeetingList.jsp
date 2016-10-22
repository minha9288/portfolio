<%-- �輭��  ȸ�� ��� --%>
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

	int listSize=0; //���� �о�� �Խù��� ��

	Vector<EnteredBean> vlist = null;
	Vector<ConfListBean> vclist = null;
	String meetingName=null;
	int meetingId=0;
%>
<title>ȸ �� �� ��</title>
</head>
<body>
<div align="center">
	<ul class="menu">

		<li><a href="../menu.jsp">Home</a></li>
		<li><a href="#">Meeting</a></li>
		<li><a href="../WebHard/list.jsp">Web Hard</a>
		<li><a href="../cal.jsp">Calendar</a></li>
		<li><a href="../board/list.jsp">A Bulletin Board</a></li>
		<li><a href="../login/BeforeUpdate.jsp">ȸ����������</a></li>
		<li><a href="../login/Logout.jsp">�α׾ƿ�</a></li>
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
		<h1><%=mbean.getUserName() %>���� ȸ�� ���</h1></div>
				<%
				vlist = cMgr.getEnteredList(userId);
				listSize = vlist.size();
				if(vlist.isEmpty()) {%>
					<p align="center">�������� ȸ�ǰ� �����ϴ�.</p>
				<%} else {
				%><table align="center" border=1>
					<tr align="center">
						<td>�� ��</td><td>�� ��</td><td>������</td><td>�����ϱ�</td>
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
							<img src="../images/exitbtn.png" alt="������ " onclick="javascript:location.href='MeetingDelete.jsp?meetingId=<%=bmeetingId %>'" onmouseover="this.style.cursor='pointer'" >
						</td>
						<td>
							<img src="../images/reallydeletebtn.png" alt="�����ϱ�" onclick="javascript:location.href='MeetingReallyDelete.jsp?meetingId=<%=bmeetingId %>'" onmouseover="this.style.cursor='pointer'" >
						</td>
					</tr>
					<%} %>
				
			</table>
			<%} %>
			<div class="form-4">
				<img src="../images/newconfbtn.png" alt="ȸ�ǰ��� " onclick="javascript:location.href='MeetingNew.jsp'" onmouseover="this.style.cursor='pointer'">
			</div>
	<%} %>
	</section>
</div>
</body>
</html>