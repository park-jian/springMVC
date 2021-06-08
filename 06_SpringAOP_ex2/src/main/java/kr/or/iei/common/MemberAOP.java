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
		//after-returning용 - 로그인이 정상 호출되면
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
		public void withDrawLog(JoinPoint jp){
			
			Object [] object = jp.getArgs();
			int userNo = ((int)object[0]);
			System.out.println("[MemberAOP:회원탈퇴 처리] userNo" + userNo+"번 유저 탈퇴 신청 - 관리자에게 확인 해주세요.");
			//리턴값으로 userNo를 가져오지 못하므로 JoinPoint를 사용하면 된다.
			
		}
}
