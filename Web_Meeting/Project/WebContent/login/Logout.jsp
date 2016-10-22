<%-- ±è¼­Èñ   ·Î±×¾Æ¿ô Ã³¸® --%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<% session.invalidate(); %>

<script>
alert("·Î±×¾Æ¿ô µÇ¾ú½À´Ï´Ù.");
location.href="Login.jsp";
</script>