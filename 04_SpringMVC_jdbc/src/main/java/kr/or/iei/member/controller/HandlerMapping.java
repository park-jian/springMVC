package kr.or.iei.member.controller;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
	private Map<String,Controller> mappings = new HashMap<String,Controller>();
	public HandlerMapping(){
		mappings.put("/login.do", new LoginController());
		mappings.put("/logout.do", new LogoutController());
		mappings.put("/memberJoin.do", new MemberJoinController());
		mappings.put("/memberSignup.do", new MemberSignupController());
		mappings.put("/memberInfo.do", new MemberInfoController());
		mappings.put("/memberUpdate.do", new MemberUpdateController());
	}
	public Controller getController(String path){
		return mappings.get(path);
	}
}
