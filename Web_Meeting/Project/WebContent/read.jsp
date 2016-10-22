 <%@ page contentType="text/html; charset=EUC-KR"%>
<html>
<head>
<%@page import="com.greenlight.board.BoardBean"%>
<jsp:useBean id="bMgr" class="com.greenlight.db.WebhardMgr2" />
<%
	  request.setCharacterEncoding("EUC-KR");
	  int num = Integer.parseInt(request.getParameter("num"));
	  String nowPage = request.getParameter("nowPage");
	  String keyField = request.getParameter("keyField");
	  String keyWord = request.getParameter("keyWord");
	  bMgr.upCount(num);//조회수 증가
	  BoardBean bean = bMgr.getBoard(num);//게시물 가져오기
	  String name = bean.getName();
	  String subject = bean.getSubject();
      String regdate = bean.getRegdate();
	  String content = bean.getContent();
	  String filename = bean.getFilename();
	  int filesize = bean.getFilesize();
	  String ip = bean.getIp();
	  int count = bean.getCount();
	  
	  session.setAttribute("bean", bean);//게시물을 세션에 저장
%>
<title>파일 읽기</title>
<script type="text/javascript">
	function list(){
	 	document.listFrm.action="list_meeting.jsp";
	    document.listFrm.submit();
	 } 
	
	function down(filename){
		 document.downFrm.filename.value=filename;
		 document.downFrm.submit();
	}
</script>
</head>
<body >
<div align="center">
   <table border="0" cellpadding="0" cellspacing="0" width=100%> 
    <tr> 
    <td align="center" bgcolor="#DDDDDD"> 제 목</td>
    <td bgcolor="#FFFFE8"><%=subject%></td>
    </tr>
    <tr>
 	<td align="center" bgcolor="#DDDDDD"> 업로드 날짜 </td>
  	<td bgcolor="#FFFFE8"><%=regdate%></td>
 </tr>
   <tr> 
     <td align="center" bgcolor="#DDDDDD">첨부파일</td>
     <td bgcolor="#FFFFE8" colspan="5">
     <% if( filename !=null && !filename.equals("")) {%>
  		<a href="javascript:down('<%=filename%>')"><%=filename%></a>
  		 &nbsp;&nbsp;<font color="blue">(<%=filesize%>KBytes)</font>  
  		 <%} else{%> 등록된 파일이 없습니다.<%}%>
     </td>
   </tr>
   </table>
  </td>
 </tr>
 <tr>
  <td align="center" colspan="2"> 
 <hr/>
 [ <a href="javascript:list()" >목록</a> | 
 <!-- <a href="update.jsp?nowPage=<%=nowPage%>&num=<%=num%>" >수 정</a> |
 <a href="reply.jsp?nowPage=<%=nowPage%>" >답 변</a> | -->
 <a href="delete.jsp?nowPage=<%=nowPage%>&num=<%=num%>">삭 제</a> ]<br>
  </td>
 </tr>
</table>

<form name="downFrm" action="download.jsp" method="post">
	<input type="hidden" name="filename">
</form>

<form name="listFrm" method="post">
	<input type="hidden" name="num" value="<%=num%>">
	<input type="hidden" name="nowPage" value="<%=nowPage%>">
	<%if(!(keyWord==null || keyWord.equals("null"))){ %>
	<input type="hidden" name="keyField" value="<%=keyField%>">
	<input type="hidden" name="keyWord" value="<%=keyWord%>">
	<%}%>
</form>
</body>
</html>