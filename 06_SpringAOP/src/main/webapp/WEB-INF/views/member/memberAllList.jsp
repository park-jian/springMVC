<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>

</head>
<body>
<%
	ArrayList<Member> list=(ArrayList<Member>)request.getAttribute("list");
	Member sessionMember = (Member)session.getAttribute("member");
%>
	<H1>전체회원리스트</H1>
	<table border="1px">
		<tr>
			<th>회원번호</th>
			<th>회원ID</th>
			<th>회원PW</th>
			<th>회원이름</th>
			<th>회원나이</th>
			<th>회원주소</th>
			<th>가입일</th>
			<th>탈퇴여부</th>
		</tr>
		
		<%for(Member m : list){ %>
			<%if(m.getEndYN()=='Y'){ %>
				<tr style="background-color : pink;">
			<%}else{ %>
				<tr style="background-color : skyblue;">
			<%} %>
				<td><%=m.getUserNo() %></td>
				<td><%=m.getUserId() %></td>
				<td><%=m.getUserPwd() %></td>
				<td><%=m.getUserName() %></td>
				<td><%=m.getAge() %></td>
				<td><%=m.getAddress() %></td>
				<td><%=m.getEnrollDate() %></td>
				
				<%if(1<=sessionMember.getUserNo()&& sessionMember.getUserNo() <=100){ %>
				<td><button class="endChangeBtn" id="<%=m.getUserNo()%>" style="width:100%;"><%=m.getEndYN() %></button></td>			
				<%}else{ %>
				<td><%=m.getEndYN() %></td>
				<%} %>
			</tr>
		<%} %>
	</table>
	<a href="/index.jsp">메인페이지로 이동</a>
	
	<script>
		$(function(){
			$('.endChangeBtn').click(function(){
				var userNo=$(this).attr('id');
				var endYN=$(this).text();
				var $btnObject = $(this); //<-- 바깥에 this를 미리 빼놓지 않고 바로 아래에다가 쓰면 this는 버튼을 뜻하는게 아니라 ajax를 가리키게 됨..그래서 밖에 미리 뺴줌.
				//alert(userNo+"/"+endYN);
				
				$.ajax({
					url : "/adminEndYNChange.do",
					type : "post",
					data : {"userNo":userNo, "endYN":endYN},
					success : function(result){
						if(result=="true")
						{
							if(endYN=='Y'){ //원래 상태가 Y였다면
								//$(this).text('N'); //<--여기 this는 ajax를 나타냄. ajax안에서 사용하면 this가 button을 뜻하는게 아니라 ajax를 갸리킴
								$btnObject.text('N');
								$btnObject.parents('tr').css('background-color','yellow');
								$btnObject.parents('tr').css('color','black');
							}else{			//원래 상태가 N였다면
								//$(this).text('Y');
								$btnObject.text('Y');
								$btnObject.parents('tr').css('background-color','skyblue');
								$btnObject.parents('tr').css('color','white');
							}
						}else{
							console.log("사용자 상태 변경 실패");	
						}
					},
					error : function(){
						console.log("ajax 통신 실패");
					}
				});
			});
		});
	
	</script>

</body>
</html>