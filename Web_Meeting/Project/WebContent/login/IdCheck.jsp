<%-- �輭��   ID �ߺ� Ȯ�� --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<html>
<head>
	<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
	<%
	request.setCharacterEncoding("EUC-KR");
	String id=request.getParameter("id");
	boolean result = userMgr.idChecking(id);
	%>
	<title>I D �� �� Ȯ ��</title>
</head>
<body>
	<div align="center">
		<br><b><%=id %></b>
		<%
		if(result) {
			out.println("��(��) �̹� �����մϴ�. �ٽ� �ۼ��� �ּ���.");
			%>
			<script>opener.isChecked="fal";</script>
			<%
		} else {
			out.println("��(��) ��� ���� �մϴ�.");
			%>
			<script>
			opener.isChecked="tr"; 
			opener.nowID="<%=id %>";
			</script>
			<%
		}
		%>
		<br><br>
		<a href="#" onClick="self.close()">�ݱ�</a>
	</div>
</body>
</html>