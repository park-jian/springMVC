package kr.or.kh.test.ano;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//Component라는 어노테이션은 스프링 컨테이너가 해당 어노테이션을 스캔하게 되면 메모리상에 객체를 생성하여 놓겠다라는 의미
@Component("com")
public class Computer {
	
	//@Autowired
	//@Qualifier("sonySpeaker") <--여기가 소니스피커인지 애플스피커인지 명시해주면 스피커가 누군인지 알려줄 수 있다.
	//@Qualifier("appleSpeaker")   
	@Resource(name="sonySpeaker")
	Speaker speaker;    //의존성 주입하는 방법
	
	public Computer(){
		System.out.println("컴퓨터 객체가 생성 되었습니다.");
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	//현재 kr.or.iei라는 패키지는 프로젝트를 새롭게 만들때 스캔 대상으로 자동으로 올라가게 되어 있기 때문!
	
	
}
