<%@ page contentType="text/html; charset=EUC-KR"%>
<html> 
<head>
<%@page import="com.greenlight.board.BoardBean"%>
<jsp:useBean id="bMgr" class="com.greenlight.db.WebhardMgr2" />
<%
	request.setCharacterEncoding("EUC-KR");
	String nowPage = request.getParameter("nowPage");
	int num = Integer.parseInt(request.getParameter("num"));
	if (request.getParameter("pass") != null) {
		//비번을 입력을 해서 세션에 있는 pass 비교요청
		String inPass = request.getParameter("pass");
		BoardBean bean = (BoardBean) session.getAttribute("bean");
		String dbPass = bean.getPass();
		if (inPass.equals(dbPass)) {
			bMgr.deleteBoard(num);
			String url = "list_meeting.jsp?nowPage=" + nowPage;
			response.sendRedirect(url);
		} else {
%>
<script type="text/javascript">
	alert("입력하신 비밀번호가 아닙니다.");
	history.back();
</script>
<%}
	} else {
%>
<title>JSP Board</title>

<script type="text/javascript">
	function check() {
		if (document.delFrm.pass.value == "") {
			alert("패스워드를 입력하세요.");
			document.delFrm.pass.focus();
			return false;
		}
		document.delFrm.submit();
	}
</script>
</head>
<body>
	<div align="center">
		<table width="100%" cellspacing="0" cellpadding="3">
			<tr>
				<td bgcolor=#dddddd width="20" align="center">
					비밀번호를<br> 입력바랍니다.
				</td>
			</tr>
		</table>
		<form name="delFrm" method="get" action="delete.jsp">
			<table width="70%" cellspacing="0" cellpadding="2">
				<tr>
					<td align="center">
						<table align="center" border="0" width=91%>
							<tr>
								<td align="center">
									<input type="password" name="pass" size="17" maxlength="15">
								</td>
							</tr>
							<tr>
								<td><hr size="1" color="#eeeeee"></td>
							</tr>
							<tr>
								<td align="center">
									<input type="button" value="삭제완료" onClick="check()"> 
									<input type="reset" value="다시쓰기">
									<input type="button" value="뒤로" onClick="history.go(-1)">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" name="nowPage" value="<%=nowPage%>"> 
			<input type="hidden" name="num" value="<%=num%>">
		</form>
	</div>
	<%}%>
</body>
</html>