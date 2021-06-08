package kr.or.iei.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import kr.or.iei.member.model.vo.Member;

public class Log4jAdvice {
	public void printLoggin(){
		System.out.println("[공통로그-Log4j] 비즈니스 로직 수행전 동작 로그 출력");
	}
	public void printTransactionLog(){
		System.out.println("[드랜잭션 로그 -Log4j] 트랜잭션 처리 완료(로그 출력)");
	}
	
	public Object aroundLog(ProceedingJoinPoint pjp)throws Throwable{
		System.out.println("[Around] - Target Method 동작전 호출");
		Object returnObj = pjp.proceed();
		System.out.println("[Around] - Target Method 동작후 호출");
		return returnObj;
	}
	
	
	public void beforeLog(JoinPoint jp){  //JoinPoint jp은 호출된 정보를 담고 있음
		//getSignature 메소드는 리턴타입, 메소드 이름, 매개변수 정보를 담고 있는 객체를 가져옴
		String methodName = jp.getSignature().getName(); //호출된 메소드의 이름을 가져옴
		Object [] args = jp.getArgs(); //비즈니스 호출시 넘겨받은 매개변수들을 가져올 수 있음.
		System.out.println("[Before]" + methodName + "메소드 호출전 동작");
		if(args.length>0){
			System.out.println("[Before] 매개변수 :" + args[0]);
		}
		
	}
	public void afterLog(JoinPoint jp){  //JoinPoint jp은 호출된 정보를 담고 있음
		//getSignature 메소드는 리턴타입, 메소드 이름, 매개변수 정보를 담고 있는 객체를 가져옴
		String methodName = jp.getSignature().getName(); //호출된 메소드의 이름을 가져옴
		System.out.println("[After]" + methodName + "메소드 호출후(무조건) 동작");
		
	}
	public void afterReturningLog(JoinPoint jp, Object obj){
		String methodName= jp.getSignature().getName();//호출된 메소드의 이름을 가져옴.
		System.out.println("[AfterReturningLog]" + methodName + "메소드 호출후(성공했을때) 동작");
		if(obj instanceof Member){ //obj객체가 Member타입과 일치한다면 이라는 의미
			Member m = (Member)obj;
			System.out.println("[" + m.getUserName()+"] 사용자 로그인 성공(AfterReturningLog)");
		}else{
			System.out.println("[로그인 실패](AfterReturningLog)");
		}	
	}
	
	public void afterThrowingLog(JoinPoint jp,Exception e){
		String methodName= jp.getSignature().getName();//호출된 메소드의 이름을 가져옴.
		System.out.println("[AfterThrowingLog]" + methodName + "메소드 호출후(실패했을때) 동작 ");
		System.out.println("[Exception code] : " + e);
	}
}
