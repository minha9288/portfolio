<%-- �輭��   �α׾ƿ� ó�� --%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("euc-kr"); %>
<% session.invalidate(); %>

<script>
alert("�α׾ƿ� �Ǿ����ϴ�.");
location.href="Login.jsp";
</script>