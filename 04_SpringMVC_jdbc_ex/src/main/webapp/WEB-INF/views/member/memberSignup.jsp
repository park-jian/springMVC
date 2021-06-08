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
		boolean result = (boolean)request.getAttribute("result");
	
	%>
	
	<script>
		<%if(result==true){%>
			alert("결과 : " + <%=result%> + "/회원가입 성공");
		
		<%}else{%>
			alert("결과 : " + <%=result%> + "/회원가입 실패");
		
		<%}%>
		location.replace("/index.jsp");
	
	
	</script>
</body>
</html>