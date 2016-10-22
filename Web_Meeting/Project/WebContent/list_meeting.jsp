 <%@ page contentType="text/html; charset=EUC-KR"%>
<html>
<head>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../css/menubarstyle.css">
<%@page import="com.greenlight.board.BoardBean"%>
<%@page import="java.util.Vector"%>
<jsp:useBean id="bMgr" class="com.greenlight.db.WebhardMgr2" />
<%	
	  request.setCharacterEncoding("EUC-KR");
	  
      int totalRecord=0; //전체레코드수
	  int numPerPage=10; // 페이지당 레코드 수 
	  int pagePerBlock=15;  //블럭당 페이지수 
	  
	  int totalPage=0; //전체 페이지 수
	  int totalBlock=0;  //전체 블럭수 

	  int nowPage=1; // 현재페이지
	  int nowBlock=1;  //현재블럭
	  
	  int start=0; //디비의 select 시작번호
	  int end=10; //시작번호로 부터 가져올 select 갯수
	  
	  int listSize=0; //현재 읽어온 게시물의 수

	String keyWord = "", keyField = "";
	Vector<BoardBean> vlist = null;
	if (request.getParameter("keyWord") != null) {
		keyWord = request.getParameter("keyWord");
		keyField = request.getParameter("keyField");
	}
	if (request.getParameter("reload") != null){
		if(request.getParameter("reload").equals("true")) {
			keyWord = "";
			keyField = "";
		}
	}
	
	if (request.getParameter("nowPage") != null) {
		nowPage = Integer.parseInt(request.getParameter("nowPage"));
	}
	 start=(nowPage * numPerPage)-numPerPage;
	 end= start+numPerPage;
	 
	totalRecord = bMgr.getTotalCount(keyField, keyWord);
	totalPage =(int)Math.ceil((double)totalRecord / numPerPage);  //전체페이지수
	nowBlock= (int)Math.ceil((double)nowPage/pagePerBlock); //현재블럭 계산
	  
	totalBlock =(int)Math.ceil((double)totalPage / pagePerBlock);  //전체블럭계산
%>
<title>파일 목록</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function list() {
		document.listFrm.action = "list_meeting.jsp";
		document.listFrm.submit();
	}
	
	function pageing(page) {
		document.readFrm.nowPage.value = page;
		document.readFrm.submit();
	}
	
	function block(value){
		 document.readFrm.nowPage.value=<%=pagePerBlock%>*(value-1)+1;
		 document.readFrm.submit();
	} 
	
	function read(num){
		document.readFrm.num.value=num;
		document.readFrm.action="read.jsp";
		document.readFrm.submit();
	}
	
	function check() {
	     if (document.searchFrm.keyWord.value == "") {
	   alert("검색어를 입력하세요.");
	   document.searchFrm.keyWord.focus();
	   return;
	     }
	  document.searchFrm.submit();
	 }
</script>
</head>
<body background="../images/chat_attend_bg.jpg">

<div align="center">
	<table align="center" width="100%" border="0" cellspacing="0" cellpadding="3">
		<tr>
			<td align="center" colspan="2">
			<%
				  vlist = bMgr.getBoardList(keyField, keyWord, start, end);
				  listSize = vlist.size();//브라우저 화면에 보여질 게시물갯수
				  if (vlist.isEmpty()) {
					out.println("등록된 파일이 없습니다.");
					
				  } else {
			%>
				  <table border="0" width="100%" cellpadding="2" cellspacing="0" bgcolor="#FFFFE8">
					<tr align="center" bgcolor="#FFB2D9" height="120%">
						<td>번 호</td>
						<td>제 목</td>					
						<td>날 짜</td>				
					</tr>
					<%
						  for (int i = 0;i<numPerPage; i++) {
							if (i == listSize) break;
							
							BoardBean bean = vlist.get(i);
							int num = bean.getNum();
							String name = bean.getName();
							String subject = bean.getSubject();
							String filename = bean.getFilename();
							String regdate = bean.getRegdate();
							int depth = bean.getDepth();
							int count = bean.getCount();
					%>
					<tr>
						<td align="center" bgcolor="#FFFFE8">
							<%=totalRecord-((nowPage-1)*numPerPage)-i%>
						</td>
						<td bgcolor="#FFFFE8">
						<%
							  if(depth>0){
								for(int j=0;j<depth;j++){
									out.println("&nbsp;&nbsp;");
									}
								}
						%>
						  <a href="javascript:read('<%=num%>')"><%=subject%></a>
						</td>
						<td align="center" bgcolor="#FFFFE8"><%=regdate%></td>
						</tr>
					<%}//for%>
				</table> <%
 			}//if
 		%>
			</td>
		</tr>

		<tr>
			<td bgcolor="#FFFFE8">
			<!-- 페이징 및 블럭 처리 Start--> 
			<%
   				  int pageStart = (nowBlock -1)*pagePerBlock + 1 ; //하단 페이지 시작번호
   				  int pageEnd = ((pageStart + pagePerBlock ) < totalPage) ?  (pageStart + pagePerBlock): totalPage+1; 
   				  //하단 페이지 끝번호
   				  if(totalPage !=0){
    			  	if (nowBlock > 1) {%>
    			  		<a href="javascript:block('<%=nowBlock-1%>')">prev...</a><%}%>&nbsp; 
    			  		<%for ( ; pageStart < pageEnd; pageStart++){%>
     			     	<a href="javascript:pageing('<%=pageStart %>')"> 
     					<%if(pageStart==nowPage) {%><font color="blue"> <%}%>
     					[<%=pageStart %>] 
     					<%if(pageStart==nowPage) {%></font> <%}%></a> 
    					<%}//for%>&nbsp; 
    					<%if (totalBlock > nowBlock ) {%>
    					<a href="javascript:block('<%=nowBlock+1%>')">.....next</a>
    				<%}%>&nbsp;  
   				<%}%>
 				<!-- 페이징 및 블럭 처리 End-->
				</td>
				<td align="right" bgcolor="#FFFFE8" >
					<a href="post.jsp">[업로드하기]</a> 
					<a href="javascript:list()">[목록보기]</a>
				</td>
			</tr>
		</table>

	<form name="listFrm" method="post">
		<input type="hidden" name="reload" value="true"> 
		<input type="hidden" name="nowPage" value="1">
	</form>
	<form name="readFrm" method="post">
		<input type="hidden" name="num"> 
		<input type="hidden" name="nowPage" value="<%=nowPage%>"> 
		<input type="hidden" name="keyField" value="<%=keyField%>"> 
		<input type="hidden" name="keyWord" value="<%=keyWord%>">
	</form>
</div>
</body>
</html>