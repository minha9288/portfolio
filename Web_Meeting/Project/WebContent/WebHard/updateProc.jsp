<%@ page contentType="text/html; charset=EUC-KR"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="bMgr" class="com.greenlight.db.WebhardMgr" />
<jsp:useBean id="bean" class="com.greenlight.board.BoardBean" scope="session"/>
<jsp:useBean id="upBean" class="com.greenlight.board.BoardBean"/>
<jsp:setProperty property="*" name="upBean"/>
<%
	  String nowPage = request.getParameter("nowPage");
	  //bean�� �ִ� pass�� upBean pass�� ��(read.jsp)
	  String upPass = upBean.getPass();
	  String inPass = bean.getPass();
	  if(upPass.equals(inPass)){
	    bMgr.updateBoard(upBean);
		String url = "read.jsp?nowPage="+nowPage+"&num="+upBean.getNum();
		response.sendRedirect(url);
	  }else{
%>
	<script type="text/javascript">
		alert("�Է��Ͻ� ��й�ȣ�� �ƴմϴ�.");
		history.back();
	</script>
<%}%>