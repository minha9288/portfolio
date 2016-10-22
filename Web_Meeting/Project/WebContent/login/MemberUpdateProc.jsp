<%-- 김서희   회원정보수정 처리하기 --%>
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
		alert("회원정보를 수정하였습니다.");
		location.href="../menu.jsp";
</script>
<% }else{%>
<script>
		alert("회원정보를 수정하지 못하였습니다.");
		location.href="MemberUpdate.jsp";
</script>
<%} %>