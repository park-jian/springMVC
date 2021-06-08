package kr.or.iei.member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import kr.or.iei.common.Sha256Util;
import kr.or.iei.member.model.service.MemberServiceImpl;
import kr.or.iei.member.model.vo.Member;



@Controller     //public class MemberController을 메모리에 올리는 어노테이션
public class MemberController {
	
	@Autowired
	@Qualifier(value="memberService")
	private MemberServiceImpl mService;
	

	//@RequestMapping어노테이션을 사용하면 HandlerMapping에 Put을 통해서 Controller를 등록했던 업무를 대신 처리 해줌
	@RequestMapping(value="/memberLogin.do")     //  <-HandlerMapping에 등록한 역할
	public String memberLogin(@RequestParam String userId, @RequestParam String userPwd, HttpServletRequest request)//매개변수이름은 view에서 넘겨주는 이름과 일치해야함
	{//HttpServletRequest request적으면 자동으로 request가 넘어옴.
		
		//Controller 매개변수는 Spring에서는 자유롭게 사용할 수 있도록 Spring에서 제공해주는 매개변수 형태가 있음
		//request객체가 필요하면 HttpServletRequest request로 작성해서 사용할 수 있고,
		//만약 request객체 자체는 필요 없지만 넘어오는 데이터만 필요할때에는 특정 어노테이션을 사용할 수 있음(@RequestParam)
		
		
		System.out.println("[/memberLogin.do] 정상적으로 호출");
		//String userId=request.getParameter("userId");
		//String userPwd = request.getParameter("userPwd");
		System.out.println("userId : " + userId + "/ userPwd : " + userPwd );
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		//비즈니스 로직처리
		Member member = mService.selectLoginMember(m);
		
		if(member !=null){ //여기까지 잘되는지 확인하는 코드
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			System.out.println("로그인 성공[" + member.getUserName()+"]");
			return "redirect:/index.jsp"; //return "index"; 이렇게 쓰면 viewResolver가 관여함. 근데 지금 관여하면 viewResolver에 미리 등록된 prefix, suffix에 의해
			///WEB-INF/views/index.jsp 경로가 되버려서 잘못된 경로로 가서 경로를 찾지 못함. 그래서 redirect: 붙여주면 viewResolver가 관여하지 못함.
		}else{
			System.out.println("로그인 실패");
			return "member/loginFail";
		}
		
	}
	
	
	@RequestMapping(value="/memberLogout.do")
	public String memberLogout(HttpSession session){
		
		//spring의 controller에서는request가 가지고 있는 session을 바로 가져올 수 있음
		session.invalidate();
		
		
		return "redirect:/index.jsp";
	}
	
	@RequestMapping(value="/memberJoinPage.do")  // <- 핸들러 맵핑에게 물어보기
	public String memberJoinPage(){
		return "member/memberJoinPage";
	}
	
	@RequestMapping(value="/memberSignup.do")      // 회원가입
	public String memberSignup(Member m, Model model) //Model객체 용도는 Controller에서 view페이지로 데이터를 전달하기 위해 사용하는 객체 
	{
	
	/* public String memberSignup(HttpServletRequest request){
		request객체를 사용하는 방식
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		int age = Integer.parseInt(request.getParameter("age"));
		String address = request.getParameter("address");
		*/
		System.out.println("[사용자 입력값] :" + m.getUserId());
		System.out.println("[사용자 입력값] :" + m.getUserPwd());
		System.out.println("[사용자 입력값] :" + m.getUserName());
		System.out.println("[사용자 입력값] :" + m.getAge());
		System.out.println("[사용자 입력값] :" + m.getAddress());
		
		//비즈니스 로직
		int result = mService.insertSignupMember(m);
		
		//결과처리
		if(result>0){
			model.addAttribute("msg","회원가입 성공했습니다.!!(new Version");
		}else{
			model.addAttribute("msg","회원가입 실패했습니다.!!(new Version");
		}
		
		model.addAttribute("location","/index.jsp");
		return "member/result";
	}
	
	@RequestMapping(value="/memberIdCheck.do")
	public void memberIdCheck(@RequestParam String userId, HttpServletResponse response) throws IOException{
		System.out.println("[memberIdCheck.do] :" + userId);
		ArrayList<Member> list = mService.selectIdCheck(userId);
		
		//list안에 데이터가 있다는 것은 이미 사용하는 유저가 있다라는 의미 -> 사용 불가
		//list안에 데이터가 없다는 것은 사용하는 유저가 없다는 의미 -> 사용 가능
		
		if(list.isEmpty()){
			//사용가능
			response.getWriter().print(false); //사용하는 유저가 없다 라는 의미로 false를 보내는 것
		}else{
			//사용불가
			response.getWriter().print(true); //사용하는 유저가 있다 라는 의미로 true를 보내는 것
		}
		//ajax통신은 비동기 방식이기 떄문에 페이지를 이동할 필요가 없다. 그래서 return하고 끝내면 됨. 페이지를 돌려줄 필요가 없음.
		return ;
	}
	@RequestMapping(value="/memberInfo.do")
	public String memberInfo(@SessionAttribute("member") Member m, HttpSession session){    //회원정보 가져오는 로직
		
		System.out.println("[memberInfo.do] : " + m.getUserId());
		
		//갱신 정보를 가져오기 위한 비즈니스 로직
		Member member = mService.selectRenewalMember(m.getUserId());
		
		//가져온 갱신 정보를 session에 업데이트
		session.removeAttribute("member");
		session.setAttribute("member", member);
		return "member/memberInfo";
	}
	@RequestMapping(value="/memberUpdate.do")  //바뀐 회원 정보를 업데이트 시키는 로직
	public ModelAndView memberUpdate(@SessionAttribute("member") Member sessionMember, //sessionMember는 세션에 가지고있는 기존의 member정보를 가지고 있음
								Member m,//m의 정보는 사용자가 변경을 원하는 정보(변경을 요청하는 정보)
								HttpSession session) //session갱신 위해 가져오기
	{
		if(m.getUserPwd().length()>0){ //입력한 길이가 0보다 크다면(입력한 값이 있다면) 패스워드를 셋팅 해주어라.
			sessionMember.setUserPwd(m.getUserPwd());
		}
		
		sessionMember.setAge(m.getAge());
		sessionMember.setAddress(m.getAddress());
		
		//정보를 가지고 비즈니스 로직 동작
		int result = mService.updateMember(sessionMember);
		
		
		//Controller에서 view로 넘겨주는 데이터로 사용되는 객체로 Model객체가 있음
		//추가적으로 ModelAndView라는 객체도 있음
		//ModelAndView라는 객체는 넘겨주는 데이터 + 보내줄 페이지 정보를 같이 넘겨줄 수 있는 객체
		//addObject메소드를 통해 보낼 데이터를 추가할 수 있고,
		//setViewName메소드를 통해서 처리할 페이지를 보내줄 수 있음.
		
		ModelAndView mav = new ModelAndView();
		
		//결과처리
		if(result>0){
			//session갱신
			//기존 session에 저장된 member객체 정보 삭제
			session.removeAttribute("member");
			session.setAttribute("member", sessionMember);
			
			mav.addObject("msg","회원정보 변경 성공!(new Version)");
		}else{
			mav.addObject("msg","회원정보 변경 실패!(new Version)");
		}
		mav.addObject("location","/memberInfo.do");
		mav.setViewName("member/result");
		return mav;
	}
	@RequestMapping(value="/memberWithDraw.do")  //회원탈퇴
	public String memberWithDraw(@SessionAttribute("member") Member m, HttpSession session, Model model){ //세션에서 탈퇴할 사람 정보 꺼내오기
		int result= mService.updateWithDrawMember(m.getUserNo()); //유니크한 값 넘겨주기
		
		if(result>0){
			//탈퇴 처리 되었을떄
			session.invalidate(); //세션파기
			model.addAttribute("msg","회원 탈퇴 성공(new Version)");
			model.addAttribute("location","/index.jsp");
		}else{
			model.addAttribute("msg","회원 탈퇴 실패(new Version)");
			model.addAttribute("location","/index.jsp");
		}
		return "member/result";
	}
	@RequestMapping(value="/memberAllList.do")
	public ModelAndView memberAllList(){        // 전체 리스트 보기 - 일반회원도 볼 수 있게 만들것.
		
		//비즈니스 로직 처리
		ArrayList<Member> list=mService.selectMemberAllList();
		
		//데이터 반환 + 페이지 이동 (1또는 2 방법, 본인이 하고싶은걸로 하기)
		//1. Model  -> 데이터 반환만 하기 때문에 return 구문을 통해서 이동할 페이지를 명시 해야 함
		//2. ModelAndView -> 데이터 반환 + 페이지 이동을 정의할 수 있으며, return시 ModelAndView를 리턴 해주어야 한다.  (지금은 이 방법으로 해볼것)
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list",list);
		mav.setViewName("member/memberAllList");   //ViewResolver에 의해서 경로가 최종 완성됨
		return mav; 
	}
	@RequestMapping(value="/adminEndYNChange.do")
	public String adminEndYNChange(@RequestParam int userNo, @RequestParam char endYN,
			@SessionAttribute("member")Member m,
			HttpServletResponse response)throws IOException //<- 이게 있어야 ajax로 뷰 페이지에 전송 가능.
	{
		System.out.println("[/adminEndYNChange.do] 정상 호출");
		System.out.println("userNo : " +userNo);
		System.out.println("endYN : " + endYN);
		if(0<=m.getUserNo()&& m.getUserNo()<=100){
			//관리자가 요청한게 맞으면 처리
			if(endYN=='N') endYN='Y';    //N->Y
			else if(endYN=='Y') endYN='N'; //Y->N으로 버튼을 바꿔라
			
			//비즈니스 로직 처리
			int result = mService.updateEndYNStateChange(userNo,endYN);
			if(result>0){
				System.out.println("사용자 탈퇴 상태 정보 변경 성공");
				response.getWriter().print(true); //입출력으로 IOException발생 , 그래서 위에 throws IOException 붙여줌
			}else{
				System.out.println("사용자 탈퇴 상태 정보 변경 실패");
				response.getWriter().print(false);
			}
		}else{
			//관리자가 아닌 사용자가 요청하면 미처리
			response.getWriter().print(false);
		}
		return null;
	}
	/*
	@RequestMapping(value="/securityTest.do")     데이터 확인용
	public void securityTest(@RequestParam String userPwd){
		
		//테스트용 메소드(추후 완성되면 삭제 예정)
		try {
			System.out.println("[/securityTest.do]: " +new Sha256Util().encryData(userPwd));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}*/
}
