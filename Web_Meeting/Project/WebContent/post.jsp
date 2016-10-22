<%@ page contentType="text/html; charset=EUC-KR"%>
<html>
<head>
<title>파일 업로드</title>
<%@page import="com.greenlight.member.MemberBean" %>
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
<%
	String userId = (String) session.getAttribute("userIdKey");
	MemberBean mbean = userMgr.getMember(userId);
%>
</head>
<body>
	<div align="center">
		<form name="frmName1" method="post" action="postProc.jsp"
			enctype="multipart/form-data">
			<table width="100%" cellspacing="0" cellpadding="0">
				
						<table border="0" width="100%" align="center">
							<tr>
								<td align="center" bgcolor="#DDDDDD">제목</td>
								<td bgcolor="#FFFFE8" width="10"><input type="text" name="subject" size="10"
									></td>
							</tr>
							<tr>
								<td align="center" bgcolor="#DDDDDD">비밀 번호</td>
								<td bgcolor="#FFFFE8" width="10"><input type="password" name="pass"
									size="10">
							</tr>
							<tr>
							<tr>
								<td align="center" bgcolor="#DDDDDD">파일첨부</td>
								<td bgcolor="#FFFFE8"><input type="file" name="filename"
									size="10"></td>
							</tr>
							<tr>
								<td><input type="hidden" name="contentType" value="HTTP">
								</td>
							</tr>
							<tr>
								<td colspan="2"></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="submit"
									value="업로드"> <input type="reset" value="다시쓰기">
									<input type="button" value="리스트"
									onClick="javascript:location.href='list_meeting.jsp'"></td>
							</tr>
						</table>
					
			
		</form>
	</div>
</body>
</html>