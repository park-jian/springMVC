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
<%
	Member m = (Member)session.getAttribute("member");
%>
	<H1>메인 페이지</H1>
	<%if(m!=null){ %>
	<H3>[<a href="/memberInfo.do"><%=m.getUserName()%>] 님 접속을 환영합니다.!!</H3>
	<a href="/logout.do">로그아웃</a><br>
	<%}else{ %>
	<H3>로그인</H3>
	<form action="/login.do" method="post">
		ID : <input type="text" name="userId"/><br>
		PW : <input type="password" name="userPwd"/><br>
		<input type="submit" value="로그인"/> <a href="/memberJoin.do">회원가입</a>
	
	</form>
	<%} %>
</body>
</html>