<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<style>
	fieldset{
		width:350px; height : 350px; margin : 0 auto;
	
	}
	legend{
		text-align: center;
	}



</style>

<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>

<script>
	$(function(){
		$('#idCheckBtn').click(function(){
			
			var userId=$('#userId').val();
		//	alert(userId);
			$.ajax({
				url: "/memberIdCheck.do",
				type: "get",
				data: {"userId": userId},
				success : function(result){
					if(result=="true"){
						alert("["+userId + "] 해당 ID는 현재 사용중입니다. (사용불가)");
					}else{
						var choice=window.confirm("["+userId+"]는 사용 가능한 ID입니다. 사용하시겠습니까?");
						if(choice==true){
							$('#userId').attr("readonly",true); //수정 못하게
						}
					}
				},
				error: function(){
					console.log("ajax 통신 실패");
				}
			});
		});
	});
	


</script>





<form action="/memberSignup.do" method="post">
	<fieldset>
		<legend>회원가입</legend>
		회원 ID : <input type="text" name="userId" id="userId" placeholder="ID를 입력하세요"/>
		<button type="button" id="idCheckBtn">ID중복체크</button>
		
		<br><br>
		비밀번호 : <input type="password" name="userPwd" placeholder="PW를 입력하세요"/><br><br>
		이름 : <input type="text" name="userName" placeholder="이름을 입력하세요"/><br><br>
		나이 : <input type="text" name="age" size="3"/><br><br>
		주소 : <input type="text" name="address" placeholder="주소를 입력하세요"/><br><br>
		<input type="submit" value="회원가입"/> <input type="reset" value="취소"/><br><br>
		<a href="/index.jsp">메인페이지로 이동</a>
	</fieldset>
	
</form>	
	
	
</body>
</html>