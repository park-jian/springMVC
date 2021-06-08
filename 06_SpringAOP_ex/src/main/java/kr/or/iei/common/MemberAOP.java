package kr.or.iei.common;

import org.aspectj.lang.JoinPoint;

import kr.or.iei.member.model.vo.Member;

public class MemberAOP {
	
	//before용 - MemberServiceImpl에서 로그인 메소드가 호출되기 전
	public void loginLog(JoinPoint jp){
		System.out.println( "[MemberAOP:로그인시도]메소드 호출전 동작");
		
		//데이터를 가져와서 출력하려면 ->JoinPoint jp객체 사용
		Object [] object = jp.getArgs(); //selectLoginMember매개변수인 Member객체를 잠시 가져오는 역할,selectLoginMember 매개변수가 1개이니 배열안에 1개 들어가 있음.
		if(object.length>0){
			Member m = (Member)object[0];
			System.out.println("[MemberAOP:로그인시도]"+m.getUserId()+"/"+m.getUserPwd()+"로그인시도");
		}
		
	}
	//after용 - 로그인이 정상 호출되면
	public void loginResultLog(){
		//로그인 성공 : ID/PW가 DB 데이터와 일치했을때 - Service처리 메소드 입장에서는 정상종료
		//로그인 실패 : ID/PW가 DB 데이터와 일치하지 않았을때 - Service처리 메소드 입장에서는 정상종료
		System.out.println("[MemberAOP]: 로그인 로직 정상 종료");
	}

	
	public void withDrawLog(){
		/*String methodName = jp.getSignature().getName();
		System.out.println( methodName + "메소드 호출 동작");
		if(obj instanceof Member){
			Member m = (Member)obj;
			System.out.println("[회원 탈퇴처리] userNo : " + m.getUserNo()+"번 유저 탈퇴 신청 - 관리자 확인 해주세요.");
		}*/
	}
}
