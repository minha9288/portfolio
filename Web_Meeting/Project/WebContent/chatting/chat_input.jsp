<%-- chatting ������ ���� �Է������� --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=EUC-KR"%>
<html>
<head>
<%@ page import="java.io.*, java.net.*, java.util.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<%
	request.setCharacterEncoding("euc-kr");	
%>
<script>
	function textReset(){
		document.chat.text.value="";
	}
</script>
</head>
<body onload="javascript:textReset()" background="../images/chat_attend_bg.jpg">
<%

	String connect = "";  //socket���� �ߴ��� ���ߴ��� �ľ��ϱ����� ����
	if(request.getParameter("connect")!=null){ //connect���� null�� �ƴϸ� true (chat_inputProc.jsp���� �����ذ�)
		connect = "true";
	}
	else{  //ó���̸� ���� �ȵȻ���
		connect = "false";
	}

%>
<form name="chat" action="chat_inputProc.jsp">
	<input type="text" name="text" value="">
	<input type="submit" value="send">
	<input type="hidden" name="connect" value=<%=connect%>>
</form>
</body>
</html>