package kr.or.iei.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberJoinController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//해당 Controller의 역할은 무엇?
		//jsp 페이지로 이동시키기 위한 역할
		return "member/memberJoin";
	}

}
