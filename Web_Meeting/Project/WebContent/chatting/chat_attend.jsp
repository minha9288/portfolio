<%@page contentType="text/html;charset=EUC-KR"%>
<%@page import="java.util.*"%>
<% 
	request.setCharacterEncoding("euc-kr");  // �ѱ� ����

	int meetingId= ((Integer) session.getAttribute("meetingId")).intValue();  //������ ȸ�Ǹ� ����
	//int meetingId = 2; //�׽�Ʈ

	//������ ȸ�Ǹ����� ��ȣ�� �̸��� �ξ� ������ ������ ���� ����
	String chatNum = "chat" + meetingId + "" ;
	Vector chat  = (Vector)application.getAttribute(chatNum);  // chat vector ����
	if(chat == null){
 		chat = new Vector(10);  // vector 10�� ���� ����
 	}
%>
<meta http-equiv='refresh' content='1'>  
<html>
<table bgcolot="blue">
<tr>
	<td>����������</td>
</tr>
</table>

	<body background="../images/chat_attend_bg.jpg">
	<%
 		String namelist;
		String listNum = "list" + meetingId + "";
 		Vector list=(Vector) application.getAttribute(listNum);  // ��ȭ�� ������ ����� �ҷ��´�
 		if(list==null){
 			namelist="���� �������� ����ڰ� �����ϴ�.";
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

