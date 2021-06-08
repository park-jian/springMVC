<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <H1>회원정보</H1>
 <form action="/memberUpdate.do" method="post">
 회원 ID : ${sessionScope.member.userId} <br>
 회원 PW : <input type="password" name="userPwd" value="${sessionScope.member.userPwd}"/><br>
 회원 이름 : ${sessionScope.member.userName} <br>
 회원 나이 : <input type="text" name="age" value="${sessionScope.member.age }"/><br>
 회원 주소 : <input type="text" name="address" value="${sessionScope.member.address}"/><br>
 <input type="submit" value="회원정보수정"/><input type="reset" value="취소"/>
 </form>
 <a href="/index.jsp">메인페이지로 이동</a>
</body>
</html>