package kr.or.iei.member.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberServiceImpl;
import kr.or.iei.member.model.vo.Member;

public class MemberUpdateController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//보내준 데이터를 + session의 정보를 합쳐서 비즈니스 로직 처리
		String userPwd = request.getParameter("userPwd");
		int age = Integer.parseInt(request.getParameter("age"));
		String address = request.getParameter("address");
		
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("member");
		
		m.setUserPwd(userPwd);
		m.setAge(age);
		m.setAddress(address);
		
		int result = new MemberServiceImpl().memberUpdate(m);
		
		if(result>0){
			request.setAttribute("result", true);
		}else{
			request.setAttribute("result", false);
		}
		
		
		return "member/memberUpdate";
	}

}
