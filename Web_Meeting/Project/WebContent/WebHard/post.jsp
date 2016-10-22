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
<title>파일 업로드</title>
<!-- 
<script language="JavaScript">
function inputValue(form1, param, form2, idx){
	var paramValue = form1.elements[idx].value;
	form2.elements[idx].value = paramValue;			 
	return;
}
function addFile(formName){
	if(formName.addcnt.value==""){
		alert(" 입력할 파일 갯수를 입력하고 확인버튼을 눌러주세요" ); 
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
	        var message = paramIndex+" 번째 파일정보가 누락되었습니다.\n업로드할 파일을 선택해 주세요";
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
				<td bgcolor="D5D5D5" height="25" align="center">파일을 업로드하세요.</td>
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
								<td width="20%">성 명</td>
								<td width="80%"><input type="text" name="name" size="50"
									value="<%=mbean.getUserName()%>" readonly></td>
							</tr>
							<tr>
								<td width="20%">제 목</td>
								<td width="80%"><input type="text" name="subject" size="50"
									maxlength="30"></td>
							</tr>
							<tr>
								<td width="20%">내 용</td>
								<td width="80%"><textarea name="content" rows="3" cols="48"></textarea></td>
							</tr>

							<tr>
								<td width="10%">비밀 번호</td>
								<td width="90%"><input type="password" name="pass"
									size="15" maxlength="15"> 비밀번호를 설정해 주세요.</td>
							</tr>
							<tr>
							<tr>
								<td width="10%">파일첨부</td>
								<td width="90%"><input type="file" name="filename"
									size="35" maxlength="50"></td>
								<!-- 
    		    <td width="10%" colspan="4">파일 수 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="addcnt">
				<input type="button" value="확인 " onclick="addFile(this.form)">
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
									value="업로드"> <input type="reset" value="다시쓰기">
									<input type="button" value="리스트"
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