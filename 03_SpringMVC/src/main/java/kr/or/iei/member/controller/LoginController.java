package kr.or.iei.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberServiceImpl;
import kr.or.iei.member.model.vo.Member;

public class LoginController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Login Controller 정상적으로 호출 성공");
		
		//1.사용자가 요청한 값 가져오기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		System.out.println("[Controller] userId : " + userId); //확인코드
		System.out.println("[Controller] userPwd : " + userPwd); //확인코드
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		
		Member member = new MemberServiceImpl().loginMember(m);
		
		if(member != null){
			//로그인이 정상작동 되었다면!!
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			return "loginSuccess";
		}else{
			//로그인이 정상작동 하지 못하였다면!!
			return "loginFail";
		}
		
		
		
		
		
		
	}

}
