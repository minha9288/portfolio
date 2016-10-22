<%@page contentType="text/html;charset=EUC-KR"%>
<%@page import="java.util.*"%>
<jsp:useBean id="Socket_Client" class="com.greenlight.chatting.ChatClient"/>
<% 

	
	request.setCharacterEncoding("euc-kr");  // 한글 지원
	
	int meetingId= ((Integer) session.getAttribute("meetingId")).intValue();
	
	String chatNum = "chat" + meetingId + "" ;
 	Vector chat  = (Vector)application.getAttribute(chatNum);  //  application은 전사용자가 사용
     				 // vector는 스택 구조와 비슷한 형태.. 채팅한 내용이 점점 쌓이면서 위로 올라가는 형태
 	if(chat == null){
  		chat = new Vector(10);  // vector 10개 공간 생성
 	}
%>

<meta http-equiv='refresh' content='1'>  
<html>
<body background="../images/chat_bg.jpg">
<%
/*
	String message;
	message = request.getParameter("message");
	System.out.println(message);
*/
 String line;
 for(int i = 0; i < chat.size(); i++){
  line = (String) chat.elementAt(i);
  out.println(line + "<br>"); 
 }
%>
	</body>
</html>

