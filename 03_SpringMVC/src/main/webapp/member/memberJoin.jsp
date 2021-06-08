<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H1>회원가입</H1>
	<form action="/memberSignup.do" method="post">
	ID : <input type="text" name="userId"/><br>
	PW : <input type="password" name="userPwd"/><br>
	NAME : <input type="text" name="userName"/><br>
	AGE : <input type="text" name="age"/><br>
	ADDRESS : <input type="text" name="address"/><br>
	<input type="submit" value="회원가입"/><input type="reset" value="취소"/>
	
	
	
	</form>
	<a href="/index.jsp">메인페이지로 이동</a>
</body>
</html>