<%@page contentType="text/html;charset=EUC-KR"%>
<%@page import="java.util.*"%>
<% 
	request.setCharacterEncoding("euc-kr");  // 한글 지원

	int meetingId= ((Integer) session.getAttribute("meetingId")).intValue();  //각각의 회의를 구분
	//int meetingId = 2; //테스트

	//각각의 회의마다의 번호로 이름을 두어 내용을 저장할 벡터 생성
	String chatNum = "chat" + meetingId + "" ;
	Vector chat  = (Vector)application.getAttribute(chatNum);  // chat vector 생성
	if(chat == null){
 		chat = new Vector(10);  // vector 10개 공간 생성
 	}
%>
<meta http-equiv='refresh' content='1'>  
<html>
<table bgcolot="blue">
<tr>
	<td>현재접속자</td>
</tr>
</table>

	<body background="../images/chat_attend_bg.jpg">
	<%
 		String namelist;
		String listNum = "list" + meetingId + "";
 		Vector list=(Vector) application.getAttribute(listNum);  // 대화방 참가자 명단을 불러온다
 		if(list==null){
 			namelist="현재 참가중인 사용자가 없습니다.";
 	%>
 	   	<%=namelist%><br>
   	<%
 		}
 		else{
 			for(int i=0; i < list.size(); i++){
  				namelist=(String) list.elementAt(i);
  	%>
   		<%=namelist%><br>
   	<%
   			}
 		}
	%>
	</body>
</html>

