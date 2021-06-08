package kr.or.iei.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberServiceImpl;
import kr.or.iei.member.model.vo.Member;

public class MemberSignup implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("memberSignup 정상적으로 호출 성공");
		
		//1.사용자가 요청한 값 가져오기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		int age = Integer.parseInt(request.getParameter("age"));
		String address = request.getParameter("address");
		
		System.out.println("[Controller] userId : " + userId); //확인코드
		System.out.println("[Controller] userPwd : " + userPwd); //확인코드
		System.out.println("[Controller] userName : " + userName); //확인코드
		System.out.println("[Controller] age : " + age); //확인코드
		System.out.println("[Controller] address : " + address); //확인코드
		
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setUserName(userName);
		m.setAge(age);
		m.setAddress(address);
		
		
		int result = new MemberServiceImpl().insertMember(m);
		
		
		
		return null;
	}

}
