<%-- �輭��   ȸ���������� ó���ϱ� --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
<jsp:useBean id="mBean" class="com.greenlight.member.MemberBean"/>
<jsp:setProperty  name="mBean" property="*"/>
<%
	  boolean result = userMgr.updateMember(mBean);
	  if(result){
%>
<script>
		alert("ȸ�������� �����Ͽ����ϴ�.");
		location.href="../menu.jsp";
</script>
<% }else{%>
<script>
		alert("ȸ�������� �������� ���Ͽ����ϴ�.");
		location.href="MemberUpdate.jsp";
</script>
<%} %>