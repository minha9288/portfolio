<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/menubarstyle.css">
<%@ page import="java.util.*" %>
<%@ page import="java.io.*"%>
<%
   request.setCharacterEncoding("euc-kr");
   Calendar cal = Calendar.getInstance();
   int todayyear = cal.get(Calendar.YEAR);
   int todaymonth = cal.get(Calendar.MONTH)+1;
   int todaydate = cal.get(Calendar.DATE);
   int year = request.getParameter("y") == null ? cal.get(Calendar.YEAR) : Integer.parseInt(request.getParameter("y"));
   int month = request.getParameter("m") == null ? cal.get(Calendar.MONTH) : (Integer.parseInt(request.getParameter("m")) - 1);

   
   cal.set(year, month, 1);
   int bgnWeek = cal.get(Calendar.DAY_OF_WEEK);

   int prevYear = year;
   int prevMonth = (month + 1) - 1;
   int nextYear = year;
   int nextMonth = (month  + 1) + 1;

   if (prevMonth < 1) {
      prevYear--;
      prevMonth = 12;
   }

   if (nextMonth > 12) {
      nextYear++;
      nextMonth = 1;
   }
%>

<html>
   <head>
      <title> Note </title>
   </head>
   <body>
   <div align="center">
	<ul class="menu">

		<li><a href="menu.jsp">Home</a></li>
		<li><a href="meetinglist/MeetingList.jsp">Meeting</a></li>
		<li><a href="WebHard/list.jsp">Web Hard</a>
		<li><a href="#">Calendar</a></li>
		<li><a href="board/list.jsp">A Bulletin Board</a></li>
		<li><a href="login/BeforeUpdate.jsp">회원정보수정</a></li>
		<li><a href="login/Logout.jsp">로그아웃</a></li>
	</ul>
	
</div>
            <table align="center" border="0"  width="900">
               <tr height="60" width="900">
                  <td colspan="6" align="center">
                  <a href="./cal.jsp?y=<%=prevYear%>&m=<%=prevMonth%>"><font size="6"> << </font></a> 
                  <font size="6">&nbsp;&nbsp;<%=year%> . <%=month+1%>&nbsp;&nbsp;</font> 
                  <a href="./cal.jsp?y=<%=nextYear%>&m=<%=nextMonth%>"><font size="6"> >> </font></a>
                  </td>
               </tr>
            </table>
            <table align="center" border="1" bordercolor="#323232" cellspacing="0">
               <tr height="30" align="center">
                  <td width="40" align="center" bgcolor="orange"><font color="red"><b>SUN</b></font></td>
                  <td width="40" align="center" bgcolor="orange"><b>MON</b></td>
                  <td width="40" align="center" bgcolor="orange"><b>TUE</b></td>
                  <td width="40" align="center" bgcolor="orange"><b>WED</b></td>
                  <td width="40" align="center" bgcolor="orange"><b>TUR</b></td>
                  <td width="40" align="center" bgcolor="orange"><b>FRI</b></td>
                  <td width="40" align="center" bgcolor="orange"><font color="blue"><b>SAT</b></font></td>
               </tr>
<%
   String opendate;
   String str=null;
   String str2=null;

   String fontcolor;
   String bgcolor;

   out.print("<tr>");

   for (int i=1; i<bgnWeek; i++)
           out.println("<td >&nbsp;</td>");

   while (cal.get(Calendar.MONTH) == month) {
      out.println("<td width='150' height='100' valign='top'><b>" + cal.get(Calendar.DATE) + "</b><br>"); 
      opendate = Integer.toString(cal.get(Calendar.YEAR)) + Integer.toString(cal.get(Calendar.MONTH)+1) + Integer.toString(cal.get(Calendar.DATE));   

      if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
         if(cal.get(Calendar.DATE)!=31){         
            out.println("</tr><tr height='50'>");
         }
      }
      cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)+1);
   }

      if(cal.get(Calendar.DAY_OF_WEEK)!=1){
      for (int i=cal.get(Calendar.DAY_OF_WEEK); i<=7; i++) 
         out.println("<td>&nbsp;</td>");
      }
%>
         </tr>
      </table>
   </body>
</html>