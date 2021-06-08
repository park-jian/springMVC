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
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		System.out.println("[Controller]userId : " + userId);
		System.out.println("[Controller]userPwd : " + userPwd);
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		Member member = new MemberServiceImpl().loginMember(m);
		if(member != null){
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			return "member/loginSuccess";
		}else{
			return "member/loginFail";
		}
		
	}
	
	

}
