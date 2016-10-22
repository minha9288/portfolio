<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!-- 
 
  @author  한유진
 
  -->
<head>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

</head>
<head>
        <title>Creative CSS3 Animation Menus</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="css/demo1.css" />
        <link rel="stylesheet" type="text/css" href="css/style6.css" />
        <link href='http://fonts.googleapis.com/css?family=Terminal+Dosis' rel='stylesheet' type='text/css' />
    </head>
    
    <body>
    <p align="right">
		<a href="login/BeforeUpdate.jsp">회원정보수정&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
		<a href="login/Logout.jsp">로그아웃&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
	</p>
     <div class="header" >
     <a href="kimLogout.jsp" value="LOGOUT"></a>
    </div>
        <div class="container" align="center" style="margin:160px;margin-bottom: 40px;margin-left: 260px" >
           <div class="content" align="center">
                <ul class="ca-menu" >
                    
                    <li>
                        <a href="meetinglist/MeetingList.jsp">
                            <span class="ca-icon">H</span>
                            <div class="ca-content">
                                <h2 class="ca-main">Metting</h2>
                                <h3 class="ca-sub">팀원들과 회의를 하는 장소</h3>
                            </div>
                        </a>
                    </li>
                  
                    <li>
                        <a href="WebHard/list.jsp">
                            <span class="ca-icon">F</span>
                            <div class="ca-content">
                                <h2 class="ca-main">Web Hard</h2>
                                <h3 class="ca-sub">파일 보관함</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="cal.jsp">
                            <span class="ca-icon">L</span>
                            <div class="ca-content">
                                <h2 class="ca-main">Calendar</h2>
                                <h3 class="ca-sub">회사일정, 날짜를 관리하는 달력</h3>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="board/list.jsp">
                            <span class="ca-icon">N</span>
                            <div class="ca-content">
                                <h2 class="ca-main">A Bulletin Board</h2>
                                <h3 class="ca-sub">사람들과 정보를 공유하는 장소</h3>
                            </div>
                        </a>
                    </li>
                </ul>
            </div><!-- content -->
        </div>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
    </body>
</html>