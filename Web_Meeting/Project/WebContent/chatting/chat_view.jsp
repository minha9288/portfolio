<%@page contentType="text/html;charset=EUC-KR"%>
<%@page import="java.util.*"%>
<jsp:useBean id="Socket_Client" class="com.greenlight.chatting.ChatClient"/>
<% 

	
	request.setCharacterEncoding("euc-kr");  // �ѱ� ����
	
	int meetingId= ((Integer) session.getAttribute("meetingId")).intValue();
	
	String chatNum = "chat" + meetingId + "" ;
 	Vector chat  = (Vector)application.getAttribute(chatNum);  //  application�� ������ڰ� ���
     				 // vector�� ���� ������ ����� ����.. ä���� ������ ���� ���̸鼭 ���� �ö󰡴� ����
 	if(chat == null){
  		chat = new Vector(10);  // vector 10�� ���� ����
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

