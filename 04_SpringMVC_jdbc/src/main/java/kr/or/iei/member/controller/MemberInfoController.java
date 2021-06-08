package kr.or.iei.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberServiceImpl;
import kr.or.iei.member.model.vo.Member;

public class MemberInfoController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		//이 Controller는 회원의 정보를 갱신해서 회원정보 보여주는 페이지로 이동 시키기 위한 목적
		
		//요청자의 정보를 session에서 꺼내와야 함
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("member");
		
		
		//비즈니스 로직을 통해서 현재 DB에서 갱신된 데이터가 넘어오는 로직
		Member member = new MemberServiceImpl().renewalMember(m.getUserId());
		
		//기존 정보 삭제
		session.removeAttribute("member");
		
		//갱신된 정보로 session을 업데이트
		session.setAttribute("member", member);
		
		return "member/memberInfo";
	}

}
