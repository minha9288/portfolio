<%@ page contentType="text/html; charset=EUC-KR"%>
<%--
 <%! 
	public String getParam(HttpServletRequest request, String paramName){
		if(request.getParameter(paramName)!=null){
			return request.getParameter(paramName);
		}else{
			return "";
		}
	}
<%
	request.setCharacterEncoding("euc-kr");
	int filecounter = 0;
	if(request.getParameter("addcnt")!=null){
		filecounter = Integer.parseInt(request.getParameter("addcnt"));
	}
%>
--%>

<html>
<head>
<title>���� ���ε�</title>
<!-- 
<script language="JavaScript">
function inputValue(form1, param, form2, idx){
	var paramValue = form1.elements[idx].value;
	form2.elements[idx].value = paramValue;			 
	return;
}
function addFile(formName){
	if(formName.addcnt.value==""){
		alert(" �Է��� ���� ������ �Է��ϰ� Ȯ�ι�ư�� �����ּ���" ); 
		formName.addcnt.focus();						 
		return;
	}
	formName.submit();
}

function elementCheck(formName){
   paramIndex = 1; 
   for(idx=0; idx<formName.elements.length; idx++){
      if(formName.elements[idx].type == "file"){
         if(formName.elements[idx].value==""){
	        var message = paramIndex+" ��° ���������� �����Ǿ����ϴ�.\n���ε��� ������ ������ �ּ���";
		    alert(message);
			formName.elements[idx].focus();
			return;		
	     }
	     paramIndex++; 
      }
   }
   formName.action = "list.jsp";
   formName.submit();
}
</script>
-->

<%@page import="com.greenlight.member.MemberBean" %>
<jsp:useBean id="userMgr" class="com.greenlight.db.UserMgr"/>
<%
	String userId = (String) session.getAttribute("userIdKey");
	MemberBean mbean = userMgr.getMember(userId);
%>
</head>
<body>
	<div align="center">
		<br />
		<br />
		<table width="80%" cellspacing="0" cellpadding="3">
			<tr>
				<td bgcolor="D5D5D5" height="25" align="center">������ ���ε��ϼ���.</td>
			</tr>
		</table>
		<br />
		<form name="frmName1" method="post" action="postProc.jsp"
			enctype="multipart/form-data">
			<table width="80%" cellspacing="0" cellpadding="3" align="center">
				<tr>
					<td align=center>
						<table border="0" width="100%" align="center">
							<tr>
								<td width="20%">�� ��</td>
								<td width="80%"><input type="text" name="name" size="50"
									value="<%=mbean.getUserName()%>" readonly></td>
							</tr>
							<tr>
								<td width="20%">�� ��</td>
								<td width="80%"><input type="text" name="subject" size="50"
									maxlength="30"></td>
							</tr>
							<tr>
								<td width="20%">�� ��</td>
								<td width="80%"><textarea name="content" rows="3" cols="48"></textarea></td>
							</tr>

							<tr>
								<td width="10%">��� ��ȣ</td>
								<td width="90%"><input type="password" name="pass"
									size="15" maxlength="15"> ��й�ȣ�� ������ �ּ���.</td>
							</tr>
							<tr>
							<tr>
								<td width="10%">����÷��</td>
								<td width="90%"><input type="file" name="filename"
									size="35" maxlength="50"></td>
								<!-- 
    		    <td width="10%" colspan="4">���� �� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="addcnt">
				<input type="button" value="Ȯ�� " onclick="addFile(this.form)">
				</td>
				-->
							</tr>
							<tr>
								<td><input type="hidden" name="contentType" value="HTTP">
								</td>
							</tr>
							<tr>
								<td colspan="2"><hr /></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="submit"
									value="���ε�"> <input type="reset" value="�ٽþ���">
									<input type="button" value="����Ʈ"
									onClick="javascript:location.href='list.jsp'"></td>
							</tr>

						</table>
					</td>
				</tr>
			</table>
			<input type="hidden" name="ip" value="<%=request.getRemoteAddr()%>">
		</form>
	</div>
</body>
</html>