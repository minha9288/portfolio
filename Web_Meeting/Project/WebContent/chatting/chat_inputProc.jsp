<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=EUC-KR"%>
<html>
<head>
<%@ page import="java.util.*" import="java.io.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">

<jsp:useBean id="Socket_Client" class="com.greenlight.chatting.ChatClient"/>
<title>Insert title here</title>
</head>
<body>

<% 

request.setCharacterEncoding("euc-kr");	

	String name = (String)session.getAttribute("userName");  //채팅창에 user이름으로 뿌려주기위한 변수

	int meetingId= ((Integer) session.getAttribute("meetingId")).intValue();  //각각의 회의를 구분
	System.out.println(meetingId);
	
	//각각의 회의마다의 번호로 이름을 두어 내용을 저장할 벡터 생성
	String chatNum = "chat" + meetingId + "" ;
	Vector chat  = (Vector)application.getAttribute(chatNum);  // chat vector 생성
	if(chat == null) {
		chat = new Vector(10); 
	}

	//연결부분
	String connect = request.getParameter("connect");
	boolean networking ;
	if(connect.equals("false")){
		Socket_Client.setUpNetworking();
	}
	
	//메세지 전송부분
	//String text = request.getParameter("text");
	String text = new String(request.getParameter("text").getBytes("8859_1"), "EUC-KR");
	System.out.println(text);
	Socket_Client.actionPerformed(text);
	
	//메세지 수신부분
	BufferedReader reader = Socket_Client.re();
	String message;
	String insert;
	int i = 0;
	
	try{
		while((message = reader.readLine()) != null && i < 1){			
			insert = name + " : " + message;     // 입력 한 사람 대화명과 입력 내용
			chat.add(insert);     // vector 제일 마지막에 add
			application.setAttribute(chatNum, chat);
			System.out.println(chat);
			i++;
		}
	}catch(Exception ex){
		ex.printStackTrace();
	}
%>
	<script>location.replace="chat_input.jsp?connect=true"</script>

</body>
</html>