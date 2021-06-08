package kr.or.iei.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.or.iei.member.model.vo.Member;

@Service     //메모리상에 올라가야 쓸수 있으므로 해줘야 함.
@Aspect
public class MemberAOP {
	
	@Autowired
	@Qualifier("Sha256Util")
	private Sha256Util enc;
	
	@Pointcut("execution(* kr.or.iei.member.model.service.MemberServiceImpl.selectLoginMember(..))")
	public void loginPointcut(){} 

	@Pointcut("execution(* kr.or.iei.member.model.service.MemberServiceImpl.updateWithDrawMember(..))")
	public void withDrawPointcut(){} 
	
	
	@Pointcut("execution(* kr.or.iei.member.model.service.MemberServiceImpl.insertSignupMember(..))")
	public void signupEncryption(){}
	
	
	@Pointcut("execution(* kr.or.iei.member.model.service.MemberServiceImpl.updateMember(..))")
	public void updateMemberEncryption(){}
	
	@Before("updateMemberEncryption()")
	public void updateMemberEncryption(JoinPoint jp) throws Exception
	{
		//비밀번호 정책 : 8~12글자
		//DB에 저장되는 비밀번호는 32Byte
		String userPwd = ((Member)jp.getArgs()[0]).getUserPwd();
		
		//넘어온 userPwd가 64글자가 아니면 (암호화된 데이터가 아니라면) 암호화 하고, 아니면 하지 말아라
		//사용자가 비밀번호를 변경하고 싶다면 userPwd안에는 "3333"같이 64글자가 아닌 글자가 들어있을것.
		//사용자가 비밀번호를 변경하고 싶지 않다면 userPwd안에는 기존비밀번호(암호화된 64글자)가 들어있을것.
		if(userPwd.length() != 64){  
			this.passwordEncryption(jp);
		}
	}
	
	@Before("loginPointcut()")
	public void loginPasswordEncryption(JoinPoint jp) throws Exception{
		this.passwordEncryption(jp);
		/*Member m = ((Member)jp.getArgs()[0]); //회원 가입시 넘어오는 Member객체를 잠시 가져옴
		String userPwd = m.getUserPwd(); //그 중 비밀번호만 꺼내기 (암호화를 위해서)
		
		String encryUserPwd = enc.encryData(userPwd);  //만들어 놓은 알고리즘으로 암호화 처리해서 가져오기
		m.setUserPwd(encryUserPwd); // 다시 Member객체에 암호화된 데이터를 넣어줌
		
		return;
		*/
	}
	
	@Before("signupEncryption()") //암호화는 회원가입 전에 해야 함.
	public void passwordEncryption(JoinPoint jp) throws Exception{
		//현재 사용자가 보내는 정보들은 암호화되지 않은 데이터
		//그 중 비밀번호만 암호화 처리하도록 하겠음
		//회원 가입시 Member객체로 데이터들이 넘어옴
		Member m = ((Member)jp.getArgs()[0]); //회원 가입시 넘어오는 Member객체를 잠시 가져옴
		String userPwd = m.getUserPwd(); //그 중 비밀번호만 꺼내기 (암호화를 위해서)
		
		
		String userId = m.getUserId();//ID값을 추출(Salt로 사용할 값)
		String encryUserPwd = enc.encryData(userPwd,userId);  //만들어 놓은 알고리즘으로 암호화 처리해서 가져오기
		m.setUserPwd(encryUserPwd); // 다시 Member객체에 암호화된 데이터를 넣어줌
		
		return;
		
	}
	
	
	
	//before용 - MemberServiceImpl에서 로그인 메소드가 호출되기 전
	@Before("loginPointcut()")
		public void loginLog(JoinPoint jp){
			System.out.println( "[MemberAOP:로그인시도]메소드 호출전 동작");
			//데이터를 가져와서 출력하려면 ->JoinPoint jp객체 사용
			Object [] object = jp.getArgs(); //selectLoginMember매개변수인 Member객체를 잠시 가져오는 역할,selectLoginMember 매개변수가 1개이니 배열안에 1개 들어가 있음.
			if(object.length>0){
				Member m = (Member)object[0];
				System.out.println("[MemberAOP:로그인시도]"+m.getUserId()+"/"+m.getUserPwd()+"로그인시도");
			}
		}
		//after-returning용 - 로그인이 정상 호출되면
	@AfterReturning(pointcut="loginPointcut()",returning="obj") 
		public void loginResultLog(JoinPoint jp, Object obj){ //JoinPoint jp, Object obj 순서 다르게 하면 에러뜬다.
			//로그인 성공 : ID/PW가 DB 데이터와 일치했을때 - Service처리 메소드 입장에서는 정상종료
			//로그인 실패 : ID/PW가 DB 데이터와 일치하지 않았을때 - Service처리 메소드 입장에서는 정상종료
			
			if(obj instanceof Member){ //리턴값이 Member타입이라면!
				Member m = (Member)obj;
				System.out.println("[MemberAOP : 로그인 성공]" + m.getUserName()+"("+m.getUserId()+")");
				
			}else{    ////리턴값이 Member타입이 아니라면!  (현재 코드상으로 보았을때는 null이 오게 됨) ->리턴값이 없어 JoinPoint jp로 매개변수 가져옴
				Object [] object = jp.getArgs();
				String userId = ((Member)object[0]).getUserId();
				System.out.println("[MemberAOP : 로그인 실패] userId : " + userId);
			}
			
		}
		
		//after-returning   (탈퇴처리가 정상적으로 처리 된 이후에 동작)
	@AfterReturning("withDrawPointcut()")  //"withDrawPointcut()"대신 pointcut="withDrawPointcut()" 이렇게 써도 동일함.
		public void withDrawLog(JoinPoint jp){
			
			Object [] object = jp.getArgs();
			int userNo = ((int)object[0]);
			System.out.println("[MemberAOP:회원탈퇴 처리] userNo" + userNo+"번 유저 탈퇴 신청 - 관리자에게 확인 해주세요.");
			//리턴값으로 userNo를 가져오지 못하므로 JoinPoint를 사용하면 된다.
			
		}
}
