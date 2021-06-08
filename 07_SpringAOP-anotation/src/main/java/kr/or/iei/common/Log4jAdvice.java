package kr.or.iei.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import kr.or.iei.member.model.vo.Member;

@Service     //메모리상에 올라가야 쓸수 있으므로 해줘야 함.
@Aspect
public class Log4jAdvice {
	
	//포인트컷 만들기
	@Pointcut("execution(* kr.or.iei.member.model.service.*ServiceImpl.*(..))")
	public void allPointcut(){}  //xml에서 포인트컷 생성시 id="" 의 id부분에 해당
	
	
	public void printLoggin(){
		System.out.println("[공통로그-Log4j] 비즈니스 로직 수행전 동작 로그 출력 - ano");
	}
	@After("allPointcut()") //호출후
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
	@AfterReturning(pointcut="allPointcut()",returning="obj") 
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
	@AfterThrowing(pointcut="allPointcut()",throwing="e")
	public void afterThrowingLog(JoinPoint jp,Exception e){
		String methodName= jp.getSignature().getName();//호출된 메소드의 이름을 가져옴.
		System.out.println("[AfterThrowingLog]" + methodName + "메소드 호출후(실패했을때) 동작 ");
		System.out.println("[Exception code] : " + e);
	}
}
