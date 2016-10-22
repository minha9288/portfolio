<%-- 김서희   ID 중복 확인 --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<html>
<head>
	<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
	<%
	request.setCharacterEncoding("EUC-KR");
	String id=request.getParameter("id");
	boolean result = userMgr.idChecking(id);
	%>
	<title>I D 중 복 확 인</title>
</head>
<body>
	<div align="center">
		<br><b><%=id %></b>
		<%
		if(result) {
			out.println("는(은) 이미 존재합니다. 다시 작성해 주세요.");
			%>
			<script>opener.isChecked="fal";</script>
			<%
		} else {
			out.println("는(은) 사용 가능 합니다.");
			%>
			<script>
			opener.isChecked="tr"; 
			opener.nowID="<%=id %>";
			</script>
			<%
		}
		%>
		<br><br>
		<a href="#" onClick="self.close()">닫기</a>
	</div>
</body>
</html>