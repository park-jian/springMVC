package kr.or.kh.test.ano;

import org.springframework.stereotype.Component;

@Component("appleSpeaker")
public class AppleSpeaker implements Speaker {
	public AppleSpeaker(){
		System.out.println("애플 스피커 객체 생성 완료!!");
	}
	
	public void powerOn(){
		System.out.println("Apple Speaker==> 전원이 켜졌습니다.");
		
	}
	public void powerOff(){
		System.out.println("Apple Speaker==> 전원이 꺼졌습니다.");
	}
}
