<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<img src="/resources/img/error-2129569_1920.jpg"/>
<%
	Member m = (Member)session.getAttribute("member");

%>
<%if(m!=null){ %>
	<h2>[<a href="/memberInfo.do"><%=m.getUserName() %></a>]</h2>님 환영합니다.
	<a href="/memberLogout.do">로그아웃</a><br>
	<a href="/memberWithDraw.do">회원탈퇴</a><br>
	<a href="/memberAllList.do">전체회원보기</a>
	<a href="/fileUploadPage.do">파일업로드 페이지</a>
<%}else{ %>

	<H1>Spring MVC 기본페이지</H1>
	<H2>로그인</H2>
	<form action="/memberLogin.do" method="post">
		ID : <input type="text" name="userId"/><br>
		PW : <input type="text" name="userPwd"/><br>
		<input type="submit" value="로그인"/><input type="reset" value="취소"/>
		<a href="/memberJoinPage.do">회원가입</a>
	</form>
<%} %>
	
	
</body>
</html>