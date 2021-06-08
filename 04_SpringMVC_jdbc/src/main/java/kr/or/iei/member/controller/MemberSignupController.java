package kr.or.iei.member.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberServiceImpl;
import kr.or.iei.member.model.vo.Member;

public class MemberSignupController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("[MemberSignupController] 회원가입 Controller 정상호출");
		
		//1. 인코딩 - 스프링을 본격적으로 사용했을때는 인코딩이 필요없음. 앞단에 필터가 있어서.
		try {         //<-- 원래 지금 메소드에 throws 해서 예외처리 해줘야 하는데 인터페이스에서 안만들어줬기에 여기서 try catch해준것.
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//2.데이터가져오기
		Member m = new Member();
		m.setUserId(request.getParameter("userId"));
		m.setUserPwd(request.getParameter("userPwd"));
		m.setUserName(request.getParameter("userName"));
		m.setAge(Integer.parseInt(request.getParameter("age")));
		m.setAddress(request.getParameter("address"));
		
		
		//3.비즈니스 로직처리
		int result= new MemberServiceImpl().memberInsert(m);
		
		//기존에는 request.getRequestDispatcher했던거는 DispatcherServlet에 작성되어 있어서 여기서는 작성안함.
		if(result>0){
			request.setAttribute("result", true);
		}else{
			request.setAttribute("result", false);
		}
		return "member/memberSignup";     //성공하든 실패하든 한 페이지로 가거라..각각 다른페이지로 가도록 할 수도 있음.
	}

}
