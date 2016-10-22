<%-- chatting 전송할 내용 입력페이지 --%>
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

	String connect = "";  //socket연결 했는지 안했는지 파악하기위한 변수
	if(request.getParameter("connect")!=null){ //connect값이 null이 아니면 true (chat_inputProc.jsp에서 보내준값)
		connect = "true";
	}
	else{  //처음이면 연결 안된상태
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